import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

// beans
import { Reimbursement, ReimbursementWrapper } from '../beans/reimbursement';

// other services
import { AlertService, AlertMessage } from './alert.service';
import { ApiService } from './api.service';


@Injectable()
export class ReimbursementsService {

    private http: HttpClient;
    private alertService: AlertService;
    private apiService: ApiService

    private api: string;
    private listSubject: BehaviorSubject<Reimbursement[]>;
    private saveSubject: Subject<Reimbursement>;
    private selectedSubject: Subject<Reimbursement>;


    constructor(httpClient: HttpClient, alertService: AlertService, apiService: ApiService) {
        this.http = httpClient;
        this.apiService = apiService;
        this.alertService = alertService;

        this.api = 'reimbursements';
        this.listSubject = new BehaviorSubject([]);
        this.saveSubject = new Subject();
        this.selectedSubject = new Subject();
    }

    public save(reimbursement: Reimbursement): void {
        console.log('attempting to save new reimbursement');

        if ( reimbursement.id === 0 ) {
            this.post(reimbursement);
        } else {
           this.put(reimbursement);
        }
    }



    /*
    * BEGIN: observables
    */
    public setSelected(reim: Reimbursement): void {
        this.selectedSubject.next(reim);
    }

    public getSelected(): Observable<Reimbursement> {
        return this.selectedSubject.asObservable();
    }

    public getList(): Observable<Reimbursement[]> {
        return this.listSubject.asObservable();
    }

    public clearList(): void {
        this.listSubject.next([]);
    }

    public getSaved(): Observable<Reimbursement> {
        return this.saveSubject.asObservable();
    }


    /*
    * BEGIN: CRUD
    */
    public getAll(): void {
        const url = this.apiService.getApiUrl(this.api);

        this.http.get<Reimbursement[]>( url, { withCredentials: true })
            .subscribe( (reimbursements) => {
                this.listSubject.next(reimbursements);
            });
    }

    public post(reimbursement: Reimbursement): void {
        const url = this.apiService.getApiUrl(this.api);
        const dataObject = ReimbursementWrapper.prepareForDao(reimbursement);
        const data = JSON.stringify( dataObject );

        this.http.post<Reimbursement>( url, data, { withCredentials: true })
            .subscribe( (savedReimbursement) => {
                this.saveSubject.next(savedReimbursement);
                this.getAll();
                this.broadcast('saved new reimbursement successfully', AlertMessage.CATEGORY_SUCCESS);
            });
    }

    public put(reimbursement: Reimbursement): void {
        const url = this.apiService.getApiUrl(this.api, [reimbursement.id]);
        const dataObject = ReimbursementWrapper.prepareForDao(reimbursement);
        const data = JSON.stringify( dataObject );

        this.http.put<Reimbursement>( url, data, { withCredentials: true })
            .subscribe( (updatedReimbursement) => {
                this.saveSubject.next(updatedReimbursement);
                this.broadcast('updated reimbursement successfully', AlertMessage.CATEGORY_SUCCESS);
            });
    }

    private broadcast(message: string, category: string) {
        this.alertService.push(message, category);
        console.log(message);
    }

}

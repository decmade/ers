import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';

// beans
import { Reimbursement, ReimbursementWrapper } from '../beans/reimbursement';
import { Servlet } from '../beans/servlet';

// other services
import { AlertService, AlertMessage } from './alert.service';


@Injectable()
export class ReimbursementsService {

    private client: HttpClient;
    private url: string;
    private listSubject: Subject<Reimbursement[]>;
    private alertService: AlertService;
    private saveSubject: Subject<Reimbursement>;


    constructor(client: HttpClient, alertService: AlertService) {
        this.client = client;
        this.url = Servlet.getServiceUrl('reimbursements');
        this.listSubject = new Subject();
        this.saveSubject = new Subject();
        this.alertService = alertService;
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
    public getList(): Observable<Reimbursement[]> {
        this.getAll();

        return this.listSubject;
    }

    public getSaved() {
        return this.saveSubject;
    }


    /*
    * BEGIN: CRUD
    */
    private getAll(): void {
        this.client.get<Reimbursement[]>( this.url, { withCredentials: true })
            .subscribe( (reimbursements) => {
                this.listSubject.next( reimbursements );
            });
    }

    private post(reimbursement: Reimbursement): void {
        const url = this.url;
        const dataObject = ReimbursementWrapper.prepareForDao(reimbursement);
        const data = JSON.stringify( dataObject );

        this.client.post<Reimbursement>( url, data, { withCredentials: true })
            .subscribe( (savedReimbursement) => {
                this.saveSubject.next(savedReimbursement);
                this.getAll();
                this.broadcast('saved new reimbursement successfully', AlertMessage.CATEGORY_SUCCESS);
            });
    }

    private put(reimbursement: Reimbursement): void {
        const url = [ this.url, reimbursement.id ].join('/');
        const dataObject = ReimbursementWrapper.prepareForDao(reimbursement);
        const data = JSON.stringify( dataObject );

        this.client.put<Reimbursement>( url, data, { withCredentials: true })
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

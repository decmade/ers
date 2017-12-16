import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

// beans
import { ReimbursementStatus } from '../beans/reimbursement';

// services
import { ApiService } from './api.service';


@Injectable()
export class ReimbursementStatusService {
    private http: HttpClient;
    private apiService: ApiService;

    private api: string;
    private listSubject: BehaviorSubject<ReimbursementStatus[]>;

    constructor(httpClient: HttpClient, apiService: ApiService) {
        this.http = httpClient;
        this.apiService = apiService;
        
        
        this.listSubject = new BehaviorSubject([]);
        this.api = 'reimbursementstatuses';

        this.getAll();
    }

    /*
    * BEGIN: observables
    */
    public getList(): Observable<ReimbursementStatus[]> {
        return this.listSubject;
    }

    public getAll(): void {
        const url = this.apiService.getApiUrl(this.api);

        this.http.get<ReimbursementStatus[]>( url, { withCredentials: true })
            .subscribe( (statuses) => this.listSubject.next(statuses) );
    }

}

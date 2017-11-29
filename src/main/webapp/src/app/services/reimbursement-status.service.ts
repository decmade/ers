import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Subject } from 'rxjs/Subject';

// beans
import { ReimbursementStatus } from '../beans/reimbursement';
import { Servlet } from '../beans/servlet';


@Injectable()
export class ReimbursementStatusService {
    private client: HttpClient;
    private url: string;
    private listSubject: Subject<ReimbursementStatus[]>;

    constructor(client: HttpClient) {
        this.client = client;
        this.url = Servlet.getServiceUrl('reimbursementstatuses');
        this.listSubject = new Subject();

        this.getAll();
    }

    /*
    * BEGIN: observables
    */
    public getList(): Observable<ReimbursementStatus[]> {
        this.getAll();
        
        return this.listSubject;
    }


    /*
    * BEGIN: CRUD
    */
    private getAll(): void {
        this.client.get<ReimbursementStatus[]>( this.url, { withCredentials: true })
            .subscribe( (statuses) => this.listSubject.next( statuses ) );
    }

}

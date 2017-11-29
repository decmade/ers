import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/take';

// beans
import { ReimbursementType } from '../beans/reimbursement';
import { Servlet } from '../beans/servlet';


@Injectable()
export class ReimbursementTypeService {
    private client: HttpClient;
    private url: string;
    private listSubject: Subject<ReimbursementType[]>;

     constructor(client: HttpClient) {
        this.client = client;
        this.url = Servlet.getServiceUrl('reimbursementtypes');

        this.listSubject = new Subject();
    }


    /*
    * BEGIN: observables
    */
    public getList(): Observable<ReimbursementType[]> {
        this.getAll();

        return this.listSubject;    }

    public get(id: string): Observable<ReimbursementType> {
        const url = [ this.url, id].join('/');

        return this.client.get<ReimbursementType>(url, { withCredentials: true });
    }


    /*
    * BEGIN: CRUD
    */
    private getAll(): void {
        this.client.get<ReimbursementType[]>( this.url, { withCredentials: true })
            .subscribe( (types) => {
                this.listSubject.next(types);
            });
    }




}

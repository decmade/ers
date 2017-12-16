import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/take';

// beans
import { ReimbursementType } from '../beans/reimbursement';

import { ApiService } from './api.service';

@Injectable()
export class ReimbursementTypeService {
    private http: HttpClient;
    private apiService: ApiService;
    
    private api: string;
    private listSubject: BehaviorSubject<ReimbursementType[]>;

     constructor(httpClient: HttpClient, apiService: ApiService) {
        this.http = httpClient;
        this.apiService = apiService;
        
        this.api = 'reimbursementtypes';
        this.listSubject = new BehaviorSubject([]);

        this.getAll();
    }


    /*
    * BEGIN: observables
    */
    public getList(): Observable<ReimbursementType[]> {
        return this.listSubject;    
    }

    public get(id: string): Observable<ReimbursementType> {
        const url = this.apiService.getApiUrl(this.api, [id] );

        return this.http.get<ReimbursementType>(url, { withCredentials: true });
    }

    public getAll(): void {
        const url = this.apiService.getApiUrl(this.api);

        this.http.get<ReimbursementType[]>( url, { withCredentials: true })
            .subscribe( (types) => {
                this.listSubject.next(types);
            });
    }




}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

// beans
import { User } from '../beans/user';

// services
import { ApiService } from './api.service';

@Injectable()
export class UsersService {
    private http: HttpClient;
    private apiService: ApiService;
    
    private api: string;
    private listSubject: BehaviorSubject<User[]>;

    constructor(httpClient: HttpClient, apiService: ApiService) {
      this.http = httpClient;
      this.apiService = apiService;

      this.api = 'users';
      this.listSubject = new BehaviorSubject([]);

      this.getAll();
    }

    /*
    * BEGIN: observables
    */
    public getList(): Observable<User[]> {
      return this.listSubject;
    }

    /*
    * BEGIN: CRUD
    */
    public getAll(): void {
      const url = this.apiService.getApiUrl(this.api);

      this.http.get<User[]>( url, { withCredentials: true })
        .subscribe( (users) => this.listSubject.next(users) );
    }

}

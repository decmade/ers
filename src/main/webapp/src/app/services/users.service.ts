import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Subject } from 'rxjs/Subject';

// beans
import { User } from '../beans/user';
import { Servlet } from '../beans/servlet';


@Injectable()
export class UsersService {
    private client: HttpClient;
    private url: string;
    private listSubject: Subject<User[]>;

    constructor(client: HttpClient) {
      this.client = client;
      this.url = Servlet.getServiceUrl('users');
      this.listSubject = new Subject();
    }


    /*
    * BEGIN: observables
    */
    public getList(): Observable<User[]> {
      this.getAll();

      return this.listSubject;
    }


    /*
    * BEGIN: CRUD
    */
    private getAll(): void {
      this.client.get<User[]>( this.url, { withCredentials: true })
        .subscribe( (users) => this.listSubject.next(users) );
    }

}

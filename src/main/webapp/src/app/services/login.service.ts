import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Routes, Router, RouterLink } from '@angular/router';

// rxjs
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/share';

// beans
import { User } from '../beans/user';
import { Servlet } from '../beans/servlet';

// other services
import { AlertService, AlertMessage } from './alert.service';


@Injectable()
export class LoginService {
    private static USER_KEY = 'login-user';
    private client: HttpClient;
    private url: string;
    private result: Observable<User>;
    private alertService: AlertService;
    private subject: Subject<User>;
    private router: Router;

    constructor(client: HttpClient, alertService: AlertService, router: Router) {
        this.router = router;
        this.client = client;
        this.url = Servlet.getServiceUrl('login');
        this.alertService = alertService;
        this.subject = new Subject();
    }

    public login(identity: string, credential: string): void {
      this.post(identity, credential);
    }

    public logout(): void {
      this.delete();
    }

    /*
    * BEGIN: observables
    */
    public getCurrentUser(): Observable<User> {
      this.get();

      return this.subject;
    }


    /*
    * BEGIN: CRUD
    */
    private get(): void {
      this.client.get<User>(this.url, { withCredentials: true })
        .subscribe( (user) => {
          this.subject.next(user);
        });
    }

    private post(identity: string, credential: string): void {
      console.log('attempting to authenticate user');

      const body = JSON.stringify({
          identity: identity,
          credential: credential
       });

      this.client.post<User>(this.url, body, { withCredentials: true }).subscribe( (user) => {
        this.subject.next( user );

        this.broadcast(`user ${user.identity} authenticated successfully`, AlertMessage.CATEGORY_SUCCESS);
      }, (error) => {
        console.log('user could not be authenticated');
        this.subject.next( null );

        this.broadcast('authentication failed', AlertMessage.CATEGORY_ERROR);
      });
    }

    private delete(): void {
      this.client.delete<User>(this.url, { withCredentials: true } ).subscribe( (user) => {
        this.subject.next( null );

        this.router.navigate([ 'home' ]);

        this.broadcast(`user ${user.identity} signed out successfully`, AlertMessage.CATEGORY_INFO);
      });
    }

    private broadcast(message: string, category: string) {
        this.alertService.push(message, category);
        console.log(message);
    }

}

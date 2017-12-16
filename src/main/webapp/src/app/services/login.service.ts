import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Routes, Router, RouterLink } from '@angular/router';

// rxjs
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/share';

// beans
import { User } from '../beans/user';

// other services
import { AlertService, AlertMessage } from './alert.service';
import { ApiService } from './api.service';
import { ReimbursementsService } from './reimbursements.service';


@Injectable()
export class LoginService {
    private http: HttpClient;
    private apiService: ApiService;
    private alertService: AlertService;
    private router: Router;
    private reimService: ReimbursementsService;

    private api: string; 
    private currentUserSubject: BehaviorSubject<User>;
    

    constructor(
      httpClient: HttpClient, 
      alertService: AlertService, 
      router: Router, 
      apiService:ApiService,
      reimbursementService: ReimbursementsService
    ) {
        this.router = router;
        this.http = httpClient;
        this.alertService = alertService;
        this.apiService = apiService;
        this.reimService = reimbursementService;

        this.api = 'login';
        this.currentUserSubject = new BehaviorSubject(null);
        this.get();
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
       return this.currentUserSubject;
    }

    private setCurrentUser(user: User): void {
      this.currentUserSubject.next(user);
      
      if ( user ) {
        this.reimService.getAll();
      } else {
        this.reimService.clearList();
      }
    }

    /*
    * BEGIN: CRUD
    */
    private get(): void {
      const url = this.apiService.getApiUrl(this.api);

      this.http.get<User>(url, { withCredentials: true })
        .subscribe( (user) => {
            this.setCurrentUser(user);
        });
    }

    private post(identity: string, credential: string): void {
      const url = this.apiService.getApiUrl(this.api);
      const data = JSON.stringify({
          identity: identity,
          credential: credential
       });

       console.log('attempting to authenticate user');

      this.http.post<User>(url, data, { withCredentials: true })
        .subscribe( (user) => {
          console.log('user authenticated successfully');
          this.setCurrentUser(user);
          this.broadcast(`user ${user.identity} authenticated successfully`, AlertMessage.CATEGORY_SUCCESS);
        }, (error) => {
          console.log('user could not be authenticated');
          this.currentUserSubject.next(null);
          this.reimService.clearList();
          this.broadcast('authentication failed', AlertMessage.CATEGORY_ERROR);
        });
    }

    private delete(): void {
      const url = this.apiService.getApiUrl(this.api);

      this.http.delete<User>(url, { withCredentials: true } )
        .subscribe( (user) => {
          this.setCurrentUser(null);
          this.router.navigate([ 'home' ]);
          this.broadcast(`user ${user.identity} signed out successfully`, AlertMessage.CATEGORY_INFO);
      });
    }

    private broadcast(message: string, category: string) {
        this.alertService.push(message, category);
        console.log(message);
    }

}

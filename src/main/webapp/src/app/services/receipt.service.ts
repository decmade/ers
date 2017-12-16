import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';

// beans
import { Receipt, ReceiptWrapper } from '../beans/receipt';

// other services
import { AlertService, AlertMessage } from './alert.service';
import { ApiService } from './api.service';


@Injectable()
export class ReceiptService {
    private http: HttpClient;
    private alertService: AlertService;
    private savedSubject: Subject<Receipt>;
    private apiService: ApiService;

    private api: string;

    constructor (httpClient: HttpClient, alertService: AlertService, apiService: ApiService) {
      this.http = httpClient;
      this.alertService = alertService;
      this.apiService = apiService;

      this.savedSubject = new Subject();
      this.api = 'receipts';
    }

    public save(receipt: File): void {
        const form = new FormData();
        const url = this.apiService.getApiUrl(this.api);

        console.log('attempting to upload a new receipt document');

        form.append('file', receipt, receipt.name);

        this.http.post<Receipt>(url, form, { withCredentials: true })
            .subscribe( (savedReceipt) => {
                this.savedSubject.next(savedReceipt);
                this.broadcast('receipt uploaded successfully', AlertMessage.CATEGORY_SUCCESS);
            });
    }

    /*
    * BEGIN: observables
    */
    public getSaved(): Observable<Receipt> {
        return this.savedSubject;
    }

    private broadcast(message: string, category: string) {
        this.alertService.push(message, category);
        console.log( message );
    }

}

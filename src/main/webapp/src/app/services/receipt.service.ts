import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';

// beans
import { Servlet } from '../beans/servlet';
import { Receipt, ReceiptWrapper } from '../beans/receipt';

// other services
import { AlertService, AlertMessage } from './alert.service';



@Injectable()
export class ReceiptService {
    private client: HttpClient;
    private url: string;
    private alertService: AlertService;
    private savedSubject: Subject<Receipt>;

    constructor (client: HttpClient, alertService: AlertService) {
      this.client = client;
      this.url = Servlet.getServiceUrl('receipt');
      this.alertService = alertService;
      this.savedSubject = new Subject();
    }

    public save(receipt: File): void {
        const form = new FormData();

        console.log('attempting to upload a new receipt document');

        form.append('file', receipt, receipt.name);

        this.client.post<Receipt>(this.url, form, { withCredentials: true })
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

import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class AlertService {
    private subject: Subject<AlertMessage>;

    constructor() {
        this.subject = new Subject();
    }

    public push(message: string, category: string): void {
        const alert = new AlertMessage(message, category);
        this.subject.next( alert );
    }

    /*
    * BEGIN: observables
    */
    public getNotifications(): Observable<AlertMessage> {
        return this.subject.asObservable();
    }
}

export class AlertMessage {
    private static idCounter = 0;

    public static CATEGORY_INFO = 'info';
    public static CATEGORY_SUCCESS = 'success';
    public static CATEGORY_ERROR = 'error';
    public static CATEGORY_WARNING = 'warning';
    public static CATEGORY_DEFAULT = 'default';

    public id: number;
    public message: string;
    public category: string;

    constructor(message: string, category: string) {
        this.message = message;
        this.category = category;
        this.id = ++AlertMessage.idCounter;
    }
}

import { Component, OnInit, Input } from '@angular/core';

// rxjs
import { Subscription } from 'rxjs/Subscription';

// services
import { AlertService, AlertMessage } from '../../services/alert.service';

// jquery
import * as $ from 'jquery';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})

export class NotificationsComponent implements OnInit {
    private alerts: AlertMessage[];
    private alertService: AlertService;
    private subscription: Subscription;

    constructor(alertService: AlertService) {
        this.alertService = alertService;
        this.alerts = [];
    }

    public getAlertClass(alert: AlertMessage): string {
        const classes = [
            'show',
            'alert',
            'alert-dismissable',
            'm-0',
            'mb-1'
        ];

        switch ( alert.category ) {
            case AlertMessage.CATEGORY_SUCCESS :
                classes.push('alert-success');
                break;
            case AlertMessage.CATEGORY_INFO :
                classes.push('alert-info');
                break;
            case AlertMessage.CATEGORY_ERROR :
                classes.push('alert-danger');
                break;
            case AlertMessage.CATEGORY_WARNING :
                classes.push('alert-warning');
                break;
            default :
                classes.push('alert-secondary');
        }

        return classes.join(' ');
    }

    public remove(alert: AlertMessage): void {
        this.alerts = this.alerts.filter( a => a.id !== alert.id);
    }

    private addAlert(alert: AlertMessage) {
        this.alerts.push(alert);

        setTimeout( () => {
            this.remove( alert );
        }, 10000);

        $(window).scrollTop(0);
    }

  ngOnInit() {
      this.subscription = this.alertService.getNotifications().subscribe( (alert) => this.addAlert(alert) );
  }

}

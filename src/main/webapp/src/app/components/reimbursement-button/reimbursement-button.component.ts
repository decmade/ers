import { Component, OnInit, Input } from '@angular/core';

// services
import { AuthorizationService } from '../../services/authorization.service';
import { LoginService } from '../../services/login.service';

// beans
import { Reimbursement, ReimbursementWrapper } from '../../beans/reimbursement';
import { User } from '../../beans/user';
import { AclResponse, AclRequest } from '../../beans/acl';



@Component({
    selector: 'app-reimbursement-button',
    templateUrl: './reimbursement-button.component.html',
    styleUrls: ['./reimbursement-button.component.css']
})

export class ReimbursementButtonComponent implements OnInit {
    @Input() reimbursement: Reimbursement;

    constructor() {
    }

    public getIconClass(): string {
        switch ( this.reimbursement.state ) {
            case ReimbursementWrapper.STATE_APPROVE :
                return 'fa fa-check';
            case ReimbursementWrapper.STATE_UPDATE :
            case ReimbursementWrapper.STATE_CREATE :
                return 'fa fa-pencil';
            default :
                return 'fa fa-eye';
        }
    }

    public getButtonClass(): string {
        switch ( this.reimbursement.state ) {
            case ReimbursementWrapper.STATE_APPROVE :
                return 'btn btn-primary';
            case ReimbursementWrapper.STATE_CREATE :
            case ReimbursementWrapper.STATE_UPDATE:
                return 'btn btn-warning';
            default :
                return 'btn btn-outline-secondary';
        }
    }


    ngOnInit() {
    }
}

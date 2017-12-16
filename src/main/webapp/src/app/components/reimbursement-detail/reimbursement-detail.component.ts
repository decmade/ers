import { Component, OnInit, Input, ViewChild, OnDestroy, ElementRef } from '@angular/core';
import { DatePipe } from '@angular/common';

// rxjs
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { share } from 'rxjs/operators';

// beans
import { Reimbursement, ReimbursementWrapper, ReimbursementType, ReimbursementStatus } from '../../beans/reimbursement';
import { User } from '../../beans/user';
import { Servlet } from '../../beans/servlet';
import { Receipt } from '../../beans/receipt';

// services
import { AlertService, AlertMessage } from '../../services/alert.service';
import { ReimbursementsService } from '../../services/reimbursements.service';
import { ReimbursementTypeService } from '../../services/reimbursement-type.service';
import { ReimbursementStatusService } from '../../services/reimbursement-status.service';
import { ReceiptService } from '../../services/receipt.service';
import { LoginService } from '../../services/login.service';

import * as any from 'jquery';

@Component({
    selector: 'app-reimbursement-detail',
    templateUrl: './reimbursement-detail.component.html',
    styleUrls: ['./reimbursement-detail.component.css']
})

export class ReimbursementDetailComponent implements OnInit, OnDestroy {
    private reimbursement: Reimbursement;
    
    @ViewChild('receiptUpload') receiptUpload: ElementRef;
    @ViewChild('reimForm') form: ElementRef;
    @ViewChild('reimModal') modal: ElementRef;

    private loginService: LoginService;
    private reimService: ReimbursementsService;
    private reimTypeService: ReimbursementTypeService;
    private reimStatusService: ReimbursementStatusService;
    private receiptService: ReceiptService;
    private alertService: AlertService;
    private reimTypes: ReimbursementType[];
    private reimStatuses: ReimbursementStatus[];
    private reimCopy: Reimbursement;
    private datePipe: DatePipe;
    private user: User;
    private userSubscription: Subscription;
    private selectedReimbursementSubscription: Subscription;
    private savedReceiptsSubscription: Subscription;
    private savedReimbursementsSubscription: Subscription;
    private reimbursementStatusListSubscription: Subscription;
    private reimbursementTypeListSubscription: Subscription;

    constructor (
        loginService: LoginService,
        reimService: ReimbursementsService,
        reimTypeService: ReimbursementTypeService,
        reimStatusService: ReimbursementStatusService,
        receiptService: ReceiptService,
        alertService: AlertService,
        datePipe: DatePipe
    ) {
        this.loginService = loginService;
        this.reimTypeService = reimTypeService;
        this.reimStatusService = reimStatusService;
        this.reimService = reimService;
        this.alertService = alertService;
        this.receiptService = receiptService;
        this.reimTypes = [];
        this.reimStatuses = [];
        this.datePipe = datePipe;
        this.reimbursement = new Reimbursement();
        this.reimCopy = new Reimbursement();
    }

    public getTypeClass(): any {
        return {
            disabled: ( this.reimbursement.state !== ReimbursementWrapper.STATE_UPDATE )
        };
    }

    public getReceiptUrl(): string {
        if ( this.reimCopy.receipt ) {
            return Servlet.getServiceUrl( this.reimCopy.receipt.url );
        } else {
            return '';
        }
    }

    public onTypeSelect(id: string): void {
        this.reimTypeService.get(id).subscribe( (type) => {
            this.reimbursement.type = type;
        });
    }

    public onStatusSelect(statusId: string): void {
        this.reimbursement.status = this.getStatusById( Number(statusId) );
        this.reimbursement.resolver = this.user;
        this.reimbursement.resolved = this.datePipe.transform(new Date(), 'yyyy-MM-ddThh:mm:ss.S');
        console.log(this.reimbursement);
    }

    public onSave() {

        if ( this.validateForm() ) {
            if ( this.inReceiptUpdateState() ) {
               this.uploadReceipt();
            } else {
                this.saveReimbursement();
            }
        } else {
            this.alertService.push('reimbursement form validation failed', AlertMessage.CATEGORY_ERROR);
        }
    }

    public onCancel() {
        this.unPark();
    }

    public inUpdateState(): boolean {
        return ( this.reimbursement.state === ReimbursementWrapper.STATE_UPDATE );
    }

    public inApproveState(): boolean {
        return ( this.reimbursement.state === ReimbursementWrapper.STATE_APPROVE );
    }

    public inEditableState(): boolean {
        switch ( true ) {
            case ( this.inUpdateState() ) :
            case ( this.inApproveState() ) :
            case ( this.inReceiptUpdateState() ) :
                return true;
            default :
                return false;
        }
    }

    public inReceiptUpdateState() {
        switch ( true ) {
            case ( this.reimbursement.state === ReimbursementWrapper.STATE_UPDATE_RECEIPT ) :
            case ( this.isNew() ) :
                return true;
            default :
                return false;
        }
    }

    public isNew(): boolean {
        switch (true) {
            case ( this.reimbursement === null ) :
            case ( this.reimbursement === undefined ) :
                return false;
            case ( this.reimbursement.id === 0 ) :
            case ( this.reimbursement.author === undefined ) :
                return true;
            default :
                return false;
        }
    }

    public setReceiptUpdateState(): void {
        this.reimbursement.state = ReimbursementWrapper.STATE_UPDATE_RECEIPT;
    }

    private uploadReceipt(): void {
        const fileBrowser = this.receiptUpload.nativeElement;
        let uploadedReceipt: File;

        if ( fileBrowser.files && fileBrowser.files.length > 0 ) {
            uploadedReceipt = fileBrowser.files[0];

            this.receiptService.save( uploadedReceipt );
        } else {
            this.alertService.push('you MUST provide a receipt file', AlertMessage.CATEGORY_ERROR);
        }
    }

    private saveReimbursement(): void {
        this.reimService.save(this.reimbursement);
    }

    private setReimbursement(reim: Reimbursement): void {
        const modal = $(this.modal.nativeElement);

        
        this.reimbursement = reim;
        this.park();
        modal.modal('show');
    }

    private getTypeById(id: number): ReimbursementType {
        return this.reimTypes.filter( rType => rType.id === id)[0];
    }

    private getStatusById(id: number): ReimbursementStatus {
        return this.reimStatuses.filter( rStatus => rStatus.id === id)[0];
    }

    private park(): void {
        Object.assign(this.reimCopy, this.reimbursement);
    }

    private unPark(): void {
        Object.assign(this.reimbursement, this.reimCopy);
    }

    private validateForm(): boolean {
        const form = this.form.nativeElement;
  
        switch (true) {
            case ( form.checkValidity() === false ):
                $(form).addClass('was-validated');
                return false;
            default :
                return true;
        }
    }

    private validateObject() {
        const object = this.reimbursement;

        switch (true) {
            case ( object === null ) :
            case ( object === undefined ) :
                this.alertService.push('reimbursement MUST be instantiated', AlertMessage.CATEGORY_ERROR);
                return false;
            case ( object.amount === undefined ) :
            case ( object.amount === 0 ) :
                this.alertService.push('reimbursement MUST have a requested amount', AlertMessage.CATEGORY_ERROR);
                return false;
            case ( object.author === undefined ) :
            case ( object.author === null ) :
                this.alertService.push('reimbursement is not detecting you as the author', AlertMessage.CATEGORY_ERROR);
                return false;
            case ( object.type === undefined ) :
            case ( object.type === null ) :
                this.alertService.push('reimbursement MUST have a type', AlertMessage.CATEGORY_ERROR);
                return false;
            case ( object.description === undefined ) :
            case ( object.description === null ) :
            case ( object.description.trim().length === 0 ) :
                this.alertService.push('reimbursement MUST have a description', AlertMessage.CATEGORY_ERROR);
                return false;
            default :
                return true;
        }
    }


    ngOnInit(): void {
        this.selectedReimbursementSubscription = this.reimService.getSelected()
            .subscribe( (reim) => this.setReimbursement(reim) );

        this.userSubscription = this.loginService.getCurrentUser()
            .subscribe( (user) => this.user = user );

        this.savedReceiptsSubscription = this.receiptService.getSaved()
            .subscribe( (receipt) => {
                this.reimbursement.receipt = receipt;
                this.reimbursement.state = ReimbursementWrapper.STATE_UPDATE;
                this.saveReimbursement();
            }, (error) => {
                this.alertService.push('receipt upload failed', AlertMessage.CATEGORY_ERROR);
            });

        this.savedReimbursementsSubscription = this.reimService.getSaved()
            .subscribe( (reimbursement) => {
                const modal = $(this.modal.nativeElement);
                
                Object.assign(this.reimbursement, reimbursement);
                modal.modal('hide');
            });

        this.reimbursementStatusListSubscription = this.reimStatusService.getList()
            .subscribe( (statuses) => this.reimStatuses = statuses );

        this.reimbursementTypeListSubscription = this.reimTypeService.getList()
            .subscribe( (types) => this.reimTypes = types );
    }

    ngOnDestroy(): void {
        this.selectedReimbursementSubscription.unsubscribe();
        this.userSubscription.unsubscribe();
        this.savedReceiptsSubscription.unsubscribe();
        this.reimbursementStatusListSubscription.unsubscribe();
        this.reimbursementTypeListSubscription.unsubscribe();
    }

}

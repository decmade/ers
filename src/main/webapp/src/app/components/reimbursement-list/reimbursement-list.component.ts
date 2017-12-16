import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

// rxjs
import { Subscription } from 'rxjs/Subscription';

// beans
import { Reimbursement, ReimbursementStatus, ReimbursementWrapper } from '../../beans/reimbursement';
import { AclRequest, AclResponse } from '../../beans/acl';
import { User } from '../../beans/user';

// pipes
import { ReimbursementStatusPipe } from '../../pipes/reimbursement-status.pipe';

// services
import { ReimbursementsService } from '../../services/reimbursements.service';
import { AuthorizationService } from '../../services/authorization.service';
import { LoginService } from '../../services/login.service';



@Component({
    selector: 'app-reimbursement-list',
    templateUrl: './reimbursement-list.component.html',
    styleUrls: ['./reimbursement-list.component.css']
})

export class ReimbursementListComponent implements OnInit, OnDestroy {
    public STATE_SHOW_PENDING = 'pending';
    public STATE_SHOW_APPROVED = 'approved';
    public STATE_SHOW_DENIED = 'denied';
    public STATE_SHOW_ALL = 'all';

    private reimbursements: Reimbursement[];
    private reimService: ReimbursementsService;
    private authService: AuthorizationService;
    private loginService: LoginService;
    private user: User;
    private reimbursementListSubscription: Subscription;
    private currentUserSubscription: Subscription;
    private router: Router;
    private reimPipe: ReimbursementStatusPipe;
    public keyword: string;
    public state: string;
    public selectedReimbursement: Reimbursement;

    constructor (
      reimService: ReimbursementsService,
      authService: AuthorizationService,
      loginService: LoginService,
      router: Router
     ) {
        this.reimbursements = [];
        this.reimService  = reimService;
        this.authService = authService;
        this.loginService = loginService;
        this.keyword = '';
        this.state = 'pending';
        this.selectedReimbursement = new Reimbursement();
        this.router = router;
        this.reimPipe = new ReimbursementStatusPipe();
    }

    public getFilteredReimbursements(): Reimbursement[] {
        return this.reimbursements
          .filter( (reimbursment) => {
              const pattern = new RegExp(`.*${ this.keyword.toLowerCase() }.*`);

              switch (true) {
                  case pattern.test( reimbursment.description.toLowerCase() ) :
                  case pattern.test( reimbursment.author.displayName.toLowerCase() ) :
                  case pattern.test( reimbursment.resolver.displayName.toLowerCase() ) :
                  case pattern.test( reimbursment.status.description.toLowerCase() ) :
                  case pattern.test( reimbursment.type.description.toLowerCase() ) :
                  case pattern.test( String(reimbursment.id) ) :
                      return true;
                  default :
                      return false;
              }
          });
    }

    public getReimbursements(mode: string): Reimbursement[] {
      return this.reimPipe.transform( this.getFilteredReimbursements(), mode);
    }

    public sort(field: string, desc: boolean ): void {
        this.reimbursements.sort( (a, b) => {
            const aValue = ReimbursementWrapper.getFieldByString(a, field);
            const bValue = ReimbursementWrapper.getFieldByString(b, field);

            switch ( true ) {
                case ( aValue > bValue ) :
                    return 1;
                case ( aValue < bValue ) :
                    return -1;
                default :
                    return 0;
            }
        });
    }

  public onSelected(reimbursement: Reimbursement): void {
      this.reimService.setSelected(reimbursement);
  }

  public startNewReimbursement(): void {
      const subject = new Reimbursement();

      subject.id = 0;
      subject.state = ReimbursementWrapper.STATE_UPDATE;
      subject.author = this.user;
      subject.type = { id: 3, description: ""};

      this.reimService.setSelected(subject);
  }

  ngOnInit(): void {
      this.reimbursementListSubscription = this.reimService.getList()
          .subscribe( (reimbursements) => this.reimbursements = reimbursements );

      this.currentUserSubscription = this.loginService.getCurrentUser()
        .subscribe( (user) => this.user = user );
  }

  ngOnDestroy(): void {
    this.reimbursementListSubscription.unsubscribe();
    this.currentUserSubscription.unsubscribe();
  }

}

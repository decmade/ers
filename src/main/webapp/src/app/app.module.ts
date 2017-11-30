import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { DatePipe } from '@angular/common';

// import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
/*
* I found the ng-bootstrap package to be lacking for the following reasons:
*
* - functionality: I can't clearly do things like set a modal size
*   I might be able to add an additonal div that does it but without it
*   being documented it will feel hackish and I can't be certain it will
*   work across versions
*
* - documentation: the ng-bootstrap usage docs are also not as clear as bootstrap's
*   you have to almost guess what parts are replacing bootstrap functionality
*   instead of them just clearly stating it
*/

// services
import { UsersService } from './services/users.service';
import { LoginService } from './services/login.service';
import { ReimbursementsService } from './services/reimbursements.service';
import { ReimbursementTypeService } from './services/reimbursement-type.service';
import { ReimbursementStatusService } from './services/reimbursement-status.service';
import { AuthorizationService } from './services/authorization.service';
import { AlertService } from './services/alert.service';
import { ReceiptService } from './services/receipt.service';
import { RoutePermissionService } from './services/route-permission.service';

// components
import { AppComponent } from './app.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { MainNavigationComponent } from './components/main-navigation/main-navigation.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { ReimbursementButtonComponent } from './components/reimbursement-button/reimbursement-button.component';
import { ReimbursementListComponent } from './components/reimbursement-list/reimbursement-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserDetailComponent } from './components/user-detail/user-detail.component';
import { ReimbursementDetailComponent } from './components/reimbursement-detail/reimbursement-detail.component';

// pipes
import { ReimbursementStatusPipe } from './pipes/reimbursement-status.pipe';

// guards
import { RouteGuard } from './guards/route.guard';

@NgModule({
  declarations: [
    // components
    AppComponent,
    NotificationsComponent,
    MainNavigationComponent,
    MainPageComponent,
    UserListComponent,
    ReimbursementListComponent,
    UserDetailComponent,
    ReimbursementDetailComponent,
    ReimbursementButtonComponent,

    // pipes
    ReimbursementStatusPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    // NgbModule.forRoot(),
  ],
  providers: [
    // services
    UsersService,
    LoginService,
    ReimbursementsService,
    AuthorizationService,
    ReimbursementTypeService,
    ReimbursementStatusService,
    AlertService,
    ReceiptService,
    RoutePermissionService,

    DatePipe,  // TODO: do this differently

    // guards
    RouteGuard,
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }

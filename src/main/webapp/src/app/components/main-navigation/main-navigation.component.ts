import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Routes, Router } from '@angular/router';

// rxjs
import { Observable } from 'rxjs/Observable';
import { delay, share } from 'rxjs/operators';
import { Subscription } from 'rxjs/Subscription';

// routes
import { navigationRoutes } from '../../app-routing.module';

// services
import { LoginService } from '../../services/login.service';
import { RoutePermissionService } from '../../services/route-permission.service';

// beans
import { User } from '../../beans/user';


@Component({
selector: 'app-main-navigation',
  templateUrl: './main-navigation.component.html',
  styleUrls: ['./main-navigation.component.css']
})

export class MainNavigationComponent implements OnInit, OnDestroy {
    private loginService: LoginService;
    private user: User;
    private loginSubscription: Subscription;
    private router: Router;
    private routePermissionService: RoutePermissionService;

    form = {
        identity: '',
        credential: '',
        elementId: 'login-form',
    };

    userBadge = {
        elementId: 'user-badge',
        iconClass: 'fa fa-user-o',
        hoverClass: 'fa fa-user',
    };

    constructor(loginService: LoginService, routePermService: RoutePermissionService, router: Router) {
        this.loginService = loginService;
        this.routePermissionService = routePermService;
        this.loginSubscription = new Subscription();
        this.user = null;
    }

    public onLogin(): void {
        this.login();
        this.resetForm();
    }

    public onLogout(): boolean {
        this.logout();
        return false;
    }

    public onLoginFormFocus(): void {
        const element = $(`#${this.form.elementId}`);

        element.find('.fa-user-o').removeClass('fa-user-o').addClass('fa-user');
        element.find('.btn-outline-primary').removeClass('btn-outline-primary').addClass('btn-primary');
    }

    public onLoginFormBlur(): void {
        const element = $(`#${this.form.elementId}`);

        element.find('.fa-user').removeClass('fa-user').addClass('fa-user-o');
        element.find('.btn-primary').removeClass('btn-primary').addClass('btn-outline-primary');
    }


    /*
    * I can make the navigation bar dynamic based
    * on policies here
    */
    public getRoutes(): Routes {
        return navigationRoutes.filter( (route) => {
            return this.routePermissionService.authorize(route.path);
        });
    }

    private login(): void {
        if ( this.validateLoginForm(this.form) ) {
            this.loginService.login(this.form.identity, this.form.credential);
        }
    }

    private logout(): void {
        this.loginService.logout();
    }

    private resetForm(): void {
        this.form.credential = '';
    }

    private validateLoginForm(form): boolean {
        if ( form.identity.length === 0 ) {
            return false;
        }

        if ( form.credential.length === 0 ) {
            return false;
        }

        return true;
    }

    ngOnInit() {
        this.loginSubscription = this.loginService.getCurrentUser()
            .subscribe( (user) => this.user = user );
    }

    ngOnDestroy(): void {
        this.loginSubscription.unsubscribe();
    }

}

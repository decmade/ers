import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RouteGuard } from './guards/route.guard';

// components ( for routes )
import { MainPageComponent } from './components/main-page/main-page.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { ReimbursementListComponent } from './components/reimbursement-list/reimbursement-list.component';

export const navigationRoutes: Routes = [
    {
        path: 'home',
        component: MainPageComponent,
        data: {
            title: 'Home',
        },
    },
    {
        path: 'reimbursements',
        component: ReimbursementListComponent,
        canActivate: [
            RouteGuard,
        ],
        data: {
            title: 'Reimbursements',
        },
    },
    {
        path: 'users',
        component: UserListComponent,
        canActivate: [
            RouteGuard,
        ],
        data: {
            title: 'Users',
            allowedRoles: [1]
        },
    },
    {
        path: '**',
        redirectTo: 'home',
    },
];

@NgModule({
    exports: [ RouterModule ],
    imports: [
        RouterModule.forRoot( navigationRoutes ),
    ],
})

export class AppRoutingModule { }

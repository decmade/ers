webpackJsonp(["main"],{

/***/ "../../../../../src/$$_lazy_route_resource lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return navigationRoutes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppRoutingModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__guards_route_guard__ = __webpack_require__("../../../../../src/app/guards/route.guard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__components_main_page_main_page_component__ = __webpack_require__("../../../../../src/app/components/main-page/main-page.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__components_user_list_user_list_component__ = __webpack_require__("../../../../../src/app/components/user-list/user-list.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__components_reimbursement_list_reimbursement_list_component__ = __webpack_require__("../../../../../src/app/components/reimbursement-list/reimbursement-list.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



// components ( for routes )



var navigationRoutes = [
    {
        path: 'home',
        component: __WEBPACK_IMPORTED_MODULE_3__components_main_page_main_page_component__["a" /* MainPageComponent */],
        data: {
            title: 'Home',
        },
    },
    {
        path: 'reimbursements',
        component: __WEBPACK_IMPORTED_MODULE_5__components_reimbursement_list_reimbursement_list_component__["a" /* ReimbursementListComponent */],
        canActivate: [
            __WEBPACK_IMPORTED_MODULE_2__guards_route_guard__["a" /* RouteGuard */],
        ],
        data: {
            title: 'Reimbursements',
        },
    },
    {
        path: 'users',
        component: __WEBPACK_IMPORTED_MODULE_4__components_user_list_user_list_component__["a" /* UserListComponent */],
        canActivate: [
            __WEBPACK_IMPORTED_MODULE_2__guards_route_guard__["a" /* RouteGuard */],
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
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["I" /* NgModule */])({
            exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* RouterModule */]],
            imports: [
                __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* RouterModule */].forRoot(navigationRoutes),
            ],
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "../../../../../src/app/app.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<app-notifications></app-notifications>\n<app-main-navigation></app-main-navigation>\n\n<div class=\"container\">\n    <router-outlet></router-outlet>\n</div>\n\n"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_login_service__ = __webpack_require__("../../../../../src/app/services/login.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


// services

var AppComponent = (function () {
    function AppComponent(router, loginService) {
        this.router = router;
        this.loginService = loginService;
    }
    AppComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-root',
            template: __webpack_require__("../../../../../src/app/app.component.html"),
            styles: [__webpack_require__("../../../../../src/app/app.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */], __WEBPACK_IMPORTED_MODULE_2__services_login_service__["a" /* LoginService */]])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_forms__ = __webpack_require__("../../../forms/esm5/forms.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_routing_module__ = __webpack_require__("../../../../../src/app/app-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_common__ = __webpack_require__("../../../common/esm5/common.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_users_service__ = __webpack_require__("../../../../../src/app/services/users.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__services_login_service__ = __webpack_require__("../../../../../src/app/services/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__services_reimbursements_service__ = __webpack_require__("../../../../../src/app/services/reimbursements.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__services_reimbursement_type_service__ = __webpack_require__("../../../../../src/app/services/reimbursement-type.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__services_reimbursement_status_service__ = __webpack_require__("../../../../../src/app/services/reimbursement-status.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__services_authorization_service__ = __webpack_require__("../../../../../src/app/services/authorization.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__services_alert_service__ = __webpack_require__("../../../../../src/app/services/alert.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__services_receipt_service__ = __webpack_require__("../../../../../src/app/services/receipt.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__services_route_permission_service__ = __webpack_require__("../../../../../src/app/services/route-permission.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__components_notifications_notifications_component__ = __webpack_require__("../../../../../src/app/components/notifications/notifications.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__components_main_navigation_main_navigation_component__ = __webpack_require__("../../../../../src/app/components/main-navigation/main-navigation.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__components_main_page_main_page_component__ = __webpack_require__("../../../../../src/app/components/main-page/main-page.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__components_reimbursement_button_reimbursement_button_component__ = __webpack_require__("../../../../../src/app/components/reimbursement-button/reimbursement-button.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__components_reimbursement_list_reimbursement_list_component__ = __webpack_require__("../../../../../src/app/components/reimbursement-list/reimbursement-list.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21__components_user_list_user_list_component__ = __webpack_require__("../../../../../src/app/components/user-list/user-list.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22__components_user_detail_user_detail_component__ = __webpack_require__("../../../../../src/app/components/user-detail/user-detail.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__components_reimbursement_detail_reimbursement_detail_component__ = __webpack_require__("../../../../../src/app/components/reimbursement-detail/reimbursement-detail.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__pipes_reimbursement_status_pipe__ = __webpack_require__("../../../../../src/app/pipes/reimbursement-status.pipe.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25__guards_route_guard__ = __webpack_require__("../../../../../src/app/guards/route.guard.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};






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









// components









// pipes

// guards

var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["I" /* NgModule */])({
            declarations: [
                // components
                __WEBPACK_IMPORTED_MODULE_15__app_component__["a" /* AppComponent */],
                __WEBPACK_IMPORTED_MODULE_16__components_notifications_notifications_component__["a" /* NotificationsComponent */],
                __WEBPACK_IMPORTED_MODULE_17__components_main_navigation_main_navigation_component__["a" /* MainNavigationComponent */],
                __WEBPACK_IMPORTED_MODULE_18__components_main_page_main_page_component__["a" /* MainPageComponent */],
                __WEBPACK_IMPORTED_MODULE_21__components_user_list_user_list_component__["a" /* UserListComponent */],
                __WEBPACK_IMPORTED_MODULE_20__components_reimbursement_list_reimbursement_list_component__["a" /* ReimbursementListComponent */],
                __WEBPACK_IMPORTED_MODULE_22__components_user_detail_user_detail_component__["a" /* UserDetailComponent */],
                __WEBPACK_IMPORTED_MODULE_23__components_reimbursement_detail_reimbursement_detail_component__["a" /* ReimbursementDetailComponent */],
                __WEBPACK_IMPORTED_MODULE_19__components_reimbursement_button_reimbursement_button_component__["a" /* ReimbursementButtonComponent */],
                // pipes
                __WEBPACK_IMPORTED_MODULE_24__pipes_reimbursement_status_pipe__["a" /* ReimbursementStatusPipe */],
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_4__app_routing_module__["a" /* AppRoutingModule */],
                __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["b" /* HttpClientModule */],
                __WEBPACK_IMPORTED_MODULE_3__angular_forms__["a" /* FormsModule */],
            ],
            providers: [
                // services
                __WEBPACK_IMPORTED_MODULE_6__services_users_service__["a" /* UsersService */],
                __WEBPACK_IMPORTED_MODULE_7__services_login_service__["a" /* LoginService */],
                __WEBPACK_IMPORTED_MODULE_8__services_reimbursements_service__["a" /* ReimbursementsService */],
                __WEBPACK_IMPORTED_MODULE_11__services_authorization_service__["a" /* AuthorizationService */],
                __WEBPACK_IMPORTED_MODULE_9__services_reimbursement_type_service__["a" /* ReimbursementTypeService */],
                __WEBPACK_IMPORTED_MODULE_10__services_reimbursement_status_service__["a" /* ReimbursementStatusService */],
                __WEBPACK_IMPORTED_MODULE_12__services_alert_service__["b" /* AlertService */],
                __WEBPACK_IMPORTED_MODULE_13__services_receipt_service__["a" /* ReceiptService */],
                __WEBPACK_IMPORTED_MODULE_14__services_route_permission_service__["a" /* RoutePermissionService */],
                __WEBPACK_IMPORTED_MODULE_5__angular_common__["d" /* DatePipe */],
                // guards
                __WEBPACK_IMPORTED_MODULE_25__guards_route_guard__["a" /* RouteGuard */],
            ],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_15__app_component__["a" /* AppComponent */]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "../../../../../src/app/beans/reimbursement.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Reimbursement; });
/* unused harmony export ReimbursementType */
/* unused harmony export ReimbursementStatus */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return ReimbursementWrapper; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__wrappers__ = __webpack_require__("../../../../../src/app/beans/wrappers.ts");
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var Reimbursement = (function () {
    function Reimbursement() {
    }
    return Reimbursement;
}());

var ReimbursementType = (function () {
    function ReimbursementType() {
    }
    return ReimbursementType;
}());

var ReimbursementStatus = (function () {
    function ReimbursementStatus() {
    }
    return ReimbursementStatus;
}());

var ReimbursementWrapper = (function (_super) {
    __extends(ReimbursementWrapper, _super);
    function ReimbursementWrapper() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ReimbursementWrapper.STATE_CREATE = 'create';
    ReimbursementWrapper.STATE_VIEW = 'view';
    ReimbursementWrapper.STATE_APPROVE = 'approve';
    ReimbursementWrapper.STATE_UPDATE = 'update';
    ReimbursementWrapper.STATE_UPDATE_RECEIPT = 'update-receipt';
    ReimbursementWrapper.STATUS_PENDING = 1;
    ReimbursementWrapper.STATUS_APPROVED = 2;
    ReimbursementWrapper.STATUS_DENIED = 3;
    return ReimbursementWrapper;
}(__WEBPACK_IMPORTED_MODULE_0__wrappers__["a" /* GenericWrapper */]));



/***/ }),

/***/ "../../../../../src/app/beans/servlet.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Servlet; });
var Servlet = (function () {
    function Servlet() {
    }
    Servlet.getRootUri = function () {
        var element = document.getElementsByTagName('base')[0];
        var pattern = new RegExp('http://.*:4200.*');
        var uri = '';
        if (pattern.test(element.baseURI)) {
            uri = 'http://localhost:8080/ers';
        }
        else {
            uri = '/ers';
        }
        return uri;
    };
    Servlet.getServiceUrl = function (serviceName) {
        return [
            this.getRootUri(),
            serviceName
        ].join('/');
    };
    return Servlet;
}());



/***/ }),

/***/ "../../../../../src/app/beans/user.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return User; });
/* unused harmony export UserRole */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return UserWrapper; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__wrappers__ = __webpack_require__("../../../../../src/app/beans/wrappers.ts");
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var User = (function () {
    function User() {
    }
    return User;
}());

var UserRole = (function () {
    function UserRole() {
    }
    return UserRole;
}());

var UserWrapper = (function (_super) {
    __extends(UserWrapper, _super);
    function UserWrapper() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return UserWrapper;
}(__WEBPACK_IMPORTED_MODULE_0__wrappers__["a" /* GenericWrapper */]));



/***/ }),

/***/ "../../../../../src/app/beans/wrappers.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GenericWrapper; });
var GenericWrapper = (function () {
    function GenericWrapper() {
    }
    GenericWrapper.getFieldByString = function (object, fieldString) {
        var fields = fieldString.split('.');
        var value = object;
        for (var _i = 0, fields_1 = fields; _i < fields_1.length; _i++) {
            var field = fields_1[_i];
            value = value[field];
        }
        value = String(value).toLowerCase();
        return value;
    };
    GenericWrapper.copy = function (src, target) {
        for (var prop in src) {
            if (src.hasOwnProperty(prop)) {
                target[prop] = src[prop];
            }
        }
    };
    GenericWrapper.prepareForDao = function (object) {
        object = GenericWrapper.flattenObject(object);
        for (var prop in object) {
            if (!(object[prop] instanceof String)) {
                var value = object[prop];
                object[prop] = String(value);
            }
        }
        return object;
    };
    GenericWrapper.flattenObject = function (object) {
        var output = Object.assign({}, object);
        for (var prop in object) {
            if (object.hasOwnProperty(prop)) {
                if (prop === 'state') {
                    delete output[prop];
                }
                if (object[prop] instanceof Object) {
                    if (object[prop].hasOwnProperty('id')) {
                        output[prop + "Id"] = object[prop].id;
                    }
                    delete output[prop];
                }
            }
        }
        return output;
    };
    return GenericWrapper;
}());



/***/ }),

/***/ "../../../../../src/app/components/main-navigation/main-navigation.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/main-navigation/main-navigation.component.html":
/***/ (function(module, exports) {

module.exports = "<!-- NAV BAR -->\n<nav class=\"navbar navbar-expand-md sticky-top revature-navbar navbar-light mb-5\">\n    <a class=\"navbar-brand\" href=\"http://www.revature.com\" target=\"_blank\">\n        <img src=\"assets/images/logo.png\" width=\"180\" height=\"56\" />&nbsp;ERS\n    </a>\n\n    <button type=\"button\" class=\"navbar-toggler\" data-toggle=\"collapse\" data-target=\"#main-navbar\">\n        <span class=\"navbar-toggler-icon\"></span>\n    </button>\n\n    <div class=\"navbar-collapse collapse\" id=\"main-navbar\">\n        <ul class=\"navbar-nav mr-auto bd-navbar-nav\">\n            <li class=\"nav-item\" *ngFor=\"let route of getRoutes()\">\n                <a class=\"nav-link\" routerLink=\"{{ route.path }}\" routerLinkActive=\"active\">{{ route.data.title }}</a>\n            </li>\n        </ul>\n\n        <span id=\"user-landing\" >\n            <ng-container *ngIf=\"user?.identity; then badge; else login\"></ng-container>\n        </span>\n    </div>\n</nav>\n\n\n\n<!-- (if user exists) BUTTON FOR AUTHENTICATED USER -->\n<ng-template #badge>\n    <div id=\"user-badge\" class=\"btn-group mr-4\" role=\"group\" >\n        <button type=\"button\" \n            class=\"btn btn-outline-success dropdown-toggle\" \n            data-toggle=\"dropdown\">\n            <span class=\"fa fa-user-o mr-2\"></span>\n            {{ user.displayName }}\n        </button>\n        <div class=\"dropdown-menu\">\n            <a href=\"#\" class=\"dropdown-item\" (click)=\"onLogout()\">Sign Out</a>\n        </div>\n    </div>\n</ng-template>\n\n<!-- (else) LOGIN FORM WHEN NO AUTHENTICATED USER -->\n<ng-template #login>\n    <form id=\"login-form\" class=\"form-inline\">\n        <div class=\"my-1 ml-1 mr-2\"><span class=\"fa fa-user-o fa-lg text-primary\"></span></div>\n        <input type=\"text\" id=\"identity\" placeholder=\"username\" class=\"form-control m-1\" name=\"identity\" [(ngModel)]=\"form.identity\" (focus)=\"onLoginFormFocus()\" (blur)=\"onLoginFormBlur()\"/>\n        <input type=\"password\" id=\"credential\" placeholder=\"password\" class=\"form-control m-1\" name=\"credential\" [(ngModel)]=\"form.credential\" (focus)=\"onLoginFormFocus()\" (blur)=\"onLoginFormBlur()\"/>\n        <button type=\"button\" class=\"btn btn-outline-primary m-1\" (click)=\"onLogin()\">Login</button>\n    </form>\n</ng-template>"

/***/ }),

/***/ "../../../../../src/app/components/main-navigation/main-navigation.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MainNavigationComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Subscription__ = __webpack_require__("../../../../rxjs/_esm5/Subscription.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_routing_module__ = __webpack_require__("../../../../../src/app/app-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_login_service__ = __webpack_require__("../../../../../src/app/services/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_route_permission_service__ = __webpack_require__("../../../../../src/app/services/route-permission.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



// routes

// services


var MainNavigationComponent = (function () {
    function MainNavigationComponent(loginService, routePermService, router) {
        this.form = {
            identity: '',
            credential: '',
            elementId: 'login-form',
        };
        this.userBadge = {
            elementId: 'user-badge',
            iconClass: 'fa fa-user-o',
            hoverClass: 'fa fa-user',
        };
        this.loginService = loginService;
        this.routePermissionService = routePermService;
        this.loginSubscription = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subscription__["a" /* Subscription */]();
        this.user = null;
    }
    MainNavigationComponent.prototype.onLogin = function () {
        this.login();
        this.resetForm();
    };
    MainNavigationComponent.prototype.onLogout = function () {
        this.logout();
        return false;
    };
    MainNavigationComponent.prototype.onLoginFormFocus = function () {
        var element = $("#" + this.form.elementId);
        element.find('.fa-user-o').removeClass('fa-user-o').addClass('fa-user');
        element.find('.btn-outline-primary').removeClass('btn-outline-primary').addClass('btn-primary');
    };
    MainNavigationComponent.prototype.onLoginFormBlur = function () {
        var element = $("#" + this.form.elementId);
        element.find('.fa-user').removeClass('fa-user').addClass('fa-user-o');
        element.find('.btn-primary').removeClass('btn-primary').addClass('btn-outline-primary');
    };
    /*
    * I can make the navigation bar dynamic based
    * on policies here
    */
    MainNavigationComponent.prototype.getRoutes = function () {
        var _this = this;
        return __WEBPACK_IMPORTED_MODULE_3__app_routing_module__["b" /* navigationRoutes */].filter(function (route) {
            return _this.routePermissionService.authorize(route.path);
        });
    };
    MainNavigationComponent.prototype.login = function () {
        if (this.validateLoginForm(this.form)) {
            this.loginService.login(this.form.identity, this.form.credential);
        }
    };
    MainNavigationComponent.prototype.logout = function () {
        this.loginService.logout();
    };
    MainNavigationComponent.prototype.resetForm = function () {
        this.form.credential = '';
    };
    MainNavigationComponent.prototype.validateLoginForm = function (form) {
        if (form.identity.length === 0) {
            return false;
        }
        if (form.credential.length === 0) {
            return false;
        }
        return true;
    };
    MainNavigationComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.loginSubscription = this.loginService.getCurrentUser()
            .subscribe(function (user) { return _this.user = user; });
    };
    MainNavigationComponent.prototype.ngOnDestroy = function () {
        this.loginSubscription.unsubscribe();
    };
    MainNavigationComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-main-navigation',
            template: __webpack_require__("../../../../../src/app/components/main-navigation/main-navigation.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/main-navigation/main-navigation.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_4__services_login_service__["a" /* LoginService */], __WEBPACK_IMPORTED_MODULE_5__services_route_permission_service__["a" /* RoutePermissionService */], __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]])
    ], MainNavigationComponent);
    return MainNavigationComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/main-page/main-page.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/main-page/main-page.component.html":
/***/ (function(module, exports) {

module.exports = "\n<!-- video: the revature business model -->\n<div class=\"row mt-3\">\n    <div class=\"col-md-2\"></div>\n    <div class=\"col-md-8\">\n        <div class=\"embed-responsive embed-responsive-16by9\">\n            <iframe class=\"embed-responsive-item\" width=\"560\" height=\"315\" \n                src=\"https://www.youtube.com/embed/TXIGiiYsB74?rel=0&amp;showinfo=0\" \n                frameborder=\"0\" allowfullscreen>\n            </iframe>\n        </div>\n    </div>\n    <div class=\"col-md-2\"></div>\n</div>\n\n<!-- DIVIDER -->\n<div class=\"row my-3\">\n    <hr class=\"w-100\" />\n</div>\n\n<!-- video: why revature? -->\n<div class=\"row mb-2\">\n    <h3 class=\"revature-orange\">Why Revature?</h3>\n    <hr class=\"w-100 revature\" />\n</div>\n\n<div class=\"row\">\n    <div class=\"col-md-4\">\n        <span class=\"embed-responsive embed-responsive-16by9 mr-5\">\n            <iframe class=\"embed-responsive-item\" width=\"560\" height=\"315\"\n                src=\"https://www.youtube.com/embed/O0iCJjxAQwI?rel=0&amp;showinfo=0\"\n                frameborder=\"0\" allowfullscreen>\n            </iframe>\n        </span>\n    </div>\n    <div class=\"col-md-8 text-justify\">\n        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam fringilla felis vitae augue \n        tincidunt, sed dignissim ex tincidunt. Praesent eget euismod metus. Sed convallis lacus non tempor \n        vulputate. Donec risus eros, cursus vitae augue non, commodo tincidunt sapien. Suspendisse sit \n        amet ex et dolor imperdiet pellentesque id eu urna. Vestibulum rhoncus interdum turpis ac rhoncus. \n        Phasellus maximus massa sed tellus cursus, lacinia convallis dui lobortis. Nam at metus consectetur, \n        viverra ipsum eu, tincidunt elit. Sed interdum nunc in orci vestibulum viverra. Nullam ut fermentum \n        diam, et pulvinar tortor. Proin ultricies volutpat urna. Cras ac mollis enim, viverra hendrerit \n        tellus.\n    </div>\n</div>\n\n<!-- DIVIDER -->\n<div class=\"row my-3\">\n    <hr class=\"w-100\" />\n</div>\n\n<!-- video: testimonials -->\n<div class=\"row mb-2\">\n    <h3 class=\"revature-orange\">Testimonials</h3>\n    <hr class=\"w-100 revature\" />\n</div>\n\n<div class=\"row\">\n    <div class=\"col-md-8 text-justify\">\n        Nam urna est, tincidunt a condimentum eu, commodo in libero. Donec et odio ac nulla mattis consectetur vitae a nisi. Mauris eu mollis leo, a lobortis velit. Donec tempus tempus nisi in convallis. Etiam magna velit, tristique eget sem eget, sollicitudin iaculis mauris. Morbi felis nisi, dictum a sapien eget, vulputate euismod orci. Suspendisse nec quam laoreet, tincidunt eros ut, fermentum tellus. Etiam suscipit turpis massa, sit amet volutpat dui egestas sit amet. Etiam vel accumsan libero. Aliquam lorem ante, malesuada sed tellus nec, vestibulum rhoncus quam. Donec gravida purus sit amet posuere imperdiet. Praesent ac nunc pharetra, maximus enim sed, tempus eros. Sed mattis lorem at tincidunt lacinia. Mauris condimentum leo ante. \n    </div>\n\n    <div class=\"col-md-4\">\n        <div class=\"embed-responsive embed-responsive-16by9 ml-2\">\n            <iframe class=\"embed-responsive-item\" width=\"560\" height=\"315\" \n                src=\"https://www.youtube.com/embed/M8wT36t3gkE?rel=0&amp;showinfo=0\" \n                frameborder=\"0\" allowfullscreen>\n            </iframe>\n        </div>\n    </div>\n</div>\n\n<!-- divider -->\n<div class=\"row my-3\">\n    <hr class=\"w-100\" />\n</div>\n\n<!-- filler content -->\n<div class=\"row mb-2\">\n    <h3 class=\"revature-orange\">News</h3>\n    <hr class=\"w-100 revature\" />\n</div>\n\n<div class=\"row\">\n    <p>\n      Phasellus convallis vel arcu vitae consectetur. Vivamus tincidunt ex sem, sed tempor lacus hendrerit ac. Donec tristique urna quis imperdiet efficitur. Curabitur vitae purus posuere, malesuada metus sed, accumsan orci. Maecenas aliquam, sapien a porta eleifend, orci est porttitor tortor, ut eleifend urna nunc in lacus. Proin euismod mi nec ligula finibus, at ornare sapien porttitor. Maecenas ultricies, urna non vulputate fermentum, mi risus sollicitudin nibh, sed imperdiet dui lectus quis massa. Nulla mollis vulputate felis faucibus luctus. Proin dictum, ligula ut imperdiet blandit, nisl velit rutrum quam, ac iaculis dui mi et neque. Nam feugiat mauris sit amet sem placerat semper. Nam ut elit mollis, commodo urna ac, finibus mi. Sed convallis justo id tellus fringilla, eget porttitor augue commodo. Proin semper velit non blandit placerat. \n    </p>\n</div>\n\n<div class=\"row\">\n    <p>\n      Nam urna est, tincidunt a condimentum eu, commodo in libero. Donec et odio ac nulla mattis consectetur vitae a nisi. Mauris eu mollis leo, a lobortis velit. Donec tempus tempus nisi in convallis. Etiam magna velit, tristique eget sem eget, sollicitudin iaculis mauris. Morbi felis nisi, dictum a sapien eget, vulputate euismod orci. Suspendisse nec quam laoreet, tincidunt eros ut, fermentum tellus. Etiam suscipit turpis massa, sit amet volutpat dui egestas sit amet. Etiam vel accumsan libero. Aliquam lorem ante, malesuada sed tellus nec, vestibulum rhoncus quam. Donec gravida purus sit amet posuere imperdiet. Praesent ac nunc pharetra, maximus enim sed, tempus eros. Sed mattis lorem at tincidunt lacinia. Mauris condimentum leo ante. \n    </p>\n</div>\n\n<div class=\"row\">\n    <p>\n      Phasellus convallis vel arcu vitae consectetur. Vivamus tincidunt ex sem, sed tempor lacus hendrerit ac. Donec tristique urna quis imperdiet efficitur. Curabitur vitae purus posuere, malesuada metus sed, accumsan orci. Maecenas aliquam, sapien a porta eleifend, orci est porttitor tortor, ut eleifend urna nunc in lacus. Proin euismod mi nec ligula finibus, at ornare sapien porttitor. Maecenas ultricies, urna non vulputate fermentum, mi risus sollicitudin nibh, sed imperdiet dui lectus quis massa. Nulla mollis vulputate felis faucibus luctus. Proin dictum, ligula ut imperdiet blandit, nisl velit rutrum quam, ac iaculis dui mi et neque. Nam feugiat mauris sit amet sem placerat semper. Nam ut elit mollis, commodo urna ac, finibus mi. Sed convallis justo id tellus fringilla, eget porttitor augue commodo. Proin semper velit non blandit placerat. \n    </p>\n</div>"

/***/ }),

/***/ "../../../../../src/app/components/main-page/main-page.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MainPageComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var MainPageComponent = (function () {
    function MainPageComponent() {
    }
    MainPageComponent.prototype.ngOnInit = function () {
    };
    MainPageComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-main-page',
            template: __webpack_require__("../../../../../src/app/components/main-page/main-page.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/main-page/main-page.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], MainPageComponent);
    return MainPageComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/notifications/notifications.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "button.close {\n    cursor: pointer;\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/notifications/notifications.component.html":
/***/ (function(module, exports) {

module.exports = "<div #alertContainer class=\"text-center font-weight-bold fixed-bottom\" >\n    <div *ngFor=\"let alert of alerts\" [class]=\"getAlertClass(alert)\" role=\"alert\">\n        {{ alert.message }}\n        <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"remove(alert)\">\n            <span aria-hidden=\"true\">&times;</span>\n        </button>\n    </div>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/components/notifications/notifications.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_alert_service__ = __webpack_require__("../../../../../src/app/services/alert.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

// services

var NotificationsComponent = (function () {
    function NotificationsComponent(alertService) {
        this.alertService = alertService;
        this.alerts = [];
    }
    NotificationsComponent.prototype.getAlertClass = function (alert) {
        var classes = [
            'show',
            'alert',
            'alert-dismissable',
            'm-0',
            'mb-1'
        ];
        switch (alert.category) {
            case __WEBPACK_IMPORTED_MODULE_1__services_alert_service__["a" /* AlertMessage */].CATEGORY_SUCCESS:
                classes.push('alert-success');
                break;
            case __WEBPACK_IMPORTED_MODULE_1__services_alert_service__["a" /* AlertMessage */].CATEGORY_INFO:
                classes.push('alert-info');
                break;
            case __WEBPACK_IMPORTED_MODULE_1__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR:
                classes.push('alert-danger');
                break;
            case __WEBPACK_IMPORTED_MODULE_1__services_alert_service__["a" /* AlertMessage */].CATEGORY_WARNING:
                classes.push('alert-warning');
                break;
            default:
                classes.push('alert-secondary');
        }
        return classes.join(' ');
    };
    NotificationsComponent.prototype.remove = function (alert) {
        this.alerts = this.alerts.filter(function (a) { return a.id !== alert.id; });
    };
    NotificationsComponent.prototype.addAlert = function (alert) {
        var _this = this;
        this.alerts.push(alert);
        setTimeout(function () {
            _this.remove(alert);
        }, 10000);
        // $(window).scrollTop(0);
    };
    NotificationsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.subscription = this.alertService.getNotifications().subscribe(function (alert) { return _this.addAlert(alert); });
    };
    NotificationsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-notifications',
            template: __webpack_require__("../../../../../src/app/components/notifications/notifications.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/notifications/notifications.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_alert_service__["b" /* AlertService */]])
    ], NotificationsComponent);
    return NotificationsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/reimbursement-button/reimbursement-button.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/reimbursement-button/reimbursement-button.component.html":
/***/ (function(module, exports) {

module.exports = "<button type=\"button\" [class]=\"getButtonClass()\">\n    <span [class]=\"getIconClass()\"></span>\n</button>"

/***/ }),

/***/ "../../../../../src/app/components/reimbursement-button/reimbursement-button.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReimbursementButtonComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__ = __webpack_require__("../../../../../src/app/beans/reimbursement.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

// beans

var ReimbursementButtonComponent = (function () {
    function ReimbursementButtonComponent() {
    }
    ReimbursementButtonComponent.prototype.getIconClass = function () {
        switch (this.reimbursement.state) {
            case __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_APPROVE:
                return 'fa fa-check';
            case __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE:
            case __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_CREATE:
                return 'fa fa-pencil';
            default:
                return 'fa fa-eye';
        }
    };
    ReimbursementButtonComponent.prototype.getButtonClass = function () {
        switch (this.reimbursement.state) {
            case __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_APPROVE:
                return 'btn btn-primary';
            case __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_CREATE:
            case __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE:
                return 'btn btn-warning';
            default:
                return 'btn btn-outline-secondary';
        }
    };
    ReimbursementButtonComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["D" /* Input */])(),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["a" /* Reimbursement */])
    ], ReimbursementButtonComponent.prototype, "reimbursement", void 0);
    ReimbursementButtonComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-reimbursement-button',
            template: __webpack_require__("../../../../../src/app/components/reimbursement-button/reimbursement-button.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/reimbursement-button/reimbursement-button.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], ReimbursementButtonComponent);
    return ReimbursementButtonComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/reimbursement-detail/reimbursement-detail.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "button.close {\n    cursor: pointer;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/reimbursement-detail/reimbursement-detail.component.html":
/***/ (function(module, exports) {

module.exports = "<div id=\"app-reimbursement-detail\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\">\n  <div class=\"modal-dialog modal-lg\" role=\"document\">\n    <div class=\"modal-content\">\n      <div class=\"modal-header\">\n        <h4 class=\"modal-title\">\n          <ng-container *ngIf=\"isNew();then newTitle; else detailTitle\"></ng-container>\n        </h4>\n        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n          <span aria-hidden=\"true\">&times;</span>\n        </button>\n      </div>\n      <div class=\"modal-body\">\n        <div class=\"container px-3\">\n          <form>\n              <!-- ROW 1 (author, status)-->\n              <div class=\"form-group row\" *ngIf=\"isNew() === false\">\n\n                <label for=\"input-author\" class=\"col-md-2 col-form-label\"><span class=\"font-weight-bold\">Author:</span></label>\n                <div class=\"col-md-4\">\n                  <input type=\"text\" readonly id=\"input-author\" class=\"form-control-plaintext text-left\" [value]=\"reimCopy?.author?.displayName\">\n                </div>\n\n                <label for=\"input-status\" class=\"col-form-label col-md-2\"><span class=\"font-weight-bold\">Status:</span></label>\n                <div class=\"col-md-4\">\n                  <ng-container *ngIf=\"inApproveState(); then statusSelection; else statusDisplay\"></ng-container>\n                </div>\n\n              </div>\n\n              <!-- ROW 2 (submitted, resolved)-->\n              <div class=\"form-group row\" *ngIf=\"isNew() === false\">\n                <label for=\"input-submitted\" class=\"col-form-label col-md-2\"><span class=\"font-weight-bold\">Submitted:</span></label>\n                <div class=\"col-md-4\">\n                  <input type=\"text\" readonly id=\"input-submitted\" class=\"form-control-plaintext text-left\" [value]=\"reimCopy.submitted | date:'MM/dd/yyyy hh:mm a'\">\n                </div>\n\n                <ng-container *ngIf=\"inEditableState() === false && reimCopy.resolved !== 'null'; then displayResolvedDate\"></ng-container>\n\n              </div>\n\n              <!-- ROW 3 (type, resolver)-->\n              <div class=\"form-group row\">\n                <label for=\"input-type\" class=\"col-form-label col-md-2\"><span class=\"font-weight-bold\">Type:</span></label>\n                <div class=\"col-md-4\">\n                  <ng-container *ngIf=\"inUpdateState(); then typeSelection; else typeDisplay\"></ng-container>\n                </div>\n\n                <!-- show resolver if the request has been processed -->\n                <ng-container *ngIf=\"inEditableState(); then hideResolver; else displayResolver\"></ng-container>\n              </div>\n\n              <!-- ROW 4 (amount)-->\n              <div class=\"form-group row\">\n                <label for=\"input-amount\" class=\"col-form-label col-md-2\"><span class=\"font-weight-bold\">Amount:</span></label>\n                <div class=\"col-md-4\">\n                   <div class=\"input-group\">\n                    <span class=\"input-group-addon\"><span class=\"fa fa-usd\"></span></span>\n                    <input type=\"text\" [readonly]=\"inUpdateState() === false\" id=\"input-submitted\" class=\"form-control text-left\" name=\"amount\" [ngModel]=\"reimCopy.amount | number:'1.2-2'\" [ngModelOptions]=\"{updateOn:'blur'}\" (ngModelChange)=\"reimCopy.amount = $event\">\n                  </div>\n                </div>\n\n                <div class=\"col-md-6\"></div>\n              </div>\n\n\n              <!-- ROW 5 (description label)-->\n              <div class=\"form-group row\">\n                <label for=\"input-description\" class=\"col-md-10 col-form-label\"><span class=\"font-weight-bold\">Description:</span></label>\n              </div>\n\n              <!-- ROW 6 description-->\n              <div class=\"form-group row\">\n                <div class=\"col-md-12\">\n                  <textarea class=\"form-control\" name=\"input-description\" rows=\"5\" [readonly]=\"inUpdateState() === false\" [(ngModel)]=\"reimCopy.description\"></textarea>\n              </div>\n              </div>\n\n              <!-- ROW 7 receipt -->\n              <div #reciptControl class=\"form-group row\">\n                <ng-container *ngIf=\"inReceiptUpdateState(); then uploadReceipt; else displayReceipt\"></ng-container>\n              </div>\n          </form>\n\n        </div>\n      </div>\n      <div class=\"modal-footer\">\n        <ng-container *ngIf=\"inEditableState();then editButtons; else dismissButton\"></ng-container>\n\n        <ng-template #editButtons>\n          <button type=\"button\" class=\"btn btn-success\" (click)=\"onSave()\" *ngIf=\"inEditableState()\"><span class=\"fa fa-floppy-o mr-2\"></span>Save</button>\n          <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\" (click)=\"onCancel()\"><span class=\"fa fa-ban mr-2\"></span>Cancel</button>\n      </ng-template>\n\n      <ng-template #dismissButton>\n          <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" (click)=\"onCancel()\"><span class=\"fa fa-times mr-2\"></span>Close</button>\n      </ng-template>\n      </div>\n    </div>\n  </div>\n</div>\n\n<!-- BEGIN: templates -->\n\n<!-- TITLE -->\n<ng-template #detailTitle>\n  Reimbursement Detail&nbsp;[ {{ reimCopy.id }} ]\n</ng-template>\n\n<ng-template #newTitle>\n  Submit A New Reimbursement\n</ng-template>\n\n<!-- STATUS-->\n<ng-template #statusSelection>\n  <select #reimStatus id=\"input-status\" class=\"custom-select\" (change)=\"onStatusSelect( reimStatus.value )\">\n    <option *ngFor=\"let status of reimStatuses\" [value]=\"status.id\" [selected]=\"status.id === reimCopy?.status?.id\">\n      {{ status.description }}\n    </option>\n  </select>\n</ng-template>\n\n<ng-template #statusDisplay>\n    <input type=\"text\" readonly id=\"input-status\" class=\"form-control-plaintext\" [value]=\"reimCopy?.status?.description\">\n</ng-template>\n\n\n<!-- TYPE -->\n<ng-template #typeSelection>\n  <select #reimType id=\"input-type\" class=\"custom-select\" (change)=\"onTypeSelect( reimType.value )\">\n    <option *ngFor=\"let type of reimTypes\" [value]=\"type.id\" [selected]=\"type.id === reimCopy?.type?.id\">\n      {{ type.description }}\n    </option>\n  </select>\n</ng-template>\n\n<ng-template #typeDisplay>\n  <input type=\"text\" readonly id=\"input-type\" class=\"form-control-plaintext\" [value]=\"reimCopy?.type?.description\">\n</ng-template>\n\n\n<!-- RESOLVER -->\n<ng-template #displayResolver>\n  <label for=\"input-resolver\" class=\"col-form-label col-md-2\"><span class=\"font-weight-bold\">Resolver:</span></label>\n  <div class=\"col-md-4\">\n    <input id=\"input-resolver\" type=\"text\" readonly class=\"form-control-plaintext\" [value]=\"reimCopy?.resolver?.displayName\">\n  </div>\n</ng-template>\n\n<ng-template #hideResolver>\n  <div class=\"col-md-6\"></div>\n</ng-template>\n\n\n<!-- RESOLVED DATE -->\n<ng-template #displayResolvedDate>\n  <label for=\"input-resolved\" class=\"col-form-label col-md-2\"><span class=\"font-weight-bold\">Resolved:</span></label>\n  <div class=\"col-md-4\">\n    <input type=\"text\" readonly id=\"input-resolved\" class=\"form-control-plaintext text-left\" [value]=\"reimCopy.resolved | date:'MM/dd/yyyy hh:mm a'\">\n  </div>\n</ng-template>\n\n\n<!-- RECEIPT -->\n<ng-template #displayReceipt>\n  <div class=\"col-md-12 text-center\">\n    <ng-container *ngIf=\"inUpdateState();then showButtonGroup; else showViewButton\"></ng-container>\n\n      <ng-template #showButtonGroup>\n        <div class=\"btn-group\">\n          <a class=\"btn btn-lg btn-primary\" target=\"blank\" [href]=\"getReceiptUrl()\">\n            <span class=\"fa fa-file-o mr-1\"></span>\n            View Receipt\n          </a>\n          <button class=\"btn btn-primary btn-lg\" (click)=\"setReceiptUpdateState()\"><span class=\"fa fa-pencil\"></span></button>\n        </div>\n      </ng-template>\n\n      <ng-template #showViewButton>\n        <a class=\"btn btn-lg btn-primary\" target=\"blank\" [href]=\"getReceiptUrl()\">\n          <span class=\"fa fa-file-o mr-1\"></span>\n          View Receipt\n        </a>\n      </ng-template>\n  </div>\n</ng-template>\n\n<ng-template #uploadReceipt>\n    <label for=\"input-receipt\" class=\"col-form-label col-md-3\"><span class=\"font-weight-bold\">Upload Receipt:</span></label>\n    \n    <div class=\"col-md-9\">\n      <input #receiptUpload type=\"file\" id=\"input-receipt\" class=\"form-control\">\n    </div>\n</ng-template>"

/***/ }),

/***/ "../../../../../src/app/components/reimbursement-detail/reimbursement-detail.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReimbursementDetailComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/esm5/common.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__ = __webpack_require__("../../../../../src/app/beans/reimbursement.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_alert_service__ = __webpack_require__("../../../../../src/app/services/alert.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_reimbursements_service__ = __webpack_require__("../../../../../src/app/services/reimbursements.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_reimbursement_type_service__ = __webpack_require__("../../../../../src/app/services/reimbursement-type.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__services_reimbursement_status_service__ = __webpack_require__("../../../../../src/app/services/reimbursement-status.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__services_receipt_service__ = __webpack_require__("../../../../../src/app/services/receipt.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__services_login_service__ = __webpack_require__("../../../../../src/app/services/login.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


// beans


// services






var ReimbursementDetailComponent = (function () {
    function ReimbursementDetailComponent(loginService, reimService, reimTypeService, reimStatusService, receiptService, alertService, datePipe) {
        this.loginService = loginService;
        this.reimTypeService = reimTypeService;
        this.reimStatusService = reimStatusService;
        this.reimService = reimService;
        this.alertService = alertService;
        this.receiptService = receiptService;
        this.reimTypes = [];
        this.reimStatuses = [];
        this.datePipe = datePipe;
        this.cloneObject();
        this.reimbursement = new __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["a" /* Reimbursement */]();
    }
    ReimbursementDetailComponent.prototype.getTypeClass = function () {
        return {
            disabled: (this.reimbursement.state !== __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE)
        };
    };
    ReimbursementDetailComponent.prototype.getReceiptUrl = function () {
        if (this.reimCopy.receipt) {
            return __WEBPACK_IMPORTED_MODULE_3__beans_servlet__["a" /* Servlet */].getServiceUrl(this.reimCopy.receipt.url);
        }
        else {
            return '';
        }
    };
    ReimbursementDetailComponent.prototype.onTypeSelect = function (id) {
        var _this = this;
        this.reimTypeService.get(id).subscribe(function (type) {
            _this.reimCopy.type = type;
        });
    };
    ReimbursementDetailComponent.prototype.onStatusSelect = function (statusId) {
        this.reimCopy.status = this.getStatusById(Number(statusId));
        this.reimCopy.resolver = this.user;
        this.reimCopy.resolved = this.datePipe.transform(new Date(), 'yyyy-MM-ddThh:mm:ss.S');
    };
    ReimbursementDetailComponent.prototype.onSave = function () {
        if (this.validateObject()) {
            if (this.inReceiptUpdateState()) {
                this.uploadReceipt();
            }
            else {
                this.saveReimbursement();
            }
        }
        else {
            this.alertService.push('reimbursement form validation failed', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
        }
    };
    ReimbursementDetailComponent.prototype.onCancel = function () {
        this.cloneObject();
    };
    ReimbursementDetailComponent.prototype.inUpdateState = function () {
        return (this.reimbursement.state === __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE);
    };
    ReimbursementDetailComponent.prototype.inApproveState = function () {
        return (this.reimbursement.state === __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_APPROVE);
    };
    ReimbursementDetailComponent.prototype.inEditableState = function () {
        switch (true) {
            case (this.inUpdateState()):
            case (this.inApproveState()):
            case (this.inReceiptUpdateState()):
                return true;
            default:
                return false;
        }
    };
    ReimbursementDetailComponent.prototype.inReceiptUpdateState = function () {
        switch (true) {
            case (this.reimCopy.state === __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE_RECEIPT):
            case (this.isNew()):
                return true;
            default:
                return false;
        }
    };
    ReimbursementDetailComponent.prototype.isNew = function () {
        switch (true) {
            case (this.reimCopy === null):
            case (this.reimCopy === undefined):
                return false;
            case (this.reimCopy.id === 0):
            case (this.reimCopy.author === undefined):
                return true;
            default:
                return false;
        }
    };
    ReimbursementDetailComponent.prototype.setReceiptUpdateState = function () {
        this.reimCopy.state = __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE_RECEIPT;
    };
    ReimbursementDetailComponent.prototype.uploadReceipt = function () {
        var fileBrowser = this.receiptUpload.nativeElement;
        var uploadedReceipt;
        if (fileBrowser.files && fileBrowser.files.length > 0) {
            uploadedReceipt = fileBrowser.files[0];
            this.receiptService.save(uploadedReceipt);
        }
        else {
            this.alertService.push('you MUST provide a receipt file', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
        }
    };
    ReimbursementDetailComponent.prototype.saveReimbursement = function () {
        this.reimService.save(this.reimCopy);
    };
    ReimbursementDetailComponent.prototype.getTypeById = function (id) {
        return this.reimTypes.filter(function (rType) { return rType.id === id; })[0];
    };
    ReimbursementDetailComponent.prototype.getStatusById = function (id) {
        return this.reimStatuses.filter(function (rStatus) { return rStatus.id === id; })[0];
    };
    ReimbursementDetailComponent.prototype.cloneObject = function () {
        this.reimCopy = new __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["a" /* Reimbursement */]();
        __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].copy(this.reimbursement, this.reimCopy);
        if (this.isNew()) {
            this.reimCopy.author = this.user;
            this.reimCopy.type = this.reimTypes[0];
            this.reimCopy.status = this.getStatusById(1);
        }
    };
    ReimbursementDetailComponent.prototype.validateObject = function () {
        var object = this.reimCopy;
        switch (true) {
            case (object === null):
            case (object === undefined):
                this.alertService.push('reimbursement MUST be instantiated', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
                return false;
            case (object.amount === undefined):
            case (object.amount === 0):
                this.alertService.push('reimbursement MUST have a requested amount', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
                return false;
            case (object.author === undefined):
            case (object.author === null):
                this.alertService.push('reimbursement is not detecting you as the author', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
                return false;
            case (object.type === undefined):
            case (object.type === null):
                this.alertService.push('reimbursement MUST have a type', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
                return false;
            case (object.description === undefined):
            case (object.description === null):
            case (object.description.trim().length === 0):
                this.alertService.push('reimbursement MUST have a description', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
                return false;
            default:
                return true;
        }
    };
    ReimbursementDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.userSubscription = this.loginService.getCurrentUser()
            .subscribe(function (user) { return _this.user = user; });
        this.savedReceiptsSubscription = this.receiptService.getSaved()
            .subscribe(function (receipt) {
            _this.reimCopy.receipt = receipt;
            _this.reimCopy.state = __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE;
            _this.saveReimbursement();
        }, function (error) {
            _this.alertService.push('receipt upload failed', __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
        });
        this.savedReimbursementsSubscription = this.reimService.getSaved()
            .subscribe(function (reimbursement) {
            __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["b" /* ReimbursementWrapper */].copy(reimbursement, _this.reimbursement);
            _this.cloneObject();
            $('#app-reimbursement-detail').modal('hide');
        });
        this.reimbursementStatusListSubscription = this.reimStatusService.getList()
            .subscribe(function (statuses) { return _this.reimStatuses = statuses; });
        this.reimbursementTypeListSubscription = this.reimTypeService.getList()
            .subscribe(function (types) { return _this.reimTypes = types; });
    };
    ReimbursementDetailComponent.prototype.ngOnChanges = function () {
        this.cloneObject();
    };
    ReimbursementDetailComponent.prototype.ngOnDestroy = function () {
        this.userSubscription.unsubscribe();
        this.savedReceiptsSubscription.unsubscribe();
        this.reimbursementStatusListSubscription.unsubscribe();
        this.reimbursementTypeListSubscription.unsubscribe();
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["D" /* Input */])(),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_2__beans_reimbursement__["a" /* Reimbursement */])
    ], ReimbursementDetailComponent.prototype, "reimbursement", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_9" /* ViewChild */])('receiptUpload'),
        __metadata("design:type", Object)
    ], ReimbursementDetailComponent.prototype, "receiptUpload", void 0);
    ReimbursementDetailComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-reimbursement-detail',
            template: __webpack_require__("../../../../../src/app/components/reimbursement-detail/reimbursement-detail.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/reimbursement-detail/reimbursement-detail.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_9__services_login_service__["a" /* LoginService */],
            __WEBPACK_IMPORTED_MODULE_5__services_reimbursements_service__["a" /* ReimbursementsService */],
            __WEBPACK_IMPORTED_MODULE_6__services_reimbursement_type_service__["a" /* ReimbursementTypeService */],
            __WEBPACK_IMPORTED_MODULE_7__services_reimbursement_status_service__["a" /* ReimbursementStatusService */],
            __WEBPACK_IMPORTED_MODULE_8__services_receipt_service__["a" /* ReceiptService */],
            __WEBPACK_IMPORTED_MODULE_4__services_alert_service__["b" /* AlertService */],
            __WEBPACK_IMPORTED_MODULE_1__angular_common__["d" /* DatePipe */]])
    ], ReimbursementDetailComponent);
    return ReimbursementDetailComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/reimbursement-list/reimbursement-list.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/reimbursement-list/reimbursement-list.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"row mb-4\">\n\t<div class=\"col-md-10\">\n\t\t<h1 class=\"display-5\">Reimbursements</h1>\n\t</div>\n\n\t<div class=\"col-md-2\">\n\t\t<button type=\"button\" class=\"btn btn-success btn-lg\" (click)=\"startNewReimbursement()\" data-toggle=\"modal\" data-target=\"#app-reimbursement-detail\">\n\t\t\t<span class=\"fa fa-plus mr-1\"></span>\n\t\t\tNew Request\n\t\t</button>\n\t</div>\n</div>\n\n<!-- Search Bar -->\n<div class=\"row mb-4\">\n\t<div class=\"col-md-12\">\n\t\t<form>\n\t\t\t<div class=\"input-group\">\n\t\t\t\t<span class=\"input-group-button\">\n\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" (click)=\"keyword = ''\">\n\t\t\t\t\t\t<span class=\"fa fa-refresh\"></span>\n\t\t\t\t\t</button>\n\t\t\t\t</span>\n\t\t\t\t<span class=\"input-group-addon\">\n\t\t\t\t\t<span class=\"fa fa-filter\"></span>\n\t\t\t\t</span>\n\t\t\t\t<input type=\"text\" class=\"form-control\" placeholder=\"filter ...\" name=\"keyword\" [(ngModel)]=\"keyword\">\n\t\t\t</div>\n\t\t</form>\n\t</div>\n</div>\n\n<!-- Nav tabs -->\n<ul class=\"nav nav-tabs\" role=\"tablist\">\n\t<li class=\"nav-item\">\n\t\t<a class=\"nav-link active\" data-toggle=\"tab\" href=\"#tab-all\" role=\"tab\" (click)=\"state = STATE_SHOW_PENDING\">\n\t\t\tPending\n\t\t\t<span class=\"badge badge-pill badge-primary ml-1\">{{ getReimbursements(STATE_SHOW_PENDING).length }}</span>\n\t\t</a>\n\t</li>\n\t<li class=\"nav-item\">\n\t\t<a class=\"nav-link\" data-toggle=\"tab\" href=\"#tab-all\" role=\"tab\" (click)=\"state = STATE_SHOW_APPROVED\">\n\t\t\tApproved\n\t\t\t<span class=\"badge badge-pill badge-success ml-1\">{{ getReimbursements(STATE_SHOW_APPROVED).length }}</span>\n\t\t</a>\n\t</li>\n\t<li class=\"nav-item\">\n\t\t<a class=\"nav-link\" data-toggle=\"tab\" href=\"#tab-all\" role=\"tab\" (click)=\"state = STATE_SHOW_DENIED\">\n\t\t\tDenied\n\t\t\t<span class=\"badge badge-pill badge-danger ml-1\">{{ getReimbursements(STATE_SHOW_DENIED).length }}</span>\n\t\t</a>\n\t</li>\n\t<li class=\"nav-item\">\n\t\t<a class=\"nav-link\" data-toggle=\"tab\" href=\"#tab-all\" role=\"tab\" (click)=\"state = STATE_SHOW_ALL\">\n\t\t\tALL\n\t\t\t<span class=\"badge badge-pill badge-secondary ml-1\">{{ getReimbursements('all').length }}</span>\n\t\t</a>\n\t</li>\n</ul>\n\n<!-- Tab panes -->\n<div class=\"tab-content\">\n\n\t<!-- ALL Reimbursements Tab (using states to change the list)-->\n\t<div class=\"tab-pane active\" id=\"tab-all\" role=\"tabpanel\">\n\t\t<table class=\"table table-striped\">\n\t\t\t<colgroup>\n\t\t\t\t<col width=\"8%\">\n\t\t\t\t<col width=\"10%\">\n\t\t\t\t<col width=\"15%\">\n\t\t\t\t<col width=\"*\">\n\t\t\t\t<col width=\"15%\">\n\t\t\t\t<col width=\"15%\">\n\t\t\t\t<col width=\"7%\">\n\t\t\t</colgroup>\n\n\t\t\t<thead>\n\t\t\t\t\t<tr>\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tID\n\t\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('id', false)\">\n\t\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t\t</button>\n\t\t\t\t\t\t</th>\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tType\n\t\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('type.description', false)\">\n\t\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t\t</button>\n\t\t\t\t\t\t</th>\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tSubmitted\n\t\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('submitted', false)\">\n\t\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t\t</button>\n\t\t\t\t\t\t</th>\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tAuthor\n\t\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('author.displayName', false)\">\n\t\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t\t</button>\n\t\t\t\t\t\t</th>\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tStatus\n\t\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('status.description', false)\">\n\t\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t\t</button>\n\t\t\t\t\t\t</th>\n\t\t\t\t\t\t<th class=\"text-right\">\n\t\t\t\t\t\t\tAmount\n\t\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('amount', false)\">\n\t\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t\t</button>\n\t\t\t\t\t\t</th>\n\t\t\t\t\t\t<th>&nbsp;</th>\n\t\t\t\t\t</tr>\n\t\t\t</thead>\n\n\t\t\t<tbody>\n\t\t\t\t<tr *ngFor=\"let reimbursement of getReimbursements(state)\">\n\t\t\t\t\t<td>{{ reimbursement.id }}</td>\n\t\t\t\t\t<td>{{ reimbursement.type.description }}</td>\n\t\t\t\t\t<td>{{ reimbursement.submitted | date:'MM/dd/yyyy'}}</td>\n\t\t\t\t\t<td>{{ reimbursement.author.displayName }}</td>\n\t\t\t\t\t<td>{{ reimbursement.status.description }}</td>\n\t\t\t\t\t<td class=\"text-right\">{{ reimbursement.amount | currency }}</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\t<app-reimbursement-button [reimbursement]=\"reimbursement\" (click)=\"openDetailView(reimbursement)\" data-target=\"#app-reimbursement-detail\" data-toggle=\"modal\"></app-reimbursement-button>\n\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\t\t\t</tbody>\n\n\t\t\t<tfoot>\n\t\t\t</tfoot>\n\t\t</table>\n\t</div>\n\n\t<app-reimbursement-detail [reimbursement]=\"selectedReimbursement\"></app-reimbursement-detail>\n\n</div>\n<!-- BEGIN: templates -->\n\n<!-- EMPTY FIELDS -->\n<ng-template #emptyField>\n\t<span class=\"text-center\">---</span>\n</ng-template>"

/***/ }),

/***/ "../../../../../src/app/components/reimbursement-list/reimbursement-list.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReimbursementListComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Subscription__ = __webpack_require__("../../../../rxjs/_esm5/Subscription.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__ = __webpack_require__("../../../../../src/app/beans/reimbursement.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pipes_reimbursement_status_pipe__ = __webpack_require__("../../../../../src/app/pipes/reimbursement-status.pipe.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_reimbursements_service__ = __webpack_require__("../../../../../src/app/services/reimbursements.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_authorization_service__ = __webpack_require__("../../../../../src/app/services/authorization.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__services_login_service__ = __webpack_require__("../../../../../src/app/services/login.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


// rxjs

// beans

// pipes

// services



var ReimbursementListComponent = (function () {
    function ReimbursementListComponent(reimService, authService, loginService, router) {
        this.STATE_SHOW_PENDING = 'pending';
        this.STATE_SHOW_APPROVED = 'approved';
        this.STATE_SHOW_DENIED = 'denied';
        this.STATE_SHOW_ALL = 'all';
        this.reimbursements = [];
        this.reimService = reimService;
        this.authService = authService;
        this.loginService = loginService;
        this.keyword = '';
        this.state = 'pending';
        this.selectedReimbursement = new __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__["a" /* Reimbursement */]();
        this.reimbursementListSubscription = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subscription__["a" /* Subscription */]();
        this.currentUserSubscription = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subscription__["a" /* Subscription */]();
        this.router = router;
        this.reimPipe = new __WEBPACK_IMPORTED_MODULE_4__pipes_reimbursement_status_pipe__["a" /* ReimbursementStatusPipe */]();
    }
    ReimbursementListComponent.prototype.getFilteredReimbursements = function () {
        var _this = this;
        return this.reimbursements
            .filter(function (reimbursment) {
            var pattern = new RegExp(".*" + _this.keyword.toLowerCase() + ".*");
            switch (true) {
                case pattern.test(reimbursment.description.toLowerCase()):
                case pattern.test(reimbursment.author.displayName.toLowerCase()):
                case pattern.test(reimbursment.resolver.displayName.toLowerCase()):
                case pattern.test(reimbursment.status.description.toLowerCase()):
                case pattern.test(reimbursment.type.description.toLowerCase()):
                case pattern.test(String(reimbursment.id)):
                    return true;
                default:
                    return false;
            }
        });
    };
    ReimbursementListComponent.prototype.getReimbursements = function (mode) {
        return this.reimPipe.transform(this.getFilteredReimbursements(), mode);
    };
    ReimbursementListComponent.prototype.sort = function (field, desc) {
        this.reimbursements.sort(function (a, b) {
            var aValue = __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__["b" /* ReimbursementWrapper */].getFieldByString(a, field);
            var bValue = __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__["b" /* ReimbursementWrapper */].getFieldByString(b, field);
            switch (true) {
                case (aValue > bValue):
                    return 1;
                case (aValue < bValue):
                    return -1;
                default:
                    return 0;
            }
        });
    };
    ReimbursementListComponent.prototype.openDetailView = function (reimbursement) {
        this.selectedReimbursement = reimbursement;
    };
    ReimbursementListComponent.prototype.startNewReimbursement = function () {
        var subject = new __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__["a" /* Reimbursement */]();
        subject.id = 0;
        subject.state = __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__["b" /* ReimbursementWrapper */].STATE_UPDATE;
        subject.author = this.user;
        this.selectedReimbursement = subject;
    };
    ReimbursementListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.reimbursementListSubscription = this.reimService.getList()
            .subscribe(function (reimbursements) { return _this.reimbursements = reimbursements; });
        this.currentUserSubscription = this.loginService.getCurrentUser()
            .subscribe(function (user) { return _this.user = user; });
    };
    ReimbursementListComponent.prototype.ngOnDestroy = function () {
        this.reimbursementListSubscription.unsubscribe();
        this.currentUserSubscription.unsubscribe();
    };
    ReimbursementListComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-reimbursement-list',
            template: __webpack_require__("../../../../../src/app/components/reimbursement-list/reimbursement-list.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/reimbursement-list/reimbursement-list.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_5__services_reimbursements_service__["a" /* ReimbursementsService */],
            __WEBPACK_IMPORTED_MODULE_6__services_authorization_service__["a" /* AuthorizationService */],
            __WEBPACK_IMPORTED_MODULE_7__services_login_service__["a" /* LoginService */],
            __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]])
    ], ReimbursementListComponent);
    return ReimbursementListComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/user-detail/user-detail.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/user-detail/user-detail.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"modal\" tabindex=\"-1\" role=\"dialog\">\n  <div class=\"modal-dialog\" role=\"document\">\n    <div class=\"modal-content\">\n      <div class=\"modal-header\">\n        <h5 class=\"modal-title\">{{ user.displayName }}</h5>\n        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n          <span aria-hidden=\"true\">&times;</span>\n        </button>\n      </div>\n      <div class=\"modal-body\">\n        <p>Modal body text goes here.</p>\n      </div>\n      <div class=\"modal-footer\">\n        <button type=\"button\" class=\"btn btn-primary\">Save changes</button>\n        <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n      </div>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/components/user-detail/user-detail.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserDetailComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__beans_user__ = __webpack_require__("../../../../../src/app/beans/user.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

// beans

var UserDetailComponent = (function () {
    function UserDetailComponent() {
    }
    UserDetailComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["D" /* Input */])(),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1__beans_user__["a" /* User */])
    ], UserDetailComponent.prototype, "user", void 0);
    UserDetailComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-user-detail',
            template: __webpack_require__("../../../../../src/app/components/user-detail/user-detail.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/user-detail/user-detail.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], UserDetailComponent);
    return UserDetailComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/user-list/user-list.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/user-list/user-list.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"row mb-4\">\n\t<h1 class=\"display-5\">Users</h1>\n</div>\n\n<div class=\"row mb-4\">\n\t<div class=\"col-md-12\">\n\t\t<form>\n\t\t\t<div class=\"input-group\">\n\t\t\t\t<span class=\"input-group-button\">\n\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" (click)=\"keyword = ''\">\n\t\t\t\t\t\t<span class=\"fa fa-refresh\"></span>\n\t\t\t\t\t</button>\n\t\t\t\t</span>\n\t\t\t\t<span class=\"input-group-addon\">\n\t\t\t\t\t<span class=\"fa fa-filter\"></span>\n\t\t\t\t</span>\n\t\t\t\t<input type=\"text\" class=\"form-control\" placeholder=\"filter ...\" name=\"keyword\" [(ngModel)]=\"keyword\">\n\t\t\t</div>\n\t\t</form>\n\t</div>\n</div>\n\n<div class=\"row\">\n\t<table class=\"table table-striped\">\n\t\t<colgroup>\n\t\t\t<col width=\"10%\">\n\t\t\t<col width=\"20%\">\n\t\t\t<col width=\"25\">\n\t\t\t<col width=\"25\">\n\t\t\t<col width=\"20%\">\n\t\t</colgroup>\n\n\t\t<thead>\n\t\t\t\t<tr>\n\t\t\t\t\t<th>\n\t\t\t\t\t\tID\n\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('id', false)\">\n\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t</button>\n\t\t\t\t\t</th>\n\t\t\t\t\t<th>\n\t\t\t\t\t\tUsername\n\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('identity', false)\">\n\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t</button>\n\t\t\t\t\t</th>\n\t\t\t\t\t<th>\n\t\t\t\t\t\tLast Name\n\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('lastName', false)\">\n\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t</button>\n\t\t\t\t\t</th>\n\t\t\t\t\t<th>\n\t\t\t\t\t\tFirst Name\n\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('firstName', false)\">\n\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t</button>\n\t\t\t\t\t</th>\n\t\t\t\t\t<th>\n\t\t\t\t\t\tRole\n\t\t\t\t\t\t<button class=\"btn btn-default btn-sm ml-1\" (click)=\"sort('role.description', false)\">\n\t\t\t\t\t\t\t<span class=\"fa fa-sort-amount-asc\"></span>\n\t\t\t\t\t\t</button>\n\t\t\t\t\t</th>\n\t\t\t\t</tr>\n\t\t</thead>\n\n\t\t<tbody>\n\t\t\t<tr *ngFor=\"let user of getFilteredUsers()\">\n\t\t\t\t<td>{{ user.id }}</td>\n\t\t\t\t<td>{{ user.identity }}</td>\n\t\t\t\t<td>{{ user.lastName }}</td>\n\t\t\t\t<td>{{ user.firstName }}</td>\n\t\t\t\t<td>{{ user.role.description }}</td>\n\t\t\t</tr>\n\t\t</tbody>\n\n\t\t<tfoot>\n\t\t</tfoot>\n\t</table>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/components/user-list/user-list.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserListComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_users_service__ = __webpack_require__("../../../../../src/app/services/users.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__beans_user__ = __webpack_require__("../../../../../src/app/beans/user.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

// services

// beans

var UserListComponent = (function () {
    function UserListComponent(userService) {
        this.userService = userService;
        this.keyword = '';
        this.users = [];
    }
    UserListComponent.prototype.getFilteredUsers = function () {
        var _this = this;
        return this.users
            .filter(function (user) {
            if (_this.keyword.length > 0) {
                var pattern = new RegExp(".*" + _this.keyword.toLowerCase() + ".*");
                switch (true) {
                    case pattern.test(user.identity.toLowerCase()):
                    case pattern.test(user.lastName.toLowerCase()):
                    case pattern.test(user.firstName.toLowerCase()):
                    case pattern.test(user.role.description.toLowerCase()):
                        return true;
                    default:
                        return false;
                }
            }
            else {
                return true;
            }
        });
    };
    UserListComponent.prototype.sort = function (field, desc) {
        if (desc === void 0) { desc = false; }
        this.users.sort(function (a, b) {
            var aValue = __WEBPACK_IMPORTED_MODULE_2__beans_user__["b" /* UserWrapper */].getFieldByString(a, field);
            var bValue = __WEBPACK_IMPORTED_MODULE_2__beans_user__["b" /* UserWrapper */].getFieldByString(b, field);
            switch (true) {
                case (aValue > bValue):
                    return 1;
                case (aValue < bValue):
                    return -1;
                default:
                    return 0;
            }
        });
        if (desc) {
            this.users.reverse();
        }
    };
    UserListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.userListSubscription = this.userService.getList()
            .subscribe(function (users) { return _this.users = users; });
    };
    UserListComponent.prototype.ngOnDestroy = function () {
        this.userListSubscription.unsubscribe();
    };
    UserListComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-user-list',
            template: __webpack_require__("../../../../../src/app/components/user-list/user-list.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/user-list/user-list.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_users_service__["a" /* UsersService */]])
    ], UserListComponent);
    return UserListComponent;
}());



/***/ }),

/***/ "../../../../../src/app/guards/route.guard.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RouteGuard; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_route_permission_service__ = __webpack_require__("../../../../../src/app/services/route-permission.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


// services

var RouteGuard = (function () {
    function RouteGuard(routePermService, router) {
        this.routePermService = routePermService;
        this.router = router;
    }
    RouteGuard.prototype.canActivate = function (route, state) {
        var allowed = this.routePermService.authorize(route.routeConfig.path) === false;
        if (allowed) {
            this.router.navigate(['home']);
            return false;
        }
        else {
            return true;
        }
    };
    RouteGuard = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__services_route_permission_service__["a" /* RoutePermissionService */],
            __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]])
    ], RouteGuard);
    return RouteGuard;
}());



/***/ }),

/***/ "../../../../../src/app/pipes/reimbursement-status.pipe.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReimbursementStatusPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__ = __webpack_require__("../../../../../src/app/beans/reimbursement.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


var ReimbursementStatusPipe = (function () {
    function ReimbursementStatusPipe() {
    }
    ReimbursementStatusPipe.prototype.transform = function (reimbursements, mode) {
        switch (mode.toLowerCase()) {
            case 'pending':
                return reimbursements.filter(function (reim) { return reim.status.id === __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATUS_PENDING; });
            case 'approved':
                return reimbursements.filter(function (reim) { return reim.status.id === __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATUS_APPROVED; });
            case 'denied':
                return reimbursements.filter(function (reim) { return reim.status.id === __WEBPACK_IMPORTED_MODULE_1__beans_reimbursement__["b" /* ReimbursementWrapper */].STATUS_DENIED; });
            default:
                return reimbursements;
        }
    };
    ReimbursementStatusPipe = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["T" /* Pipe */])({
            name: 'rStatus',
        })
    ], ReimbursementStatusPipe);
    return ReimbursementStatusPipe;
}());



/***/ }),

/***/ "../../../../../src/app/services/alert.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return AlertService; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AlertMessage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_Subject__ = __webpack_require__("../../../../rxjs/_esm5/Subject.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AlertService = (function () {
    function AlertService() {
        this.subject = new __WEBPACK_IMPORTED_MODULE_1_rxjs_Subject__["a" /* Subject */]();
    }
    AlertService.prototype.push = function (message, category) {
        var alert = new AlertMessage(message, category);
        this.subject.next(alert);
    };
    /*
    * BEGIN: observables
    */
    AlertService.prototype.getNotifications = function () {
        return this.subject.asObservable();
    };
    AlertService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [])
    ], AlertService);
    return AlertService;
}());

var AlertMessage = (function () {
    function AlertMessage(message, category) {
        this.message = message;
        this.category = category;
        this.id = ++AlertMessage.idCounter;
    }
    AlertMessage.idCounter = 0;
    AlertMessage.CATEGORY_INFO = 'info';
    AlertMessage.CATEGORY_SUCCESS = 'success';
    AlertMessage.CATEGORY_ERROR = 'error';
    AlertMessage.CATEGORY_WARNING = 'warning';
    AlertMessage.CATEGORY_DEFAULT = 'default';
    return AlertMessage;
}());



/***/ }),

/***/ "../../../../../src/app/services/authorization.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthorizationService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AuthorizationService = (function () {
    function AuthorizationService(client) {
        this.client = client;
        this.url = __WEBPACK_IMPORTED_MODULE_2__beans_servlet__["a" /* Servlet */].getServiceUrl('acl');
    }
    AuthorizationService.prototype.authorize = function (request) {
        var data = request.toJson();
        return this.client.post(this.url, data, {
            withCredentials: true,
        });
    };
    AuthorizationService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], AuthorizationService);
    return AuthorizationService;
}());



/***/ }),

/***/ "../../../../../src/app/services/login.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_Subject__ = __webpack_require__("../../../../rxjs/_esm5/Subject.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_share__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/share.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__alert_service__ = __webpack_require__("../../../../../src/app/services/alert.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






// other services

var LoginService = (function () {
    function LoginService(client, alertService, router) {
        this.router = router;
        this.client = client;
        this.url = __WEBPACK_IMPORTED_MODULE_5__beans_servlet__["a" /* Servlet */].getServiceUrl('login');
        this.alertService = alertService;
        this.subject = new __WEBPACK_IMPORTED_MODULE_3_rxjs_Subject__["a" /* Subject */]();
    }
    LoginService.prototype.login = function (identity, credential) {
        this.post(identity, credential);
    };
    LoginService.prototype.logout = function () {
        this.delete();
    };
    /*
    * BEGIN: observables
    */
    LoginService.prototype.getCurrentUser = function () {
        this.get();
        return this.subject;
    };
    /*
    * BEGIN: CRUD
    */
    LoginService.prototype.get = function () {
        var _this = this;
        this.client.get(this.url, { withCredentials: true })
            .subscribe(function (user) {
            _this.subject.next(user);
        });
    };
    LoginService.prototype.post = function (identity, credential) {
        var _this = this;
        console.log('attempting to authenticate user');
        var body = JSON.stringify({
            identity: identity,
            credential: credential
        });
        this.client.post(this.url, body, { withCredentials: true }).subscribe(function (user) {
            _this.subject.next(user);
            _this.broadcast("user " + user.identity + " authenticated successfully", __WEBPACK_IMPORTED_MODULE_6__alert_service__["a" /* AlertMessage */].CATEGORY_SUCCESS);
        }, function (error) {
            console.log('user could not be authenticated');
            _this.subject.next(null);
            _this.broadcast('authentication failed', __WEBPACK_IMPORTED_MODULE_6__alert_service__["a" /* AlertMessage */].CATEGORY_ERROR);
        });
    };
    LoginService.prototype.delete = function () {
        var _this = this;
        this.client.delete(this.url, { withCredentials: true }).subscribe(function (user) {
            _this.subject.next(null);
            _this.router.navigate(['home']);
            _this.broadcast("user " + user.identity + " signed out successfully", __WEBPACK_IMPORTED_MODULE_6__alert_service__["a" /* AlertMessage */].CATEGORY_INFO);
        });
    };
    LoginService.prototype.broadcast = function (message, category) {
        this.alertService.push(message, category);
        console.log(message);
    };
    LoginService.USER_KEY = 'login-user';
    LoginService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_6__alert_service__["b" /* AlertService */], __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */]])
    ], LoginService);
    return LoginService;
}());



/***/ }),

/***/ "../../../../../src/app/services/receipt.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReceiptService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__ = __webpack_require__("../../../../rxjs/_esm5/Subject.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__alert_service__ = __webpack_require__("../../../../../src/app/services/alert.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



// beans

// other services

var ReceiptService = (function () {
    function ReceiptService(client, alertService) {
        this.client = client;
        this.url = __WEBPACK_IMPORTED_MODULE_3__beans_servlet__["a" /* Servlet */].getServiceUrl('receipt');
        this.alertService = alertService;
        this.savedSubject = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__["a" /* Subject */]();
    }
    ReceiptService.prototype.save = function (receipt) {
        var _this = this;
        var form = new FormData();
        console.log('attempting to upload a new receipt document');
        form.append('file', receipt, receipt.name);
        this.client.post(this.url, form, { withCredentials: true })
            .subscribe(function (savedReceipt) {
            _this.savedSubject.next(savedReceipt);
            _this.broadcast('receipt uploaded successfully', __WEBPACK_IMPORTED_MODULE_4__alert_service__["a" /* AlertMessage */].CATEGORY_SUCCESS);
        });
    };
    /*
    * BEGIN: observables
    */
    ReceiptService.prototype.getSaved = function () {
        return this.savedSubject;
    };
    ReceiptService.prototype.broadcast = function (message, category) {
        this.alertService.push(message, category);
        console.log(message);
    };
    ReceiptService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_4__alert_service__["b" /* AlertService */]])
    ], ReceiptService);
    return ReceiptService;
}());



/***/ }),

/***/ "../../../../../src/app/services/reimbursement-status.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReimbursementStatusService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__ = __webpack_require__("../../../../rxjs/_esm5/Subject.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ReimbursementStatusService = (function () {
    function ReimbursementStatusService(client) {
        this.client = client;
        this.url = __WEBPACK_IMPORTED_MODULE_3__beans_servlet__["a" /* Servlet */].getServiceUrl('reimbursementstatuses');
        this.listSubject = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__["a" /* Subject */]();
        this.getAll();
    }
    /*
    * BEGIN: observables
    */
    ReimbursementStatusService.prototype.getList = function () {
        this.getAll();
        return this.listSubject;
    };
    /*
    * BEGIN: CRUD
    */
    ReimbursementStatusService.prototype.getAll = function () {
        var _this = this;
        this.client.get(this.url, { withCredentials: true })
            .subscribe(function (statuses) { return _this.listSubject.next(statuses); });
    };
    ReimbursementStatusService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], ReimbursementStatusService);
    return ReimbursementStatusService;
}());



/***/ }),

/***/ "../../../../../src/app/services/reimbursement-type.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReimbursementTypeService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__ = __webpack_require__("../../../../rxjs/_esm5/Subject.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_take__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/take.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ReimbursementTypeService = (function () {
    function ReimbursementTypeService(client) {
        this.client = client;
        this.url = __WEBPACK_IMPORTED_MODULE_4__beans_servlet__["a" /* Servlet */].getServiceUrl('reimbursementtypes');
        this.listSubject = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__["a" /* Subject */]();
    }
    /*
    * BEGIN: observables
    */
    ReimbursementTypeService.prototype.getList = function () {
        this.getAll();
        return this.listSubject;
    };
    ReimbursementTypeService.prototype.get = function (id) {
        var url = [this.url, id].join('/');
        return this.client.get(url, { withCredentials: true });
    };
    /*
    * BEGIN: CRUD
    */
    ReimbursementTypeService.prototype.getAll = function () {
        var _this = this;
        this.client.get(this.url, { withCredentials: true })
            .subscribe(function (types) {
            _this.listSubject.next(types);
        });
    };
    ReimbursementTypeService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], ReimbursementTypeService);
    return ReimbursementTypeService;
}());



/***/ }),

/***/ "../../../../../src/app/services/reimbursements.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReimbursementsService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__ = __webpack_require__("../../../../rxjs/_esm5/Subject.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__ = __webpack_require__("../../../../../src/app/beans/reimbursement.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__alert_service__ = __webpack_require__("../../../../../src/app/services/alert.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



// beans


// other services

var ReimbursementsService = (function () {
    function ReimbursementsService(client, alertService) {
        this.client = client;
        this.url = __WEBPACK_IMPORTED_MODULE_4__beans_servlet__["a" /* Servlet */].getServiceUrl('reimbursements');
        this.listSubject = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__["a" /* Subject */]();
        this.saveSubject = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__["a" /* Subject */]();
        this.alertService = alertService;
    }
    ReimbursementsService.prototype.save = function (reimbursement) {
        console.log('attempting to save new reimbursement');
        if (reimbursement.id === 0) {
            this.post(reimbursement);
        }
        else {
            this.put(reimbursement);
        }
    };
    /*
    * BEGIN: observables
    */
    ReimbursementsService.prototype.getList = function () {
        this.getAll();
        return this.listSubject;
    };
    ReimbursementsService.prototype.getSaved = function () {
        return this.saveSubject;
    };
    /*
    * BEGIN: CRUD
    */
    ReimbursementsService.prototype.getAll = function () {
        var _this = this;
        this.client.get(this.url, { withCredentials: true })
            .subscribe(function (reimbursements) {
            _this.listSubject.next(reimbursements);
        });
    };
    ReimbursementsService.prototype.post = function (reimbursement) {
        var _this = this;
        var url = this.url;
        var dataObject = __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__["b" /* ReimbursementWrapper */].prepareForDao(reimbursement);
        var data = JSON.stringify(dataObject);
        this.client.post(url, data, { withCredentials: true })
            .subscribe(function (savedReimbursement) {
            _this.saveSubject.next(savedReimbursement);
            _this.getAll();
            _this.broadcast('saved new reimbursement successfully', __WEBPACK_IMPORTED_MODULE_5__alert_service__["a" /* AlertMessage */].CATEGORY_SUCCESS);
        });
    };
    ReimbursementsService.prototype.put = function (reimbursement) {
        var _this = this;
        var url = [this.url, reimbursement.id].join('/');
        var dataObject = __WEBPACK_IMPORTED_MODULE_3__beans_reimbursement__["b" /* ReimbursementWrapper */].prepareForDao(reimbursement);
        var data = JSON.stringify(dataObject);
        this.client.put(url, data, { withCredentials: true })
            .subscribe(function (updatedReimbursement) {
            _this.saveSubject.next(updatedReimbursement);
            _this.broadcast('updated reimbursement successfully', __WEBPACK_IMPORTED_MODULE_5__alert_service__["a" /* AlertMessage */].CATEGORY_SUCCESS);
        });
    };
    ReimbursementsService.prototype.broadcast = function (message, category) {
        this.alertService.push(message, category);
        console.log(message);
    };
    ReimbursementsService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_5__alert_service__["b" /* AlertService */]])
    ], ReimbursementsService);
    return ReimbursementsService;
}());



/***/ }),

/***/ "../../../../../src/app/services/route-permission.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RoutePermissionService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__login_service__ = __webpack_require__("../../../../../src/app/services/login.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

// services

var RoutePermissionService = (function () {
    function RoutePermissionService(loginService) {
        var _this = this;
        loginService.getCurrentUser().subscribe(function (user) {
            _this.user = user;
        });
    }
    RoutePermissionService.prototype.authorize = function (path) {
        switch (path.toLowerCase()) {
            case ('home'):
                return true;
            case ('reimbursements'):
                return (this.detectAuthenticated(this.user));
            case ('users'):
                if (this.detectAuthenticated(this.user) === false) {
                    return false;
                }
                return (this.user.role.id === 1);
            default:
                return false;
        }
    };
    /*
      * BEGIN: helper functions
      */
    RoutePermissionService.prototype.detectAuthenticated = function (user) {
        switch (true) {
            case (user === null):
            case (user === undefined):
            case (user.id === 0):
                // console.log('no user logged in');
                return false;
            default:
                // console.log('user is logged in');
                return true;
        }
    };
    RoutePermissionService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__login_service__["a" /* LoginService */]])
    ], RoutePermissionService);
    return RoutePermissionService;
}());



/***/ }),

/***/ "../../../../../src/app/services/users.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UsersService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__ = __webpack_require__("../../../../rxjs/_esm5/Subject.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__beans_servlet__ = __webpack_require__("../../../../../src/app/beans/servlet.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var UsersService = (function () {
    function UsersService(client) {
        this.client = client;
        this.url = __WEBPACK_IMPORTED_MODULE_3__beans_servlet__["a" /* Servlet */].getServiceUrl('users');
        this.listSubject = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Subject__["a" /* Subject */]();
    }
    /*
    * BEGIN: observables
    */
    UsersService.prototype.getList = function () {
        this.getAll();
        return this.listSubject;
    };
    /*
    * BEGIN: CRUD
    */
    UsersService.prototype.getAll = function () {
        var _this = this;
        this.client.get(this.url, { withCredentials: true })
            .subscribe(function (users) { return _this.listSubject.next(users); });
    };
    UsersService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], UsersService);
    return UsersService;
}());



/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
var environment = {
    production: false
};


/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/esm5/platform-browser-dynamic.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_14" /* enableProdMode */])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map
import { Component } from '@angular/core';
import { Router } from '@angular/router';

// services
import { LoginService } from './services/login.service';

import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

    private router: Router;
    private loginService: LoginService;

    constructor(router: Router, loginService: LoginService) {
        this.router = router;
        this.loginService = loginService;
    }
}

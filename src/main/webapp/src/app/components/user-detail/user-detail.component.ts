import { Component, OnInit, Input } from '@angular/core';

// beans
import { User, UserWrapper } from '../../beans/user';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
    @Input ()public user: User;

    constructor() { }

    ngOnInit() {
    }
}

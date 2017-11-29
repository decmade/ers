import { Component, OnInit, OnDestroy } from '@angular/core';

// rxjs
import { Subscription } from 'rxjs/Subscription';

// services
import { UsersService } from '../../services/users.service';

// beans
import { User, UserWrapper } from '../../beans/user';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {
    public users: User[];
    public keyword: string;
    private userService: UsersService;
    private userListSubscription: Subscription;

    constructor(userService: UsersService) {
        this.userService = userService;
        this.keyword = '';
        this.users = [];
    }

    public getFilteredUsers(): User[] {
        return this.users
            .filter((user) => {
                if ( this.keyword.length > 0 ) {
                    const pattern = new RegExp(`.*${ this.keyword.toLowerCase() }.*`);

                    switch ( true ) {
                        case pattern.test( user.identity.toLowerCase() ) :
                        case pattern.test( user.lastName.toLowerCase() ) :
                        case pattern.test( user.firstName.toLowerCase() ) :
                        case pattern.test( user.role.description.toLowerCase() ) :
                            return true;
                        default:
                            return false;
                    }
                } else {
                    return true;
                }
            });
    }

    public sort(field: string, desc: boolean = false): void {
        this.users.sort( function(a, b) {
            const aValue = UserWrapper.getFieldByString(a, field);
            const bValue = UserWrapper.getFieldByString(b, field);

            switch (true) {
                case ( aValue > bValue ) :
                    return 1;
                case ( aValue < bValue ) :
                    return -1;
                default :
                    return 0;
            }
        });

        if ( desc ) {
            this.users.reverse();
        }
    }

    ngOnInit() {
        this.userListSubscription = this.userService.getList()
            .subscribe( users => this.users = users );
    }

    ngOnDestroy(): void {
        this.userListSubscription.unsubscribe();
    }

}

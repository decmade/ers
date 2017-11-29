import { Injectable } from '@angular/core';

// services
import { LoginService } from './login.service';

// beans
import { User } from '../beans/user';
import { AclRequest } from '../beans/acl';


@Injectable()
export class RoutePermissionService {
    private user: User;

  constructor(
      loginService: LoginService,
  ) {
      loginService.getCurrentUser().subscribe( (user) => {
          this.user = user;
      });
  }

  public authorize(path: string): boolean {
      switch ( path.toLowerCase() ) {
          case ('home') :
              return true;
          case ('reimbursements') :
              return ( this.detectAuthenticated( this.user) );
          case ('users') :
              if ( this.detectAuthenticated( this.user) === false ) {
                  return false;
              }

              return ( this.user.role.id === 1);
         default :
             return false;
      }
  }

  /*
    * BEGIN: helper functions
    */
    private detectAuthenticated(user: User) {
        switch (true) {
            case ( user === null ) :
            case ( user === undefined ) :
            case ( user.id === 0 ) :
                // console.log('no user logged in');
                 return false;
            default :
                // console.log('user is logged in');
                return true;
        }
    }

}

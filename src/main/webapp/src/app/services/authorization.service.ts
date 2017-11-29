import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

// beans
import { User } from '../beans/user';
import { Servlet } from '../beans/servlet';
import { AclResponse, AclRequest } from '../beans/acl';

@Injectable()
export class AuthorizationService {
    private client: HttpClient;
    private url: string;

    constructor(client: HttpClient ) {
      this.client = client;
      this.url = Servlet.getServiceUrl('acl');
    }

    public authorize(request: AclRequest): Observable<AclResponse> {
      const data = request.toJson();

      return this.client.post<AclResponse>( this.url, data, {
          withCredentials: true,
      });
    }
}

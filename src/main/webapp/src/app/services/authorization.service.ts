import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// rxjs
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

// services
import { ApiService } from './api.service';

// beans
import { User } from '../beans/user';
import { AclResponse, AclRequest } from '../beans/acl';

@Injectable()
export class AuthorizationService {
    private http: HttpClient;
    private apiService: ApiService;

    private api: string;

    constructor(httpClient: HttpClient, apiService: ApiService ) {
      this.http = httpClient;
      this.apiService = apiService;

      this.api = 'acl';
    }

    public authorize(request: AclRequest): Observable<AclResponse> {
      const data = request.toJson();
      const url = this.apiService.getApiUrl(this.api);

      return this.http.post<AclResponse>( url, data, {
          withCredentials: true,
      });
    }
}

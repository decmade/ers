import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';



@Injectable()
export class ApiService {

  constructor() { }

  public getApiUrl(apiNode: string, params = []): string {
    return [ 
      this.getApiPrefix(),
      apiNode,
      params.join('/'),
    ].join('/');
  }

  private getApiPrefix(): any {
    return environment.apiPrefix;
  }
}

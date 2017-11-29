export class AclResponse {
    public allowed: boolean;
}

export class AclRequest {
    public verb: string;
    public resourceType: string;
    public resourceId: number;

    public toJson(): any  {
      return JSON.stringify({
        verb: this.verb,
        resourceId: String( this.resourceId ),
        resourceType: this.resourceType,
      });
    }
}
import { GenericWrapper } from './wrappers';

export class User {
    public id: number;
    public identity: string;
    public credential: string;
    public firstName: string;
    public lastName: string;
    public role: UserRole;
    public displayName: string;
}

export class UserRole {
    public id: number;
    public description: string;
}

export class UserWrapper extends GenericWrapper {

}

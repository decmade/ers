import { User } from './user';
import { Receipt } from './receipt';
import { GenericWrapper } from './wrappers';
import { AclResponse } from './acl';

export class Reimbursement {
    id: number;
    description: string;
    type: ReimbursementType;
    submitted: string;
    amount: number;
    author: User;
    receipt: Receipt;
    resolver: User;
    resolved: string;
    status: ReimbursementStatus;
    state: string;
}

export class ReimbursementType {
    id: number;
    description: string;
}

export class ReimbursementStatus {
    id: number;
    description: string;
}

export class ReimbursementWrapper extends GenericWrapper {
    public static STATE_CREATE = 'create';
    public static STATE_VIEW = 'view';
    public static STATE_APPROVE = 'approve';
    public static STATE_UPDATE = 'update';
    public static STATE_UPDATE_RECEIPT = 'update-receipt';

    public static STATUS_PENDING = 1;
    public static STATUS_APPROVED = 2;
    public static STATUS_DENIED = 3;
}

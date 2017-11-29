import { Pipe, PipeTransform } from '@angular/core';
import { Reimbursement, ReimbursementWrapper } from '../beans/reimbursement';

@Pipe({
    name: 'rStatus',
})


export class ReimbursementStatusPipe implements PipeTransform {
    public transform( reimbursements: Reimbursement[], mode: string ) {
        switch ( mode.toLowerCase() ) {
            case 'pending' :
                return reimbursements.filter( reim =>  reim.status.id === ReimbursementWrapper.STATUS_PENDING );
            case 'approved' :
                return reimbursements.filter( reim =>  reim.status.id === ReimbursementWrapper.STATUS_APPROVED );
            case 'denied' :
                return reimbursements.filter( reim =>  reim.status.id === ReimbursementWrapper.STATUS_DENIED );
            default :
                return reimbursements;
        }
    }
}

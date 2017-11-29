package com.revature.ers.acl.policies;

import com.revature.ers.acl.Request;
import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.beans.wrappers.ReceiptWrapper;
import com.revature.ers.beans.wrappers.ReimbursementWrapper;


/**
 * policy for User access to ReimbursementInterface objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReceiptPolicy extends AbstractPolicy 
{
	@Override
	/**
	 * policy for allowing access
	 * 
	 * @param ACLRequest request
	 * 
	 * @return boolean
	 */
	public boolean allow(Request request)
	{
		/*
		 * ONLY check for requests for ReceiptInterface objects 
		 */
		if ( ReceiptInterface.class.isInstance( request.resource ) == false ) {
			return false;
		}
			
		switch( request.verb.toLowerCase() ) {
			case "create" :
				return true;
			case "update" :
			case "delete" :
				if ( this.userIsOwner(request) ) {
					return true;
				}
				
				if ( this.userIsSystemAdministrator(request) ) {
					return true;
				}
				
				break;
			case "read" :
				if ( this.userIsOwner(request) ) {
					return true;
				}
				
				if ( this.userIsSystemAdministrator(request) ) {
					return true;
				}
				
				if ( this.userIsFinanceManager(request)) {
					return true;
				}
				
				break;

		}
		
		/*
		 * return false if not true somehow above
		 */
		return false;
	}
	
	/**
	 * returns true if the user making the request is the author of
	 * the reimbursement that this receipt belongs to
	 * 
	 * @param Request request
	 * 
	 * @return boolean
	 */
	private boolean userIsOwner(Request request)
	{
		ReceiptInterface receipt = (ReceiptInterface)request.resource;
		ReceiptWrapper receiptWrapper = new ReceiptWrapper();
		ReimbursementInterface reimbursement = null;
		ReimbursementWrapper reimWrapper = new ReimbursementWrapper();
		
		receiptWrapper.setSubject( receipt );
		reimbursement = receiptWrapper.getReimbursement();
		reimWrapper.setSubject( reimbursement );
		
		if ( reimWrapper.hasSubject() ) {
			return ( reimWrapper.getAuthor().getId() == request.user.getId() );
		} else {
			return false;
		}

	}
	
}

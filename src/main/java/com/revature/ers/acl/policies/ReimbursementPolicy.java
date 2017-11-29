package com.revature.ers.acl.policies;

import com.revature.ers.acl.Request;
import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.wrappers.ReimbursementWrapper;

/**
 * policy for User access to ReimbursementInterface objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementPolicy extends AbstractPolicy 
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
		 * ONLY check for requests for ReimbursementInterface objects 
		 */
		if ( ReimbursementInterface.class.isInstance( request.resource ) == false ) {
			return false;
		}
			
		switch( request.verb.toLowerCase() ) {
			case "create" :
				return true;
				
			case "read" :
				if ( this.userIsOwner(request) ) {
					return true;
				}
				
				if ( this.userIsFinanceManager(request)) {
					return true;
				}
				
				break;
				
			case "update" :
			case "delete" :
				if ( this.userIsOwner(request) ) {
					return true;
				}
				
				if ( this.userIsFinanceManager(request) ) {
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
	 * allow a user to have this access to their own object
	 * 
	 * @param Request request
	 * 
	 * @return boolean
	 */
	private boolean userIsOwner(Request request) 
	{
		ReimbursementWrapper reimWrapper = new ReimbursementWrapper();
		ReimbursementInterface reimbursement = (ReimbursementInterface)request.resource;
		UserInterface author = null;
		String logMessage;
		
		reimWrapper.setSubject( reimbursement );
		author = reimWrapper.getAuthor();
		
		if ( author == null ) {
			return false;
		}
		
		/* 
		 * allow a user to have this access to their own object
		 */
		if ( author.getId() == request.user.getId() ) {
			logMessage = String.format("USER: %s is ALLOWED view access to owned REIMBURSEMENT: %d", 
				request.user.getIdentity(),
				reimWrapper.getId()
			);
			
			log.debug( logMessage );
			
			return true;
		} 
		
		return false;
	}
	
}

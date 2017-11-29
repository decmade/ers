package com.revature.ers.acl.policies;

import com.revature.ers.acl.Request;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.wrappers.UserWrapper;

/**
 * policy for User access to ReimbursementInterface objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UserPolicy extends AbstractPolicy 
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
		 * ONLY check for requests for UserInterface objects 
		 *  short-circuit if not
		 */
		if ( UserInterface.class.isInstance( request.resource ) == false ) {
			return false;
		}
		
		
		
		switch( request.verb.toLowerCase() ) {
			case "read" :
			case "update" :
				if ( this.userIsSystemAdministrator(request)) {
					return true;
				}
				
				if ( this.userIsFinanceManager(request) ) {
					return true;
				}
				
				if ( this.userIsRequestingSelf(request)) {
					return true;
				}
				
				break;
				
			case "delete" :
			case "create" :
				if ( this.userIsSystemAdministrator(request)) {
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
	private boolean userIsRequestingSelf(Request request) 
	{
		String logMessage;	
		UserWrapper userWrapper = new UserWrapper();
		UserInterface targetedUser = (UserInterface)request.resource;
					
		userWrapper.setSubject( targetedUser );
		
		/*
		 * allow a user read access to themselves
		 */
		if ( request.user.getId() == userWrapper.getId() ) {
			logMessage = String.format("USER: %s is ALLOWED read access to self", 
				request.user.getIdentity(),
				userWrapper.getId()
			);
			
			log.debug( logMessage );
			
			return true;
		}
		
		return false;
	}
}

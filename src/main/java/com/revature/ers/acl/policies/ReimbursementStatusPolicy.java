package com.revature.ers.acl.policies;

import com.revature.ers.acl.Request;
import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;


/**
 * policy for User access to ReimbursementInterface objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementStatusPolicy extends AbstractPolicy 
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
		 * ONLY check for requests for ReimbursementStatusInterface objects 
		 */
		if ( ReimbursementStatusInterface.class.isInstance( request.resource ) == false ) {
			return false;
		}
			
		switch( request.verb.toLowerCase() ) {
			case "create" :
			case "update" :
			case "delete" :
				if ( this.userIsSystemAdministrator(request) ) {
					return true;
				}
				
				break;
			case "read" :
				return true;

		}
		
		/*
		 * return false if not true somehow above
		 */
		return false;
	}
	
}

package com.revature.ers.acl.policies;

import com.revature.ers.acl.Request;
import com.revature.ers.acl.markers.FinanceManagerMarker;
import com.revature.ers.beans.wrappers.UserWrapper;

/**
 * policy for User access to Account objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class FinanceManagerPolicy extends AbstractPolicy 
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
		String logMessage;
		Integer roleId;
		FinanceManagerMarker marker = FinanceManagerMarker.getInstance();
		UserWrapper userWrapper = new UserWrapper();
		
		userWrapper.setSubject( request.user );
		roleId = userWrapper.getRoleId();
		
		if ( FinanceManagerMarker.class.isInstance( request.resource ) ) {			
			/* 
			 * users who have a role that gives them Finance Manager
			 * privileges are allowed access based on this request
			 */
			if ( marker.getRoleIds().contains( roleId ) ) {
				logMessage = String.format(
					"USER: %s is ALLOWED Finance Manager privileges", 
					userWrapper.getDisplayName()
				);
				
				log.debug( logMessage );
				
				return true;
			}
			
		} 
		
		return false;
	}


	
	
}

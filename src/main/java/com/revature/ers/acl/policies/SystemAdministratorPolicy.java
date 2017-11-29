package com.revature.ers.acl.policies;

import com.revature.ers.acl.Request;
import com.revature.ers.acl.markers.SystemAdministratorMarker;
import com.revature.ers.beans.wrappers.UserWrapper;

/**
 * policy for User access to Account objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class SystemAdministratorPolicy extends AbstractPolicy 
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
		SystemAdministratorMarker marker = SystemAdministratorMarker.getInstance();

		UserWrapper userWrapper = new UserWrapper();
		userWrapper.setSubject( request.user );
		roleId = userWrapper.getRoleId();
		
		if ( SystemAdministratorMarker.class.isInstance( request.resource ) ) {
				
			/* 
			 * users who have a role that gives them Finance Manager
			 * privileges are allowed access based on this request
			 */
			if ( marker.getRoleIds().contains( roleId ) ) {
				logMessage = String.format(
					"USER: %s is ALLOWED System Administrator privileges", 
					userWrapper.getDisplayName()
				);
				
				log.debug( logMessage );
				
				return true;
			}
			
		} 
		
		return false;
	}


	
	
}

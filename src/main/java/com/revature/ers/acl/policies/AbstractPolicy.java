package com.revature.ers.acl.policies;

import com.revature.ers.acl.Acl;
import com.revature.ers.acl.Request;
import com.revature.ers.acl.markers.FinanceManagerMarker;
import com.revature.ers.acl.markers.SystemAdministratorMarker;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.logger.AclLogger;
import com.revature.ers.logger.LoggerInterface;

/**
 * template for all ACL policy objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
abstract public class AbstractPolicy implements PolicyInterface 
{
	protected static LoggerInterface log = AclLogger.getInstance();		// get handle on system log instance
	
	/**
	 * default allow policy of extending classes
	 * 
	 * @param Request request
	 * 
	 * @return boolean
	 */
	@Override
	public boolean allow(Request request)
	{
		log.trace("default ACL allow policy used");
		return false;
	}
	
	/**
	 * default deny policy of extending classes
	 * 
	 * @param Request request
	 * 
	 * @return boolean
	 */
	@Override
	public boolean deny(Request request)
	{
		log.trace( String.format("default ACL DENY policy used for %s access TO %s FOR %s",
			request.verb,
			request.resource,
			request.user.getIdentity()
		));
		return false;
	}
	
	/**
	 * returns true if the user passed has
	 * Finance Manager access
	 * 
	 * @param UserInterface user
	 * 
	 * @return boolean
	 */
	protected boolean isFinanceManager(UserInterface user)
	{
		return Acl.authorize(user, "any", FinanceManagerMarker.getInstance() );
	}
	
	/** 
	 * allow a FinanceManager access
	 * 
	 * @param Request request
	 * 
	 * @return boolean
	 */
	protected boolean userIsFinanceManager(Request request)
	{
		String logMessage;
		
		/*
		 * allow Finance Managers access
		 */
		if( this.isFinanceManager( request.user ) ) {
			logMessage = String.format("USER: %s is ALLOWED access due to having Finance Manager privileges", 
				request.user.getIdentity()
			);
			
			log.debug( logMessage );
			
			return true;
		} else {
			return false;
		}
	
	}
	
	/**
	 * returns true if the user passed has
	 * Finance Manager access
	 * 
	 * @param UserInterface user
	 * 
	 * @return boolean
	 */
	protected boolean isSystemAdministrator(UserInterface user)
	{
		return Acl.authorize(user, "any", SystemAdministratorMarker.getInstance() );
	}
	
	/** 
	 * allow a SystemAdministrator access
	 * 
	 * @param Request request
	 * 
	 * @return boolean
	 */
	protected boolean userIsSystemAdministrator(Request request)
	{
		String logMessage;
		
		/*
		 * allow System Administrators access
		 */
		if( this.isSystemAdministrator( request.user ) ) {
			logMessage = String.format("USER: %s is ALLOWED access due to having System Administrator privileges", 
				request.user.getIdentity()
			);
			
			log.debug( logMessage );
			
			return true;
		} else {
			return false;
		}
	
	}
}

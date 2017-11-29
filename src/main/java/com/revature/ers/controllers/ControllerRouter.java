package com.revature.ers.controllers;

import java.util.HashMap;
import java.util.Map;

import com.revature.ers.logger.DispatchLogger;
import com.revature.ers.logger.LoggerInterface;

/**
 * << singleton >>
 * 
 * a utility class that encapsulates the
 * logic for what controller to select for a
 * given request
 * 
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ControllerRouter 
{
	/*
	 * instantiate the singleton instance
	 */
	private static ControllerRouter instance = new ControllerRouter();
	private static LoggerInterface log = DispatchLogger.getInstance();
	
	/*
	 * all registered controllers indexed by
	 * their regex patterns
	 */
	private Map<String, ControllerInterface> controllers;
	
	/**
	 * returns the singleton instance
	 * 
	 * @return
	 */
	public static ControllerRouter getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ControllerRouter()
	{
		this.controllers = this.initializeControllers();
	}
	
	/**
	 * returns the first Controller that is 
	 * registered with a pattern that matches
	 * the URI
	 * passed
	 * 
	 * @param String uri
	 * 
	 * @return ControllerInterface
	 */
	public ControllerInterface route(String uri) 
	{
		ControllerInterface matchedController = null;
		
		for(String pattern: this.controllers.keySet() ) {
			
			if ( uri.matches(pattern) ) {
				matchedController = this.controllers.get(pattern);
				
				log.debug(String.format("request for URI:[%s] matched to Controller:[%s]", 
					uri, 
					matchedController.getClass().getCanonicalName() 
				));
				
				break;
			}
			
		}
		
		return matchedController;
	}
	
	/**
	 * registers controllers to route matches
	 * 
	 */
	private Map<String,ControllerInterface> initializeControllers()
	{
		Map<String, ControllerInterface> controllers = new HashMap<>();
		
		/*
		 * add a regex that should be tested for the route and the
		 * route itself
		 */
		controllers.put("/user[s]{0,1}[/]{0,1}[0-9]*", UserController.getInstance() );
		controllers.put("/reimbursement[s]{0,1}[/]{0,1}[0-9]*", ReimbursementController.getInstance() );
		controllers.put("/maintenance[/]*", ApplicationController.getInstance() );
		controllers.put("/reimbursementstatus(es){0,1}[/]{0,1}[0-9]*", ReimbursementStatusController.getInstance() );
		controllers.put("/reimbursementtype[s]{0,1}[/]{0,1}[0-9]*", ReimbursementTypeController.getInstance() );
		controllers.put("/userrole[s]{0,1}[/]{0,1}[0-9]*", UserRoleController.getInstance() );
		controllers.put("/login.*", LoginController.getInstance() );
		controllers.put("/acl.*", AuthorizationController.getInstance() );
		controllers.put( String.format("/%s[0-9]+", ReceiptFileController.URL_ROOT), ReceiptFileController.getInstance() );
		controllers.put("/receipt[s]{0,1}[/]{0,1}[0-9]*", ReceiptController.getInstance() );
		
		
		
		return controllers;
	}
}

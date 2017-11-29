package com.revature.ers.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.acl.Acl;
import com.revature.ers.acl.markers.FinanceManagerMarker;
import com.revature.ers.acl.markers.SystemAdministratorMarker;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.wrappers.UserWrapper;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.exceptions.AbstractCustomHttpException;
import com.revature.ers.exceptions.InvalidHttpRequestException;
import com.revature.ers.exceptions.UnauthorizedHttpRequestException;
import com.revature.ers.io.Json;


/**
 * << singleton >>
 * 
 * represents the controller for User objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class AuthorizationController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static AuthorizationController instance = new AuthorizationController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static AuthorizationController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private AuthorizationController()
	{
		super();
	}

	@Override
	public void get(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{
		// stub		
	}

	@Override
	public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{	
		/*
		 * validate that the request is well formed
		 */
		this.validateRequest(request);
		
		Map<String,String> params = this.decode( this.getRequestBody(request) );
		Map<String, Boolean> result = new HashMap<>();
		UserInterface user = Acl.getAuthenticatedUser( request.getSession() );
		UserWrapper userWrapper = new UserWrapper();
		String verb = "";
		String id = "";
		Object resource = null;
		
		
		userWrapper.setSubject( user );
		
		if ( userWrapper.hasSubject() == false ) {
			log.error("an ACL request requires that a user be logged into the solution");
			throw new UnauthorizedHttpRequestException(403);
		}
		
		if ( this.requestIsComplete(params) == false ) {
			log.error("request does not have all the necessary parameters");
			throw new InvalidHttpRequestException(400);
		}
		
		verb = params.get("verb");
		id = params.get("resourceId");
		
		switch( params.get("resourceType").toLowerCase() ) {
			case "reimbursement" :
				resource = BeanManager.getReimbursement( id );
				break;
			case "receipt" :
				resource = BeanManager.getReceipt(id);
				break;
			case "user" :
				resource = BeanManager.getUser(id);
				break;
			case "administrator" :
				resource = SystemAdministratorMarker.getInstance();
				break;
			case "manager" :
				resource = FinanceManagerMarker.getInstance();
				break;
		}
		
		result.put("allowed", Acl.authorize(userWrapper, verb, resource) );
		
	
		this.writeToResponse(response, Json.encode( result ) );
	}

	@Override
	public void put(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{
		// stub		
	}

	@Override
	public void delete(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{	
		// stub
	}
	
	/**
	 * returns false if any of the parameters for the
	 * Acl request are missing
	 * 
	 * @param Map<String, String> params
	 * 
	 * @return boolean
	 */
	private boolean requestIsComplete(Map<String, String> params ) {
		if ( params.containsKey("verb") == false ) {
			return false;
		}
		if ( params.containsKey("resourceId") == false ) {
			return false;
		}
		
		if ( params.containsKey("resourceType") == false ) {
			return false;
		}
		
		return true;
	}

}

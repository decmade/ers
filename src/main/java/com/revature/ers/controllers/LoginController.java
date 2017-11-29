package com.revature.ers.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.revature.ers.acl.Acl;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.wrappers.UserWrapper;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.exceptions.AbstractCustomHttpException;
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
public class LoginController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static LoginController instance = new LoginController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static LoginController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private LoginController()
	{
		super();
	}

	@Override
	public void get(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{
		/*
		 * validate that the request is well formed
		 */
		this.validateRequest(request);
		
		UserWrapper userWrapper = new UserWrapper();
		UserInterface user = null;
		HttpSession session = request.getSession();
		
		user = Acl.getAuthenticatedUser( session ); 
		userWrapper.setSubject( user );
		
		this.writeToResponse( response, Json.encode( userWrapper.getPropertyMap() ) );		
	}

	@Override
	public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{	
		/*
		 * validate that the request is well formed
		 */
		this.validateRequest(request);
		
		Map<String,String> credentials = this.decode( this.getRequestBody(request) );
		UserInterface user = null;
		UserWrapper userWrapper = new UserWrapper();
		HttpSession session = request.getSession();
		String identity = "";
		String credential = "";
		

		if( this.credentialsAreValid(credentials) == false ) {
			throw new UnauthorizedHttpRequestException(400);
		} 
		
		identity = credentials.get("identity");
		credential = credentials.get("credential");
		user = BeanManager.getUserByIdentity( identity );		
		userWrapper.setSubject( user );
		
		if ( userWrapper.hasSubject() == false ) {
			throw new UnauthorizedHttpRequestException(401);
		}
		
		if ( Acl.authenticate(session, identity, credential ) ) {
			user = Acl.getAuthenticatedUser( session );
			userWrapper.setSubject( user );
			this.writeToResponse(response, Json.encode( userWrapper.getPropertyMap() ));
		} else {
			throw new UnauthorizedHttpRequestException(401);
		}
	}

	@Override
	public void put(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{
		/*
		 * validate that the request is well formed
		 */
		this.validateRequest(request);
		
		
		
	}

	@Override
	public void delete(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{	
		UserInterface user = Acl.getAuthenticatedUser( request.getSession() );
		UserWrapper userWrapper = new UserWrapper();
		
		userWrapper.setSubject(user);
		
		if ( userWrapper.hasSubject() ) {
			Acl.clear( request.getSession() );
		}
		
		this.writeToResponse(response, Json.encode( userWrapper.getPropertyMap() ) );
	}
	
	/**
	 * returns true if the credentials properties passed are valid
	 * 
	 * @param Map<String, String> credentials
	 * 
	 * @return boolean
	 */
	private boolean credentialsAreValid(Map<String, String> credentials) {
		if( credentials.containsKey("identity") == false) {
			return false;
		}
		
		if ( credentials.containsKey("credential") == false ) {
			return false;
		}
		
		return true;
	}

}

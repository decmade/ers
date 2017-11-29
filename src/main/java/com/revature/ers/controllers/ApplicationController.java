package com.revature.ers.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.acl.markers.SystemAdministratorMarker;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.interfaces.MigrationInterface;
import com.revature.ers.dbal.migrations.InitializeAppMigration;
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
public class ApplicationController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static ApplicationController instance = new ApplicationController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static ApplicationController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ApplicationController()
	{
		super();
	}

	@Override
	public void get(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws InvalidHttpRequestException
	{
		this.validateRequest(request);
		
		// TODO: application get method
		
	}

	@Override
	public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws InvalidHttpRequestException
	{		
		this.validateRequest(request);
		
		// TODO: application post method		
	}

	@Override
	public void put(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws InvalidHttpRequestException
	{
		this.validateRequest(request);
		
		// TODO: application put method
		
	}

	@Override
	public void delete(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws InvalidHttpRequestException, UnauthorizedHttpRequestException
	{
		if ( BeanManager.getAllUserRoles().size() > 0 ) {
			if ( this.authorizeRequest(request, "reset", SystemAdministratorMarker.getInstance()) == false ) {
				throw new UnauthorizedHttpRequestException(401);
			}
		}
		
		Map<String,String> output = new HashMap<>();
	
		log.debug("resetting application data to factory settings");
		MigrationInterface reset = new InitializeAppMigration();
		reset.run();
		
		output.put("message", "Application restored to factory settings");
		output.put("status", "SUCCESS");
		
		this.writeToResponse(response, Json.encode(output) );
	}
	

}

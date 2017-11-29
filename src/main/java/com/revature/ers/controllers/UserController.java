package com.revature.ers.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.beans.User;
import com.revature.ers.beans.factories.UserFactory;
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
public class UserController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static UserController instance = new UserController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static UserController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private UserController()
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
		
		String userId = this.getRouteParameter( request );
		
		/*
		 * if route has an ID parameter, return one
		 * user
		 * 
		 * otherwise return all users
		 */
		if ( userId.isEmpty() ) {
			this.getAllUsers(request, response);
		} else {
			this.getUser(userId, request, response);
		}
		
	}

	@Override
	public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{	
		/*
		 * validate that the request is well formed
		 */
		this.validateRequest(request);
		
		/*
		 * check to see if authenticated user can create a new user
		 */
		if ( this.authorizeRequest(request, "create", new User() ) == false ) {
			throw new UnauthorizedHttpRequestException(403);
		}
		
		String requestData = this.getRequestBody(request);
		

		Map<String, String> properties = this.decode( requestData );
		UserInterface user = UserFactory.getInstance().create( properties );
		UserWrapper userWrapper = new UserWrapper();
		
		log.info(String.format("inserting user as %s", properties ) );
		userWrapper.setSubject( user );
		
		if ( BeanManager.saveUser(userWrapper) == true ) {
			this.writeToResponse(response, Json.encode( userWrapper.getPropertyMap() ) );	
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
		
		String requestData = this.getRequestBody(request);	
		String routeParameter = this.getRouteParameter(request);
		Map<String, String> properties = this.decode( requestData );
		UserInterface user = UserFactory.getInstance().create( properties );;
		UserWrapper userWrapper = new UserWrapper();
		
		userWrapper.setSubject( user );
		userWrapper.setId( Integer.valueOf( routeParameter ) );
		
		if ( userWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can update
			 * the user indicated
			 */
			if ( this.authorizeRequest(request, "update", userWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
	
			log.debug(String.format("updating user with ID:[%s] as [%s]", userWrapper.getId(), properties )) ;
			
			/*
			 * if the user cannot be updated, (ie. does not exist),
			 * then insert the user in the database by removing
			 * the ID value and saving again
			 * 
			 * the DAO will detect that 0 ID and fork into an
			 * INSERT statement instead of an UPDATE
			 */
			if ( BeanManager.saveUser(userWrapper) == false ) {
				userWrapper.setId(0);
				BeanManager.saveUser(userWrapper);
			}
			
			this.writeToResponse(response, Json.encode( userWrapper.getPropertyMap() ) );
		} else {
			throw new InvalidHttpRequestException(404);
		}
		
	}

	@Override
	public void delete(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{
		/*
		 * validate that the request is well formed
		 */
		this.validateRequest(request);
		
		String userId = this.getRouteParameter(request);
		UserInterface user = BeanManager.getUser( userId );
		UserWrapper userWrapper = new UserWrapper();
		
		userWrapper.setSubject( user );
		
		if ( userWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can delete
			 * the user indicated
			 */
			if ( this.authorizeRequest(request, "delete", userWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			BeanManager.removeUser(userWrapper);
			
			this.writeToResponse(response, Json.encode( userWrapper.getPropertyMap() ) );
		} else {
			throw new InvalidHttpRequestException(404);
		}
	}
	
	/**
	 * retrieves the JSON data of one user
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	private void getUser(String userId, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{
		UserInterface user = BeanManager.getUser(userId);
		UserWrapper userWrapper = new UserWrapper();
		String data;
		
		userWrapper.setSubject( user );
		
		if ( userWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can view
			 * the user indicated
			 */
			if ( this.authorizeRequest(request, "read", userWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			data = Json.encode( userWrapper.getPropertyMap() );
			this.writeToResponse(response, data);
		} else {
			throw new InvalidHttpRequestException(404);
		}

	}
	
	/**
	 * injects the JSON data of all users
	 * into the response
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	private void getAllUsers(HttpServletRequest request, HttpServletResponse response) 
	{
		List<UserInterface> users = BeanManager.getAllUsers();
		UserWrapper userWrapper = new UserWrapper();
		List< Map<String, Object> > mapList = new ArrayList<>();
		String data = "[]";
		
		users.stream()
			.filter( user -> {
				/*
				 * check to see if authenticated user can view
				 * the user indicated
				 */
				return this.authorizeRequest(request, "read", user);
			})
			.forEach((user) -> {
				userWrapper.setSubject( user );
				mapList.add( userWrapper.getPropertyMap() );
			});
		
		data = Json.encode( mapList );
		
		this.writeToResponse(response, data);
	}

}

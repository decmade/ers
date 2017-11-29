package com.revature.ers.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.beans.UserRole;
import com.revature.ers.beans.factories.UserRoleFactory;
import com.revature.ers.beans.interfaces.UserRoleInterface;
import com.revature.ers.beans.wrappers.UserRoleWrapper;
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
public class UserRoleController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static UserRoleController instance = new UserRoleController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static UserRoleController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private UserRoleController()
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
		
		String id = this.getRouteParameter( request );
		
		/*
		 * if route has an ID parameter, return one
		 * role
		 * 
		 * otherwise return all records
		 */
		if ( id.isEmpty() ) {
			this.getAllUserRoles(request, response);
		} else {
			this.getUserRole(id, request, response);
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
		 * check to see if authenticated user can create a
		 * new record
		 */
		if ( this.authorizeRequest(request, "create", new UserRole() ) == false ) {
			throw new UnauthorizedHttpRequestException(403);
		}
		
		String requestData = this.getRequestBody(request);
		

		Map<String, String> properties = this.decode( requestData );
		UserRoleInterface role = UserRoleFactory.getInstance().create( properties );
		UserRoleWrapper roleWrapper = new UserRoleWrapper();
		
		log.info(String.format("inserting user role as %s", properties ) );
		
		roleWrapper.setSubject( role );
		
		if ( BeanManager.saveUserRole(roleWrapper) == true ) {
			this.writeToResponse(response, Json.encode( roleWrapper.getPropertyMap() ) );	
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
		UserRoleInterface role = UserRoleFactory.getInstance().create( properties );;
		UserRoleWrapper roleWrapper = new UserRoleWrapper();
				
		roleWrapper.setSubject( role );
		roleWrapper.setId( Integer.valueOf( routeParameter ) );
		
		if ( roleWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can update the requested record
			 */
			if ( this.authorizeRequest(request, "update", roleWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
	
			log.debug(String.format("updating user role with ID:[%s] as [%s]", roleWrapper.getId(), 
					properties 
			)) ;
			
			/*
			 * if the record cannot be updated, (ie. does not exist),
			 * then insert the record in the database by removing
			 * the ID value and saving again
			 * 
			 * the DAO will detect that 0 ID and fork into an
			 * INSERT statement instead of an UPDATE
			 */
			if ( BeanManager.saveUserRole(roleWrapper) == false ) {
				roleWrapper.setId(0);
				BeanManager.saveUserRole(roleWrapper);
			}
			
			this.writeToResponse(response, Json.encode( roleWrapper.getPropertyMap() ) );
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
		
		String id = this.getRouteParameter(request);
		UserRoleInterface role = BeanManager.getUserRole( id );
		UserRoleWrapper roleWrapper = new UserRoleWrapper();
		
		roleWrapper.setSubject(role);
		
		if ( roleWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can delete the requested record
			 */
			if ( this.authorizeRequest(request, "delete", roleWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			BeanManager.removeUserRole(roleWrapper);
			
			this.writeToResponse(response, Json.encode( roleWrapper.getPropertyMap() ) );
		} else {
			throw new InvalidHttpRequestException(404);
		}
	}
	
	/**
	 * retrieves the JSON data of one record
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * 
	 * @throws AbstractCustomHttpException
	 */
	private void getUserRole(String id, HttpServletRequest request, HttpServletResponse response)
	throws AbstractCustomHttpException
	{
		UserRoleInterface role = BeanManager.getUserRole(id);
		UserRoleWrapper roleWrapper = new UserRoleWrapper();
		String data;
		
		roleWrapper.setSubject( role );
		
		if ( roleWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can view the requested record
			 */
			if ( this.authorizeRequest(request, "read", roleWrapper) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			data = Json.encode( roleWrapper.getPropertyMap() );
			
			this.writeToResponse(response, data);
		} else {
			throw new InvalidHttpRequestException(404);
		}

	}
	
	/**
	 * injects the JSON data of all records
	 * into the response
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	private void getAllUserRoles(HttpServletRequest request, HttpServletResponse response) 
	{
		List<UserRoleInterface> roles = BeanManager.getAllUserRoles();
		UserRoleWrapper roleWrapper = new UserRoleWrapper();
		List< Map<String, Object> > mapList = new ArrayList<>();
		String data = "[]";
		
		roles.stream()
			.filter( role -> {
				/*
				 * check to see if authenticated user can view the requested record
				 */
				return this.authorizeRequest(request, "read", role);
			})
			.forEach((role) -> {
				roleWrapper.setSubject( role );
				mapList.add( roleWrapper.getPropertyMap() );
			});
		
		data = Json.encode( mapList );
		
		this.writeToResponse(response, data);
	}

}

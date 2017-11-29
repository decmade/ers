package com.revature.ers.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.beans.ReimbursementType;
import com.revature.ers.beans.factories.ReimbursementTypeFactory;
import com.revature.ers.beans.interfaces.ReimbursementTypeInterface;
import com.revature.ers.beans.wrappers.ReimbursementTypeWrapper;
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
public class ReimbursementTypeController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static ReimbursementTypeController instance = new ReimbursementTypeController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static ReimbursementTypeController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementTypeController()
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
		
		String typeId = this.getRouteParameter( request );
		
		/*
		 * if route has an ID parameter, return one
		 * record
		 * 
		 * otherwise return all records
		 */
		if ( typeId.isEmpty() ) {
			this.getAllReimbursementTypes(request, response);
		} else {
			this.getReimbursementType(typeId, request, response);
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
		 * new reimbursement type
		 */
		if ( this.authorizeRequest(request, "create", new ReimbursementType() ) == false ) {
			throw new UnauthorizedHttpRequestException(403);
		}
		
		String requestData = this.getRequestBody(request);
		

		Map<String, String> properties = this.decode( requestData );
		ReimbursementTypeInterface type = ReimbursementTypeFactory.getInstance().create( properties );
		ReimbursementTypeWrapper typeWrapper = new ReimbursementTypeWrapper();
		
		log.info(String.format("inserting reimbursement type as %s", properties ) );
		
		typeWrapper.setSubject( type );
		
		if ( BeanManager.saveUserRole(typeWrapper) == true ) {
			this.writeToResponse(response, Json.encode( typeWrapper.getPropertyMap() ) );	
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
		ReimbursementTypeInterface type = ReimbursementTypeFactory.getInstance().create( properties );;
		ReimbursementTypeWrapper typeWrapper = new ReimbursementTypeWrapper();
				
		typeWrapper.setSubject( type );
		typeWrapper.setId( Integer.valueOf( routeParameter ) );
		
		if ( typeWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can update the requested record
			 */
			if ( this.authorizeRequest(request, "update", typeWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
	
			log.debug(String.format("updating reimbursement type with ID:[%s] as [%s]", typeWrapper.getId(), 
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
			if ( BeanManager.saveUserRole(typeWrapper) == false ) {
				typeWrapper.setId(0);
				BeanManager.saveUserRole(typeWrapper);
			}
			
			this.writeToResponse(response, Json.encode( typeWrapper.getPropertyMap() ) );
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
		ReimbursementTypeInterface type = BeanManager.getReimbursementType( id );
		ReimbursementTypeWrapper typeWrapper = new ReimbursementTypeWrapper();
		
		typeWrapper.setSubject(type);
		
		if ( typeWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can delete the requested reimbursement type
			 */
			if ( this.authorizeRequest(request, "delete", typeWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			BeanManager.removeReimbursementType(typeWrapper);
			
			this.writeToResponse(response, Json.encode( typeWrapper.getPropertyMap() ) );
		} else {
			throw new InvalidHttpRequestException(404);
		}
	}
	
	/**
	 * retrieves the JSON data of one ReimbursementType
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * 
	 * @throws AbstractCustomHttpException
	 */
	private void getReimbursementType(String id, HttpServletRequest request, HttpServletResponse response)
	throws AbstractCustomHttpException
	{
		ReimbursementTypeInterface type = BeanManager.getReimbursementType(id);
		ReimbursementTypeWrapper typeWrapper = new ReimbursementTypeWrapper();
		String data;
		
		typeWrapper.setSubject( type );
		
		if ( typeWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can view the requested reimbursement
			 * type
			 */
			if ( this.authorizeRequest(request, "read", typeWrapper) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			data = Json.encode( typeWrapper.getPropertyMap() );
			
			this.writeToResponse(response, data);
		} else {
			throw new InvalidHttpRequestException(404);
		}

	}
	
	/**
	 * injects the JSON data of all reimbursement types
	 * into the response
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	private void getAllReimbursementTypes(HttpServletRequest request, HttpServletResponse response) 
	{
		List<ReimbursementTypeInterface> types = BeanManager.getAllReimbursementTypes();
		ReimbursementTypeWrapper typeWrapper = new ReimbursementTypeWrapper();
		List< Map<String, Object> > mapList = new ArrayList<>();
		String data = "[]";
		
		types.stream()
			.filter( type -> {
				/*
				 * check to see if authenticated user can view the requested reimbursement type
				 */
				return this.authorizeRequest(request, "read", type);
			})
			.forEach((type) -> {
				typeWrapper.setSubject( type );
				mapList.add( typeWrapper.getPropertyMap() );
			});
		
		data = Json.encode( mapList );
		
		this.writeToResponse(response, data);
	}

}

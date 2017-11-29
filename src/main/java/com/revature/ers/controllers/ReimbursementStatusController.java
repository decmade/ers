package com.revature.ers.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.beans.ReimbursementStatus;
import com.revature.ers.beans.factories.ReimbursementStatusFactory;
import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;
import com.revature.ers.beans.wrappers.ReimbursementStatusWrapper;
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
public class ReimbursementStatusController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static ReimbursementStatusController instance = new ReimbursementStatusController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static ReimbursementStatusController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementStatusController()
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
		
		String statusId = this.getRouteParameter( request );
		
		/*
		 * if route has an ID parameter, return one
		 * record
		 * 
		 * otherwise return all records
		 */
		if ( statusId.isEmpty() ) {
			this.getAllReimbursementStatuses(request, response);
		} else {
			this.getReimbursementStatus(statusId, request, response);
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
		 * new reimbursement status
		 */
		if ( this.authorizeRequest(request, "create", new ReimbursementStatus() ) == false ) {
			throw new UnauthorizedHttpRequestException(403);
		}
		
		String requestData = this.getRequestBody(request);
		

		Map<String, String> properties = this.decode( requestData );
		ReimbursementStatusInterface status = ReimbursementStatusFactory.getInstance().create( properties );
		ReimbursementStatusWrapper statusWrapper = new ReimbursementStatusWrapper();
		
		log.info(String.format("inserting reimbursement status as %s", properties ) );
		
		statusWrapper.setSubject( status );
		
		if ( BeanManager.saveReimbursementStatus(statusWrapper) == true ) {
			this.writeToResponse(response, Json.encode( statusWrapper.getPropertyMap() ) );	
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
		ReimbursementStatusInterface status = ReimbursementStatusFactory.getInstance().create( properties );;
		ReimbursementStatusWrapper statusWrapper = new ReimbursementStatusWrapper();
				
		statusWrapper.setSubject( status );
		statusWrapper.setId( Integer.valueOf( routeParameter ) );
		
		if ( statusWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can update the requested reimbursement status
			 */
			if ( this.authorizeRequest(request, "update", statusWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
	
			log.debug(String.format("updating reimbursement status with ID:[%s] as [%s]", statusWrapper.getId(), properties )) ;
			
			/*
			 * if the record cannot be updated, (ie. does not exist),
			 * then insert the record in the database by removing
			 * the ID value and saving again
			 * 
			 * the DAO will detect that 0 ID and fork into an
			 * INSERT statement instead of an UPDATE
			 */
			if ( BeanManager.saveReimbursementStatus(statusWrapper) == false ) {
				statusWrapper.setId(0);
				BeanManager.saveReimbursementStatus(statusWrapper);
			}
			
			this.writeToResponse(response, Json.encode( statusWrapper.getPropertyMap() ) );
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
		
		String statusId = this.getRouteParameter(request);
		ReimbursementStatusInterface status = BeanManager.getReimbursementStatus( statusId );
		ReimbursementStatusWrapper statusWrapper = new ReimbursementStatusWrapper();
		
		statusWrapper.setSubject(status);
		
		if ( statusWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can delete the requested reimbursement status
			 */
			if ( this.authorizeRequest(request, "delete", statusWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			BeanManager.removeReimbursementStatus(statusWrapper);
			
			this.writeToResponse(response, Json.encode( statusWrapper.getPropertyMap() ) );
		} else {
			throw new InvalidHttpRequestException(404);
		}
	}
	
	/**
	 * retrieves the JSON data of one ReimbursementStatus
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * 
	 * @throws AbstractCustomHttpException
	 */
	private void getReimbursementStatus(String statusId, HttpServletRequest request, HttpServletResponse response)
	throws AbstractCustomHttpException
	{
		ReimbursementStatusInterface status = BeanManager.getReimbursementStatus(statusId);
		ReimbursementStatusWrapper statusWrapper = new ReimbursementStatusWrapper();
		String data;
		
		statusWrapper.setSubject( status );
		
		if ( statusWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can view the requested reimbursement
			 * status
			 */
			if ( this.authorizeRequest(request, "read", statusWrapper) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			data = Json.encode( statusWrapper.getPropertyMap() );
			
			this.writeToResponse(response, data);
		} else {
			throw new InvalidHttpRequestException(404);
		}

	}
	
	/**
	 * injects the JSON data of all reimbursement statuses
	 * into the response
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	private void getAllReimbursementStatuses(HttpServletRequest request, HttpServletResponse response) 
	{
		List<ReimbursementStatusInterface> statuses = BeanManager.getAllReimbursementStatuses();
		ReimbursementStatusWrapper statusWrapper = new ReimbursementStatusWrapper();
		List< Map<String, Object> > mapList = new ArrayList<>();
		String data = "[]";
		
		statuses.stream()
			.filter( status -> {
				/*
				 * check to see if authenticated user can view the requested reimbursement status
				 */
				return this.authorizeRequest(request, "read", status);
			})
			.forEach((status) -> {
				statusWrapper.setSubject( status );
				mapList.add( statusWrapper.getPropertyMap() );
			});
		
		data = Json.encode( mapList );
		
		this.writeToResponse(response, data);
	}

}

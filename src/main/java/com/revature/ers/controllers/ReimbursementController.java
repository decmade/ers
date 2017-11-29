package com.revature.ers.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ers.acl.Acl;
import com.revature.ers.beans.Reimbursement;
import com.revature.ers.beans.factories.ReimbursementFactory;
import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.wrappers.ReimbursementWrapper;
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
public class ReimbursementController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static ReimbursementController instance = new ReimbursementController();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static ReimbursementController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementController()
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
		
		String reimbursementId = this.getRouteParameter( request );
		
		/*
		 * if route has an ID parameter, return one
		 * record
		 * 
		 * otherwise return all records
		 */
		if ( reimbursementId.isEmpty() ) {
			this.getAllReimbursements(request, response);
		} else {
			this.getReimbursement(reimbursementId, request, response);
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
		 * new reimbursement
		 */
		if ( this.authorizeRequest(request, "create", new Reimbursement() ) == false ) {
			throw new UnauthorizedHttpRequestException(403);
		}
		
		String requestData = this.getRequestBody(request);
		

		Map<String, String> properties = this.decode( requestData );
		ReimbursementInterface reimbursement = ReimbursementFactory.getInstance().create( properties );
		ReimbursementWrapper reimWrapper = new ReimbursementWrapper();
		
		log.info(String.format("inserting reimbursement as %s", properties ) );
		
		reimWrapper.setSubject( reimbursement );
		reimWrapper.setSubmitted( LocalDateTime.now() );
	
		
		if ( BeanManager.saveReimbursement(reimWrapper) == true ) {
			this.writeToResponse(response, this.prepareJson(reimWrapper, request.getSession() ) );	
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
		ReimbursementInterface reimbursement = ReimbursementFactory.getInstance().create( properties );;
		ReimbursementWrapper reimWrapper = new ReimbursementWrapper();
				
		reimWrapper.setSubject( reimbursement );
		reimWrapper.setId( Integer.valueOf( routeParameter ) );
		
		if ( reimWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can update the requested reimbursement
			 */
			if ( this.authorizeRequest(request, "update", reimWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
	
			log.debug(String.format("updating reimbursement with ID:[%s] as [%s]", reimWrapper.getId(), properties )) ;
			
			/*
			 * if the record cannot be updated, (ie. does not exist),
			 * then insert the record in the database by removing
			 * the ID value and saving again
			 * 
			 * the DAO will detect that 0 ID and fork into an
			 * INSERT statement instead of an UPDATE
			 */
			if ( BeanManager.saveReimbursement(reimWrapper) == false ) {
				reimWrapper.setId(0);
				BeanManager.saveReimbursement(reimWrapper);
			}
						
			this.writeToResponse(response, this.prepareJson(reimWrapper, request.getSession() ) );
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
		
		String reimbursementId = this.getRouteParameter(request);
		ReimbursementInterface reimbursement = BeanManager.getReimbursement( reimbursementId );
		ReimbursementWrapper reimWrapper = new ReimbursementWrapper();
		
		reimWrapper.setSubject(reimbursement);
		
		if ( reimWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can delete the requested reimbursement
			 */
			if ( this.authorizeRequest(request, "delete", reimWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			BeanManager.removeReimbursement(reimWrapper);
			
			this.writeToResponse(response, this.prepareJson( reimWrapper, request.getSession() ));
		} else {
			throw new InvalidHttpRequestException(404);
		}
	}
	
	/**
	 * retrieves the JSON data of one reimbursement
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * 
	 * @throws AbstractCustomHttpException
	 */
	private void getReimbursement(String reimbursementId, HttpServletRequest request, HttpServletResponse response)
	throws AbstractCustomHttpException
	{
		ReimbursementInterface reimbursement = BeanManager.getReimbursement(reimbursementId);
		ReimbursementWrapper reimWrapper = new ReimbursementWrapper();
		
		reimWrapper.setSubject( reimbursement );
		
		if ( reimWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can view the requested reimbursement
			 */
			if ( this.authorizeRequest(request, "read", reimWrapper) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
					
			this.writeToResponse(response, this.prepareJson( reimWrapper, request.getSession() ) );
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
	private void getAllReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		List<ReimbursementInterface> reimbursements = BeanManager.getAllReimbursements();
		ReimbursementWrapper reimWrapper = new ReimbursementWrapper();
		List<String> json = new ArrayList<>();
		
		reimbursements.stream()
			.filter( reimbursement -> {
				/*
				 * check to see if authenticated user can view the requested reimbursement
				 */
				return this.authorizeRequest(request, "read", reimbursement);
			})
			.forEach((reimbursement) -> {
				reimWrapper.setSubject( reimbursement );
				json.add( this.prepareJson( reimWrapper, request.getSession() ) );
			});
		
		this.writeToResponse(response, String.valueOf( json ) );
	}
	
	
	/**
	 * prepare the reimbursement data for the response
	 * 
	 * @param ReimbursementInterface reimbursement
	 * @param HttpSession session
	 * 
	 * @return String (JSON)
	 */
	private String prepareJson( ReimbursementInterface reimbursement, HttpSession session ) {
		ReimbursementWrapper wrapper = new ReimbursementWrapper();
		Map<String, Object> data;
		
		wrapper.setSubject(reimbursement);
		data = wrapper.getPropertyMap();
		data.put("state", this.getViewState(session, wrapper) );
		
		return Json.encode(data);
	}
	
	/**
	 * returns the view state of a reimbursement object based upon
	 * the permissions of the user in the current session
	 * 
	 * @param HttpSession session
	 * @param ReimbursementInterface reimbursement
	 * 
	 * @return String
	 */
	private String getViewState(HttpSession session, ReimbursementInterface reimbursement) {
		UserInterface user = Acl.getAuthenticatedUser(session);
		UserWrapper userWrapper = new UserWrapper();
		
		userWrapper.setSubject(user);
		
		if ( reimbursement.getStatusId() == 1) {
			if ( userWrapper.hasSubject() ) {
				if ( Acl.authorize(userWrapper, "update", reimbursement ) ) {
					if ( userWrapper.getId() == reimbursement.getAuthorId() ) {
						return ReimbursementWrapper.STATE_UPDATE;
					} else {
						return ReimbursementWrapper.STATE_APPROVE;
					}
				}
			}
		}
		
		return ReimbursementWrapper.STATE_VIEW;
	}

}

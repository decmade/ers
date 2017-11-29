package com.revature.ers.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.beans.Receipt;
import com.revature.ers.beans.factories.ReceiptFactory;
import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.beans.wrappers.ReceiptWrapper;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.exceptions.AbstractCustomHttpException;
import com.revature.ers.exceptions.InvalidHttpRequestException;
import com.revature.ers.exceptions.UnauthorizedHttpRequestException;
import com.revature.ers.io.ReceiptFileUpload;
import com.revature.ers.io.Json;


/**
 * << singleton >>
 * 
 * represents the controller for Receipt objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReceiptController extends AbstractController
{
	/*
	 * initializes a singleton instance
	 */
	private static ReceiptController instance = new ReceiptController();
	
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static ReceiptController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReceiptController()
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
			this.getAllReceipts(request, response);
		} else {
			this.getReceipt(id, request, response);
		}
		
	}

	@Override
	public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{	
		
		/*
		 * check to see if authenticated user can create a
		 * new record
		 */
		if ( this.authorizeRequest(request, "create", new Receipt() ) == false ) {
			throw new UnauthorizedHttpRequestException(403);
		}
		
	
		ReceiptInterface receipt = new Receipt();
		ReceiptWrapper receiptWrapper = new ReceiptWrapper();
		String filePath = "";
		
		log.info("attempting to process an uploaded receipt");
		
		receiptWrapper.setSubject( receipt );
		receiptWrapper.setCreated( LocalDateTime.now() );
		
		if ( BeanManager.saveReceipt(receiptWrapper) == true ) {

			filePath = ReceiptFileUpload.store(request, String.valueOf( receiptWrapper.getId() ) );
			
			if ( filePath.isEmpty() ) {
				log.error("receipt upload failed");
				throw new InvalidHttpRequestException(400);
			}
			
			receiptWrapper.setFileName( filePath );
			BeanManager.saveReceipt(receiptWrapper);
						
			this.writeToResponse(response, Json.encode( receiptWrapper.getPropertyMap() ) );	
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
		ReceiptInterface receipt = ReceiptFactory.getInstance().create( properties );;
		ReceiptWrapper receiptWrapper = new ReceiptWrapper();
				
		receiptWrapper.setSubject( receipt );
		receiptWrapper.setId( Integer.valueOf( routeParameter ) );
		
		if ( receiptWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can update the requested record
			 */
			if ( this.authorizeRequest(request, "update", receiptWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
	
			log.debug(String.format("updating receipt with ID:[%s] as [%s]", receiptWrapper.getId(), 
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
			if ( BeanManager.saveReceipt(receiptWrapper) == false ) {
				receiptWrapper.setId(0);
				BeanManager.saveReceipt(receiptWrapper);
			}
			
			this.writeToResponse(response, Json.encode( receiptWrapper.getPropertyMap() ) );
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
		ReceiptInterface receipt = BeanManager.getReceipt( id );
		ReceiptWrapper receiptWrapper = new ReceiptWrapper();
		
		receiptWrapper.setSubject(receipt);
		
		if ( receiptWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can delete the requested record
			 */
			if ( this.authorizeRequest(request, "delete", receiptWrapper ) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			BeanManager.removeReceipt(receiptWrapper);
			
			this.writeToResponse(response, Json.encode( receiptWrapper.getPropertyMap() ) );
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
	private void getReceipt(String id, HttpServletRequest request, HttpServletResponse response)
	throws AbstractCustomHttpException
	{
		ReceiptInterface receipt = BeanManager.getReceipt(id);
		ReceiptWrapper receiptWrapper = new ReceiptWrapper();
		String data;
		
		receiptWrapper.setSubject( receipt );
		
		if ( receiptWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can view the requested record
			 */
			if ( this.authorizeRequest(request, "read", receiptWrapper) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			data = Json.encode( receiptWrapper.getPropertyMap() );
			
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
	private void getAllReceipts(HttpServletRequest request, HttpServletResponse response) 
	{
		List<ReceiptInterface> receipts = BeanManager.getAllReceipts();
		ReceiptWrapper receiptWrapper = new ReceiptWrapper();
		List< Map<String, Object> > mapList = new ArrayList<>();
		String data = "[]";
		
		receipts.stream()
			.filter( receipt -> {
				/*
				 * check to see if authenticated user can view the requested record
				 */
				return this.authorizeRequest(request, "read", receipt);
			})
			.forEach((receipt) -> {
				receiptWrapper.setSubject( receipt );
				mapList.add( receiptWrapper.getPropertyMap() );
			});
		
		data = Json.encode( mapList );
		
		this.writeToResponse(response, data);
	}

}

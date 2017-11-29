package com.revature.ers.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.beans.wrappers.ReceiptWrapper;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.DateTimeConverter;
import com.revature.ers.exceptions.AbstractCustomHttpException;
import com.revature.ers.exceptions.InvalidHttpRequestException;
import com.revature.ers.exceptions.UnauthorizedHttpRequestException;
import com.revature.ers.http.HttpServletResponseUtil;
import com.revature.ers.io.ReceiptFileUpload;


/**
 * << singleton >>
 * 
 * represents the controller for Receipt objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReceiptFileController extends AbstractController
{
	/*
	 * the relative path from the route of the servlet that this controller
	 * will serve files from
	 * 
	 * used in the ReceiptWrapper to generate the URL property for JSON consumption
	 * 
	 * MUST always end with a '/' to denote where the route ends and the ID parameter
	 * begins
	 * 
	 * SHOULD not begin with an '/' as the path is relative to the root path of the servlet
	 */
	public static final String URL_ROOT = "receipt/file/"; 
	
	/*
	 * initializes a singleton instance
	 */
	private static ReceiptFileController instance = new ReceiptFileController();
	
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UserController
	 */
	public static ReceiptFileController getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReceiptFileController()
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
		ReceiptInterface receipt = BeanManager.getReceipt(id);
		ReceiptWrapper receiptWrapper = new ReceiptWrapper();
		String contentDisposition = "";
		
		receiptWrapper.setSubject( receipt );
		
		if ( receiptWrapper.hasSubject() ) {
			/*
			 * check to see if authenticated user can view the requested record
			 */
			if ( this.authorizeRequest(request, "read", receiptWrapper) == false ) {
				throw new UnauthorizedHttpRequestException(403);
			}
			
			contentDisposition = String.format("inline;filename=\"Reimbursement Receipt Submitted %s.%s\"", 
					DateTimeConverter.toFileNameString( receiptWrapper.getCreated() ),
					receiptWrapper.getFileExtension()
			);
		
			response.setHeader("Content-Disposition", contentDisposition );
			HttpServletResponseUtil.sendFile(response, ReceiptFileUpload.retrieve(receiptWrapper) );
			
		} else {
			throw new InvalidHttpRequestException(404);
		}
		
	}

	@Override
	public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
	throws AbstractCustomHttpException
	{		
		// stub
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

}

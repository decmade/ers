package com.revature.ers.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.acl.Acl;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.exceptions.AbstractCustomHttpException;
import com.revature.ers.exceptions.InvalidHttpRequestException;
import com.revature.ers.http.HttpServletRequestUtil;
import com.revature.ers.http.HttpServletResponseUtil;
import com.revature.ers.io.Json;
import com.revature.ers.logger.DispatchLogger;
import com.revature.ers.logger.LoggerInterface;
import com.revature.ers.validators.HttpServletRequestValidator;
import com.revature.ers.validators.HttpServletRequestValidatorFactory;

abstract public class AbstractController implements ControllerInterface
{
	protected static LoggerInterface log = DispatchLogger.getInstance();

	
	@Override
	abstract public void get(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;

	@Override
	abstract public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;
	
	@Override
	abstract public void put(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;

	@Override
	abstract public void delete(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;
	
	/**
	 * returns the URI with the prefix/context
	 * removed
	 * 
	 * @param String uri
	 * 
	 * @return String
	 */
	protected String getTargetOfUri(String uri)
	{
		String[] parts = uri.split("/");
		
		return uri.substring( parts[0].length() + 1);
	}
	
	/**
	 * appends data to a response object
	 * 
	 * @param HttpServletResponse response
	 * @param String message
	 */
	protected void writeToResponse(HttpServletResponse response, String data ) 
	{
		HttpServletResponseUtil.writeTo(response, data);
	}
	

	/**
	 * returns the data in the body of a request
	 * 
	 * @param HttpServletRequest request
	 * 
	 * @return String
	 */
	protected String getRequestBody(HttpServletRequest request) 
	{
		return HttpServletRequestUtil.getRequestBody(request);
	}
	
	/**
	 * returns the parameter on the end of the route URI
	 * 
	 * @param HttpServletRequest request
	 * 
	 * @return String
	 */
	protected String getRouteParameter(HttpServletRequest request)
	{
		return HttpServletRequestUtil.getRouteParameter(request);
	}
	
	/**
	 * assembles an HttpServletRequestValidator and
	 * uses it to validate the request
	 * 
	 * @param HttpServletRequest request
	 * 
	 * @return boolean
	 */
	protected void validateRequest(HttpServletRequest request)
	{
		String method = request.getMethod();
		HttpServletRequestValidator validator = HttpServletRequestValidatorFactory.assemble(method);
		
		if ( validator.validate(request) == false ) {
			throw new InvalidHttpRequestException( 405 );
		}
	}
	
	/**
	 * returns true if the current authenticated user
	 * has the requested access to the resource passed
	 * 
	 * @param HttpServletRequest request
	 * @param String verb
	 * @param Object resource
	 * 
	 * @return boolean
	 */
	protected boolean authorizeRequest(HttpServletRequest request, String verb, Object resource)
	{
		UserInterface authenticatedUser = null;

		// TODO: remove this hack when PostMan testing is done
//		request.getSession().setAttribute(Authenticator.USER_SESSION_KEY, BeanManager.getUserByIdentity("kruppab") );
		
		authenticatedUser = Acl.getAuthenticatedUser(request.getSession() );
		
		if ( authenticatedUser == null ) {
			return false;
		
		}
		
		return Acl.authorize(authenticatedUser, verb, resource);
	}
	
	/**
	 * attempts to decode a json string into a Map<String, String>
	 * 
	 * @param String json
	 * 
	 * @return Map<String, String>
	 * 
	 * @throws AbstractCustomHttpException
	 */
	protected Map<String, String> decode(String json)
	throws AbstractCustomHttpException
	{
		Map<String, String> data =  new HashMap<>();
		
		try {
			data = Json.decode( json );
		} catch(IOException e) {
			log.error( e.getMessage() );
			throw new InvalidHttpRequestException(400);
		}
		
		return data;
	}
	
}

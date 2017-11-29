package com.revature.ers.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.revature.ers.acl.Acl;
import com.revature.ers.beans.User;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.wrappers.UserWrapper;
import com.revature.ers.http.HttpServletRequestUtil;

public class UnauthorizedHttpRequestException extends AbstractCustomHttpException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2647774923845886771L;

	public UnauthorizedHttpRequestException(int statusCode) 
	{
		super(statusCode);		
	}

	/**
	 * returns a message customized for the request passed
	 */
	@Override
	public String getRequestMessage(HttpServletRequest request) {
		UserInterface user = Acl.getAuthenticatedUser( request.getSession() );
		UserWrapper userWrapper = new UserWrapper();
		
		userWrapper.setSubject( user );
		
		if ( userWrapper.hasSubject() == false ) {
			user = new User();
			userWrapper.setSubject( user );
			userWrapper.setIdentity("anonymous");			
		}
		
		String message = String.format("User:[%s] does not have permission for [%s] request to [%s]",
				userWrapper.getIdentity(),
				request.getMethod(),
				HttpServletRequestUtil.extractRelativeUri(request)
		);
		
		return message;
	}

}

package com.revature.ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.revature.ers.controllers.ControllerInterface;
import com.revature.ers.controllers.ControllerRouter;
import com.revature.ers.exceptions.AbstractCustomHttpException;
import com.revature.ers.http.HttpServletResponseUtil;
import com.revature.ers.logger.DispatchLogger;
import com.revature.ers.logger.LoggerInterface;

@MultipartConfig
public class DispatcherServlet extends DefaultServlet 
{
	private static final long serialVersionUID = 100888L;
	private static ControllerRouter router = ControllerRouter.getInstance();
	private static LoggerInterface log = DispatchLogger.getInstance();

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        
		super.service(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		String uri = this.getRelativePath(request);
		ControllerInterface controller = this.getController(uri);
		
		/*
		 * if no controller matches the URI then
		 * default to the default behavior,
		 * probably pull a static page
		 */
		if ( controller == null ) {
			log.info( String.format("no route match found for URI:[%s]", uri ) );
			super.doDelete(request, response);
		} else {
			try {
				controller.delete(uri, request, response);
			} catch(Exception e) {
				this.handleException(request, response, e);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException 
	{
		String uri = this.getRelativePath(request);
		ControllerInterface controller = this.getController(uri);
		
		/*
		 * if no controller matches the URI then
		 * default to the default behavior,
		 * probably pull a static page
		 */
		if ( controller == null ) {
			log.info( String.format("no route match found for URI:[%s]", uri ) );
		
			super.doGet(request, response);
			
			log.debug( String.format("returning status code: %d for request to [%s]", response.getStatus(), uri) );			
		} else {
			try {
				controller.get(uri, request, response);
			} catch(Exception e) {
				this.handleException(request, response, e);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException 
	{
		String uri = this.getRelativePath(request);
		ControllerInterface controller = this.getController(uri);
		
		/*
		 * if no controller matches the URI then
		 * default to the default behavior,
		 * probably pull a static page
		 */
		if ( controller == null ) {
			log.info( String.format("no route match found for URI:[%s]", uri ) );
			super.doPost(request, response);
		} else {
			try {
				controller.post(uri, request, response);
			} catch(Exception e) {
				this.handleException(request, response, e);
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		String uri = this.getRelativePath(request);
		ControllerInterface controller = this.getController(uri);
		
		/*
		 * if no controller matches the URI then
		 * default to the default behavior,
		 * probably pull a static page
		 */
		if ( controller == null ) {
			log.info( String.format("no route match found for URI:[%s]", uri ) );
			super.doPut(request, response);
		} else {
			try {
				controller.put(uri, request, response);
			} catch(Exception e) {
				this.handleException(request, response, e);
			}
		}
	}
	
	/**
	 * handles all exceptions thrown by the controller functions
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Exception e
	 */
	private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
	{	
		if ( AbstractCustomHttpException.class.isInstance(e) ) {
			HttpServletResponseUtil.handleCustomException(request, response, (AbstractCustomHttpException)e);
		}
	}
	
	
	/**
	 * returns the Controller that matches the
	 * URI pattern passed
	 * 
	 * @param String uri
	 * 
	 * @return ControllerInterface
	 */
	private ControllerInterface getController(String uri)
	{
		log.info( String.format("routing a request for URI:[%s]", uri) );
		return router.route(uri);
	}
}

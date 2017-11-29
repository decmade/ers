package com.revature.ers.validators.strategies;

import javax.servlet.http.HttpServletRequest;

import com.revature.ers.http.HttpServletRequestUtil;

public class HttpRequestHasValidIDOrNoIDParameterStrategy implements ValidatorStrategyInterface
{
	@Override
	public boolean test(Object subject) 
	{
		HttpServletRequest request = (HttpServletRequest)subject;
		String routeParam = HttpServletRequestUtil.getRouteParameter(request);
		
		if ( routeParam.isEmpty() ) {
			return true;
		}
		
		if ( routeParam.matches("[0-9]+") ) {
			return true;
		}
		
		return false;
	}

}

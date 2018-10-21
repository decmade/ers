package com.revature.ers.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

abstract public class AbstractController
{
	protected Logger logger;
	
	@Autowired
	public void setLogger(@Qualifier("logger-controller") Logger logger) {
		this.logger = logger;
	}
	
	protected Logger getLogger() {
		return this.logger;
	}
	
}

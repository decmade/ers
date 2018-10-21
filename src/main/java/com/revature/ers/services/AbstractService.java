package com.revature.ers.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.revature.ers.services.interfaces.ServiceInterface;

abstract public class AbstractService implements ServiceInterface {
	
	protected Logger logger;
	
	@Autowired
	public void setLogger(@Qualifier("logger-service") Logger logger) {
		this.logger = logger;
	}
	
	protected Logger getLogger() {
		return this.logger;
	}
}

package com.revature.ers.controllers;

abstract public class AbstractEntityController<T> extends AbstractController {
	protected T service;
	
	abstract public void setService(T service);
}

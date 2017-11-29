package com.revature.ers.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.exceptions.AbstractCustomHttpException;

public interface ControllerInterface 
{
	public void get(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;
	public void post(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;
	public void put(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;
	public void delete(String uri, HttpServletRequest request, HttpServletResponse response) 
			throws AbstractCustomHttpException;
}

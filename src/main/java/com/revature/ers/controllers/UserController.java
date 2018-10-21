package com.revature.ers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ers.entities.User;
import com.revature.ers.services.interfaces.UserServiceInterface;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractEntityController<UserServiceInterface> {

	@Override
	@Autowired
	public void setService(UserServiceInterface service) {
		this.service = service;		
	}
	
	@GetMapping("/all")
	public List<User> getAll() {
		this.getLogger().debug("retrieving all Users");
		
		return this.service.getAll();
	}

}

package com.revature.ers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ers.entities.User;
import com.revature.ers.repositories.UserRepositoryInterface;
import com.revature.ers.services.interfaces.UserServiceInterface;

@Service
public class UserService extends AbstractEntityService<UserRepositoryInterface, User> implements UserServiceInterface {

	@Autowired
	@Override
	public void setRepository(UserRepositoryInterface repository) {
		this.repository = repository;		
	}
	
	@Override
	public List<User> getAll() {
		this.getLogger().debug("retrieving ALL users from repository");
		
		return this.repository.findAll();
	}

	@Override
	public User getById(long id) {
		String message = String.format("retrieving User with ID[%d] from the repository", id);
		
		this.getLogger().debug(message);
		
		return this.repository.getOne(id);
	}

}

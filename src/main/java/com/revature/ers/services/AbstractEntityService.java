package com.revature.ers.services;

import com.revature.ers.services.interfaces.EntityServiceInterface;

abstract public class AbstractEntityService<T, K> extends AbstractService implements EntityServiceInterface<T, K> {
	
	protected T repository;

	@Override
	abstract public void setRepository(T repository);
	
}

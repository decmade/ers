package com.revature.ers.services.interfaces;

import java.util.List;

public interface EntityServiceInterface<T, K> {
	
	void setRepository(T repository);
	
	List<K> getAll();
	
	K getById(long id);
}

package com.revature.ers.dbal.interfaces;

import java.util.List;

/**
 * represents the interface for all table adapters
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
public interface TableAdapterInterface<T> 
{
	
	/**
	 * returns one instance of an object gien
	 * a String that holds the key for the object
	 * 
	 * @param String id
	 * 
	 * @return T
	 */
	public T get(String id);
	
	/**
	 * returns a collection of all stored objects
	 * 
	 * @return List<T>
	 */
	public List<T> getAll();
	
	/**
	 * saves an instance of an object to the database
	 * 
	 * @param T object
	 * 
	 * @return boolean
	 */
	public boolean save(T object);
	
	/**
	 * removes an instance of an object in the database
	 * 
	 * @param T object
	 * 
	 * @return boolean
	 */
	public boolean remove(T object);
	
}

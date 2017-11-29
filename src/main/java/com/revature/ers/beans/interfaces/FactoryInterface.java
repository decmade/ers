package com.revature.ers.beans.interfaces;

import java.sql.ResultSet;
import java.util.Map;

/**
 * represents the interface for all factory
 * objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
public interface FactoryInterface<T> 
{
	/**
	 * create a hydrated instance of an object
	 * using a Map of properties to specify
	 * its members
	 * 
	 * @param Map<String, String> data
	 * 
	 * @return T
	 */
	public T create(Map<String, String> data);
	
	/**
	 * create a hydrated instance of an object
	 * using a ResultSet of properties to specify
	 * its members
	 * 
	 * @param ResultSet data
	 * 
	 * @return T
	 */
	public T create(ResultSet data);
}

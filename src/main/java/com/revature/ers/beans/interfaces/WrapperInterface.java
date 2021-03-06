package com.revature.ers.beans.interfaces;

import java.util.Map;

/**
 * << decorator >>
 * 
 * interface for bean object wrapper classes
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
public interface WrapperInterface<T>
{
	/**
	 * set the object to decorate 
	 * 
	 * @param T subject
	 * 
	 */
	public void setSubject(T subject);
	
	/**
	 * retrieve the decorated object
	 * 
	 * @return T
	 */
	public T getSubject();
	
	/**
	 * return true of the wrapper has a subject
	 * set, fasle if now
	 * 
	 * @return boolean
	 */
	public boolean hasSubject();
	
	/**
	 * returns as a Map<String, Object> that contains
	 * the key->value pairs of the object
	 * 
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getPropertyMap();
}

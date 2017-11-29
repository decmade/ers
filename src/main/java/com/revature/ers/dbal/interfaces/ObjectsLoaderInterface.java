package com.revature.ers.dbal.interfaces;

import java.util.List;

/**
 * interface of objects that generate
 * collections of other objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
public interface ObjectsLoaderInterface<T>
{
	/**
	 * returns a collection of objects 
	 * 
	 * @return List<T>
	 */
	public List<T> load();
}

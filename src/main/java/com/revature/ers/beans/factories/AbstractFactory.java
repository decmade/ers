package com.revature.ers.beans.factories;

import java.sql.ResultSet;
import java.util.Map;

import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.dbal.ResultSetUtil;
import com.revature.ers.logger.BeansLogger;
import com.revature.ers.logger.LoggerInterface;


/**
 * a template for FactoryInterface classes
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
abstract public class AbstractFactory<T> implements FactoryInterface<T> 
{
	/**
	 * static reference to the LoggerInterface for factories
	 */
	protected static LoggerInterface log = BeansLogger.getInstance();
	
	@Override
	abstract public T create(Map<String, String> data);
	
	@Override
	public T create(ResultSet results) 
	{
		Map<String, String> data = ResultSetUtil.toMap( results );
		return this.create( data );
	}
}

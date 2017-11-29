package com.revature.ers.dbal.adapters;

import java.sql.Connection;
import java.util.List;

import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.dbal.connection.DbConnection;
import com.revature.ers.dbal.interfaces.TableAdapterInterface;
import com.revature.ers.logger.DbalLogger;
import com.revature.ers.logger.LoggerInterface;


/**
 * base template for all TableAdapterInterface classes
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
abstract public class AbstractTableAdapter<T> implements TableAdapterInterface<T>
{
	/**
	 * static reference to the logger
	 */
	protected static LoggerInterface log = DbalLogger.getInstance();
	protected static Connection connection = DbConnection.getInstance().connect(); // persistent database connection
	
	/**
	 * factory used to instantiate retrieved objects
	 */
	protected FactoryInterface<T> factory = null;
	
	
	@Override
	abstract public T get(String id);
	
	@Override
	abstract public List<T> getAll();
	
	@Override
	abstract public boolean save(T object);
	
	@Override
	abstract public boolean remove(T object);
	
	/**
	 * retrieves the factory that instantiates
	 * hydrated objects of the type that this adapter
	 * serves
	 * 
	 * @return
	 */
	abstract protected FactoryInterface<T> getFactory();
	
}

package com.revature.ers.dbal.migrations.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.dbal.interfaces.ObjectsLoaderInterface;
import com.revature.ers.logger.DbalLogger;
import com.revature.ers.logger.LoggerInterface;

/**
 * template for all ObjectLoaderInterface objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
abstract public class AbstractObjectsLoader<T> implements ObjectsLoaderInterface<T>
{
	protected static LoggerInterface log = DbalLogger.getInstance();
	
	protected FactoryInterface<T> factory;
	
	@Override
	public List<T> load()
	{
		String[] columns = this.getColumns();
		String[][] rows = this.getDataArray();
		Map<String, String> data = new HashMap<>();
		List<T> objects = new ArrayList<>();
		String columnName = "";
		String columnValue = "";
		
		for(String[] row: rows) {
			data.clear();
			
			for(int i = 0; i < columns.length; i++ ) {
				columnName = columns[i];
				columnValue = row[i];
				data.put( columnName, columnValue );
			}
			
			objects.add( this.getFactory().create(data) );
		}
		
		return objects;
	}
	
	abstract protected FactoryInterface<T> getFactory();
	abstract protected String[] getColumns();
	abstract protected String[][] getDataArray();
}

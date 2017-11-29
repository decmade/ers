package com.revature.ers.dbal;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.ers.logger.DbalLogger;
import com.revature.ers.logger.LoggerInterface;

/**
 * a utility class that transforms ResutlSet
 * objects into other data structures
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ResultSetUtil 
{
	private static LoggerInterface log = DbalLogger.getInstance();

	/**
	 * transform a result set to a Map<String, String>
	 * to be consumed by an entity factory
	 * 
	 * @param ResultSet data
	 * 
	 * @return Map<String, String>
	 */
	public static Map<String, String> toMap(ResultSet data)
	{
		Map<String, String> output = new HashMap<>();
		
		log.trace("attempting to convert ResultSet to Map<String,String>");
		try {
			ResultSetMetaData md = data.getMetaData();
			int columnCount = md.getColumnCount();

			for( int i = 1; i <= columnCount; i++ ) {
				String columnName = md.getColumnLabel(i);
				String columnData = data.getString(columnName);
				
				output.put(columnName, columnData);
			}
			log.trace("ResultSet converted successfully");
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return output;
	}
}

package com.revature.ers.io;

import java.util.HashMap;
import java.util.Map;

import com.revature.ers.logger.IOLogger;
import com.revature.ers.logger.LoggerInterface;

/**
 * @deprecated
 *  -> in favor of using JSON strings to pass parameters
 *  
 * a utility class for performing transformations
 * on POST parameters received from HttpServletRequest
 * objects of type Map<String, String[]>
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
@Deprecated
public class PostParameters 
{
	private static LoggerInterface log = IOLogger.getInstance();
			
	/**
	 * converts POST parameters to Map<String, String>
	 * 
	 * @param Map<String, String[]>
	 * 
	 * @return Map<String, String>
	 */
	public static Map<String, String> toPropertyMap( Map<String, String[]> input)
	{
		Map<String, String> properties = new HashMap<>();
		
		log.trace("converting POST paramters to Map<String,String>");
		
		input.forEach((key, dataArray) -> {
			switch( dataArray.length ) {
				case 0:
					properties.put(key, "");
					break;
				case 1:
					properties.put(key, dataArray[0] );
					break;
				default :
					properties.put(key, String.valueOf(dataArray) );
			}
		});
		
		return properties;
	}
}

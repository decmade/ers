package com.revature.ers.dbal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * utility class the encapsulates the conversion of a
 * LocalDateTime object to a String and vice versa
 * by using the same DateTimeFormatter to ensure
 * that there is no data loss
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class DateTimeConverter 
{
	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	/**
	 * convert a LocalDateTime value to a String
	 * 
	 * @param LocalDateTime value
	 * 
	 * @return String
	 */
	public static String toString(LocalDateTime value)
	{
		String output = "null";
		
		if ( value != null ) {
			output = formatter.format( value );
		}
		
		return output;
	}
	
	/**
	 * convert a LocalDateTime value to a String
	 * that only captures the date portion of the
	 * timestamp
	 * 
	 * @param LocalDateTime value
	 * 
	 * @return String
	 */
	public static String toFileNameString(LocalDateTime value)
	{
		String output = "null";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
		
		if ( value != null ) {
			output = dateFormatter.format( value );
		}
		
		return output;
	}
	
	/**
	 * converts as String to a LocalDateTime value
	 * 
	 * @param String value
	 * 
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(String value)
	{
		if ( value == null || value.isEmpty() || value.equalsIgnoreCase("null") ) {
			return null;
		} else {
			return LocalDateTime.parse(value, formatter);
		}
	}
}

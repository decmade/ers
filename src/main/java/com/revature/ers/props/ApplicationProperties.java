package com.revature.ers.props;

import java.util.Properties;

import com.revature.ers.io.PropertiesFile;

/**
 * << singleton >>
 * 
 * a convenience class for loading the application-wide
 * properties from file without making successive calls
 * to the file io layer
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ApplicationProperties 
{
	/*
	 * create singleton instance
	 */
	private static ApplicationProperties instance = new ApplicationProperties();
	
	/*
	 * name of the application's properties file
	 */
	final public static String FILE_NAME = "application.properties";
	
	private Properties properties;
	
	/**
	 * returns singleton instance
	 * 
	 * @return ApplicationProperties
	 */
	public static ApplicationProperties getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ApplicationProperties()
	{
		super();
		
		this.properties = PropertiesFile.load(FILE_NAME);
	}
	
	/**
	 * return the encapsulated Properties object
	 * 
	 * @return Properties
	 */
	public Properties getProperties()
	{
		return this.properties;
	}
}

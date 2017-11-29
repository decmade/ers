package com.revature.ers.dbal.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.revature.ers.logger.DbalLogger;
import com.revature.ers.logger.LoggerInterface;
import com.revature.ers.props.ApplicationProperties;

/**
 * << singleton >>
 * 
 * represents a persistent database connection
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class DbConnection 
{
	/**
	 * singleton instantiation
	 */
	private static DbConnection instance = new DbConnection();
	
	private static LoggerInterface log = DbalLogger.getInstance();
	
	private Connection connection;
	private Properties appProperties;
	private Context environmentContext;
	
	/**
	 * provide singleton instance to clients
	 * 
	 * @return SysLog
	 */
	public static DbConnection getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private DbConnection()
	{
		super();
		
		this.connection = null;
		this.appProperties = null;
		this.environmentContext = null;
	}
	
	/**
	 * connect to the database
	 * 
	 * lazy-loads Connection instance to prevent multiple connection attempts
	 * 
	 * @return Connection
	 */
	public Connection connect()
	{
		
		if ( this.connection == null ) {
			
			Properties props = this.getApplicationProperties();
			String dbClassName = props.getProperty("DatabaseDriver", "");
			String url = props.getProperty("DatabaseUrl", "");
			String username = props.getProperty("DatabaseUser", "");
			String password = props.getProperty("DatabasePassword", "");
			String datasourceName = props.getProperty("DatasourceName", "");
			
			
			log.debug("attempting to establish a database connection");
			
			if ( datasourceName.isEmpty() ) {
				try {
					Class.forName( dbClassName );
					log.debug("establishing JDBC connection");
					this.connection = DriverManager.getConnection( url, username, password );
					log.debug("database connection established");
				} catch(SQLException e) {
					log.error( e.getMessage() );
				} catch(ClassNotFoundException e) {
					log.error( String.format("could not find database driver class: [%s]", dbClassName) );
				}
			} else {
				try {
					log.debug("retriving datasource from web container");
					DataSource ds = (DataSource)this.getEnvironmentContext().lookup( datasourceName );
					log.debug("database connection established");
					return ds.getConnection();
				} catch (SQLException e) {
					log.error( e.getMessage() );
				} catch (NamingException e) {
					log.error( e.getMessage() );
				}
			}
		}
		
		return this.connection;
	}
	
	@Override
	public void finalize()
	{
		if ( this.connection != null ) {
			log.debug("attempting to close open database connection");
			
			try {
				this.connection.close();
			} catch(SQLException e) {
				log.error( e.getMessage() );
			}
			
			log.debug("database connection closed");
		}
	}
	
	/**
	 * << lazy-load >>
	 * 
	 * properties file is not needed as long as I am using the 
	 * pooled JNDI interface from Tomcat
	 * 
	 * retrieve the properties for the application
	 * 
	 * @return Properties
	 */
	private Properties getApplicationProperties()
	{
		if ( this.appProperties == null ) {
			this.appProperties = ApplicationProperties.getInstance().getProperties();
		}
		
		return  this.appProperties;
	}
	
	/**
	 * << lazy-load >>
	 * returns the environment context for the servlet
	 * 
	 * @param String contextName
	 * @return
	 */
	private Context getEnvironmentContext() {
		if ( this.environmentContext == null ) {
			try {
				String contextName = this.getApplicationProperties().getProperty("EnvironmentContext");
				Context initContext = new InitialContext();
				this.environmentContext  = (Context)initContext.lookup( contextName );
			
			} catch(NamingException e) {
				log.error( e.getMessage() );
			}
		}
		
		return this.environmentContext;
	}
}

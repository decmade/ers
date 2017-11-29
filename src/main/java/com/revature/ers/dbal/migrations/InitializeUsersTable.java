package com.revature.ers.dbal.migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.migrations.data.UserObjectsLoader;

/**
 * initializes the Users table in the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class InitializeUsersTable extends AbstractMigration 
{
	
	@Override
	public void run() {
		log.info("initializing Users table");
		
//		this.dropSequence();
		this.dropUsersTable();
		this.createUsersTable();
//		this.addSequence();
//		this.addTrigger();
		this.fillUsersTable();	
		
		log.info("Users table initialized");
	}
	
	/**
	 * creates Users table
	 */
	private void createUsersTable()
	{
		String sql = "CREATE TABLE users ( id INTEGER PRIMARY KEY, identity VARCHAR2(50) UNIQUE, lastname VARCHAR2(50), firstname VARCHAR2(50), email VARCHAR2(100), password VARCHAR2(200), roleid NUMBER )";
		PreparedStatement stmt = null;
		
		log.debug("attempting to create Users table");
		try {	
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("Users table created");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the users table from the database
	 */
	private void dropUsersTable()
	{
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop Users table");
		try {
			stmt = connection.prepareStatement("DROP TABLE users");
			stmt.executeUpdate();
			log.debug("Users table dropped");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * fills user table with data
	 */
	private void fillUsersTable()
	{
		List<UserInterface> users = UserObjectsLoader.getInstance().load();
		
		log.debug("attempting to insert initial User records into the database");
		
		users.forEach( user -> {
			BeanManager.saveUser(user);
		});
		
		log.debug( String.format("inserted %d User records into the database", users.size() ) );
		
		
	}
	
	/**
	 * creates a sequence for the ID field
	 */
	private void addSequence()
	{
		String sql = "CREATE SEQUENCE users_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to add ID sequence to Users table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("added ID sequence to Users table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the sequence for the ID field
	 */
	private void dropSequence()
	{
		String sql = "DROP SEQUENCE users_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ID sequence for Users table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("dropped ID sequence for Users table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a trigger for inserting and updating records
	 */
	private void addTrigger()
	{
		String sql = "CREATE OR REPLACE TRIGGER users_change_trig BEFORE UPDATE OR INSERT ON users FOR EACH ROW BEGIN IF INSERTING THEN SELECT users_id_seq.nextVal INTO :new.id FROM DUAL; ELSE SELECT :old.id INTO :new.id FROM DUAL; END IF; END;";
		
		Statement stmt = null;
		
		log.debug("attempting to add trigger to Users table");
		try {
			stmt = connection.createStatement();
			stmt.execute( sql );
			log.debug("added trigger to Users table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
}

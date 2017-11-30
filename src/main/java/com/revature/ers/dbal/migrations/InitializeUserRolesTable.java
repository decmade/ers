package com.revature.ers.dbal.migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.ers.beans.interfaces.UserRoleInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.migrations.data.UserRoleObjectsLoader;

public class InitializeUserRolesTable extends AbstractMigration 
{

	@Override
	public void run() {
		log.info("attempting to initialize UserRoles table");
		
		this.dropSequence();
		this.dropUsersRoleTable();
		this.createUsersRoleTable();
		this.addSequence();
		this.addTrigger();
		this.fillUserRolesTable();
		
		log.info("UserRoles table initialized");
	}
	
	/**
	 * creates the UserRoles table in the database
	 */
	private void createUsersRoleTable()
	{
		String sql = "CREATE TABLE userroles ( id INTEGER PRIMARY KEY, description VARCHAR2(50) )";
		PreparedStatement stmt = null;
		
		log.debug("attempting to created UserRoles table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("UserRoles table created successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the UserRoles table in the database
	 */
	private void dropUsersRoleTable()
	{
		String sql = "DROP TABLE userroles";
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop UserRoles table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("UserRoles table dropped successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a sequence for the ID field
	 */
	private void addSequence()
	{
		String sql = "CREATE SEQUENCE userroles_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to add ID sequence to UserRoles table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("added ID sequence to UserRoles table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the sequence for the ID field
	 */
	private void dropSequence()
	{
		String sql = "DROP SEQUENCE userroles_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ID sequence for UserRoles table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("dropped ID sequence for UserRoles table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a trigger for inserting and updating records
	 */
	private void addTrigger()
	{
		String sql = "CREATE OR REPLACE TRIGGER userroles_change_trig BEFORE UPDATE OR INSERT ON userroles FOR EACH ROW BEGIN IF INSERTING THEN SELECT userroles_id_seq.nextVal INTO :new.id FROM DUAL; ELSE SELECT :old.id INTO :new.id FROM DUAL; END IF; END;";
		
		Statement stmt = null;
		
		log.debug("attempting to add trigger to UserRoles table");
		try {
			stmt = connection.createStatement();
			stmt.execute( sql );
			log.debug("added trigger to UserRoles table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	private void fillUserRolesTable()
	{
		List<UserRoleInterface> roles = UserRoleObjectsLoader.getInstance().load();
		
		log.debug("attempting to add initial UserRole records");
		roles.forEach( role -> BeanManager.saveUserRole(role) );
		log.debug( String.format("%d UserRole records inserted", roles.size() ) );
	}
	

}

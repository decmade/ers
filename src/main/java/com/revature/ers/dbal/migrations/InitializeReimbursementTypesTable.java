package com.revature.ers.dbal.migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.ers.beans.interfaces.ReimbursementTypeInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.migrations.data.ReimbursementTypeObjectsLoader;

public class InitializeReimbursementTypesTable extends AbstractMigration 
{

	@Override
	public void run() {
//		this.dropSequence();
		this.dropReimbursementTypesTable();
		this.createReimbursementTypesTable();
//		this.addSequence();
//		this.addTrigger();
		this.fillReimbursementTypesTable();
		
	}
	
	/**
	 * creates the table in the database
	 */
	private void createReimbursementTypesTable()
	{
		String sql = "CREATE TABLE reimbursement_types ( id INTEGER PRIMARY KEY, description VARCHAR2(255) )";
		PreparedStatement stmt = null;
		
		log.debug("attempting to created ReimbursementTypes table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("ReimbursementTypes table created successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the table in the database
	 */
	private void dropReimbursementTypesTable()
	{
		String sql = "DROP TABLE reimbursement_types";
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ReimbursementTypes table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("ReimbursementTypes table dropped successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a sequence for the ID field
	 */
	private void addSequence()
	{
		String sql = "CREATE SEQUENCE reimbursement_types_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to add ID sequence to ReimbursementTypes table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("added ID sequence to ReimbursementTypes table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the sequence for the ID field
	 */
	private void dropSequence()
	{
		String sql = "DROP SEQUENCE reimbursement_types_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ID sequence for ReimbursementTypes table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("dropped ID sequence for ReimbursementTypes table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a trigger for inserting and updating records
	 */
	private void addTrigger()
	{
		String sql = "CREATE OR REPLACE TRIGGER reimb_types_change_trig BEFORE UPDATE OR INSERT ON reimbursement_types FOR EACH ROW BEGIN IF INSERTING THEN SELECT reimbursement_types_id_seq.nextVal INTO :new.id FROM DUAL; ELSE SELECT :old.id INTO :new.id FROM DUAL; END IF; END;";
		
		Statement stmt = null;
		
		log.debug("attempting to add trigger to ReimbursementTypes table");
		try {
			stmt = connection.createStatement();
			stmt.execute( sql );
			log.debug("added trigger to ReimbursementTypes table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	private void fillReimbursementTypesTable()
	{
		List<ReimbursementTypeInterface> types = ReimbursementTypeObjectsLoader.getInstance().load();
		
		log.debug("attempting to add initial ReimbursementType records");
		types.forEach( type -> BeanManager.saveUserRole(type) );
		log.debug( String.format("%d ReimbursementType records inserted", types.size() ) );
	}
	
	


}

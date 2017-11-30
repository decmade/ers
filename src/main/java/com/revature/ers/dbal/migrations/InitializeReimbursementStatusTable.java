package com.revature.ers.dbal.migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.migrations.data.ReimbursementStatusObjectsLoader;

public class InitializeReimbursementStatusTable extends AbstractMigration 
{

	@Override
	public void run() {
		this.dropSequence();
		this.dropReimbursementStatusTable();
		this.createReimbursementStatusTable();
		this.addSequence();
		this.addTrigger();
		this.fillTable();
		
	}
	
	/**
	 * creates the table in the database
	 */
	private void createReimbursementStatusTable()
	{
		String sql = "CREATE TABLE reimbursement_status ( id INTEGER PRIMARY KEY, description VARCHAR2(255) )";
		PreparedStatement stmt = null;
		
		log.debug("attempting to created ReimbursementStatus table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("ReimbursementStatus table created successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the table in the database
	 */
	private void dropReimbursementStatusTable()
	{
		String sql = "DROP TABLE reimbursement_status";
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ReimbursementStatus table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("ReimbursementStatus table dropped successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a sequence for the ID field
	 */
	private void addSequence()
	{
		String sql = "CREATE SEQUENCE reimbursement_status_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to add ID sequence to ReimbursementStatus table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("added ID sequence to ReimbursementStatus table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the sequence for the ID field
	 */
	private void dropSequence()
	{
		String sql = "DROP SEQUENCE reimbursement_status_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ID sequence for ReimbursementStatus table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("dropped ID sequence for ReimbursementStatus table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a trigger for inserting and updating records
	 */
	private void addTrigger()
	{
		String sql = "CREATE OR REPLACE TRIGGER reimb_status_change_trig BEFORE UPDATE OR INSERT ON reimbursement_status FOR EACH ROW BEGIN IF INSERTING THEN SELECT reimbursement_status_id_seq.nextVal INTO :new.id FROM DUAL; ELSE SELECT :old.id INTO :new.id FROM DUAL; END IF; END;";
		
		Statement stmt = null;
		
		log.debug("attempting to add trigger to ReimbursementStatus table");
		try {
			stmt = connection.createStatement();
			stmt.execute( sql );
			log.debug("added trigger to ReimbursementStatus table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	private void fillTable()
	{
		List<ReimbursementStatusInterface> types = ReimbursementStatusObjectsLoader.getInstance().load();
		
		log.debug("attempting to add initial ReimbursementStatus records");
		types.forEach( status -> BeanManager.saveReimbursementStatus(status) );
		log.debug( String.format("%d ReimbursementStatsus records inserted", types.size() ) );
	}
	
	


}

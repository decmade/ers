package com.revature.ers.dbal.migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.migrations.data.ReimbursementObjectsLoader;

public class InitializeReimbursementsTable extends AbstractMigration 
{

	@Override
	public void run() {
//		this.dropSequence();
		this.dropReimbursementsTable();
		this.createReimbursementsTable();
//		this.addSequence();
//		this.addTrigger();
		this.fillReimbursementsTable();
		
	}
	
	/**
	 * creates the table in the database
	 */
	private void createReimbursementsTable()
	{
		String sql = "CREATE TABLE reimbursements ( id INTEGER PRIMARY KEY, description VARCHAR2(255), submitted VARCHAR(30), resolved VARCHAR2(30), authorid NUMBER, resolverid NUMBER, statusid NUMBER, typeid NUMBER, receiptid NUMBER, amount NUMBER(15, 2) )";
		PreparedStatement stmt = null;
		
		log.debug("attempting to created Reimbursements table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("Reimbursements table created successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the table in the database
	 */
	private void dropReimbursementsTable()
	{
		String sql = "DROP TABLE reimbursements";
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop Reimbursements table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("Reimbursements table dropped successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a sequence for the ID field
	 */
	private void addSequence()
	{
		String sql = "CREATE SEQUENCE reimbursements_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to add ID sequence to Reimbursements table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("added ID sequence to Reimbursements table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the sequence for the ID field
	 */
	private void dropSequence()
	{
		String sql = "DROP SEQUENCE reimbursements_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ID sequence for Reimbursements table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("dropped ID sequence for Reimbursements table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a trigger for inserting and updating records
	 */
	private void addTrigger()
	{
		String sql = "CREATE OR REPLACE TRIGGER reimbursements_change_trig BEFORE UPDATE OR INSERT ON reimbursements FOR EACH ROW BEGIN IF INSERTING THEN SELECT reimbursements_id_seq.nextVal INTO :new.id FROM DUAL; ELSE SELECT :old.id INTO :new.id FROM DUAL; END IF; END;";
		
		Statement stmt = null;
		
		log.debug("attempting to add trigger to Reimbursements table");
		try {
			stmt = connection.createStatement();
			stmt.execute( sql );
			log.debug("added trigger to Reimbursements table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	private void fillReimbursementsTable()
	{
		List<ReimbursementInterface> reimbursements = ReimbursementObjectsLoader.getInstance().load();
		
		log.debug("attempting to add initial Reimbursement records");
		reimbursements.forEach( reimbursement -> BeanManager.saveReimbursement(reimbursement) );
		log.debug( String.format("%d Reimbursement records inserted", reimbursements.size() ) );
	}
	
	


}

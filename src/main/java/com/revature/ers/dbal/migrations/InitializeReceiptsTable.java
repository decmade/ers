package com.revature.ers.dbal.migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.migrations.data.ReceiptObjectsLoader;

public class InitializeReceiptsTable extends AbstractMigration 
{

	@Override
	public void run() {
		log.info("attempting to initialize Receipts table");
		
//		this.dropSequence();
		this.dropTable();
		this.createTable();
//		this.addSequence();
//		this.addTrigger();
		this.fillUserRolesTable();
		
		log.info("Receipts table initialized");
	}
	
	/**
	 * creates the Receipts table in the database
	 */
	private void createTable()
	{
		String sql = "CREATE TABLE receipts ( id INTEGER PRIMARY KEY, filename VARCHAR2(200), created VARCHAR(30) )";
		PreparedStatement stmt = null;
		
		log.debug("attempting to created Receipts table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("Receipts table created successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the UserRoles table in the database
	 */
	private void dropTable()
	{
		String sql = "DROP TABLE receipts";
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop Receipts table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("Receipts table dropped successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a sequence for the ID field
	 */
	private void addSequence()
	{
		String sql = "CREATE SEQUENCE receipts_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to add ID sequence to Receipts table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("added ID sequence to Receipts table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * drops the sequence for the ID field
	 */
	private void dropSequence()
	{
		String sql = "DROP SEQUENCE receipts_id_seq";
		
		PreparedStatement stmt = null;
		
		log.debug("attempting to drop ID sequence for Receipts table");
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			log.debug("dropped ID sequence for Receipts table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	/**
	 * creates a trigger for inserting and updating records
	 */
	private void addTrigger()
	{
		String sql = "CREATE OR REPLACE TRIGGER receipts_change_trig BEFORE UPDATE OR INSERT ON receipts FOR EACH ROW BEGIN IF INSERTING THEN SELECT receipts_id_seq.nextVal INTO :new.id FROM DUAL; ELSE SELECT :old.id INTO :new.id FROM DUAL; END IF; END;";
		
		Statement stmt = null;
		
		log.debug("attempting to add trigger to Receipts table");
		try {
			stmt = connection.createStatement();
			stmt.execute( sql );
			log.debug("added trigger to Receipts table successfully");
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
	}
	
	private void fillUserRolesTable()
	{
		List<ReceiptInterface> receipts = ReceiptObjectsLoader.getInstance().load();
		
		log.debug("attempting to add initial Receipts records");
		receipts.forEach( receipt -> BeanManager.saveReceipt(receipt) );
		log.debug( String.format("%d Receipt records inserted", receipts.size() ) );
	}
	

}

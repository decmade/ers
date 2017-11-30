package com.revature.ers.dbal.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.ers.beans.factories.ReceiptFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.dbal.DateTimeConverter;

/**
 * << singleton >>
 * 
 * represents the interface to the Receipts table in
 * the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReceiptsTableAdapter extends AbstractTableAdapter<ReceiptInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReceiptsTableAdapter instance = new ReceiptsTableAdapter();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return ReceiptInterfacesTableAdapter
	 */
	public static ReceiptsTableAdapter getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReceiptsTableAdapter()
	{
		super();
	}
	
	@Override
	public ReceiptInterface get(String id) 
	{
		String sql = "SELECT * FROM receipts r WHERE r.id = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		ReceiptInterface receipt = null;
		
		log.debug( String.format("retrieving receipt associated with ID: %s from database", id) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, Integer.parseInt( id ));
			
			results = stmt.executeQuery();
			
			if ( results.next()  ) {
				receipt = this.getFactory().create( results );
			} else {
				log.debug( String.format("no receipt found with ID: %s in the database", id) );
			}
			
			results.close();
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return receipt;		
	}
	

	@Override
	public List<ReceiptInterface> getAll() 
	{
		String sql = "SELECT * FROM receipts r ORDER BY created";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<ReceiptInterface> receipts = new ArrayList<>();
		
		log.debug( "retrieving all Receipts from database" );
		try {
			stmt = connection.prepareStatement( sql );
			
			results = stmt.executeQuery();
			
			while ( results.next()  ) {
				receipts.add( this.getFactory().create( results ) );
			} 
			
			log.debug( String.format("%d receipts retrieved from the database", receipts.size() ) );
			
			results.close();
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return receipts;	
	}

	@Override
	public boolean save(ReceiptInterface receipt) 
	{
		boolean result = false;
		
		log.debug("attempting to save Receipt to database");
		if ( receipt.getId() > 0 ) {
			result = this.update(receipt);
		} else {
			result = this.insert(receipt);
		}
		
		return result;
	}

	@Override
	public boolean remove(ReceiptInterface receipt) 
	{
		String sql = "DELETE FROM receipts r WHERE r.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("removing Recript associated with ID:%d from database", receipt.getId() ) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, receipt.getId() );
			
	
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("receipt ID:%d removed from the database", receipt.getId() ) );
			} else {
				log.debug( String.format("no receipt found with id:%d in the database", receipt.getId() ) );
			}
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}

	@Override
	protected FactoryInterface<ReceiptInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = ReceiptFactory.getInstance();
		}
		
		return this.factory;
	}
	

	
	/**
	 * updates ReceiptInterface with ID in the database
	 * 
	 * @param ReceiptInterface receipt
	 * 
	 * @return boolean
	 */
	private boolean update(ReceiptInterface receipt)
	{
		String sql = "UPDATE receipts SET filename = ? WHERE id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("attempting to update record for receipt with ID: %d", receipt.getId() ) );
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, receipt.getFileName() );
			stmt.setInt(2, receipt.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("successfully updated record for receipt with ID: %d", receipt.getId() ) );
			} else {
				log.debug( String.format("record was not updated for receipt with ID: %d", receipt.getId() ) );
			}
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
				
		return result;
	}
	
	/**
	 * inserts Receipt into the database
	 * 
	 * @param ReceiptInterface receipt
	 * 
	 * @return boolean
	 */
	private boolean insert(ReceiptInterface receipt)
	{
		String sql = "INSERT INTO receipts (filename, created) VALUES (?, ?)";
		String[] returnFields = {"id"};
		ResultSet returns = null;
		PreparedStatement stmt = null;
		boolean result = false;
		receipt.setCreated( LocalDateTime.now() );
		
		log.debug("attempting to insert new Receipt record into the database");
		try {
			stmt = connection.prepareStatement(sql, returnFields);
			
			stmt.setString(1, receipt.getFileName() );
			stmt.setString(2, DateTimeConverter.toString( receipt.getCreated() ) );
			
			if( stmt.executeUpdate() > 0 ) {
				result = true;
				returns = stmt.getGeneratedKeys();
				
				if (returns.next() ) {
					receipt.setId( returns.getInt(1));
					log.debug( String.format("successfully inserted new Receipt record with ID: %d", receipt.getId() ) );
				}
				
				returns.close();
			}
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
		
	}

}

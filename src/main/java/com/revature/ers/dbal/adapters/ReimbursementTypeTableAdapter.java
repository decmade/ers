package com.revature.ers.dbal.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ers.beans.factories.ReimbursementTypeFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReimbursementTypeInterface;

/**
 * << singleton >>
 * 
 * represents the interface to the ReimbursementType table in
 * the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementTypeTableAdapter extends AbstractTableAdapter<ReimbursementTypeInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReimbursementTypeTableAdapter instance = new ReimbursementTypeTableAdapter();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UsersTableAdapter
	 */
	public static ReimbursementTypeTableAdapter getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementTypeTableAdapter()
	{
		super();
	}
	
	@Override
	public ReimbursementTypeInterface get(String id) 
	{
		String sql = "SELECT * FROM reimbursement_types rt WHERE rt.id = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		ReimbursementTypeInterface type = null;
		
		log.debug( String.format("retrieving ReimbursementType associated with ID: %s from database", id) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, Integer.parseInt( id ));
			
			results = stmt.executeQuery();
			
			if ( results.next()  ) {
				type = this.getFactory().create( results );
			} else {
				log.debug( String.format("no ReimbursementType found with ID: %s in the database", id) );
			}
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return type;		
	}
	

	@Override
	public List<ReimbursementTypeInterface> getAll() 
	{
		String sql = "SELECT * FROM reimbursement_types rt ORDER BY rt.description";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<ReimbursementTypeInterface> types = new ArrayList<>();
		
		log.debug( "retrieving all ReimbursementTypes from database" );
		try {
			stmt = connection.prepareStatement( sql );
			
			results = stmt.executeQuery();
			
			while ( results.next()  ) {
				types.add( this.getFactory().create( results ) );
			} 
			
			log.debug( String.format("%d ReimbursementTypes retrieved from the database", types.size() ) );
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return types;	
	}

	@Override
	public boolean save(ReimbursementTypeInterface type) 
	{
		boolean result = false;
		
		log.debug("attempting to save ReimbursementType to database");
		if ( type.getId() > 0 ) {
			result = this.update(type);
		} else {
			result = this.insert(type);
		}
		
		return result;
	}

	@Override
	public boolean remove(ReimbursementTypeInterface type) 
	{
		String sql = "DELETE FROM reimbursement_types rt WHERE rt.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("removing ReimbursementType associated with ID:%d from database", type.getId() ) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, type.getId() );
			
	
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("ReimbursementType ID:%d removed from the database", type.getId() ) );
			} else {
				log.debug( String.format("no ReimbursementType found with id:%d in the database", type.getId() ) );
			}
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}

	@Override
	protected FactoryInterface<ReimbursementTypeInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = ReimbursementTypeFactory.getInstance();
		}
		
		return this.factory;
	}
	

	
	/**
	 * updates ReimbursementType with ID in the database
	 * 
	 * @param ReimbursementTypeInterface type
	 * 
	 * @return boolean
	 */
	private boolean update(ReimbursementTypeInterface type)
	{
		String sql = "UPDATE reimbursement_types rt SET rt.description = ? WHERE rt.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("attempting to update record for ReimbursementType with ID: %d", type.getId() ) );
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, type.getDescription() );
			stmt.setInt(2, type.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("successfully updated record for ReimbursementType with ID: %d", type.getId() ) );
			} else {
				log.debug( String.format("record was not updated for ReimbursementType with ID: %d", type.getId() ) );
			}
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
				
		return result;
	}
	
	/**
	 * inserts Receipt into the database
	 * 
	 * @param ReimbursementTypeInterface type
	 * 
	 * @return boolean
	 */
	private boolean insert(ReimbursementTypeInterface type)
	{
		String sql = "INSERT INTO reimbursement_types (description) VALUES (?)";
		String[] returnFields = {"id"};
		ResultSet returns = null;
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug("attempting to insert new ReimbursementType record into the database");
		try {
			stmt = connection.prepareStatement(sql, returnFields);
			
			stmt.setString(1, type.getDescription() );
			
			if( stmt.executeUpdate() > 0 ) {
				result = true;
				returns = stmt.getGeneratedKeys();
				
				if (returns.next() ) {
					type.setId( returns.getInt(1));
					log.debug( String.format("successfully inserted new ReimbursementType record with ID: %d", type.getId() ) );
				}
			}
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
		
	}

}

package com.revature.ers.dbal.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ers.beans.factories.ReimbursementStatusFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;

/**
 * << singleton >>
 * 
 * represents the interface to the ReimbursementStatus table in
 * the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementStatusTableAdapter extends AbstractTableAdapter<ReimbursementStatusInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReimbursementStatusTableAdapter instance = new ReimbursementStatusTableAdapter();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UsersTableAdapter
	 */
	public static ReimbursementStatusTableAdapter getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementStatusTableAdapter()
	{
		super();
	}
	
	@Override
	public ReimbursementStatusInterface get(String id) 
	{
		String sql = "SELECT * FROM reimbursement_status rs WHERE rs.id = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		ReimbursementStatusInterface status = null;
		
		log.debug( String.format("retrieving ReimbursementStatus associated with ID: %s from database", id) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, Integer.parseInt( id ));
			
			results = stmt.executeQuery();
			
			if ( results.next()  ) {
				status = this.getFactory().create( results );
			} else {
				log.debug( String.format("no ReimbursementStatus found with ID: %s in the database", id) );
			}
			
			results.close();
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return status;		
	}
	

	@Override
	public List<ReimbursementStatusInterface> getAll() 
	{
		String sql = "SELECT * FROM reimbursement_status rs ORDER BY id";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<ReimbursementStatusInterface> statuses = new ArrayList<>();
		
		log.debug( "retrieving all ReimbursementStatuses from database" );
		try {
			stmt = connection.prepareStatement( sql );
			
			results = stmt.executeQuery();
			
			while ( results.next()  ) {
				statuses.add( this.getFactory().create( results ) );
			} 
			
			log.debug( String.format("%d ReimbursementStatuses retrieved from the database", statuses.size() ) );
			
			results.close();
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return statuses;	
	}

	@Override
	public boolean save(ReimbursementStatusInterface status) 
	{
		boolean result = false;
		
		log.debug("attempting to save ReimbursementStatus to database");
		if ( status.getId() > 0 ) {
			result = this.update(status);
		} else {
			result = this.insert(status);
		}
		
		return result;
	}

	@Override
	public boolean remove(ReimbursementStatusInterface status) 
	{
		String sql = "DELETE FROM reimbursement_status rs WHERE rs.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("removing ReimbursementStatus associated with ID:%d from database", status.getId() ) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, status.getId() );
			
	
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("ReimbursementStatus ID:%d removed from the database", status.getId() ) );
			} else {
				log.debug( String.format("no ReimbursementStatus found with id:%d in the database", status.getId() ) );
			}
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}

	@Override
	protected FactoryInterface<ReimbursementStatusInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = ReimbursementStatusFactory.getInstance();
		}
		
		return this.factory;
	}
	

	
	/**
	 * updates ReimbursementStatus with ID in the database
	 * 
	 * @param ReimbursementStatusInterface status
	 * 
	 * @return boolean
	 */
	private boolean update(ReimbursementStatusInterface status)
	{
		String sql = "UPDATE reimbursement_status rs SET rs.description = ? WHERE rs.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("attempting to update record for ReimbursementStatus with ID: %d", status.getId() ) );
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, status.getDescription() );
			stmt.setInt(2, status.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("successfully updated record for ReimbursementStatus with ID: %d", status.getId() ) );
			} else {
				log.debug( String.format("record was not updated for ReimbursementStatus with ID: %d", status.getId() ) );
			}
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
				
		return result;
	}
	
	/**
	 * inserts Receipt into the database
	 * 
	 * @param ReimbursementStatusInterface status
	 * 
	 * @return boolean
	 */
	private boolean insert(ReimbursementStatusInterface status)
	{
		String sql = "INSERT INTO reimbursement_status (description) VALUES (?)";
		String[] returnFields = {"id"};
		ResultSet returns = null;
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug("attempting to insert new ReimbursementStatus record into the database");
		try {
			stmt = connection.prepareStatement(sql, returnFields);
			
			stmt.setString(1, status.getDescription() );
			
			if( stmt.executeUpdate() > 0 ) {
				result = true;
				returns = stmt.getGeneratedKeys();
				
				if (returns.next() ) {
					status.setId( returns.getInt(1));
					log.debug( String.format("successfully inserted new ReimbursementStatus record with ID: %d", status.getId() ) );
				}
				
				returns.close();
			}
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
		
	}

}

package com.revature.ers.dbal.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ers.beans.factories.ReimbursementFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.dbal.DateTimeConverter;

/**
 * << singleton >>
 * 
 * represents the interface to the reimbursements table in
 * the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementsTableAdapter extends AbstractTableAdapter<ReimbursementInterface>
{
	/*
	 * instantiate a singleton instance
	 */
	private static ReimbursementsTableAdapter instance = new ReimbursementsTableAdapter();
	
	/**
	 * returns the singleton instance
	 */
	public static ReimbursementsTableAdapter getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementsTableAdapter()
	{
		super();
	}

	@Override
	public ReimbursementInterface get(String id) 
	{
		String sql = "SELECT * FROM reimbursements r WHERE r.id = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		int reimbursementId = Integer.parseInt( id );
		ReimbursementInterface reimbursement = null;
		
		log.debug( String.format("attempting to retrieve Reimbursement from database with ID:%d", reimbursementId ) );
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, reimbursementId );
			
			results = stmt.executeQuery();
			
			if ( results.next() ) {
				reimbursement = this.getFactory().create( results );
				log.debug( String.format("successfully retrieved Reimbursement from database with ID:%d", reimbursementId ) );
			}
			
			results.close();
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return reimbursement;
	}
	
	/**
	 * retrieves the Reimbursements associated with a certain
	 * Author User
	 * 
	 * @param UserInterface user
	 * 
	 * @return List<ReimbursementInterface>
	 */
	public List<ReimbursementInterface> getByAuthor(UserInterface user) 
	{
		String sql = "SELECT * FROM reimbursements r WHERE r.authorid = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<ReimbursementInterface> reimbursements = new ArrayList<>();
		
		log.debug( String.format("attempting to retrieve Reimbursements from database with Author ID:%d", user.getId() ) );
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, user.getId() );
			
			results = stmt.executeQuery();
			
			while ( results.next() ) {
				reimbursements.add( this.getFactory().create( results ) );
			}
			
			log.debug( String.format("successfully retrieved %d Reimbursements from database with Author ID:%d", reimbursements.size(), user.getId() ) );
			
			results.close();
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return reimbursements;
	}
	
	/**
	 * retrieves the Reimbursements associated with a certain
	 * Resolver User
	 * 
	 * @param UserInterface user
	 * 
	 * @return List<ReimbursementInterface>
	 */
	public List<ReimbursementInterface> getByResolver(UserInterface resolver) 
	{
		String sql = "SELECT * FROM reimbursements r WHERE r.resolverid = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<ReimbursementInterface> reimbursements = new ArrayList<>();
		
		log.debug( String.format("attempting to retrieve Reimbursements from database with Resolver ID:%d", resolver.getId() ) );
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, resolver.getId() );
			
			results = stmt.executeQuery();
			
			while ( results.next() ) {
				reimbursements.add( this.getFactory().create( results ) );
			}
			
			log.debug( String.format("successfully retrieved %d Reimbursements from database with Resolver ID:%d", reimbursements.size(), resolver.getId() ) );
			
			results.close();
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return reimbursements;
	}
	
	/**
	 * retrieves the Reimbursements associated with a certain
	 * Receip
	 * 
	 * @param ReceiptInterface receipt
	 * 
	 * @return List<ReimbursementInterface>
	 */
	public List<ReimbursementInterface> getByReceipt(ReceiptInterface receipt) 
	{
		String sql = "SELECT * FROM reimbursements r WHERE r.receiptid = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<ReimbursementInterface> reimbursements = new ArrayList<>();
		
		log.debug( String.format("attempting to retrieve Reimbursements from database with Receipt ID:%d", receipt.getId() ) );
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, receipt.getId() );
			
			results = stmt.executeQuery();
			
			while ( results.next() ) {
				reimbursements.add( this.getFactory().create( results ) );
			}
			
			log.debug( String.format("successfully retrieved %d Reimbursements from database with Receipt ID:%d", reimbursements.size(), receipt.getId() ) );
			
			results.close();
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return reimbursements;
	}

	@Override
	public List<ReimbursementInterface> getAll() {
		String sql = "SELECT * FROM reimbursements r ORDER BY r.submitted, r.id";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<ReimbursementInterface> reimbursements = new ArrayList<>();
		
		log.debug( "attempting to retrieve all Reimbursements from database" );
		try {
			stmt = connection.prepareStatement(sql);
			
			results = stmt.executeQuery();
			
			while ( results.next() ) {
				reimbursements.add( this.getFactory().create( results ) );
			}
			
			log.debug( String.format("successfully retrieved %d Reimbursement objects from the database", reimbursements.size() ) );

			results.close();
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return reimbursements;
	}

	@Override
	public boolean save(ReimbursementInterface reimbursement ) 
	{
		if ( reimbursement.getId() > 0 ) {
			return this.update(reimbursement);
		} else {
			return this.insert(reimbursement);
		}
	}

	@Override
	public boolean remove(ReimbursementInterface reimbursement) 
	{
		boolean result = false;
		String sql = "DELETE FROM reimbursements r WHERE r.id = ?";
		PreparedStatement stmt = null;
		
		log.debug( String.format("attempting to delete Reimbursement from database with ID:%d", reimbursement.getId() ) );
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, reimbursement.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("successfully deleted Reimbursement from database with ID:%d", reimbursement.getId() ) );
			}
			
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}

	@Override
	protected FactoryInterface<ReimbursementInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = ReimbursementFactory.getInstance();
		}
		
		return this.factory;
	}
	
	/**
	 * inserts a Reimbursement object into the database
	 * 
	 * @param ReimbursementInterface reimbursement
	 *  
	 * @return boolean
	 */
	private boolean insert(ReimbursementInterface reimbursement)
	{
		String sql = "INSERT INTO reimbursements (description, amount, typeid, statusid, authorid, resolverid, receiptid, resolved, submitted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String[] returnFields = {"id"};
		ResultSet returns = null;
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug("attempting to insert Reimbursement object into database");
		try {
			stmt = connection.prepareStatement(sql, returnFields );
			stmt.setString(1, reimbursement.getDescription() );
			stmt.setDouble(2, reimbursement.getAmount() );
			stmt.setInt(3,  reimbursement.getTypeId() );
			stmt.setInt(4, reimbursement.getStatusId() );
			stmt.setInt(5,  reimbursement.getAuthorId() );
			stmt.setInt(6,  reimbursement.getResolverId() );
			stmt.setInt(7,  reimbursement.getReceiptId() );
			stmt.setString(8,  DateTimeConverter.toString( reimbursement.getResolved() ) );
			stmt.setString(9,  DateTimeConverter.toString( reimbursement.getSubmitted() ) );
			
			if ( stmt.executeUpdate() > 0 ) {			
				returns = stmt.getGeneratedKeys();
				if ( returns.next() ) {
					log.debug(String.format("successfully inserted Reimbursement object into database with ID:%d", returns.getInt(1) ));
					reimbursement.setId( returns.getInt(1) );
					result = true;
				}
				
				returns.close();
			} else {
				log.debug("attempt to insert Reimbursement object failed");
			}
			
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;

	}

	/**
	 * updates a Reimbursement object that already exists in the database
	 * 
	 * @param ReimbursementInterface reimbursement
	 *  
	 * @return boolean
	 */
	private boolean update(ReimbursementInterface reimbursement)
	{
		String sql = "UPDATE reimbursements SET description = ?, amount = ?, typeid = ?, statusid = ?, authorid = ?, resolverid = ?, receiptid = ?, resolved = ?, submitted = ? WHERE id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("attempting to update Reimbursement object in the database with ID:%d", reimbursement.getId() ) );
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, reimbursement.getDescription() );
			stmt.setDouble(2, reimbursement.getAmount() );
			stmt.setInt(3,  reimbursement.getTypeId() );
			stmt.setInt(4, reimbursement.getStatusId() );
			stmt.setInt(5,  reimbursement.getAuthorId() );
			stmt.setInt(6,  reimbursement.getResolverId() );
			stmt.setInt(7,  reimbursement.getReceiptId() );
			stmt.setString(8,  DateTimeConverter.toString( reimbursement.getResolved() ) );
			stmt.setString(9,  DateTimeConverter.toString( reimbursement.getSubmitted() ) );
			stmt.setInt(10, reimbursement.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {			
				log.debug(String.format("successfully updated Reimbursement object into database with ID:%d", reimbursement.getId() ));
				result = true;
			} else {
				log.debug( String.format("attempt to update Reimbursement object failed with ID:%d", reimbursement.getId() ) );
			}
			
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;

	}
}

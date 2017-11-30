package com.revature.ers.dbal.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ers.beans.factories.UserRoleFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.UserRoleInterface;

/**
 * << singleton >>
 * 
 * represents the interface to the userroles table in
 * the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UserRolesTableAdapter extends AbstractTableAdapter<UserRoleInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static UserRolesTableAdapter instance = new UserRolesTableAdapter();
	
	/**
	 * retrieves the singleton instance
	 * 
	 * @return UserRoleTableAdapter
	 */
	public static UserRolesTableAdapter getInstance()
	{
		return instance;
	}
	
	/**
	 * hiddne constructor
	 */
	private UserRolesTableAdapter()
	{
		super();
	}

	@Override
	public UserRoleInterface get(String id) {
		String sql = "SELECT * FROM userroles ur WHERE ur.id = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		UserRoleInterface role = null;
		
		log.debug( String.format( "attempting to retrieve UserRole object with id: %s", id) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, Integer.parseInt( id ) );
			
			results = stmt.executeQuery();
			
			if ( results.next() ) {
				role = this.getFactory().create( results );
			}
			
			log.debug( String.format("successfully retrieved UserRole with id: %s", id) );
			
			results.close();
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return role;
	}

	@Override
	public List<UserRoleInterface> getAll() {
		String sql = "SELECT * FROM userroles ur ORDER BY ur.description";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<UserRoleInterface> roles = new ArrayList<>();
		
		log.debug( "attempting to retrieve all UserRole objects from database" );
		try {
			stmt = connection.prepareStatement( sql );
			
			results = stmt.executeQuery();
			
			while ( results.next() ) {
				roles.add( this.getFactory().create( results ) );
			}
			
			log.debug( String.format("successfully retrieved %d UserRole objects from database", roles.size() ) );
			
			results.close();
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return roles;
	}

	@Override
	public boolean save(UserRoleInterface role) {
		if ( role.getId() > 0 ) {
			return this.updateUserRole(role);
		} else {
			return this.insertUserRole(role);
		}
	}

	@Override
	public boolean remove(UserRoleInterface role) {
		String sql = "DELETE FROM userroles ur WHERE ur.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format( "attempting to delete UserRole object with id: %d", role.getId()) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, role.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("successfully deleted UserRole with id: %d", role.getId() ) );
			} else {
				log.debug( String.format("could not delete UserRole with id: %d", role.getId() ) );
			}
			
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}

	@Override
	protected FactoryInterface<UserRoleInterface> getFactory() {
		if ( this.factory == null ) {
			this.factory = UserRoleFactory.getInstance();
		}
		
		return this.factory;
	}
	
	/**
	 * inserts a UserRole into the database
	 * 
	 * @param UserRoleInterface role
	 * 
	 * @return boolean
	 */
	private boolean insertUserRole(UserRoleInterface role) 
	{
		String sql = "INSERT INTO userroles (description) VALUES (?)";
		PreparedStatement stmt = null;
		String[] returnColumns = {"id"};
		ResultSet returns = null;
		boolean result = false;
		
		log.debug( "attempting to insert UserRole object into database" );
		try {
			stmt = connection.prepareStatement( sql, returnColumns );
			stmt.setString(1, role.getDescription() );
			
			if ( stmt.executeUpdate() > 0 ) {
				returns = stmt.getGeneratedKeys();
				
				if ( returns.next()  ) {
					result = true;
					role.setId( returns.getInt(1) );
					log.debug( String.format("successfully inserted UserRole with id: %d", role.getId() ) );
				}
				
				returns.close();
			} else {
				log.debug( "could not insert UserRole" );
			}
			
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}
	
	/**
	 * updates a UserRole into the database
	 * 
	 * @param UserRoleInterface role
	 * 
	 * @return boolean
	 */
	private boolean updateUserRole(UserRoleInterface role) 
	{
		String sql = "UPDATE userroles ur SET ur.description = ? WHERE ur.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("attempting to update UserRole object in database with ID: %d", role.getId() ) );
		try {
			stmt = connection.prepareStatement( sql);
			stmt.setString(1, role.getDescription() );
			stmt.setInt(2, role.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("successfully udpated UserRole with ID: %d", role.getId() ) );
			} else {
				log.debug( String.format( "could not update UserRole with ID: %d", role.getId() ) );
			}
			
			stmt.close();
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}	
}

package com.revature.ers.dbal.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ers.beans.factories.UserFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.interfaces.UserRoleInterface;

/**
 * << singleton >>
 * 
 * represents the interface to the users table in
 * the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UsersTableAdapter extends AbstractTableAdapter<UserInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static UsersTableAdapter instance = new UsersTableAdapter();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return UsersTableAdapter
	 */
	public static UsersTableAdapter getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private UsersTableAdapter()
	{
		super();
	}
	
	@Override
	public UserInterface get(String id) {
		String sql = "SELECT * FROM users u WHERE u.id = ?";
		PreparedStatement stmt = null;
		ResultSet results = null;
		UserInterface user = null;
		
		log.debug( String.format("retrieving user associated with ID: %s from database", id) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, Integer.parseInt( id ));
			
			results = stmt.executeQuery();
			
			if ( results.next()  ) {
				user = this.getFactory().create( results );
			} else {
				log.debug( String.format("no user found with ID: %s in the database", id) );
			}
			
			log.debug( String.format("user associated with ID: %s successfully retrieved from database", id) );
			results.close();
			stmt.close();
			
	
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return user;		
	}
	
	/**
	 * retrieves as user by their identity
	 * 
	 * @param String identity
	 * 
	 * @return UserInterface
	 */
	public UserInterface getByIdentity(String identity) {
		String sql = "SELECT * FROM users u WHERE lower(u.identity) = lower(?)";
	
		PreparedStatement stmt = null;
		ResultSet results = null;
		UserInterface user = null;
		
		log.debug( String.format("retrieving user associated with Identity: %s from database", identity) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setString(1, identity );
			
			results = stmt.executeQuery();
			
			if ( results.next()  ) {
				user = this.getFactory().create( results );
			} else {
				log.debug( String.format("no user found with Identity: %s in the database", identity) );
			}
			
			results.close();
			stmt.close();
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return user;		
	}
	
	/**
	 * retrieves a collection of Users with the
	 * given Role
	 * 
	 * @param String roleId
	 * 
	 * @return List<UserInterface>
	 */
	public List<UserInterface> getByRole(UserRoleInterface role) {
		String sql = "SELECT * FROM users u WHERE u.roleid = ? ORDER BY lastname, firstname, identity";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<UserInterface> users = new ArrayList<>();
		
		log.debug( String.format("retrieving users associated with Role ID: %s from database", role.getId() ) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, role.getId() );
			
			results = stmt.executeQuery();
			
			while ( results.next()  ) {
				users.add( this.getFactory().create( results ) );
			} 
				
			log.debug( String.format("%d users found with Role ID: %s in the database", users.size(), role.getId() ) );
			
			results.close();
			stmt.close();
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return users;		
	}

	@Override
	public List<UserInterface> getAll() {
		String sql = "SELECT * FROM users u ORDER BY lastname, firstname, identity";
		PreparedStatement stmt = null;
		ResultSet results = null;
		List<UserInterface> users = new ArrayList<>();
		
		log.debug( "retrieving all users from database" );
		try {
			stmt = connection.prepareStatement( sql );
			
			results = stmt.executeQuery();
			
			while ( results.next()  ) {
				users.add( this.getFactory().create( results ) );
			} 
			
			log.debug( String.format("%d users retrieved from the database", users.size() ) );
			
			results.close();
			stmt.close();
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return users;	
	}

	@Override
	public boolean save(UserInterface user) {
		boolean result = false;
		
		log.debug("attempting to save User to database");
		if ( user.getId() > 0 ) {
			result = this.updateUser(user);
		} else {
			result = this.insertUser(user);
		}
		
		return result;
	}

	@Override
	public boolean remove(UserInterface user) {
		String sql = "DELETE FROM users u WHERE u.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("removing user associated with ID:%d from database", user.getId() ) );
		try {
			stmt = connection.prepareStatement( sql );
			stmt.setInt(1, user.getId() );
			
	
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("user ID:%d removed from the database", user.getId() ) );
			} else {
				log.debug( String.format("no user found with id:%d in the database", user.getId() ) );
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
	}

	@Override
	protected FactoryInterface<UserInterface> getFactory() {
		if ( this.factory == null ) {
			this.factory = UserFactory.getInstance();
		}
		
		return this.factory;
	}
	

	
	/**
	 * updates User with ID in the database
	 * 
	 * @param UserInterface user
	 * 
	 * @return boolean
	 */
	private boolean updateUser(UserInterface user)
	{
		String sql = "UPDATE users u SET u.firstname = ?, u.lastname = ?, u.email = ?, u.roleid = ?, u.password = ?, u.secret = ?, u.identity = ? WHERE u.id = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug( String.format("attempting to update record for user with ID: %d", user.getId() ) );
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getFirstName() );
			stmt.setString(2, user.getLastName() );
			stmt.setString(3, user.getEmail() );
			stmt.setInt(4, user.getRoleId() );
			stmt.setString(5, user.getPassword() );
			stmt.setString(6, user.getSecret() );
			stmt.setString(7,  user.getIdentity() );
			stmt.setInt(8, user.getId() );
			
			if ( stmt.executeUpdate() > 0 ) {
				result = true;
				log.debug( String.format("successfully updated record for user with ID: %d", user.getId() ) );
			} else {
				log.debug( String.format("record was not updated for user with ID: %d", user.getId() ) );
			}
			
			stmt.close();
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
				
		return result;
	}
	
	/**
	 * inserts User into the database
	 * 
	 * @param User user
	 * 
	 * @return boolean
	 */
	private boolean insertUser(UserInterface user)
	{
		String sql = "INSERT INTO users (identity, lastname, firstname, email, roleid, password, secret) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String[] returnFields = {"id"};
		ResultSet returns = null;
		PreparedStatement stmt = null;
		boolean result = false;
		
		log.debug("attempting to insert new User record into the database");
		try {
			stmt = connection.prepareStatement(sql, returnFields);
			
			stmt.setString(1, user.getIdentity() );
			stmt.setString(2, user.getLastName() );
			stmt.setString(3, user.getFirstName() );
			stmt.setString(4, user.getEmail() );
			stmt.setInt(5, user.getRoleId() );
			stmt.setString(6, user.getPassword() );
			stmt.setString(7, user.getSecret() );
			
			if( stmt.executeUpdate() > 0 ) {
				result = true;
				returns = stmt.getGeneratedKeys();
				
				if (returns.next() ) {
					user.setId( returns.getInt(1));
					log.debug( String.format("successfully inserted new User record for user with ID: %d", user.getId() ) );
				}
				
				returns.close();
			}
			
		} catch(SQLException e) {
			log.error( e.getMessage() );
		}
		
		return result;
		
	}

}

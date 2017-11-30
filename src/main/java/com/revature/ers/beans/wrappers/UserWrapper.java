package com.revature.ers.beans.wrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.interfaces.UserRoleInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.io.Encryption;

/**
 * << decorator >>
 * 
 * decorates a UserInterface object to add functionality
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UserWrapper extends AbstractWrapper<UserInterface> implements UserInterface
{
	
	@Override
	public int getId() {
		if ( this.hasSubject() ) {
			return this.subject.getId();
		} else {
			return 0;
		}
	}

	@Override
	public void setId(int id) {
		if ( this.hasSubject() ) {
			this.subject.setId(id);
		}
	}

	@Override
	public String getIdentity() {
		if ( this.hasSubject() ) {
			return this.subject.getIdentity();
		} else {
			return "";
		}
	}

	@Override
	public void setIdentity(String name) {
		if ( this.hasSubject() ) {
			this.subject.setIdentity(name);
		}
		
	}

	@Override
	public String getPassword() {
		if ( this.hasSubject() ) {
			return this.subject.getPassword();
		} else {
			return "";
		}
	}

	@Override
	public void setPassword(String password) {
		if ( this.hasSubject() ) {
			this.subject.setPassword(password);
		}
	}

	@Override
	public String getFirstName() {
		if ( this.hasSubject() ) {
			return this.subject.getFirstName();
		} else {
			return "";
		}
	}

	@Override
	public void setFirstName(String firstName) {
		if ( this.hasSubject() ) {
			this.subject.setFirstName(firstName);
		}
	}

	@Override
	public String getLastName() {
		if ( this.hasSubject() ) {
			return this.subject.getLastName();
		} else {
			return "";
		}
	}

	@Override
	public void setLastName(String lastName) {
		if ( this.hasSubject() ) {
			this.subject.setLastName(lastName);
		}	
	}

	@Override
	public String getEmail() {
		if ( this.hasSubject() ) {
			return this.subject.getEmail();
		} else {
			return "";
		}
	}

	@Override
	public void setEmail(String email) {
		if ( this.hasSubject() ) {
			this.subject.setEmail(email);
		}		
	}

	@Override
	public int getRoleId() {
		if ( this.hasSubject() ) {
			return this.subject.getRoleId();
		} else {
			return 0;
		}
	}

	@Override
	public void setRoleId(int roleId) {
		if ( this.hasSubject() ) {
			this.subject.setRoleId(roleId);
		}
	}
	
	
	
	
	
	@Override
	public String getSecret() {
		if ( this.hasSubject() ) {
			return this.subject.getSecret();
		} else {
			return "null";
		}
	}

	@Override
	public void setSecret(String secret) {
		if ( this.hasSubject() ) {
			this.subject.setSecret( secret );
		}
		
	}

	/**
	 * returns the User's name formatted as a single String
	 * for compact displaying purposes
	 * 
	 * @return String
	 */
	public String getDisplayName()
	{
		if ( this.hasSubject() ) {
			return String.format("%s %s", 
				this.subject.getFirstName(), 
				this.subject.getLastName()
			);
		} else { 
			return "";
		}
	}
	
	/**
	 * compares the credential passed to the credential of
	 * the encapsulated object and returns true
	 * if there is a match;
	 * 
	 * @param String credential
	 * 
	 * @return boolean
	 */
	public boolean checkCredential(String credential) {
		if ( this.hasSubject() ) {
			String password = this.getPassword();
			String secret = this.getSecret();
			
			String hash = Encryption.encrypt(credential, secret);
			
			return password.equals( hash );
		} else {
			return false;
		}
	}
	
	/**
	 * returns the UserRoleInterface associated
	 * with the encapsulated UserInterface object
	 * 
	 * @return UserRoleInterface
	 */
	public UserRoleInterface getRole()
	{
		int roleId = 0;
		
		if ( this.hasSubject() ) {
			roleId = this.subject.getRoleId();
			
			if ( roleId > 0 ) {
				return BeanManager.getUserRole( String.valueOf(roleId) );
			}
		}
		
		return null;
	}
	
	/**
	 * sets the UserRole reference of a User
	 * object to point to the UserRoleInterface
	 * passed in the database
	 * 
	 * will only set the role if it has an ID value
	 * and has therefore been persisted in the database
	 * 
	 * @param UserRoleInterface
	 */
	public void setRole(UserRoleInterface role)
	{
		if ( this.hasSubject() ) {
			if ( role.getId() > 0 ) {
				this.subject.setRoleId( role.getId() );
			}
		}
	}
	
	/**
	 * returns all reimbursements for which the
	 * encapsulated User is the author
	 * 
	 * @return List<ReimbursementInterface>
	 */
	public List<ReimbursementInterface> getReimbursements()
	{
		if ( this.hasSubject() ) {
			return BeanManager.getReimbursementsByAuthor( this.subject );
		} else {
			return new ArrayList<ReimbursementInterface>();
		}
	}
	
	/**
	 * returns all reimbursements for which the
	 * encapsulated User is the resolver
	 * 
	 * @return List<ReimbursementInterface>
	 */
	public List<ReimbursementInterface> getResolvedReimbursements()
	{
		if ( this.hasSubject() ) {
			return BeanManager.getReimbursementsByResolver( this.subject );
		} else {
			return new ArrayList<ReimbursementInterface>();
		}
	}
	
	@Override
	public Map<String, Object> getPropertyMap() 
	{
		Map<String, Object> data = new HashMap<>();
		
		data.put("id", this.getId() );
		data.put("displayName", this.getDisplayName() );
		data.put("identity", this.getIdentity() );
		data.put("lastName", this.getLastName() );
		data.put("firstName", this.getFirstName() );
		data.put("email", this.getEmail() );
		data.put("role", this.getRolePropertyMap() );
		
		return data;
	}
	
	/**
	 * returns the property map for the attached
	 * role
	 * 
	 * @return String (JSON formatted)
	 */
	private Map<String, Object> getRolePropertyMap()
	{
		UserRoleInterface role = this.getRole();
		UserRoleWrapper roleWrapper = new UserRoleWrapper();
		
		roleWrapper.setSubject( role );
		
		return roleWrapper.getPropertyMap();
	}

}

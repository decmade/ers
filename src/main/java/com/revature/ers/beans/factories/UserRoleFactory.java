package com.revature.ers.beans.factories;

import java.util.Map;

import com.revature.ers.beans.UserRole;
import com.revature.ers.beans.interfaces.UserRoleInterface;

/**
 * << singleton >>
 * 
 * creates hydrated instances of UserRole objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UserRoleFactory extends AbstractFactory<UserRoleInterface>
{
	/**
	 * instantiate singleton instance
	 */
	private static UserRoleFactory instance = new UserRoleFactory();
	
	/**
	 * returns singleton instance
	 * 
	 * @return UserRoleFactory
	 */
	public static UserRoleFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private UserRoleFactory()
	{
		super();
	}

	@Override
	public UserRole create(Map<String, String> data) 
	{
		UserRole role = new UserRole();
		
		log.debug("instantiating UserRole object with Map<String,String>");
		
		data.forEach((key, value) -> {
			switch( key.toLowerCase() ) {
				case "id" :
					role.setId( Integer.parseInt(value) );
					break;
				case "description" :
					role.setDescription(value);
					break;
			}
		});
		
		return role;
	}
	
}

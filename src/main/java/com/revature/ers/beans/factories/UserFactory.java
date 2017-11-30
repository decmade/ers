package com.revature.ers.beans.factories;

import java.util.Map;

import com.revature.ers.beans.User;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.io.Encryption;

/**
 * << factory, singleton >>
 * 
 * creates hydrated instances of User objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UserFactory extends AbstractFactory<UserInterface>
{
	/**
	 * instantiate singleton instance
	 */
	private static UserFactory instance = new UserFactory();
	
	/**
	 * retrieves singleton instance
	 * 
	 * @return UserFactory
	 */
	public static UserFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private UserFactory()
	{
		super();
	}

	@Override
	public UserInterface create(Map<String, String> data) 
	{
		UserInterface user = new User();
		
		log.debug("instantiating User object with Map<String,String>");
		

		data.forEach((key, value) -> {
			switch( key.toLowerCase() ) {
				case "id" :
					user.setId( Integer.parseInt(value) );
					break;
				case "identity" :
				case "username" :
				case "name" :
					user.setIdentity( value );
					break;
				case "password" :
					/*
					 * Oracle revealed the weakness in the approach here
					 * there the column names as retrieved fromt he database
					 * may be in all upper and it is therefore difficult to
					 * look for one in particular like the secret field
					 */
					String secret = this.getSecretValue(data);
					
					/* 
					 * if user has no secret defined already, generate one
					 * and encrypt the password value with it
					 */
					if ( secret == null || secret.isEmpty() ) {
						secret = Encryption.generateKey();
						value = Encryption.encrypt(value, secret);
						
						user.setSecret(secret);
					}
					
					user.setPassword( value );
					break;
				case "secret" :
					user.setSecret( value);
					break;
				case "firstname" :
					user.setFirstName( value );
					break;
				case "lastname" :
					user.setLastName( value );
					break;
				case "email" :
					user.setEmail( value );
					break;
				case "roleid" :
					user.setRoleId( Integer.parseInt( value ) );
					break;					
			}
		});
		
		return user;
	}
	
	/**
	 * extracts the secret column from the map outside of the property
	 * assignment loop to detect it prior to looping into the password field
	 * and therefore knowing that a secret is already set when we get there
	 * 
	 *  -> thanks Oracle
	 *  
	 * @param Map<String, String> data
	 * 
	 * @return String
	 */
	private String getSecretValue(Map<String, String> data ) {
		String secret = "";
		Object[] keySet = data.keySet().toArray();
		
		for(int i = 0; i < keySet.length; i++ ) {
			String key = (String)keySet[i];
			
			if ( "secret".equalsIgnoreCase(key) ) {
				secret = data.get(key);
			}
		}

		return secret;
		
	}
}

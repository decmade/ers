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
					String secret = data.get("secret");
					
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
}

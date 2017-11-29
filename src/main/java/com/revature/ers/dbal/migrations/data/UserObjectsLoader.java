package com.revature.ers.dbal.migrations.data;

import com.revature.ers.beans.factories.UserFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.UserInterface;

/**
 * << singleton >>
 * 
 * loads a collection of data for User objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UserObjectsLoader extends AbstractObjectsLoader<UserInterface> 
{
	/**
	 * instantiate singleton instance
	 */
	private static UserObjectsLoader instance = new UserObjectsLoader();
	
	/**
	 * returns singleton isntance
	 * 
	 * @return
	 */
	public static UserObjectsLoader getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private UserObjectsLoader()
	{
		super();
	}

	@Override
	protected FactoryInterface<UserInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = UserFactory.getInstance();
		}
		
		return this.factory;
	}

	@Override
	protected String[] getColumns() {
		return new String[] {
			"identity",
			"firstName",
			"lastName",
			"email",
			"roleId",
			"password"
		};
	}

	@Override
	protected String[][] getDataArray() {
		log.trace("loading User objects defined by UserObjecstLoader");
		
		return new String[][] {
			{"brownj", "John", "Brown", "jbrown@email.com", "1", "password"},
			{"kruppab", "Blake", "Kruppa", "bkruppa@email.com", "3", "password"},
			{"fayd", "David", "Fay", "dfay@email.com", "2", "password"},
			{"smithd", "David", "Smith", "dsmith@email.com", "2", "password"},
			{"joness", "Sarah", "Jones", "joness@email.com", "2", "password"},
			{"sandersd", "Deon", "Sanders", "sandersd@email.com", "2", "password"},
			{"hoopert", "Tonya", "Hooper", "hoopert@email.com", "2", "password"},
			{"darkod", "Donnie", "Darko", "darkod@email.com", "2", "password"},
		};
	}
	
}

package com.revature.ers.dbal.migrations.data;

import com.revature.ers.beans.factories.UserRoleFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.UserRoleInterface;

public class UserRoleObjectsLoader extends AbstractObjectsLoader<UserRoleInterface>
{
	/*
	 * instantiate singleton instance
	 */
	private static UserRoleObjectsLoader instance = new UserRoleObjectsLoader();
	
	/**
	 * returns singleton instance
	 * 
	 * @return UserRoleObjectsLoader
	 */
	public static UserRoleObjectsLoader getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private UserRoleObjectsLoader()
	{
		super();
	}

	@Override
	protected FactoryInterface<UserRoleInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = UserRoleFactory.getInstance();
		}

		return this.factory;
	}

	@Override
	protected String[] getColumns() {
		return new String[] {
			"description"
		};
	}

	@Override
	protected String[][] getDataArray() {
		return new String[][] {
			{ "System Administrator"},
			{ "Normal User" },
			{ "Finance Manager" },
		};
	}

}

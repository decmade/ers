package com.revature.ers.dbal.migrations.data;

import com.revature.ers.beans.factories.ReimbursementTypeFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReimbursementTypeInterface;

/**
 * << singleton >>
 * 
 * loads a collection of data for ReimbursementType objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementTypeObjectsLoader extends AbstractObjectsLoader<ReimbursementTypeInterface>
{
	/*
	 * initialize singleton instance
	 */
	private static ReimbursementTypeObjectsLoader instance = new ReimbursementTypeObjectsLoader();
	
	/**
	 * returns singleton instance
	 * 
	 * @return ReimbursementTypeObjectsLoader
	 */
	public static ReimbursementTypeObjectsLoader getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementTypeObjectsLoader()
	{
		super();
	}

	@Override
	protected FactoryInterface<ReimbursementTypeInterface> getFactory() {
		if ( this.factory == null ) {
			this.factory = ReimbursementTypeFactory.getInstance();
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
			{ "Lodging" },
			{ "Travel" },
			{ "Food" },
			{ "Other" }
		};
	}

}

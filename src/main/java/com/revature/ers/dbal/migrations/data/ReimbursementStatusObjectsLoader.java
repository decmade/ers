package com.revature.ers.dbal.migrations.data;

import com.revature.ers.beans.factories.ReimbursementStatusFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;

/**
 * << singleton >>
 * 
 * loads a collection of data for ReimbursementType objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementStatusObjectsLoader extends AbstractObjectsLoader<ReimbursementStatusInterface>
{
	/*
	 * initialize singleton instance
	 */
	private static ReimbursementStatusObjectsLoader instance = new ReimbursementStatusObjectsLoader();
	
	/**
	 * returns singleton instance
	 * 
	 * @return ReimbursementStatusObjectsLoader
	 */
	public static ReimbursementStatusObjectsLoader getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementStatusObjectsLoader()
	{
		super();
	}

	@Override
	protected FactoryInterface<ReimbursementStatusInterface> getFactory() {
		if ( this.factory == null ) {
			this.factory = ReimbursementStatusFactory.getInstance();
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
			{ "Pending" },
			{ "Approved" },
			{ "Denied" },
		};
	}

}

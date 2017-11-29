package com.revature.ers.dbal.migrations.data;

import java.time.LocalDateTime;

import com.revature.ers.beans.factories.ReimbursementFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReimbursementInterface;

/**
 * << singleton >>
 * 
 * loads a collection of data for Reimbursement objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementObjectsLoader extends AbstractObjectsLoader<ReimbursementInterface> 
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReimbursementObjectsLoader instance = new ReimbursementObjectsLoader();
	
	/**
	 * returns the singleton instance
	 * 
	 * @return ReimbursementObjectsLoader
	 */
	public static ReimbursementObjectsLoader getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementObjectsLoader()
	{
		super();
	}

	@Override
	protected FactoryInterface<ReimbursementInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = ReimbursementFactory.getInstance();
		}
		
		return this.factory;
	}

	@Override
	protected String[] getColumns() 
	{
		return new String[] {
			"description",
			"amount",
			"typeid",
			"statusid",
			"authorid",
			"resolverid",
			"receiptid",
			"resolved",
			"submitted"
		};
	}

	@Override
	protected String[][] getDataArray() 
	{
		return new String[][] {
			{"Test Reimbursement", "50.00", "1", "1", "5", "", "1", "", String.valueOf( LocalDateTime.now() ) },
			{"Test Reimbursement", "500.00", "4", "1", "3", "", "2", "", String.valueOf( LocalDateTime.now() ) },
			{"Test Reimbursement", "750.00", "2", "1", "7", "", "3", "", String.valueOf( LocalDateTime.now() ) },
			{"Test Reimbursement", "70.00", "3", "1", "2", "", "4", "", String.valueOf( LocalDateTime.now() ) },
			{"Test Reimbursement", "73.03", "3", "1", "2", "", "5", "", String.valueOf( LocalDateTime.now() ) },
		};
	}
	

}

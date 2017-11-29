package com.revature.ers.beans.factories;

import java.util.Map;

import com.revature.ers.beans.ReimbursementStatus;
import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;

/**
 * << factory, singleton >>
 * 
 * creates hydrated instances of ReimbursementStatus objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementStatusFactory extends AbstractFactory<ReimbursementStatusInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReimbursementStatusFactory instance = new ReimbursementStatusFactory();
	
	/**
	 * returns the singleton instance
	 * @return
	 */
	public static ReimbursementStatusFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementStatusFactory()
	{
		super();
	}

	@Override
	public ReimbursementStatusInterface create(Map<String, String> data) 
	{
		ReimbursementStatusInterface status = new ReimbursementStatus();
		
		log.debug("instantiating ReimbursementStatus object with Map<String,String>");
		
		data.forEach((key, value) -> {
			switch( key.toLowerCase() ) {
				case "id" :
					status.setId( Integer.parseInt(value) );
					break;
				case "description" :
					status.setDescription( value );
					break;
			}
		});
		
		return status;
	}
	
}

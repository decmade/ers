package com.revature.ers.beans.factories;

import java.util.Map;

import com.revature.ers.beans.ReimbursementType;
import com.revature.ers.beans.interfaces.ReimbursementTypeInterface;

/**
 * << factory, singleton >>
 * 
 * creates hydrated instances of ReimbursementType objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementTypeFactory extends AbstractFactory<ReimbursementTypeInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReimbursementTypeFactory instance = new ReimbursementTypeFactory();
	
	/**
	 * returns the singleton instance
	 * @return
	 */
	public static ReimbursementTypeFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementTypeFactory()
	{
		super();
	}

	@Override
	public ReimbursementTypeInterface create(Map<String, String> data) 
	{
		ReimbursementTypeInterface type = new ReimbursementType();
		
		log.debug("instantiating ReimbursementType object with Map<String,String>");
		
		data.forEach((key, value) -> {
			switch( key.toLowerCase() ) {
				case "id" :
					type.setId( Integer.parseInt(value) );
					break;
				case "description" :
					type.setDescription( value );
					break;
			}
		});
		
		return type;
	}
	
}

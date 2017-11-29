package com.revature.ers.beans.factories;

import java.util.Map;

import com.revature.ers.beans.Receipt;
import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.dbal.DateTimeConverter;

/**
 * << factory, singleton >>
 * 
 * creates hydrated instances of Receipt objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReceiptFactory extends AbstractFactory<ReceiptInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReceiptFactory instance = new ReceiptFactory();
	
	/**
	 * returns the singleton instance
	 * @return
	 */
	public static ReceiptFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReceiptFactory()
	{
		super();
	}

	@Override
	public ReceiptInterface create(Map<String, String> data) 
	{
		ReceiptInterface receipt = new Receipt();
		
		log.debug("instantiating Receipt object with Map<String,String>");
		
		data.forEach((key, value) -> {
			switch( key.toLowerCase() ) {
				case "id" :
					receipt.setId( Integer.parseInt(value) );
					break;
				case "filename" :
					receipt.setFileName( value );
					break;
				case "created" :
					receipt.setCreated( DateTimeConverter.toLocalDateTime(value) );
					break;
			}
		});
		
		return receipt;
	}
	
}

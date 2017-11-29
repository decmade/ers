package com.revature.ers.dbal.migrations.data;

import java.time.LocalDateTime;

import com.revature.ers.beans.factories.ReceiptFactory;
import com.revature.ers.beans.interfaces.FactoryInterface;
import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.dbal.DateTimeConverter;

public class ReceiptObjectsLoader extends AbstractObjectsLoader<ReceiptInterface>
{
	/*
	 * instantiate singleton instance
	 */
	private static ReceiptObjectsLoader instance = new ReceiptObjectsLoader();
	
	/**
	 * returns singleton instance
	 * 
	 * @return UserRoleObjectsLoader
	 */
	public static ReceiptObjectsLoader getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReceiptObjectsLoader()
	{
		super();
	}

	@Override
	protected FactoryInterface<ReceiptInterface> getFactory() 
	{
		if ( this.factory == null ) {
			this.factory = ReceiptFactory.getInstance();
		}

		return this.factory;
	}

	@Override
	protected String[] getColumns() {
		return new String[] {
			"fileName",
			"created"
		};
	}

	@Override
	protected String[][] getDataArray() {
		return new String[][] {
			{ "1.pdf", DateTimeConverter.toString( LocalDateTime.now() ) },
			{ "2.pdf", DateTimeConverter.toString( LocalDateTime.now() ) },
			{ "3.pdf", DateTimeConverter.toString( LocalDateTime.now() ) },
			{ "4.pdf", DateTimeConverter.toString( LocalDateTime.now() ) },
			{ "5.pdf", DateTimeConverter.toString( LocalDateTime.now() ) }
		};
	}

}

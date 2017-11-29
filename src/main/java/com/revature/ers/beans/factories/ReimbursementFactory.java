package com.revature.ers.beans.factories;

import java.util.Map;

import com.revature.ers.beans.Reimbursement;
import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.dbal.DateTimeConverter;

/**
 * << factory, singleton >>
 * 
 * creates hydrated instances of Reimbursement objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementFactory extends AbstractFactory<ReimbursementInterface>
{
	/*
	 * instantiate the singleton instance
	 */
	private static ReimbursementFactory instance = new ReimbursementFactory();
	
	/**
	 * returns singleton instance
	 * 
	 * @return ReimbursementFactory
	 */
	public static ReimbursementFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * hidden constructor
	 */
	private ReimbursementFactory()
	{
		super();
	}

	@Override
	public ReimbursementInterface create(Map<String, String> data) 
	{
		ReimbursementInterface reimbursement = new Reimbursement();
		
		log.debug("instantiating Reimbursement object with Map<String,String>");
		
		data.forEach( (key, value) -> {
			switch( key.toLowerCase() ) {
				case "id" :
					reimbursement.setId( Integer.parseInt( value ) );
					break;
				case "amount" :
					reimbursement.setAmount( Double.parseDouble( value ));
					break;
				case "authorid" :
					reimbursement.setAuthorId( Integer.parseInt( value ));
					break;
				case "description" :
					reimbursement.setDescription( value );
					break;
				case "receiptid" :
					reimbursement.setReceiptId( Integer.parseInt( value ) );
					break;
				case "resolved" :
					if ( value.isEmpty() == false ) {
						reimbursement.setResolved( DateTimeConverter.toLocalDateTime(value) );
					}
					
					break;
				case "resolverid" :
					if ( value.isEmpty() == false ) {
						reimbursement.setResolverId( Integer.parseInt( value ));
					}
					
					break;
				case "statusid" :
					reimbursement.setStatusId( Integer.parseInt( value ) );
					break;
				case "submitted" :
					reimbursement.setSubmitted( DateTimeConverter.toLocalDateTime(value) );
					break;
				case "typeid" :
					reimbursement.setTypeId( Integer.parseInt( value ) );
					break;
			}
		});
		
		return reimbursement;
	}
	

}

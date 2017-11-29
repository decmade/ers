package com.revature.ers.beans.wrappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.controllers.ReceiptFileController;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.DateTimeConverter;

/**
 * << decorator >>
 * 
 * decorates a Receipt object to add functionality
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReceiptWrapper extends AbstractWrapper<ReceiptInterface> implements ReceiptInterface
{
	@Override
	public int getId() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getId();
		} else {
			return 0;
		}
	}

	@Override
	public void setId(int id) 
	{
		if ( this.hasSubject() ) {
			this.subject.setId(id);
		}
	}

	@Override
	public String getFileName() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getFileName();
		} else {
			return "";
		}
	}

	@Override
	public void setFileName(String fileName) 
	{
		if ( this.hasSubject() ) {
			this.subject.setFileName(fileName);
		}
	}

	@Override
	public LocalDateTime getCreated() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getCreated();
		} else {
			return null;
		}
	}

	@Override
	public void setCreated(LocalDateTime created) 
	{
		if ( this.hasSubject() ) {
			this.subject.setCreated(created);
		}
	}
	

	/**
	 * returns the file extension of the file name
	 * 
	 * @return String
	 */
	public String getFileExtension() {
		if ( this.hasSubject() ) {
			String[] parts = this.subject.getFileName().split("\\.");
			return parts[ parts.length - 1].toLowerCase();
		} else {
			return "";
		}
	}
	

	@Override
	public Map<String, Object> getPropertyMap() 
	{
		Map<String, Object> data = new HashMap<>();
		
		data.put("id", this.getId() );
		data.put("fileName", this.getFileName() );
		data.put("created", DateTimeConverter.toString( this.getCreated() ) );
		data.put("url", String.format("%s%d", ReceiptFileController.URL_ROOT, this.getId() ) );
		
		return data;
	}
	
	/**
	 * retrieves first Reimbursement that has the
	 * encapsulated receipt attached to it
	 * 
	 * @return ReimbursementInterface
	 */
	public ReimbursementInterface getReimbursement() 
	{
		List<ReimbursementInterface> reimbursements = new ArrayList<>();
		
		if ( this.hasSubject() ) {
			reimbursements = BeanManager.getReimbursementsByReceipt( this.subject );
			
			if ( reimbursements.size() > 0 ) {
				return reimbursements.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	

}

package com.revature.ers.beans.wrappers;

import java.util.HashMap;
import java.util.Map;

import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;

/**
* << decorator >>
* 
* decorates a ReimbursementStatusInterface object to add functionality
* 
* @author john.w.brown.jr@gmail.com
*
*/
public class ReimbursementStatusWrapper extends AbstractWrapper<ReimbursementStatusInterface> implements ReimbursementStatusInterface
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
	public String getDescription() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getDescription();
		} else {
			return "";
		}
	}

	@Override
	public void setDescription(String description) 
	{
		if ( this.hasSubject() ) {
			this.subject.setDescription(description);
		}
	}
	
	@Override
	public Map<String, Object> getPropertyMap() 
	{
		Map<String, Object> data = new HashMap<>();
		
		data.put("id", this.getId() );
		data.put("description", this.getDescription() );
		
		return data;
	}
	
}

package com.revature.ers.beans.wrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.beans.interfaces.UserRoleInterface;
import com.revature.ers.dbal.BeanManager;

/**
 * << decorator >>
 * 
 * decorates a UserRoleInterface object to add functionality
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class UserRoleWrapper extends AbstractWrapper<UserRoleInterface> implements UserRoleInterface
{
	@Override
	public int getId() {
		if ( this.hasSubject() ) {
			return this.subject.getId();
		} else {
			return 0;
		}
	}

	@Override
	public void setId(int id) {
		if ( this.hasSubject() ) {
			this.subject.setId(id);
		}		
	}

	@Override
	public String getDescription() {
		if ( this.hasSubject() ) {
			return this.subject.getDescription();
		} else {
			return "";
		}
	}

	@Override
	public void setDescription(String description) {
		if ( this.hasSubject() ) {
			this.subject.setDescription(description);
		}
	}
	
	/**
	 * returns all Users assigned to this Role
	 * 
	 * @return List<UserInterface>
	 */
	public List<UserInterface> getUsers()
	{
		if ( this.hasSubject() ) {
			return BeanManager.getUsersByRole( this.subject );
		} else {
			return new ArrayList<UserInterface>();
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

package com.revature.ers.beans;

import com.revature.ers.beans.interfaces.UserRoleInterface;

public class UserRole implements UserRoleInterface
{
	private int id;
	private String description;
	
	
	@Override
	public int getId() 
	{
		return id;
	}
	
	@Override
	public void setId(int id) 
	{
		this.id = id;
	}
	
	@Override
	public String getDescription() 
	{
		return description;
	}
	
	@Override
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
}

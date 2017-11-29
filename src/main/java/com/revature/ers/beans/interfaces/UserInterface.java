package com.revature.ers.beans.interfaces;

public interface UserInterface 
{
	public int getId();
	
	public void setId(int id);
	
	public String getIdentity();
	
	public void setIdentity(String name);
	
	public String getPassword();
	
	public void setPassword(String password);
	
	public String getFirstName();
	
	public void setFirstName(String firstName);
	
	public String getLastName();
	
	public void setLastName(String lastName);
	
	public String getEmail();
	
	public void setEmail(String email);
	
	public int getRoleId();
	
	public void setRoleId(int roleId);
}

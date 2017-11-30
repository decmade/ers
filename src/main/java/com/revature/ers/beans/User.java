package com.revature.ers.beans;

import com.revature.ers.beans.interfaces.UserInterface;

public class User implements UserInterface
{
	private int id;
	private String identity;
	private String password;
	private String secret;
	private String firstName;
	private String lastName;
	private String email;
	private int roleId;
	
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
	public String getIdentity() 
	{
		return identity;
	}
	
	@Override
	public void setIdentity(String name) 
	{
		this.identity = name;
	}
	
	@Override
	public String getPassword() 
	{
		return password;
	}
	
	@Override
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	@Override
	public String getFirstName() 
	{
		return firstName;
	}
	
	@Override
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	@Override
	public String getLastName() 
	{
		return lastName;
	}
	
	@Override
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	@Override
	public String getEmail() 
	{
		return email;
	}
	
	@Override
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	@Override
	public int getRoleId() 
	{
		return roleId;
	}
	
	@Override
	public void setRoleId(int roleId) 
	{
		this.roleId = roleId;
	}

	@Override
	public String getSecret() {
		return secret;
	}

	@Override
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
}

package com.revature.ers.beans;

import java.time.LocalDateTime;

import com.revature.ers.beans.interfaces.ReceiptInterface;

public class Receipt implements ReceiptInterface
{
	private int id;
	private String fileName;
	private LocalDateTime created;
	
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
	public String getFileName() 
	{
		return fileName;
	}
	
	@Override
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	@Override
	public LocalDateTime getCreated() 
	{
		return created;
	}
	
	@Override
	public void setCreated(LocalDateTime created) 
	{
		this.created = created;
	}
	
	
}

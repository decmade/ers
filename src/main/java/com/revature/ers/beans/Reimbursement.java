package com.revature.ers.beans;

import java.time.LocalDateTime;

import com.revature.ers.beans.interfaces.ReimbursementInterface;

public class Reimbursement implements ReimbursementInterface
{
	private int id;
	private double amount;
	private LocalDateTime submitted;
	private LocalDateTime resolved;
	private String description;
	private int receiptId;
	private int authorId;
	private int resolverId;
	private int statusId;
	private int typeId;
	
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
	public double getAmount() 
	{
		return amount;
	}
	
	@Override
	public void setAmount(double amount) 
	{
		this.amount = amount;
	}
	
	@Override
	public LocalDateTime getSubmitted() 
	{
		return submitted;
	}
	
	@Override
	public void setSubmitted(LocalDateTime submitted) 
	{
		this.submitted = submitted;
	}
	
	@Override
	public LocalDateTime getResolved() 
	{
		return resolved;
	}
	
	@Override
	public void setResolved(LocalDateTime resolved) 
	{
		this.resolved = resolved;
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
	
	@Override
	public int getReceiptId() 
	{
		return receiptId;
	}
	
	@Override
	public void setReceiptId(int receiptId) 
	{
		this.receiptId = receiptId;
	}
	
	@Override
	public int getAuthorId() 
	{
		return authorId;
	}
	
	@Override
	public void setAuthorId(int authorId) 
	{
		this.authorId = authorId;
	}
	
	@Override
	public int getResolverId() 
	{
		return resolverId;
	}
	
	@Override
	public void setResolverId(int resolverId) 
	{
		this.resolverId = resolverId;
	}
	
	@Override
	public int getStatusId() 
	{
		return statusId;
	}
	
	@Override
	public void setStatusId(int statusId) 
	{
		this.statusId = statusId;
	}
	
	@Override
	public int getTypeId() 
	{
		return typeId;
	}
	
	@Override
	public void setTypeId(int typeId) 
	{
		this.typeId = typeId;
	}
	
	
}

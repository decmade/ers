package com.revature.ers.beans.interfaces;

import java.time.LocalDateTime;

public interface ReimbursementInterface 
{
	public int getId();
	
	public void setId(int id);
	
	public double getAmount();
	
	public void setAmount(double amount);
	
	public LocalDateTime getSubmitted();
	
	public void setSubmitted(LocalDateTime submitted);
	
	public LocalDateTime getResolved();
	
	public void setResolved(LocalDateTime resolved);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public int getReceiptId();
	
	public void setReceiptId(int receiptId);
	
	public int getAuthorId();
	
	public void setAuthorId(int authorId);
	
	public int getResolverId();
	
	public void setResolverId(int resolverId);
	
	public int getStatusId();
	
	public void setStatusId(int statusId);
	
	public int getTypeId();
	
	public void setTypeId(int typeId);
}

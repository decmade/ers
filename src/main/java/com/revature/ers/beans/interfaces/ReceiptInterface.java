package com.revature.ers.beans.interfaces;

import java.time.LocalDateTime;

public interface ReceiptInterface 
{
	public int getId();
	public void setId(int id);
	public String getFileName();
	public void setFileName(String fileName);
	public LocalDateTime getCreated();
	public void setCreated(LocalDateTime created);
}

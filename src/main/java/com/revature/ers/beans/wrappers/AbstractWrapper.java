package com.revature.ers.beans.wrappers;

import java.util.Map;

import com.revature.ers.beans.interfaces.WrapperInterface;

/**
 * template for WrapperInterface objects
 * 
 * @author john.w.brown.jr@gmail.com
 *
 * @param <T>
 */
abstract public class AbstractWrapper<T> implements WrapperInterface<T>
{
	protected T subject;
		
	public AbstractWrapper()
	{
		this.subject = null;
	}
	
	@Override
	abstract public Map<String, Object> getPropertyMap();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void setSubject(T subject) 
	{
		/*
		 * prevent infinite reference loop of setting subject if wrapper
		 * decorates another wrapper of the same type
		 */
		if ( WrapperInterface.class.isInstance(subject) ) {
			this.subject = ((WrapperInterface<T>) subject).getSubject();
		} else {
			this.subject = subject;
		}
	}

	@Override
	public T getSubject() 
	{
		return this.subject;
	}

	@Override
	public boolean hasSubject() 
	{
		return ( this.subject != null );
	}	

}

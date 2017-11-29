package com.revature.ers.beans.wrappers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.beans.interfaces.ReimbursementInterface;
import com.revature.ers.beans.interfaces.ReimbursementStatusInterface;
import com.revature.ers.beans.interfaces.ReimbursementTypeInterface;
import com.revature.ers.beans.interfaces.UserInterface;
import com.revature.ers.dbal.BeanManager;
import com.revature.ers.dbal.DateTimeConverter;

/**
 * << decorator >>
 * 
 * decorates a ReimbursementInterface object to add functionality
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReimbursementWrapper extends AbstractWrapper<ReimbursementInterface> implements ReimbursementInterface 
{
	public static String STATE_UPDATE = "update";
	public static String STATE_APPROVE = "approve";
	public static String STATE_CREATE = "create";
	public static String STATE_VIEW = "view";
	
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
	public double getAmount() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getAmount();
		} else {
			return 0.00d;
		}
	}

	@Override
	public void setAmount(double amount) 
	{
		if ( this.hasSubject() ) {
			this.subject.setAmount(amount);
		} 
	}

	@Override
	public LocalDateTime getSubmitted() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getSubmitted();
		} else {
			return null;
		}
	}

	@Override
	public void setSubmitted(LocalDateTime submitted) 
	{
		if ( this.hasSubject() ) {
			this.subject.setSubmitted(submitted);
		}
	}

	@Override
	public LocalDateTime getResolved() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getResolved();
		} else {
			return null;
		}
	}

	@Override
	public void setResolved(LocalDateTime resolved) 
	{
		if ( this.hasSubject() ) {
			this.subject.setResolved(resolved);
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
	public int getReceiptId() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getReceiptId();
		} else {
			return 0;
		}
	}

	@Override
	public void setReceiptId(int receiptId) 
	{
		if ( this.hasSubject() ) {
			this.subject.setReceiptId(receiptId);
		}
	}

	@Override
	public int getAuthorId() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getAuthorId();
		} else {
			return 0;
		}
	}

	@Override
	public void setAuthorId(int authorId) 
	{
		if ( this.hasSubject() ) {
			this.subject.setAuthorId(authorId);
		}		
	}

	@Override
	public int getResolverId() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getResolverId();
		} else {
			return 0;
		}
	}

	@Override
	public void setResolverId(int resolverId) 
	{
		if ( this.hasSubject() ) {
			this.subject.setResolverId(resolverId);
		}
	}

	@Override
	public int getStatusId() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getStatusId();
		} else {
			return 0;
		}
	}

	@Override
	public void setStatusId(int statusId) 
	{
		if ( this.hasSubject() ) {
			this.subject.setStatusId(statusId);
		}
	}

	@Override
	public int getTypeId() 
	{
		if ( this.hasSubject() ) {
			return this.subject.getTypeId();
		} else {
			return 0;
		}
	}

	@Override
	public void setTypeId(int typeId) 
	{
		if ( this.hasSubject() ) {
			this.subject.setTypeId(typeId);
		}
	}
	
	/**
	 * returns the ReimbursementStatusInterface object associated with the 
	 * encapsulated ReimbursementInterface object
	 * 
	 * @return ReimbursementStatusInterface
	 */
	public ReimbursementStatusInterface getStatus()
	{
		int statusId;
		
		if ( this.hasSubject() ) {
			statusId = this.subject.getStatusId();
			
			if ( statusId > 0 ) {
				return BeanManager.getReimbursementStatus( String.valueOf(statusId ) );
			}
		} 
		
		return null;
	}
	
	/**
	 * sets the Reimbursement object's reference
	 * to a ReimbursementStatusInterface to match
	 * the ID of the object passed
	 * 
	 * @param ReimbursementStatusInterface status
	 */
	public void setStatus(ReimbursementStatusInterface status)
	{
		if ( this.hasSubject() ) {
			if ( status.getId() > 0 ) {
				this.subject.setStatusId( status.getId() );
			}
		} 
	}
	
	/**
	 * returns the ReimbursementTypeInterface object associated with the 
	 * encapsulated ReimbursementInterface object
	 * 
	 * @return ReimbursementTypeInterface
	 */
	public ReimbursementTypeInterface getType()
	{
		int typeId;
		
		if ( this.hasSubject() ) {
			typeId = this.subject.getTypeId();
			
			if ( typeId > 0 ) {
				return BeanManager.getReimbursementType( String.valueOf(typeId) );
			}
		} 
		
		return null;
	}
	
	/**
	 * sets the Reimbursement object's reference
	 * to a ReimbursementTypeInterface to match
	 * the ID of the object passed
	 * 
	 * @param ReimbursementTypeInterface type
	 */
	public void setType(ReimbursementTypeInterface type)
	{
		if ( this.hasSubject() ) {
			if ( type.getId() > 0 ) {
				this.subject.setTypeId( type.getId() );
			}
		} 
	}
	
	/**
	 * returns the UserInterface object associated with the 
	 * authorId of the encapsulated ReimbursementInterface object
	 * 
	 * @return UserInterface
	 */
	public UserInterface getAuthor()
	{
		int authorId;
		
		if ( this.hasSubject() ) {
			authorId = this.subject.getAuthorId();
			
			if ( authorId > 0 ) {
				return BeanManager.getUser( String.valueOf(authorId) );
			}
		} 
		
		return null;
	}
	
	/**
	 * sets the Reimbursement object's reference
	 * to an Author to match
	 * the ID of the object passed
	 * 
	 * @param UserInterface user
	 */
	public void setAuthor(UserInterface user)
	{
		if ( this.hasSubject() ) {
			if ( user.getId() > 0 ) {
				this.subject.setAuthorId( user.getId() );
			}
		} 
	}
	
	/**
	 * returns the UserInterface object associated with the 
	 * resolverId of the encapsulated ReimbursementInterface object
	 * 
	 * @return UserInterface
	 */
	public UserInterface getResolver()
	{
		int resolverId;
		
		if ( this.hasSubject() ) {
			resolverId = this.subject.getResolverId();
			
			if ( resolverId > 0 ) {
				return BeanManager.getUser( String.valueOf(resolverId) );
			}
		} 
		
		return null;
	}
	
	/**
	 * sets the Reimbursement object's reference
	 * to an Resolver to match
	 * the ID of the object passed
	 * 
	 * @param UserInterface user
	 */
	public void setResolver(UserInterface user)
	{
		if ( this.hasSubject() ) {
			if ( user.getId() > 0 ) {
				this.subject.setResolverId( user.getId() );
			}
		} 
	}
	
	/**
	 * returns the ReceiptInterface object associated with the 
	 * the encapsulated ReimbursementInterface object
	 * 
	 * @return ReceiptInterface
	 */
	public ReceiptInterface getReceipt()
	{
		int receiptId;
		
		if ( this.hasSubject() ) {
			receiptId = this.subject.getReceiptId();
			
			if ( receiptId > 0 ) {
				return BeanManager.getReceipt( String.valueOf(receiptId) );
			}
		} 
		
		return null;
	}
	
	/**
	 * sets the Reimbursement object's reference
	 * to an ReciptInterface to match
	 * the ID of the object passed
	 * 
	 * @param ReceiptInterface receipt
	 */
	public void setReceipt(ReceiptInterface receipt)
	{
		if ( this.hasSubject() ) {
			if ( receipt.getId() > 0 ) {
				this.subject.setReceiptId( receipt.getId() );
			}
		} 
	}
	
	@Override
	public Map<String, Object> getPropertyMap() 
	{
		Map<String, Object> data = new HashMap<>();

		data.put("id", this.getId() );
		data.put("description", this.getDescription() );
		data.put("type", this.getTypePropertyMap() );
		data.put("amount", this.getAmount() );
		data.put("author", this.getAuthorPropertyMap() );
		data.put("submitted", DateTimeConverter.toString( this.getSubmitted() ) );
		data.put("resolver", this.getResolverPropertyMap() );
		data.put("resolved", DateTimeConverter.toString( this.getResolved() ));
		data.put("status", this.getStatusPropertyMap() );
		data.put("receipt", this.getReceiptPropertyMap() );
		
		return data;
	}
	
	/**
	 * returns the property map for the
	 * status attached
	 * 
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getStatusPropertyMap()
	{
		ReimbursementStatusWrapper wrapper = new ReimbursementStatusWrapper();
		
		wrapper.setSubject( this.getStatus() );
		
		return wrapper.getPropertyMap();
	}
	
	/**
	 * returns the property map for the
	 * type attached
	 * 
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getTypePropertyMap()
	{
		ReimbursementTypeWrapper wrapper = new ReimbursementTypeWrapper();
		
		wrapper.setSubject( this.getType() );
		
		return wrapper.getPropertyMap();
	}
	
	/**
	 * returns the property map for the
	 * author attached
	 * 
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getAuthorPropertyMap()
	{
		UserWrapper wrapper = new UserWrapper();
		
		wrapper.setSubject( this.getAuthor() );
		
		return wrapper.getPropertyMap();
	}
	
	/**
	 * returns the property map for the
	 * resolver attached
	 * 
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getResolverPropertyMap()
	{
		UserWrapper wrapper = new UserWrapper();
		
		wrapper.setSubject( this.getResolver() );	
			
		return wrapper.getPropertyMap();
	}
	
	/**
	 * returns the property map for the
	 * receipt attached
	 * 
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getReceiptPropertyMap()
	{
		ReceiptWrapper wrapper = new ReceiptWrapper();
		
		wrapper.setSubject( this.getReceipt() );	
			
		return wrapper.getPropertyMap();
	}

}

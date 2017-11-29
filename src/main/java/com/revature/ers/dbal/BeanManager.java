 package com.revature.ers.dbal;

import java.util.List;

import com.revature.ers.beans.interfaces.*;
import com.revature.ers.dbal.adapters.*;

/**
 * << facade >>
 * 
 * a utility class the proxies calls to the various TableAdapterInterface
 * objects to act on objects represented in the database
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class BeanManager 
{
	/*
	 * UserTableAdapter Facades
	 */
	public static UserInterface getUser(String id)
	{
		return UsersTableAdapter.getInstance().get( id );
	}
	
	public static UserInterface getUserByIdentity(String identity)
	{
		return UsersTableAdapter.getInstance().getByIdentity(identity);
	}
	
	public static List<UserInterface> getUsersByRole( UserRoleInterface role)
	{
		return UsersTableAdapter.getInstance().getByRole( role );
	}
	
	public static List<UserInterface> getAllUsers()
	{
		return UsersTableAdapter.getInstance().getAll();
	}
	
	public static boolean saveUser(UserInterface user)
	{
		return UsersTableAdapter.getInstance().save(user);
	}
	
	public static boolean removeUser(UserInterface user)
	{
		return UsersTableAdapter.getInstance().remove(user);
	}
	
	
	
	
	
	
	/*
	 * UserRoleTableAdapter Facades
	 */

	public static UserRoleInterface getUserRole(String id)
	{
		return UserRolesTableAdapter.getInstance().get( id );
	}
	
	public static List<UserRoleInterface> getAllUserRoles()
	{
		return UserRolesTableAdapter.getInstance().getAll();
	}
	
	public static boolean saveUserRole(UserRoleInterface role)
	{
		return UserRolesTableAdapter.getInstance().save(role);
	}
	
	public static boolean removeUserRole(UserRoleInterface role)
	{
		return UserRolesTableAdapter.getInstance().remove(role);
	}
	
	
	
	
	
	
	/*
	 * ReimbursementTableAdapter Facades
	 */

	public static ReimbursementInterface getReimbursement(String id) 
	{
		return ReimbursementsTableAdapter.getInstance().get( id );
	}
	
	public static List<ReimbursementInterface> getReimbursementsByAuthor(UserInterface author)
	{
		return ReimbursementsTableAdapter.getInstance().getByAuthor( author );
	}
	
	public static List<ReimbursementInterface> getReimbursementsByResolver(UserInterface resolver)
	{
		return ReimbursementsTableAdapter.getInstance().getByResolver( resolver );
	}
	
	public static List<ReimbursementInterface> getReimbursementsByReceipt(ReceiptInterface receipt)
	{
		return ReimbursementsTableAdapter.getInstance().getByReceipt( receipt );
	}
	
	public static List<ReimbursementInterface> getAllReimbursements()
	{
		return ReimbursementsTableAdapter.getInstance().getAll();
	}
	
	public static boolean removeReimbursement(ReimbursementInterface reimbursement) 
	{
		return ReimbursementsTableAdapter.getInstance().remove(reimbursement);
	}
	
	public static boolean saveReimbursement(ReimbursementInterface reimbursement)
	{
		return ReimbursementsTableAdapter.getInstance().save(reimbursement);
	}
	
	
	
	
	
	
	/*
	 * ReimbursementTypeTableAdapter Facades
	 */

	public static ReimbursementTypeInterface getReimbursementType(String id) 
	{
		return ReimbursementTypeTableAdapter.getInstance().get( id );
	}
	
	public static List<ReimbursementTypeInterface> getAllReimbursementTypes()
	{
		return ReimbursementTypeTableAdapter.getInstance().getAll();
	}
	
	public static boolean removeReimbursementType(ReimbursementTypeInterface type) 
	{
		return ReimbursementTypeTableAdapter.getInstance().remove(type);
	}
	
	public static boolean saveUserRole(ReimbursementTypeInterface type)
	{
		return ReimbursementTypeTableAdapter.getInstance().save(type);
	}
	
	
	
	
	
	
	/*
	 * ReimbursementStatusTableAdapter Facades
	 */

	public static ReimbursementStatusInterface getReimbursementStatus(String id) 
	{
		return ReimbursementStatusTableAdapter.getInstance().get( id );
	}
	
	public static List<ReimbursementStatusInterface> getAllReimbursementStatuses()
	{
		return ReimbursementStatusTableAdapter.getInstance().getAll();
	}
	
	public static boolean removeReimbursementStatus(ReimbursementStatusInterface status) 
	{
		return ReimbursementStatusTableAdapter.getInstance().remove(status);
	}
	
	public static boolean saveReimbursementStatus(ReimbursementStatusInterface status)
	{
		return ReimbursementStatusTableAdapter.getInstance().save(status);
	}
	
	
	
	
	
	/*
	 * ReceiptTableAdapter Facades
	 */

	public static ReceiptInterface getReceipt(String id) 
	{
		return ReceiptsTableAdapter.getInstance().get( id );
	}
	
	public static List<ReceiptInterface> getAllReceipts()
	{
		return ReceiptsTableAdapter.getInstance().getAll();
	}
	
	public static boolean removeReceipt(ReceiptInterface receipt) 
	{
		return ReceiptsTableAdapter.getInstance().remove(receipt);
	}
	
	public static boolean saveReceipt(ReceiptInterface receipt)
	{
		return ReceiptsTableAdapter.getInstance().save(receipt);
	}
}

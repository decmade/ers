package com.revature.ers;

import com.revature.ers.dbal.interfaces.MigrationInterface;
import com.revature.ers.dbal.migrations.InitializeAppMigration;

/**
 * @deprecated
 * use the /maintenace URI to run application-wide functions
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
@Deprecated
public class ERSLauncher 
{
	public static void main(String[] args) 
	{
		MigrationInterface migration = new InitializeAppMigration();
		migration.run();
		
//		UserInterface john = BeanManager.getUserByIdentity("brownj");
//		System.out.println( john );
//		
//		UserController controller = UserController.getInstance();
//		System.out.println( controller );
	}
	
}

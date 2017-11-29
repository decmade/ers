package com.revature.ers.dbal.migrations;

import java.util.ArrayList;
import java.util.List;

import com.revature.ers.dbal.interfaces.MigrationInterface;

public class InitializeAppMigration extends AbstractMigration
{
	
	@Override
	public void run() {
		List<MigrationInterface> migrations = this.getMigrations();
		
		log.debug("attempting to initialize data structure for application");
		
		migrations.forEach( (migration) -> {
			migration.run();
		}); 
		
		log.debug("application data structure is completed");
		
	}
	
	private List<MigrationInterface> getMigrations()
	{
		List<MigrationInterface> migrations = new ArrayList<>();
		
		migrations.add( new InitializeUsersTable() );
		migrations.add( new InitializeUserRolesTable() );
		migrations.add( new InitializeReimbursementTypesTable() );
		migrations.add( new InitializeReimbursementStatusTable() );
		migrations.add( new InitializeReimbursementsTable() );
		migrations.add( new InitializeReceiptsTable() );
		
		return migrations;
	}

}

package com.revature.ers.dbal.migrations;

import java.sql.Connection;

import com.revature.ers.dbal.connection.DbConnection;
import com.revature.ers.dbal.interfaces.MigrationInterface;
import com.revature.ers.logger.DbalLogger;
import com.revature.ers.logger.LoggerInterface;

/**
 * template for all migration classes
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
abstract public class AbstractMigration implements MigrationInterface 
{
	protected static LoggerInterface log = DbalLogger.getInstance();
	protected static Connection connection = DbConnection.getInstance().connect();  // persistent database connection

	@Override
	abstract public void run();

}

package runner.input;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;

/**
 * Tests the InserRelationshipHandler.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class TestInserRelationshipHandler
{

	/**
	 * Sets up the environment.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Before
	public void setup() throws ClassNotFoundException, SQLException {
		Database.beginTransaction();
	}
	
	/**
	 * Rollbacks the data.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@After
	public void teardown() throws ClassNotFoundException, SQLException {
		Database.rollbackTransaction();
	}
	
	/**
	 * Tests Initialization and validate method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInitialization() throws ClassNotFoundException, SQLException
	{
		StripNail nail = StripNailMapper.create("5555", 1000, 10, 10, 50);
		PowerTool tool = PowerToolMapper.create("6666", 100, 10, "Power tool", false);
		tool.getId();nail.getId();
		 InsertRelationshipHandler handler = new  InsertRelationshipHandler( InsertRelationshipHandler.POWERTOOL);
		 InsertRelationshipHandler handler2 = new  InsertRelationshipHandler( InsertRelationshipHandler.STRIPNAIL);
		 assertTrue(handler.validate("6666"));
		 assertTrue(handler2.validate("5555"));
		 assertFalse(handler.validate("asd"));
		 assertFalse(handler2.validate("98668686"));
		 InsertRelationshipHandler handler3 = new  InsertRelationshipHandler( "aa");
		 assertFalse(handler3.validate("6666"));
		 
	}

}

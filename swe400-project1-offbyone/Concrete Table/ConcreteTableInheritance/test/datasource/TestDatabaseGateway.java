package datasource;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import domain.PowerToolsToStripNailsMapper;
import model.InventoryItem;
import model.Nail;
import model.PowerTool;
import model.StripNail;
/**
 * Tests database gateway
 * @author Darnell Martin & Ronald Sease
 *
 */
public class TestDatabaseGateway {

	/**
	 * Sets up transaction
	 * @throws SQLException
	 */
	@Before
	public void setUpTransaction() throws SQLException
	{
		DatabaseGateway.getConnection();
		DatabaseGateway.setTransaction();
	}
	
	/**
	 * Rolls back transaction
	 * @throws SQLException
	 */
	@After
	public void rollbackTransaction() throws SQLException
	{
		DatabaseGateway.rollbackTransaction();
		DatabaseGateway.resetAutoIncrement();
	}
	
	/**
	 * Tests the connection to the database
	 * @throws SQLException
	 */
	@Test
	public void testGetConnection() throws SQLException
	{
		assertTrue(DatabaseGateway.getConnection() != null);
	}
	
	/**
	 * Tests adding a nail to the database and the nail entry has an entry
	 * @throws SQLException
	 */
	@Test
	public void testGetTable() throws SQLException
	{
		new Nail("asdf",5,5,5.0,20);
		ResultSet rs = DatabaseGateway.getTable("Nail");
		if(rs.next())
		{
			assertEquals(rs.getInt("id"),1);
			assertEquals(rs.getInt("ManufacturerID"),5);
			assertEquals(rs.getInt("Price"),5);
			assertEquals(rs.getString("UPC"),"asdf");
			assertTrue(rs.getDouble("Length") == 5.0);
			assertEquals(rs.getInt("NumberInBox"), 20);
		}
	}
	
	/**
	 * Tests that the key table is autogenerating values
	 * @throws SQLException
	 */
	@Test
	public void testGetNewTableKey() throws SQLException
	{
		assertEquals(1, DatabaseGateway.getNewID());
	}
	
	/**
	 * Tests getting an object by it's id
	 * @throws SQLException
	 */
	@Test
	public void testGetObjectByID() throws SQLException
	{
		InventoryItem nail = new Nail("asdf",5,5,5.0,20);
		ResultSet rs = DatabaseGateway.getObjectByID("Nail", nail.id);
		if(rs.next())
		{
			assertEquals(rs.getInt("id"),1);
			assertEquals(rs.getInt("ManufacturerID"),5);
			assertEquals(rs.getInt("Price"),5);
			assertEquals(rs.getString("UPC"),"asdf");
			assertTrue(rs.getDouble("Length") == 5.0);
			assertEquals(rs.getInt("NumberInBox"), 20);
		}
	}
	
	/**
	 * Tests adding a row to a database table.
	 * @throws SQLException
	 */
	@Test
	public void testCreateRow() throws SQLException
	{
		String insertStatement = "INSERT INTO Nail (" + 
				"id, UPC, ManufacturerID, Price,Length, NumberInBox" +
				") VALUES (1,'asdf',5,5,5.0,20)";
		DatabaseGateway.createRow(insertStatement);
		
		ResultSet rs = DatabaseGateway.getObjectByID("Nail", 1);
		if(rs.next())
		{
			assertEquals(rs.getInt("id"),1);
			assertEquals(rs.getInt("ManufacturerID"),5);
			assertEquals(rs.getInt("Price"),5);
			assertEquals(rs.getString("UPC"),"asdf");
			assertTrue(rs.getDouble("Length") == 5.0);
			assertEquals(rs.getInt("NumberInBox"), 20);
		}
	}
	
	/**
	 * Tests adding a row to the associated table PowerToolsTOStripNails
	 * @throws SQLException
	 */
	@Test
	public void testCreateRowRelation() throws SQLException
	{
		new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		new StripNail("UPC", 5, 5, 3.33, 9);
		
		new PowerToolsToStripNailsMapper(1,2);
		
		ResultSet rs = DatabaseGateway.getTable("PowerToolsTOStripNails");
		if(rs.next())
		{
			assertEquals(rs.getInt("PowerToolid"),1);
			assertEquals(rs.getInt("StripNailid"),2);
		}
	}
	
	/**
	 * Tests reading from the associated table PowerToolsTOStripNails
	 * @throws SQLException
	 */
	@Test
	public void testReadRowRelationPowerTool() throws SQLException
	{
		new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		new StripNail("UPC", 5, 5, 3.33, 9);
		
		new PowerToolsToStripNailsMapper(1,2);
		
		ArrayList<Integer> powerToolsRelatedToStripNail = DatabaseGateway.readRowRelationPowerTool(1);
		assertTrue(powerToolsRelatedToStripNail.contains(2));
	}
	
	/**
	 * Tests reading from the PowerToolsTOStripNails
	 * @throws SQLException
	 */
	@Test
	public void testReadRowRelationStripNail() throws SQLException
	{
		new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		new StripNail("UPC", 5, 5, 3.33, 9);
		
		new PowerToolsToStripNailsMapper(1,2);
		
		ArrayList<Integer> stripNailsRelatedToPowerTools = DatabaseGateway.readRowRelationStripNail(2);
		assertTrue(stripNailsRelatedToPowerTools.contains(1));
	}
	
	/**
	 * Tests getting all keys in from the key table
	 * @throws SQLException
	 */
	@Test 
	public void testGetAllKeysFromKeyTable() throws SQLException
	{
		new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		ArrayList<Integer> results = DatabaseGateway.getAllKeysFromKeyTable();
		assertTrue(results.contains(1));
	
	}
}

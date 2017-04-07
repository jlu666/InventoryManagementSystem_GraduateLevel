package model;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import model.PowerTool;
/**
 * Tests power tool
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestPowerTool {

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
	 * Rollsback all changes made to the database
	 * @throws SQLException
	 */
	@After
	public void rollbackTtansaction() throws SQLException
	{
		DatabaseGateway.rollbackTransaction();
		DatabaseGateway.resetAutoIncrement();
	}
	
	/**
	 * Tests toString format
	 * @throws SQLException
	 */
	@Test
	public void testPowerToolToString() throws SQLException 
	{
		PowerTool item = new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		
		assertEquals("PowerTool: UPC: asdf, Manufacturer ID:5, Price:5, Description:PowerTooldescription, is Battery Powered:true" ,item.toString());		
	}
	
	/**
	 * Tests initializing a new PowerTool
	 * @throws SQLException
	 */
	@Test
	public void testPowerToolCreationConstructor() throws SQLException
	{
		PowerTool item = new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		
		assertTrue(item.getID() > 0);
		assertEquals(item.getManufacturerID(),5);
		assertEquals(item.getPrice(),5);
		assertEquals(item.getUPC(),"asdf");
		assertEquals(item.getBatteryPowered(),true);
		assertEquals(item.getDescription(),"PowerTooldescription");
	}
	
	
	/**
	 * Tests getting a PowerTool from the database using the finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testPowerToolFinderIDConstructor() throws SQLException
	{
		PowerTool item = new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		PowerTool itemfromDatabase = new PowerTool(item.id);
		
		assertEquals(itemfromDatabase.getID(), 1);
		assertEquals(itemfromDatabase.getManufacturerID(),5);
		assertEquals(itemfromDatabase.getPrice(),5);
		assertEquals(itemfromDatabase.getUPC(),"asdf");
		assertEquals(itemfromDatabase.getBatteryPowered(),true);
		assertEquals(itemfromDatabase.getDescription(),"PowerTooldescription");
	}
	
	/**
	 * Tests setters
	 * @throws SQLException 
	 */
	@Test
	public void testPowerToolSetters() throws SQLException
	{
		PowerTool item = new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		PowerTool itemFromDatabase = new PowerTool(item.id);
		itemFromDatabase.setUPC("1111-1111");
		itemFromDatabase.setManufacturerID(2);
		itemFromDatabase.setPrice(10);
		itemFromDatabase.setDescription("new description");
		itemFromDatabase.setBatteryPowered(false);
		assertEquals("1111-1111", itemFromDatabase.getUPC());
		assertEquals(2, itemFromDatabase.getManufacturerID());
		assertEquals(10, itemFromDatabase.getPrice());
		assertEquals("new description", itemFromDatabase.getDescription());
		assertEquals(false, itemFromDatabase.getBatteryPowered());
	}
	
	

	/**
	 * Tests passing in a invalid ID returns an id of 0, which is an invalid PowerTool
	 * @throws SQLException
	 */
	@Test
	public void testPowerToolFinderConstructorReturnsNullWithInvalidID() throws SQLException
	{
		PowerTool item = new PowerTool(99999999);
		//with enough entries, this will eventually fail 
		assertEquals(0, item.id);
	}
	
	/**
	 * Tests passing in a invalid UPC returns an id of 0, which is an invalid PowerTool
	 * @throws SQLException
	 */
	@Test
	public void testPowerToolFinderConstructorReturnsNullWithInvalidUPC() throws SQLException
	{
		PowerTool item = new PowerTool("99999999");
		//with enough entries, this will eventually fail 
		assertEquals(0, item.id);
	}

	/**
	 * Tests getting a PowerTool from the database using the UPC finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testPowerToolFinderUPCConstructor() throws SQLException
	{
		new PowerTool("15463-88888", 5, 5, "PowerTooldescription", true);
		PowerTool itemfromDatabase = new PowerTool("15463-88888");
		
		assertEquals(itemfromDatabase.getID(), 1);
		assertEquals(itemfromDatabase.getManufacturerID(),5);
		assertEquals(itemfromDatabase.getPrice(),5);
		assertEquals(itemfromDatabase.getUPC(),"15463-88888");
		assertEquals(itemfromDatabase.getBatteryPowered(),true);
		assertEquals(itemfromDatabase.getDescription(),"PowerTooldescription");
	}
	
}

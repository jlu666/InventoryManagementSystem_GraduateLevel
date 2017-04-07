package model;
import static org.junit.Assert.*;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import model.Tool;
/**
 * Tests the tool
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestTool {

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
	public void rollbackTransaction() throws SQLException
	{
		DatabaseGateway.rollbackTransaction();
		DatabaseGateway.resetAutoIncrement();
	}
	
	/**
	 * Tests toString format
	 * @throws SQLException
	 */
	@Test
	public void testToolToString() throws SQLException 
	{
		Tool item = new Tool("asdf", 5, 5, "Tooldescription");
		assertEquals("Tool: UPC:asdf, ManufacturerID: 5, Price: 5, Description: Tooldescription" ,item.toString());		
	}
	
	/**
	 * Tests initializing a new nail
	 * @throws SQLException
	 */
	@Test
	public void testToolCreationConstructor() throws SQLException
	{
		Tool item = new Tool("asdf", 5, 5, "Tooldescription");
		
		assertEquals(item.id , 1);
		assertEquals(item.getManufacturerID(),5);
		assertEquals(item.getPrice(),5);
		assertEquals(item.getUPC(),"asdf");
		assertEquals(item.getDescription(),"Tooldescription");
	}
	
	/**
	 * Tests passing in a invalid ID returns an id of 0, which is an invalid Tool
	 * @throws SQLException
	 */
	@Test
	public void testToolFinderConstructorReturnsNullWithInvalidUPC() throws SQLException
	{
		Tool item = new Tool("99999999");
		//with enough entries, this will eventually fail 
		assertEquals(0, item.id);
	}
	
	/**
	 * Tests getting a Tool from the database using the UPC finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testToolFinderUPCConstructor() throws SQLException
	{
		new Tool("1111-1111", 5, 5, "Tooldescription");
		Tool itemfromDatabase = new Tool("1111-1111");
		
		assertEquals(itemfromDatabase.id , 1);
		assertEquals(itemfromDatabase.getManufacturerID(),5);
		assertEquals(itemfromDatabase.getPrice(),5);
		assertEquals(itemfromDatabase.getUPC(),"1111-1111");
		assertEquals(itemfromDatabase.getDescription(),"Tooldescription");
	}
	
	/**
	 * Tests the setters for tool
	 * @throws SQLException 
	 */
	@Test
	public void testSetters() throws SQLException
	{
		new Tool("1111-1111", 5, 5, "ToolDescription");
		Tool toolFromDatabase = new Tool("1111-1111");
		
		assertEquals(1, toolFromDatabase.getID());
		assertEquals("1111-1111", toolFromDatabase.getUPC());
		assertEquals(5, toolFromDatabase.getManufacturerID());
		assertEquals(5, toolFromDatabase.getPrice());
		assertEquals("ToolDescription", toolFromDatabase.getDescription());
		toolFromDatabase.setManufacturerID(3);
		toolFromDatabase.setUPC("1111-1112");
		toolFromDatabase.setPrice(9);
		toolFromDatabase.setDescription("new tool description");
		assertEquals("1111-1112", toolFromDatabase.getUPC());
		assertEquals(3, toolFromDatabase.getManufacturerID());
		assertEquals(9, toolFromDatabase.getPrice());
		assertEquals("new tool description", toolFromDatabase.getDescription());
	}
	
}


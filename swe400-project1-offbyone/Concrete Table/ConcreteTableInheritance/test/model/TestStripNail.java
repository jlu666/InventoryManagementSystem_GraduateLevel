package model;
import static org.junit.Assert.*;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import model.StripNail;
/**
 * Tests StripNail
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestStripNail {

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
	public void testStripNailToString() throws SQLException 
	{
		StripNail item = new StripNail("asdf",5,5,5.0,20);
		assertEquals("StripNail: UPC: asdf, ManufacturerID: 5, Price: 5, Length: 5.0, Number In Strip: 20" ,item.toString());		
	}
	
	/**
	 * Tests initializing a new nail
	 * @throws SQLException
	 */
	@Test
	public void testStripNailCreationConstructor() throws SQLException
	{
		StripNail item = new StripNail("asdf",5,5,5.0,20);
		
		assertEquals(1, item.getID());
		assertEquals(5, item.getManufacturerID());
		assertEquals(5, item.getPrice());
		assertEquals("asdf", item.getUPC());
		assertEquals(5.0, item.getLength(), .001);
		assertEquals(20, item.getNumberInStrip());
	}
	
	/**
	 * Tests getting a Strip Nail from the database using the finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testStripNailFinderIDConstructor() throws SQLException
	{
		StripNail item = new StripNail("asdf",5,5,5.0,20);		
		StripNail itemfromDatabase = new StripNail(item.id);
		
		
		assertEquals(1, itemfromDatabase.getID());
		assertEquals(5, itemfromDatabase.getManufacturerID());
		assertEquals(5, itemfromDatabase.getPrice());
		assertEquals("asdf", itemfromDatabase.getUPC());
		assertEquals(5.0, itemfromDatabase.getLength(), .001);
		assertEquals(20, itemfromDatabase.getNumberInStrip());	
	}
	
	/**
	 * Tests setters
	 * @throws SQLException 
	 */
	@Test
	public void testStripNailSetters() throws SQLException
	{
		StripNail item = new StripNail("asdf",5,5,5.0,20);		
		StripNail itemFromDatabase = new StripNail(item.id);
		
		itemFromDatabase.setUPC("1234-5678");
		itemFromDatabase.setManufacturerID(3);
		itemFromDatabase.setPrice(9);
		itemFromDatabase.setLength(5.4);
		itemFromDatabase.setNumberInStrip(99);
		assertEquals(3, itemFromDatabase.getManufacturerID());
		assertEquals(9, itemFromDatabase.getPrice());
		assertEquals("1234-5678", itemFromDatabase.getUPC());
		assertEquals(5.4, itemFromDatabase.getLength(), .001);
		assertEquals(99, itemFromDatabase.getNumberInStrip());	
	}
	
	/**
	 * Tests getting a Strip Nail from the database using the UPC finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testStripNailFinderUPCConstructor() throws SQLException
	{
		new StripNail("12345-67890",5,5,5.0,20);		
		StripNail itemfromDatabase = new StripNail("12345-67890");
		
		
		assertEquals(1, itemfromDatabase.getID());
		assertEquals(5, itemfromDatabase.getManufacturerID());
		assertEquals(5, itemfromDatabase.getPrice());
		assertEquals("12345-67890", itemfromDatabase.getUPC());
		assertEquals(5.0, itemfromDatabase.getLength(), .001);
		assertEquals(20, itemfromDatabase.getNumberInStrip());	
	}

	/**
	 * Tests passing in a invalid ID returns an id of 0, which is an invalid StripNail
	 * @throws SQLException
	 */
	@Test
	public void testStripNailFinderConstructorReturnsNullWithInvalidID() throws SQLException
	{
		StripNail item = new StripNail(99999999);
		//with enough entries, this will eventually fail 
		assertEquals(0, item.id);
	}
	
}



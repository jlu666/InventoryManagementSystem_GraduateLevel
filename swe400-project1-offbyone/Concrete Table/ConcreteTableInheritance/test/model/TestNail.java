package model;
import static org.junit.Assert.*;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import model.Nail;
/**
 * Tests instance of Nail
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestNail 
{
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
	public void testNailToString() throws SQLException 
	{
		Nail item = new Nail("asdf",5,5,5.0,20);
		assertEquals("Nail: UPC: asdf, ManufactuerID: 5, Price: 5, Length: 5.0, Number In Box: 20" ,item.toString());
	}
	
	/**
	 * Tests initializing a new nail
	 * @throws SQLException
	 */
	@Test
	public void testNailCreationConstructor() throws SQLException
	{
		Nail item = new Nail("asdf",5,5,5.0,20);
		
		assertEquals(item.getID() ,1);
		assertEquals(item.getManufacturerID(),5);
		assertEquals(item.getPrice(),5);
		assertEquals(item.getUPC(),"asdf");
		assertEquals(item.getLength(), 5.0, .001);
		assertEquals(item.getNumberInBox(), 20);
	}
	
	/**
	 * Tests getting a nail from the database using the finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testNailFinderConstructor() throws SQLException
	{
		new Nail("asdf",5,5,5.0,20);		
		Nail itemfromDatabase = new Nail("asdf");
		
		assertEquals(itemfromDatabase.getID() ,1);
		assertEquals(itemfromDatabase.getManufacturerID(),5);
		assertEquals(itemfromDatabase.getPrice(),5);
		assertEquals(itemfromDatabase.getUPC(),"asdf");
		assertEquals(itemfromDatabase.getLength(), 5.0, .001);
		assertEquals(itemfromDatabase.getNumberInBox(), 20);
	}
	
	/**
	 * Tests getting a Nail from the database using the UPC finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testNailFinderUPCConstructor() throws SQLException
	{
		new Nail("1111-2222", 5, 5, 3.4, 2);
		Nail itemfromDatabase = new Nail("1111-2222");
		
		assertEquals(itemfromDatabase.id , 1);
		assertEquals(itemfromDatabase.getManufacturerID(),5);
		assertEquals(itemfromDatabase.getPrice(),5);
		assertEquals(itemfromDatabase.getUPC(),"1111-2222");
		assertEquals(itemfromDatabase.getLength(), 3.4, .001);
		assertEquals(itemfromDatabase.getNumberInBox(), 2);
	}
	
	/**
	 * Tests passing in a invalid ID returns an id of 0, which is an invalid Nail
	 * @throws SQLException
	 */
	@Test
	public void testNailFinderConstructorReturnsNullWithInvalidID() throws SQLException
	{
		Nail item = new Nail("99999999");
		assertEquals(0, item.id);
	}
	
	/**
	 * Tests setters
	 */
	/**
	 * Tests getting a nail from the database using the finder constructor
	 * @throws SQLException
	 */
	@Test
	public void testNailSetters() throws SQLException
	{
		new Nail("asdf",5,5,5.0,20);		
		Nail itemFromDatabase = new Nail("asdf");
		
		itemFromDatabase.setUPC("1111-1111");
		itemFromDatabase.setManufacturerID(2);
		itemFromDatabase.setLength(3.2);
		itemFromDatabase.setPrice(10);
		itemFromDatabase.setNumberInBox(42);
		assertEquals("1111-1111", itemFromDatabase.getUPC());
		assertEquals(2, itemFromDatabase.getManufacturerID());
		assertEquals(3.2, itemFromDatabase.getLength(), .001);
		assertEquals(10, itemFromDatabase.getPrice());
		assertEquals(42, itemFromDatabase.getNumberInBox());
		
	}
	
}

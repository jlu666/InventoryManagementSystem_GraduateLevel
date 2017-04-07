package gateway;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import model.PowerTool;
import model.StripNail;
/**
 * Tests getting Strip Nail Gateway
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestStripNailGateway 
{

	/**
	 * Tests getting a strip nail from the database.
	 * @throws SQLException
	 */
	@Test
	public void testGetStripNailFromDatabase() throws SQLException
	{
		StripNail stripnail = new StripNail("5453432345");
		assertEquals(7, stripnail.getID());
	}
	
	/**
	 * Gets power tools that are associated with a strip nail
	 * @throws SQLException
	 */
	@Test
	public void testGetStripNailWithRelationsFromDatabase() throws SQLException
	{
		StripNail stripnail = new StripNail("5453432345");
		assertEquals(7, stripnail.getID());
		ArrayList<PowerTool> list = stripnail.getPowerToolsForStripNail();
		assertEquals(2, list.size());
	}
	
	/**
	 * Tests updating the upc of a  strip nail within the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateNailFromDatabase() throws SQLException
	{
		StripNail stripnail = new StripNail("4343434543");
		assertEquals(8, stripnail.getID());
		stripnail.setUPC("5555555");
		StripNail stripnail2 = new StripNail("5555555");
		assertEquals(8, stripnail2.getID());
		assertEquals("5555555", stripnail2.getUPC());
		
	}

}

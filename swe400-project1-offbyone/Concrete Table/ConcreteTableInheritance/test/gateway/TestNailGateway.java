package gateway;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import model.Nail;
/**
 * Tests getting Nail Gateway
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestNailGateway 
{
	/**
	 * Tests getting a nail from the database.
	 * @throws SQLException
	 */
	@Test
	public void testGetNailFromDatabase() throws SQLException
	{
		Nail nail = new Nail("5453432767");
		assertEquals(17, nail.getID());
	}
	
	/**
	 * Tests updating the upc of a nail within the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateNailFromDatabase() throws SQLException
	{
		Nail nail = new Nail("4343432345");
		assertEquals(21, nail.getID());
		nail.setUPC("5555555");
		Nail nail2 = new Nail("5555555");
		assertEquals(21, nail2.getID());
		assertEquals("5555555", nail2.getUPC());
		
	}

}

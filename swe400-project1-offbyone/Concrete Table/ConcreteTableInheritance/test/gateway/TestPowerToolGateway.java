package gateway;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import model.PowerTool;
import model.StripNail;
/**
 * Tests getting Tool Gateway
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestPowerToolGateway 
{

	/**
	 * Tests getting a Power Tool from the database.
	 * @throws SQLException
	 */
	@Test
	public void testGetPowerToolFromDatabase() throws SQLException
	{
		PowerTool powerTool = new PowerTool("1231231234");
		assertEquals(1, powerTool.getID());
	}
	
	/**
	 * Tests updating the upc of a power tool within the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateNailFromDatabase() throws SQLException
	{
		PowerTool powerTool = new PowerTool("4445553333");
		assertEquals(2, powerTool.getID());
		powerTool.setUPC("5555555");
		PowerTool powerTool2 = new PowerTool("5555555");
		assertEquals(2, powerTool2.getID());
		assertEquals("5555555", powerTool2.getUPC());
		
	}
	
	/**
	 * Tests getting a power tool with it's strip nail relations from the database.
	 * @throws SQLException
	 */
	@Test
	public void testGetPowerToolsWithRelationsFromDatabase() throws SQLException
	{
		PowerTool powerTool = new PowerTool("1231231234");
		assertEquals(1, powerTool.getID());
		ArrayList<StripNail> list = powerTool.getStripNailsForPowerTool();
		assertEquals(2, list.size());
	}
	
}




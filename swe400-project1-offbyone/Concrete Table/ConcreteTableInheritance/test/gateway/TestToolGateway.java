package gateway;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;

import model.Tool;
/**
 * Tests getting Tool Gateway
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestToolGateway
{

	/**
	 * Tests gettings a tool from the database
	 * @throws SQLException
	 */
	@Test
	public void testGetToolFromDatabase() throws SQLException
	{
		Tool tool = new Tool("1232343345");
		assertEquals(13, tool.getID());
	}
	
	/**
	 * Tests updating the upc of a tool within the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateNailFromDatabase() throws SQLException
	{
		Tool tool = new Tool("0121232234");
		assertEquals(12, tool.getID());
		tool.setUPC("5555555");
		Tool tool2 = new Tool("5555555");
		assertEquals(12, tool2.getID());
		assertEquals("5555555", tool2.getUPC());
		
	}

}

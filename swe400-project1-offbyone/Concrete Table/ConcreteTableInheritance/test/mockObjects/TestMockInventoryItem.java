package mockObjects;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import model.InventoryItem;
/**
 * Tests Mock Inventory item
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestMockInventoryItem
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
	 * Tests creating a mock inventory item
	 */
	@Test
	public void testMockInventoryCreation()
	{
		InventoryItem item = new MockInventoryItem(0, "0000-0000", 1, 2);
		assertEquals(item.getID(),0);
		assertEquals(item.getManufacturerID(),1);
		assertEquals(item.getUPC(), "0000-0000");
		assertEquals(item.getPrice(), 2);
	}
	
	
}

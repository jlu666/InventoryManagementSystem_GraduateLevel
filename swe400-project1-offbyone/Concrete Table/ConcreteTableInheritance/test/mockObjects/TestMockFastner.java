package mockObjects;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import model.Fastner;
/**
 * Tests the mock fastner
 * @author Darnell martin & Ronald Sease
 *
 */
public class TestMockFastner {

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
	 * Tests creation of a mock fastner
	 */
	@Test
	public void testMockFastnerCreation()
	{
		Fastner item = new MockFastner("0000-0000", 1, 2, 5.5);
		assertEquals(item.getID(),0);
		assertEquals(item.getManufacturerID(),1);
		assertEquals(item.getUPC(), "0000-0000");
		assertEquals(item.getPrice(), 2);
		assertEquals(item.getLength(), 5.5, .001);
	}

}

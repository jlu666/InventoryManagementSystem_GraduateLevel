package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test FastnerGateway.
 * 
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestFastnerGateway
{
	
	/**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Before
    public void setup() throws ClassNotFoundException, SQLException {
        Database.beginTransaction();
    }

    /**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @After
    public void teardown() throws ClassNotFoundException, SQLException {
        Database.rollbackTransaction();
    }

	/**
	 * Test save method to call insert or update method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInsertUpdate() throws ClassNotFoundException, SQLException
	{
		FastenerGateway gateway = new MockFastenerGateway(-1,"upc",1,100,50.0);
		assertEquals(-1,gateway.getId());
		assertEquals("upc",gateway.getUpc());
		assertEquals(1,gateway.getManufacturerId());
		assertEquals(100,gateway.getPrice());
		assertEquals(50.0,gateway.getLength(),0.0001);
		gateway.save();
		assertNotEquals(-1,gateway.getId());
		
		gateway.setLength(40.0);
		gateway.save();
		
		FastenerGateway gateway2 = new MockFastenerGateway();
		assertEquals(gateway2.getTypeCode(),TypeCode.Nail);
	}

}

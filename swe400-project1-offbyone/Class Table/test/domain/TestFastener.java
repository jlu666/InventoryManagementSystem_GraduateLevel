package domain;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.fastener.Fastener;

/**
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestFastener
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
	 * Tests initialization
	 */
	@Test
	public void testFastener()
	{
		Fastener fa = new MockFastener();
		fa.setLength(10);
		assertEquals(fa.getLength(), 10.0, 0.0001);
	}

	/**
	 * Tests toStringMethod
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
    public void testToString() throws ClassNotFoundException, SQLException{
		Fastener fa = new MockFastener();
		fa.setLength(10);
		fa.setUpc("upc");
		fa.setId(10);
		fa.setManufacturerId(21);
		fa.setPrice(42);
		fa.setLength(10.0);
    	String output = "Fastener(upc: upc, manufacturerId: 21, price: 42, length: 10.0)";
    	assertEquals(fa.toString(),output);
    }
}

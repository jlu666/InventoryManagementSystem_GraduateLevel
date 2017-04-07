package datasource;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test NailFinder
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestNailFinder {

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
	 * @throws ClassNotFoundException  ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@After
	public void teardown() throws ClassNotFoundException, SQLException {
		Database.rollbackTransaction();
		Database.resetAutoIncrement();
	}

	/**
	 * Tests find method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testFinfer() throws ClassNotFoundException, SQLException {
		NailGateway gateway = NailFinder.find(2000);//assume 2000 is invalid id.Cause 2000 columns is null.
		assertNull(gateway);
		
		gateway = new NailGateway(-1,"upc",1,100,50.0,50);
		gateway.save();
		NailGateway copy = NailFinder.find(gateway.getId());
		assertEquals(copy.getId(),gateway.getId());
		assertEquals(copy.getUpc(),gateway.getUpc());
		assertEquals(copy.getManufacturerId(),gateway.getManufacturerId());
		assertEquals(copy.getPrice(),gateway.getPrice());
		assertEquals(copy.getLength(),gateway.getLength(),0.0001);
		assertEquals(copy.getNumberInBox(),gateway.getNumberInBox());
	}

}

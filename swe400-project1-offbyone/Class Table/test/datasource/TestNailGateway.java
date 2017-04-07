package datasource;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test NailGateway.
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestNailGateway {

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
       Database.resetAutoIncrement();
    }
	
    /**
	 * Tests Insert method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void TestInsert() throws ClassNotFoundException, SQLException{
		NailGateway gateway = new NailGateway(-1,"upc",1,100,50.0,50);
		assertEquals(-1,gateway.getId());
		assertEquals("upc",gateway.getUpc());
		assertEquals(1,gateway.getManufacturerId());
		assertEquals(100,gateway.getPrice());
		assertEquals(50.0,gateway.getLength(),0.0001);
		assertEquals(50,gateway.getNumberInBox());
		gateway.save();
		assertNotEquals(-1,gateway.getId());
		
		NailGateway gateway2 = new NailGateway();
		gateway2.setUpc("upc2");
		gateway2.setManufacturerId(1);
		gateway2.setPrice(100);
		gateway2.setLength(50.0);
		gateway2.setNumberInBox(50);
		assertEquals(-1,gateway2.getId());
		assertEquals("upc2",gateway2.getUpc());
		assertEquals(1,gateway2.getManufacturerId());
		assertEquals(100,gateway2.getPrice());
		assertEquals(50.0,gateway2.getLength(),0.0001);
		assertEquals(50,gateway2.getNumberInBox());
		assertTrue(gateway2.getTypeCode().equals(TypeCode.Nail));
		gateway2.save();
		assertNotEquals(-1,gateway2.getId());
	}
	
	/**
	 * Test update method and NailFinder
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void TestUpdateAndFinder() throws ClassNotFoundException, SQLException{
		NailGateway gateway = new NailGateway(-1,"upc",1,100,50.0,50);
		gateway.save();
		NailGateway copy = NailFinder.find(gateway.getId());
		assertEquals(copy.getId(),gateway.getId());
		assertEquals(copy.getUpc(),gateway.getUpc());
		assertEquals(copy.getManufacturerId(),gateway.getManufacturerId());
		assertEquals(copy.getPrice(),gateway.getPrice());
		assertEquals(copy.getLength(),gateway.getLength(),0.0001);
		assertEquals(copy.getNumberInBox(),gateway.getNumberInBox());
		
		//test update
		gateway.setUpc("change");
		gateway.setLength(1.0);
		gateway.setPrice(60);
		gateway.setManufacturerId(300);
		gateway.setNumberInBox(30);
		gateway.save();
		copy = NailFinder.find(gateway.getId());
		assertEquals(copy.getUpc(),"change");
		assertEquals(copy.getLength(),1.0,0.0001);
		assertEquals(copy.getManufacturerId(),300);
		assertEquals(copy.getPrice(),60);
		assertEquals(copy.getNumberInBox(),30);
		
		
	}
}

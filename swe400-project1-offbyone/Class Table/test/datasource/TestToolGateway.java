package datasource;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test ToolGateway.
 * @author Alan Malloy & Jixiang Lu
 */
public class TestToolGateway {

	
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
	 * Tests Inserts Method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void TestInsert() throws ClassNotFoundException, SQLException{
		ToolGateway gateway = new ToolGateway(-1,"upc",1,100,"description");
		assertEquals(-1,gateway.getId());
		assertEquals(TypeCode.Tool, gateway.getTypeCode());
		assertEquals("upc",gateway.getUpc());
		assertEquals(1,gateway.getManufacturerId());
		assertEquals(100,gateway.getPrice());
		assertEquals("description",gateway.getDescription());
		gateway.save();
		assertNotEquals(-1,gateway.getId());
		
		ToolGateway gateway2 = new ToolGateway();
		gateway2.setUpc("upc2");
		gateway2.setManufacturerId(1);
		gateway2.setPrice(100);
		gateway2.setDescription("description");
		assertEquals(-1,gateway2.getId());
		assertEquals(TypeCode.Tool, gateway2.getTypeCode());
		assertEquals("upc2",gateway2.getUpc());
		assertEquals(1,gateway2.getManufacturerId());
		assertEquals(100,gateway2.getPrice());
		assertEquals("description",gateway2.getDescription());
		assertTrue(gateway2.getTypeCode().equals(TypeCode.Tool));
		gateway2.save();
		assertNotEquals(-1,gateway2.getId());
	}
	
	
	/**
	 * Tests update method and ToolFinder.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void TestUpdateAndFinder() throws ClassNotFoundException, SQLException{
		ToolGateway gateway = new ToolGateway(-1,"upc",1,100,"description");
		gateway.save();
		ToolGateway copy = ToolFinder.find(gateway.getId());
		assertEquals(copy.getId(),gateway.getId());
		assertEquals(copy.getTypeCode(), gateway.getTypeCode());
		assertEquals(copy.getUpc(),gateway.getUpc());
		assertEquals(copy.getManufacturerId(),gateway.getManufacturerId());
		assertEquals(copy.getPrice(),gateway.getPrice());
		assertEquals(copy.getDescription(),gateway.getDescription());
		
		//test update
		gateway.setUpc("change");
		gateway.setPrice(60);
		gateway.setManufacturerId(300);
		gateway.setDescription("changed description");
		gateway.save();
		
		copy = ToolFinder.find(gateway.getId());
		assertEquals(copy.getTypeCode(), TypeCode.Tool);
		assertEquals(copy.getUpc(),"change");
		assertEquals(copy.getManufacturerId(),300);
		assertEquals(copy.getPrice(),60);
		assertEquals(copy.getDescription(),"changed description");
	}
	
}

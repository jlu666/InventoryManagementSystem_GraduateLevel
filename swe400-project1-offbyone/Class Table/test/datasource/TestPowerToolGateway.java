package datasource;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TestPowerToolGateway.
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestPowerToolGateway {

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
	 * Tests Insert method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void TestInsert() throws ClassNotFoundException, SQLException{
		PowerToolGateway gateway = new PowerToolGateway(-1,"upc",1,100,"description", true);
		assertEquals(-1,gateway.getId());
		assertEquals(TypeCode.PowerTool, gateway.getTypeCode());
		assertEquals("upc",gateway.getUpc());
		assertEquals(1,gateway.getManufacturerId());
		assertEquals(100,gateway.getPrice());
		assertEquals("description",gateway.getDescription());
		assertEquals(true, gateway.isBatteryPowered());
		gateway.save();
		assertNotEquals(-1,gateway.getId());
		
		PowerToolGateway gateway2 = new PowerToolGateway();
		gateway2.setUpc("upc2");
		gateway2.setManufacturerId(1);
		gateway2.setPrice(100);
		gateway2.setDescription("description");
		gateway2.setBatteryPowered(false);
		assertEquals(-1,gateway2.getId());
		assertEquals(TypeCode.PowerTool, gateway2.getTypeCode());
		assertEquals("upc2",gateway2.getUpc());
		assertEquals(1,gateway2.getManufacturerId());
		assertEquals(100,gateway2.getPrice());
		assertEquals("description",gateway2.getDescription());
		assertEquals(false, gateway2.isBatteryPowered());
		assertTrue(gateway2.getTypeCode().equals(TypeCode.PowerTool));
		gateway2.save();
		assertNotEquals(-1,gateway2.getId());
	}
	
	/**
	 *  Test update method and PowerFinder
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void TestUpdateAndFinder() throws ClassNotFoundException, SQLException{
		PowerToolGateway gateway = new PowerToolGateway(-1,"upc",1,100,"description", true);
		gateway.save();
		PowerToolGateway copy = PowerToolFinder.find(gateway.getId());
		assertEquals(copy.getId(),gateway.getId());
		assertEquals(copy.getTypeCode(), gateway.getTypeCode());
		assertEquals(copy.getUpc(),gateway.getUpc());
		assertEquals(copy.getManufacturerId(),gateway.getManufacturerId());
		assertEquals(copy.getPrice(),gateway.getPrice());
		assertEquals(copy.getDescription(),gateway.getDescription());
		assertEquals(copy.isBatteryPowered(), gateway.isBatteryPowered());
		
		//test update
		gateway.setUpc("change");
		gateway.setPrice(60);
		gateway.setManufacturerId(300);
		gateway.setDescription("changed description");
		gateway.setBatteryPowered(false);
		gateway.save();
		
		copy = PowerToolFinder.find(gateway.getId());
		assertEquals(copy.getTypeCode(), TypeCode.PowerTool);
		assertEquals(copy.getUpc(),"change");
		assertEquals(copy.getManufacturerId(),300);
		assertEquals(copy.getPrice(),60);
		assertEquals(copy.getDescription(),"changed description");
		assertFalse(copy.isBatteryPowered());
	}
	
}


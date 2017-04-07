package gateway;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import datasource.PowerToolStripNailRelationGateway;

/**
 * Tests strip nail power tool gateway
 * @author Ronald Sease & Darnell Martin
 */
public class TestStripNailPowerToolRelationGateway 
{

	/**
	 * Tests the creation of getting a relation from the gateway
	 * @throws SQLException
	 */
	@Test
	public void testStripNailPowerToolRelationGatewayCreation() throws SQLException
	{
		PowerToolStripNailRelationGateway gateway = new PowerToolStripNailRelationGateway(1,1,7);
		assertEquals(1, gateway.getID());
		
		assertEquals(1, gateway.getPowerToolGateway().getID());
		assertEquals("1231231234", gateway.getPowerToolGateway().getUPC());
		assertEquals(13, gateway.getPowerToolGateway().getManufacturerID());
		assertEquals(39900, gateway.getPowerToolGateway().getPrice());
		assertEquals("Pheumatic Nail Gun", gateway.getPowerToolGateway().getDescription());
		assertEquals(false, gateway.getPowerToolGateway().getBatteryPowered());
		
		assertEquals(7, gateway.getStripNailGateway().getID());
		assertEquals("5453432345", gateway.getStripNailGateway().getUPC());
		assertEquals(13, gateway.getStripNailGateway().getManufacturerID());
		assertEquals(1099, gateway.getStripNailGateway().getPrice());
		assertEquals(2.50, gateway.getStripNailGateway().getLength(), .001);
		assertEquals(50, gateway.getStripNailGateway().getNumberInStrip());
	}
	
	/**
	 * Test to make sure relation is updated
	 * @throws SQLException
	 */
	@Test
	public void testStripNailPowerToolRelationGatewayupdate() throws SQLException
	{
		new PowerToolStripNailRelationGateway(1,1,8);
		PowerToolStripNailRelationGateway.updateRelation(1, 1, 9);
		assertEquals(PowerToolStripNailRelationGateway.getPowerToolIDFRomRelationByID(1),1);
		assertEquals(PowerToolStripNailRelationGateway.getStripNailIDFRomRelationByID(1),9);
	}
}

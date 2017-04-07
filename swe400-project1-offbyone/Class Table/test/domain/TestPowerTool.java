package domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.inventory_item.InventoryItem;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;

/**
 * Tests PowerTool and PowerToolMapper.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestPowerTool {

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
	 * Tests NailMapper create and find methods
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testCreateFind() throws ClassNotFoundException, SQLException {
        PowerTool createdPowerTool = PowerToolMapper.create("pt_upc", 21, 42, "power_tool_description", true);
        assertNotNull(createdPowerTool.getId());
        assertEquals("pt_upc", createdPowerTool.getUpc());
        assertEquals(21, createdPowerTool.getManufacturerId());
        assertEquals(42, createdPowerTool.getPrice());
        assertEquals("power_tool_description", createdPowerTool.getDescription());
        assertEquals(true, createdPowerTool.isBatteryPowered());

        PowerTool foundPowerTool = PowerToolMapper.find(createdPowerTool.getId());
        assertEquals(createdPowerTool.getId(), foundPowerTool.getId());
        assertEquals("pt_upc", foundPowerTool.getUpc());
        assertEquals(21, foundPowerTool.getManufacturerId());
        assertEquals(42, foundPowerTool.getPrice());
        assertEquals("power_tool_description", foundPowerTool.getDescription());
        assertEquals(true, foundPowerTool.isBatteryPowered());
    }

    /**
	 *  Test toString method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testToString() throws ClassNotFoundException, SQLException{
    	PowerTool tool =PowerToolMapper.create("pt_upc", 21, 42, "power_tool_description", true);
    	String output = "PowerTool(upc: pt_upc, manufacturerId: 21, price: 42, description: power_tool_description, batteryPowered: true)";
    	assertEquals(tool.toString(),output);
    }

    /**
	 * Tests addStripNailRelations and getStripNail method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testRelationShip() throws ClassNotFoundException, SQLException{
    	PowerTool tool = PowerToolMapper.create("pt_upc", 21, 42, "power_tool_description", true);
    	assertEquals(0,tool.getStripNails().size());
    	StripNail nail1 = StripNailMapper.create("sn_upc1", 7, 90, 1.8, 25);
    	StripNail nail2 = StripNailMapper.create("sn_upc2", 7, 90, 1.8, 25);
    	StripNail nail3 = StripNailMapper.create("sn_upc3", 7, 90, 1.8, 25);
    	tool.addStripNailRelations(nail1);
    	tool.addStripNailRelations(nail2);
    	tool.addStripNailRelations(nail3);
    	PowerTool copy = PowerToolMapper.find(tool.getId());
    	assertEquals(3,tool.getStripNails().size());
    	List<StripNail> list = copy.getStripNails();
    	assertTrue(((StripNail)list.get(0)).equals(nail1));
    	assertTrue(((StripNail)list.get(1)).equals(nail2));
    	assertTrue(((StripNail)list.get(2)).equals(nail3));
    }

    /**
     * Ensure that Power Tool associated items are strip nails.
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
	@Test
	public void testAssociatedItems() throws ClassNotFoundException, SQLException {
		PowerTool pt = PowerToolMapper.create("pt_upc", 21, 42, "power_tool_description", true);
		StripNail nail1 = StripNailMapper.create("sn_upc1", 7, 90, 1.8, 25);
    	StripNail nail2 = StripNailMapper.create("sn_upc2", 7, 90, 1.8, 25);
    	StripNail nail3 = StripNailMapper.create("sn_upc3", 7, 90, 1.8, 25);
		pt.addStripNailRelations(nail1);
    	pt.addStripNailRelations(nail2);
    	pt.addStripNailRelations(nail3);
		List<InventoryItem> list = pt.associatedItems();
		assertTrue(((StripNail)list.get(0)).equals(nail1));
    	assertTrue(((StripNail)list.get(1)).equals(nail2));
    	assertTrue(((StripNail)list.get(2)).equals(nail3));
	}

}

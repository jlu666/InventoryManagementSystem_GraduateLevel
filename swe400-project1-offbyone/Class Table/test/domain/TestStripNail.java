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
 *
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestStripNail {

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
	 * Tests create method and find method are in the Mapper.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testCreateFind() throws ClassNotFoundException, SQLException {
        StripNail createdStripNail = StripNailMapper.create("sn_upc", 7, 90, 1.8, 25);
        assertNotNull(createdStripNail.getId());
        assertEquals("sn_upc", createdStripNail.getUpc());
        assertEquals(7, createdStripNail.getManufacturerId());
        assertEquals(90, createdStripNail.getPrice());
        assertEquals(1.8, createdStripNail.getLength(), 0.001);
        assertEquals(25, createdStripNail.getNumberinStrip());
        StripNail foundStripNail = StripNailMapper.find(createdStripNail.getId());
        assertEquals(createdStripNail.getId(), foundStripNail.getId());

        assertEquals("sn_upc", foundStripNail.getUpc());
        assertEquals(7, foundStripNail.getManufacturerId());
        assertEquals(90, foundStripNail.getPrice());
        assertEquals(1.8, foundStripNail.getLength(), 0.001);
        assertEquals(25, foundStripNail.getNumberinStrip());
    }

    /**
	 * Tests toString method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testToString() throws ClassNotFoundException, SQLException{
    	StripNail nail = StripNailMapper.create("sn_upc", 7, 90, 1.8, 25);
    	String output = "StripNail(upc: sn_upc, manufacturerId: 7, price: 90, length: 1.8, numberInStrip: 25)";
    	assertEquals(nail.toString(),output);
    }

    /**
	 * Tests search and create relationship method. 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testRelationShip() throws ClassNotFoundException, SQLException{
    	StripNail nail1 = StripNailMapper.create("sn_upc", 7, 90, 1.8, 25);
    	assertEquals(0,nail1.getPowerTools().size());
    	PowerTool tool1 = PowerToolMapper.create("pt_upc1", 21, 42, "power_tool_description", true);
    	PowerTool tool2 = PowerToolMapper.create("pt_upc2", 21, 42, "power_tool_description", true);
    	PowerTool tool3 = PowerToolMapper.create("pt_upc3", 21, 42, "power_tool_description", true);
    	nail1.addPowerToolRelation(tool1);
    	nail1.addPowerToolRelation(tool2);
    	nail1.addPowerToolRelation(tool3);
    	StripNail copy = StripNailMapper.find(nail1.getId());
    	assertEquals(3,nail1.getPowerTools().size());
    	List<PowerTool> list = copy.getPowerTools();
    	assertTrue(((PowerTool)list.get(0)).equals(tool1));
    	assertTrue(((PowerTool)list.get(1)).equals(tool2));
    	assertTrue(((PowerTool)list.get(2)).equals(tool3));
    }
    
    /**
     * Ensure that Power Tool associated items are strip nails.
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
    @Test
    public void testAssociatedItems() throws ClassNotFoundException, SQLException{
    	StripNail nail1 = StripNailMapper.create("sn_upc", 7, 90, 1.8, 25);
    	PowerTool tool1 = PowerToolMapper.create("pt_upc1", 21, 42, "power_tool_description", true);
    	PowerTool tool2 = PowerToolMapper.create("pt_upc2", 21, 42, "power_tool_description", true);
    	PowerTool tool3 = PowerToolMapper.create("pt_upc3", 21, 42, "power_tool_description", true);
    	nail1.addPowerToolRelation(tool1);
    	nail1.addPowerToolRelation(tool2);
    	nail1.addPowerToolRelation(tool3);
    	List<InventoryItem> list = nail1.associatedItems();
    	assertTrue(((PowerTool)list.get(0)).equals(tool1));
    	assertTrue(((PowerTool)list.get(1)).equals(tool2));
    	assertTrue(((PowerTool)list.get(2)).equals(tool3));
    }
}

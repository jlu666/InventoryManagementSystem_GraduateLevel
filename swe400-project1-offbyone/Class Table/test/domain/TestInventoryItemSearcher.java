package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.inventory_item.InventoryItemSearcher;
import domain.nail.Nail;
import domain.nail.NailMapper;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;
import domain.tool.Tool;
import domain.tool.ToolMapper;

/**
 * Tests InventoryItemSearcher.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestInventoryItemSearcher {
	
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
	 * Tests find method with falseCase.
	 * @throws ClassNotFoundException  ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testFindUndefined() throws ClassNotFoundException, SQLException {
		Tool foundTool = (Tool)InventoryItemSearcher.find(123456789);
		PowerTool foundPowerTool = (PowerTool)InventoryItemSearcher.find(123456789);
		Nail foundNail = (Nail)InventoryItemSearcher.find(123456789);
		StripNail foundStripNail = (StripNail)InventoryItemSearcher.find(123456789);
		assertNull(foundTool);
		assertNull(foundPowerTool);
		assertNull(foundNail);
		assertNull(foundStripNail);
	}

	/**
	 * Tests findbyid method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testFindById() throws ClassNotFoundException, SQLException {
		Tool tool = ToolMapper.create("tool upc", 1, 100, "tool description");
		PowerTool powerTool = PowerToolMapper.create("pt upc", 2, 200, "power tool description", true);
		Nail nail = NailMapper.create("nail upc", 3, 300, 1.05, 20);
		StripNail stripNail = StripNailMapper.create("sn upc", 4, 400, 2.97, 15);
		
		assertNotEquals(-1, tool.getId());
		assertNotEquals(-1, powerTool.getId());
		assertNotEquals(-1, nail.getId());
		assertNotEquals(-1, stripNail.getId());
		
		Tool foundTool = (Tool)InventoryItemSearcher.find(tool.getId());
		PowerTool foundPowerTool = (PowerTool)InventoryItemSearcher.find(powerTool.getId());
		Nail foundNail = (Nail)InventoryItemSearcher.find(nail.getId());
		StripNail foundStripNail = (StripNail)InventoryItemSearcher.find(stripNail.getId());
		
		assertEquals(tool.getId(), foundTool.getId());
		assertEquals("tool upc", foundTool.getUpc());
		assertEquals(1, foundTool.getManufacturerId());
		assertEquals(100, foundTool.getPrice());
		assertEquals("tool description", foundTool.getDescription());
		
		assertEquals(powerTool.getId(), foundPowerTool.getId());
		assertEquals("pt upc", foundPowerTool.getUpc());
		assertEquals(2, foundPowerTool.getManufacturerId());
		assertEquals(200, foundPowerTool.getPrice());
		assertEquals("power tool description", foundPowerTool.getDescription());
		assertEquals(true, foundPowerTool.isBatteryPowered());
		
		assertEquals(nail.getId(), foundNail.getId());
		assertEquals("nail upc", foundNail.getUpc());
		assertEquals(3, foundNail.getManufacturerId());
		assertEquals(300, foundNail.getPrice());
		assertEquals(1.05, foundNail.getLength(), 0.001);
		assertEquals(20, foundNail.getnumberInBox());
		
		assertEquals(stripNail.getId(), foundStripNail.getId());
		assertEquals("sn upc", foundStripNail.getUpc());
		assertEquals(4, foundStripNail.getManufacturerId());
		assertEquals(400, foundStripNail.getPrice());
		assertEquals(2.97, foundStripNail.getLength(), 0.001);
		assertEquals(15, foundStripNail.getNumberinStrip());
	}
	
	/**
	 * Tests findbyUpc method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testFindByUpc() throws ClassNotFoundException, SQLException {
		Tool tool = ToolMapper.create("tool upc", 1, 100, "tool description");
		PowerTool powerTool = PowerToolMapper.create("pt upc", 2, 200, "power tool description", true);
		Nail nail = NailMapper.create("nail upc", 3, 300, 1.05, 20);
		StripNail stripNail = StripNailMapper.create("sn upc", 4, 400, 2.97, 15);
		
		assertNotEquals(-1, tool.getId());
		assertNotEquals(-1, powerTool.getId());
		assertNotEquals(-1, nail.getId());
		assertNotEquals(-1, stripNail.getId());
		
		Tool foundTool = (Tool)InventoryItemSearcher.findByUpc("tool upc");
		PowerTool foundPowerTool = (PowerTool)InventoryItemSearcher.findByUpc("pt upc");
		Nail foundNail = (Nail)InventoryItemSearcher.findByUpc("nail upc");
		StripNail foundStripNail = (StripNail)InventoryItemSearcher.findByUpc("sn upc");
		
		assertEquals(tool.getId(), foundTool.getId());
		assertEquals("tool upc", foundTool.getUpc());
		assertEquals(1, foundTool.getManufacturerId());
		assertEquals(100, foundTool.getPrice());
		assertEquals("tool description", foundTool.getDescription());
		
		assertEquals(powerTool.getId(), foundPowerTool.getId());
		assertEquals("pt upc", foundPowerTool.getUpc());
		assertEquals(2, foundPowerTool.getManufacturerId());
		assertEquals(200, foundPowerTool.getPrice());
		assertEquals("power tool description", foundPowerTool.getDescription());
		assertEquals(true, foundPowerTool.isBatteryPowered());
		
		assertEquals(nail.getId(), foundNail.getId());
		assertEquals("nail upc", foundNail.getUpc());
		assertEquals(3, foundNail.getManufacturerId());
		assertEquals(300, foundNail.getPrice());
		assertEquals(1.05, foundNail.getLength(), 0.001);
		assertEquals(20, foundNail.getnumberInBox());
		
		assertEquals(stripNail.getId(), foundStripNail.getId());
		assertEquals("sn upc", foundStripNail.getUpc());
		assertEquals(4, foundStripNail.getManufacturerId());
		assertEquals(400, foundStripNail.getPrice());
		assertEquals(2.97, foundStripNail.getLength(), 0.001);
		assertEquals(15, foundStripNail.getNumberinStrip());
	}
	

}

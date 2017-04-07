package runner;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.inventory_item.InventoryItem;
import domain.nail.Nail;
import domain.nail.NailMapper;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;
import domain.tool.Tool;
import domain.tool.ToolMapper;

/**
 * Test runner.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestRunnerUpc {
	
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
     * Test FindByUpc Method
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
	@Test
	public void testFindByUpc() throws ClassNotFoundException, SQLException {
		Tool tool = ToolMapper.create("tool upc", 1, 1, "tool desc");
		PowerTool powerTool = PowerToolMapper.create("pt upc", 2, 2, "pt desc", true);
		Nail nail = NailMapper.create("nail upc", 3, 3, 3.0, 3);
		StripNail stripNail = StripNailMapper.create("sn upc", 4, 4, 4.0, 4);
		 
		Tool foundTool = (Tool)RunnerUpc.findByUpc("tool upc");
		PowerTool foundPowerTool = (PowerTool)RunnerUpc.findByUpc("pt upc");
		Nail foundNail = (Nail)RunnerUpc.findByUpc("nail upc");
		StripNail foundStripNail = (StripNail)RunnerUpc.findByUpc("sn upc");
		
		assertEquals(tool.getId(), foundTool.getId());
		assertEquals("tool upc", foundTool.getUpc());
		assertEquals(1, foundTool.getManufacturerId());
		assertEquals(1, foundTool.getPrice());
		assertEquals("tool desc", foundTool.getDescription());
		
		assertEquals(powerTool.getId(), foundPowerTool.getId());
		assertEquals("pt upc", foundPowerTool.getUpc());
		assertEquals(2, foundPowerTool.getManufacturerId());
		assertEquals(2, foundPowerTool.getPrice());
		assertEquals("pt desc", foundPowerTool.getDescription());
		assertEquals(true, foundPowerTool.isBatteryPowered());
		
		assertEquals(nail.getId(), foundNail.getId());
		assertEquals("nail upc", foundNail.getUpc());
		assertEquals(3, foundNail.getManufacturerId());
		assertEquals(3, foundNail.getPrice());
		assertEquals(3.0, foundNail.getLength(), 0.01);
		assertEquals(3, foundNail.getnumberInBox());
		
		assertEquals(stripNail.getId(), foundStripNail.getId());
		assertEquals("sn upc", foundStripNail.getUpc());
		assertEquals(4, foundStripNail.getManufacturerId());
		assertEquals(4, foundStripNail.getPrice());
		assertEquals(4.0, foundStripNail.getLength(), 0.01);
		assertEquals(4, foundStripNail.getNumberinStrip());
	}
	
	/**
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
	@Test
	public void testFindToolByUpc() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String lineSeparator = System.getProperty("line.separator");
		
		ToolMapper.create("tool upc", 1, 1, "tool desc");
		
		String inputData = "tool upc\nexit";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerUpc.main(new String[0]);
		String outputData = new String(outputBuffer.toByteArray());
		String expectedOutput = "Please provide a upc or \"exit\" to exit system: Tool(upc: tool upc, manufacturerId: 1, price: 1, description: tool desc)"+lineSeparator+"\nPlease provide a upc or \"exit\" to exit system: Thank you for using this system!\n"+lineSeparator;

		assertEquals(expectedOutput, outputData);
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
	@Test
	public void testFindInvalidByUpc() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String lineSeparator = System.getProperty("line.separator");
		
		String inputData = "fake upc\nexit";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerUpc.main(new String[0]);
		String outputData = new String(outputBuffer.toByteArray());
		System.setIn(originalIn);
		System.setOut(originalOut);
		String expectedOutput = "Please provide a upc or \"exit\" to exit system: Unable to locate a matching inventory item.\n"+lineSeparator+"\nPlease provide a upc or \"exit\" to exit system: Thank you for using this system!\n"+lineSeparator;
		assertEquals(expectedOutput, outputData);
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * Tests associatedItems() method in RunnerUpc. 
	 * @throws SQLException  SQLException
	 * @throws ClassNotFoundException ClassNotFoundException 
	 *  
	 */
	@Test
	public void testAssociatedItems() throws ClassNotFoundException, SQLException{
		Nail nail = NailMapper.create("nail upc", 3, 3, 3.0, 3);
		assertEquals(RunnerUpc.associatedItems(nail).size(),0);
		PowerTool tool = PowerToolMapper.create("pt_upc", 21, 42, "power_tool_description", true);
    	assertEquals(0,tool.getStripNails().size());
    	StripNail nail1 = StripNailMapper.create("sn_upc1", 7, 90, 1.8, 25);
    	StripNail nail2 = StripNailMapper.create("sn_upc2", 7, 90, 1.8, 25);
    	StripNail nail3 = StripNailMapper.create("sn_upc3", 7, 90, 1.8, 25);
    	tool.addStripNailRelations(nail1);
    	tool.addStripNailRelations(nail2);
    	tool.addStripNailRelations(nail3);
    	PowerTool copy = PowerToolMapper.find(tool.getId());
    	assertEquals(3,RunnerUpc.associatedItems(copy).size());
    	List<InventoryItem> list = RunnerUpc.associatedItems(copy);
    	assertTrue(((StripNail)list.get(0)).equals(nail1));
    	assertTrue(((StripNail)list.get(1)).equals(nail2));
    	assertTrue(((StripNail)list.get(2)).equals(nail3));	
	}
	
	/**
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
	@Test
	public void testFindAssociatedItems() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		String lineSeparator = System.getProperty("line.separator");
		
		PowerTool tool = PowerToolMapper.create("pt_upc", 21, 42, "power_tool_description", true);
    	StripNail nail1 = StripNailMapper.create("sn_upc1", 7, 90, 1.8, 25);
    	StripNail nail2 = StripNailMapper.create("sn_upc2", 7, 90, 1.8, 25);
    	StripNail nail3 = StripNailMapper.create("sn_upc3", 7, 90, 1.8, 25);
    	tool.addStripNailRelations(nail1);
    	tool.addStripNailRelations(nail2);
    	tool.addStripNailRelations(nail3);
		
		String inputData = "pt_upc\nexit";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerUpc.main(new String[0]);
		String outputData = new String(outputBuffer.toByteArray());
		String expectedOutput = "Please provide a upc or \"exit\" to exit system: PowerTool(upc: pt_upc, manufacturerId: 21, price: 42, description: power_tool_description, batteryPowered: true)"+lineSeparator+"\nAssociated Items:"+lineSeparator+"StripNail(upc: sn_upc1, manufacturerId: 7, price: 90, length: 1.8, numberInStrip: 25)"+lineSeparator+"StripNail(upc: sn_upc2, manufacturerId: 7, price: 90, length: 1.8, numberInStrip: 25)"+lineSeparator+"StripNail(upc: sn_upc3, manufacturerId: 7, price: 90, length: 1.8, numberInStrip: 25)"+lineSeparator+"\nPlease provide a upc or \"exit\" to exit system: Thank you for using this system!\n"+lineSeparator;
		assertEquals(expectedOutput, outputData);
	
		System.setIn(originalIn);
		System.setOut(originalOut);
	}

}

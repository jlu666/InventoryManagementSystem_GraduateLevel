package runner;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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
 * It is used to test PowerToolSequence.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestRunnerWrite {
	
	/**
	 * Sets up environment
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Before
	public void setup() throws ClassNotFoundException, SQLException {
		Database.beginTransaction();
	}
	
	/**
	 * Rollbacks.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@After
	public void teardown() throws ClassNotFoundException, SQLException {
		Database.rollbackTransaction();
	}
	
	/**
	 * Tests insert method and execute method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInsertPowerTool() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\npt\n\n123456\n123456\ntest tool description\ntrue\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
	
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: No existing item found.  Inserting new item."+lineSeparator +"Enter type code (n, sn, t, pt): Please enter upc (default 123456): Please enter ManufacturerID: Please enter item price: Enter a description: Please enter whether the battery is powered(true or false): Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		PowerTool powerTool = (PowerTool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", powerTool.getUpc());
		assertEquals(123456, powerTool.getManufacturerId());
		assertEquals(123456, powerTool.getPrice());
		assertEquals("test tool description", powerTool.getDescription());
		assertEquals(true, powerTool.isBatteryPowered());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInsertTool() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\nt\n\n123456\n123456\ntest tool description";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: No existing item found.  Inserting new item."+lineSeparator+"Enter type code (n, sn, t, pt): Please enter upc (default 123456): Please enter ManufacturerID: Please enter item price: Enter a description: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		Tool tool = (Tool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", tool.getUpc());
		assertEquals(123456, tool.getManufacturerId());
		assertEquals(123456, tool.getPrice());
		assertEquals("test tool description", tool.getDescription());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInsertNail() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\nn\n\n123456\n123456\n14.5\n18";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: No existing item found.  Inserting new item."+lineSeparator+"Enter type code (n, sn, t, pt): Please enter upc (default 123456): Please enter ManufacturerID: Please enter item price: Please enter the length of Nails: Pleaser enter the number in box: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		Nail nail = (Nail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", nail.getUpc());
		assertEquals(123456, nail.getManufacturerId());
		assertEquals(123456, nail.getPrice());
		assertEquals(14.5, nail.getLength(), 0.01);
		assertEquals(18, nail.getnumberInBox());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInsertStripNail() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\nsn\n\n123456\n123456\n14.5\n18\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: No existing item found.  Inserting new item."+lineSeparator+"Enter type code (n, sn, t, pt): Please enter upc (default 123456): Please enter ManufacturerID: Please enter item price: Please enter the length of Nails: Pleaser enter the number in strip: Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		StripNail nail = (StripNail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", nail.getUpc());
		assertEquals(123456, nail.getManufacturerId());
		assertEquals(123456, nail.getPrice());
		assertEquals(14.5, nail.getLength(), 0.01);
		assertEquals(18, nail.getNumberinStrip());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdatePowerTool() throws ClassNotFoundException, SQLException {
		PowerToolMapper.create("123456", 123456, 123456, "test tool description", true);
		
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\n\n\n\nreal test tool description\n\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: Existing item found.  Updating item."+lineSeparator+"Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Enter a description (default test tool description): Please enter whether the battery is powered(true or false) (default true): Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		PowerTool powerTool = (PowerTool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", powerTool.getUpc());
		assertEquals(123456, powerTool.getManufacturerId());
		assertEquals(123456, powerTool.getPrice());
		assertEquals("real test tool description", powerTool.getDescription());
		assertEquals(true, powerTool.isBatteryPowered());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdateTool() throws ClassNotFoundException, SQLException {
		ToolMapper.create("123456", 123456, 123456, "test tool description");
		
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\n\n\n\nreal test tool description";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: Existing item found.  Updating item."+lineSeparator+"Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Enter a description (default test tool description): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		Tool tool = (Tool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", tool.getUpc());
		assertEquals(123456, tool.getManufacturerId());
		assertEquals(123456, tool.getPrice());
		assertEquals("real test tool description", tool.getDescription());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdateNail() throws ClassNotFoundException, SQLException {
		NailMapper.create("123456", 123456, 123456, 14.5, 18);
		
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\n\n\n\n\n23";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: Existing item found.  Updating item."+lineSeparator+"Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Please enter the length of Nails (default 14.5): Pleaser enter the number in box (default 18): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		Nail nail = (Nail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", nail.getUpc());
		assertEquals(123456, nail.getManufacturerId());
		assertEquals(123456, nail.getPrice());
		assertEquals(14.5, nail.getLength(), 0.01);
		assertEquals(23, nail.getnumberInBox());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdateStripNail() throws ClassNotFoundException, SQLException {
		StripNailMapper.create("123456", 123456, 123456, 14.5, 18);
		
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "123456\n\n\n\n\n23\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: Existing item found.  Updating item."+lineSeparator+"Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Please enter the length of Nails (default 14.5): Pleaser enter the number in strip (default 18): Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		StripNail nail = (StripNail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", nail.getUpc());
		assertEquals(123456, nail.getManufacturerId());
		assertEquals(123456, nail.getPrice());
		assertEquals(14.5, nail.getLength(), 0.01);
		assertEquals(23, nail.getNumberinStrip());
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testNoInput() throws ClassNotFoundException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		
		String inputData = "";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerWrite.main(new String[0]);
		String lineSeparator = System.getProperty("line.separator");
		String expectedOutput ="A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: No input detected."+lineSeparator;
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}

}

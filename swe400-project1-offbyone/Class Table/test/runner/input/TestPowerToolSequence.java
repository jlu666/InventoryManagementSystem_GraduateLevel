package runner.input;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.inventory_item.InventoryItemSearcher;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;

/**
 * It is used to test PowerToolSequence.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestPowerToolSequence {
	
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
	public void testInsert() throws ClassNotFoundException, SQLException {
		String inputData = "123456\n123456\n123456\ntest tool description\ntrue\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		PowerToolSequence sequence = new PowerToolSequence(outputStream, inputScanner);
		sequence.execute();
		String expectedOutput ="Please enter upc: Please enter ManufacturerID: Please enter item price: Enter a description: Please enter whether the battery is powered(true or false): Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.insert();
		PowerTool powerTool = (PowerTool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", powerTool.getUpc());
		assertEquals(123456, powerTool.getManufacturerId());
		assertEquals(123456, powerTool.getPrice());
		assertEquals("test tool description", powerTool.getDescription());
		assertEquals(true, powerTool.isBatteryPowered());
	}
	
	/**
	 * Tests insert relationship.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInsertRelationShip() throws ClassNotFoundException, SQLException {
		StripNail nail = StripNailMapper.create("5555", 1000, 10, 10, 50);		
		String inputData = "123456\n123456\n123456\ntest tool description\nfalse\ny\n5555";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		PowerToolSequence sequence = new PowerToolSequence(outputStream, inputScanner);
		sequence.execute();
		String expectedOutput ="Please enter upc: Please enter ManufacturerID: Please enter item price: Enter a description: Please enter whether the battery is powered(true or false): Do you want to insert relationship(y or n): Please enter the upc which you want to create a relationship: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.insert();
		PowerTool powerTool = (PowerTool)InventoryItemSearcher.findByUpc("123456");
		assertEquals(powerTool.getStripNails().get(0).toString(),nail.toString());
	}
	
	/**
	 * Tests update method and execute method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdate() throws ClassNotFoundException, SQLException {
		PowerTool tool = PowerToolMapper.create("123456", 123456, 123456, "test tool description", true);
		String inputData = "\n\n888\ntool test\nfalse\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		PowerToolSequence sequence = new PowerToolSequence(outputStream, inputScanner, tool);
		sequence.execute();
		String expectedOutput ="Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Enter a description (default test tool description): Please enter whether the battery is powered(true or false) (default true): Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.update();
		PowerTool foundPowerTool = (PowerTool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", foundPowerTool.getUpc());
		assertEquals(123456, foundPowerTool.getManufacturerId());
		assertEquals(888, foundPowerTool.getPrice());
		assertEquals("tool test", foundPowerTool.getDescription());
		assertEquals(false, foundPowerTool.isBatteryPowered());
	}
	
	/**
	 * Tests update relationship.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdateRelationship() throws ClassNotFoundException, SQLException {
		PowerTool tool = PowerToolMapper.create("123456", 123456, 123456, "test tool description", false);
		StripNail nail = StripNailMapper.create("5555", 1000, 10, 10, 50);
		StripNail nail2 = StripNailMapper.create("6666", 1000, 10, 10, 50);	
		String inputData = "\n\n888\ntool test\ntrue\ny\n5555";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		PowerToolSequence sequence = new PowerToolSequence(outputStream, inputScanner, tool);
		sequence.execute();
		String expectedOutput ="Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Enter a description (default test tool description): Please enter whether the battery is powered(true or false) (default false): Do you want to insert relationship(y or n): Please enter the upc which you want to create a relationship: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.update();
		 inputData = "\n\n888\ntool test\nfalse\ny\n6666";
		inputStream = new ByteArrayInputStream(inputData.getBytes());
		inputScanner = new Scanner(inputStream);
		sequence = new PowerToolSequence(outputStream, inputScanner, tool);
		sequence.execute();
		sequence.update();
		PowerTool powerTool = (PowerTool)InventoryItemSearcher.findByUpc("123456");
		assertEquals(powerTool.getStripNails().get(0).toString(),nail.toString());
		assertEquals(powerTool.getStripNails().get(1).toString(),nail2.toString());
	}

}

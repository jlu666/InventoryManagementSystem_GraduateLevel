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
 * It is used to test StripNailSequence.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestStripNailSequence {
	
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
		String inputData = "123456\n123456\n123456\n3.14\n15\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		StripNailSequence sequence = new StripNailSequence(outputStream, inputScanner);
		sequence.execute();
		String expectedOutput ="Please enter upc: Please enter ManufacturerID: Please enter item price: Please enter the length of Nails: Pleaser enter the number in strip: Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.insert();
		StripNail nail = (StripNail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", nail.getUpc());
		assertEquals(123456, nail.getManufacturerId());
		assertEquals(123456, nail.getPrice());
		assertEquals(3.14, nail.getLength(), 0.01);
		assertEquals(15, nail.getNumberinStrip());
	}
	
	/**
	 * Tests insert relationship.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testInsertRelationship() throws ClassNotFoundException, SQLException {
		PowerTool tool = PowerToolMapper.create("5555", 100, 10, "Power tool", false);
		String inputData = "123456\n123456\n123456\n3.14\n15\ny\n5555";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		StripNailSequence sequence = new StripNailSequence(outputStream, inputScanner);
		sequence.execute();
		String expectedOutput ="Please enter upc: Please enter ManufacturerID: Please enter item price: Please enter the length of Nails: Pleaser enter the number in strip: Do you want to insert relationship(y or n): "
				+ "Please enter the upc which you want to create a relationship: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.insert();
		StripNail nail = (StripNail)InventoryItemSearcher.findByUpc("123456");
		assertEquals(tool.toString(),nail.getPowerTools().get(0).toString());
	}
	
	/**
	 * Tests update method and execute method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdate() throws ClassNotFoundException, SQLException {
		StripNail nail = StripNailMapper.create("123456", 123456, 123456, 3.14, 15);
		String inputData = "\n\n888\n\n19\nn";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		StripNailSequence sequence = new StripNailSequence(outputStream, inputScanner, nail);
		sequence.execute();
		String expectedOutput ="Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Please enter the length of Nails (default 3.14): Pleaser enter the number in strip (default 15): Do you want to insert relationship(y or n): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.update();
		StripNail foundNail = (StripNail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", foundNail.getUpc());
		assertEquals(123456, foundNail.getManufacturerId());
		assertEquals(888, foundNail.getPrice());
		assertEquals(3.14, foundNail.getLength(), 0.01);
		assertEquals(19, foundNail.getNumberinStrip());
	}
	
	/**
	 * Tests update relationship.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdateRelationShip() throws ClassNotFoundException, SQLException {
		PowerTool tool = PowerToolMapper.create("5555", 100, 10, "Power tool", false);
		PowerTool tool2 = PowerToolMapper.create("6666", 100, 10, "Power tool", false);
		StripNail nail = StripNailMapper.create("123456", 123456, 123456, 3.14, 15);
		String inputData = "\n\n888\n\n19\ny\n5555";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		StripNailSequence sequence = new StripNailSequence(outputStream, inputScanner, nail);
		sequence.execute();
		String expectedOutput ="Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Please enter the length of Nails (default 3.14): Pleaser enter the number in strip (default 15): Do you want to insert relationship(y or n): Please enter the upc which you want to create a relationship: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.update();
		
		inputData = "\n\n888\n\n19\ny\n6666";
		inputStream = new ByteArrayInputStream(inputData.getBytes());
		inputScanner = new Scanner(inputStream);
		sequence = new StripNailSequence(outputStream, inputScanner, nail);
		sequence.execute();
		assertEquals(expectedOutput, outputData);
		sequence.update();
		assertEquals(tool.toString(),nail.getPowerTools().get(0).toString());
		assertEquals(tool2.toString(),nail.getPowerTools().get(1).toString());
	}

}

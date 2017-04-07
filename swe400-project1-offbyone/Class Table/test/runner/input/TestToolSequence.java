package runner.input;

import static org.junit.Assert.*;

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
import domain.tool.Tool;
import domain.tool.ToolMapper;

/**
 * It is used to test ToolSequence.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestToolSequence {
	
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
		String inputData = "123456\n123456\n123456\ntest tool description";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		ToolSequence sequence = new ToolSequence(outputStream, inputScanner);
		sequence.execute();
		String expectedOutput ="Please enter upc: Please enter ManufacturerID: Please enter item price: Enter a description: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.insert();
		Tool tool = (Tool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", tool.getUpc());
		assertEquals(123456, tool.getManufacturerId());
		assertEquals(123456, tool.getPrice());
		assertEquals("test tool description", tool.getDescription());
	}
	
	/**
	 * Tests update method and execute method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdate() throws ClassNotFoundException, SQLException {
		Tool tool = ToolMapper.create("123456", 123456, 123456, "test tool description");
		String inputData = "\n\n888\ntool test";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		ToolSequence sequence = new ToolSequence(outputStream, inputScanner, tool);
		sequence.execute();
		String expectedOutput ="Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Enter a description (default test tool description): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.update();
		Tool foundTool = (Tool)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", foundTool.getUpc());
		assertEquals(123456, foundTool.getManufacturerId());
		assertEquals(888, foundTool.getPrice());
		assertEquals("tool test", foundTool.getDescription());
	}

}

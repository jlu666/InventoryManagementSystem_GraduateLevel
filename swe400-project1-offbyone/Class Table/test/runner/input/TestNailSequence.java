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
import domain.nail.Nail;
import domain.nail.NailMapper;

/**
 * It is used to test NailSequence.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestNailSequence {
	
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
		String inputData = "123456\n123456\n123456\n3.14\n15";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		NailSequence sequence = new NailSequence(outputStream, inputScanner);
		sequence.execute();
		String expectedOutput ="Please enter upc: Please enter ManufacturerID: Please enter item price: Please enter the length of Nails: Pleaser enter the number in box: ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.insert();
		Nail nail = (Nail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", nail.getUpc());
		assertEquals(123456, nail.getManufacturerId());
		assertEquals(123456, nail.getPrice());
		assertEquals(3.14, nail.getLength(), 0.01);
		assertEquals(15, nail.getnumberInBox());
	}
	
	/**
	 * Tests update method and execute method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testUpdate() throws ClassNotFoundException, SQLException {
		Nail nail = NailMapper.create("123456", 123456, 123456, 3.14, 15);
		String inputData = "\n\n888\n\n19";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		NailSequence sequence = new NailSequence(outputStream, inputScanner, nail);
		sequence.execute();
		String expectedOutput ="Please enter upc (default 123456): Please enter ManufacturerID (default 123456): Please enter item price (default 123456): Please enter the length of Nails (default 3.14): Pleaser enter the number in box (default 15): ";
		String outputData = new String(outputBuffer.toByteArray());
		assertEquals(expectedOutput, outputData);
		sequence.update();
		Nail foundNail = (Nail)InventoryItemSearcher.findByUpc("123456");
		assertEquals("123456", foundNail.getUpc());
		assertEquals(123456, foundNail.getManufacturerId());
		assertEquals(888, foundNail.getPrice());
		assertEquals(3.14, foundNail.getLength(), 0.01);
		assertEquals(19, foundNail.getnumberInBox());
	}

}

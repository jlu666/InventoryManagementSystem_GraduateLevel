package runner;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;

/**
 * Test runner.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestRunnerList {
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Before
	public void setup() throws ClassNotFoundException, SQLException {
		Database.beginTransaction();
	}
	
	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@After
	public void teardown() throws ClassNotFoundException, SQLException {
		Database.rollbackTransaction();
	}

	/**
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws FileNotFoundException FileNotFoundException
	 * @throws IOException IOException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testList() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		String lineSeparator = System.getProperty("line.separator");
		
		String inputData = "";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerSchema.main(new String[0]);
		String outputData = new String(outputBuffer.toByteArray());
		String expectedOutput = "Reconstructing schema... Done."+lineSeparator+"Inserting data... Done."+lineSeparator+"Inserting relations... Done."+lineSeparator;
		assertEquals(expectedOutput, outputData);
		
		System.setIn(originalIn);
		System.setOut(originalOut);
		
		inputData = "\n";
		inputStream = new ByteArrayInputStream(inputData.getBytes());
		outputBuffer = new ByteArrayOutputStream();
		outputStream = new PrintStream(outputBuffer);
		
		System.setIn(inputStream);
		System.setOut(outputStream);
		
		RunnerList.main(new String[0]);
		outputData = new String(outputBuffer.toByteArray());
		
		expectedOutput = "Listing inventory items..."+lineSeparator+"Nail(upc: 5453432767, manufacturerId: 15, price: 1348, length: 3.0, numberInBox: 500)"+lineSeparator+"Nail(upc: 4343412343, manufacturerId: 27, price: 1899, length: 3.0, numberInBox: 750)"+lineSeparator+"Nail(upc: 9876711127, manufacturerId: 15, price: 1212, length: 2.5, numberInBox: 500)"+lineSeparator+"Nail(upc: 6562229876, manufacturerId: 27, price: 1988, length: 2.5, numberInBox: 750)"+lineSeparator+"Nail(upc: 4343432345, manufacturerId: 13, price: 1576, length: 1.5, numberInBox: 150)"+lineSeparator+"PowerTool(upc: 1231231234, manufacturerId: 13, price: 39900, description: Pheumatic Nail Gun, batteryPowered: false)"+lineSeparator+"PowerTool(upc: 4445553333, manufacturerId: 24, price: 42999, description: Framing Nail Gun, batteryPowered: false)"+lineSeparator+"PowerTool(upc: 7657896543, manufacturerId: 24, price: 39654, description: Standard 10 inch table saw, batteryPowered: false)"+lineSeparator+"PowerTool(upc: 9993458585, manufacturerId: 13, price: 55555, description: Portable 10 inch table saw, batteryPowered: false)"+lineSeparator+"PowerTool(upc: 7654564848, manufacturerId: 24, price: 33424, description: Cordless brad nailer, batteryPowered: true)"+lineSeparator+"PowerTool(upc: 7784452828, manufacturerId: 13, price: 15758, description: Brad nailer, batteryPowered: false)"+lineSeparator+"StripNail(upc: 5453432345, manufacturerId: 13, price: 1099, length: 2.5, numberInStrip: 50)"+lineSeparator+"StripNail(upc: 4343434543, manufacturerId: 24, price: 1299, length: 3.0, numberInStrip: 75)"+lineSeparator+"StripNail(upc: 9876784727, manufacturerId: 13, price: 2099, length: 2.5, numberInStrip: 50)"+lineSeparator+"StripNail(upc: 6565459876, manufacturerId: 24, price: 1988, length: 1.5, numberInStrip: 150)"+lineSeparator+"StripNail(upc: 4343432346, manufacturerId: 13, price: 1576, length: 1.5, numberInStrip: 150)"+lineSeparator+"Tool(upc: 0121232234, manufacturerId: 32, price: 899, description: Ball Peen Hammer)"+lineSeparator+"Tool(upc: 1232343345, manufacturerId: 42, price: 3999, description: Ten piece screwdriver set)"+lineSeparator+"Tool(upc: 1111111111, manufacturerId: 42, price: 5999, description: Thirty piece wrench set (English))"+lineSeparator+"Tool(upc: 23423423232, manufacturerId: 33, price: 1997, description: Lumber Lok Vise)"+lineSeparator+"Tool(upc: 23424444232, manufacturerId: 33, price: 1997, description: 4 in 1 Steel Vise)"+lineSeparator;
		assertEquals(expectedOutput, outputData);
		
		System.setIn(originalIn);
		System.setOut(originalOut);
	}

}

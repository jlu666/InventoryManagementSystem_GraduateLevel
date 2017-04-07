import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import datasource.DatabaseGateway;
import datasource.PowerToolStripNailRelationGateway;
import domain.PowerToolsToStripNailsMapper;
import enums.EnumInsert;
import model.InventoryItem;
import model.Nail;
import model.PowerTool;
import model.PowerToolStripNailRelation;
import model.StripNail;
import model.Tool;
import runner.Runner;

/**
 * Tests the Runner class
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestRunner 
{

	/**
	 * Empties out database of any pre-existing data
	 * @throws SQLException
	 */
	@BeforeClass
	public static void clearDatabase() throws SQLException
	{
		DatabaseGateway.createDatabase();
	}
	/**
	 * Sets up transaction
	 * @throws SQLException
	 */
	
	@Before
	public void setUpTransaction() throws SQLException
	{
		DatabaseGateway.getConnection();
		DatabaseGateway.setTransaction();
	}
	
	/**
	 * Rolls back transaction
	 * @throws SQLException
	 */
	@After
	public void rollbackTtansaction() throws SQLException
	{
		DatabaseGateway.rollbackTransaction();
		DatabaseGateway.resetAutoIncrement();
		DatabaseGateway.dropAndRecreateRelationsTable();
	}
	
	/**
	 * Test that each type of inventory item can be found in the database
	 * and that if an invalid id is given, returns null
	 * @throws SQLException
	 */
	@Test
	public void testFindItemsByUPC() throws SQLException 
	{
		new Nail("1111-1111",5,5,5.0,20);		
		InventoryItem expectedNailItem = Runner.findItemByUPC("1111-1111");
		Nail nailitem = (Nail)expectedNailItem;
		assertEquals(nailitem.getID() ,1);
		assertEquals(nailitem.getManufacturerID(),5);
		assertEquals(nailitem.getPrice(),5);
		assertEquals(nailitem.getUPC(),"1111-1111");
		assertEquals(nailitem.getLength(), 5.0, .001);
		assertEquals(nailitem.getNumberInBox(), 20);
		
		new StripNail("2222-2222", 5, 5, 5.0, 0);
		InventoryItem expectedStripNailItem = Runner.findItemByUPC("2222-2222");
		StripNail stripNailItem = (StripNail)expectedStripNailItem;
		assertEquals(stripNailItem.getID() ,2);
		assertEquals(stripNailItem.getManufacturerID(),5);
		assertEquals(stripNailItem.getPrice(),5);
		assertEquals(stripNailItem.getUPC(),"2222-2222");
		assertEquals(stripNailItem.getLength(), 5.0, .001);
		assertEquals(stripNailItem.getNumberInStrip(), 0);
		
		new Tool("3333-3333", 2, 2, "toolDescription");
		InventoryItem expectedToolItem = Runner.findItemByUPC("3333-3333");
		Tool toolItem = (Tool)expectedToolItem;
		assertEquals(toolItem.getID() , 3);
		assertEquals(toolItem.getManufacturerID(),2);
		assertEquals(toolItem.getPrice(),2);
		assertEquals(toolItem.getUPC(),"3333-3333");
		assertEquals(toolItem.getDescription(), "toolDescription");
		
		new PowerTool("4444-4444", 2, 2, "PowerToolDescription", true);
		InventoryItem expectedPowerToolItem = Runner.findItemByUPC("4444-4444");
		PowerTool powerToolItem = (PowerTool)expectedPowerToolItem;
		assertEquals(powerToolItem.getID() , 4);
		assertEquals(powerToolItem.getManufacturerID(),2);
		assertEquals(powerToolItem.getPrice(),2);
		assertEquals(powerToolItem.getUPC(),"4444-4444");
		assertEquals(powerToolItem.getDescription(), "PowerToolDescription");
		assertEquals(powerToolItem.getBatteryPowered(), true);
		
		InventoryItem invalidInventoryItem = Runner.findItemByUPC("999999999");
		assertNull(invalidInventoryItem);		
	}
	
	/**
	 * Tests all the inserts complete successfully are in the database
	 * If any of the inserts are unsuccessful, the boolean won't be set to true,
	 * making it fail.
	 * @throws SQLException
	 */
	@Test
	public void testInsertion() throws SQLException
	{
		boolean allEnumsInserted = false;
		try{
		EnumInsert.insertPowerToolsEnums();
		EnumInsert.insertStripNailsEnums();
		EnumInsert.insertRelations();
		EnumInsert.insertToolsEnums();
		EnumInsert.insertNailsEnums();
		allEnumsInserted = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		assertEquals(allEnumsInserted, true);
	}
	/**
	 * Tests the insertTool method in the Runner class 
	 * by mocking user input and then checking to see if the tool
	 * Exists in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerInsertTool() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("123\n23\n22\nNewTool\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		Runner.insertTool();
		Tool tool = new Tool("123");
		assertEquals(tool.getUPC(),"123");
		assertEquals(tool.getDescription(),"NewTool");
		assertEquals(tool.getPrice(),22);
		assertEquals(tool.getManufacturerID(),23);
	}
	
	/**
	 * Tests the insertNail method in the Runner class 
	 * by mocking user input and then checking to see if the Nail
	 * Exists in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerInsertNail() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("123\n23\n22\n11.5\n100\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		Runner.insertNail();
		Nail nail = new Nail("123");
		assertEquals(nail.getUPC(),"123");
		assertEquals(11.5,nail.getLength(),.001);
		assertEquals(nail.getPrice(),22);
		assertEquals(nail.getManufacturerID(),23);
		assertEquals(nail.getNumberInBox(),100);
	}
	
	/**
	 * Tests the insertPowerTool method in the Runner class 
	 * by mocking user input and then checking to see if the PowerTool
	 * Exists in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerInsertPowerTool() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("123\n23\n22\nNewTool\ntrue\nN\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		Runner.insertPowerTool();
		PowerTool powerTool = new PowerTool("123");
		assertEquals(powerTool.getUPC(),"123");
		assertEquals(powerTool.getDescription(),"NewTool");
		assertEquals(powerTool.getPrice(),22);
		assertEquals(powerTool.getManufacturerID(),23);
		assertEquals(powerTool.getBatteryPowered(),true);
	}
	
	/**
	 * Tests the insertStripNail method in the Runner class 
	 * by mocking user input and then checking to see if the StripNail
	 * Exists in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerInsertStripNail() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("123\n23\n22\n11.5\n50\nN\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		Runner.insertStripNail();
		StripNail stripNail = new StripNail("123");
		assertEquals(stripNail.getUPC(),"123");
		assertEquals(11.5,stripNail.getLength(),.001);
		assertEquals(stripNail.getPrice(),22);
		assertEquals(stripNail.getManufacturerID(),23);
		assertEquals(stripNail.getNumberInStrip(),50);
	}
	
	/**
	 * Tests the queryItem method to see if it will return 
	 * "No Product with this ID exists" when an incorrect UPC
	 * is entered.
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testRunnerQueryItemWithNoUPC() throws SQLException, IOException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		runner.Runner.queryItem();
		String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		if(System.getProperty("os.name").contains("Windows"))
		{
			assertEquals(content,"Please enter a UPC \r\nNo product with this UPC exists\r\n");
		}
		else if(System.getProperty("os.name").contains("Linux"))
		{
			assertEquals(content,"Please enter a UPC \nNo product with this UPC exists\n");
		}
	}
	
	/**
	 * Test the queryItem method to see if it will return a power tool.
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testRunnerQueryPowerTool() throws SQLException, IOException
	{
		new PowerTool("456", 5, 5, "PowerTooldescription", true);
		ByteArrayInputStream in = new ByteArrayInputStream("456\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		runner.Runner.queryItem();
		String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		if(System.getProperty("os.name").contains("Windows"))
		{
			assertEquals(content,"Please enter a UPC \r\nPowerTool: UPC: 456, Manufacturer ID:5, Price:5, Description:PowerTooldescription, is Battery Powered:true\r\n\r\nStrip Nails associated with this power tool: \r\n");
		}
		else if(System.getProperty("os.name").contains("Linux"))
		{
			assertEquals(content,"Please enter a UPC \nPowerTool: UPC: 456, Manufacturer ID:5, Price:5, Description:PowerTooldescription, is Battery Powered:true\n\nStrip Nails associated with this power tool: \n");
		}
	}
	
	/**
	 * Test the queryItem method to see if it will return a strip nail
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testRunnerQueryStripNail() throws SQLException, IOException
	{
		new StripNail("99999", 5, 5, 5.0, 20);
		ByteArrayInputStream in = new ByteArrayInputStream("99999\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		runner.Runner.queryItem();
		String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		if(System.getProperty("os.name").contains("Windows"))
		{
			assertEquals(content,"Please enter a UPC \r\nStripNail: UPC: 99999, ManufacturerID: 5, Price: 5, Length: 5.0, Number In Strip: 20\r\n\r\nPower tools associated with this strip nail: \r\n");
		}
		else if(System.getProperty("os.name").contains("Linux"))
		{
			assertEquals(content,"Please enter a UPC \nStripNail: UPC: 99999, ManufacturerID: 5, Price: 5, Length: 5.0, Number In Strip: 20\n\nPower tools associated with this strip nail: \n");
		}
	}
	
	
	
	/**
	 * Test the queryItem method to see if it will return a valid item.
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testRunnerQueryItemWithValidUPC() throws SQLException, IOException
	{
		new Nail("asdf",5,5,5.0,20);
		ByteArrayInputStream in = new ByteArrayInputStream("asdf\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		runner.Runner.queryItem();
		String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		if(System.getProperty("os.name").contains("Windows"))
		{
			assertEquals(content,"Please enter a UPC \r\nNail: UPC: asdf, ManufactuerID: 5, Price: 5, Length: 5.0, Number In Box: 20\r\n");
		}
		else if(System.getProperty("os.name").contains("Linux"))
		{
			assertEquals(content,"Please enter a UPC \nNail: UPC: asdf, ManufactuerID: 5, Price: 5, Length: 5.0, Number In Box: 20\n");

		}
	}
	
	/**
	 * Tests the updateStripNail method in the Runner class 
	 * by mocking user input and then checking to see if the StripNail
	 * is updated in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerUpdateStripNail() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("y\n123\ny\n23\ny\n22\ny\n11.5\ny\n50\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		StripNail item = new StripNail("asdf",5,5,5.0,20);
		Runner.updateStripNail(item);
		StripNail Stripnail = new StripNail("123");
		assertEquals(Stripnail.getUPC(),"123");
		assertEquals(11.5,Stripnail.getLength(),.001);
		assertEquals(Stripnail.getPrice(),22);
		assertEquals(Stripnail.getManufacturerID(),23);
		assertEquals(Stripnail.getNumberInStrip(),50);
	}
	
	/**
	 * Tests the updateTool method in the Runner class 
	 * by mocking user input and then checking to see if the tool
	 * is updated in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerUpdateTool() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("y\n123\ny\n23\ny\n22\ny\nNewTool\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		Tool item = new Tool("asdf", 5, 5, "Tooldescription");
		Runner.updateTool(item);
		Tool tool = new Tool("123");
		assertEquals(tool.getUPC(),"123");
		assertEquals(tool.getDescription(),"NewTool");
		assertEquals(tool.getPrice(),22);
		assertEquals(tool.getManufacturerID(),23);
	}
	
	/**
	 * Tests the updateNail method in the Runner class 
	 * by mocking user input and then checking to see if the Nail
	 * is updated in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerUpdateNail() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("y\n123\ny\n23\ny\n22\ny\n11.5\ny\n100\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		Nail item = new Nail("asdf",5,5,5.0,20);
		Runner.updateNail(item);
		Nail nail = new Nail("123");
		assertEquals(nail.getUPC(),"123");
		assertEquals(11.5,nail.getLength(),.001);
		assertEquals(nail.getPrice(),22);
		assertEquals(nail.getManufacturerID(),23);
		assertEquals(nail.getNumberInBox(),100);
	}
	
	/**
	 * Tests the updatePowerTool method in the Runner class 
	 * by mocking user input and then checking to see if the PowerTool
	 * is updated in the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Test
	public void testRunnerUpdatePowerTool() throws IOException, SQLException
	{
		ByteArrayInputStream in = new ByteArrayInputStream("y\n123\ny\n23\ny\n22\ny\nNewTool\ny\n1\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);
		PowerTool item = new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		Runner.updatePowerTool(item);
		PowerTool powerTool = new PowerTool("123");
		assertEquals(powerTool.getUPC(),"123");
		assertEquals(powerTool.getDescription(),"NewTool");
		assertEquals(powerTool.getPrice(),22);
		assertEquals(powerTool.getManufacturerID(),23);
		assertEquals(powerTool.getBatteryPowered(),true);
	}
	
	
	/**
	 * Tests adding a relation to the database
	 * @throws IOException
	 * @throws SQLException
	 */
	@Test
	public void testRunnerAddRelation() throws IOException, SQLException
	{
		new PowerTool("456", 5, 5, "PowerTooldescription", true);
		new StripNail("123",5,5,5.0,20);
		ByteArrayInputStream in = new ByteArrayInputStream("456\n123\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);		
		Runner.addRelation();
		ArrayList<PowerToolStripNailRelation> relations = PowerToolStripNailRelationGateway.getAllRelations();
		assertEquals("456", relations.get(0).getPowerTool().getUPC());
		assertEquals("123", relations.get(0).getStripNail().getUPC());
	}
	
	/**
	 * Tests updating a relation to the database
	 * @throws IOException
	 * @throws SQLException
	 */
	@Test
	public void testRunnerUpdateRelation() throws IOException, SQLException
	{
		PowerTool tool = new PowerTool("456", 5, 5, "PowerTooldescription", true);
		StripNail nail= new StripNail("123",5,5,5.0,20);
		new StripNail("99999", 5, 5, 5.0, 20);
		new PowerTool("55555", 5, 5, "PowerTool2description", true);
		new PowerToolsToStripNailsMapper(tool.getID(),nail.getID()); 
		ByteArrayInputStream in = new ByteArrayInputStream("1\ny\n55555\ny\n123\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);		
		Runner.updateRelation();
		ArrayList<PowerToolStripNailRelation> relations = PowerToolStripNailRelationGateway.getAllRelations();
		assertEquals("55555", relations.get(0).getPowerTool().getUPC());
		assertEquals("123", relations.get(0).getStripNail().getUPC());
	}
	
	/**
	 * Tests invalid choice in main
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testInvalidChoiceInMain() throws SQLException, IOException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(("5\nn\n").getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);		
		Runner.main(null);
		String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		if(System.getProperty("os.name").contains("Windows"))
		{
			assertEquals("Inserting power tools\r\nInserting strip nails\r\nInserting tools\r\nInserting nails\r\nSelect 1 to query an inventory item by UPC\r\nSelect 2 to insert a new inventory item\r\nSelect 3 to update an existing inventory item\r\nSelect 4 to add/update/delete relations\r\nInvalid choice\r\nWant to run the program again? Enter Y to run: ", content);
		}
		else if(System.getProperty("os.name").contains("Linux"))
		{
			assertEquals("Inserting power tools\nInserting strip nails\nInserting tools\nInserting nails\nSelect 1 to query an inventory item by UPC\nSelect 2 to insert a new inventory item\nSelect 3 to update an existing inventory item\nSelect 4 to add/update/delete relations\nInvalid choice\nWant to run the program again? Enter Y to run: ", content);
		}
	}
	
	/**
	 * Tests deleting a relation
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@Test 
	public void testRunnerDeleteRelation() throws SQLException, IOException
	{
		PowerTool tool = new PowerTool("456", 5, 5, "PowerTooldescription", true);
		StripNail nail= new StripNail("123",5,5,5.0,20);
		new PowerToolsToStripNailsMapper(tool.getID(),nail.getID()); 
		ArrayList<PowerToolStripNailRelation> relations = PowerToolStripNailRelationGateway.getAllRelations();
		assertEquals(1, relations.size());
		int id = relations.get(0).getID();
		
		ByteArrayInputStream in = new ByteArrayInputStream((id + "\n").getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);		
		Runner.deleteRelation();
		ArrayList<PowerToolStripNailRelation> relationss = PowerToolStripNailRelationGateway.getAllRelations();
		assertEquals(0, relationss.size());
	}
	
	/**
	 * Tests inserting strip nail relations after inserting a new power tool
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testInsertStripNailsForNewPowerTool() throws SQLException, IOException
	{
		new StripNail("123",5,5,5.0,20);
		new StripNail("99999", 5, 5, 5.0, 20);
		PowerTool powerTool = new PowerTool("55555", 5, 5, "PowerTool2description", true);
		ByteArrayInputStream in = new ByteArrayInputStream("y\n99999\ny\n123\nn\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);		
		Runner.insertStripNailsForNewPowerTool(powerTool);
		ArrayList<PowerToolStripNailRelation> relations = PowerToolStripNailRelationGateway.getAllRelations();
		assertEquals("55555", relations.get(0).getPowerTool().getUPC());
		assertEquals("99999", relations.get(0).getStripNail().getUPC());
		assertEquals("55555", relations.get(1).getPowerTool().getUPC());
		assertEquals("123", relations.get(1).getStripNail().getUPC());
	}
	
	/**
	 * Tests inserting power tool relations after inserting a new strip nail
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@Test
	public void testInsertPowerToolsForNewStripNail() throws SQLException, IOException
	{
		StripNail stripNail = new StripNail("123",5,5,5.0,20);
		new PowerTool("99999", 5, 5, "PowerTooldescription", true);
		new PowerTool("55555", 5, 5, "PowerTool2description", true);
		ByteArrayInputStream in = new ByteArrayInputStream("y\n99999\ny\n55555\nn\n".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		System.setOut(out);
		System.setIn(in);		
		Runner.insertPowerToolsForNewStripNail(stripNail);
		ArrayList<PowerToolStripNailRelation> relations = PowerToolStripNailRelationGateway.getAllRelations();
		assertEquals("99999", relations.get(0).getPowerTool().getUPC());
		assertEquals("123", relations.get(0).getStripNail().getUPC());
		assertEquals("55555", relations.get(1).getPowerTool().getUPC());
		assertEquals("123", relations.get(1).getStripNail().getUPC());
	}
}

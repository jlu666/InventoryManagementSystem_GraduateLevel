package model;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DatabaseGateway;
import domain.PowerToolsToStripNailsMapper;
import model.PowerTool;
import model.StripNail;
/**
 * Tests the relation between power tools and strip nails
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestPowerToolToStripNails {

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
	 * Rollsback all changes made to the database
	 * @throws SQLException
	 */
	@After
	public void rollbackTtansaction() throws SQLException
	{
		DatabaseGateway.rollbackTransaction();
		DatabaseGateway.resetAutoIncrement();
	}
	
	
	/**
	 * Tests toString format
	 * @throws SQLException
	 */
	@Test
	public void testInsertValueIntoPowerToolsTOStripNails() throws SQLException
	{
		new PowerTool("asdf", 5, 5, "PowerTooldescription", true);
		new StripNail("UPC", 5, 5, 3.33, 9);
		
		PowerToolsToStripNailsMapper mapper = new PowerToolsToStripNailsMapper(1,2);
		ArrayList<PowerTool> powerToolsRelatedToStripNail = mapper.getPowerToolsforStripNail(2);
		ArrayList<StripNail> stripNailsRelatedToPowerTool = mapper.getStripNailsforPowerTool(1);
		
		assertTrue(powerToolsRelatedToStripNail.get(0).id == 1);
		
		assertTrue(stripNailsRelatedToPowerTool.get(0).id == 2);
	}
	
	/**
	 * Tests getting all the power tools related to a strip nail
	 * @throws SQLException 
	 */
	@Test
	public void testGetAllPowerToolsForStripNail() throws SQLException
	{
		new PowerTool("powerTool1", 5, 5, "PowerTooldescription", true);
		new PowerTool("powerTool2", 5, 5, "PowerTooldescription", true);
		new StripNail("UPC", 5, 5, 3.33, 9);
		StripNail stripNail = new StripNail(3);
		new PowerToolsToStripNailsMapper(1,3);
	    new PowerToolsToStripNailsMapper(2,3);
	    ArrayList<PowerTool> powerTools  = stripNail.getPowerToolsForStripNail();
	 
	    assertEquals(2, powerTools.size());
	}
	
	/**
	 * Tests getting all the power tools related to a strip nail
	 * @throws SQLException 
	 */
	@Test
	public void testGetAllPowerToolsForStripNailMapperIsInitialized() throws SQLException
	{
		new PowerTool("powerTool1", 5, 5, "PowerTooldescription", true);
		new PowerTool("powerTool2", 5, 5, "PowerTooldescription", true);
		new StripNail("UPC", 5, 5, 3.33, 9);
		StripNail stripNail = new StripNail(3);
		new PowerToolsToStripNailsMapper(1,3);
	    new PowerToolsToStripNailsMapper(2,3);
	    ArrayList<PowerTool> powerTools  = stripNail.getPowerToolsForStripNail();
	    powerTools  = stripNail.getPowerToolsForStripNail();

	    assertEquals(2, powerTools.size());
	}
	
	/**
	 * Tests getting all the power tools related to a strip nail
	 * @throws SQLException 
	 */
	@Test
	public void testGetAllStripNailsForPowerTools() throws SQLException
	{
		new StripNail("UPC1", 5, 5, 3.33, 9);
		new StripNail("UPC2", 5, 5, 3.33, 9);
		new PowerTool("powerTool1", 5, 5, "PowerTooldescription", true);
		PowerTool powerTool = new PowerTool("powerTool1");
		new PowerToolsToStripNailsMapper(3,1);
	    new PowerToolsToStripNailsMapper(3,2);
	    ArrayList<StripNail> stripNail  = powerTool.getStripNailsForPowerTool();

	    assertEquals(2, stripNail.size());
	}
	
	/**
	 * Tests getting all the power tools related to a strip nail
	 * @throws SQLException 
	 */
	@Test
	public void testGetAllStripNailsForPowerToolsMapperIsInitialized() throws SQLException
	{
		new StripNail("UPC1", 5, 5, 3.33, 9);
		new StripNail("UPC2", 5, 5, 3.33, 9);
		new PowerTool("powerTool1", 5, 5, "PowerTooldescription", true);
		PowerTool powerTool = new PowerTool("powerTool1");
		new PowerToolsToStripNailsMapper(3,1);
	    new PowerToolsToStripNailsMapper(3,2);
	    ArrayList<StripNail> stripNail  = powerTool.getStripNailsForPowerTool();
	    stripNail  = powerTool.getStripNailsForPowerTool();

	    assertEquals(2, stripNail.size());
	}

}

package domain;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import datasource.Database;
import domain.tool.Tool;
import domain.tool.ToolMapper;

import java.sql.SQLException;

/**
 * 
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestTool {

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
        Database.resetAutoIncrement();
    }

    /**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testCreateFind() throws ClassNotFoundException, SQLException {
        Tool createdTool =ToolMapper.create("upc", 12, 24, "tool_description");
        assertNotNull(createdTool.getId());
        assertEquals("upc", createdTool.getUpc());
        assertEquals(12, createdTool.getManufacturerId());
        assertEquals(24, createdTool.getPrice());
        assertEquals("tool_description", createdTool.getDescription());

        Tool foundTool = ToolMapper.find(createdTool.getId());
        assertEquals(createdTool.getId(), foundTool.getId());
        assertEquals("upc", foundTool.getUpc());
        assertEquals(12, foundTool.getManufacturerId());
        assertEquals(24, foundTool.getPrice());
        assertEquals("tool_description", foundTool.getDescription());
    }

    /**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testToString() throws ClassNotFoundException, SQLException{
    	Tool tool = ToolMapper.create("upc", 12, 24, "tool_description");  	
    	String output = "Tool(upc: upc, manufacturerId: 12, price: 24, description: tool_description)";
    	assertEquals(tool.toString(),output);
    }
}

package domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;

/**
 * 
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestPowerToolToStripNailMapper
{

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
    public void testCreateRelation() throws ClassNotFoundException, SQLException {
        StripNail nail = StripNailMapper.create("1", 1, 1, 1, 1);
        PowerTool tool =  PowerToolMapper.create("2", 1, 1, "1", true);
        PowerToolToStripNailMapper.save(tool, nail);
        assertEquals(((PowerTool)nail.getPowerTools().get(0)).getId(),tool.getId());


    }
    
    /**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testFailCase() throws ClassNotFoundException, SQLException{
    	assertFalse(PowerToolToStripNailMapper.save(null, null));
    	StripNail nail = StripNailMapper.create("1", 1, 1, 1, 1);
        PowerTool tool = PowerToolMapper.create("2", 1, 1, "1", true);
        assertFalse(PowerToolToStripNailMapper.save(tool, null));
        assertFalse(PowerToolToStripNailMapper.save(null, nail));
      
    }
    
}

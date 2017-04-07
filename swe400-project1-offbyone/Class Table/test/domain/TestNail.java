package domain;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import datasource.Database;
import domain.nail.Nail;
import domain.nail.NailMapper;

import java.sql.SQLException;

/**
 * Tests Nail and NailMapper.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestNail {

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
	 * Tests NailMapper create and find methods
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testCreateFind() throws ClassNotFoundException, SQLException {
        Nail createdNail = NailMapper.create("nail_upc", 4, 4343, 1.4, 12);
        assertNotNull(createdNail.getId());
        assertEquals("nail_upc", createdNail.getUpc());
        assertEquals(4, createdNail.getManufacturerId());
        assertEquals(4343, createdNail.getPrice());
        assertEquals(1.4, createdNail.getLength(), 0.001);
        assertEquals(12, createdNail.getnumberInBox());

        Nail foundNail = NailMapper.find(createdNail.getId());
        assertEquals(createdNail.getId(), foundNail.getId());
        assertEquals("nail_upc", foundNail.getUpc());
        assertEquals(4, foundNail.getManufacturerId());
        assertEquals(4343, foundNail.getPrice());
        assertEquals(1.4, foundNail.getLength(), 0.001);
        assertEquals(12, foundNail.getnumberInBox());
    }
    
    /**
	 * Tests toString method.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Test
    public void testToString() throws ClassNotFoundException, SQLException{
    	Nail nail = NailMapper.create("nail_upc", 4, 4343, 1.4, 12);
    	String output = "Nail(upc: nail_upc, manufacturerId: 4, price: 4343, length: 1.4, numberInBox: 12)";
    	assertEquals(nail.toString(),output);
    }

    /**
     * Test AssociatedItem Method.
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
    @Test
    public void testAssociatedItems() throws ClassNotFoundException, SQLException{
    	Nail nail = NailMapper.create("nail_upc", 4, 4343, 1.4, 12);
    	assertEquals(0,nail.associatedItems().size());
    }
}

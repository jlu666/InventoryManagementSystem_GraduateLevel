package domain;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

import domain.NailMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 * 
 * Test NailMapper 
 *
 */
public class TestNailMapper 
{

	/**
	 * Test creation of NailMapper
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCreation() throws ClassNotFoundException, SQLException 
	{
		NailMapper nm = new NailMapper("101", 0, 0, 5.0, 0, "NailMapper");
		assertEquals("101", nm.getUpc());
		assertEquals(0, nm.getManufacturerID());
		assertEquals(0, nm.getPrice());
		assertEquals(5.0, nm.getLength(), 0.001);
		assertEquals(0, nm.getNumberInBox());
		assertEquals("NailMapper", nm.getClassName());
	}
	
	/**
	 * tests setters for nail mapper
	 */
	@Test
	public void testSetters()
	{
		NailMapper nailMapper = new NailMapper();
		
		nailMapper.setUpc("3");
		nailMapper.setManufacturerID(4);
		nailMapper.setPrice(40);
		nailMapper.setLength(4.5);
		nailMapper.setNumberInBox(4);
		nailMapper.setClassName("Nail");
		
		assertEquals("3", nailMapper.getUpc());
		assertEquals(4, nailMapper.getManufacturerID());
		assertEquals(40, nailMapper.getPrice());
		assertEquals(4.5, nailMapper.getLength(), 0.001);
		assertEquals(4, nailMapper.getNumberInBox());
		assertEquals("Nail", nailMapper.getClassName());
	}
	
	@Test
	public void testUpdateNail() throws ClassNotFoundException, SQLException
	{
		Nail nail = new Nail("upc", 1, 1, 1.0, 1, "Nail");
		NailMapper mapper = new NailMapper();
		mapper.updateNail(nail);
		
		assertEquals("upc", mapper.getUpc());
		assertEquals(1, mapper.getManufacturerID());
		assertEquals(1, mapper.getPrice());
		assertEquals(1.0, mapper.getLength(), 0.001);
		assertEquals(1, mapper.getNumberInBox());
		assertEquals("Nail", mapper.getClassName());

	}
}
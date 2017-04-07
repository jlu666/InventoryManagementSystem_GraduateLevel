package enums;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Nails Enum
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestNailsEnum 
{
	/**
	 * Tests the getters of the Nails enum
	 */
	@Test
	public void testNailsEnum() 
	{
		assertEquals("5453432767", Nails.COMMON_10D.getUpc());
		assertEquals(15, Nails.COMMON_10D.getManufacturerID());
		assertEquals(1348, Nails.COMMON_10D.getPrice());
		assertEquals(3, Nails.COMMON_10D.getLength(), .001);
		assertEquals(500, Nails.COMMON_10D.getNumberInBox());
	}

}

package enums;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests the Strip Nails Enum
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestStripNailsEnum 
{
	/**
	 * Tests the getters of the StripNails enum
	 */
	@Test
	public void testStripNailsEnum() 
	{
		assertEquals("5453432345", StripNails.ROUND_HEAD_NAIL_STRIP.getUpc());
		assertEquals(13, StripNails.ROUND_HEAD_NAIL_STRIP.getManufacturerID());
		assertEquals(1099, StripNails.ROUND_HEAD_NAIL_STRIP.getPrice());
		assertEquals(2.5, StripNails.ROUND_HEAD_NAIL_STRIP.getLength(), .001);
		assertEquals(50, StripNails.ROUND_HEAD_NAIL_STRIP.getNumberInStrip());
	}

}

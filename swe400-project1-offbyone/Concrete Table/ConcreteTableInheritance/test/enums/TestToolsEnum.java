package enums;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Tools Enum
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestToolsEnum
{
	/**
	 * Tests the getters of the Tools enum
	 */
	@Test
	public void testToolsEnum() 
	{
		assertEquals("0121232234", Tools.HAMMER.getUpc());
		assertEquals(32, Tools.HAMMER.getManufacturerID());
		assertEquals(899, Tools.HAMMER.getPrice());
		assertEquals("Ball Peen Hammer", Tools.HAMMER.getDescription());
	}

}

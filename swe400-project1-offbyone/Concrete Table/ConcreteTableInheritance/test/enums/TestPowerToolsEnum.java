package enums;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests the Power Tools Enum
 * @author Ronald Sease & Darnell Martin
 *
 */
public class TestPowerToolsEnum 
{
	/**
	 * Tests the getters of the PowerTools enum
	 */
	@Test
	public void testPowerToolsEnum() 
	{
		assertEquals("1231231234", PowerTools.HITACHI_PNEUMATIC_NAILER.getUpc());
		assertEquals(13, PowerTools.HITACHI_PNEUMATIC_NAILER.getManufacturerID());
		assertEquals(39900, PowerTools.HITACHI_PNEUMATIC_NAILER.getPrice());
		assertEquals("Pheumatic Nail Gun", PowerTools.HITACHI_PNEUMATIC_NAILER.getDescription());
		assertEquals(false, PowerTools.HITACHI_PNEUMATIC_NAILER.isBatteryPowered());
	}

}

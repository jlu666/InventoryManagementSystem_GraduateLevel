package data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test enum data.
 */
public class TestEnums {
	
	/**
	 * Test nail enum data.
	 */
	@Test
	public void testNailsEnum() {
		assertEquals("5453432767", Nails.COMMON_10D.getUpc());
		assertEquals(15, Nails.COMMON_10D.getManufacturerID());
		assertEquals(1348, Nails.COMMON_10D.getPrice());
		assertEquals(3, Nails.COMMON_10D.getLength(), .001);
		assertEquals(500, Nails.COMMON_10D.getNumberInBox());
	}
	
	/**
	 * Test power tool enum data.
	 */
	@Test
	public void testPowerToolsEnum() {
		assertEquals("1231231234", PowerTools.HITACHI_PNEUMATIC_NAILER.getUpc());
		assertEquals(13, PowerTools.HITACHI_PNEUMATIC_NAILER.getManufacturerID());
		assertEquals(39900, PowerTools.HITACHI_PNEUMATIC_NAILER.getPrice());
		assertEquals("Pheumatic Nail Gun", PowerTools.HITACHI_PNEUMATIC_NAILER.getDescription());
		assertEquals(false, PowerTools.HITACHI_PNEUMATIC_NAILER.isBatteryPowered());
	}
	
	/**
	 * Test strip nail enum data.
	 */
	@Test
	public void testStripNailsEnum() {
		assertEquals("5453432345", StripNails.ROUND_HEAD_NAIL_STRIP.getUpc());
		assertEquals(13, StripNails.ROUND_HEAD_NAIL_STRIP.getManufacturerID());
		assertEquals(1099, StripNails.ROUND_HEAD_NAIL_STRIP.getPrice());
		assertEquals(2.5, StripNails.ROUND_HEAD_NAIL_STRIP.getLength(), .001);
		assertEquals(50, StripNails.ROUND_HEAD_NAIL_STRIP.getNumberInStrip());
	}
	
	/**
	 * Test tool enum data.
	 */
	@Test
	public void testToolsEnum() {
		assertEquals("0121232234", Tools.HAMMER.getUpc());
		assertEquals(32, Tools.HAMMER.getManufacturerID());
		assertEquals(899, Tools.HAMMER.getPrice());
		assertEquals("Ball Peen Hammer", Tools.HAMMER.getDescription());
	}

}

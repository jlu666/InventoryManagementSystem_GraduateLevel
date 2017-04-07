package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test BatteryPoweredHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestBatteryPoweredHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		BatteryPoweredHandler handler = new BatteryPoweredHandler();
		assertFalse(handler.validate("123"));
		assertTrue(handler.validate("true"));
		assertTrue(handler.validate("false"));
	}

}

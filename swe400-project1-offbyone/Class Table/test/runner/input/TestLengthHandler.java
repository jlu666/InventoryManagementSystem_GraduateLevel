package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test BatteryPoweredHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestLengthHandler {

	/**
	 * Test whether the input meets the requirement.
	 */
	@Test
	public void testValidate() {
		LengthHandler handler = new LengthHandler();
		assertFalse(handler.validate("abc"));
		assertFalse(handler.validate("123a"));
		assertTrue(handler.validate("123.4"));
	}

}

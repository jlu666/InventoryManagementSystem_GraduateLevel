package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test PriceHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestPriceHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		PriceHandler handler = new PriceHandler();
		assertFalse(handler.validate("abc"));
		assertTrue(handler.validate("123"));
	}

}

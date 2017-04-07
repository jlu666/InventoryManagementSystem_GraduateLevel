package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test NumberInStripHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestNumberInStripHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		NumberInStripHandler handler = new NumberInStripHandler();
		assertFalse(handler.validate("abc"));
		assertTrue(handler.validate("123"));
	}

}

package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test NumberInBoxHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestNumberInBoxHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		NumberInBoxHandler handler = new NumberInBoxHandler();
		assertFalse(handler.validate("abc"));
		assertTrue(handler.validate("123"));
	}

}

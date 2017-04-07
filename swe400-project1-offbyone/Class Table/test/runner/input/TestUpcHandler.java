package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test UpcHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestUpcHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		UpcHandler handler = new UpcHandler();
		assertFalse(handler.validate("123123123123123"));
		assertTrue(handler.validate("123123123123"));
	}

}

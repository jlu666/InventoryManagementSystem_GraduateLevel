package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test DescriptionHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestDescriptionHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		DescriptionHandler handler = new DescriptionHandler();
		assertTrue(handler.validate(""));
	}

}

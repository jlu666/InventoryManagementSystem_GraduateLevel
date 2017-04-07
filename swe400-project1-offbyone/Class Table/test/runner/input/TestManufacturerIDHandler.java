package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * It is used to test ManuFacturerHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestManufacturerIDHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		ManufacturerIDHandler handler = new ManufacturerIDHandler();
		assertFalse(handler.validate("abc"));
		assertTrue(handler.validate("123"));
	}

}

package runner.input;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * It is used to test AddRelationShipHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestAddRelationShipHandler
{
	/**
	 * Tests whether the information is valid.
	 */
	@Test
	public void testValid(){
		AddRelationShipHandler handler = new AddRelationShipHandler();
		assertFalse(handler.validate("yasd"));
		assertTrue(handler.validate("y"));
		assertTrue(handler.validate("N"));
	}
}

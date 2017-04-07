package runner.input;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.TypeCode;

/**
 * It is used to test TypeCodeHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestTypeCodeHandler {

	/**
	 * Tests whether the information is accepting.
	 */
	@Test
	public void testValidate() {
		TypeCodeHandler handler = new TypeCodeHandler();
		assertFalse(handler.validate(null));
		assertFalse(handler.validate(""));
		assertFalse(handler.validate("abc"));
		
		for(TypeCode typeCode: TypeCode.values()) {
			assertTrue(handler.validate(typeCode.toString()));
		}
	}

}

package runner.input;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * It is used to test inputHandler.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class TestInputHandler {
	
	private MockInputHandler inputHandler;
	
	/**
	 * Initialization the MockInputHandler.
	 */
	@Before
	public void setup() {
		this.inputHandler = new MockInputHandler("handlerName", "initialPrompt", "validationPrompt");
	}

	/**
	 * Tests whether the input meets the requirement.
	 */
	@Test
	public void testValidation() {
		assertFalse(this.inputHandler.validate("string"));
		this.inputHandler.passValidation(true);
		assertTrue(this.inputHandler.validate("string"));
		this.inputHandler.passValidation(true);
		assertFalse(this.inputHandler.validate("string"));
	}
	
	/**
	 * Tests the input format with or without default value.
	 */
	@Test
	public void testIsDefault() {
		assertFalse(this.inputHandler.isDefault(""));
		assertFalse(this.inputHandler.isDefault("not empty"));
		this.inputHandler.setValue("test value");
		assertTrue(this.inputHandler.isDefault(""));
		assertFalse(this.inputHandler.isDefault("not empty"));
	}
	
	/**
	 * Tests the valid prompt.
	 */
	@Test
	public void testInitialPrompt() {
		assertEquals("initialPrompt: ", this.inputHandler.initialPrompt());
		this.inputHandler.setValue("a value");
		assertEquals("initialPrompt (default a value): ", this.inputHandler.initialPrompt());
	}
	
	/**
	 * Test the invalid prompt.
	 */
	@Test
	public void testValidationPrompt() {
		assertEquals("validationPrompt: ", this.inputHandler.validationPrompt());
		this.inputHandler.setValue("a value");
		assertEquals("validationPrompt (default a value): ", this.inputHandler.validationPrompt());
	}
	
	/**
	 * Tests getName() methods.
	 */
	@Test
	public void testGetName() {
		assertEquals("handlerName", this.inputHandler.getName());
	}
	
	/**
	 * Tests output information.
	 */
	@Test
	public void testHandleTwoLoop() {
		String inputData = "abc\ndef\nghi";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		inputHandler.handle(outputStream, inputScanner);
		String output = new String(outputBuffer.toByteArray());
		assertEquals("initialPrompt: validationPrompt: validationPrompt: ", output);
		assertEquals("ghi", inputHandler.getValue());
	}
	
	/**
	 * Tests output information with default value.
	 */
	@Test
	public void testHandleDefaultValue() {
		String inputData = "\n\n";
		InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
		Scanner inputScanner = new Scanner(inputStream);
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(outputBuffer);
		inputHandler.setValue("xyz");
		inputHandler.handle(outputStream, inputScanner);
		String output = new String(outputBuffer.toByteArray());
		assertEquals("initialPrompt (default xyz): ", output);
		assertEquals("xyz", inputHandler.getValue());
	}

}

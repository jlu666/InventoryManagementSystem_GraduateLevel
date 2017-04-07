package runner.input;

import java.io.PrintStream;
import java.util.Scanner;
/**
 * InputHandler interface. It is used to tell user how to input, 
 * check whether the input meets the requirement and store the input
 * information. 
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class InputHandler {
	
	private String initialPrompt;
	private String validationPrompt;
	private String name;
	private String value;
	
	/**
	 * Constructor.
	 * @param name the name of handler.
	 * @param initialPrompt Initial prompt
	 * @param validationPrompt the invalid prompt when the input is invalid.
	 */
	public InputHandler(String name, String initialPrompt, String validationPrompt) {
		this.name = name;
		this.initialPrompt = initialPrompt;
		this.validationPrompt = validationPrompt;
		this.value = null;
	}
	
	/**
	 * Uses scanner to get the information from the user, And uses PrintStream to print 
	 * output.
	 * @param output PrintStream
	 * @param input Scanner
	 */
	public void handle(PrintStream output, Scanner input) {
		output.print(this.initialPrompt());
		String value = input.nextLine().trim();
		
		while(!isDefault(value) && !this.validate(value)) {
			output.print(this.validationPrompt());
			value = input.nextLine().trim();
		}
		
		if(!isDefault(value)) {
			this.value = value;
		}
	}
	
	/**
	 * Gets the initial prompt.
	 * @return the initial prompt.
	 */
	protected String initialPrompt() {
		return this.formatPrompt(this.initialPrompt);
	}
	
	/**
	 * @return invalid prompt.
	 */
	protected String validationPrompt() {
		return this.formatPrompt(this.validationPrompt);
	}
	
	/**
	 * Gets the string based on value of the handler.
	 * @param prompt initial prompt.
	 * @return the string based on value of the handler
	 */
	protected String formatPrompt(String prompt) {
		if(this.value != null) {
			prompt += " (default " + this.value + ")";
		}
		
		prompt += ": ";
		return prompt;
	}
	
	/**
	 * Gets the name of handler.
	 * @return the name of handler.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the value that the handler stores.
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Sets the value of the handler.
	 * @param value  the value of the handler.
	 */
	protected void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Checks whether handler stores information.
	 * @param value
	 * @return
	 */
	protected boolean isDefault(String value) {
		return this.getValue() != null && value.isEmpty();
	}
	
	/**
	 * @param value input
	 * @return true if the input is valid. Otherwise return false.
	 */
	protected abstract boolean validate(String value);

}

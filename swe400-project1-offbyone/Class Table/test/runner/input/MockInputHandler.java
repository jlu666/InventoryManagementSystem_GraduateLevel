package runner.input;

/**
 * It is used to test InputHandler interface.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class MockInputHandler extends InputHandler {
	
	private boolean passValidation;
	private int validationLoops;
	
	/**
	 * Constructor.
	 * @param name the name of InputHandler
	 * @param ip the valid information
	 * @param vp the invalid information
	 */
	public MockInputHandler(String name, String ip, String vp) {
		super(name, ip, vp);
		this.passValidation = false;
		this.validationLoops = 2;
	}
	
	/**
	 * Sets whether the information meets the requirement. 
	 * @param p Sets whether the information meets the requirement.
	 */
	public void passValidation(boolean p) {
		this.passValidation = p;
	}
	
	protected boolean validate(String value) {
		this.validationLoops -= 1;
		
		if(this.validationLoops >= 0) {
			return this.passValidation;
		} else {
			return !this.passValidation;
		}
	}
	
}

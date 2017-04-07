package runner.input;

/**
 * The handler handles how to input the description of Tool.
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class DescriptionHandler extends InputHandler {
	
	/**
	 * Constructor.
	 */
	public DescriptionHandler() {
		super("description", "Enter a description", null);
	}

	@Override
	protected boolean validate(String value) {
		return true;
	}

}

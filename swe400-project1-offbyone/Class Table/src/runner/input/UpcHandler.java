package runner.input;

/**
 *The handler handles how to input the upc of InventoryItem.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class UpcHandler extends InputHandler {
	
	/**
	 * Constructor.
	 */
	public UpcHandler() {
		super("upc", "Please enter upc", "A upc must be <= 12 characters long.  Please re-enter");
	}

	@Override
	protected boolean validate(String value) {
		return value.length() <= 12;
	}

}

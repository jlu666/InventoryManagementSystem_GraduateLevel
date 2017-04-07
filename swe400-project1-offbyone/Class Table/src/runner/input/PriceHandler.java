package runner.input;

/**
 * The handler handles how to input the price of inventoryItem.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class PriceHandler extends InputHandler {
	
	/**
	 * Constructor.
	 */
	public PriceHandler() {
		super("price", "Please enter item price", "An item price must be a valid integer.  Please re-enter");
	}

	@Override
	protected boolean validate(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

}

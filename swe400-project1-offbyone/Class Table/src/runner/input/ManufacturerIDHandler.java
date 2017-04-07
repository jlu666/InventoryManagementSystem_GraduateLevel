package runner.input;

/**
 * The handler handles how to input the ManufacturerID of InventoryItem.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class ManufacturerIDHandler extends InputHandler
{

	/**
	 * Constructor
	 */
	public ManufacturerIDHandler(){
		super("ManufacturerID","Please enter ManufacturerID","A ManufacturerID must be a valid integer.  Please re-enter");
	}
	
	/**
	 * Test whether the data stored in handler can be converted to the required data type.
	 * ManufactureID can be converted to an integer.
	 */
	@Override
	protected boolean validate(String value)
	{
		try {
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
}

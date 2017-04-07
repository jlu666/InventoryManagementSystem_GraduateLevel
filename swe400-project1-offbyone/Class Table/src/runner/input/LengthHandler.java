package runner.input;

/**
 * The handler handles how to input the length of Nail.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class LengthHandler extends InputHandler{

	/**
	 * Constructor.
	 */
	public LengthHandler(){
		super("length","Please enter the length of Nails","A nail length must be a valid decimal.  Please re-enter");
	}
	
	@Override
	protected boolean validate(String value) {
		
		try {
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

}

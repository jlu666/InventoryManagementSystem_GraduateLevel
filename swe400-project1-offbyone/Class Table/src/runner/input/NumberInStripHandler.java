package runner.input;

/**
 * The handler handles how to input the number Strip Nail.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class NumberInStripHandler extends InputHandler {

	/**
	 * Constructor.
	 */
	public NumberInStripHandler(){
		super("Number In Strip","Pleaser enter the number in strip"," The number in strip must be a valid integer.  Please re-enter");
	}
	
	@Override
	protected boolean validate(String value) {
		try{
			Integer.parseInt(value);
			return true;
		}catch(NumberFormatException ex){
			return false;
		}
	}

}

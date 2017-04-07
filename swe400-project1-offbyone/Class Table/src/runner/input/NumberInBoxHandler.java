package runner.input;


/**
 * The handler handles how to input the number in box of Nail.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class NumberInBoxHandler extends InputHandler {
	
	/**
	 * Constructor.
	 */
	public NumberInBoxHandler(){
		super("Number In Box","Pleaser enter the number in box"," The number in box must be a valid integer.  Please re-enter");
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

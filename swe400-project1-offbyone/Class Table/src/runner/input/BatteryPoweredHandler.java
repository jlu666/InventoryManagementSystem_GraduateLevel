package runner.input;

/**
 * The handler handles how to input the Battery Powered of PowerTool.
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class BatteryPoweredHandler extends InputHandler{

	/**
	 * Constructor.
	 */
	public BatteryPoweredHandler(){
		super("Battery Powered","Please enter whether the battery is powered(true or false)","Please enter true or false only");
	}
	
	@Override
	protected boolean validate(String value) {
		boolean isTrue = value.toLowerCase().equals("true");
		boolean isFalse = value.toLowerCase().equals("false");
		return isTrue || isFalse;
	}

}

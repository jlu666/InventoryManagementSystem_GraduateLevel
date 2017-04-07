package runner.input;

/**
 * The handler handles whether the user inserts or updates the relation between StripNaill and PowerTool.
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class AddRelationShipHandler extends InputHandler {

	/**
	 * Constructor.
	 */
	public AddRelationShipHandler() {
		super("addRelation", "Do you want to insert relationship(y or n)", "Only y or n is accepted, please enter agian!");
		
	}

	@Override
	protected boolean validate(String value) {
		boolean isTrue = value.toLowerCase().equals("y");
		boolean isFalse = value.toLowerCase().equals("n");
		return isTrue||isFalse;
	}

}

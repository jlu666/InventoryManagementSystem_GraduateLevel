package runner.input;

import datasource.TypeCode;
/**
 * The handler handles how to input the typeCode of Inventory.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class TypeCodeHandler extends InputHandler {
	
	private TypeCode[] typeCodes;
	
	/**
	 * Constructor.
	 */
	public TypeCodeHandler() {
		super("typeCode", "Enter type code (n, sn, t, pt)", "Type code must be one of (n, sn, t, pt).  Please re-enter");
		this.typeCodes = TypeCode.values();
	}

	@Override
	protected boolean validate(String value) {
		for(TypeCode typeCode: this.typeCodes) {
			if(typeCode.toString().equals(value)) {
				return true;
			}
		}
		
		return false;
	}

}

package datasource;

/**
 * The list of TypeCode.
 * t-tool
 * pt-powerTool
 * n-nail
 * sn-StripNail
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public enum TypeCode {
	/**
	 * represent tool
	 */
	Tool("t"),
	/**
	 * represent PowerTool
	 */
	PowerTool("pt"),
	/**
	 * represent Nail
	 */
	Nail("n"),
	/**
	 * represent StripNail
	 */
	StripNail("sn");
	
	private String value;
	
	TypeCode(String value) {
		this.value = value;
	}
	
	/**
	 * Gets the value of the TypeCode.
	 * @return the value of the TypeCode.
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Return the TypeCode in String form.
	 * @return String TypeCode in String Form.
	 */
	public String toString() {
		return this.getValue();
	}
	
	/**
	 * Check whether has the same value.
	 * @param typeCode the other TypeCode
	 * @return true if the value is same. Otherwise, return false.
	 */
	public boolean equals(TypeCode typeCode) {
		return this.getValue().equals(typeCode.getValue());
	}

}

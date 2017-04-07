package datasource;

/**
 * MockFastener Class.
 *  @author Alan Malloy & Jixiang Lu
 *
 */
public class MockFastenerGateway extends FastenerGateway
{

	protected MockFastenerGateway(int id, String upc, int manufacturerId, int price,double length) {
		super(id, upc, manufacturerId, price,length);
	}
	
	/**
	 * Constructor without any fields.
	 */
	public MockFastenerGateway(){
		super();
	}
	/**
	 * Gets the TypeCode.
	 */
	@Override
	public TypeCode getTypeCode()
	{
		return TypeCode.Nail;
	}

}

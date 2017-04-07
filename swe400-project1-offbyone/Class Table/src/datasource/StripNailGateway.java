package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * It is a row gateway which is used to manage the entity in the StripNail table.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class StripNailGateway extends FastenerGateway{

	private int numberInStrip;
	private static final String INSERT_SQL="INSERT INTO strip_nails(id, number_in_strip) VALUES(?, ?)";
	private static final String UPDATE_SQL="UPDATE strip_nails SET number_in_strip=? WHERE id=?";

	/**
	 * Constructor without any parameters.
	 */
	public StripNailGateway() {
		super();
	}
	
	/**
	 * Constructor with all fields except TypeCode.
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerId
	 * @param price
	 * @param length
	 * @param numberInBox
	 */
	protected StripNailGateway(int id, String upc, int manufacturerId, int price,double length,int numberInStrip) {
		super(id, upc, manufacturerId, price,length);
		this.numberInStrip = numberInStrip;
	}
	
	/**
	 * Gets the TypeCode of NailGateway.
	 * @return  the TypeCode of NailGateway
	 */
	public TypeCode getTypeCode() {
		return TypeCode.StripNail;
	}

	/**
	 * Gets the number in Strip.
	 * @return  the number in Strip.
	 */
	public int getNumberInStrip() {
		return numberInStrip;
	}

	/**
	 * Sets the number in strip
	 * @param numberInStrip  the number in strip
	 */
	public void setNumberInStrip(int numberInStrip) {
		this.numberInStrip = numberInStrip;
	}
	
	/**
	 * Inserts this entity into database.
	 */
	protected int insert() throws ClassNotFoundException, SQLException {
		int id = super.insert();
		PreparedStatement statement = Database.prepareStatement(INSERT_SQL);
		
        statement.setInt(1, id);
        statement.setInt(2, this.getNumberInStrip());
        statement.executeUpdate();
        statement.close();
        
        return id;
	}
	
	/**
	 * Updates this entity to database.
	 */
	protected void update() throws ClassNotFoundException, SQLException {
		super.update();
		PreparedStatement statement = Database.prepareStatement(UPDATE_SQL);
		statement.setInt(1, this.getNumberInStrip());
        statement.setInt(2, this.getId());
        statement.executeUpdate();
        statement.close();
	}
}

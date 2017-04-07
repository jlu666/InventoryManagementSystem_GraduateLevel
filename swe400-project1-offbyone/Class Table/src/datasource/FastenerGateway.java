package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * It is a row gateway which is used to manage the entity in the fastener table.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class FastenerGateway extends InventoryItemGateway{
	
	private double length;
	private static final String INSERT_SQL ="INSERT INTO fastener(id, length) VALUES(?, ?)";
	private static final String UPDATE_SQL = "UPDATE fastener SET length=? WHERE id=?";
	
	/**
	 * Constructor without any parameters.
	 */
	public FastenerGateway() {
		super();
	}

	/**
	 * Constructor with all fields except TypeCode.
	 * @param id id
	 * @param upc upc
	 * @param manufacturerId manufacturerId
	 * @param price price
	 * @param length length
	 */
	protected FastenerGateway(int id, String upc, int manufacturerId, int price,double length) {
		super(id, upc, manufacturerId, price);
		this.length = length;
	}

	/**
	 * Gets the length of the Fastener.
	 * @return the length of the Fastener.
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Sets the length of the Fastener.
	 * @param length of the Fastener.
	 */
	public void setLength(double length) {
		this.length = length;
	}
	
	/**
	 * Inserts this entity into database.
	 */
	protected int insert() throws ClassNotFoundException, SQLException {
		int id = super.insert();
		PreparedStatement statement = Database.prepareStatement(INSERT_SQL);
		
        statement.setInt(1, id);
        statement.setDouble(2, this.getLength());
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
		statement.setDouble(1, this.getLength());
        statement.setInt(2, this.getId());
        statement.executeUpdate();
        statement.close();
	}
	

}

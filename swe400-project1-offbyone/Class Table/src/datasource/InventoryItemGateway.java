package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * It is a row gateway which is used to manage the entity in the inventory table.
 * 
 * @author Alan Malloy & Jixiang Lu
 */
public abstract class InventoryItemGateway extends RowGateway {
	
	private static final String INSERT_SQL = "INSERT INTO inventory_item(typeCode, upc, manufacturerId, price) VALUES(?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE inventory_item SET typeCode=?, upc=?, manufacturerId=?, price=? WHERE id=?";
	
	private String upc;
	private int manufacturerId;
	private int price;
	
	/**
	 * Constructor without any parameters.
	 */
	public InventoryItemGateway() {
		super();
	}
	
	/**
	 * Constructor with all fields except TypeCode.
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerId
	 * @param price
	 */
	protected InventoryItemGateway(int id, String upc, int manufacturerId, int price) {
		super(id);
		this.upc = upc;
		this.manufacturerId = manufacturerId;
		this.price = price;
	}
	
	/**
	 * Gets the TypeCode of this InventoryItemGateway.
	 * @return  the TypeCode of this InventoryItemGateway
	 */
	public abstract TypeCode getTypeCode();
	
	/**
	 * Gets the UPC of this entity.
	 * @return the UPC 
	 */
	public String getUpc() {
		return this.upc;
	}
	
	/**
	 * Gets the ManufactureId of this entity.
	 * @return the ManufactureId
	 */
	public int getManufacturerId() {
		return this.manufacturerId;
	}
	
	/**
	 * Gets the price of this entiry.
	 * @return the price
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * Sets the upc of this entiry.
	 * @param upc the upc
	 */
	public void setUpc(String upc) {
		this.upc = upc;
	}
	
	/**
	 * Sets the manufacturerId of the entiry.
	 * @param manufacturerId the manufacturerId
	 */
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	
	/**
	 * Sets the price of the entity.
	 * @param price the price.
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Inserts this entity into database.
	 */
	protected int insert() throws ClassNotFoundException, SQLException {
		PreparedStatement statement = Database.prepareStatement(INSERT_SQL);
		
		statement.setString(1, this.getTypeCode().toString());
        statement.setString(2, this.getUpc());
        statement.setInt(3, this.getManufacturerId());
        statement.setInt(4, this.getPrice());
        statement.executeUpdate();
        
        ResultSet keys = statement.getGeneratedKeys();
        int id = keys.next() ? keys.getInt(1) : -1;
        statement.close();
        
        return id;
	}
	
	/**
	 * Updates this entity to database.
	 */
	protected void update() throws ClassNotFoundException, SQLException {
		PreparedStatement statement = Database.prepareStatement(UPDATE_SQL);
		statement.setString(1, this.getTypeCode().toString());
        statement.setString(2, this.getUpc());
        statement.setInt(3, this.getManufacturerId());
        statement.setInt(4, this.getPrice());
        statement.setInt(5, this.getId());
        statement.executeUpdate();
        statement.close();
	}
	
}

package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * It is a row gateway which is used to manage the entity in the Tool table.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class ToolGateway extends InventoryItemGateway {
	
	private static final String INSERT_SQL = "INSERT INTO tool(id, description) VALUES(?, ?)";
	private static final String UPDATE_SQL = "UPDATE tool SET description=? WHERE id=?";
	
	private String description;
	
	/**
	 * Constructor without any parameters.
	 */
	public ToolGateway() {
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
	protected ToolGateway(int id, String upc, int manufacturerId, int price, String description) {
		super(id, upc, manufacturerId, price);
		this.description = description;
	}
	
	/**
	 * Gets the TypeCode of PowerToolGateway.
	 * @return  the TypeCode of PowerToolGateway
	 */
	public TypeCode getTypeCode() {
		return TypeCode.Tool;
	}
	
	/**
	 * Gets the Description of this entity.
	 * @return the Description of this entity
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Sets the Description of this entiry.
	 * @param description the Description of this entiry
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Inserts this entity into database.
	 */
	protected int insert() throws ClassNotFoundException, SQLException {
		int id = super.insert();
		PreparedStatement statement = Database.prepareStatement(INSERT_SQL);
		
        statement.setInt(1, id);
        statement.setString(2, this.getDescription());
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
		statement.setString(1, this.getDescription());
        statement.setInt(2, this.getId());
        statement.executeUpdate();
        statement.close();
	}

}

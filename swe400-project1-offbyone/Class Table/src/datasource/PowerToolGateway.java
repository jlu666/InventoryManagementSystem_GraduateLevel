package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * It is a row gateway which is used to manage the entity in the PowerTool table.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class PowerToolGateway extends ToolGateway {
	
	private static final String INSERT_SQL = "INSERT INTO power_tool(id, battery_powered) VALUES(?, ?)";
	private static final String UPDATE_SQL = "UPDATE power_tool SET battery_powered=? WHERE id=?";
	
	private boolean isBatteryPowered;
	
	/**
	 * Constructor without any parameters.
	 */
	public PowerToolGateway() {
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
	protected PowerToolGateway(int id, String upc, int manufacturerId, int price, String description, boolean isBatteryPowered) {
		super(id, upc, manufacturerId, price, description);
		this.isBatteryPowered = isBatteryPowered;
	}
	
	/**
	 * Gets the TypeCode of PowerToolGateway.
	 * @return  the TypeCode of PowerToolGateway
	 */
	public TypeCode getTypeCode() {
		return TypeCode.PowerTool;
	}
	
	/**
	 * Return true if the PowerTool uses battery, Otherwise return false.
	 * @return true if the PowerTool uses battery, Otherwise return false.
	 */
	public boolean isBatteryPowered() {
		return this.isBatteryPowered;
	}
	
	/**
	 * Sets the PowertTool whether uses battery. True is using battery, Otherwise, to set false.
	 * @param isBatterPowered the PowertTool whether uses battery.
	 */
	public void setBatteryPowered(boolean isBatterPowered) {
		this.isBatteryPowered = isBatterPowered;
	}
	
	/**
	 * Inserts this entity into database.
	 */
	protected int insert() throws ClassNotFoundException, SQLException {
		int id = super.insert();
		PreparedStatement statement = Database.prepareStatement(INSERT_SQL);
		
		statement.setInt(1, id);
        statement.setInt(2, this.isBatteryPowered() ? 1 : 0);
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
		statement.setInt(1, this.isBatteryPowered() ? 1 : 0);
        statement.setInt(2, this.getId());
        statement.executeUpdate();
        statement.close();
	}

}

package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * It is a Finder which is used to load PowerToolRowGateway from database according to specific condition.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class PowerToolFinder {
	
	private static final String ID_SQL = "SELECT * FROM inventory_item LEFT JOIN tool ON inventory_item.id = tool.id LEFT JOIN power_tool ON tool.id = power_tool.id WHERE inventory_item.id = ?";
	private static final String SELECT_BASE_NAIL = "SELECT it.id, it.upc, it.manufacturerId, it.price, tool.description, ptool.battery_powered" 
			+" FROM inventory_item it, tool tool, power_tool ptool, powertool_stripnail sn"
			+" WHERE sn.stripnailId = ? AND sn.powertoolId = ptool.id AND ptool.id = tool.id AND tool.id = it.id"
			+ " ORDER BY it.id;" ;
	
	/**
	 * Gets the PowerToolRowGateway which uses this id.
	 * 
	 * @param id the identify number
	 * @return the PowerToolRowGateway which uses this id.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static PowerToolGateway find(int id) throws ClassNotFoundException, SQLException {
		PreparedStatement statement = Database.prepareStatement(ID_SQL);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		PowerToolGateway gateway = null;
		
		if(resultSet.next()) {
			gateway = structure(resultSet);
		}
		
		return gateway;
	}
	
	/**
	 * Returns a list which contains all PowerToolGateway that have a relationship with the specific StripNailId. 
	 * @param stripNailId The StripNail's id
	 * @return a list which contains all PowerToolGateway that have a relationship with the specific StripNailId. 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static ArrayList<PowerToolGateway> findByStripNail(int stripNailId) throws ClassNotFoundException, SQLException{	
		PreparedStatement statement = Database.prepareStatement(SELECT_BASE_NAIL);
		statement.setInt(1, stripNailId);
		ResultSet rs = statement.executeQuery();
		ArrayList<PowerToolGateway> list = new ArrayList<PowerToolGateway>();
		PowerToolGateway gateway = null;
		while(rs.next()){
			gateway= structure(rs);
			list.add(gateway);
		}
		return list;
		
	}
	
	/**
	 * Return a PowerToolRowGateway which has been loaded base on the ResultSet.
	 * @param resultSet ResultSet contains data.
	 * @return a PowerToolRowGateway which has been loaded base on the ResultSet.
	 * @throws SQLException  SQLExceptions
	 */
	protected static PowerToolGateway structure(ResultSet resultSet) throws SQLException {
		return new PowerToolGateway(
			resultSet.getInt("id"),
			resultSet.getString("upc"),
			resultSet.getInt("manufacturerId"),
			resultSet.getInt("price"),
			resultSet.getString("description"),
			resultSet.getInt("battery_powered") == 1
		);
	}
	
}
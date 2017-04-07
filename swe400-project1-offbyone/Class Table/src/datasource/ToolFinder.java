package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * It is a Finder which is used to load ToolRowGateway from database according to specific condition.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class ToolFinder {
	
	private static final String ID_SQL = "SELECT * FROM inventory_item LEFT JOIN tool ON inventory_item.id = tool.id WHERE inventory_item.id = ?";
	
	/**
	 * Gets the ToolRowGateway which uses this id.
	 * 
	 * @param id the identify number
	 * @return the ToolRowGateway which uses this id.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static ToolGateway find(int id) throws ClassNotFoundException, SQLException {
		PreparedStatement statement = Database.prepareStatement(ID_SQL);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		ToolGateway gateway = null;
		
		if(resultSet.next()) {
			gateway = structure(resultSet);
		}
		
		return gateway;
	}
	
	/**
	 * Return a ToolRowGateway which has been loaded base on the ResultSet.
	 * @param resultSet ResultSet contains data.
	 * @return a ToolRowGateway which has been loaded base on the ResultSet.
	 * @throws SQLException  SQLExceptions
	 */
	protected static ToolGateway structure(ResultSet resultSet) throws SQLException {
		return new ToolGateway(
			resultSet.getInt("id"),
			resultSet.getString("upc"),
			resultSet.getInt("manufacturerId"),
			resultSet.getInt("price"),
			resultSet.getString("description")
		);
	}
	
}
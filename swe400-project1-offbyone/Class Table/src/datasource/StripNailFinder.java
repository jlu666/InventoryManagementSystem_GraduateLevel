package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * It is a Finder which is used to load StripNailRowGateway from database according to specific condition.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class StripNailFinder {
	/**
	 * Sql commands: query the StripNail base on the specific id.
	 */
	public static final String SELECT_SQL = "SELECT * FROM inventory_item "
			+ "LEFT JOIN fastener ON inventory_item.id = fastener.id "
			+ "LEFT JOIN strip_nails ON fastener.id = strip_nails.id "
			+ "WHERE inventory_item.id = ?;";
	
	/**
	 * Sql commands: query the StripNail base on the specific PowertTool's id.
	 */
	private static final String SELECT_BASE_TOOL = "SELECT it.id, it.upc, it.manufacturerId, it.price,fn.length, snail.number_in_strip"
			+" FROM inventory_item it,fastener fn, strip_nails snail, powertool_stripnail sn"
			+" WHERE sn.powertoolId = ? AND sn.stripnailId = it.id AND it.id = fn.id AND fn.id = snail.id"
			+ " ORDER BY it.id;";
	
	/**
	 * Gets the StripNailRowGateway which uses this id.
	 * @param id the identify number
	 * @return the StripNailRowGatewa which uses this id.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException  ClassNotFoundException
	 */
	public static StripNailGateway find(int id) throws ClassNotFoundException, SQLException{
		PreparedStatement stmt = Database.prepareStatement(SELECT_SQL);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return structure(rs);
		}	
		else{
			rs.close();
			stmt.close();
			return null;
		}
	}
	
	/**
	 * Returns a list which contains all StripNailGateway that have a relationship with the specific PowerToolId. 
	 * @param powerToolId The PowerTool's id
	 * @return a list which contains all StripNailGateway that have a relationship with the specific PowerToolId.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static ArrayList<StripNailGateway> findByPowerTool(int powerToolId) throws ClassNotFoundException, SQLException{
		PreparedStatement statement = Database.prepareStatement(SELECT_BASE_TOOL);
		statement.setInt(1, powerToolId);
		ResultSet rs = statement.executeQuery();
		ArrayList<StripNailGateway> list = new ArrayList<StripNailGateway>();
		StripNailGateway gateway = null;
		while(rs.next()){
			gateway= structure(rs);
			list.add(gateway);
		}
		return list;
	}
	
	/**
	 * Return a StripNailRowGateway which has been loaded base on the ResultSet.
	 * @param resultSet ResultSet contains data.
	 * @return a StripNailRowGateway which has been loaded base on the ResultSet.
	 * @throws SQLException  SQLExceptions
	 */
	protected static StripNailGateway structure(ResultSet resultSet) throws SQLException {
		return new StripNailGateway(
			resultSet.getInt("id"),
			resultSet.getString("upc"),
			resultSet.getInt("manufacturerId"),
			resultSet.getInt("price"),
			resultSet.getDouble("length"),
			resultSet.getInt("number_in_strip")
		);
	}
	
}

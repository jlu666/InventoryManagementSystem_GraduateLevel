package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * It is a Finder which is used to load NailRowGateway from database according to specific condition.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class NailFinder {

	/**
	 * Sql commands: query the Nail base on the specific id. 
	 */
	public static final String SELECT_SQL = "SELECT * FROM inventory_item "
			+ "LEFT JOIN fastener ON inventory_item.id = fastener.id "
			+ "LEFT JOIN nail ON fastener.id = nail.id "
			+ "WHERE inventory_item.id = ?;";
	
	/**
	 * Gets the NailRowGateway which uses this id.
	 * 
	 * @param id the identify number
	 * @return the NailRowGatewa which uses this id.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static NailGateway find(int id) throws ClassNotFoundException, SQLException{
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
	 * Return a NailRowGateway which has been loaded base on the ResultSet.
	 * 
	 * @param resultSet ResultSet contains data.
	 * @return a NailRowGateway which has been loaded base on the ResultSet
	 * @throws SQLException SQLException
	 */
	protected static NailGateway structure(ResultSet resultSet) throws SQLException {
		return new NailGateway(
			resultSet.getInt("id"),
			resultSet.getString("upc"),
			resultSet.getInt("manufacturerId"),
			resultSet.getInt("price"),
			resultSet.getDouble("length"),
			resultSet.getInt("number_in_box")
		);
	}
	
}

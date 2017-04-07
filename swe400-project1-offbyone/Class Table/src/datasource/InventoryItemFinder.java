package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * It is a Finder which is used to load inventoryItem from database according to specific condition.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class InventoryItemFinder {
	
	private static final String ALL_SQL = "SELECT * FROM inventory_item" +
			" LEFT JOIN tool ON inventory_item.id = tool.id" +
			" LEFT JOIN power_tool ON tool.id = power_tool.id" +
			" LEFT JOIN fastener ON fastener.id = inventory_item.id" +
			" LEFT JOIN nail ON nail.id = fastener.id" + 
			" LEFT JOIN strip_nails ON strip_nails.id = fastener.id";
	
	private static final String ID_SQL = ALL_SQL + " WHERE inventory_item.id = ?";
	
	private static final String UPC_SQL = ALL_SQL + " WHERE inventory_item.upc = ?";
	
	/**
	 * Gets a list which contains all the InventoryRowGateway stored in the database.
	 * 
	 * @return a list which contains all the InventoryItem stored in the database
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static ArrayList<InventoryItemGateway> all() throws ClassNotFoundException, SQLException {
		PreparedStatement statement = Database.prepareStatement(ALL_SQL);
		ResultSet resultSet = statement.executeQuery();
		ArrayList<InventoryItemGateway> gateways = new ArrayList<InventoryItemGateway>();
		
		while(resultSet.next()) {
			gateways.add(restore(resultSet));
		}
		
		return gateways;
	}
	
	/**
	 * Gets the InventoryRowGateway which uses this id.
	 * @param id the particular id
	 * @return the InventoryRowGateway.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException  SQLException
	 */
	public static InventoryItemGateway find(int id) throws ClassNotFoundException, SQLException {
		PreparedStatement statement = Database.prepareStatement(ID_SQL);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return restore(resultSet);
		}
		
		return null;
	}
	
	/**
	 * Gets the InventoryRowGateway which uses this upc.
	 * @param upc the particular upc
	 * @return the InventoryRowGateway 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static InventoryItemGateway findByUpc(String upc) throws ClassNotFoundException, SQLException {
		PreparedStatement statement = Database.prepareStatement(UPC_SQL);
		statement.setString(1, upc);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return restore(resultSet);
		}
		
		return null;
	}
	
	/**
	 * Loads the data which stored in ResultSet into InventoryItemGateway.
	 * Return the InventoryItemGateway.
	 * 
	 * @param resultSet the ResultSet which contains data.
	 * @return The InventoryItemGateway with the data.
	 * @throws SQLException  SQLException
	 */
	private static final InventoryItemGateway restore(ResultSet resultSet) throws SQLException {
		String typeCode = resultSet.getString("typeCode");
		
		if(TypeCode.Tool.toString().equals(typeCode)) {
			return ToolFinder.structure(resultSet);
		} else if(TypeCode.PowerTool.toString().equals(typeCode)) {
			return PowerToolFinder.structure(resultSet);
		} else if(TypeCode.Nail.toString().equals(typeCode)) {
			return NailFinder.structure(resultSet);
		} else if(TypeCode.StripNail.toString().equals(typeCode)) {
			return StripNailFinder.structure(resultSet);
		}
		
		return null;
	}

}

package domain.inventory_item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import datasource.InventoryItemFinder;
import datasource.InventoryItemGateway;
import datasource.NailGateway;
import datasource.PowerToolGateway;
import datasource.StripNailGateway;
import datasource.ToolGateway;
import datasource.TypeCode;
import domain.nail.NailMapper;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNailMapper;
import domain.tool.ToolMapper;

/**
 * It is used to search invenotryItem from database according to some conditions.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class InventoryItemSearcher {
	
	/**
	 * 
	 * @return All the InventoryItem which are stored in database, sorted in order of Nails, Power Tools, Strip Nails, Tools
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException  SQLException
	 */
	public static ArrayList<InventoryItem> all() throws ClassNotFoundException, SQLException {
		ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
		
		for(InventoryItemGateway gateway: InventoryItemFinder.all()) {
			items.add(restore(gateway));
		}
		
		Collections.sort(items, (o1, o2) -> o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName()));
		return items;
	}
	
	/**
	 * 
	 * @param id identify number.
	 * @return InventoryItem uses the id
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static InventoryItem find(int id) throws ClassNotFoundException, SQLException {
		InventoryItemGateway gateway = InventoryItemFinder.find(id);
		return restore(gateway);
	}
	
	/**
	 * 
	 * @param upc upc code.
	 * @return InventoryItem uses the upc.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static InventoryItem findByUpc(String upc) throws ClassNotFoundException, SQLException {
		InventoryItemGateway gateway = InventoryItemFinder.findByUpc(upc);
		return restore(gateway);
	}
	
	private static final InventoryItem restore(InventoryItemGateway gateway) {
		if(gateway != null) {
			String typeCode = gateway.getTypeCode().toString();
			
			if(TypeCode.Tool.toString().equals(typeCode)) {
				return ToolMapper.restore((ToolGateway)gateway);
			} else if(TypeCode.PowerTool.toString().equals(typeCode)) {
				return PowerToolMapper.restore((PowerToolGateway)gateway);
			} else if(TypeCode.Nail.toString().equals(typeCode)) {
				return NailMapper.restore((NailGateway)gateway);
			} else if(TypeCode.StripNail.toString().equals(typeCode)) {
				return StripNailMapper.restore((StripNailGateway)gateway);
			}
		}
		
		return null;
	}

}

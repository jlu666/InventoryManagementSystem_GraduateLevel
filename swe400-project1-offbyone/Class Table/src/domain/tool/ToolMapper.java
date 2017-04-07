package domain.tool;
import java.sql.SQLException;

import datasource.RowGateway;
import datasource.ToolFinder;
import datasource.ToolGateway;
import domain.inventory_item.InventoryItemMapper;
import domain.supertype.DomainObject;

/**
 * ToolMapper provide a mapping between Tool object and database. It is used to manage the PowerTool Object
 * saving and loading.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class ToolMapper extends InventoryItemMapper {
	
	/**
	 * Finds the Tool that uses this id from database.
	 * @param id the Identify number.
	 * @return Tool use this id. If there is no Tool that uses this id, return null.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static Tool find(int id) throws ClassNotFoundException, SQLException {
		ToolGateway gateway = ToolFinder.find(id);
		return restore(gateway);
	}
	
	/**
	 * Creates a new Tool entity into database.
	 * @param upc  upc
	 * @param manufacturerId manufactureId
	 * @param price price
	 * @param description length
	 * @return the Tool has been created.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static Tool create(String upc, int manufacturerId, int price, String description) throws ClassNotFoundException, SQLException {
		Tool tool = new Tool();
		tool.setUpc(upc);
		tool.setManufacturerId(manufacturerId);
		tool.setPrice(price);
		tool.setDescription(description);
		store(tool);
		return tool;
	}
	
	/**
	 * Loads the data into entity from a gateway.
	 * @param gateway the specific gateway
	 * @return the entity with data.
	 */
	public static Tool restore(ToolGateway gateway) {
		Tool tool = new Tool();
		ToolMapper mapper = new ToolMapper();
		mapper.load(tool, gateway);
		return tool;
	}
	
	/**
	 * Stores the data into gateway from an entity.
	 * @param tool the entity
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void store(Tool tool) throws ClassNotFoundException, SQLException {
		ToolGateway gateway = new ToolGateway();
		ToolMapper mapper = new ToolMapper();
		mapper.save(tool, gateway);
		gateway.save();
		mapper.load(tool, gateway);
	}
	
	/**
     * Pulls values from the Gateway and sets them on the domain object.
     */
	protected void load(DomainObject object, RowGateway gateway) {
		super.load(object, gateway);
		((Tool)object).setDescription(((ToolGateway)gateway).getDescription());
	}
	
	/**
     * Pulls values from the domain object and saves them in the database.
     */
	protected void save(DomainObject object, RowGateway gateway) {
		super.save(object, gateway);
		((ToolGateway)gateway).setDescription(((Tool)object).getDescription());
	}
	
}
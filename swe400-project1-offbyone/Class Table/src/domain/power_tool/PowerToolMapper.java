package domain.power_tool;

import java.sql.SQLException;
import java.util.ArrayList;

import datasource.PowerToolFinder;
import datasource.PowerToolGateway;
import datasource.RowGateway;
import domain.Virtualist;
import domain.supertype.DomainObject;
import domain.tool.ToolMapper;

/**
 * PowerToolMapper provide a mapping between PowerTool object and database. It is used to manage the PowerTool Object
 * saving and loading.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class PowerToolMapper extends ToolMapper {

	/**
	 * Finds the PowerTool that uses this id from database.
	 * @param id the Identify number.
	 * @return PowerTool use this id. If there is no PowerTool that uses this id, return null.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static PowerTool find(int id) throws ClassNotFoundException, SQLException {
		PowerToolGateway gateway = PowerToolFinder.find(id);
		return restore(gateway);
	}
	
	/**
	 * Creates a new PowerTool entity into database.
	 * @param upc  upc
	 * @param manufacturerId manufactureId
	 * @param price price
	 * @param description length
	 * @param batteryPowered batteryPowered
	 * @return the PowerTool has been created.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static PowerTool create(String upc, int manufacturerId, int price, String description, boolean batteryPowered) throws ClassNotFoundException, SQLException {
		PowerTool powerTool = new PowerTool();
		powerTool.setUpc(upc);
		powerTool.setManufacturerId(manufacturerId);
		powerTool.setPrice(price);
		powerTool.setDescription(description);
		powerTool.setBatteryPowered(batteryPowered);
		store(powerTool);
		return powerTool;
	}
	
	/**
	 * Loads the data into entity from a gateway.
	 * @param gateway the specific gateway
	 * @return the entity with data.
	 */
	public static PowerTool restore(PowerToolGateway gateway) {
		PowerTool powerTool = new PowerTool();
		PowerToolMapper mapper = new PowerToolMapper();
		mapper.load(powerTool, gateway);
		return powerTool;
	}
	
	/**
	 * Stores the data into gateway from an entity.
	 * @param tool the entity
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void store(PowerTool tool) throws ClassNotFoundException, SQLException {
		PowerToolGateway gateway = new PowerToolGateway();
		PowerToolMapper mapper = new PowerToolMapper();
		mapper.save(tool, gateway);
		gateway.save();
		mapper.load(tool, gateway);
	}
	
	/**
	 *  Gets all the PowerTools related to this StripNail's id.
	 * @param id the StripNail's id
	 * @return  all the PowerTools related to this StripNail's id.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static ArrayList<PowerTool> getPowerToolsByStripNail(int id) throws ClassNotFoundException, SQLException{
		ArrayList<PowerToolGateway> gateways = PowerToolFinder.findByStripNail(id);
		ArrayList<PowerTool> tools = new ArrayList<>();
		PowerToolMapper mapper = new PowerToolMapper();
		for(PowerToolGateway gateway:gateways){
			PowerTool tool = new PowerTool();
			mapper.load(tool, gateway);
			tools.add(tool);
		}
		return tools;
		
	}
	
	 /**
     * Pulls values from the Gateway and sets them on the domain object.
     */
	@SuppressWarnings("unchecked")
	protected void load(DomainObject object, RowGateway gateway) {
		super.load(object, gateway);
		((PowerTool)object).setBatteryPowered(((PowerToolGateway)gateway).isBatteryPowered());
		((PowerTool)object).setStripNails(new Virtualist(gateway.getId(),new PowerToolMapper()));
		
	}
	
	/**
     * Pulls values from the domain object and saves them in the database.
     */
	protected void save(DomainObject object, RowGateway gateway) {
		super.save(object, gateway);
		((PowerToolGateway)gateway).setBatteryPowered(((PowerTool)object).isBatteryPowered());
	}

}

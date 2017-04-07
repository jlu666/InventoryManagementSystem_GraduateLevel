package model;
import java.sql.SQLException;
import java.util.ArrayList;
import datasource.PowerToolGateway;
import datasource.ProxyPowerToolsToStripNails;

/**
 * Instance of Power Tool
 * @author Ronald Sease & Darnell Martin
 *
 */
public class PowerTool extends Tool
{
	boolean batteryPowered;
	ProxyPowerToolsToStripNails proxy;
	PowerToolGateway gateway;
	
	/**
	 * Creation constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param Description
	 * @param batteryPowered
	 * @throws SQLException 
	 */
	public PowerTool(String UPC, int ManufacturerID, int price, String Description, boolean batteryPowered) throws SQLException
	{
		this.UPC = UPC;
		this.ManufacturerID = ManufacturerID;
		this.Price = price;
		this.Description = Description;
		this.batteryPowered = batteryPowered;
		/**
		 *Save the newly constructed object in a table
		 */
		this.gateway = new PowerToolGateway(UPC, ManufacturerID, price,Description, batteryPowered);
		this.id = this.gateway.getID();
	}
	
	/**
	 * Finder constructor using an id
	 * @param id
	 * @throws SQLException 
	 */
	public PowerTool(int id) throws SQLException
	{
		this.gateway = new PowerToolGateway(id);
		this.id = this.gateway.getID();
		this.UPC = this.gateway.getUPC();
		this.ManufacturerID = this.gateway.getManufacturerID();
		this.Price = this.gateway.getPrice();
		this.Description = this.gateway.getDescription();
		this.batteryPowered = this.gateway.getBatteryPowered();
	}
	
	/**
	 * Finder constructor using a upc
	 * @param upc - upc to look for
	 * @throws SQLException 
	 */
	public PowerTool(String upc) throws SQLException 
	{
		super();
		this.gateway = new PowerToolGateway(upc);
		this.id = this.gateway.getID();
		this.UPC = this.gateway.getUPC();
		this.ManufacturerID = this.gateway.getManufacturerID();
		this.Price = this.gateway.getPrice();
		this.Description = this.gateway.getDescription();
		this.batteryPowered = this.gateway.getBatteryPowered();
		
		this.proxy = new ProxyPowerToolsToStripNails(this.id);
	}
	
	/**
	 * Empty constructor to be used for the relation
	 */
	public PowerTool() 
	{
		
	}

	/**
	 * Sets the upc
	 * @param upc
	 * @throws SQLException 
	 */
	public void setUPC(String upc) throws SQLException 
	{
		this.gateway.setUPC(upc);
		this.UPC = upc;
	}
	
	/**
	 * Sets the manufacturerID
	 * @param manufacturerID
	 * @throws SQLException 
	 */
	public void setManufacturerID(int manufacturerID) throws SQLException 
	{
		this.gateway.setManufacturerID(manufacturerID);
		this.ManufacturerID = manufacturerID;
	}

	/**
	 * Sets the price
	 * @param price
	 * @throws SQLException 
	 */
	public void setPrice(int price) throws SQLException 
	{
		this.gateway.setPrice(price);
		this.Price = price;
	}
	
	/**
	 * Sets the description
	 * @param description
	 * @throws SQLException 
	 */
	public void setDescription(String description) throws SQLException
	{
		this.gateway.setDescription(description);
		this.Description = description;
	}

	/**
	 * Sets  battery powered
	 * @param batteryPowered
	 * @throws SQLException 
	 */
	public void setBatteryPowered(boolean batteryPowered) throws SQLException
	{
		this.gateway.setBatteryPowered(batteryPowered);
		this.batteryPowered = batteryPowered;
	}
	
	/**
	 * @return all the variables out for the power tool in proper format
	 */
	@Override
	public String toString()
	{
		return "PowerTool: "+ "UPC: " + this.UPC  +", Manufacturer ID:" + this.ManufacturerID + ", Price:" + this.Price + ", Description:" + 
				this.Description + ", is Battery Powered:" + this.batteryPowered;
	}
	
	/**
	 * Returns whether the power tool is battery powered or not.
	 * @return batteryPowered
	 */
	public boolean getBatteryPowered()
	{
		return this.batteryPowered;
	}
	
	/**
	 * Gets all the power tools related to the strip nail using lazy load,
	 * initializing the mapper only when this getter is called.
	 * @return all the power tools for this strip nail
	 * @throws SQLException
	 */
	public ArrayList<StripNail> getStripNailsForPowerTool() throws SQLException
	{
		return this.proxy.getStripNailsForPowerTool();
	}
}

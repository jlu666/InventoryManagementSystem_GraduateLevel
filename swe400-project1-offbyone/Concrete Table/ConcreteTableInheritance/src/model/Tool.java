package model;
import java.sql.SQLException;

import datasource.ToolGateway;

/**
 * Instance of tool
 * @author Ronald Sease & Darnell Martin
 */
public class Tool extends InventoryItem
{
	protected String Description;
	ToolGateway gateway;
	
	/**
	 * Creator Constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param Description
	 * @throws SQLException 
	 */
	public Tool(String UPC, int ManufacturerID, int price, String Description) throws SQLException
	{
		super(UPC, ManufacturerID, price);
		this.Description = Description;
		
		/**
		 *Save the newly constructed object in a table
		 */
		this.gateway = new ToolGateway(UPC,ManufacturerID, price, Description);
		this.id = this.gateway.getID();
	}
	
	/**
	 * Finder Constructor
	 * @param upc
	 * @throws SQLException 
	 */
	public Tool(String upc) throws SQLException
	{
		this.gateway = new ToolGateway(upc);
		this.id = this.gateway.getID();
		this.UPC = this.gateway.getUPC();
		this.ManufacturerID = this.gateway.getManufacturerID();
		this.Price = this.gateway.getPrice();
		this.Description = this.gateway.getDescription();
		
	}

	/**
	 * Constructor to define null object for PowerTool class
	 */
	public Tool() 
	{}
	

	/**
	 * @return all the variables out for the power tool in proper format
	 */
	@Override
	public String toString()
	{
		return "Tool: UPC:"+ this.UPC  + ", ManufacturerID: " + this.ManufacturerID + ", Price: " + this.Price + ", Description: " + this.Description;
	}
	
	/**
	 * Returns the description of the tool
	 * @return Description
	 */
	public String getDescription()
	{
		return this.Description;
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
}

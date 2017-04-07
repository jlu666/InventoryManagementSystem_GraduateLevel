package model;

import java.sql.SQLException;
import java.util.ArrayList;

import datasource.ProxyPowerToolsToStripNails;
import datasource.StripNailGateway;
/**
 * Instance of strip nail
 * @author Ronald Sease & Darnell Martin
 *
 */
public class StripNail extends Fastner
{
	int numberInStrip;
	ProxyPowerToolsToStripNails proxy;
	StripNailGateway gateway;
	
	/**
	 * Creator Constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param length
	 * @param numberInStrip
	 * @throws SQLException 
	 */
	public StripNail(String UPC, int ManufacturerID, int price, double length, int numberInStrip) throws SQLException
	{
		super(UPC, ManufacturerID, price, length);
		this.numberInStrip = numberInStrip;
		/**
		 *Save the newly constructed object in a table
		 */
		this.gateway = new StripNailGateway(UPC, ManufacturerID, price,length, numberInStrip);
		this.id = this.gateway.getID();
	}
	
	/**
	 * Finder Constructor.
	 * @param id
	 * @throws SQLException 
	 */
	public StripNail(int id) throws SQLException
	{
		this.gateway = new StripNailGateway(id);
		this.id = this.gateway.getID();
		this.UPC = this.gateway.getUPC();
		this.ManufacturerID = this.gateway.getManufacturerID();
		this.Price = this.gateway.getPrice();
		this.length = this.gateway.getLength();
		this.numberInStrip = this.gateway.getNumberInStrip();
		this.proxy = new ProxyPowerToolsToStripNails(this.id);
	}
	
	/**
	 * Finder Constructor.using upc
	 * @param upc 
	 * @throws SQLException 
	 */
	public StripNail(String upc) throws SQLException
	{
		this.gateway = new StripNailGateway(upc);
		
		/**
		 * Assign all of the column from the gateway
		 */
		this.id = this.gateway.getID();
		this.UPC = this.gateway.getUPC();
		this.ManufacturerID = this.gateway.getManufacturerID();
		this.Price = this.gateway.getPrice();
		this.length = this.gateway.getLength();
		this.numberInStrip = this.gateway.getNumberInStrip();
		this.proxy = new ProxyPowerToolsToStripNails(this.id);
	}
	
	/**
	 * Empty constructor to be used for the relation
	 */
	public StripNail() 
	{
	}

	/**
	 * @return all the variables out for the Strip Nail in proper format
	 */
	public String toString()
	{
		return "StripNail: UPC: " + this.UPC +", ManufacturerID: " + this.ManufacturerID + ", Price: " + this.Price + ", Length: " + 
				this.length + ", Number In Strip: " + this.numberInStrip;
	}
	
	/**
	 * Returns the number in strip
	 * @return numberInStrip
	 */
	public int getNumberInStrip()
	{
		return this.numberInStrip;
	}
	
	/**
	 * Gets all the power tools related to the strip nail using lazy load,
	 * initializing the mapper only when this getter is called.
	 * @return all the power tools for this strip nail
	 * @throws SQLException
	 */
	public ArrayList<PowerTool> getPowerToolsForStripNail() throws SQLException
	{
		return this.proxy.getPowerToolsForStripNail();
	}
	
	/**
	 * Sets the UPC and updates it in the database
	 * @param UPC
	 */
	public void setUPC(String UPC)
	{
		this.gateway.setUPC(UPC);
		this.UPC = this.gateway.getUPC();
	}
	
	/**
	 * Sets the manufacturerID and updates it in the database
	 * @param manufacturerID
	 */
	public void setManufacturerID(int manufacturerID)
	{
		this.gateway.setManufacturerID(manufacturerID);
		this.ManufacturerID = this.gateway.getManufacturerID();
	}
	
	/**
	 * Sets the price and updates it in the database
	 * @param price
	 */
	public void setPrice(int price)
	{
		this.gateway.setPrice(price);
		this.Price = this.gateway.getPrice();
	}
	
	/**
	 * Sets the length and updates it in the database
	 * @param length
	 */
	public void setLength(double length)
	{
		this.gateway.setLength(length);
		this.length = this.gateway.getLength();
	}
	
	/**
	 * Sets the numberInBox and updates it in the database
	 * @param numberInBox
	 */
	public void setNumberInStrip(int numberInBox)
	{
		this.gateway.setNumberInBox(numberInBox);
		this.numberInStrip = this.gateway.getNumberInStrip();
	}
}

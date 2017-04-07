package model;
import java.sql.SQLException;

import datasource.NailGateway;
/**
 * Instance of Nail
 * @author Ronald Sease & Darnell Martin
 *
 */
public class Nail extends Fastner
{
	

	int numberInBox;
	NailGateway gateway;
	/**
	 * Creator constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param length
	 * @param numberInBox
	 * @throws SQLException 
	 */
	public Nail(String UPC, int ManufacturerID, int price, double length, int numberInBox) throws SQLException 
	{
		super(UPC, ManufacturerID, price, length);
		this.numberInBox = numberInBox;
		
		/**
		 *Save the newly constructed object in a table
		 */
		this.gateway = new NailGateway(UPC,ManufacturerID, price, length, numberInBox);
		this.id = this.gateway.getID();
	}
	
	/**
	 * Finder constructor using a upc
	 * @param upc
	 * @throws SQLException
	 */
	public Nail(String upc) throws SQLException 
	{
		this.gateway = new NailGateway(upc);
		this.id = this.gateway.getID();
		this.UPC = this.gateway.getUPC();
		this.ManufacturerID = this.gateway.getManufacturerID();
		this.Price = this.gateway.getPrice();
		this.length = this.gateway.getLength();
		this.numberInBox = this.gateway.getNumberInBox();
	}

	/**
	 * @return all the variables out for the nail in proper format
	 */
	@Override
	public String toString()
	{
		return "Nail: UPC: "+ this.UPC +", ManufactuerID: " + this.ManufacturerID + ", Price: " + this.Price + ", Length: "+ 
				this.length + ", Number In Box: " + this.numberInBox;
	}
	
	/**
	 * Returns the number in box
	 * @return numberInBox
	 */
	public int getNumberInBox()
	{
		return this.numberInBox;
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
	public void setNumberInBox(int numberInBox)
	{
		this.gateway.setNumberInBox(numberInBox);
		this.numberInBox = this.gateway.getNumberInBox();
	}
	
}

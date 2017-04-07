package model;
/**
 * Abstract class Inventory Item that all concrete types of inventory method will be based off of. Has a constructor to create a new inventory item
 * from the database, and a constructor to take in the parameters of an inventory item before it is added to the database and is assigned a key.
 * @author Darnell Martin & Ronald Sease
 *
 */
public abstract class InventoryItem 
{
	/**
	 * 
	 */
	public int id;
	protected String UPC;
	protected int ManufacturerID;
	protected int Price;
	
	/**
	 * Finder constructor 
	 * @param id
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 */
	protected InventoryItem(int id, String UPC, int ManufacturerID, int price)
	{
		this.id = id;
		this.UPC = UPC;
		this.ManufacturerID = ManufacturerID;
		this.Price = price;
	}
	
	/**
	 * Creation constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 */
	InventoryItem(String UPC, int ManufacturerID, int price)
	{
		this.UPC = UPC;
		this.ManufacturerID = ManufacturerID;
		this.Price = price;
	}
	
	/**
	 * Empty constructor for testing
	 */
	InventoryItem()
	{}
	
	/**
	 * Returns the id of the inventory item
	 * @return id
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * Returns the UPC inventory item
	 * @return UPC
	 */
	public String getUPC()
	{
		return this.UPC;
	}
	
	/**
	 * Returns the manufacutuerID of the inventory item
	 * @return ManufacturerID
	 */
	public int getManufacturerID()
	{
		return this.ManufacturerID;
	}
	
	/**
	 * Returns the price of the inventory item
	 * @return Price
	 */
	public int getPrice()
	{
		return this.Price;
	}

	/**
	 * Sets the id to the new id that is passed in
	 * @param newID - new id of the inventory item
	 */
	public void setID(int newID)
	{
		this.id = newID;
	}
	
	
	
}

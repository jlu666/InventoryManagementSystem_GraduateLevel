package model;
/**
 * Abstract class for strip nail and nail to be based off of. Extends off Inventory Item
 * @author Ronald Sease & Darnell Martin
 *
 */
public abstract class Fastner extends InventoryItem 
{

	double length;
	
	/**
	 * Creation constructor
	 * @param UPC -
	 * @param ManufacturerID
	 * @param price
	 * @param length
	 */
	protected Fastner(String UPC, int ManufacturerID, int price, double length)
	{
		super(UPC, ManufacturerID, price);
		this.length = length;
	}
	
	/**
	 * Finder Constructor
	 * @param id
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param length
	 */
	Fastner(int id, String UPC, int ManufacturerID, int price, double length)
	{
		super(id, UPC, ManufacturerID, price);
		this.length = length;
	}

	/**
	 * Empty constructor 
	 */
	public Fastner() 
	{
		super();
	}

	/**
	 * Returns the length 
	 * @return length
	 */
	public double getLength() 
	{
		return this.length;
	}

	/**
	 * Sets the length
	 * @param length - length to be set
	 */
	public void setLength(double length) 
	{
		this.length = length;
	}
}

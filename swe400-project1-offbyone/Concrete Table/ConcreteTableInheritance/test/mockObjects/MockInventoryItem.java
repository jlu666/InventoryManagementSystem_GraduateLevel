package mockObjects;

import model.InventoryItem;

/**
 * Used to test the abstract inventory item class
 * @author Ronald Sease & Darnell Martin
 *
 */
public class MockInventoryItem extends InventoryItem
{
	/**
	 * Mock inventory item used for testing
	 * @param id - id assigned by database
	 * @param UPC 
	 * @param ManufacturerID
	 * @param price
	 */
	public MockInventoryItem(int id, String UPC, int ManufacturerID, int price)
	{
		super(id, UPC, ManufacturerID, price);
	}
}

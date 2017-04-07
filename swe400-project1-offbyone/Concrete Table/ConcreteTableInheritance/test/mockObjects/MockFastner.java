package mockObjects;

import model.Fastner;

/**
 * Used to test the Fastner abstract class, as we don't actually
 * use a fastner by itself
 * @author Ronald Sease & Darnell Martin
 *
 */
public class MockFastner extends Fastner
{

	/**
	 * Constructor for Mock Fastner
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param length
	 */
	public MockFastner(String UPC, int ManufacturerID, int price, double length)
	{
		super(UPC, ManufacturerID, price, length);
	}

}

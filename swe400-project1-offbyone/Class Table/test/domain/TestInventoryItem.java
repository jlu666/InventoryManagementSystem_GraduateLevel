package domain;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the root inventory item.
 */
public class TestInventoryItem {

	/**
	 * Test if a root inventory item has any associated items.
	 */
	@Test
	public void testAssociatedItems() {
		MockInventoryItem item = new MockInventoryItem();
		assertEquals(0, item.associatedItems().size());
	}

}

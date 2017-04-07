package domain.tool;
import domain.inventory_item.InventoryItem;

/**
 * It represents Tool.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class Tool extends InventoryItem {

	private String description;

	protected Tool() {
	}

	/**
	 *
	 * @return the description of the tool.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the tool
	 * @param description the description of the tool.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	  /**
     * @return the item in string form.
     */
    public String toString() {
        return "Tool(upc: " + getUpc() + ", manufacturerId: " + getManufacturerId() + ", price: " + getPrice() +", description: "+description+ ")";
    }
}

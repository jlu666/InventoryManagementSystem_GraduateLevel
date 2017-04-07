package domain.fastener;

import domain.inventory_item.InventoryItem;

/**
 * A Fastener abstract class inherited inventoryItem class.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class Fastener extends InventoryItem {

    private double length;

    protected Fastener() {
    }

    /**
     * @return double The length of the fastener.
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the fastener length to the length parameter value.
     * @param length The value to set the fastener length to.
     */
	public void setLength(double length) {
        this.length = length;
    }

    /**
     * @return the item in string form.
     */
    public String toString() {
        return "Fastener(upc: " + getUpc() + ", manufacturerId: " + getManufacturerId() + ", price: " + getPrice() +", length: "+length+ ")";
    }


}

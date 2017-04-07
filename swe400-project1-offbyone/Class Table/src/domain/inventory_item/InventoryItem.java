package domain.inventory_item;

import java.util.ArrayList;
import java.util.List;

import domain.supertype.DomainObject;

/**
 * It represents InventoryItem.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class InventoryItem extends DomainObject {

    private String upc;
    private int manufacturerId;
    private int price;

    protected InventoryItem() {
    }
    
    /**
     * 
     * @return the item's upc code.
     */
    public String getUpc() {
    	return this.upc;
    }
    
    /**
     * 
     * @return the item's manufacturer id
     */
    public int getManufacturerId() {
    	return this.manufacturerId;
    }
    
    /**
     * 
     * @return the item's price.
     */
    public int getPrice() {
    	return price;
    }
    
    /**
     * Sets the item's upc code
     * @param upc  upc code
     */
    public void setUpc(String upc) {
    	this.upc = upc;
    }
    
    /**
     * Sets the item's manufacturer id.
     * @param manufacturerId manufacturer id
     */
    public void setManufacturerId(int manufacturerId) {
    	this.manufacturerId = manufacturerId;
    }
    
    /**
     * Sets the item's price.
     * @param price the item's price
     */
    public void setPrice(int price) {
    	this.price = price;
    }
    
    /**
     * Gets associated Items.
     * @return a list of associated Items.
     */
    public List<InventoryItem> associatedItems() {
    	return new ArrayList<InventoryItem>();
    }

}

package domain.inventory_item;

import datasource.InventoryItemGateway;
import datasource.RowGateway;
import domain.supertype.DomainMapper;
import domain.supertype.DomainObject;

/**
 * AbstractInventoryMapper provide a mapping between Fastener object and database. It is used to manage the Inventory Object
 * saving and loading.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class InventoryItemMapper extends DomainMapper {
	
	/**
     * Pulls values from the Gateway and sets them on the domain object.
     */
	protected void load(DomainObject object, RowGateway gateway) {
		super.load(object, gateway);
		((InventoryItem)object).setUpc(((InventoryItemGateway)gateway).getUpc());
		((InventoryItem)object).setManufacturerId(((InventoryItemGateway)gateway).getManufacturerId());
		((InventoryItem)object).setPrice(((InventoryItemGateway)gateway).getPrice());
	}
	
	/**
     * Pulls values from the domain object and saves them in the database.
     */
	protected void save(DomainObject object, RowGateway gateway) {
		super.save(object, gateway);
		((InventoryItemGateway)gateway).setUpc(((InventoryItem)object).getUpc());
		((InventoryItemGateway)gateway).setManufacturerId(((InventoryItem)object).getManufacturerId());
		((InventoryItemGateway)gateway).setPrice(((InventoryItem)object).getPrice());
	}
	
}

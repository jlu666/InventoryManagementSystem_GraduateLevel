package domain.power_tool;

import java.util.ArrayList;
import java.util.List;

import domain.PowerToolToStripNailMapper;
import domain.inventory_item.InventoryItem;
import domain.strip_nail.StripNail;
import domain.tool.Tool;

/**
 * It represents PowerTool.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class PowerTool extends Tool {

	private boolean isBatteryPowered;
	private List<StripNail> stripNails;

	protected PowerTool() {
		this.isBatteryPowered = false;
		stripNails = new ArrayList<StripNail>();
	}

	/**
	 * @return  whether the power tool is battery powered or not
	 */
	public boolean isBatteryPowered() {
		return this.isBatteryPowered;
	}

	/**
	 * Sets the PowerTool at whether the power tool is battery powered or not.
	 * @param batteryPowered whether the power tool is battery powered or not.
	 */
	public void setBatteryPowered(boolean batteryPowered) {
		this.isBatteryPowered = batteryPowered;
	}

	/**
	 * Gets all the StripNail related to the PowerTool. Using LazyLoad pattern, Virtualist that is stored in this entity
	 * will be load until this method is called.
	 * @return all the StripNail related to the PowerTool.
	 */
	public List<StripNail> getStripNails() {
		return stripNails;
	}

	/**
	 * Sets a list contains all StripNail related to the PowerTool.
	 * @param stripNails the list of StripNails
	 */
	public void setStripNails(List<StripNail> stripNails) {
		this.stripNails = stripNails;
	}

	/**
	 * Adds new StripNail is related to the PowerTool
	 * @param nail newStripNail
	 */
	public void addStripNailRelations(StripNail nail){
		boolean isContains = false;
		for(StripNail sn: stripNails){
			if(sn.getId()==nail.getId()){
				isContains = true;
			}
		}
		if(!isContains){
			stripNails.add(nail);
			PowerToolToStripNailMapper.save(this, nail);
		}
	}

	 /**
	 * @return String the item in string form.
     */
    public String toString() {
        return "PowerTool(upc: " + getUpc() + ", manufacturerId: " + getManufacturerId() + ", price: " + getPrice() +", description: "+getDescription()+", batteryPowered: "+isBatteryPowered+ ")";
    }
    
    /**
     * Returns the list of associated items.
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<InventoryItem> associatedItems() {
    	return (List<InventoryItem>)(Object)this.getStripNails();
    }
}

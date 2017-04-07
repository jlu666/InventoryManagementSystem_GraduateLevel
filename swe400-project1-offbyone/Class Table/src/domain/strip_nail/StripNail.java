package domain.strip_nail;
import java.util.ArrayList;
import java.util.List;

import domain.PowerToolToStripNailMapper;
import domain.fastener.Fastener;
import domain.inventory_item.InventoryItem;
import domain.power_tool.PowerTool;


/**
 * It represents StripNail.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class StripNail extends Fastener {

    private int numberinStrip;
    private List<PowerTool> powerTools;

    protected StripNail() {
    	powerTools = new ArrayList<PowerTool>();
    }

    /**
     * @return the number of nails per strip for this strip nail product.
     */
    public int getNumberinStrip() {
        return numberinStrip;
    }

    /**
     * Sets the number of nails per strip for this strip nail product.
     * @param numberinStrip The number of nails in a strip.
     */
    public void setNumberinStrip(int numberinStrip) {
        this.numberinStrip = numberinStrip;
    }

    /**
	 * Gets all the PowerTools related to the StripNail. Using LazyLoad pattern, Virtualist that is stored in this entity
	 * will be load until this method is called.
	 * @return all the PowerTools related to the StripNail.
	 */
    public List<PowerTool> getPowerTools() {
		return powerTools;
	}


    /**
	 * Sets a list contains all PowerTool related to the StripNail.
	 * @param powerTools the list of StripNails
	 */
	public void setPowerTools(List<PowerTool> powerTools) {
		this.powerTools = powerTools;
	}

	/**
	 * Adds new PowerTool is related to the StripNail.
	 * @param tool new PowerTool
	 */
	public void addPowerToolRelation(PowerTool tool){
		boolean isContains = false;
		for(PowerTool sn: powerTools){
			if(sn.getId()==tool.getId()){
				isContains = true;
			}
		}
		if(!isContains){
			powerTools.add(tool);
			PowerToolToStripNailMapper.save(tool, this);
		}
	}
	/**
     * @return String the item in string form.
     */
    public String toString() {
        return "StripNail(upc: " + getUpc() + ", manufacturerId: " + getManufacturerId() + ", price: " + getPrice() +", length: "+getLength()+", numberInStrip: "+numberinStrip+ ")";
    }
    
    /**
     * Returns the list of associated items.
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<InventoryItem> associatedItems() {
    	return (List<InventoryItem>)(Object)this.getPowerTools();
    }


}

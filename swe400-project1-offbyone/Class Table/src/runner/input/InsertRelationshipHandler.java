package runner.input;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.inventory_item.InventoryItem;
import domain.inventory_item.InventoryItemSearcher;
import domain.power_tool.PowerTool;
import domain.strip_nail.StripNail;

/**
 * The handler is used to get the upc of StripNails or PowerTool, and handles whether the 
 * upc user entered can be used to add the relationship.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class InsertRelationshipHandler extends InputHandler
{

	/**
	 * PowerTool Tag.
	 */
	public static final String POWERTOOL = "tool";
	
	/**
	 * StripNail Tag.
	 */
	public static final String STRIPNAIL = "nail"; 
	private ArrayList<String> upcs;
	
	/**
	 * Constructor.
	 * @param toolOrNail Tag if tag is STRIPNAIL means the handler stores the upc of StripNail.
	 * Otherwise, the upc is PowerTool's upc.
	 */
	public InsertRelationshipHandler(String toolOrNail) 
	{
		super("InsertRelationship", "Please enter the upc which you want to create a relationship", "Please enter a valid upc");
		upcs = new ArrayList<String>();
		try{
			if(toolOrNail.equals(STRIPNAIL)){
				ArrayList<InventoryItem> items = InventoryItemSearcher.all();
				for(InventoryItem item: items){
					if(item instanceof StripNail){
						upcs.add(item.getUpc());
					}
				}
			}
			else if(toolOrNail.equals(POWERTOOL)){
				ArrayList<InventoryItem> items = InventoryItemSearcher.all();
				for(InventoryItem item: items){
					if(item instanceof PowerTool){
						upcs.add(item.getUpc());
					}
				}
			}
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

	}

	@Override
	protected boolean validate(String value)
	{
		
		return upcs.contains(value.toLowerCase());
	}

}

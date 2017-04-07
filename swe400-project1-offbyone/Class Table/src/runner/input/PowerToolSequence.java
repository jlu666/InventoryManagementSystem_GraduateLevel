package runner.input;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.inventory_item.InventoryItem;
import domain.inventory_item.InventoryItemSearcher;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;

/**
 * The class is used to get the information about inventoryItem from Scanner, and handle the 
 * input and store the information to database. The class stores a list of handlers that used to
 * handle PowerTool item.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class PowerToolSequence extends ToolSequence {
	
	/**
	 * Constructor.
	 * @param output PrintStream.
	 * @param input Scanner
	 * @param item PowerTool item
	 */
	public PowerToolSequence(PrintStream output, Scanner input, InventoryItem item) {
		super(output, input, item);
		this.handlers.add(new BatteryPoweredHandler());
		this.handlers.add(new AddRelationShipHandler());
		this.setValue("Battery Powered", ((PowerTool)this.item).isBatteryPowered());
		
	}

	/**
	 * Constructor.
	 * @param output PrinStream
	 * @param input Scanner.
	 */
	public PowerToolSequence(PrintStream output, Scanner input) {
		super(output, input);
		this.handlers.add(new BatteryPoweredHandler());
		this.handlers.add(new AddRelationShipHandler());
	}
	
	/**
	 * Execute the handler it stores.
	 */
	public void execute() {
		super.execute();

		if(this.getValue("addRelation").equals("y")) {
			if(item !=null){
				List<StripNail> list =((PowerTool)item).getStripNails();
				if(list.size()>0){
					this.output.println("Current relevant item:");
					for(StripNail item:list){
						this.output.println(item);
					}
				}
			}
			InsertRelationshipHandler handler = new InsertRelationshipHandler(InsertRelationshipHandler.STRIPNAIL);
			this.handlers.add(handler);
			handler.handle(this.output,this.input);
		}

	}
	
	/**
	 * Inserts the PowerTool into database based on the information stored in the handlers.
	 * @throws ClassNotFoundException ClassNotFoundException 
	 * @throws SQLException SQLException
	 * 
	 */
	public void insert() throws ClassNotFoundException, SQLException {
		String upc = this.getValue("upc");
		int manufacturerID = Integer.parseInt(this.getValue("ManufacturerID"));
		int price = Integer.parseInt(this.getValue("price"));
		String description = this.getValue("description");
		boolean batteryPowered = (this.getValue("Battery Powered").toLowerCase().equals("true"))?true:false;
		item = PowerToolMapper.create(upc, manufacturerID, price, description, batteryPowered);
		insertRelation();
	}
	
	/**
	 * Updates the PowerTool into database based on the information stored in the handlers.
	 * @throws ClassNotFoundException ClassNotFoundException 
	 * @throws SQLException SQLException
	 * 
	 */
	@Override
	public void update() throws ClassNotFoundException, SQLException {
		String upc = this.getValue("upc");
		int manufacturerID = Integer.parseInt(this.getValue("ManufacturerID"));
		int price = Integer.parseInt(this.getValue("price"));
		String description = this.getValue("description");
		boolean batteryPowered = (this.getValue("Battery Powered").toLowerCase().equals("true"))?true:false;
		((PowerTool)item).setUpc(upc);
		((PowerTool)item).setManufacturerId(manufacturerID);
		((PowerTool)item).setPrice(price);
		((PowerTool)item).setDescription(description);
		((PowerTool)item).setBatteryPowered(batteryPowered);
		PowerToolMapper.store(((PowerTool)item));
		insertRelation();
		
	}
	
	/**
	 * Inserts the Relationship between StripNail and PowerTool the class stored into database.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException  SQLException
	 */
	private void insertRelation() throws ClassNotFoundException, SQLException{
		if(this.getValue("addRelation").equals("y")) {
			StripNail added =((StripNail)InventoryItemSearcher.findByUpc( this.getValue("InsertRelationship")));
			((PowerTool)item).addStripNailRelations(added);
			output.println("The added Item:");
			output.println(added);
		}
	}

}

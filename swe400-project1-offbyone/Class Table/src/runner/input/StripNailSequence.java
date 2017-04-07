package runner.input;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.inventory_item.InventoryItem;
import domain.inventory_item.InventoryItemSearcher;
import domain.power_tool.PowerTool;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;

/**
 * The class is used to get the information about inventoryItem from Scanner, and handle the 
 * input and store the information to database. The class stores a list of handlers that used to
 * handle StripNail item.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class StripNailSequence extends FastenerSequence {
	
	/**
	 * Constructor.
	 * @param output PrintStream
	 * @param input Scanner
	 * @param item StripNail item
	 */
	public StripNailSequence(PrintStream output, Scanner input, InventoryItem item) {
		super(output, input, item);
		this.handlers.add(new NumberInStripHandler());
		this.handlers.add(new AddRelationShipHandler());
		this.setValue("Number In Strip", ((StripNail)this.item).getNumberinStrip());
	}
	
	/**
	 * Constructor.
	 * @param output PrintStream
	 * @param input Scanner.
	 */
	public StripNailSequence(PrintStream output, Scanner input) {
		super(output,input);
		this.handlers.add(new NumberInStripHandler());
		this.handlers.add(new AddRelationShipHandler());
	}
	
	/**
	 * Execute the handler it stores.
	 */
	public void execute() {
		super.execute();
		
		if(this.getValue("addRelation").equals("y")) {
			if(item!=null){
				List<PowerTool> list =((StripNail)item).getPowerTools();
				if(list.size()>0){
					this.output.println("Current relevant item:");
					for(PowerTool item:list){
						this.output.println(item);
					}
				}
			}
		InsertRelationshipHandler handler = new InsertRelationshipHandler(InsertRelationshipHandler.POWERTOOL);
		this.handlers.add(handler);
		handler.handle(this.output,this.input);		
		}
		
	}
	
	/**
	 * Inserts the StripNail into database based on the information stored in the handlers.
	 * @throws ClassNotFoundException ClassNotFoundException 
	 * @throws SQLException SQLException
	 * 
	 */
	public void insert() throws ClassNotFoundException, SQLException {
		String upc = this.getValue("upc");
		int manufacturerID = Integer.parseInt(this.getValue("ManufacturerID"));
		int price = Integer.parseInt(this.getValue("price"));
		double length = Double.parseDouble( this.getValue("length"));
		int inStrip = Integer.parseInt(this.getValue("Number In Strip"));
		item = StripNailMapper.create(upc, manufacturerID, price, length, inStrip);
		insertRelation();
	}

	/**
	 * Updates the StripNail into database based on the information stored in the handlers.
	 * @throws ClassNotFoundException ClassNotFoundException 
	 * @throws SQLException SQLException
	 * 
	 */
	@Override
	public void update() throws ClassNotFoundException, SQLException {
		String upc = this.getValue("upc");
		int manufacturerID = Integer.parseInt(this.getValue("ManufacturerID"));
		int price = Integer.parseInt(this.getValue("price"));
		double length = Double.parseDouble( this.getValue("length"));
		int inStrip = Integer.parseInt(this.getValue("Number In Strip"));
		((StripNail)item).setUpc(upc);
		((StripNail)item).setManufacturerId(manufacturerID);
		((StripNail)item).setPrice(price);
		((StripNail)item).setLength(length);
		((StripNail)item).setNumberinStrip(inStrip);
		StripNailMapper.store(((StripNail)item));
		insertRelation();		
	}
	
	/**
	 * Inserts the Relationship between PowerTool and StripNail the class stored into database.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException  SQLException
	 */
	private void insertRelation() throws ClassNotFoundException, SQLException{
		if(this.getValue("addRelation").equals("y")) {
			PowerTool added =((PowerTool)InventoryItemSearcher.findByUpc( this.getValue("InsertRelationship")));
			((StripNail)item).addPowerToolRelation(added);
			output.println("The added Item:");
			output.println(added);
		}
	}
	
}
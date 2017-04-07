package runner.input;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

import domain.inventory_item.InventoryItem;
import domain.tool.Tool;
import domain.tool.ToolMapper;

/**
 * The class is used to get the information about inventoryItem from Scanner, and handle the 
 * input and store the information to database. The class stores a list of handlers that used to
 * handle Tool item.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class ToolSequence extends InventoryItemSequence {
	
	/**
	 * Constructor.
	 * @param output PrintStream
	 * @param input Scanner
	 * @param item Tool item
	 */
	public ToolSequence(PrintStream output, Scanner input, InventoryItem item) {
		super(output, input, item);
		this.handlers.add(new DescriptionHandler());
		this.setValue("description", ((Tool)this.item).getDescription());
	}
	
	/**
	 * Constructor
	 * @param output PrintStream
	 * @param input Scanner.
	 */
	public ToolSequence(PrintStream output, Scanner input) {
		super(output, input);
		this.handlers.add(new DescriptionHandler());
	}
	
	/**
	 * Inserts the Tool into database based on the information stored in the handlers.
	 * @throws ClassNotFoundException ClassNotFoundException 
	 * @throws SQLException SQLException
	 * 
	 */
	public void insert() throws ClassNotFoundException, SQLException {
		String upc = this.getValue("upc");
		int manufacturerID = Integer.parseInt(this.getValue("ManufacturerID"));
		int price = Integer.parseInt(this.getValue("price"));
		String description = this.getValue("description");
		ToolMapper.create(upc, manufacturerID, price, description);
	}

	/**
	 * Updates the Tool into database based on the information stored in the handlers.
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
		((Tool)item).setUpc(upc);
		((Tool)item).setManufacturerId(manufacturerID);
		((Tool)item).setPrice(price);
		((Tool)item).setDescription(description);
		ToolMapper.store(((Tool)item));
		
		
	}
	

}

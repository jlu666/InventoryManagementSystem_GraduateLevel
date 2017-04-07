package runner.input;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.inventory_item.InventoryItem;

/**
 * The class is used to get the information about inventoryItem from Scanner, and handle the 
 * input and store the information to database. The class stores a list of handlers that used to
 * handle particular item.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public abstract class InventoryItemSequence {
	
	protected PrintStream output;
	protected Scanner input;
	ArrayList<InputHandler> handlers;
	InventoryItem item;
	
	/**
	 * Constructor.
	 * @param output PrintStream
	 * @param input Scanner
	 * @param item the Inventory it stored.
	 */
	public InventoryItemSequence(PrintStream output, Scanner input, InventoryItem item) {
		this(output, input);
		this.item = item;
		this.setValue("upc", this.item.getUpc());
		this.setValue("ManufacturerID", this.item.getManufacturerId());
		this.setValue("price", this.item.getPrice());
	}
	
	/**
	 * Constructor.
	 * @param output PrintStream
	 * @param input Scanner
	 */
	public InventoryItemSequence(PrintStream output, Scanner input) {
		this.output = output;
		this.input = input;
		this.handlers = new ArrayList<InputHandler>();
		this.handlers.add(new UpcHandler());
		this.handlers.add(new ManufacturerIDHandler());
		this.handlers.add(new PriceHandler());
	}
	
	/**
	 * Execute the list of handlers.
	 */
	public void execute() {
		for(InputHandler handler: this.handlers) {
			handler.handle(output, input);
		}
	}
	
	/**
	 * Gets the handler according to name.
	 * @param name
	 * @return
	 */
	private InputHandler findHandle(String name) {
		for(InputHandler handler: this.handlers) {
			if(handler.getName().toLowerCase().equals(name.toLowerCase())) {
				return handler;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets values of handler based on the name of handler.
	 * @param name the name of handler.
	 * @return  values of handler based on the name of handler.
	 */
	public String getValue(String name) {
		InputHandler handler = this.findHandle(name);
		
		if(handler != null) {
			return handler.getValue();
		} else {
			return null;
		}
	}
	
	/**
	 * Sets the value of handler based on the name of handler.
	 * @param name the name of handler.
	 * @param value values of handler.
	 */
	public void setValue(String name, String value) {
		InputHandler handler = this.findHandle(name);
		
		if(handler != null) {
			handler.setValue(value);
		}
	}
	
	/**
	 * Sets the value of handler based on the name of handler.
	 * @param name the name of handler.
	 * @param value  value values of handler.
	 */
	public void setValue(String name, int value) {
		this.setValue(name, "" + value);
	}
	
	/**
	 * Sets the value of handler based on the name of handler.
	 * @param name the name of handler.
	 * @param value  value values of handler.
	 */
	public void setValue(String name, double value) {
		this.setValue(name, "" + value);
	}
	
	/**
	 * Sets the value of handler based on the name of handler.
	 * @param name the name of handler.
	 * @param value  value values of handler.
	 */
	public void setValue(String name, boolean value) {
		this.setValue(name, value ? "true" : "false");
	}
	
	/**
	 * Insert the inventoryItem into database based on the information stored in the handlers
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public abstract void insert() throws ClassNotFoundException, SQLException;
	/**
	 * Updates the inventoryItem into database based on the information stored in the handlers.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public abstract void update() throws ClassNotFoundException, SQLException;	
	
}


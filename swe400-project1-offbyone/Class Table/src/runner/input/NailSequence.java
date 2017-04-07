package runner.input;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

import domain.inventory_item.InventoryItem;
import domain.nail.Nail;
import domain.nail.NailMapper;

/**
 * The class is used to get the information about inventoryItem from Scanner, and handle the 
 * input and store the information to database. The class stores a list of handlers that used to
 * handle Nail item.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class NailSequence extends FastenerSequence {
	
	/**
	 * Constructor.
	 * @param output PrintStream.
	 * @param input Scanner.
	 * @param item the Nail item.
	 */
	public NailSequence(PrintStream output, Scanner input, InventoryItem item) {
		super(output, input, item);
		this.handlers.add(new NumberInBoxHandler());
		this.setValue("Number In Box", ((Nail)this.item).getnumberInBox());
	}
	
	/**
	 * Constructor.
	 * @param output PrintStream
	 * @param input Scanner
	 */
	public NailSequence(PrintStream output, Scanner input) {
		super(output, input);
		this.handlers.add(new NumberInBoxHandler());
	}
	
	/**
	 * Inserts the Nail into database based on the information stored in the handlers.
	 * @throws ClassNotFoundException ClassNotFoundException 
	 * @throws SQLException SQLException
	 * 
	 */
	public void insert() throws ClassNotFoundException, SQLException {
		String upc = this.getValue("upc");
		int manufacturerID = Integer.parseInt(this.getValue("ManufacturerID"));
		int price = Integer.parseInt(this.getValue("price"));
		double length = Double.parseDouble( this.getValue("length"));
		int inBox = Integer.parseInt(this.getValue("Number In Box"));
		NailMapper.create(upc, manufacturerID, price, length, inBox);
	}

	/**
	 * Updates the Nail into database based on the information stored in the handlers.
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
		int inBox = Integer.parseInt(this.getValue("Number In Box"));
		((Nail)item).setUpc(upc);
		((Nail)item).setManufacturerId(manufacturerID);
		((Nail)item).setnumberInBox(inBox);
		((Nail)item).setLength(length);
		((Nail)item).setPrice(price);
		NailMapper.store(((Nail)item));
		
	}
	
}

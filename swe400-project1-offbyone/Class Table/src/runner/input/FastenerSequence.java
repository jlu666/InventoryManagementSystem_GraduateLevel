package runner.input;

import java.io.PrintStream;
import java.util.Scanner;

import domain.fastener.Fastener;
import domain.inventory_item.InventoryItem;

/**
 * The class is used to get the information about inventoryItem from Scanner, and handle the 
 * input and store the information to database. The class stores a list of handlers that used to
 * handle Fastener item.
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public abstract class FastenerSequence extends InventoryItemSequence {
	
	/**
	 * Constructor.
	 * @param output PrintStream
	 * @param input Scanner
	 * @param item Fastener Item
	 */
	public FastenerSequence(PrintStream output, Scanner input, InventoryItem item) {
		super(output, input, item);
		this.handlers.add(new LengthHandler());
		this.setValue("length", ((Fastener)this.item).getLength());
	}
	
	/**
	 * Constructor
	 * @param output PrintStream 
	 * @param input Scanner
	 */
	public FastenerSequence(PrintStream output, Scanner input) {
		super(output, input);
		this.handlers.add(new LengthHandler());
	}

}

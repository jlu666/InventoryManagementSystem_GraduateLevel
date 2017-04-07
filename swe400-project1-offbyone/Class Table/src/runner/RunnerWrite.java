package runner;

import java.sql.SQLException;
import java.util.Scanner;

import domain.inventory_item.InventoryItem;
import domain.inventory_item.InventoryItemSearcher;
import domain.nail.Nail;
import domain.power_tool.PowerTool;
import domain.strip_nail.StripNail;
import domain.tool.Tool;
import runner.input.InventoryItemSequence;
import runner.input.NailSequence;
import runner.input.PowerToolSequence;
import runner.input.StripNailSequence;
import runner.input.ToolSequence;
import runner.input.TypeCodeHandler;

/**
 * Inserts or Updates the information of inventory item into database.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class RunnerWrite {
	
	/**
	 * The main method.
	 * @param args args
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.print("A non-existant upc will insert a new item, and an existing upc will update the identified item.  Enter upc: ");
		Scanner scanner = new Scanner(System.in);
		
		if(scanner.hasNextLine()) {
			String upc = scanner.nextLine();
			InventoryItem item = InventoryItemSearcher.findByUpc(upc);
			
			if(item == null) {
				System.out.println("No existing item found.  Inserting new item.");
				doInsert(scanner, upc);
			} else {
				System.out.println("Existing item found.  Updating item.");
				doUpdate(scanner, item);
			}
		} else {
			System.out.println("No input detected.");
		}
		
		scanner.close();
	}
	
	/**
	 * Inserts the Inventory item's information
	 * 	 * @param scanner Scanner that input will be read from.
	 * @param upc the upc that will be given to the item
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void doInsert(Scanner scanner, String upc) throws ClassNotFoundException, SQLException {
		InventoryItemSequence inputSequence = null;
		TypeCodeHandler typeCode = new TypeCodeHandler();
		typeCode.handle(System.out, scanner);
		
		switch(typeCode.getValue()) {
			case "t":
				inputSequence = new ToolSequence(System.out, scanner);
				break;
			case "pt":
				inputSequence = new PowerToolSequence(System.out, scanner);
				break;
			case "n":
				inputSequence = new NailSequence(System.out, scanner);
				break;
			case "sn":
				inputSequence = new StripNailSequence(System.out, scanner);
				break;
		}
		
		inputSequence.setValue("upc", upc);
		inputSequence.execute();
		inputSequence.insert();
	}
	
	/**
	 * Updates the Inventory item's information.
	 * @param scanner Scanner that input will be read from.
	 * @param item Item will be updated.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void doUpdate(Scanner scanner, InventoryItem item) throws ClassNotFoundException, SQLException {
		InventoryItemSequence inputSequence = null;
		
		if(item.getClass().equals(Tool.class)) {
			inputSequence = new ToolSequence(System.out, scanner, item);
		} else if(item.getClass().equals(PowerTool.class)) {
			inputSequence = new PowerToolSequence(System.out, scanner, item);
		} else if(item.getClass().equals(Nail.class)) {
			inputSequence = new NailSequence(System.out, scanner, item);
		} else if(item.getClass().equals(StripNail.class)) {
			inputSequence = new StripNailSequence(System.out, scanner, item);
		}
		
		inputSequence.execute();
		inputSequence.update();
	}
	
}
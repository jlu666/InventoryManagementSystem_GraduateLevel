package runner;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.inventory_item.InventoryItem;
import domain.inventory_item.InventoryItemSearcher;

/**
 * 
 *@author Alan Malloy & Jixiang Lu
 *
 */
public class RunnerUpc {
	
	/**
	 * Tests use upc to find product information.
	 * 
	 * @param args nothing
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		
		while(!exit){
			System.out.print("Please provide a upc or \"exit\" to exit system: ");
			
			if(scanner.hasNext()){
				String input = scanner.nextLine();
				
				if(input.compareTo("exit") == 0){
					exit = true;
				} else {
					InventoryItem it = findByUpc(input);
					
					if(it != null) {
						System.out.println(it);
						
						List<InventoryItem> associatedItems = associatedItems(it);
						
						if(associatedItems.size() > 0) {
							System.out.println("\nAssociated Items:");
							
							for(InventoryItem item: associatedItems) {
								System.out.println(item);
							}
						}
					} else {
						System.out.println("Unable to locate a matching inventory item.\n");
					}
					
					System.out.print("\n");
				}
			}
		}
		
		System.out.println("Thank you for using this system!\n");
		scanner.close();
	}
	
	/**
	 * @param upc The upc to filter inventory items by.
	 * @return InventoryItem An inventory item that is identified by the provided upc.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static InventoryItem findByUpc(String upc) throws ClassNotFoundException, SQLException {
		return InventoryItemSearcher.findByUpc(upc);
	}
	
	/**
	 * Gets the InventoryItem's associated List.
	 * @param item InventoryItem
	 * @return the InventoryItem's associated List.
	 */
	public static List<InventoryItem> associatedItems(InventoryItem item) {
		return item.associatedItems();
	}

}


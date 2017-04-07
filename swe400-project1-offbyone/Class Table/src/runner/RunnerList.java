package runner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import domain.inventory_item.InventoryItem;
import domain.inventory_item.InventoryItemSearcher;

/**
 * Prints All the InventoryItem in the database.
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class RunnerList {

	/**
	 * Prints All the InventoryItem in the database.
	 * @param args nothings.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws FileNotFoundException FileNotFoundException
	 * @throws IOException IOException
	 * @throws SQLException SQLException
	 */
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
        System.out.println("Listing inventory items...");

        for(InventoryItem item: InventoryItemSearcher.all()) {
            System.out.println(item.toString());
        }
    }

}

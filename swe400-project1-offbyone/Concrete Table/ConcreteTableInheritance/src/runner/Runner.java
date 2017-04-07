package runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import datasource.DatabaseGateway;
import datasource.PowerToolGateway;
import datasource.PowerToolStripNailRelationGateway;
import datasource.StripNailGateway;
import domain.PowerToolsToStripNailsMapper;
import enums.EnumInsert;
import model.InventoryItem;
import model.Nail;
import model.PowerTool;
import model.PowerToolStripNailRelation;
import model.StripNail;
import model.Tool;
/**
 * This is the main Runner for the assignment
 * @author Ronald Sease & Darnell Martin
 *
 */
public class Runner
{

	/**
	 * reader - reads input
	 * TOOL_PROMPT - used to ask user what values to enter for a Tool
	 * POWER_TOOL_PROMPT - used to ask user what values to enter for a Power Tool

	 */
	static BufferedReader reader;
	static final String[] TOOL_PROMPT = {"UPC", "Manufacturer ID", "Price", "Description (no special characters)"};
	static final String[] POWER_TOOL_PROMPT = {"UPC", "Manufacturer ID", "Price", "Description (no special characters)", "Battery Powered"};
	static final String[] NAIL_PROMPT = {"UPC", "Manufacturer ID", "Price", "Length", "Number In Box"};
	static final String[] STRIP_NAIL_PROMPT = {"UPC", "Manufacturer ID", "Price", "Length", "Number In Strip"};
	/**
	 * Drops all pre-existing tables, creates new table, inserts
	 * the enums into their respective tables, then pulls them out back
	 * out of the database. 
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SQLException, IOException
	{
		DatabaseGateway.createDatabase();
		
		System.out.println("Inserting power tools");
		EnumInsert.insertPowerToolsEnums();
		System.out.println("Inserting strip nails");
		EnumInsert.insertStripNailsEnums();
		System.out.println("Inserting tools");
		EnumInsert.insertToolsEnums();
		System.out.println("Inserting nails");
		EnumInsert.insertNailsEnums();
		EnumInsert.insertRelations();
		reader = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		while(run)
		{
			System.out.println("Select 1 to query an inventory item by UPC");
			System.out.println("Select 2 to insert a new inventory item");
			System.out.println("Select 3 to update an existing inventory item");
			System.out.println("Select 4 to add/update/delete relations");
			int actionToTake = Integer.parseInt(reader.readLine());
			if(actionToTake == 1)
			{
				queryItem();
			}
			else if(actionToTake == 2)
			{
				insertItem();
			}
			else if(actionToTake == 3)
			{
				updateItem();
			}
			else if(actionToTake == 4)
			{
				updateRelations();
			}
			else
			{
				System.out.println("Invalid choice");
			}		
			System.out.print("Want to run the program again? Enter Y to run: ");
			String shouldRun = reader.readLine();
			if(!(shouldRun.contains("y")) && !(shouldRun.contains("Y")))
			{
				run = false;
				reader.close();
			}
		}	
	}		
	
	/**
	 * Updates a relation in the PowerToolsTOStripNails table
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private static void updateRelations() throws SQLException, NumberFormatException, IOException
	{
		System.out.println("Select 1 to add a power tool/strip nail relation");
		System.out.println("Select 2 to update an existing power too/strip nail relation");
		System.out.println("Select 3 to delete a strip nail relation");
		int actionToTake = Integer.parseInt(reader.readLine());
		if(actionToTake == 1)
		{
			addRelation();
		}
		else if(actionToTake == 2)
		{
			updateRelation();
		}
		else if(actionToTake == 3)
		{
			deleteRelation();
		}
	}

	/**
	 * Adds a relation in the PowerToolsTOStripNails table
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void addRelation() throws SQLException, IOException
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> powerToolUPCs = PowerToolGateway.getAllUPCs();
		for(int i = 0; i < powerToolUPCs.size(); i++)
		{
			System.out.println(findItemByUPC(powerToolUPCs.get(i).toString())); 
		}
		System.out.println("Enter UPC of power tool you want to associate with a strip nail");
		String powerToolUPC = localReader.readLine();
		System.out.println();
		ArrayList<String> stripNailUPCs = StripNailGateway.getAllUPCs();
		for(int i = 0; i < stripNailUPCs.size(); i++)
		{
			System.out.println(findItemByUPC(stripNailUPCs.get(i).toString())); 
		}
		System.out.println("Enter UPC of strip nail you want to associate with a power tool");
		String stripNailUPC = localReader.readLine();
		PowerTool powerToolToRelate = (PowerTool)findItemByUPC(powerToolUPC);
		StripNail stripNailToRelate = (StripNail)findItemByUPC(stripNailUPC);
		new PowerToolsToStripNailsMapper(powerToolToRelate.getID(),stripNailToRelate.getID());
	}

	/**
	 * Updates a relation in the PowerToolsTOStripNails table
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void updateRelation() throws SQLException, IOException
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<PowerToolStripNailRelation> relations = PowerToolStripNailRelationGateway.getAllRelations();
		for(int i = 0; i < relations.size(); i++)
		{
			System.out.println("Entry " + relations.get(i).getID() + ": PowerTool UPC: " + relations.get(i).getPowerTool().getUPC() + ", " +
							"Strip Nail UPC: " + relations.get(i).getStripNail().getUPC());
		}
		System.out.println("Enter which entry number you want to change");
		int id = Integer.parseInt(localReader.readLine());
		String powerToolUPC = relations.get(id-1).getPowerTool().getUPC();
		String stripNailUPC = relations.get(id-1).getStripNail().getUPC();
		System.out.println("Do you want to change the power tool? Enter Y for yes or any other letter for no");
		if(localReader.readLine().equalsIgnoreCase("Y"))
		{
			System.out.println("Enter new power tool upc for relation");
			powerToolUPC = localReader.readLine();
		}
		System.out.println("Do you want to change the strip nail? Enter Y for yes or any other letter for no");
		if(localReader.readLine().equalsIgnoreCase("Y"))
		{
			System.out.println("Enter new strip nail upc for relation");
			stripNailUPC = localReader.readLine();
		}
		int powerToolID = new PowerTool(powerToolUPC).getID();
		int stripNailID = new StripNail(stripNailUPC).getID();
		PowerToolStripNailRelationGateway.updateRelation(id, powerToolID, stripNailID);
	}

	/**
	 * Deletes a relation in the PowerToolsTOStripNails table
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void deleteRelation() throws SQLException, IOException 
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<PowerToolStripNailRelation> relations = PowerToolStripNailRelationGateway.getAllRelations();
		for(int i = 0; i < relations.size(); i++)
		{
			System.out.println("Entry " + relations.get(i).getID() + ": PowerTool UPC: " + relations.get(i).getPowerTool().getUPC() + ", " +
							"Strip Nail UPC: " + relations.get(i).getStripNail().getUPC());
		}
		System.out.println("Enter which entry number you want to delete");
		int entryToDelete = Integer.parseInt(localReader.readLine());
		PowerToolStripNailRelationGateway.deleteRelation(entryToDelete);
	}

	/**
	 * When choosing to update an item, this is called to decide
	 * which type of tool is being updated
	 * @throws SQLException
	 * @throws IOException
	 */
	private static void updateItem() throws SQLException, IOException 
	{
		System.out.println("Enter UPC of the item you wish to update");
		String upc = reader.readLine();
		InventoryItem itemToUpdate = findItemByUPC(upc);
		if(itemToUpdate!= null)
		{
			String className = itemToUpdate.getClass().getSimpleName();
			if(className.equals("Tool"))
			{
				updateTool(itemToUpdate);
			}
			else if(className.equals("PowerTool"))
			{
				updatePowerTool(itemToUpdate);
			}
			else if(className.equals("Nail"))
			{
				updateNail(itemToUpdate);
			}
			else if(className.equals("StripNail"))
			{
				updateStripNail(itemToUpdate);
			}
		}
		
	}
	
	/**
	 * Updates a tool
	 * @param itemToUpdate
	 * @throws IOException
	 * @throws SQLException 
	 */
	public static void updateTool(InventoryItem itemToUpdate) throws IOException, SQLException 
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
		Tool toolToUpdate = (Tool) itemToUpdate;
		for(int i = 0; i < TOOL_PROMPT.length; i++)
		{
			System.out.println("Do you want to update " + TOOL_PROMPT[i] + "? Enter Y for yes or any other letter for no");
			String update = localReader.readLine();
			if(update.contains("y") || update.contains("Y"))
			{
				if(i == 0)
				{
					System.out.println("Enter new UPC");
					toolToUpdate.setUPC(localReader.readLine());
				}
				else if(i == 1)
				{
					System.out.println("Enter ManufacturerID");
					toolToUpdate.setManufacturerID(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 2)
				{
					System.out.println("Enter Price");
					toolToUpdate.setPrice(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 3)
				{
					System.out.println("Enter Description");
					toolToUpdate.setDescription(localReader.readLine());
				}
			}
		}
	}
	
	/**
	 * Updates a power tool
	 * @param itemToUpdate
	 * @throws IOException
	 * @throws SQLException 
	 */
	public static void updatePowerTool(InventoryItem itemToUpdate) throws IOException, SQLException 
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
		PowerTool powerToolToUpdate = (PowerTool) itemToUpdate;
		for(int i = 0; i < POWER_TOOL_PROMPT.length; i++)
		{
			System.out.println("Do you want to update " + POWER_TOOL_PROMPT[i] + "? Enter Y for yes or any other letter for no");
			String update = localReader.readLine();
			if(update.contains("y") || update.contains("Y"))
			{
				if(i == 0)
				{
					System.out.println("Enter new UPC");
					powerToolToUpdate.setUPC(localReader.readLine());
					
				}
				else if(i == 1)
				{
					System.out.println("Enter ManufacturerID");
					powerToolToUpdate.setManufacturerID(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 2)
				{
					System.out.println("Enter Price");
					powerToolToUpdate.setPrice(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 3)
				{
					System.out.println("Enter Description");
					String s = localReader.readLine();
					System.out.println("s is " + s);
					powerToolToUpdate.setDescription(s);
					
				}
				else if(i == 4)
				{
					System.out.println("Enter battery Powered (type 1 for true or 0 for false)");
					int bool = Integer.parseInt(localReader.readLine());
					if(bool == 1)
					{
						powerToolToUpdate.setBatteryPowered(true);
					}
					if(bool == 0)
					{
						powerToolToUpdate.setBatteryPowered(false);
					}
				
				}
			}
		}
	}
	
	/**
	 * Updates a nail
	 * @param itemToUpdate
	 * @throws IOException
	 */
	public static void updateNail(InventoryItem itemToUpdate) throws IOException 
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
		Nail nailToUpdate = (Nail) itemToUpdate;
		for(int i = 0; i < NAIL_PROMPT.length; i++)
		{
			System.out.println("Do you want to update " + NAIL_PROMPT[i] + "? Enter Y for yes or any other letter for no");
			String update = localReader.readLine();
			if(update.equals("y") || update.equals("Y"))
			{
				if(i == 0)
				{
					System.out.println("Enter new UPC");
					nailToUpdate.setUPC(localReader.readLine());
				}
				else if(i == 1)
				{
					System.out.println("Enter ManufacturerID");
					nailToUpdate.setManufacturerID(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 2)
				{
					System.out.println("Enter Price");
					nailToUpdate.setPrice(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 3)
				{
					System.out.println("Enter Length");
					nailToUpdate.setLength(Double.parseDouble(localReader.readLine()));
				}
				else if(i == 4)
				{
					System.out.println("Enter Number In Box");
					nailToUpdate.setNumberInBox(Integer.parseInt(localReader.readLine()));
				}
			}
		}
	}
	
	/**
	 * Updates a strip nail
	 * @param itemToUpdate
	 * @throws IOException
	 */
	public static void updateStripNail(InventoryItem itemToUpdate) throws IOException 
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
		StripNail stripNailToUpdate = (StripNail) itemToUpdate;
		for(int i = 0; i < NAIL_PROMPT.length; i++)
		{
			System.out.println("Do you want to update " + NAIL_PROMPT[i] + "? Enter Y for yes or any other letter for no");
			String update = localReader.readLine();
			if(update.equals("y") || update.equals("Y"))
			{
				if(i == 0)
				{
					System.out.println("Enter new UPC");
					stripNailToUpdate.setUPC(localReader.readLine());
				}
				else if(i == 1)
				{
					System.out.println("Enter ManufacturerID");
					stripNailToUpdate.setManufacturerID(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 2)
				{
					System.out.println("Enter Price");
					stripNailToUpdate.setPrice(Integer.parseInt(localReader.readLine()));
				}
				else if(i == 3)
				{
					System.out.println("Enter Length");
					stripNailToUpdate.setLength(Double.parseDouble(localReader.readLine()));
				}
				else if(i == 4)
				{
					System.out.println("Enter Number In Strip");
					stripNailToUpdate.setNumberInStrip(Integer.parseInt(localReader.readLine()));
				}
			}
		}
	}
	
	/**
	 * Calls the insert method based on what type of inventory item the user decides to create
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private static void insertItem() throws IOException, NumberFormatException, SQLException 
	{
		System.out.println("What type of Inventory Item are you creating?");
		System.out.println("Enter 1 for Tool, 2 for Power Tool, 3 for Nail, 4 for Strip Nail");
		int typeOfTool = Integer.parseInt(reader.readLine());
		
		if(typeOfTool == 1)
		{
			insertTool();
		}
		else if(typeOfTool == 2)
		{
			insertPowerTool();
		}
		else if(typeOfTool == 3)
		{
			insertNail();
		}
		else if(typeOfTool == 4)
		{
			insertStripNail();
		}
		
	}
	
	/**
	 * Inserts a new strip nail
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertStripNail() throws NumberFormatException, IOException, SQLException 
	{
		BufferedReader stripNailReader = new BufferedReader(new InputStreamReader(System.in));
		String upc = "";
		int manufacturerID = 0;
		int price = 0;
		double length = 0;
		int numberInStrip = 0;
		for(int i = 0; i < STRIP_NAIL_PROMPT.length; i++)
		{
			System.out.println("Enter value for " + NAIL_PROMPT[i] + ".");
			if(i == 0)
			{
				upc = stripNailReader.readLine();
			}
			if(i == 1)
			{
				manufacturerID = Integer.parseInt(stripNailReader.readLine());
			}
			if(i == 2)
			{
				price = Integer.parseInt(stripNailReader.readLine());
			}
			if(i == 3)
			{
				length = Double.parseDouble(stripNailReader.readLine());
			}
			if(i == 4)
			{
				numberInStrip = Integer.parseInt(stripNailReader.readLine());
			}
		}
		StripNail stripNail = new StripNail(upc, manufacturerID, price, length, numberInStrip);		
		insertPowerToolsForNewStripNail(stripNail);
	}

	/**
	 * Inserts relation(s) of power tools to a new strip nail
	 * @param stripNail
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void insertPowerToolsForNewStripNail(StripNail stripNail) throws SQLException, IOException 
	{
		BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Is this related to any strip nails? Enter Y for yes or any other letter for no");
		String createRelation = localReader.readLine();
		if(createRelation == null){return;}
		if(createRelation.equalsIgnoreCase("Y"))
		{
			boolean insertRelations = true;
			ArrayList<String> upcs = PowerToolGateway.getAllUPCs();
			for(int i = 0; i < upcs.size(); i++)
			{
				System.out.println(findItemByUPC(upcs.get(i).toString())); 
			}
			while(insertRelations)
			{
				System.out.println("Enter the UPC of a power tool that is to be associated with the strip nail just added");
				String upcToBeRelated = localReader.readLine();
				PowerTool powerTool = new PowerTool(upcToBeRelated);
				if(stripNail != null)
				{
					new PowerToolsToStripNailsMapper(powerTool.getID(), stripNail.getID());
				}
				
				System.out.println("Want to associate this power tool with another strip nail? Enter Y for yes or any other letter for no");
				if(!localReader.readLine().equalsIgnoreCase("Y"))
				{
					insertRelations = false;
				}
			}
		}
	}

	/**
	 * Inserts a nail into the database
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertNail() throws NumberFormatException, IOException, SQLException 
	{
		BufferedReader nailreader = new BufferedReader(new InputStreamReader(System.in));
		String upc = "";
		int manufacturerID = 0;
		int price = 0;
		double length = 0;
		int numberInBox = 0;
		for(int i = 0; i < NAIL_PROMPT.length; i++)
		{
			System.out.println("Enter value for " + NAIL_PROMPT[i] + ".");
			if(i == 0)
			{
				upc = nailreader.readLine();
			}
			if(i == 1)
			{
				manufacturerID = Integer.parseInt(nailreader.readLine());
			}
			if(i == 2)
			{
				price = Integer.parseInt(nailreader.readLine());
			}
			if(i == 3)
			{
				length = Double.parseDouble(nailreader.readLine());
			}
			if(i == 4)
			{
				numberInBox = Integer.parseInt(nailreader.readLine());
			}
		}
		new Nail(upc, manufacturerID, price, length, numberInBox);	
	}

	/**
	 * Inserts a new power tool into the database
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertPowerTool() throws IOException, SQLException
	{
		BufferedReader powertoolreader = new BufferedReader(new InputStreamReader(System.in));
		String upc = "";
		int manufacturerID = 0;
		int price = 0;
		String description = "";
		boolean batteryPowered = false;
		for(int i = 0; i < POWER_TOOL_PROMPT.length; i++)
		{
			System.out.println("Enter value for " + POWER_TOOL_PROMPT[i] + ".");
			if(i == 0)
			{
				upc = powertoolreader.readLine();
			}
			if(i == 1)
			{
				manufacturerID = Integer.parseInt(powertoolreader.readLine());
			}
			if(i == 2)
			{
				price = Integer.parseInt(powertoolreader.readLine());
			}
			if(i == 3)
			{
				description = powertoolreader.readLine();
			}
			if(i == 4)
			{
				System.out.println("Enter true or false");
				batteryPowered = Boolean.parseBoolean(powertoolreader.readLine());
			}
		}
		PowerTool powerTool = new PowerTool(upc, manufacturerID, price, description, batteryPowered);
		insertStripNailsForNewPowerTool(powerTool);
	}

	/**
	 *Inserts relation(s) of strip nails to a new power tool
	 * @param powerTool
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void insertStripNailsForNewPowerTool(PowerTool powerTool) throws IOException, SQLException 
	{
		BufferedReader localreader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Is this related to any strip nails? Enter Y for yes or any other letter for no");
		String createRelation = localreader.readLine();
		if(createRelation == null)
		{
			return;
		}
		if(createRelation.equalsIgnoreCase("Y"))
		{
			boolean insertRelations = true;
			ArrayList<String> upcs = StripNailGateway.getAllUPCs();
			for(int i = 0; i < upcs.size(); i++)
			{
				System.out.println(findItemByUPC(upcs.get(i).toString())); 
			}
			while(insertRelations)
			{
				System.out.println("Enter the UPC of a strip nail that is to be associated with the power tool just added");
				String upcToBeRelated = localreader.readLine();
				StripNail stripNail = new StripNail(upcToBeRelated);
				if(stripNail != null)
				{
					new PowerToolsToStripNailsMapper(powerTool.getID(), stripNail.getID());
				}
				
				System.out.println("Want to associate this power tool with another strip nail? Enter Y for yes or any other letter for no");
				if(!localreader.readLine().equalsIgnoreCase("Y"))
				{
					insertRelations = false;
				}
			}
		}
	}

	/**
	 * Inserts a new tool into the database
	 * @throws IOException
	 * @throws SQLException 
	 */
	public static void insertTool() throws IOException, SQLException 
	{
		BufferedReader toolreader = new BufferedReader(new InputStreamReader(System.in));
		String upc = "";
		int manufacturerID = 0;
		int price = 0;
		String description = "";
		for(int i = 0; i < TOOL_PROMPT.length; i++)
		{
			System.out.println("Enter value for " + TOOL_PROMPT[i] + ".");
			if(i == 0)
			{
				upc = toolreader.readLine();
			}
			if(i == 1)
			{
				manufacturerID = Integer.parseInt(toolreader.readLine());
			}
			if(i == 2)
			{
				price = Integer.parseInt(toolreader.readLine());
			}
			if(i == 3)
			{
				description = toolreader.readLine();
			}
		}
		new Tool(upc, manufacturerID, price, description);
	}

	/**
	 * Finds an item in the database using the UPC and prints out the information for that item
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void queryItem() throws SQLException, IOException 
	{
		BufferedReader localreader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a UPC ");
		String upc = localreader.readLine();
		InventoryItem item = findItemByUPC(upc);
		if(item != null)
		{
			if(item instanceof StripNail)
			{
				System.out.println(item.toString());
				System.out.println();
				System.out.println("Power tools associated with this strip nail: ");
				ArrayList<PowerTool> powerTools = ((StripNail) item).getPowerToolsForStripNail();
				for(int x=0; x<powerTools.size();x++)
				{
					System.out.println(powerTools.get(x).toString());
				}
			}
			else if(item instanceof PowerTool)
			{
				System.out.println(item.toString());
				System.out.println();
				System.out.println("Strip Nails associated with this power tool: ");
				ArrayList<StripNail> stripNails = ((PowerTool) item).getStripNailsForPowerTool();
				for(int x=0; x<stripNails.size();x++)
				{
					System.out.println(stripNails.get(x).toString());
				}
			}
			else
			{
				System.out.println(item.toString());
			}
		}
		else
		{
			System.out.println("No product with this UPC exists");
		}
	}
	
	/**
	 * Finds an object with the UPC that is passed in.
	 * @param upc - upc of the item you are looking for
	 * @return InventoryItem - the inventory item, if found 
	 * @throws SQLException 
	 */
	public static InventoryItem findItemByUPC(String upc) throws SQLException 
	{
		Nail nail = new Nail(upc);
		StripNail stripNail = new StripNail(upc);
		Tool tool = new Tool(upc);
		PowerTool powerTool = new PowerTool(upc);
	
		if(nail.id != 0)
		{
			return nail;
		}
		else if(stripNail.id != 0)
		{
			return stripNail ;
		}
		else if(tool.id != 0)
		{
			return tool;
		}
		else if(powerTool.id != 0)
		{
			return powerTool;
		}
		else
		{
			return null;
		}
	}
}

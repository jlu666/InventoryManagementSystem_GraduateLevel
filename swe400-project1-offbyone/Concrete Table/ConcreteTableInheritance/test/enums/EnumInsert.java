package enums;

import java.sql.SQLException;

import domain.PowerToolsToStripNailsMapper;
import model.Nail;
import model.PowerTool;
import model.StripNail;
import model.Tool;

/**
 * Holds methods for the enums to be inserted into the database
 * @author Ronald Sease & Darnell Martin
 *
 */
public class EnumInsert 
{
	/**
	 * Inserts tools
	 * @throws SQLException
	 */
	public static void insertToolsEnums() throws SQLException
	{
		for(int i = 0; i < Tools.values().length; i++)
		{
			String upc = Tools.values()[i].getUpc();
			int manufacturerID = Tools.values()[i].getManufacturerID();
			int price = Tools.values()[i].getPrice();
			String description = Tools.values()[i].getDescription();
			new Tool(upc, manufacturerID, price, description);
		}
		
	}
	
	/**
	 * Inserts powertools
	 * @throws SQLException
	 */
	public static void insertPowerToolsEnums() throws SQLException
	{
		for(int i = 0; i < PowerTools.values().length; i++)
		{
			String upc = PowerTools.values()[i].getUpc();
			int manufacturerID = PowerTools.values()[i].getManufacturerID();
			int price = PowerTools.values()[i].getPrice();
			String description = PowerTools.values()[i].getDescription();
			boolean batteryPowered = PowerTools.values()[i].isBatteryPowered();
			new PowerTool(upc, manufacturerID, price, description, batteryPowered);
		}
		
	}
	
	/**
	 * Inserts nails
	 * @throws SQLException
	 */
	public static void insertNailsEnums() throws SQLException
	{
		for(int i = 0; i < Nails.values().length; i++)
		{
			String upc = Nails.values()[i].getUpc();
			int manufacturerID = Nails.values()[i].getManufacturerID();
			int price = Nails.values()[i].getPrice();
			double length = Nails.values()[i].getLength();
			int numberInBox = Nails.values()[i].getNumberInBox();
			new Nail(upc, manufacturerID, price, length,numberInBox);
		}
		
	}
	
	/**
	 * Inserts stripnails
	 * @throws SQLException
	 */
	public static void insertStripNailsEnums() throws SQLException
	{
		for(int i = 0; i < StripNails.values().length; i++)
		{
			String upc = StripNails.values()[i].getUpc();
			int manufacturerID = StripNails.values()[i].getManufacturerID();
			int price = StripNails.values()[i].getPrice();
			double length = StripNails.values()[i].getLength();
			int numberInStrip = StripNails.values()[i].getNumberInStrip();
			new StripNail(upc, manufacturerID, price, length, numberInStrip);
		}
		
	}
	
	/**
	 * Inserts relations of PowerTools and StripNails
	 * @throws SQLException 
	 */
	public static void insertRelations() throws SQLException
	{
		new PowerToolsToStripNailsMapper(1,7);
		new PowerToolsToStripNailsMapper(2,7);
		new PowerToolsToStripNailsMapper(1,8);
		new PowerToolsToStripNailsMapper(2,8);
		new PowerToolsToStripNailsMapper(5,10);
		new PowerToolsToStripNailsMapper(6,10);
		new PowerToolsToStripNailsMapper(5,11);
		new PowerToolsToStripNailsMapper(6,11);
	}
}

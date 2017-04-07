package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Handles saving a PowerTool to the database
 * and retrieving a PowerTool from the database
 * @author Ronald Sease & Darnell Martin
 */
public class PowerToolGateway extends AbstractConcreteGateway
{
	int id;
	String UPC;
	int ManufacturerID;
	int Price; 
	String Description;
	boolean batteryPowered;
	Connection conn;
	
	/**
	 * Creation constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param Description
	 * @param batteryPowered
	 * @throws SQLException 
	 */
	public PowerToolGateway(String UPC, int ManufacturerID, int price, String Description, boolean batteryPowered) throws SQLException
	{
		this.UPC = UPC;
		this.ManufacturerID = ManufacturerID;
		this.Price = price;
		this.Description = Description;
		this.batteryPowered = batteryPowered;
		this.conn = DatabaseGateway.getConnection();
		this.id = DatabaseGateway.getNewID();
		this.insert();
	}
	/**
	 * Finder Constructor using UPC
	 * @param UPC
	 * @throws SQLException 
	 */
	public PowerToolGateway(String UPC) throws SQLException
	{
		this.UPC = UPC;
		this.conn = DatabaseGateway.getConnection();
		this.getRowByUPC();
	}
	
	/**
	 * Finder Constructor using id
	 * @param id
	 * @throws SQLException 
	 */
	public PowerToolGateway(int id) throws SQLException
	{
		this.id = id;
		this.conn = DatabaseGateway.getConnection();
		this.getRowById();
	}
	
	/**
	 * Returns the id column of the row
	 * @return id
	 */
	public int getID() 
	{
		return this.id;
	}

	/**
	 * Returns the UPC column of the row
	 * @return UPC
	 */
	public String getUPC() 
	{	
		return this.UPC;
	}

	/**
	 * Returns the ManufacturerId column of the row
	 * @return ManufacturerId
	 */
	public int getManufacturerID() 
	{
		return this.ManufacturerID;
	}

	/**
	 * Returns the Price column of the row
	 * @return Price
	 */
	public int getPrice() 
	{
		return this.Price;
	}

	/**
	 * Returns the Description column of the row
	 * @return Description
	 */
	public String getDescription() 
	{
		return this.Description;
	}

	/**
	 * Returns the batteryPowered column of the row
	 * @return batteryPowered
	 */
	public boolean getBatteryPowered()
	{
		return this.batteryPowered;
	}
	
	/**
	 * Sets the upc and updates the database
	 * @param upc 
	 * @throws SQLException 
	 */
	public void setUPC(String upc) throws SQLException 
	{
		this.UPC = upc;
		update();
	}
	
	/**
	 * Sets the manufactuerID and updates the database
	 * @param manufacturerID 
	 * @throws SQLException 
	 */
	public void setManufacturerID(int manufacturerID) throws SQLException
	{
		this.ManufacturerID = manufacturerID;
		update();
	}
	/**
	 * Sets the price and updates the database
	 * @param price
	 * @throws SQLException 
	 */
	public void setPrice(int price) throws SQLException
	{
		this.Price = price;
		update();
	}
	
	/**
	 * Sets the description and updates the database
	 * @param description
	 * @throws SQLException 
	 */
	public void setDescription(String description) throws SQLException
	{
		this.Description = description;
		update();
	}
	
	/**
	 * Sets the battery powered status and updates the database
	 * @param batteryPowered
	 * @throws SQLException 
	 */
	public void setBatteryPowered(boolean batteryPowered) throws SQLException 
	{
		this.batteryPowered = batteryPowered;
		update();
	}
	/**
	 * Returns a string that is properly formatted into a sql statement to save a power tool to the database.
	 * @return Insert string
	 */
	public String createInsertStatement()
	{
		String toDatabase = "INSERT INTO PowerTool " + "(" + 
				"id, UPC, ManufacturerID, Price, Description, batteryPowered" +
				") VALUES "+  getVariablesToString();
		return toDatabase;
	}
	
	/**
	 * Returns a properly formatted string of the values of the power tool.
	 * @return string of values
	 */
	public String getVariablesToString()
	{
		return "(" + this.getID() + "," + "'" + this.getUPC()  + "'" +"," + this.getManufacturerID() + "," + this.getPrice() + "," + "'" + 
											this.getDescription() + "'," + this.getBatteryPowered() + ")";
	}

	/**
	 * Finds a row in the database based on the UPC
	 * and then assigns all of the instance variables.
	 * @throws SQLException 
	 */
	public void getRowByUPC() throws SQLException
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		String getRow = "SELECT * FROM PowerTool WHERE UPC = '" + this.getUPC() + "';";
			st = this.conn.prepareStatement(getRow);
			rs = st.executeQuery();
		
			if(rs.next())
			{
				this.id = rs.getInt("id");
				this.ManufacturerID = rs.getInt("ManufacturerID");
				this.Price = rs.getInt("Price");
				this.Description = rs.getString("Description");
				this.batteryPowered = rs.getBoolean("batteryPowered");
			}

	}
	
	/**
	 * Finds a row in the database based on the ID
	 * and then assigns all of the instance variables.
	 * @throws SQLException 
	 */
	private void getRowById() throws SQLException
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		String getRow = "SELECT * FROM PowerTool WHERE id = " + this.getID() + ";";
			st = this.conn.prepareStatement(getRow);
			rs = st.executeQuery();
			this.id = 0;
			if(rs.next())
			{
				this.id = rs.getInt("id");
				this.ManufacturerID = rs.getInt("ManufacturerID");
				this.Price = rs.getInt("Price");
				this.Description = rs.getString("Description");
				this.batteryPowered = rs.getBoolean("batteryPowered");
				this.UPC = rs.getString("UPC");
			}
	}
	
	/**
	 * Updates a Power Tool in the database
	 * @throws SQLException 
	 */
	public void update() throws SQLException
	{
		String update = "UPDATE PowerTool SET UPC = " + this.getUPC() + 
				", ManufacturerID = " + this.getManufacturerID() + 
				", Price = " + this.getPrice() + 
				", Description = '" + this.getDescription() + 
				"', batteryPowered = " + this.getBatteryPowered() + 
				" WHERE id = " + this.getID();
		PreparedStatement st;
			st = this.conn.prepareStatement(update);
			st.execute();
	}
	
	/**
	 * Gets all UPCs of all power tools
	 * @return upcs - upcs of all power tools
	 */
	public static ArrayList<String> getAllUPCs()
	{
		ArrayList<String> upcs = new ArrayList<String>();
		String select = "SELECT UPC FROM PowerTool";
		PreparedStatement st;
		ResultSet rs = null;
		try{
			st = DatabaseGateway.getConnection().prepareStatement(select);
			rs = st.executeQuery();
		}
		catch (SQLException e){e.printStackTrace();}
		
		try {
			while(rs.next())
			{
				upcs.add(rs.getString("UPC"));
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		return upcs;
	}
}

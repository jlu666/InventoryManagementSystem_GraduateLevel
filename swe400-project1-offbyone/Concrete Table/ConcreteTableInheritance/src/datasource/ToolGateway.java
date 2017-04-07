package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles saving a Tool to the database
 * and retrieving a Tool from the database
 * @author Ronald Sease & Darnell Martin
 */
public class ToolGateway extends AbstractConcreteGateway
{
	int id;
	String UPC;
	int ManufacturerID;
	int Price; 
	String Description;
	Connection conn;
	
	/**
	 * Creation Constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param Description
	 * @throws SQLException 
	 */
	public ToolGateway(String UPC, int ManufacturerID, int price, String Description) throws SQLException
	{
		this.UPC = UPC;
		this.ManufacturerID = ManufacturerID;
		this.Price = price;
		this.Description = Description;
		this.conn = DatabaseGateway.getConnection();
		this.id = DatabaseGateway.getNewID();
		this.insert();
	}
	
	/**
	 * Finder Constructor using UPC
	 * @param UPC
	 * @throws SQLException 
	 */
	public ToolGateway(String UPC) throws SQLException
	{
		this.UPC = UPC;
		this.conn = DatabaseGateway.getConnection();
		this.getRowByUPC();
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
	 * Returns the manufacutuerID column of the row
	 * @return ManufacturerID
	 */
	public int getManufacturerID() 
	{
		return this.ManufacturerID;
	}

	/**
	 * Returns the price column of the row
	 * @return Price
	 */
	public int getPrice() 
	{
		return this.Price;
	}

	/**
	 * Returns the description column of the row
	 * @return Description
	 */
	public String getDescription() 
	{
		return this.Description;
	}
	
	/**
	 * Sets the upc and updates the database
	 * @param upc
	 */
	public void setUPC(String upc) 
	{
		this.UPC = upc;
		update();
	}

	/**
	 * Sets the ManufacturerID and updates the database
	 * @param manufacturerID
	 */
	public void setManufacturerID(int manufacturerID)
	{
		this.ManufacturerID = manufacturerID;
		update();
	}

	/**
	 * Sets the price and updates the database
	 * @param price
	 */
	public void setPrice(int price) 
	{
		this.Price = price;
		update();
	}

	/**
	 * Sets the description and updates the database
	 * @param description
	 */
	public void setDescription(String description) 
	{
		this.Description = description;
		update();
	}

	/**
	 * Returns a created insert statement
	 * @return string for inserting
	 */
	public String createInsertStatement()
	{
		String insertStatement = "INSERT INTO Tool " + "( id, UPC, ManufacturerID, Price, Description" 
									+ ") VALUES "+  getVariablesToString();
		return insertStatement;
	}
	
	/**
	 * Returns a formatted string of an Tools values.
	 * @return string of values
	 */
	public String getVariablesToString()
	{
		return "(" + this.getID() + "," + "'" + this.getUPC()  + "'" +"," + this.getManufacturerID() + "," + this.getPrice() + ",'" + this.getDescription() + "')";
	}
	
	/**
	 * Returns the table name of Tool
	 * @return table name
	 */
	public String getTableName()
	{
		return "Tool";
	}
	
	/**
	 * Finds a row in the database based on the UPC
	 * and then assigns all of the instance variables.
	 */
	public void getRowByUPC()
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		String getRow = "SELECT * FROM Tool WHERE UPC = '" + this.getUPC() + "';";
		try 
		{
			st = this.conn.prepareStatement(getRow);
			rs = st.executeQuery();
			if(rs.next())
			{
				this.id = rs.getInt("id");
				this.ManufacturerID = rs.getInt("ManufacturerID");
				this.Price = rs.getInt("Price");
				this.Description = rs.getString("Description");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a  Tool in the database
	 */
	public void update()
	{
		String update = "UPDATE Tool SET UPC = " + this.getUPC() + 
				", ManufacturerID = " + this.getManufacturerID() + 
				", Price = " + this.getPrice() + 
				", Description = '" + this.getDescription() + 
				"' WHERE id = " + this.getID();
		PreparedStatement st;
		try {
			st = this.conn.prepareStatement(update);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

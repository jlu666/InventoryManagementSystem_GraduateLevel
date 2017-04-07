package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Handles saving a StripNail to the database
 * and retrieving a StripNail from the database
 * @author Ronald Sease & Darnell Martin
 */
public class StripNailGateway extends AbstractConcreteGateway
{
	int id;
	String UPC;
	int ManufacturerID;
	int Price; 
	double length;
	int numberInStrip;
	Connection conn;
	
	/**
	 * Creator Constructor
	 * @param UPC
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInStrip
	 * @throws SQLException 
	 */
	public StripNailGateway(String UPC, int manufacturerID, int price, double length, int numberInStrip) throws SQLException
	{
		this.UPC = UPC;
		this.ManufacturerID = manufacturerID;
		this.Price = price;
		this.length = length;
		this.numberInStrip = numberInStrip;
		this.conn = DatabaseGateway.getConnection();
		this.id = DatabaseGateway.getNewID();
		this.insert();
	}
	/**
	 * Finder Constructor using UPC
	 * @param UPC
	 * @throws SQLException 
	 */
	public StripNailGateway(String UPC) throws SQLException
	{
		this.UPC = UPC;
		this.conn = DatabaseGateway.getConnection();
		this.getRowByUPC();
	}
	
	/**
	 * Finder Constructor using ID
	 * @param id
	 * @throws SQLException 
	 */
	public StripNailGateway(int id) throws SQLException
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
	 * Returns the length  column of the row
	 * @return length
	 */
	public double getLength()
	{
		return this.length;
	}

	/**
	 * Returns the number in strip
	 * @return numberInStrip
	 */
	public int getNumberInStrip() 
	{
		return this.numberInStrip;
	}
	
	/**
	 * Sets the UPC and updates in the database
	 * @param UPC - UPC
	 */
	public void setUPC(String UPC)
	{
		this.UPC = UPC;
		update();
	}
	
	/**
	 * Sets manufactuerID and updates in the database
	 * @param manufacturerID
	 */
	public void setManufacturerID(int manufacturerID) 
	{
		this.ManufacturerID = manufacturerID;
		update();
	}

	/**
	 * Sets the Price and updates in the database
	 * @param price
	 */
	public void setPrice(int price)
	{
		this.Price = price;
		update();
	}

	/**
	 * Sets the length and updates in the database
	 * @param length
	 */
	public void setLength(double length)
	{
		this.length = length;
		update();
	}
	
	/**
	 * Sets the number in strip and updates in the database
	 * @param numberInStrip
	 */
	public void setNumberInBox(int numberInStrip) 
	{
		this.numberInStrip = numberInStrip;
		update();
	}


	/**
	 * Creates the insert into statement for inserting a
	 * new row into the database
	 * @return - String of insert statement
	 */
	public String createInsertStatement()
	{
		String insertStatement = "INSERT INTO StripNail " + "(id, UPC, ManufacturerID, Price, Length, NumberInStrip" 
														+ ") VALUES "+  getVariablesToString();
		return insertStatement;
	}
	
	/**
	 * Returns a formatted string of a Nail's values.
	 * @return String of this nail's values
	 */
	public String getVariablesToString()
	{
		return "(" + this.getID() + "," + "'" + this.getUPC()  + "'" +"," + this.getManufacturerID() + "," + this.getPrice() + "," + this.getLength() + "," + this.getNumberInStrip() + ")";
	}
	
	/**
	 * Returns the table name of Nail
	 * @return "Nail" which is the table name
	 */
	public String getTableName()
	{
		return "StripNail";
	}
	
	

	/**
	 * Finds a row in the database based on the UPC
	 * and then assigns all of the instance variables.
	 */
	public void getRowByUPC()
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		String getRow = "SELECT * FROM StripNail WHERE UPC = '" + this.getUPC() + "';";
		try 
		{
			st = this.conn.prepareStatement(getRow);
			rs = st.executeQuery();
			if(rs.next())
			{
				this.id = rs.getInt("id");
				this.ManufacturerID = rs.getInt("ManufacturerID");
				this.Price = rs.getInt("Price");
				this.length = rs.getDouble("Length");
				this.numberInStrip = rs.getInt("NumberInStrip");
			}
		} 
		catch (SQLException e) 
		{
				e.printStackTrace();
		}
	}
	
	/**
	 * Finds a row in the database based on the ID
	 * and then assigns all of the instance variables.
	 */
	private void getRowById()
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		String getRow = "SELECT * FROM StripNail WHERE id = " + this.getID() + ";";
		try 
		{
			st = this.conn.prepareStatement(getRow);
			rs = st.executeQuery();
			this.id = 0;
			if(rs.next())
			{
				this.id = rs.getInt("id");
				this.UPC = rs.getString("UPC");
				this.ManufacturerID = rs.getInt("ManufacturerID");
				this.Price = rs.getInt("Price");
				this.length = rs.getDouble("Length");
				this.numberInStrip = rs.getInt("NumberInStrip");
			}
		} 
		catch (SQLException e) 
		{
				e.printStackTrace();
		}
	}
	
	/**
	 * Updates a nail in the database
	 */
	public void update()
	{
		String update = "UPDATE StripNail SET UPC = " + this.getUPC() + 
				", ManufacturerID = " + this.getManufacturerID() + 
				", Price = " + this.getPrice() + 
				", Length = " + this.getLength() + 
				", NumberInStrip = " + this.getNumberInStrip() + 
				" WHERE id = " + this.getID();
		PreparedStatement st;
		try {
			st = this.conn.prepareStatement(update);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all strip nails UPCs in the database
	 * @return upcs - array list of all upcs of strip nailss
	 */
	public static ArrayList<String> getAllUPCs()
	{
		ArrayList<String> upcs = new ArrayList<String>();
		String select = "SELECT UPC FROM StripNail";
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

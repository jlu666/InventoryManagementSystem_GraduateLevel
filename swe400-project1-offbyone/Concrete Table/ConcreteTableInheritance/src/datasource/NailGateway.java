package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles saving a Nail to the database
 * and retrieving a Nail from the database
 * @author Ronald Sease & Darnell Martin
 */
public class NailGateway extends AbstractConcreteGateway
{
	int id;
	String UPC;
	int ManufacturerID;
	int Price; 
	double length;
	int numberInBox;
	Connection conn;
	
	/**
	 * Creator constructor
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param length
	 * @param numberInBox
	 * @throws SQLException 
	 */
	public NailGateway(String UPC, int ManufacturerID, int price, double length, int numberInBox) throws SQLException
	{
		this.UPC = UPC;
		this.ManufacturerID = ManufacturerID;
		this.Price = price;
		this.length = length;
		this.numberInBox = numberInBox;
		this.conn = DatabaseGateway.getConnection();
		this.id = DatabaseGateway.getNewID();
		this.insert();
	}

	/**
	 * Finder Constructor using UPC
	 * @param UPC
	 * @throws SQLException 
	 */
	public NailGateway(String UPC) throws SQLException
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
	 * Returns the Length column of the row
	 * @return Length
	 */
	public double getLength()
	{
		return this.length;
	}

	/**
	 * Returns the NumberInBox column of the row
	 * @return NumberInBox
	 */
	public int getNumberInBox() 
	{
		return this.numberInBox;
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
	 * Sets the number in box and updates in the database
	 * @param numberInBox
	 */
	public void setNumberInBox(int numberInBox) 
	{
		this.numberInBox = numberInBox;
		update();
	}

	/**
	 * Returns a created insert statement
	 * @return string for inserting
	 */
	public String createInsertStatement()
	{
		String insertStatement = "INSERT INTO Nail " + "(id, UPC, ManufacturerID, Price, Length, NumberInBox" 
														+ ") VALUES "+  getVariablesToString();
		return insertStatement;
	}
	
	/**
	 * Returns a formatted string of an Nails values.
	 * @return string of values
	 */
	public String getVariablesToString()
	{
		return "(" + this.getID() + "," + "'" + this.getUPC()  + "'" +"," + this.getManufacturerID() + "," + this.getPrice() + "," + this.getLength() + "," + this.getNumberInBox() + ")";
	}
	
	

	/**
	 * Finds a row in the database based on the UPC
	 * and then assigns all of the instance variables.
	 */
	public void getRowByUPC() 
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		String getRow = "SELECT * FROM Nail WHERE UPC = '" + this.getUPC() + "';";
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
				this.numberInBox = rs.getInt("NumberInBox");
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
		String update = "UPDATE Nail SET UPC = " + this.getUPC() + 
				", ManufacturerID = " + this.getManufacturerID() + 
				", Price = " + this.getPrice() + ", Length = " + this.getLength() + 
				", NumberInBox = " + this.getNumberInBox() + 
				" WHERE id = " + this.getID();
		PreparedStatement st;
		try {
			st = this.conn.prepareStatement(update);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

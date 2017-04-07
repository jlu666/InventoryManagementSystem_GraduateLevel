package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PowerToolStripNailRelation;

/**
 * Handles saving, updating, and deleting from the PowerToolsTOStripNails table
 * @author Ronald Sease & Darnell Martin
 *
 */
public class PowerToolStripNailRelationGateway 
{
	StripNailGateway stripNailGateway;
	PowerToolGateway powerToolGateway;
	int id;
	/**
	 * This gateway is given the power tool id and the strip nail id in one relation.
	 * These ids are then passed in into their respective gateways so that the model
	 * can call upon this gateway to access the strip nail and power tool gateway to access
	 * the values of the models.
	 * @param id - id of relation in database
	 * @param powerToolID - id of the power tool
	 * @param stripNailID - id of the strip nail
	 * @throws SQLException 
	 */
	public PowerToolStripNailRelationGateway(int id, int powerToolID, int stripNailID) throws SQLException
	{
		this.id = id;
		this.powerToolGateway = new PowerToolGateway(powerToolID);
		this.stripNailGateway = new StripNailGateway(stripNailID);
	}

	/**
	 * Returns the strip nail gateway to access the strip nail data
	 * @return stripNailGateway
	 */
	public StripNailGateway getStripNailGateway() 
	{
		return this.stripNailGateway;
	}

	/**
	 * Returns the power tool gateway to access the power tool data 
	 * @return powerToolGateway
	 */
	public PowerToolGateway getPowerToolGateway() 
	{
		return this.powerToolGateway;
	}

	/**
	 * Returns the id
	 * @return id
	 */
	public int getID() 
	{
		return this.id;
	}
	
	/**
	 * Gets all the relations in the database
	 * @return relationModels - all the power tool and strip nails related
	 * @throws SQLException 
	 */
	public static ArrayList<PowerToolStripNailRelation> getAllRelations() throws SQLException
	{
		ArrayList<PowerToolStripNailRelation> relationModels = new ArrayList<PowerToolStripNailRelation>();
		String getAllRelations = "SELECT * FROM PowerToolsTOStripNails";
		PreparedStatement stmt =  DatabaseGateway.getConnection().prepareStatement(getAllRelations);
		ResultSet relations = stmt.executeQuery();
		
		while(relations.next())
		{
			relationModels.add(new PowerToolStripNailRelation(relations.getInt("id"), relations.getInt("PowerToolid"), relations.getInt("StripNailid")));
		}
		
		return relationModels;
	}

	/**
	 * Updates a relation
	 * @param id - id of key in table
	 * @param powerToolID - id of power tool  
	 * @param stripNailID - id of strip nail
	 * @throws SQLException
	 */
	public static void updateRelation(int id, int powerToolID, int stripNailID) throws SQLException 
	{
		String update = "UPDATE PowerToolsTOStripNails SET PowerToolid = " + powerToolID + " , StripNailid =" + stripNailID + " WHERE id = "+ id + ";" ;
		PreparedStatement stmt = DatabaseGateway.getConnection().prepareStatement(update);
		stmt.execute();
	}

	/**
	 * Deletes a relation
	 * @param entryToDelete - the id of the entry in the table to delete
	 * @throws SQLException
	 */
	public static void deleteRelation(int entryToDelete) throws SQLException 
	{
		String delete = "DELETE FROM PowerToolsTOStripNails WHERE id = " + entryToDelete;
		PreparedStatement stmt = DatabaseGateway.getConnection().prepareStatement(delete);
		stmt.execute();
	}
	
	/**
	 * Method to get PowerTool id from a relation based on its id
	 * @param id
	 * @return Returns PowerTool id
	 * @throws SQLException
	 */
	public static int getPowerToolIDFRomRelationByID(int id) throws SQLException
	{
		String statement = "SELECT * FROM PowerToolsTOStripNails WHERE id = " + id + ";";
		PreparedStatement stmt =  DatabaseGateway.getConnection().prepareStatement(statement);
		ResultSet relation = stmt.executeQuery();
		while(relation.next())
		{
			return relation.getInt("PowerToolid");
		}
		return 0;
	}
	
	/**
	 * Method to get StripNail id from a relation based on its id
	 * @param id
	 * @return Returns StripNail id
	 * @throws SQLException
	 */
	public static int getStripNailIDFRomRelationByID(int id) throws SQLException
	{
		String statement = "SELECT * FROM PowerToolsTOStripNails WHERE id = " + id + ";";
		PreparedStatement stmt =  DatabaseGateway.getConnection().prepareStatement(statement);
		ResultSet relation = stmt.executeQuery();
		while(relation.next())
		{
			return relation.getInt("StripNailid");
		}
		return 0;
	}
}

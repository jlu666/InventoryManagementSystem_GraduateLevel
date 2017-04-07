package domain;
import java.sql.SQLException;
import java.util.ArrayList;

import datasource.DatabaseGateway;
import model.PowerTool;
import model.StripNail;
/**
 * Handles the relation between power tools and strip nails
 * @author Ronald Sease & Darnell Martin
 *
 */
public class PowerToolsToStripNailsMapper 
{
	/**
	 * Used to create a row in the database
	 * @param powerToolID
	 * @param stripNailID
	 * @throws SQLException
	 */
	public PowerToolsToStripNailsMapper(int powerToolID, int stripNailID) throws SQLException
	{
		DatabaseGateway.createRowRelation(powerToolID, stripNailID);
	}
	
	/**
	 * Empty constructor to initialize for lazy load implementation
	 */
	public PowerToolsToStripNailsMapper()
	{
		
	}
	
	/**
	 * Returns a list of PowerTools of all the power tool associated to a specific strip nail
	 * @param stripNailID - id of the strip nail to find power tools associated to
	 * @return powerTools - all the power tools associated to the strip nail
	 * @throws SQLException
	 */
	public ArrayList<PowerTool> getPowerToolsforStripNail(int stripNailID) throws SQLException
	{
		ArrayList<Integer> powerToolIDs = DatabaseGateway.readRowRelationStripNail(stripNailID);
		ArrayList<PowerTool> powerTools = new ArrayList<PowerTool>();
		for(int powerToolID : powerToolIDs)
		{
			powerTools.add(new PowerTool(powerToolID));
		}
		return powerTools;
	}
	
	/**
	 * Returns a list of StripNails for all the strip nails associated to a specific power tool
	 * @param powerToolID - id of the power tool to find strip nails associated to
	 * @return stripNails - all the strip nails associated to the power tool
	 * @throws SQLException
	 */
	public  ArrayList<StripNail>getStripNailsforPowerTool(int powerToolID) throws SQLException
	{
		ArrayList<Integer> stripNailIDs = DatabaseGateway.readRowRelationPowerTool(powerToolID);
		ArrayList<StripNail> stripNails = new ArrayList<StripNail>();
		for(int stripNailID : stripNailIDs)
		{
			stripNails.add(new StripNail(stripNailID));
		}
		return stripNails;
	}
	
	
	

}

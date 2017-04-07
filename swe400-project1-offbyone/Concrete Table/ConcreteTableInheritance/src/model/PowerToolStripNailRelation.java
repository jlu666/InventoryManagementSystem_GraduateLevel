package model;

import java.sql.SQLException;

import datasource.PowerToolStripNailRelationGateway;

/**
 * Holds a strip nail and a power tool that are related.
 * Used for listing all relations in the database.
 * @author Ronald Sease & Darnell Martin
 */
public class PowerToolStripNailRelation
{
	PowerToolStripNailRelationGateway gateway;
	int id;
	PowerTool powerTool;
	StripNail stripNail;
	
	/**
	 * Holds a power tool and a strip nail that are related
	 * @param id - id of entry of relation in PowerToolsTOStripNails Table
	 * @param powerToolID - id of power tool
	 * @param stripNailID - id of strip nail
	 * @throws SQLException 
	 */
	public PowerToolStripNailRelation(int id, int powerToolID, int stripNailID) throws SQLException
	{
		this.gateway = new PowerToolStripNailRelationGateway(id, powerToolID, stripNailID);
		this.id = this.gateway.getID();
		this.assignPowerTool();
		this.assignStripNail();
	}

	/**
	 * Sets the id
	 * @param id - key of the relation in the table
	 */
	public void setID(int id)
	{
		this.id = id;
	}
	
	/**
	 * gets the id
	 * @return id
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * Assigns the values of the power tool from the gateway
	 */
	private void assignPowerTool() 
	{
		this.powerTool = new PowerTool();
		this.powerTool.id = this.gateway.getPowerToolGateway().getID();
		this.powerTool.UPC = this.gateway.getPowerToolGateway().getUPC();
		this.powerTool.ManufacturerID = this.gateway.getPowerToolGateway().getManufacturerID();
		this.powerTool.Price = this.gateway.getPowerToolGateway().getPrice();
		this.powerTool.Description = this.gateway.getPowerToolGateway().getDescription();
		this.powerTool.batteryPowered = this.gateway.getPowerToolGateway().getBatteryPowered();
	}

	/**
	 * Assigns the values of the strip nail from the gateway
	 */
	private void assignStripNail() 
	{
		this.stripNail = new StripNail();
		this.stripNail.id = this.gateway.getStripNailGateway().getID();
		this.stripNail.UPC = this.gateway.getStripNailGateway().getUPC();
		this.stripNail.ManufacturerID = this.gateway.getStripNailGateway().getManufacturerID();
		this.stripNail.Price = this.gateway.getStripNailGateway().getPrice();		
		this.stripNail.numberInStrip = this.gateway.getStripNailGateway().getNumberInStrip();
	}

	/**
	 * returns the power tool of this relation
	 * @return powerTool
	 */
	public PowerTool getPowerTool() 
	{
		return this.powerTool;
	}

	/**
	 * returns the strip nail of this relation
	 * @return stripNail
	 */
	public StripNail getStripNail() 
	{
		return this.stripNail;
	}
	
	
}

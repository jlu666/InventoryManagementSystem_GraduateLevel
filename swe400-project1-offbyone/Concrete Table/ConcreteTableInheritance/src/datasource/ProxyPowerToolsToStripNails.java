package datasource;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.PowerToolsToStripNailsMapper;
import model.PowerTool;
import model.StripNail;

/**
 * Proxy for the relationships between the power tools and
 * strip nails. 
 * Takes in an id of a power tool or a strip nail. 
 * Only initializes the mapper when one of the methods are called.
 * @author Darnell Martin & Marty Sease
 *
 */
public class ProxyPowerToolsToStripNails 
{
	private int id;
	private PowerToolsToStripNailsMapper mapper;
	ArrayList<PowerTool> powerTools;
	ArrayList<StripNail> stripNails;
	
	/**
	 * @param id
	 */
	public ProxyPowerToolsToStripNails(int id)
	{
		this.id = id;
	}
	
	/**
	 * Finds all the power tools related to a strip nail.
	 * Initializes the mapper only when it is called.
	 * @return all the power tools associated to the strip nail
	 * @throws SQLException
	 */
	public ArrayList<PowerTool> getPowerToolsForStripNail() throws SQLException
	{
		if(this.mapper != null)
		{
			this.powerTools = this.mapper.getPowerToolsforStripNail(this.id);
		}
		else
		{
			this.mapper = new PowerToolsToStripNailsMapper();
			this.powerTools = this.mapper.getPowerToolsforStripNail(this.id);
		}
		return this.powerTools;
	}
	
	/**
	 * Finds all the strip nails related to a power tool.
	 * Initializes the mapper only when it is called.
	 * @return all the strip nails associated to the power tool
	 * @throws SQLException
	 */
	public ArrayList<StripNail> getStripNailsForPowerTool() throws SQLException
	{
		if(this.mapper != null)
		{
			this.stripNails = this.mapper.getStripNailsforPowerTool(this.id);
		}
		else
		{
			this.mapper = new PowerToolsToStripNailsMapper();
			this.stripNails = this.mapper.getStripNailsforPowerTool(this.id);
		}
		return this.stripNails;
	}
	
	
}

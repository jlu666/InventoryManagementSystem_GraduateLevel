package domain;
import java.sql.SQLException;
import data_source.LinkTableGateway;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 * A mapper for the LinkTable which handles the compatibilities between StripNails and PowerTools
 */
public class LinkTableMapper 
{
	protected int linkID;
	protected int powerToolID;
	protected int stripNailID;
	
	/**
	 * Empty Constructor 
	 */
	public LinkTableMapper() {}
	
	/**
	 * Get LinkID
	 * 
	 * @return the linkID
	 */
	public int getLinkID() 
	{
		return this.linkID;
	}
	
	/**
	 * Set LinkID
	 * 
	 * @param linkID the linkID to set
	 */
	public void setLinkID(int linkID) 
	{
		this.linkID = linkID;
	}
	
	/**
	 * Get PowerToolID
	 * 
	 * @return the powerToolID
	 */
	public int getPowerToolID() 
	{
		return this.powerToolID;
	}
	
	/**
	 * Set PowerToolID
	 * 
	 * @param powerToolID the powerToolID to set
	 */
	public void setPowerToolID(int powerToolID) 
	{
		this.powerToolID = powerToolID;
	}
	
	/**
	 * Get StripNailID
	 * 
	 * @return the stripNailID
	 */
	public int getStripNailID() 
	{
		return this.stripNailID;
	}
	
	/**
	 * Set StripNailID
	 * 
	 * @param stripNailID the stripNailID to set
	 */
	public void setStripNailID(int stripNailID) 
	{
		this.stripNailID = stripNailID;
	}

	/**
	 * Removes the relation from the LinkTable
	 * 
	 * @param id
	 * @param stripNailID2
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void removeRelation(int powerToolID, int stripNailID) throws ClassNotFoundException, SQLException 
	{
		LinkTableGateway.removeRelation(powerToolID, stripNailID);
	}

	/**
	 * Adds the relation from the LinkTable
	 * 
	 * @param powerToolID
	 * @param stripNailID
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void addRelation(int powerToolID, int stripNailID) throws ClassNotFoundException, SQLException 
	{
		LinkTableGateway.addRelation(powerToolID, stripNailID);
	}	
}
package domain;
import java.sql.SQLException;

import datasource.PowerToolStripNailRowGateway;
import domain.power_tool.PowerTool;
import domain.strip_nail.StripNail;

/**
 * Handles saving and loading the relationship between PowerTool and StripNail to database.
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class PowerToolToStripNailMapper{
	
	/**
	 * Saves a relation between the PowerTool and StripNail into database.
	 * @param tool the specific PowerTool
	 * @param nail the specific StripNails
	 * @return true if this operation is successful. Otherwise, return false.
	 */
	public static boolean save(PowerTool tool, StripNail nail) {
			boolean result =false;;
			try{
				if(tool!=null&&nail!=null){
					result = PowerToolStripNailRowGateway.save(tool.getId(), nail.getId());
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}catch(ClassNotFoundException ex){
				ex.printStackTrace();
			}		
			return result;	
	}

}

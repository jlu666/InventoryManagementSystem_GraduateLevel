package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * It is a row gateway which is used to manage the entity in the powertool_stripnail table
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class PowerToolStripNailRowGateway
{
	private static final String INSERT = "INSERT INTO powertool_stripnail VALUES(?,?);";
	
	/**
	 * Saves a relation between the PowerToolGateway and StripNailGateway into database.
	 * @param tool the specific PowerToolGateway
	 * @param nail the specific StripNailGateway
	 * @return true if this operation is successful. Otherwise, return false.
	 * @throws SQLException SQLException
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	public static boolean save(int tool, int nail) throws SQLException, ClassNotFoundException{
		int success = 0;
		if(tool!=-1&&nail!=-1){
			PreparedStatement stmt = Database.prepareStatement(INSERT);
			stmt.setInt(1, tool);
			stmt.setInt(2, nail);
			success = stmt.executeUpdate();
			stmt.close();
		}
		return (success == 1)? true:false;
		
	}
}

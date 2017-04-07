package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Abstract gateway that each concrete gateway will implement.
 * All children can call the insert method, and each child
 * will have a createInsertStatement method.
 * @author Ronald Sease & Darnell Martin
 *
 */
public abstract class AbstractConcreteGateway 
{

	/**
	 * Inserts a row into the database
	 */
	public void insert()
	{		
		PreparedStatement st = null;
		try 
		{
			st = DatabaseGateway.getConnection().prepareStatement(createInsertStatement());
			st.execute();
		} 
		catch (SQLException e) 
		{
				e.printStackTrace();
		}
	}
	
	/**
	 * Insert statement that each concrete class will have
	 * @return an insert statement formatted to insert the model into the database
	 */
	public abstract String createInsertStatement();
}

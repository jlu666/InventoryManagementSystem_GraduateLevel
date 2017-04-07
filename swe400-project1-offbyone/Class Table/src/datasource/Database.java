package datasource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * It is used to connect to Database.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class Database {

    private static final String ADDRESS = "db.cs.ship.edu:3306";
    private static final String DATABASE = "swe400-13";
    private static final String USERNAME = "swe400_1";
    private static final String PASSWORD = "pwd4swe400_1F16";
	
//	private static final String ADDRESS = "localhost";
//	private static final String DATABASE = "class_table";
//	private static final String USERNAME = "ct_user";
//	private static final String PASSWORD = "ct_pass";
/*	private static final String ADDRESS = "localhost";
	private static final String DATABASE = "test";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";*/


    private static final String CONNECTION_STRING = "jdbc:mysql://" + ADDRESS + "/" + DATABASE;

    private static Connection connection;

    /**
     * @return Properties The database properties used to authenticate a connection.
     */
    private static Properties getConnectionProperties() {
        Properties properties = new Properties();
        properties.put("user", USERNAME);
        properties.put("password", PASSWORD);
        return properties;
    }

    /**
     * @return Connection A connection to the database.
     */
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_STRING, getConnectionProperties());
        }

        return connection;
    }

    /**
     * @return PreparedStatement A prepared statement derived from the input sql.
     * @param statement The input sql.
     * @throws ClassNotFoundException If the sql connector can not be loaded.
     * @throws SQLException If the statement parameter contains invalid sql.
     */
    public static PreparedStatement prepareStatement(String statement) throws ClassNotFoundException, SQLException {
        return getConnection().prepareStatement(statement);
    }

    /**
     * Closes the connection to the database.
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
    public static void closeConnection() throws ClassNotFoundException, SQLException {
        getConnection().close();
    }

    /**
     * Starts a database transaction.
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
    public static void beginTransaction() throws ClassNotFoundException, SQLException {
        PreparedStatement statement = prepareStatement("START TRANSACTION");
        statement.execute();
        statement.close();
    }

    /**
     * Commits the current database transaction.
     */
    protected static void commitTransaction() throws ClassNotFoundException, SQLException {
        PreparedStatement statement = prepareStatement("COMMIT");
        statement.execute();
        statement.close();
    }

    /**
     * Rolls the current database transaction back.
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
    public static void rollbackTransaction() throws ClassNotFoundException, SQLException {
        PreparedStatement statement = prepareStatement("ROLLBACK");
        statement.execute();
        statement.close();
    }
    
    /**
     * Resets AutoIncrement field
     * @throws SQLException SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     */
	public static void resetAutoIncrement() throws SQLException, ClassNotFoundException
	{
		
		PreparedStatement st =  Database.prepareStatement("ALTER TABLE inventory_item AUTO_INCREMENT = 1");
		st.execute();
	}
    
}

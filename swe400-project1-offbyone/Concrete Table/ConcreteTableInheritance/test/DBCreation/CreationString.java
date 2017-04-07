package DBCreation;

/**
 * MySql statements used to create and drop all the tables
 * @author Ronald Sease & Darnell Martin
 *
 */
public class CreationString 
{
	/**
	 * String array that is used as statements to create the database
	 */
	public static final String[] createTables = {"CREATE TABLE Tool ("
			+ "id INT(9) PRIMARY KEY,"
			+ "UPC VARCHAR(30) NOT NULL,"
			+ "ManufacturerID INT(9) NOT NULL,"
			+ "Price INT(9) NOT NULL,"
			+ "Description VARCHAR(50) NOT NULL);",
			
			"CREATE TABLE PowerTool ("
			+ "id INT(9) PRIMARY KEY,"
			+ "UPC VARCHAR(30) NOT NULL,"
			+ "ManufacturerID INT(9) NOT NULL,"
			+ "Price INT(9)NOT NULL,"
			+ "Description VARCHAR(50) NOT NULL,"
			+ "batteryPowered BOOLEAN);",

			 "CREATE TABLE StripNail ("
			+ "id INT(9) PRIMARY KEY,"
			+ "UPC VARCHAR(30) NOT NULL,"
			+ "ManufacturerID INT(9) NOT NULL,"
			+ "Price INT(9)NOT NULL,"
			+ "length DECIMAL(5,2) NOT NULL,"
			+ "NumberInStrip INT(5) NOT NULL);",

			"CREATE TABLE Nail("
			+ "id INT(9) PRIMARY KEY,"
			+ "UPC VARCHAR(30) NOT NULL,"
			+ "ManufacturerID INT(9) NOT NULL,"
			+ "Price INT(9) NOT NULL,"
			+ "Length DECIMAL(5,2) NOT NULL,"
			+ "NumberInBox INT(9) NOT NULL);",
			
		    "CREATE TABLE PowerToolsTOStripNails("
			+ "id INT(9) PRIMARY KEY AUTO_INCREMENT,"
			+ "PowerToolid INT(9) NOT NULL,"
			+ "FOREIGN KEY(PowerToolid) REFERENCES PowerTool(id), "
			+ "StripNailid INT(9) NOT NULL,"
			+ "FOREIGN KEY(StripNailid) REFERENCES StripNail(id));",
			
			"CREATE TABLE `KeyTable` ("
			+ "`Key` int(11) NOT NULL AUTO_INCREMENT,"
			+ "`InUse` tinyint(4) NOT NULL,"
			+ "PRIMARY KEY (`Key`));"};
	
	/**
	 * String array to drop all the tables in the database
	 */
	public static final String[] dropTables = { "DROP TABLE PowerToolsTOStripNails;",
												"DROP TABLE Tool;",
												"DROP TABLE PowerTool;",
												"DROP TABLE StripNail;",
												"DROP TABLE Nail;",
												"DROP TABLE KeyTable;"};
}

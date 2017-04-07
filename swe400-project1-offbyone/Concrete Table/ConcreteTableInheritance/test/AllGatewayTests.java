import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import datasource.DatabaseGateway;
import enums.EnumInsert;
import gateway.TestNailGateway;
import gateway.TestPowerToolGateway;
import gateway.TestStripNailGateway;
import gateway.TestStripNailPowerToolRelationGateway;
import gateway.TestToolGateway;
/**
 * Recreates the database, puts in the enums and runs all the tests
 * @author Darnell Martin & Ronald Sease
 *
 */
@RunWith(Suite.class)


@Suite.SuiteClasses({
	TestNailGateway.class,
	TestPowerToolGateway.class,
	TestStripNailGateway.class,
	TestToolGateway.class,
	TestStripNailPowerToolRelationGateway.class
})

public class AllGatewayTests{
	/**
	 * This clears the database so we don't have any unexpected values in our database when testing.
	 * 
	 * An example of how to use @BeforeClass was found at:
	 * http://stackoverflow.com/questions/1967308/java-junit-test-suite-code-to-run-before-any-test-classes
	 * @throws SQLException
	 */
	@BeforeClass
	public static void clearDatabase() throws SQLException
	{
		System.out.println("Recreating database and inserting enums for Gateway tests");
		DatabaseGateway.createDatabase();
		EnumInsert.insertPowerToolsEnums();
		EnumInsert.insertStripNailsEnums();
		EnumInsert.insertRelations();
		EnumInsert.insertToolsEnums();
		EnumInsert.insertNailsEnums();
	}
}
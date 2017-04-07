import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import datasource.DatabaseGateway;
import datasource.TestDatabaseGateway;
import enums.TestNailsEnum;
import enums.TestPowerToolsEnum;
import enums.TestStripNailsEnum;
import enums.TestToolsEnum;
import mockObjects.TestMockFastner;
import mockObjects.TestMockInventoryItem;
import model.TestNail;
import model.TestPowerTool;
import model.TestPowerToolToStripNails;
import model.TestStripNail;
import model.TestTool;
/**
 * Recreates the database and runs all tests
 * @author Darnell Martin & Ronald Sease
 *
 */
@RunWith(Suite.class)


@Suite.SuiteClasses({
	TestPowerTool.class,
	TestTool.class,
	TestStripNail.class,
	TestNail.class,
	TestMockInventoryItem.class,
	TestPowerToolToStripNails.class,
	TestDatabaseGateway.class,
	TestMockFastner.class,
	TestPowerToolsEnum.class,
	TestToolsEnum.class,
	TestNailsEnum.class,
	TestStripNailsEnum.class,
	TestRunner.class,
	AllGatewayTests.class
})

public class AllTests{
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
		DatabaseGateway.createDatabase();
	}
}
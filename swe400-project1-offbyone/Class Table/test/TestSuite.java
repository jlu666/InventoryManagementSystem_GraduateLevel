
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import data.TestEnums;
import datasource.TestFastnerGateway;
import datasource.TestNailFinder;
import datasource.TestNailGateway;
import datasource.TestPowerToolGateway;
import datasource.TestStripNailFinder;
import datasource.TestStripNailGateway;
import datasource.TestToolGateway;
import domain.TestDomainObject;
import domain.TestFastener;
import domain.TestInventoryItem;
import domain.TestInventoryItemSearcher;
import domain.TestNail;
import domain.TestPowerTool;
import domain.TestPowerToolToStripNailMapper;
import domain.TestStripNail;
import domain.TestTool;
import domain.TestVirtualList;
import runner.TestRunnerList;
import runner.TestRunnerUpc;
import runner.TestRunnerWrite;
import runner.input.TestAddRelationShipHandler;
import runner.input.TestBatteryPoweredHandler;
import runner.input.TestDescriptionHandler;
import runner.input.TestInputHandler;
import runner.input.TestInserRelationshipHandler;
import runner.input.TestLengthHandler;
import runner.input.TestManufacturerIDHandler;
import runner.input.TestNailSequence;
import runner.input.TestNumberInBoxHandler;
import runner.input.TestNumberInStripHandler;
import runner.input.TestPowerToolSequence;
import runner.input.TestPriceHandler;
import runner.input.TestStripNailSequence;
import runner.input.TestToolSequence;
import runner.input.TestTypeCodeHandler;
import runner.input.TestUpcHandler;

/**
 *  @author Alan Malloy & Jixiang Lu
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestNailFinder.class,
    TestNailGateway.class,
    TestStripNailFinder.class,
    TestStripNailGateway.class,
    TestStripNail.class,
    TestNail.class,
    TestToolGateway.class,
    TestTool.class,
    TestPowerToolGateway.class,
    TestPowerTool.class,
    TestVirtualList.class,
    TestFastener.class,
    TestFastnerGateway.class,
    TestTool.class,
    TestInventoryItemSearcher.class,
    TestRunnerUpc.class,
    TestInventoryItem.class,
    TestPowerToolToStripNailMapper.class,
    TestDomainObject.class,
    TestEnums.class,
    TestInputHandler.class,
    TestPriceHandler.class,
    TestBatteryPoweredHandler.class,
    TestDescriptionHandler.class,
    TestUpcHandler.class,
    TestNumberInStripHandler.class,
    TestNumberInBoxHandler.class,
    TestManufacturerIDHandler.class,
    TestLengthHandler.class,
    TestTypeCodeHandler.class,
    TestToolSequence.class,
    TestPowerToolSequence.class,
    TestNailSequence.class,
    TestStripNailSequence.class,
    TestAddRelationShipHandler.class,
    TestInserRelationshipHandler.class,
    TestRunnerList.class,
    TestRunnerWrite.class
})

/**
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestSuite {
}

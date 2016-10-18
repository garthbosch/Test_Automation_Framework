package testFramework.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import testFramework.utils.ApplicationConfiguration;

/**
 *
 * @author Garth Bosch
 */
public class CashBackDataSetupNG {

    static TestDirector instance;

    public CashBackDataSetupNG() {
        this.instance = new TestDirector();
    } 

    @Test
    public void runSetupCashBackData() throws Exception {
        System.out.println("============================================================================================================================================");
        System.out.println("==========Starting to setup data for Cash Back Testing (with data file " + ApplicationConfiguration.getCashBackDATAFile() + "==========");
        System.out.println("============================================================================================================================================");
        instance.runTests(ApplicationConfiguration.getTestEnvironment(), ApplicationConfiguration.getQcTestSetFolerPathRelease7_0_0(),
                ApplicationConfiguration.getQcTestSetName(), ApplicationConfiguration.getCashBackDATAFile());
        Assert.assertTrue(TestDirector.reportGenerator.totalFails < 1, "[Error] There were one or more tests that has failed");
    }
}

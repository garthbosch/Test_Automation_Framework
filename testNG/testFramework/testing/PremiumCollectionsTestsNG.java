package testFramework.testing;

import org.junit.Assert;
import org.junit.Test;

import testFramework.utils.ApplicationConfiguration;

/**
 *
 * @author Garth Bosch
 */
public class PremiumCollectionsTestsNG {

    static TestDirector instance;

    public PremiumCollectionsTestsNG() {
        this.instance = new TestDirector();
    }

    @Test
    public void runPremiumCollectionsTests() throws Exception {
        System.out.println("================================================================================================================================");
        System.out.println("==========Starting Premium Collections Test Suite (with data file " + ApplicationConfiguration.getPremiumCollectionsDATAFile() + "==========");
        System.out.println("================================================================================================================================");
        instance.runTests(ApplicationConfiguration.getTestEnvironment(), ApplicationConfiguration.getQcTestSetFolerPathRelease7_0_0(),
                ApplicationConfiguration.getQcTestSetName(), ApplicationConfiguration.getPremiumCollectionsDATAFile());
               Assert.assertTrue("[Error] There were one or more tests that has failed", TestDirector.reportGenerator.totalFails < 1);
    }
}

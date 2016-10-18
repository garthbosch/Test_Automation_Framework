package testFramework.testing;

import org.junit.Assert;
import org.junit.Test;
import testFramework.utils.ApplicationConfiguration;

/**
 *
 * @author Garth Bosch
 */
public class SchemeStatusesTestsNG {

    static TestDirector instance;

    public SchemeStatusesTestsNG() {
        this.instance = new TestDirector();
    }

    @Test
    public void runSchemeStatusesTests() throws Exception {
        System.out.println("=========================================================================================================================");
        System.out.println("==========Starting Schemes Statuses Tests (with data file " + ApplicationConfiguration.getSchemeStatusDATAFile() + "==========");
        System.out.println("=========================================================================================================================");
        instance.runTests(ApplicationConfiguration.getTestEnvironment(), ApplicationConfiguration.getQcTestSetFolerPathRelease7_0_0(),
                ApplicationConfiguration.getQcTestSetName(), ApplicationConfiguration.getSchemeStatusDATAFile());
        Assert.assertTrue("[Error] There were one or more tests that has failed", TestDirector.reportGenerator.totalFails < 1);
    }
}

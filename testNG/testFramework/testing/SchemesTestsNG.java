package testFramework.testing;

import org.junit.Assert;
import org.junit.Test;
import testFramework.utils.ApplicationConfiguration;

/**
 *
 * @author Garth Bosch
 */
public class SchemesTestsNG {

    static TestDirector instance;

    public SchemesTestsNG() {
        this.instance = new TestDirector();
    }

    /**
     * Test where you create a BSSP scheme without a quote
     *
     * @throws java.lang.Exception
     */
    @Test
    public void runSchemesTests() throws Exception {
        System.out.println("================================================================================================================");
        System.out.println("==========Starting Schemes Tests (with data file " + ApplicationConfiguration.getSchemesDATAFile() + "==========");
        System.out.println("================================================================================================================");
        instance.runTests(ApplicationConfiguration.getTestEnvironment(), ApplicationConfiguration.getQcTestSetFolerPathRelease7_0_0(),
                ApplicationConfiguration.getQcTestSetName(), ApplicationConfiguration.getSchemesDATAFile());
        Assert.assertTrue("[Error] There were one or more tests that has failed", TestDirector.reportGenerator.totalFails < 1);
    }
}

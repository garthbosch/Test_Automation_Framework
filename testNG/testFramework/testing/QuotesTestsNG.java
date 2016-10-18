package testFramework.testing;

import org.junit.Assert;
import org.junit.Test;
import testFramework.utils.ApplicationConfiguration;

/**
 *
 * @author Garth Bosch
 */
public class QuotesTestsNG {

    static TestDirector instance;

    public QuotesTestsNG() {
        this.instance = new TestDirector();
    }

    @Test
    public void runQuotesTests() throws Exception {
        System.out.println("=======================================================================================================================");
        System.out.println("==========Starting Quotes Tests (with data file " + ApplicationConfiguration.getQuotesDATAFile() + "==========");
        System.out.println("=======================================================================================================================");
        instance.runTests(ApplicationConfiguration.getTestEnvironment(), ApplicationConfiguration.getQcTestSetFolerPathRelease7_0_0(),
                ApplicationConfiguration.getQcTestSetName(), ApplicationConfiguration.getQuotesDATAFile());
        Assert.assertTrue("[Error] There were one or more tests that has failed", TestDirector.reportGenerator.totalFails < 1);
    }
}

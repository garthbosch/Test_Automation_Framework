package testFramework.testing;

import static org.junit.Assert.fail;
import org.junit.Test;
import testFramework.testing.testSuites.MembersTests;

/**
 *
 * @author Garth Bosch
 */
public class MembersTestsNG {

    public MembersTestsNG() {
    }

    private final String env = "DEV";
//    private final String env = "QA";
    private final String qcTestLabPath = "Root\\Automated Tests - Selenium\\Release 6.7.0\\" + env + " Environment";
    private final String qcTestSetName = "Quotes And Schemes Regression";

    /**
     * This test will search for a specific scheme and add existing members to
     * it.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void getAddExistingMembersToSchemeTest() throws Exception {
        MembersTests instance = new MembersTests();
        Boolean testCompleted = instance.addExistingMembersToSchemeTest(env, qcTestLabPath, qcTestSetName);
        if (!testCompleted) {
            fail("This test has failed - the desired outcome was not met.");
        } else {
            System.out.println("This test has passed successfully!!!");
        }
    }
}

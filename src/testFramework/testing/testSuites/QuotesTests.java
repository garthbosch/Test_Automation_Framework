package testFramework.testing.testSuites;

import testFramework.enums.ProductType;
import testFramework.utils.Parameter;
import java.util.List;
import testFramework.testing.testOutcome.TestResult;
import testFramework.testing.testPages.Quotes;
import static testFramework.testing.testSuites.BaseClass.qcIntegration;
import static testFramework.testing.testSuites.BaseClass.seleniumDriver;

/**
 *
 * @author Garth Bosch
 */
public class QuotesTests extends BaseClass {

    public QuotesTests() {
    }

    /**
     * This test creates a BSSP Customised Quote and put the Quote in an
     * Accepted status.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult addNewBSSPCustomisedQuoteTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(0);
        return doTest(addingQuote(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a GFS Normal Quote and put the Quote in an Accepted
     * status.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult addNewGFSNormalQuoteTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(1);
        return doTest(addingQuote(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a GFS Customised Quote and put the Quote in an Accepted
     * status.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult addNewGFSCustomisedQuoteTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(2);
        return doTest(addingQuote(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    public Boolean addingQuote(Parameter parameter, String env) throws Exception {
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create a Quote=============");
        if (!quotes.createQuote(parameter, env)) {
            System.err.println("[Error] Failed to complete perform Adding Quote");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Approve Or Assessment Of Quote=============");
        if (!approvalOrAssessmentOfQuote(parameter, env)) {
            System.err.println("[Error] Failed to complete perform Approval Or Assessment Of Quote");
            return false;
        }
        return true;
    }

    private Boolean approvalOrAssessmentOfQuote(Parameter parameter, String env) throws Exception {
        boolean isSuccessfullyCompleted = false;
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Approve Quote=============");
        if (parameter.getProductType().equals(ProductType.GFS.getProductTypeName())) {
            isSuccessfullyCompleted = quotes.approveQuote(parameter, env);
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Assess and Approve Quote=============");
        if (parameter.getProductType().equals(ProductType.BSCustomised.getProductTypeName())
                || parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName())) {
            isSuccessfullyCompleted = quotes.assessBSSPCustomisedQuote(parameter, env) && quotes.approveQuote(parameter, env);
        }
        return isSuccessfullyCompleted;
    }

    private TestResult doTest(Boolean test, List<Parameter> parms, Parameter parm, String qcTestLabPath, String qcTestSetName) throws Exception {
        if (!test) {
            scrnShotsandMsgs.captureScreenshot("Failed to complete " + parm.getTestCaseName() + "_", parm, true);
            if (!zipEvidenceFolder()) {
                System.err.println("[Error] The directory \"" + evidenceFolder + "\" was not zipped");
            }
            if (!qcIntegration.updateTestOnQC(parm, evidenceFolder, qcTestLabPath, qcTestSetName, false)) {
                System.err.println("[Error] Test Set \"" + qcTestSetName + "\" was not updated on Quality Center");
            }
            return new TestResult(parms, false, "Failed to complete " + parm.getTestCaseName() + "_", this.getTotalExecutionTime());
        }
        scrnShotsandMsgs.captureScreenshot("Successfully completed " + parm.getTestCaseName() + "_", parm, false);
        if (!zipEvidenceFolder()) {
            System.err.println("[Error] The directory \"" + evidenceFolder + "\" was not zipped");
        }
        if (!qcIntegration.updateTestOnQC(parm, evidenceFolder, qcTestLabPath, qcTestSetName, true)) {
            System.err.println("[Error] Test Set \"" + qcTestSetName + "\" was not updated on Quality Center");
        }
        return new TestResult(parms, true, "Successfully completed " + parm.getTestCaseName() + "_", this.getTotalExecutionTime());
    }
}

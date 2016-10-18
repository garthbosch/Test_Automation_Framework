package testFramework.testing.testSuites;

import testFramework.enums.ProductType;
import testFramework.utils.MemberDetails;
import testFramework.utils.Parameter;
import testFramework.utils.ParameterHelper;
import java.util.List;
import testFramework.testing.testOutcome.TestResult;
import testFramework.testing.testPages.Schemes;
import testFramework.testing.testPages.Quotes;

/**
 *
 * @author Garth Bosch
 */
public class SchemesTests extends BaseClass {

    /**
     * This test creates a BSSP Scheme from the SCHEMES page, add a product
     * option, add members, assess the scheme, do cash confirmation and put the
     * scheme in an Active Status.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult addNewBSSPSchemeWithProductOptionAndMembersTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(0);
        return doTest(addingSchemeWithProductOptionAndMembers(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a BSSP Scheme by creating a BSSP Customised Quote from
     * the QUOTES page, assess and approve the quote, complete the scheme on the
     * SCHEMES page, add scheme members, assess scheme, do an Advance Payment
     * and put the scheme in an Active Status.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult addNewBSSPSchemeWithQuoteProductOptionAndMembersTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(1);
        return doTest(addingSchemeWithQuoteProductOptionAndMembers(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a GFS Scheme by creating a GFS Normal Quote from the
     * QUOTES page, assess and approve the quote, complete the scheme on the
     * SCHEMES page, add scheme members, assess scheme, do an Advance Payment
     * and put the scheme in an Active Status.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult addNewGFSSchemeWithQuoteProductOptionAndMembersTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(2);
        return doTest(addingSchemeWithQuoteProductOptionAndMembers(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a BSSP Scheme by creating a GFS Customised Quote from
     * the QUOTES page, assess and approve the quote, complete the scheme on the
     * SCHEMES page, add scheme members, assess scheme, do an Advance Payment
     * and put the scheme in an Active Status.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult addNewGFSSchemeWithCustomisedQuoteProductOptionAndMembersTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(3);
        return doTest(addingSchemeWithQuoteProductOptionAndMembers(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    public Boolean addingSchemeWithProductOptionAndMembers(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
        return true;
    }

    public Boolean addingSchemeWithQuoteProductOptionAndMembers(Parameter parameter, String env) throws Exception {
        List<MemberDetails> memDetails = ParameterHelper.readMemberDetails(appConfig.getNewMembersDATAFile());
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Quote=============");
        if (!quotes.createQuote(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Quote");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Approval Or Assessment Of Quote=============");
        if (!approvalOrAssessmentOfQuote(parameter, env)) {
            System.err.println("[Error] Failed to complete Approval Or Assessment Of Quote");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Complete Scheme=============");
        if (!schemes.completeScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Add Scheme Members Via Policy Admin=============");
        if (!schemes.addSchemeMembersViaPolicyAdmin(parameter, env, memDetails)) {
            System.err.println("[Error] Failed to complete Add Scheme Members Via Policy Admin");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Scheme To Be Assessed=============");
        if (!schemes.setSchemeToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete  Set Scheme To Be Assessed");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
        return true;
    }

    private boolean createNewSchemeUpToToBeAssessed(Parameter parameter, String env) throws Exception {
        List<MemberDetails> memDetails = ParameterHelper.readMemberDetails(appConfig.getNewMembersDATAFile());
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme=============");
        if (!schemes.createNewScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Create New Scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Add Scheme Product Options=============");
        if (!schemes.addSchemeProductOptions(parameter, env)) {
            System.err.println("[Error] Failed to complete Add Scheme Product Options");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Add Scheme Members Via Policy Admin=============");
        if (!schemes.addSchemeMembersViaPolicyAdmin(parameter, env, memDetails)) {
            System.err.println("[Error] Failed to complete Add Scheme Members Via Policy Admin");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Scheme To Be Assessed=============");
        if (!schemes.setSchemeToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete Set Scheme To Be Assessed");
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

package testFramework.testing.testSuites;

import java.util.ArrayList;
import testFramework.enums.ProductType;
import testFramework.utils.MemberDetails;
import testFramework.utils.Parameter;
import testFramework.utils.ParameterHelper;
import java.util.List;
import testFramework.enums.SchemeStatuses;
import testFramework.testing.testOutcome.TestResult;
import testFramework.testing.testPages.Schemes;
import testFramework.testing.testPages.Quotes;
import static testFramework.testing.testSuites.BaseClass.seleniumDriver;

/**
 *
 * @author Garth Bosch
 */
public class CashBackDataSetup extends BaseClass {

    /**
     * This is to setup data for a cash back scenario where are a GFS customised
     * scheme has 500 PMs and 12 consecutive premiums
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult gfsSchemeTwelveSuccessfulPremsTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(0);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     *
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult bsspSchemeTwelveSuccessfulPremsTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(1);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     *
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult gfsSchemeTwelvePremsNotConsecutivelyTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(2);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }
    
    /**
     *
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult bsspSchemeTwelvePremsNotConsecutivelyTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(3);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }
    
    /**
     *
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult gfsSchemeTwelveSuccessfulPremsPMsLessThan500Test(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(4);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }
    
    /**
     *
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult bsspSchemeTwelveSuccessfulPremsPMsLessThan500Test(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(5);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    
    /**
     * 
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult gfsSchemeElevenSuccessfulPremsTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(6);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     *
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult bsspSchemeElevenSuccessfulPremsTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(7);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }
    /**
     * 
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult gfsSchemeTwelveSuccessfulPremsCancelTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(8);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     *
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult bsspSchemeTwelveSuccessfulPremsCancelTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(9);
        return doTest(createSchemeTwelveSuccessfulPremsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }
    
//========================================================================================================================================//    
    private Boolean createSchemeTwelveSuccessfulPremsTest(Parameter parameter, String env) throws Exception {
        List<MemberDetails> memDetails = ParameterHelper.readMemberDetails(appConfig.getNewMembersDATAFile());
        schemes = new Schemes();
        quotes = new Quotes();
//        set the ScheduleParameter value to 12 months and 45 days back from today's date.
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(-18, -13, 0));
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
//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Add Scheme Members Via Policy Admin=============");
//        if (!schemes.addSchemeMembersViaPolicyAdmin(parameter, env, memDetails)) {
//            System.err.println("[Error] Failed to complete Add Scheme Members Via Policy Admin");
//            return false;
//        }
//       
//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Scheme To Be Assessed=============");
//        if (!schemes.setSchemeToBeAssessed(parameter, env)) {
//            System.err.println("[Error] Failed to complete  Set Scheme To Be Assessed");
//            return false;
//        }
//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
//            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
//            return false;
//        }
//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
//        if (!schemes.activatingTheScheme(parameter, env, false)) {
//            System.err.println("[Error] Failed to Activate the scheme");
//            return false;
//        }

//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
//        seleniumDriver.setScheduleParameterValueToSpecificValue(env, "2014-02-19 00:00:00.000");
//        
//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
//            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
//            return false;
//        }        
//        
//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
//        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.Lapsed.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
//            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
//            return false;
//        }

//        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Quote=============");
//        if (!schemes.cancelScheme(parameter, env)) {
//            System.err.println("[Error] Failed to complete Cancel Scheme");
//            return false;
//        }
        
//        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter); 
//        schemeNumber = "GFS001869000000";        
//        List<String> nextThirteenScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 10);
//        List<String> nextThirteenScheduledRaisingDatesForScheme = new ArrayList<>();
//        nextThirteenScheduledRaisingDatesForScheme.add("2014-07-21 00:00:00.000");
////        nextThirteenScheduledRaisingDatesForScheme.add("2014-05-21 00:00:00.000");
////        nextThirteenScheduledRaisingDatesForScheme.add("2014-06-19 00:00:00.000");
////        nextThirteenScheduledRaisingDatesForScheme.add("2014-07-21 00:00:00.000");
////        nextThirteenScheduledRaisingDatesForScheme.add("2014-08-20 00:00:00.000");
//        for (String nextScheduleRaisingDateForScheme : nextThirteenScheduledRaisingDatesForScheme) {
//            System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
//            seleniumDriver.setScheduleParameterValueToSpecificValue(env, nextScheduleRaisingDateForScheme);
//            
//            System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//            if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
//                System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
//                return false;
//            }
//        }
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
//            if (!zipEvidenceFolder()) {
//                System.err.println("[Error] The directory \"" + evidenceFolder + "\" was not zipped");
//            }
//            if (!qcIntegration.updateTestOnQC(parm, evidenceFolder, qcTestLabPath, qcTestSetName, false)) {
//                System.err.println("[Error] Test Set \"" + qcTestSetName + "\" was not updated on Quality Center");
//            }
            return new TestResult(parms, false, "Failed to complete " + parm.getTestCaseName() + "_", this.getTotalExecutionTime());
        }
        scrnShotsandMsgs.captureScreenshot("Successfully completed " + parm.getTestCaseName() + "_", parm, false);
//        if (!zipEvidenceFolder()) {
//            System.err.println("[Error] The directory \"" + evidenceFolder + "\" was not zipped");
//        }
//        if (!qcIntegration.updateTestOnQC(parm, evidenceFolder, qcTestLabPath, qcTestSetName, true)) {
//            System.err.println("[Error] Test Set \"" + qcTestSetName + "\" was not updated on Quality Center");
//        }
        return new TestResult(parms, true, "Successfully completed " + parm.getTestCaseName() + "_", this.getTotalExecutionTime());
    }
}

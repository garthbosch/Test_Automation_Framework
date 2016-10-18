package testFramework.testing.testSuites;

import testFramework.enums.SchemeStatuses;
import testFramework.utils.MemberDetails;
import testFramework.utils.Parameter;
import testFramework.utils.ParameterHelper;
import java.util.List;
import testFramework.testing.testOutcome.TestResult;
import testFramework.testing.testPages.Quotes;
import testFramework.testing.testPages.Schemes;
import static testFramework.testing.testSuites.BaseClass.seleniumDriver;

/**
 *
 * @author Garth Bosch
 */
public class SchemeStatusesTests extends BaseClass {

    public SchemeStatusesTests() {
    }

    /**
     * This test creates a Scheme and put it in an Awaiting First Premium status
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult awaitingFirstPremiumSchemeStatusTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(0);
        return doTest(createSchemeAndMoveToAwaitingFirstPremium(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme and put it in a Cancel status
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult cancelSchemeStatusTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(1);
        return doTest(createSchemeAndCancelIt(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme and put it in a Lapse Pending status
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult lapsePendingSchemeStatusTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(2);
        return doTest(createSchemeAndPutInLapsePendingStatus(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme and put it in a Lapsed status
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult lapsedSchemeStatusTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(3);
        return doTest(createSchemeAndPutInLapsedStatus(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme and put it in a New Business Non Payment
     * status
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult newBusinessNonPaymentSchemeStatusTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(4);
        return doTest(createSchemeAndMoveToNewBusinessNonPayment(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme and put it in a Not Taken status
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult notTakenSchemeStatusTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(5);
        return doTest(createSchemeAndMoveToNotTaken(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

//========================================================================================================================================//
    private Boolean createSchemeAndMoveToAwaitingFirstPremium(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        return true;
    }

    private Boolean createSchemeAndCancelIt(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Accepting Scheme First Payment And Activate Scheme=============");
        if (!schemes.acceptingSchemeFirstPaymentAndActivateScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Set Scheme To Be Assessed");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Cancel Scheme=============");
        if (!schemes.cancelScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Cancel Scheme");
            return false;
        }
        return true;
    }

    private Boolean createSchemeAndPutInLapsePendingStatus(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Accepting Scheme First Payment And Activate Scheme=============");
        if (!schemes.acceptingSchemeFirstPaymentAndActivateScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Set Scheme To Be Assessed");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.LapsePending.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
            return false;
        }
        return true;
    }

    private Boolean createSchemeAndPutInLapsedStatus(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Accepting Scheme First Payment And Activate Scheme=============");
        if (!schemes.acceptingSchemeFirstPaymentAndActivateScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Set Scheme To Be Assessed");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.Lapsed.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
            return false;
        }
        return true;
    }

    private Boolean createSchemeAndMoveToNewBusinessNonPayment(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============New Business Non Payment Or Not Taken Scheme=============");
        if (!schemes.newBusinessNonPaymentOrNotTakenScheme(parameter, env, SchemeStatuses.NewBusinessNonPayment.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete New Business Non Payment Or Not Taken Scheme");
            return false;
        }
        return true;
    }

    private Boolean createSchemeAndMoveToNotTaken(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
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
        if (!schemes.newBusinessNonPaymentOrNotTakenScheme(parameter, env, SchemeStatuses.NotTaken.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete New Business Non Payment Or Not Taken Scheme");
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

    private String setScheduleParameterValueToTodaysDate(String env) throws Exception {
//        get the value field of the prd.ScheduleParameter table
        String scheduleParameterValue = dao.getScheduleParameterValue(env);
        System.out.println("Schedule Parameter Default Value is: " + scheduleParameterValue);
//        set the value field of the prd.ScheduleParameter table to today's date 
        System.out.println("***********************Changing the Value item on the ScheduleParameter table to " + seleniumDriver.getDate(0, 0, 0));
        dao.setScheduleParameterValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
        return scheduleParameterValue;
    }

    private void setScheduleParameterValueBackToDefault(String env, String scheduleParameterValue) throws Exception {
//        set the value field of the prd.ScheduleParameter table to what it was before the change
        System.out.println("***********************Changing the Value item on the ScheduleParameter table back to the default value " + scheduleParameterValue);
        dao.setScheduleParameterValue(env, scheduleParameterValue);
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

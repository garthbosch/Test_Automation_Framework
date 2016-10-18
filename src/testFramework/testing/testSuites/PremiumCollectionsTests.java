package testFramework.testing.testSuites;

import java.io.File;
import testFramework.utils.MemberDetails;
import testFramework.utils.Parameter;
import testFramework.utils.ParameterHelper;
import java.util.List;
import testFramework.enums.SchemeStatuses;
import testFramework.testing.testOutcome.TestResult;
import testFramework.testing.testPages.Quotes;
import testFramework.testing.testPages.Schemes;
import static testFramework.testing.testSuites.BaseClass.seleniumDriver;
import testFramework.utils.IOUtils;

/**
 *
 * @author Garth Bosch
 */
public class PremiumCollectionsTests extends BaseClass {

    public PremiumCollectionsTests() {
    }

    /**
     * This test creates a Brand New Scheme and check if a NEW Transaction has
     * been added to the YMOutput file
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult newTransactionBrandNewSchemeTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(0);
        return doTest(createNewTransactionBrandNewSchemeTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme with a CASH payment method, changes the
     * payment method type to Debit Order and check if a NEW Transaction has
     * been added to the YMOutput file
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult newTransactionPaymentMethodTypeChangeCashToDebitTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(1);
        return doTest(createNewTransactionPaymentMethodTypeChangeCashToDebitTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme with and changes the payment method day to a
     * different day and check if a NEW and Delete Transaction has been added to
     * the YMOutput file
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult deleteNewTransactionPaymentDayChangeTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        seleniumDriver.setScheduleParameterValueToTodaysDate(env);
        this.setStartTime();
        Parameter parm = parms.get(2);
        return doTest(createDeleteNewTransactionPaymentDayChangeTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme apply consecutive RD's, with cash
     * confirmations between the RD's to keep the scheme active. A New YM
     * Transaction will be created for this scheme after next daily raising.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult newTransactionConsecutiveRDsTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(3);
        return doTest(createNewTransactionConsecutiveRDsTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme activate it then lapsed it. After a delete has
     * been sent to the YMOutput file do a cash confirmation to reactivate the
     * scheme. Make sure New transaction has been sent YMoutput file.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult deleteNewTransactionLapsedSchemeTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(4);
        return doTest(createDeleteNewTransactionLapsedSchemeTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Debit Order Scheme, activate it by doing a cash
     * confirmation. Allow the second premium to be received via the daily
     * raisings. A new transaction should then appear in the YMOutput file for
     * this scheme.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult newTransactionFirstPremCashConfirmationSecondPremViaRaisingTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(5);
        return doTest(createNewTransactionFirstPremCashConfirmationSecondPremViaRaisingTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Debit Order Scheme, activate it. On the next daily
     * raising RD the scheme with reason 'DEBIT NOT ALLOWED'. Transaction on YM
     * will immediately be deleted. A cash confirmation is done and with the
     * next daily raising a new Transaction is sent to YM
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult newTransactionDeleteFromYMwithSpecificRDreasonTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(6);
        return doTest(createNewTransactionDeleteFromYMwithSpecificRDreasonTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Debit Order Scheme, and put the scheme in Awaiting
     * First Premium. Daily Raising is done and it RD's. Scheme goes in New
     * Business None Payment status. On the next daily raising a Delete is sent
     * to YM this scheme.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult deleteTransactionNewBusinessNonePaymentSchemeTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(7);
        return doTest(createDeleteTransactionNewBusinessNonePaymentSchemeTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Debit Order Scheme, activate it. Change the Payment
     * Method Type from debit to cash. A Delete transaction should be send to YM
     * this scheme.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return TestResult
     * @throws Exception
     */
    public TestResult deleteTransactionPaymentMethodTypeChangeDebitToCashTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(8);
        return doTest(createDeleteTransactionPaymentMethodTypeChangeDebitToCashTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Debit Order Scheme, activate it. Lapse the scheme and
     * do not recover any arrear payments. A Delete transaction should be send
     * to YM this scheme.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult deleteTransactionSchemeLapsedNoArrearPaymentReceivedTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(9);
        return doTest(createDeleteTransactionSchemeLapsedNoArrearPaymentReceivedTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Debit Order Scheme, activate it. Cancel the scheme. A
     * Delete transaction should be send to YM this scheme.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult deleteTransactionSchemeCancelledStatusTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(10);
        return doTest(createDeleteTransactionSchemeCancelledStatusTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme that is in Awaiting First Premium and applies
     * an OVER cash application
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult oversCashApplicationForAwaitingFirstPremiumTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(11);
        return doTest(createOversCashApplicationForAwaitingFirstPremiumTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme that is in New Business Non Payment Status and
     * an over payment is captured.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult oversCashApplicationForNewBusinessNonPaymentTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(12);
        return doTest(createOversCashApplicationForNewBusinessNonPaymentTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme that is in Not Taken Status and an over
     * payment is captured.
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult overCashApplicationNotTakenTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(13);
        return doTest(createOverCashApplicationNotTakenTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

    /**
     * This test creates a Scheme that is in Lapsed Status and an under payment
     * is captured to open up PMAR
     *
     * @param parms
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public TestResult underCashApplicationAndPMARLapsedTest(List<Parameter> parms, String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        this.setStartTime();
        Parameter parm = parms.get(14);
        return doTest(createUnderCashApplicationAndPMARLapsedTest(parm, env), parms, parm, qcTestLabPath, qcTestSetName);
    }

//========================================================================================================================================//
    private Boolean createNewTransactionBrandNewSchemeTest(Parameter parameter, String env) throws Exception {
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createNewTransactionPaymentMethodTypeChangeCashToDebitTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Accepting Scheme First Payment And Activate Scheme=============");
        if (!schemes.acceptingSchemeFirstPaymentAndActivateScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Change Scheme Payment Method Type=============");
//        changing the payment method type to Debit Order
        parameter.setSchemePaymentMethodType("Debit Order");
        if (!schemes.changeSchemePaymentMethod(parameter, env, new String[]{"PaymentMethodType"})) {
            System.err.println("[Error] Failed to complete Change Scheme Payment Method");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createDeleteNewTransactionPaymentDayChangeTest(Parameter parameter, String env) throws Exception {
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Change Scheme Payment Method Day to 15=============");
//        changing the payment method day to Debit Order        
        parameter.setSchemePaymentMethodPaymentDay("15");
        if (!schemes.changeSchemePaymentMethod(parameter, env, new String[]{"PaymentMethodPaymentDay"})) {
            System.err.println("[Error] Failed to complete Change Scheme Payment Day");
            return false;
        }
//        set the ScheduleParameter value to tomorrow
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(+1, 0, 0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(+1, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify Delete Transaction Entry In YMOutput File=============");
        if (!schemes.verifyDeleteTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(+1, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify Delete Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createNewTransactionConsecutiveRDsTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
//        set the ScheduleParameter value to more than a month back from today's date. reason for this is to get the scheme activated a month back
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, -1, 0));

        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
//        1st raising to get the scheme active    
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
//        set the ScheduleParameter value to next scheduled raising date for the scheme after it went active
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
//        2nd raising for scheme        
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
//        RD the scheme's raising - 1st RD
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.Lapsed.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
            return false;
        }
//        do cash confirmation to get the scheme active again
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.formatStringDate(topThreeNextScheduledRaisingDatesForScheme.get(0)), false)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
//        update the DateUpdated date (also known as the Transaction Date) to the same as the DateReceived (also known as the Process/Payment date)
        if (!dao.updatePremiumCollectionItem(schemeNumber, env)) {
            System.err.println("[Error] Failed to update the DateUpdate field in the Premium Collections table");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
//        set the ScheduleParameter value to the 2nd raising date of the scheme after it went active
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(1));
//        3rd Raising of the scheme
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
//        RD the scheme's raising - 2nd RD        
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.Lapsed.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
            return false;
        }
//        do cash confirmation to get the scheme active again        
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.formatStringDate(topThreeNextScheduledRaisingDatesForScheme.get(1)), false)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
//        update the DateUpdated date (also known as the Transaction Date) to the same as the DateReceived (also known as the Process/Payment date)
        if (!dao.updatePremiumCollectionItem(schemeNumber, env)) {
            System.err.println("[Error] Failed to update the DateUpdate field in the Premium Collections table");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
//        set the ScheduleParameter value to the 3rd raising date of the scheme after it went active        
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(2));
//        4th Raising for scheme
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, topThreeNextScheduledRaisingDatesForScheme.get(2))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createDeleteNewTransactionLapsedSchemeTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
//        set the ScheduleParameter value to more than a month and 5 days back from today's date. reason for this is to get the scheme activated a month and 5 day back
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, -1, 0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
//        1st Daily Raising to get scheme active
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, -1, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
//        set the ScheduleParameter value to next scheduled raising date for the scheme after it went active
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//        2nd Daily Raising which will be used as the RD
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.Lapsed.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
            return false;
        }
//        set the ScheduleParameter value to scheme's next raising date
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(1));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//        3rd Daily Raising which will output that this scheme must be deleted from YM
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify Delete Transaction Entry In YMOutput File=============");
        if (!schemes.verifyDeleteTransactionEntryInYMOutputFile(parameter, env, topThreeNextScheduledRaisingDatesForScheme.get(1))) {
            System.err.println("[Error] Failed to complete Verify Delete Transaction Entry In YMOutputFile");
            return false;
        }
//        do cash confirmation to get the scheme active again
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.formatStringDate(topThreeNextScheduledRaisingDatesForScheme.get(1)), false)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
//        update the DateUpdated date (also known as the Transaction Date) to the same as the DateReceived (also known as the Process/Payment date)
        if (!dao.updatePremiumCollectionItem(schemeNumber, env)) {
            System.err.println("[Error] Failed to update the DateUpdate field in the Premium Collections table");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
//        set the ScheduleParameter value to next scheduled raising date for the scheme after it went active
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(2));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//        4th Daily Raising which will output a New Transaction as this scheme is re-instated
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, topThreeNextScheduledRaisingDatesForScheme.get(2))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createNewTransactionFirstPremCashConfirmationSecondPremViaRaisingTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.getDate(0, 0, 0), false)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
//        update the DateUpdated date (also known as the Transaction Date) to the same as the DateReceived (also known as the Process/Payment date)
        if (!dao.updatePremiumCollectionItem(schemeNumber, env)) {
            System.err.println("[Error] Failed to update the DateUpdate field in the Premium Collections table");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
//        set the ScheduleParameter value to next scheduled raising date for the scheme after it went active
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//        2nd Daily Raising which will be used as the RD
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, topThreeNextScheduledRaisingDatesForScheme.get(0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createNewTransactionDeleteFromYMwithSpecificRDreasonTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
//        1st Daily Raising to get scheme active
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
//        set the ScheduleParameter value to next scheduled raising date for the scheme after it went active
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
//        2nd Daily Raising which will be used as the RD
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.Lapsed.getSchemeStatusDescription(), "DEBIT NOT ALLOWED")) {
            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
            return false;
        }
//        do cash confirmation to get the scheme active again
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.formatStringDate(topThreeNextScheduledRaisingDatesForScheme.get(0)), false)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
//        update the DateUpdated date (also known as the Transaction Date) to the same as the DateReceived (also known as the Process/Payment date)
        if (!dao.updatePremiumCollectionItem(schemeNumber, env)) {
            System.err.println("[Error] Failed to update the DateUpdate field in the Premium Collections table");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
//        set the ScheduleParameter value to next scheduled raising date for the scheme after it went active
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(1));
//        2st Daily Raising to get scheme active
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createDeleteTransactionNewBusinessNonePaymentSchemeTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============New Business Non Payment Or Not Taken Scheme=============");
        if (!schemes.newBusinessNonPaymentOrNotTakenScheme(parameter, env, SchemeStatuses.NewBusinessNonPayment.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete New Business Non Payment Or Not Taken Scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(+1, 0, 0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify Delete Transaction Entry In YMOutput File=============");
        if (!schemes.verifyDeleteTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(+1, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createDeleteTransactionPaymentMethodTypeChangeDebitToCashTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Change Scheme Payment Method Type=============");
//        changing the payment method type to Cash
        parameter.setSchemePaymentMethodType("Cash M65");
        if (!schemes.changeSchemePaymentMethod(parameter, env, new String[]{"PaymentMethodType"})) {
            System.err.println("[Error] Failed to complete Change Scheme Payment Method Type");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createRandomDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify Delete Transaction Entry In YMOutput File=============");
        if (!schemes.verifyDeleteTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createDeleteTransactionSchemeLapsedNoArrearPaymentReceivedTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
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
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(1));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify Delete Transaction Entry In YMOutput File=============");
        if (!schemes.verifyDeleteTransactionEntryInYMOutputFile(parameter, env, topThreeNextScheduledRaisingDatesForScheme.get(1))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createDeleteTransactionSchemeCancelledStatusTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify New Transaction Entry In YMOutput File=============");
        if (!schemes.verifyNewTransactionEntryInYMOutputFile(parameter, env, seleniumDriver.getDateWithYearFist(0, 0, 0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Cancel Scheme=============");
        if (!schemes.cancelScheme(parameter, env)) {
            System.err.println("[Error] Failed to complete Cancel Scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Verify Delete Transaction Entry In YMOutput File=============");
        if (!schemes.verifyDeleteTransactionEntryInYMOutputFile(parameter, env, topThreeNextScheduledRaisingDatesForScheme.get(0))) {
            System.err.println("[Error] Failed to complete Verify New Transaction Entry In YMOutputFile");
            return false;
        }
        return true;
    }

    private Boolean createOversCashApplicationForAwaitingFirstPremiumTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create New Scheme Up To Be Assessed=============");
        if (!createNewSchemeUpToToBeAssessed(parameter, env)) {
            System.err.println("[Error] Failed to complete the creation of a new Scheme upto To Be Assessed status");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.getDate(0, 0, 0), true)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
        return true;
    }

    private Boolean createOversCashApplicationForNewBusinessNonPaymentTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, 0, 0));
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
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.formatStringDate(seleniumDriver.getDateAndTimeDashFormat(0, 0, 0, 0, 0, 0)), true)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
//        update the DateUpdated date (also known as the Transaction Date) to the same as the DateReceived (also known as the Process/Payment date)
        if (!dao.updatePremiumCollectionItem(schemeNumber, env)) {
            System.err.println("[Error] Failed to update the DateUpdate field in the Premium Collections table");
            return false;
        }
        return true;
    }

    private Boolean createOverCashApplicationNotTakenTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, -1, 0));
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
        if (!schemes.newBusinessNonPaymentOrNotTakenScheme(parameter, env, SchemeStatuses.NotTaken.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete New Business Non Payment Or Not Taken Scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Cash Confirmation=============");
        if (!schemes.doSchemeCashConfirmation(parameter, env, seleniumDriver.formatStringDate(seleniumDriver.getDateAndTimeDashFormat(0, -1, 0, 0, 0, 0)), true)) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
//        update the DateUpdated date (also known as the Transaction Date) to the same as the DateReceived (also known as the Process/Payment date)
        if (!dao.updatePremiumCollectionItem(schemeNumber, env)) {
            System.err.println("[Error] Failed to update the DateUpdate field in the Premium Collections table");
            return false;
        }
        return true;
    }

    private Boolean createUnderCashApplicationAndPMARLapsedTest(Parameter parameter, String env) throws Exception {
        schemes = new Schemes();
        quotes = new Quotes();
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, seleniumDriver.getDateWithYearFist(0, -4, 0));
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
//        here scheme must become active -- use activatingTheScheme
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Activating The Scheme=============");
        if (!schemes.activatingTheScheme(parameter, env, false)) {
            System.err.println("[Error] Failed to Activate the scheme");
            return false;
        }
//        set scheduleparameter to the next daily raising value of this scheme
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Set Schedule Parameter Value To Specific Value=============");
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        List<String> topThreeNextScheduledRaisingDatesForScheme = dao.getNextScheduledRaisingDateForScheme(env, parameter, schemeNumber, 3);
        seleniumDriver.setScheduleParameterValueToSpecificValue(env, topThreeNextScheduledRaisingDatesForScheme.get(0));
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Create Daily Raising And YMOutput File=============");
        if (!schemes.createDailyRaisingAndYMOutputFile(parameter, env)) {
            System.err.println("[Error] Failed to complete Create Daily Raising And YMOutputFile");
            return false;
        }
//        RD the scheme's raising - 1st RD
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Lapsed Or Lapse Pending Scheme=============");
        if (!schemes.lapsedOrLapsePendingScheme(parameter, env, SchemeStatuses.Lapsed.getSchemeStatusDescription(), "INSUFFICIENT FUNDS/NO FUNDS")) {
            System.err.println("[Error] Failed to complete Lapsed Or Lapse Pending Scheme");
            return false;
        }
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Do Scheme CashConfirmation with PMAR=============");
        if (!schemes.doSchemeCashConfirmationWithPMAR(parameter, env, seleniumDriver.formatStringDate(seleniumDriver.getDateAndTimeDashFormat(0, 0, 0, 0, 0, 0)))) {
            System.err.println("[Error] Failed to complete the Scheme Cash Confirmation with PMAR");
            return false;
        }
        return true;
    }

    /*  if the actual test failed then a screenshot will be taken of the failed page. It will zip the evidence folder
     *  it will update Quality Center with a failed message. 
     *  i the actual test passed it will do the same.    
     */
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
}

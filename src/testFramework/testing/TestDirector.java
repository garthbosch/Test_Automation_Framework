package testFramework.testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import testFramework.testing.testSuites.BaseClass;
import static testFramework.testing.testSuites.BaseClass.evidenceFolder;
import testFramework.testing.testSuites.CashBackDataSetup;
import testFramework.testing.testSuites.PremiumCollectionsTests;
import testFramework.testing.testSuites.QuotesTests;
import testFramework.testing.testSuites.SchemeStatusesTests;
import testFramework.testing.testSuites.SchemesTests;
import testFramework.utils.Parameter;
import testFramework.utils.ParameterHelper;
import testFramework.utils.ReportGenerator;
import testFramework.utils.SeleniumWebDriverUtils;

/**
 *
 * @author Garth Bosch
 */
public class TestDirector extends BaseClass {

    public TestDirector() {
//        testDataList = new ArrayList<>();
//        excelInputReader = new ExcelReaderUtility();
        browserType = appConfig.getBrowserType();
        seleniumDriver = new SeleniumWebDriverUtils(browserType);
    }

    public void runTests(String env, String qcTestPath, String qcTestSetName, String dataFile) throws Exception {
        List<Parameter> parms = ParameterHelper.readParameters(dataFile);
        reportGenerator = new ReportGenerator(dataFile, null);
//        this.generateReportDirectory();

        if (parms.size() < 1) {
            System.err.println("Test data object is empty - file not found or is empty");
        } else {
            // Each Test Case represents a test keyword found in the data file
            for (Parameter parameter : parms) {
                // Make sure browser is not null - could have thrown an exception and terminated
                CheckBrowserExists();
                // Check to see if the test case must be run in the file
                if (parameter.isRunTest()) {
                    this.redirectOutputStreams(parameter);
                    /*Schemes Tests*/
                    if (parameter.getTestCaseName().equals("addNewBSSPSchemeWithProductOptionAndMembersTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemesTests st = new SchemesTests();
                        reportGenerator.addResult(st.addNewBSSPSchemeWithProductOptionAndMembersTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("addNewBSSPSchemeWithQuoteProductOptionAndMembersTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemesTests st = new SchemesTests();
                        reportGenerator.addResult(st.addNewBSSPSchemeWithQuoteProductOptionAndMembersTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("addNewGFSSchemeWithQuoteProductOptionAndMembersTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemesTests st = new SchemesTests();
                        reportGenerator.addResult(st.addNewGFSSchemeWithQuoteProductOptionAndMembersTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("addNewGFSSchemeWithCustomisedQuoteProductOptionAndMembersTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemesTests st = new SchemesTests();
                        reportGenerator.addResult(st.addNewGFSSchemeWithCustomisedQuoteProductOptionAndMembersTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    /*Scheme Statuses Tests*/
                    if (parameter.getTestCaseName().equals("awaitingFirstPremiumSchemeStatusTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemeStatusesTests sst = new SchemeStatusesTests();
                        reportGenerator.addResult(sst.awaitingFirstPremiumSchemeStatusTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("cancelSchemeStatusTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemeStatusesTests sst = new SchemeStatusesTests();
                        reportGenerator.addResult(sst.cancelSchemeStatusTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("lapsePendingSchemeStatusTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemeStatusesTests sst = new SchemeStatusesTests();
                        reportGenerator.addResult(sst.lapsePendingSchemeStatusTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("lapsedSchemeStatusTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemeStatusesTests sst = new SchemeStatusesTests();
                        reportGenerator.addResult(sst.lapsedSchemeStatusTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("newBusinessNonPaymentSchemeStatusTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemeStatusesTests sst = new SchemeStatusesTests();
                        reportGenerator.addResult(sst.newBusinessNonPaymentSchemeStatusTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("notTakenSchemeStatusTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        SchemeStatusesTests sst = new SchemeStatusesTests();
                        reportGenerator.addResult(sst.notTakenSchemeStatusTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    /*Quotes Tests*/
                    if (parameter.getTestCaseName().equals("addNewBSSPCustomisedQuoteTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        QuotesTests qt = new QuotesTests();
                        reportGenerator.addResult(qt.addNewBSSPCustomisedQuoteTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("addNewGFSNormalQuoteTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        QuotesTests qt = new QuotesTests();
                        reportGenerator.addResult(qt.addNewGFSNormalQuoteTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("addNewGFSCustomisedQuoteTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        QuotesTests qt = new QuotesTests();
                        reportGenerator.addResult(qt.addNewGFSCustomisedQuoteTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    /*Premium Collections Tests*/
                    if (parameter.getTestCaseName().equals("newTransactionBrandNewSchemeTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.newTransactionBrandNewSchemeTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("newTransactionPaymentMethodTypeChangeCashToDebitTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.newTransactionPaymentMethodTypeChangeCashToDebitTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("deleteNewTransactionPaymentDayChangeTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.deleteNewTransactionPaymentDayChangeTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("newTransactionConsecutiveRDsTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.newTransactionConsecutiveRDsTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("deleteNewTransactionLapsedSchemeTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.deleteNewTransactionLapsedSchemeTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("newTransactionFirstPremCashConfirmationSecondPremViaRaisingTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.newTransactionFirstPremCashConfirmationSecondPremViaRaisingTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("newTransactionDeleteFromYMwithSpecificRDreasonTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.newTransactionDeleteFromYMwithSpecificRDreasonTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("deleteTransactionNewBusinessNonePaymentSchemeTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.deleteTransactionNewBusinessNonePaymentSchemeTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("deleteTransactionPaymentMethodTypeChangeDebitToCashTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.deleteTransactionPaymentMethodTypeChangeDebitToCashTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("deleteTransactionSchemeLapsedNoArrearPaymentReceivedTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.deleteTransactionSchemeLapsedNoArrearPaymentReceivedTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("deleteTransactionSchemeCancelledStatusTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.deleteTransactionSchemeCancelledStatusTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("oversCashApplicationForAwaitingFirstPremiumTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.oversCashApplicationForAwaitingFirstPremiumTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    // done by wendy  for oversCashApplicationForNewBusinessNonPaymentTest
                    if (parameter.getTestCaseName().equals("oversCashApplicationForNewBusinessNonPaymentTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.oversCashApplicationForNewBusinessNonPaymentTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    
                    // done by wendy  for overCashApplicationNotTakenTest
                    if (parameter.getTestCaseName().equals("overCashApplicationNotTakenTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.overCashApplicationNotTakenTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    
                    if (parameter.getTestCaseName().equals("underCashApplicationAndPMARLapsedTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        PremiumCollectionsTests pct = new PremiumCollectionsTests();
                        reportGenerator.addResult(pct.underCashApplicationAndPMARLapsedTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    
                    
                    /*Cash Back Data Setup*/
                    if (parameter.getTestCaseName().equals("gfsSchemeTwelveSuccessfulPremsTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.gfsSchemeTwelveSuccessfulPremsTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("bsspSchemeTwelveSuccessfulPremsTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.bsspSchemeTwelveSuccessfulPremsTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("gfsSchemeTwelvePremsNotConsecutivelyTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.gfsSchemeTwelvePremsNotConsecutivelyTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("bsspSchemeTwelvePremsNotConsecutivelyTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.bsspSchemeTwelvePremsNotConsecutivelyTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("gfsSchemeTwelveSuccessfulPremsPMsLessThan500Test")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.gfsSchemeTwelveSuccessfulPremsPMsLessThan500Test(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("bsspSchemeTwelveSuccessfulPremsPMsLessThan500Test")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.bsspSchemeTwelveSuccessfulPremsPMsLessThan500Test(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("gfsSchemeElevenSuccessfulPremsTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.gfsSchemeElevenSuccessfulPremsTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("bsspSchemeElevenSuccessfulPremsTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.bsspSchemeElevenSuccessfulPremsTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("gfsSchemeTwelveSuccessfulPremsCancelTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.gfsSchemeTwelveSuccessfulPremsCancelTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                    if (parameter.getTestCaseName().equals("bsspSchemeTwelveSuccessfulPremsCancelTest")) {
                        System.out.println("======================================================================================================");
                        System.out.println("==========Starting " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.out.println("======================================================================================================");
                        System.err.println("======================================================================================================");
                        System.err.println("==========Errors for " + parameter.getTestCaseName() + " - " + parameter.getTestCaseCode() + " Test Case==========");
                        System.err.println("======================================================================================================");
                        ensureNewBrowserInstance();
                        CashBackDataSetup cbds = new CashBackDataSetup();
                        reportGenerator.addResult(cbds.bsspSchemeTwelveSuccessfulPremsCancelTest(parms, env, qcTestPath, qcTestSetName));
                        continue;
                    }
                }
            }
            seleniumDriver.shutDown();
//        reportGenerator.generateTestReport();
//        reportEmailer = new TestReportEmailerUtility(reportGenerator.testResults, inputFile);         
//        reportEmailer.SendResultsEmail();
//        copyReportsDirectoryToNewtorkDrive();

//        this.flushOutputStreams();
        }
    }

    public void ensureNewBrowserInstance() {
        if (seleniumDriver.isDriverRunning()) {
            seleniumDriver.shutDown();
        }
        seleniumDriver.startDriver();
    }

    public void CheckBrowserExists() {
        if (seleniumDriver == null) {
            seleniumDriver = new SeleniumWebDriverUtils(browserType);
            seleniumDriver.startDriver();
        }
    }

    public void redirectOutputStreams(Parameter parameter) throws Exception {
        getEvidenceFolder(parameter);
        try {
            File evidenceDirectory = new File(evidenceFolder);
            File errorFile = new File(evidenceFolder + "\\" + seleniumDriver.generateDateTimeString() + "_OneFM_ErrorTest_Log.txt");
            File infoFile = new File(evidenceFolder + "\\" + seleniumDriver.generateDateTimeString() + "_OneFM_InfoTest_Log.txt");
            evidenceDirectory.mkdirs();
            PrintStream infoOutputStream = new PrintStream(infoFile);
            System.setOut(infoOutputStream);
            PrintStream errorOutputStream = new PrintStream(errorFile);
            System.setErr(errorOutputStream);
        } catch (FileNotFoundException ex) {
            System.err.println("[Error] Could not create log files - " + ex.getMessage());
        }
    }
}

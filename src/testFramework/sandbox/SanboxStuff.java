package testFramework.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import testFramework.enums.BrowserTypes;
import testFramework.testing.TestDirector;
import testFramework.testing.testSuites.BaseClass;
import testFramework.utils.ApplicationConfiguration;
import testFramework.utils.ExecutingExternalFiles;
import testFramework.utils.OneFMDAO;
import testFramework.utils.QCIntegration;
import testFramework.utils.SeleniumWebDriverUtils;

/**
 *
 * @author OM37389
 */
public class SanboxStuff extends BaseClass {

    private SanboxStuff() {
    }

    public static void main(String[] args) throws Exception {
//        retrieving the payment date for a scheme
//        try {
//            OneFMDAO dataRev = new OneFMDAO();
//            String paymentDate = dataRev.getPaymentDay(null, "DEV");
//            System.out.println("[Info] The payment date is: " + paymentDate);
//        } catch (Exception ex) {
//            System.err.println("[Error] Unable to retrieve the payment date - " + ex.getMessage());
//        }

////        running the workflow service
//        try {
//            ExecutingExternalFiles ex = new ExecutingExternalFiles();
//            Boolean isServiceRun = ex.runOneFMWorkflowService("DEV");
//            System.out.println("[Info] Did the service run successfully: " + isServiceRun);
//        } catch (Exception ex) {
//            System.err.println("[Error] Error occurred while running the workflow service - " + ex.getMessage());
//        }
//        
//        get the YMRaising date from YMRaisingSchedule table
//        try {
//            OneFMDAO daoData = new OneFMDAO();
//            seleniumDriver = new SeleniumWebDriverUtils(BrowserTypes.Chrome);
//            String ymRaisingDate = 
//                    daoData.checkIfSelectedParameterValueExistOnYMRaisingScheduleTable("DEV", seleniumDriver.getDateWithYearFist(0, -1, 0));
//            System.out.println("[Info] The YMRaisingDate is: " + ymRaisingDate);
//        } catch (Exception ex) {
//            System.err.println("[Error] Unable to retrieve the YMRaisingDate - " + ex.getMessage());
//        }
//        create daily raising and process ym output file        
//        try {
//            ExecutingExternalFiles ex = new ExecutingExternalFiles();
//            OneFMDAO db = new OneFMDAO();
////            to run the daily raising for specific date change this value
////            db.setScheduleParameterValue("DEV", "2014-10-15");
////            Boolean drJob = ex.runDailyRaisingJob("DEV");
////            Boolean ymJob = ex.runProcessOuputYMfile("DEV");
//            Boolean cancelJob = ex.runCancelScheme("DEV");
//        } catch (Exception ex) {
//            System.err.println("[Error] An error occurred while running the job - " + ex.getMessage());
//        }
//        try {
//            String amountDue = "71.40";
//            System.out.println("Amoun Due before change: " + amountDue);
//            double incAmountDue = Double.valueOf(amountDue) + 50;
//            System.out.printf("Amoun  change int two decimal place %.2f: ", incAmountDue);
//        } catch (Exception ex) {
//            System.err.println("[Error] FAILED");
//        }
//        try {
////            String date = "2014-12-20 00:00:00";
//            SeleniumWebDriverUtils sm = new SeleniumWebDriverUtils(null);
//            String date = sm.getDateAndTimeDashFormat(0, 0, 0, 0, 0, 0);
//            System.out.println("Today's date: " + date);
//            String getDate = sm.formatStringDate(date);
//            System.out.print("The date is: " + getDate);
//        } catch (Exception ex) {
//            System.err.println("[Error] FAILED");
//        }
//        try {
//            OneFMDAO dao = new OneFMDAO();
//            Boolean update = dao.updatePremiumCollectionItem("BS0010104000000", "DEV");
//            System.out.print("True/ False: " + update);
//        } catch (Exception ex) {
//            System.err.println("[Error] FAILED");
//        }
    }
}
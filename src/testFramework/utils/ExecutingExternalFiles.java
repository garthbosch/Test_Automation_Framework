package testFramework.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import testFramework.testing.testSuites.BaseClass;

/**
 *
 * @author Garth Bosch
 */
public class ExecutingExternalFiles extends BaseClass {

    public ExecutingExternalFiles() {
    }

    public boolean runDailyRaisingJob(String env) throws Exception {
        try {
//            OPTION 1
            System.out.println("[Info]**********Starting to Run the Daily Raising.....Processing....**********");
            String executable = "C:\\Projects\\OneFM\\trunk\\OneFM.Sandbox\\bin\\Debug\\OneFM.Sandbox.exe";
            Process processDailyRaising = new ProcessBuilder(executable, "DailyRaisings").start();
            InputStream isDR = processDailyRaising.getInputStream();
            InputStreamReader isrDR = new InputStreamReader(isDR);
            BufferedReader brDR = new BufferedReader(isrDR);
            System.out.println("[Info] End of Running the Daily Raisings: " + brDR.readLine());
            processDailyRaising.destroy();

////            OPTION 2
//            System.out.println("[Info]**********Starting to Run the Daily Raising.....Processing....**********");
//            Process processDR = Runtime.getRuntime().exec("C:\\Projects\\OneFM\\trunk\\OneFM.Sandbox\\bin\\Debug\\OneFM.Sandbox.exe DailyRaisings");
//            BufferedReader inputDR = new BufferedReader(new InputStreamReader(processDR.getInputStream()));
//            for (int i = 0; i < inputDR.read(); i++) {
//                System.out.println("[Info] End of Running the Daily Raisings: " + inputDR.readLine());
//                break;
//            }
////            int exitValDR = processDR.waitFor();
////            System.err.println("[Error] Exited with error code " + exitValDR);
//            processDR.destroy();
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to run the Daily Raisings Job - " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean runProcessOuputYMfile(String env) throws Exception {
        try {
            System.out.println("[Info=============Getting the default Schedule Parameter value for id 4=============");
            String defaultYMoutputLocation = dao.getYMoutputLocation(env);
            System.out.println("[Info=============Setting the Schedule Parameter value for id 4 to a new value=============");
            if (!dao.setYMoutputLocation(env, ApplicationConfiguration.getYmOutputLocation())) {
                return false;
            }
            System.out.println("[Info]**********Starting to Run the Process YMOutput.....Processing....**********");
            Process processYM = Runtime.getRuntime().exec("C:\\Projects\\OneFM\\trunk\\OneFM.Sandbox\\bin\\Debug\\OneFM.Sandbox.exe ProcessYMOutput");
            BufferedReader inputYM = new BufferedReader(new InputStreamReader(processYM.getInputStream()));
            System.out.println("[Info] End of Running the Process YMOutput: " + inputYM.readLine());
            System.out.println("[Info=============Setting the Schedule Parameter value for id 4 to the default value=============");
            if (!dao.setYMoutputLocation(env, defaultYMoutputLocation)) {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to run the Process YM Output Job - " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean runCancelScheme(String env) throws Exception {
        try {
            System.out.println("[Info]**********Starting to Run the Cancel Scheme Job.....Processing....**********");
            Process processCS = Runtime.getRuntime().exec("C:\\Projects\\OneFM\\trunk\\OneFM.Sandbox\\bin\\Debug\\OneFM.Sandbox.exe CancelScheme");
            BufferedReader inputCS = new BufferedReader(new InputStreamReader(processCS.getInputStream()));
            System.out.println("[Info] End of Running the Cancel Scheme Job: " + inputCS.readLine());
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to run the Cancel Scheme Job - " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkYMOutputFileForTransactions(String transactionType, String schemeNumber, String schemePremiumsDue, Parameter parameter) {
        try {
            File file = new File(ApplicationConfiguration.getYmOutputLocation());
            String dataFromYmOutputFile = IOUtils.readFileIntoString(file);
            String premiumAndDeductDay = schemePremiumsDue /*+ parameter.getSchemePaymentMethodPaymentDay() + "MPREMLIMYGS"*/;
            if (transactionType.equalsIgnoreCase("New")) {
                if (dataFromYmOutputFile.contains("NOMG" + schemeNumber) && dataFromYmOutputFile.contains(premiumAndDeductDay)) {
                    System.out.println("[Info]**********************************************The New Transaction was successfully captured in the YMOutput file (NOMG" + schemeNumber + " and Premium " + premiumAndDeductDay + ")*********************************************************************");
                    System.out.println(dataFromYmOutputFile);
                    System.out.println("[Info]**********************************************End Of String*******************************************************************************************************************************************************************************************");
                } else {
                    System.err.println("[Error]**********************************************The YMoutput file does not contain the new entry (NOMG" + schemeNumber + " and Premium " + premiumAndDeductDay + ")**************************************************************************************");
                    System.err.println(dataFromYmOutputFile);
                    System.err.println("[Error]**********************************************End Of String******************************************************************************************************************************************************************************************");
                    return false;
                }
            }
            if (transactionType.equalsIgnoreCase("Delete")) {
                if (dataFromYmOutputFile.contains("DOMG" + schemeNumber) && dataFromYmOutputFile.contains(premiumAndDeductDay)) {
                    System.out.println("[Info]**********************************************The Delete Transaction was successfully captured in the YMOutput file (DOMG" + schemeNumber + " and Premium " + premiumAndDeductDay + ")*********************************************************************");
                    System.out.println(dataFromYmOutputFile);
                    System.out.println("[Info]**********************************************End Of String*******************************************************************************************************************************************************************************************");
                } else {
                    System.err.println("[Error]**********************************************The YMoutput file does not contain the new entry (DOMG" + schemeNumber + " and Premium " + premiumAndDeductDay + ")**************************************************************************************");
                    System.err.println(dataFromYmOutputFile);
                    System.err.println("[Error]**********************************************End Of String******************************************************************************************************************************************************************************************");
                    return false;
                }
            }
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to retrieve ymoutput file and reading the values - " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean runOneFMWorkflowService(String env) {
        try {
            System.out.println("[Info]**********Starting to Run the OneFM Workflow Service.....Processing....**********");
//            String executable = "C:\\Projects\\OneFM\\trunk\\OneFM.Sandbox\\bin\\Debug\\OneFM.Sandbox.exe";
//            Process processOneFMWorkflow = new ProcessBuilder(executable, "OneFMWorkflow").start();
//            InputStream isOFMW = processOneFMWorkflow.getInputStream();
//            InputStreamReader isrOFMW = new InputStreamReader(isOFMW);
//            BufferedReader brOFMW = new BufferedReader(isrOFMW);
//            System.out.println("[Info] End of Running the OneFM Workflow Service: " + brOFMW.readLine());
//            if (brOFMW.readLine().contains("Schedule Not Found")) {
//                System.err.println("[Error] There were NO Schedules Found");
//                processOneFMWorkflow.destroy();
//                return false;
//            } else {
//                System.out.println("[Info] End of Running the OneFM Workflow Service: " + brOFMW.readLine());
//                processOneFMWorkflow.destroy();
//                return true;
//            }

            Process oneFMWorkflow = Runtime.getRuntime().exec("C:\\Projects\\OneFM\\trunk\\OneFM.Sandbox\\bin\\Debug\\OneFM.Sandbox.exe OneFMWorkflow");
            BufferedReader inputOneFMWorkflow = new BufferedReader(new InputStreamReader(oneFMWorkflow.getInputStream()));
            System.out.println("[Info] Returned from the line: " + inputOneFMWorkflow.readLine());
            if (inputOneFMWorkflow.readLine().contains("Schedule Not Found")) {
                System.err.println("[Error] There were NO Schedules Found - " + inputOneFMWorkflow.readLine());
                return false;
            } else {
                System.out.println("[Info] End of Running the OneFM Workflow Service: " + inputOneFMWorkflow.readLine());
                return true;
            }
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to run the OneFM Workflow Service - " + ex.getMessage());
            return false;
        }
    }

    public void openDownloadedAgeProfileFile() {

    }

}

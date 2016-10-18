package testFramework.utils;

import com.mercury.qualitycenter.otaclient.*;
import com4j.Com4jObject;
import java.io.File;
import testFramework.testing.testSuites.BaseClass;

/**
 *
 * @author Garth Bosch
 */
public class QCIntegration extends BaseClass {

    public boolean updateTestOnQC(Parameter parameter, String evidenceFolder, String qcTestLabPath, String qcTestSetName, boolean passedOrFailed) throws Exception {
        System.out.println(seleniumDriver.generateDateTimeString() + "=============Updating Quality Center=============");
        ITDConnection connection = qcConnectToFM();
        String testLabPath = qcTestLabPath;
        String testSetName = qcTestSetName;
        ITestSetTreeManager qcTestLabTestSetTreeManager;
        ITestSetFolder qcTestLabTestSetFolder = null;
//        using com4j to interact with QC
        try {
            qcTestLabTestSetTreeManager = (connection.testSetTreeManager()).queryInterface(ITestSetTreeManager.class);
            qcTestLabTestSetFolder = (qcTestLabTestSetTreeManager.nodeByPath(testLabPath)).queryInterface(ITestSetFolder.class);
        } catch (Exception ex) {
            System.err.println("[Error] Node \"" + testLabPath + "\" not found! - " + ex.getMessage());
            return false;
        }

        IList tests = qcTestLabTestSetFolder.findTestSets(null, true, null);

        Boolean existTestSet = false;
        for (int i = 1; i <= tests.count(); i++) {
            try {
                Com4jObject comObj = (Com4jObject) tests.item(i);
                ITestSet testSet = comObj.queryInterface(ITestSet.class);
                if (testSetName.equalsIgnoreCase(testSet.name())) {
                    existTestSet = true;
//                    update the specific test set
                    if (!executeTestSet(existTestSet, testSet, evidenceFolder, testLabPath, testSetName, parameter, connection, passedOrFailed)) {
                        System.err.println("[Error] The Test Set Execution failed");
                        return false;
                    }
                    continue;
                }
            } catch (Exception ex) {
                System.err.println("[Error] An error occurred while attempting to executing the Test Set \"" + testSetName + "\" on Quality Center - " + ex.getMessage());
                return false;
            }
        }
        qcDisconnect(connection);
        return true;
    }

    private boolean executeTestSet(Boolean existTestSet, ITestSet testSet, String evidenceFolder, String testLabPath, String testSetName, Parameter parameter, ITDConnection connection, boolean passedOrFailed) {
        if (existTestSet) {
            System.out.println("[Info] Test Set Name \"" + testSetName + "\" Exist in the Path \"" + testLabPath + "\"");
            IBaseFactory obj2 = testSet.tsTestFactory().queryInterface(IBaseFactory.class);
            IList tstestlist = obj2.newList("");

            System.out.println("[Info] Number of test instances: " + tstestlist.count());
            boolean testcaseExist = false;
            for (Com4jObject test : tstestlist) {
                ITSTest tstest = test.queryInterface(ITSTest.class);
                if (parameter.getQcInstanceName().equalsIgnoreCase(tstest.testName())) {
                    testcaseExist = true;
//                update all the test instances
                    if (!executeTestInstance(testcaseExist, tstest, evidenceFolder, testLabPath, testSetName, connection, parameter, passedOrFailed)) {
                        System.err.println("[Error] The Test Instance Execution failed");
                        return false;
                    }
                    continue;
                }
            }
            System.out.println("[Info] Test Set Name is: \"" + testSetName + "\" and the Path is: \"" + testLabPath + "\"");
            return true;
        } else {
            System.err.println("[Error] Test Set Name \"" + testSetName + "\" does not Exist in the Path \"" + testLabPath + "\"");
            return false;
        }
    }

    private boolean executeTestInstance(boolean testcaseExist, ITSTest qcTestLabTestCase, String evidenceFolder, String testLabPath, String testSetName, ITDConnection connection, Parameter parameter, boolean passedOrFailed) {
        if (testcaseExist) {
            System.out.println("[Info] Testcase \"" + qcTestLabTestCase.testName() + "\" Found under the Test Set Name \"" + testSetName + "\" and the Path \"" + testLabPath + "\"");
            IRunFactory sRunFactory = (qcTestLabTestCase.runFactory().queryInterface(IRunFactory.class));
            IRun sRun = sRunFactory.addItem("Selenium Automated Test Run").queryInterface(IRun.class);
            String status;
            String actualResult;
            if (passedOrFailed) {
                status = "Passed";
                actualResult = "Expected result was met.";
            } else {
                status = "Failed";
                actualResult = "Expected result was NOT met.";
            }
            sRun.status(status);
            sRun.copyDesignSteps();
            sRun.copyStepsToTest();
            String attachmentLocation = new File(evidenceFolder).getParent() + "\\";
            String attachmentFileName = getFileName(evidenceFolder);
//                Normal Attachment Route
            IExtendedStorage sRunStorage = sRun.extendedStorage().queryInterface(IExtendedStorage.class);
            sRunStorage.clientPath(attachmentLocation);
            IAttachmentFactory attachFactoryRun = sRun.attachments().queryInterface(IAttachmentFactory.class);
            IAttachment attachment = attachFactoryRun.addItem(attachmentFileName).queryInterface(IAttachment.class);
            attachment.description("Screenshots attached from Selenium Tests.");
            attachment.post();
            System.out.println("[Info] File Location = " + attachment.fileName());
            IExtendedStorage attachStorage = attachment.attachmentStorage().queryInterface(IExtendedStorage.class);
            attachStorage.clientPath(sRunStorage.clientPath());
            System.out.println("[Info] Client Path = " + attachStorage.clientPath());
            attachStorage.save(attachmentFileName, true);
            sRun.post();
            IBaseFactory sStepFactory = sRun.stepFactory().queryInterface(IBaseFactory.class);
            IList sStepList = sStepFactory.newList("");
            for (Com4jObject sStepObj : sStepList) {
                IStep sStep = sStepObj.queryInterface(IStep.class);
                sStep.field("ST_STATUS", status);
                sStep.field("ST_ACTUAL", actualResult);
                sStep.post();
                continue;
            }
            sendEmailToUser(connection, parameter, passedOrFailed);
            System.out.println("[Info] Test Case \"" + qcTestLabTestCase.testName() + "\" successfully updated");
            return true;
        } else {
            qcTestLabTestCase = null;
            System.err.println("[Error] Testcase \"" + qcTestLabTestCase.testName() + "\" not Found under the Test Set Name \"" + testSetName + "\" and the Path \"" + testLabPath + "\"");
            return false;
        }
    }

    private ITDConnection qcConnectToFM() {
        try {
            ITDConnection connection = ClassFactory.createTDConnection();
            connection.initConnectionEx(ApplicationConfiguration.getQcURL());
            System.out.println("[Info] QC Connection is " + (connection.projectConnected() ? "CLOSED" : "OPEN"));
            connection.connectProjectEx(ApplicationConfiguration.getQcDomainName(), ApplicationConfiguration.getQcProjectName(), ApplicationConfiguration.getQcUsername(), ApplicationConfiguration.getQcPassword());
            System.out.println("[Info] Successful logon to QC!!!");
//        System.out.println("Database Name: " + connection.dbName());
//        System.out.println("Server Name: " + connection.serverName());
//        System.out.println("Database Type: " + connection.dbType());
//        System.out.println("Server URL: " + connection.serverURL());
            return connection;
        } catch (Exception ex) {
            System.err.println("[Error] There a problem with connecting to or loggin onto Quality Center - " + ex.getMessage());
            return null;
        }
    }

    private boolean qcDisconnect(ITDConnection connection) {
        try {
            connection.disconnectProject();
            if (!connection.projectConnected()) {
                System.out.println("[Info] CLOSED");
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] QC Connection is " + (connection.projectConnected() ? "STILL OPEN" : "CLOSED"));
            return false;
        }
    }

    private void sendEmailToUser(ITDConnection connection, Parameter parameter, boolean passedOrFailed) {
        if (passedOrFailed) {
            connection.sendMail("GBosch@oldmutual.com", null, "Automated Regression Tests - QC", "Good Day Mr. Bosch, \n \n This is just to inform you that the testcase \'" + parameter.getQcInstanceName() + "\' was successfully executed. \n \n Regards, \n Selenium Automation", null, null);
        } else {
            connection.sendMail("GBosch@oldmutual.com", null, "Automated Regression Tests - QC", "Good Day Mr. Bosch, \n \n This is just to inform you that the testcase \'" + parameter.getQcInstanceName() + "\' was NOT successfully executed. \n \n Regards, \n Selenium Automation", null, null);
        }
    }

    private String getFileName(String evidenceFolder) {
        int beginIndex = evidenceFolder.lastIndexOf("\\") + 1;
        return evidenceFolder.substring(beginIndex) + ".zip";
    }
}

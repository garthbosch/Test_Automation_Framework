package testFramework.testing.testSuites;

import testFramework.utils.OneFMDAO;
import testFramework.testing.testPages.Quotes;
import testFramework.testing.testPages.Schemes;
import testFramework.utils.ExecutingExternalFiles;
import testFramework.utils.IOUtils;
import testFramework.utils.Parameter;
import testFramework.utils.QCIntegration;
import testFramework.utils.StoringScreenshotsAndErrorLogs;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import testFramework.enums.BrowserTypes;
import testFramework.utils.ApplicationConfiguration;
import testFramework.utils.ReportGenerator;
import testFramework.utils.SeleniumWebDriverUtils;

/**
 *
 * @author Garth Bosch
 */
public class BaseClass {

    public String filename = null;
    public String quoteNumber = null;
    public String schemeNumber = null;
    public String schemePremiumsDue = null;
    public String schemePremiumsRaisedFor = null;
    public static String evidenceFolder = null;
    public String errorLogFolder = null;
    public String zippedEvidenceFolder = null;
    public Component frame = null;
    public Exception ex = null;
    public Schemes schemes;
    public Quotes quotes;
    public static StoringScreenshotsAndErrorLogs scrnShotsandMsgs = new StoringScreenshotsAndErrorLogs();
    public static OneFMDAO dao = new OneFMDAO();
    public static QCIntegration qcIntegration = new QCIntegration();
    public static ExecutingExternalFiles executeEF = new ExecutingExternalFiles();
    private DateTime startTime, endTime;
    private Duration testDuration;
    public BrowserTypes browserType;
    public static SeleniumWebDriverUtils seleniumDriver;
    public static ApplicationConfiguration appConfig = new ApplicationConfiguration();
    public static String testSetName = null;
    public static ReportGenerator reportGenerator;

    public BaseClass() {
        System.setProperty("java.awt.headless", "true");
    }

    public void setStartTime() {
        this.startTime = new DateTime();
    }

    public long getTotalExecutionTime() {
        this.endTime = new DateTime();
        testDuration = new Duration(this.startTime, this.endTime);
        return testDuration.getStandardSeconds();
    }

    protected void getEvidenceFolder(Parameter parameter) throws Exception {
        evidenceFolder = getFolder(parameter);
    }

    protected void getErrorLogFolder(Parameter parameter) throws Exception {
        errorLogFolder = getErrorFolder(parameter);
    }

    protected boolean zipEvidenceFolder() throws Exception {
        if (!IOUtils.zipDirectory(evidenceFolder)) {
            System.err.println("[Error] zipEvidenceFolder was not successfully executed");
            return false;
        }
        return true;
    }

//===========================================================================================================================================================//       
    private String getFolder(Parameter parameter) throws IOException {
        String getFolderPath = new File(ApplicationConfiguration.getResultsReportFileDirectory() + "\\" + parameter.getScreenshotsLocation()).getPath();
        if (getFolderPath == null) {
            getFolderPath = Runtime.getRuntime().exec("mkdirs " + ApplicationConfiguration.getResultsReportFileDirectory() + "\\" + parameter.getScreenshotsLocation()).toString();
            return getFolderPath;
        }
        return getFolderPath;
    }

    private String getErrorFolder(Parameter parameter) throws IOException {
        String getFolderPath = new File(ApplicationConfiguration.getResultsReportFileDirectory() + "\\" + parameter.getScreenshotsLocation()).getPath();
        if (getFolderPath == null) {
            getFolderPath = Runtime.getRuntime().exec("mkdirs " + ApplicationConfiguration.getResultsReportFileDirectory() + "\\" + parameter.getScreenshotsLocation()).toString();
            return getFolderPath;
        }
        return getFolderPath;
    }
}

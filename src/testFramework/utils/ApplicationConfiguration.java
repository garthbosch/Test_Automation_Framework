/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testFramework.utils;

import testFramework.enums.BrowserTypes;
import testFramework.enums.Environments;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Garth Bosch
 */
public final class ApplicationConfiguration {

    private static String resultsReportFileDirectory;
    private static BrowserTypes browserType;
    private static String browserTypeConfig;
    private static int waitTimeout;
    private static String mailingList;
    private static String emailSender;
    private static String emailHost;
    private static String existingMembersDATAFile;
    private static String newMembersDATAFile;
    private static String quotesDATAFile;
    private static String schemesDATAFile;
    private static String schemeStatusDATAFile;
    private static String premiumCollectionsDATAFile;
    private static String cashBackDATAFile;
    private static String testEnvironment;
    public static Environments environment;
    public static String environmentConfig;
    private static String devOneFMurl;
    private static String qaOneFMurl;
    private static String prodOneFMurl;
    private static String qcURL;
    private static String dbDriverName;
    private static String dbDEVurl;
    private static String dbQAurl;
    private static String qcUsername;
    private static String qcPassword;
    private static String qcDomainName;
    private static String qcProjectName;
    private static String qcTestSetFolerPathRelease7_0_0;
    private static String qcTestSetName;
    private static String qcTestSetNameAdhoc;
    private static String ymOutputLocation;

    public Properties appConfig;
    private final String appConfigFilePath = "appConfig.properties";

    public static String getResultsReportFileDirectory() {
        return resultsReportFileDirectory;
    }

    public static BrowserTypes getBrowserType() {
        return browserType;
    }

    public static int getWaitTimeout() {
        return waitTimeout;
    }

    public static String[] getMailingList() {
        if (mailingList.contains(";")) {
            return mailingList.split(";");
        } else if (mailingList.contains(",")) {
            return mailingList.split(",");
        }

        return mailingList.split("");
    }

    public static String getEmailSender() {
        return emailSender;
    }

    public static String getEmailHost() {
        return emailHost;
    }

    public static String getExistingMembersDATAFile() {
        return existingMembersDATAFile;
    }

    public static String getNewMembersDATAFile() {
        return newMembersDATAFile;
    }

    public static String getQuotesDATAFile() {
        return quotesDATAFile;
    }

    public static String getSchemesDATAFile() {
        return schemesDATAFile;
    }

    public static String getSchemeStatusDATAFile() {
        return schemeStatusDATAFile;
    }
    
    public static String getPremiumCollectionsDATAFile() {
        return premiumCollectionsDATAFile;
    }
    
    public static String getCashBackDATAFile() {
        return cashBackDATAFile;
    }

    public static String getTestEnvironment() {
        return testEnvironment;
    }

    public static Environments getEnvironment() {
        return environment;
    }

    public static String getDevOneFMurl() {
        return devOneFMurl;
    }

    public static String getQaOneFMurl() {
        return qaOneFMurl;
    }

    public static String getProdOneFMurl() {
        return prodOneFMurl;
    }

    public static String getQcURL() {
        return qcURL;
    }

    public static String getDbDriverName() {
        return dbDriverName;
    }

    public static String getDbDEVurl() {
        return dbDEVurl;
    }

    public static String getDbQAurl() {
        return dbQAurl;
    }

    public static String getQcUsername() {
        return qcUsername;
    }

    public static String getQcPassword() {
        return qcPassword;
    }

    public static String getQcDomainName() {
        return qcDomainName;
    }

    public static String getQcProjectName() {
        return qcProjectName;
    }

    public static String getQcTestSetFolerPathRelease7_0_0() {
        return qcTestSetFolerPathRelease7_0_0;
    }

    public static String getQcTestSetName() {
        return qcTestSetName;
    }

    public static String getQcTestSetNameAdhoc() {
        return qcTestSetNameAdhoc;
    }

    public Properties getAppConfig() {
        return appConfig;
    }

    public String getAppConfigFilePath() {
        return appConfigFilePath;
    }

    public static String getYmOutputLocation() {
        return ymOutputLocation;
    }
    
    public ApplicationConfiguration() {
        try {
            loadConfigurationSettings();
        } catch (Exception e) {
            // One or more of the appConfig values could not be found in the config file - 
            // Reload default values and read from file. 
            generateDefaultConfigurationFile();
            loadExistingConfigurationFile();
            loadConfigurationSettings();
        }
    }

    private void loadConfigurationSettings() {
        if (!loadExistingConfigurationFile()) {
            generateDefaultConfigurationFile();
        }
        try {
            resultsReportFileDirectory = appConfig.getProperty("resultsReportFileDirectory");
            browserType = retrieveBrowserType();
            waitTimeout = Integer.parseInt(appConfig.getProperty("waitTimeout"));
            mailingList = appConfig.getProperty("mailingList");
            emailSender = appConfig.getProperty("emailSender");
            emailSender = appConfig.getProperty("emailSender");
            existingMembersDATAFile = appConfig.getProperty("existingMembersDATAFile");
            newMembersDATAFile = appConfig.getProperty("newMembersDATAFile");
            quotesDATAFile = appConfig.getProperty("quotesDATAFile");
            schemesDATAFile = appConfig.getProperty("schemesDATAFile");
            schemeStatusDATAFile = appConfig.getProperty("schemeStatusDATAFile");
            premiumCollectionsDATAFile = appConfig.getProperty("premiumCollectionsDATAFile");
            cashBackDATAFile = appConfig.getProperty("cashBackDATAFile");
            testEnvironment = appConfig.getProperty("testEnvironment");
            devOneFMurl = appConfig.getProperty("devOneFMurl");
            qaOneFMurl = appConfig.getProperty("qaOneFMurl");
            prodOneFMurl = appConfig.getProperty("prodOneFMurl");
            qcURL = appConfig.getProperty("qcURL");
            dbDriverName = appConfig.getProperty("dbDriverName");
            dbDEVurl = appConfig.getProperty("dbDEVurl");
            dbQAurl = appConfig.getProperty("dbQAurl");
            qcUsername = appConfig.getProperty("qcUsername");
            qcPassword = appConfig.getProperty("qcPassword");
            qcDomainName = appConfig.getProperty("qcDomainName");
            qcProjectName = appConfig.getProperty("qcProjectName");
            qcTestSetFolerPathRelease7_0_0 = appConfig.getProperty("qcTestSetFolerPathRelease7_0_0");
            qcTestSetName = appConfig.getProperty("qcTestSetName");
            qcTestSetNameAdhoc = appConfig.getProperty("qcTestSetNameAdhoc");
            ymOutputLocation = appConfig.getProperty("ymOutputLocation");

            environment = retrieveEnvironment();

        } catch (Exception e) {
            System.out.println("[Error] Loading application configuration...see stack trace:");
            e.printStackTrace();
        }
    }

    private Environments retrieveEnvironment() {
        environmentConfig = appConfig.getProperty("testEnvironment");
        return environmentConfig.contains("DEV") ? Environments.DEV : (environmentConfig.contains("QA") ? Environments.QA
                : (environmentConfig.contains("PROD") ? Environments.Prod : Environments.DEV));
    }

    private BrowserTypes retrieveBrowserType() {
        browserTypeConfig = appConfig.getProperty("browserType");
        return browserTypeConfig.contains("IE") ? BrowserTypes.IE : (browserTypeConfig.contains("FireFox") ? BrowserTypes.FireFox
                : (browserTypeConfig.contains("Chrome") ? BrowserTypes.Chrome : (browserTypeConfig.contains("HTML") ? BrowserTypes.HTML
                : BrowserTypes.Chrome)));
    }

    private void generateDefaultConfigurationFile() {
        try {
            appConfig = new Properties();
            appConfig.setProperty("resultsReportFileDirectory", "TestResultReports");
            appConfig.setProperty("browserType", "Chrome");
            appConfig.setProperty("waitTimeout", "40");
            appConfig.setProperty("mailingList", "gbosch@oldmutual.com");
            appConfig.setProperty("emailSender", "gbosch@gmail.com");
            appConfig.setProperty("emailHost", "smtp.gmail.com");
            appConfig.setProperty("existingMembersDATAFile", "testDATA\\existingMembersDATA.csv");
            appConfig.setProperty("newMembersDATAFile", "testDATA\\newMembersDATA.csv");
            appConfig.setProperty("quotesDATAFile", "testDATA\\quotesDATA.csv");
            appConfig.setProperty("schemesDATAFile", "testDATA\\schemesDATA.csv");
            appConfig.setProperty("schemeStatusDATAFile", "testDATA\\schemeStatusDATA.csv");
            appConfig.setProperty("premiumCollectionsDATAFile", "testDATA\\premiumCollectionsDATA.csv");
            appConfig.setProperty("cashBackDATAFile", "testDATA\\cashBackDATA.csv");
            appConfig.setProperty("testEnvironment", "DEV");
            appConfig.setProperty("devOneFMurl", "http://omgs.intranet.dev/OneFM/");
            appConfig.setProperty("qaOneFMurl", "http://omgs.intranet.qai/OneFM/");
            appConfig.setProperty("prodOneFMurl", "No Access!!!");
            appConfig.setProperty("qcURL", "http://10.109.2.169:8080/qcbin");
            appConfig.setProperty("dbDriverName", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            appConfig.setProperty("dbDEVurl", "jdbc:sqlserver://omdsqv051\\dev:63106;databaseName=RMM_BSSP_OneFM_App;integratedSecurity=true");
            appConfig.setProperty("dbQAurl", "jdbc:sqlserver://omnsqv051\\qa:54452;databaseName=RMM_BSSP_OneFM_App;integratedSecurity=true");
            appConfig.setProperty("qcUsername", "Bosch Garth");
            appConfig.setProperty("qcPassword", "mb@270613");
            appConfig.setProperty("qcDomainName", "GROUPSCHEMES");
            appConfig.setProperty("qcProjectName", "Foundation_Market");
            appConfig.setProperty("qcTestSetFolerPathRelease7_0_0", "Root\\Foundation_Market\\Release7.0.0_20-08-2014\\Dev Environment\\Regression Testing");
            appConfig.setProperty("qcTestSetName", "Automation Testing");
            appConfig.setProperty("qcTestSetNameAdhoc", "Adhoc Tests");
            appConfig.setProperty("ymOutputLocation", "TestResultReports\\Premium Collections\\YMOutput\\ymoutput.txt");

            appConfig.store(new FileOutputStream(appConfigFilePath), null);

        } catch (Exception ex) {
            System.err.println("[Error] Error Loading default configuration...see stack trace: " + ex.getMessage());
        }
    }

    private boolean loadExistingConfigurationFile() {
        try {
            if (appConfig == null) {
                appConfig = new Properties();
            }
            appConfig.load(new FileInputStream(appConfigFilePath));
            System.out.println("[Info] Successfully loaded Config File values");
            return true;
        } catch (Exception e) {
            System.out.println("[Warning] Configuration file not found, reverting to default configuration...see stack trace:");
            e.printStackTrace();
            System.out.println("[Warning] Loading default configuration......");
            return false;
        }
    }
}

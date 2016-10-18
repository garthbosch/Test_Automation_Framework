///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package testFramework.utils;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Properties;
//import java.util.UUID;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
//import testFramework.testing.testOutcome.TestResult;
//import testFramework.testing.testSuites.BaseClass;
//
///**
// *
// * @author fnell
// */
//public class TestReportEmailerUtility extends BaseClass {
//
//    ApplicationConfig appConfig = new ApplicationConfig();
//
//    List<TestResult> testResults;
//
//    String[] emailRecipients;
//
//    String emailSender;
//    String emailSubject;
//    String emailHost;
//    String inputFilePath;
//
//    int testCount = 0;
//    int failCount = 0;
//    int passCount = 0;
//    long totalSeconds = 0;
//    long totalMinutes = 0;
//    long totalHours = 0;
//    long testDuration;
//
//    StringBuilder HtmlEmailBody;
//
//    TestResult testResult;
//
//    public TestReportEmailerUtility(List<TestResult> _testResults, String inputFilePath) {
//        this.testResults = _testResults;
//        this.emailRecipients = ApplicationConfig.MailingList();
//        this.emailSender = ApplicationConfig.EmailSender();
//        this.emailHost = ApplicationConfig.EmailHost();
//        this.inputFilePath = inputFilePath;
//        HtmlEmailBody = new StringBuilder();
//
//        this.CalculateTestStatistics();
//    }
//
//    public String GenerateTestReport() {
//
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String dateTime = dateFormatter.format(calendar.getTime());
//        String testMethodLink = "";
//        String errorLog = this.networkReportDirectory + "\\ErrorTestLog.txt";
//        String infoLog = this.networkReportDirectory + "\\InfoTestLog.txt";
//
//        HtmlEmailBody.append("<!doctype html>\n");
//        HtmlEmailBody.append("<html lang='en'>\n");
//
//        HtmlEmailBody.append("<head>\n");
//        HtmlEmailBody.append("<meta charset='utf-8'>\n");
//        HtmlEmailBody.append("<title style=\"font-family:verdana;\">Automation Test Report - " + this.resolveScenarioName() + "</title>\n");
//        HtmlEmailBody.append("</head>\n\n");
//
//        HtmlEmailBody.append("<body>\n");
//
//        HtmlEmailBody.append("<h1 style=\"font-family:verdana;\"><a href=\"" + this.networkReportDirectory + "\">Automation Test Report - " + this.resolveScenarioName() + " - " + ApplicationConfig.Environment() + " Environment</a></h1>\n");
//        HtmlEmailBody.append("<h3 style=\"font-family:verdana;\">Report date - " + dateTime + "</h2>\n");
//        //HtmlEmailBody.append( "<h3>Test statistics</h3>\n" );
//
//        HtmlEmailBody.append("<table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" style=\" border-collapse:collapse; font-family:verdana; border:1px solid black\">\n");
//
//        HtmlEmailBody.append("<tr>\n");
//        HtmlEmailBody.append("<td colspan=\"4\" style=\"background-color:#FFA500; font-size: 15pt\">Test Statistics</td>\n");
//        HtmlEmailBody.append("</tr>\n");
//
//        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Tests</th>\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Passed</th>\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Failed</th>\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Runtime</th>\n");
//        HtmlEmailBody.append("</tr>\n");
//
//        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");
//        HtmlEmailBody.append("<td style=\"border-left:1px solid black;text-align:center;\">" + this.testCount + "</td>\n");
//        HtmlEmailBody.append("<td style=\"border-left:1px solid black;text-align:center;\">" + this.passCount + "</td>\n");
//        HtmlEmailBody.append("<td style=\"border-left:1px solid black;text-align:center;\">" + this.failCount + "</td>\n");
//        HtmlEmailBody.append("<td style=\"border-left:1px solid black;text-align:center;\">" + this.totalHours + " hours, " + this.totalMinutes + " minutes, " + this.totalSeconds + " seconds</td>\n");
//        HtmlEmailBody.append("</tr>\n");
//
//        HtmlEmailBody.append("</table>\n");
//
//        HtmlEmailBody.append("<table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" style=\" border-collapse:collapse;font-family:verdana; border:1px solid black;\">\n");
//
//        HtmlEmailBody.append("<tr>\n");
//        HtmlEmailBody.append("<td colspan=\"5\" style=\"background-color:#FFA500; font-size: 15pt\">Results Summary</td>\n");
//        HtmlEmailBody.append("</tr>\n");
//
//        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Test Case ID</th>\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Keyword</th>\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Pass \\ Fail</th>\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Test Message</th>\n");
//        HtmlEmailBody.append("<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Test Duration</th>\n");
//        HtmlEmailBody.append("</tr>\n");
//
//        for (TestResult testResult : testResults) {
//            HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");
//            testMethodLink = this.networkReportDirectory + "\\" + testResult.testData.TestMethod + "_" + testResult.testData.TestCaseId;
//
//            if (testResult.testPassed) {
//                HtmlEmailBody.append("<td  bgcolor=\"#B2FCA3\" style=\"border-left:1px solid black;\">" + testResult.testData.TestCaseId + "</td>\n");
//                HtmlEmailBody.append("<td style=\"border-left:1px solid black;\"><a href = \"" + testMethodLink + "\">" + testResult.testData.TestMethod + "</a></td>\n");
//                HtmlEmailBody.append("<td style=\"border-left:1px solid black;\">Pass</td>\n");
//            } else {
//                HtmlEmailBody.append("<td  bgcolor=\"#FF9494\" style=\"border-left:1px solid black;\">" + testResult.testData.TestCaseId + "</td>\n");
//                HtmlEmailBody.append("<td style=\"border-left:1px solid black;\"><a href = \"" + testMethodLink + "\">" + testResult.testData.TestMethod + "</a></td>\n");
//                HtmlEmailBody.append("<td style=\"border-left:1px solid black;\">Fail</td>\n");
//            }
//
//            HtmlEmailBody.append("<td style=\"border-left:1px solid black;\">" + testResult.errorMessage + "</td>\n");
//
//            HtmlEmailBody.append("<td style=\"border-left:1px solid black;\">" + testResult.calculateFormattedTestTime() + "</td>\n");
//            HtmlEmailBody.append("</tr>\n");
//        }
//
//        HtmlEmailBody.append("</table>\n");
//
//        HtmlEmailBody.append("<a href=\"" + infoLog + "\">Info Log</a>");
//
//        HtmlEmailBody.append("</br>");
//
//        HtmlEmailBody.append("<a href=\"" + errorLog + "\">Error Log</a>");
//
//        HtmlEmailBody.append("</body>\n\n");
//
//        HtmlEmailBody.append("</html>\n");
//
//        this.saveAsHTMLTestReport("TestReport.html");
//
//        return HtmlEmailBody.toString();
//
//    }
//
//    private void saveAsHTMLTestReport(String htmlReportFileName) {
//
//        try {
//
//            File reportFile = new File("HTMLTestReport\\" + htmlReportFileName);
//            File evidenceReportFile = new File(this.reportDirectory + "\\" + htmlReportFileName);
//
//            if (!reportFile.exists()) {
//                reportFile.createNewFile();
//            }
//
//            if (!evidenceReportFile.exists()) {
//                evidenceReportFile.createNewFile();
//            }
//
//            BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile));
//            writer.write(HtmlEmailBody.toString());
//
//            reportFile = null;
//            writer.close();
//
//            BufferedWriter writerEvidence = new BufferedWriter(new FileWriter(evidenceReportFile));
//            writerEvidence.write(HtmlEmailBody.toString());
//
//            evidenceReportFile = null;
//            writerEvidence.close();
//        } catch (Exception e) {
//            System.err.println("[Error] Failed to save HTML report to - " + htmlReportFileName + " - Error - " + e.getMessage());
//        }
//
//    }
//
//    public void SendResultsEmail() {
//        try {
//            Properties properties = System.getProperties();
//
//            properties.setProperty("mail.smtp.host", this.emailHost);
//            properties.setProperty("mail.smtp.port", "465");
//            properties.setProperty("mail.user", "psooraj@gmail.com");
//            properties.setProperty("mail.password", "password");
//
//            Session session = Session.getDefaultInstance(properties);
//
//            MimeMessage message = new MimeMessage(session);
//
//            message.setFrom(new InternetAddress(this.emailSender));
//
//            for (String recipient : this.emailRecipients) {
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            }
//
//            message.setSubject("Old Mutual OM_Konnect Automation Test Report");
//
//            MimeMultipart multipartMessage = new MimeMultipart();
//
//            MimeBodyPart messageBody = new MimeBodyPart();
//            MimeBodyPart attachment = new MimeBodyPart();
//            DataSource inputFile = new FileDataSource(inputFilePath);
//
//            messageBody.setContent(this.GenerateTestReport(), "text/html");
//
//            attachment.setDataHandler(new DataHandler(inputFile));
//            attachment.setHeader("Content-ID", "<" + UUID.randomUUID().toString() + ">");
//            attachment.setFileName(inputFile.getName());
//
//            multipartMessage.addBodyPart(messageBody);
//            multipartMessage.addBodyPart(attachment);
//
//            message.setContent(multipartMessage);
//
//            //Transport.send(message);
//            //System.out.println("Report Email sent...");
//        } catch (Exception e) {
//            System.err.println("[Error] could not send results email - " + e.getMessage());
//        }
//
//    }
//
//    private String resolveScenarioName() {
//        String isolatedFileNameString;
//        String[] splitFileName;
//        String[] inputFileNameArray;
//        String resolvedScenarioName = "";
//
//        // Get file name from inputFilePath (remove file extension)
//        inputFileNameArray = this.inputFilePath.split("\\.");
//        isolatedFileNameString = inputFileNameArray[0];
//        if (isolatedFileNameString.contains("/")) {
//            inputFileNameArray = isolatedFileNameString.split("/");
//        }
//
//        if (isolatedFileNameString.contains("\\")) {
//            inputFileNameArray = isolatedFileNameString.split("\\\\");
//        }
//        isolatedFileNameString = inputFileNameArray[inputFileNameArray.length - 1];
//
//        splitFileName = isolatedFileNameString.split("(?=\\p{Upper})");
//
//        for (String word : splitFileName) {
//            resolvedScenarioName += word + " ";
//        }
//
//        return resolvedScenarioName.trim();
//    }
//
//    private void CalculateTestStatistics() {
//
//        for (TestResult result : testResults) {
//            this.totalSeconds += result.testDuration;
//            this.testCount++;
//
//            if (result.testPassed) {
//                this.passCount++;
//            } else {
//                this.failCount++;
//            }
//        }
//
//        if (totalSeconds > 60) {
//            while (totalSeconds > 60) {
//                totalMinutes += 1;
//                totalSeconds -= 60;
//
//            }
//        }
//
//        if (totalMinutes > 60) {
//            while (totalMinutes > 60) {
//                totalHours += 1;
//                totalMinutes -= 60;
//            }
//        }
//    }
//}

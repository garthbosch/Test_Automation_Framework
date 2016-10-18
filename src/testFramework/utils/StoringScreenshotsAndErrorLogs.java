package testFramework.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import testFramework.testing.testSuites.BaseClass;

/**
 *
 * @author Garth Bosch
 */
public class StoringScreenshotsAndErrorLogs extends BaseClass {

    public void captureScreenshot(String filename, Parameter parameter, boolean isError) throws Exception {
        String screenshotFile = "";
        getEvidenceFolder(parameter);

        try {
            StringBuilder sb = new StringBuilder();
            // add date time folder and test case id folder
            sb.append(evidenceFolder).append("\\");
            sb.append(parameter.getSchemeName()).append("\\");
            if (isError) {
                sb.append("FAILED_");
            } else {
                sb.append("PASSED_");
            }
            sb.append(seleniumDriver.generateDateTimeString()).append("_");
            sb.append(filename).append(".png");
            screenshotFile = sb.toString();
            File screenCapture = ((TakesScreenshot) seleniumDriver.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenCapture, new File(screenshotFile));
            System.out.println("[Info] Screenshot " + screenshotFile.toString() + " taken successfully.");
        } catch (IOException ex) {
            Logger.getLogger(StoringScreenshotsAndErrorLogs.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("[Error] could not take screenshot - " + screenshotFile.toString() + " - " + ex.getMessage());
        }
    }

//    public void ProcessYMOutputFile(Parameter parameter, YMDataLine ymoutput, YMDataTrailer ymtrailer, String env) throws Exception {
//        getEvidenceFolder(parameter);
//        File file = new File(evidenceFolder + "\\" + parameter.getSchemeName() + "\\" + env + "_" + "ymoutput.txt");
//        try {
//            for (YMLineItem item : ymoutput.getAllLineItems()) {
////                PrepareItem(item, TargetTransform.File);
//                FileUtils.writeStringToFile(file, item.getData());
//            }
//            for (YMLineItem item : ymtrailer.getAllLineItems()) {
////                PrepareItem(item, TargetTransform.File);
//                FileUtils.writeStringToFile(file, item.getData());
//            }
//        } catch (Exception ex) {
//            throw new Exception("Unable to create YM Output file : " + file, ex);
//        }
//    }
}
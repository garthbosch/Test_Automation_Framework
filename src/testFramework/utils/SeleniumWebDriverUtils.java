package testFramework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import testFramework.enums.PageElements;
import testFramework.testing.testSuites.BaseClass;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.enums.BrowserTypes;
import static testFramework.testing.testSuites.BaseClass.dao;

/**
 *
 * @author Garth Bosch
 */
public class SeleniumWebDriverUtils extends BaseClass {

    public WebDriver driver;
    private final BrowserTypes browserType;
    File fileIEDriver;
    File fileChromeDriver;
    private Boolean _isDriverRunning;
    public String driverExceptionMessages = "";

    public SeleniumWebDriverUtils(BrowserTypes selectedBrowser) {

        _isDriverRunning = false;
        browserType = selectedBrowser;

        fileIEDriver = new File("IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", fileIEDriver.getAbsolutePath());

        fileChromeDriver = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
    }

    public boolean isDriverRunning() {
        return _isDriverRunning;
    }

    public void startDriver() {
        switch (browserType) {
            case Chrome:
                driver = new ChromeDriver(setCapabilities("Chrome"));
                _isDriverRunning = true;
                break;
            case IE:
                driver = new InternetExplorerDriver(setCapabilities("IE"));
                _isDriverRunning = true;
                break;
            case FireFox:
                driver = new FirefoxDriver(setCapabilities("FireFox"));
                _isDriverRunning = true;
                break;
            case HTML:
                driver = new HtmlUnitDriver(setCapabilities("HTML"));
                _isDriverRunning = true;
                break;
        }
        if (appConfig.getTestEnvironment().equals("DEV")) {
            driver.get(appConfig.getDevOneFMurl());
        }
        if (appConfig.getTestEnvironment().equals("QA")) {
            driver.get(appConfig.getQaOneFMurl());
        }
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(appConfig.getWaitTimeout(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private DesiredCapabilities setCapabilities(String browserTypes) {
        DesiredCapabilities dc = null;
        System.out.println("[Info] Browser type is: " + browserTypes);
        if (browserTypes.equalsIgnoreCase("Chrome")) {
            dc = DesiredCapabilities.chrome();
            dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
            dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            dc.setJavascriptEnabled(true);
            /*
             * added this code to remove the error message chrome was throwing
             * You are using an unsupported command-line flag: --ignore-certificate-errors. Stability and security will suffer
             */
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            dc.setCapability(ChromeOptions.CAPABILITY, options);
            return dc;
        }
        if (browserTypes.equalsIgnoreCase("IE")) {
            dc = DesiredCapabilities.internetExplorer();
            dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
            dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            dc.setJavascriptEnabled(true);
            return dc;
        }
        if (browserTypes.equalsIgnoreCase("FireFox")) {
            dc = DesiredCapabilities.firefox();
            dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
            dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            dc.setJavascriptEnabled(true);
            return dc;
        }
        System.out.println("[Info] The browser name is: " + dc.getCapability("Chrome"));
        return dc;
    }

    public void pause(int milisecondsToWait) {
        try {
            Thread.sleep(milisecondsToWait);
            System.out.println("[Info] Successfully waited for " + milisecondsToWait + " miliseconds");
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
        }
    }

    public void shutDown() {
        try {
            driver.quit();
            closeChromeInstances();
        } catch (Exception ex) {
            System.err.println("[Error] shutting down driver - " + ex.getMessage());
            this.driverExceptionMessages = ex.getMessage();
        }
        _isDriverRunning = false;
    }

    public void getBackToButton() throws Exception {
        pause(1000);
        driver.navigate().back();
        pause(1000);
    }

    public void initialiseWithoutSigningIn(Parameter parameter) throws Exception {
        driver = new HtmlUnitDriver();
        getEvidenceFolder(parameter);
    }

    public void initialiseWithoutSigningIns() throws Exception {
        driver = new HtmlUnitDriver();
    }

    public void updateTheQualityCenterTests(Parameter parameter, String qcTestLabPath, String qcTestSetName, boolean passedOrFailed) throws Exception {
        updateQC(parameter, qcTestLabPath, qcTestSetName, passedOrFailed);
    }

    public void updateQC(Parameter parameter, String qcTestLabPath, String qcTestSetName, boolean passedOrFailed) throws Exception {
        initialiseWithoutSigningIn(parameter);
        qcIntegration.updateTestOnQC(parameter, evidenceFolder, qcTestLabPath, qcTestSetName, passedOrFailed);
        shutDown();
    }

    public boolean navigateTo(String pageUrl) {
        try {
            driver.navigate().to(pageUrl);
            System.out.println("[Info] Successfully navigated to " + pageUrl);
            return true;
        } catch (Exception e) {
            System.err.println("[Error] navigating to URL - " + e.getMessage());
            this.driverExceptionMessages = ex.getMessage();
            return false;
        }
    }

    public String generateDateTimeString() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        return dateFormat.format(dateNow).toString();
    }

    public void closeChromeInstances() throws IOException {
        if (browserType.equals(browserType.Chrome)) {
            String TASKLIST = "tasklist";
            String KILL = "taskkill /IM ";
            String line;
            String serviceName = "chrome.exe";
            Process p = Runtime.getRuntime().exec(TASKLIST);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            System.out.println("Closing Chrome tasks...");
            while ((line = reader.readLine()) != null) {

                if (line.contains(serviceName)) {
                    Runtime.getRuntime().exec(KILL + serviceName);
                }
            }
        }
    }

    /*
     * Returns true of false whether or not the provided text for an id elemet is found on the page
     * @param id
     * @param text
     * @return Boolean value
     */
    public boolean waitForTextToBePresentInId(final String id, final String text) throws Exception {
        return (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElementLocated(By.id(id), text));
    }

    /*
     * Returns true of false whether or not the elemet is found on the page
     * @param id
     * @return Boolean value
     */
    public boolean waitForIdOnPage(String id) throws Exception {
        return (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.presenceOfElementLocated(By.id(id))).isEnabled();
    }

    /*
     * Returns true of false whether or not the provided text for a className elemet is found on the page
     * @param className
     * @param text
     * @return Boolean value
     */
    public boolean waitForTextToBePresentClassName(final String className, final String text) throws Exception {
        return (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.textToBePresentInElementLocated(By.className(className), text));
    }

    /*
     * Returns true of false whether or not the provided link is accessible on the page
     * @param link
     * @return Boolean value
     */
    public boolean waitForLinkAccessibility(final String link) throws Exception {
        try {
            WebElement element = (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.elementToBeClickable(By.linkText(link)));
//            System.out.println("[Info] Element " + link.toString() + " is accessible.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] The element " + link.toString() + " is not accessible - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided id is accessible on the page
     * @param id
     * @return Boolean value
     */
    public boolean waitForIDAccessibility(final String id) throws Exception {
        try {
            WebElement element = (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.elementToBeClickable(By.id(id)));
//            System.out.println("[Info] Element " + element.toString() + " is accessible.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] The element " + id.toString() + " is not accessible - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided xpath is accessible on the page
     * @param xpath
     * @return Boolean value
     */
    public boolean waitForXpathAccessibility(final String xpath) throws Exception {
        try {
            WebElement element = (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//            System.out.println("[Info] Element " + element.toString() + " is accessible.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] The element " + xpath.toString() + " is not accessible - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided name is accessible on the page
     * @param name
     * @return Boolean value
     */
    public boolean waitForNameAccessibility(final String name) throws Exception {
        try {
            WebElement element = (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.elementToBeClickable(By.name(name)));
//            System.out.println("[Info] Element " + element.toString() + " is accessible.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] The element " + name.toString() + " is not accessible - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided css is accessible on the page
     * @param css
     * @return Boolean value
     */
    public boolean waitForCSSLocatorAccessibility(final String css) throws Exception {
        try {
            WebElement element = (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
//            System.out.println("[Info] Element " + element.toString() + " is accessible.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] The element " + css.toString() + " is not accessible - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided className is accessible on the page
     * @param className
     * @return Boolean value
     */
    public boolean waitForClassNameAccessibility(final String className) throws Exception {
        try {
            WebElement element = (new WebDriverWait(driver, appConfig.getWaitTimeout())).until(ExpectedConditions.elementToBeClickable(By.className(className)));
//            System.out.println("[Info] Element " + element.toString() + " is accessible.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] The element " + className.toString() + " is not accessible - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided id element is accessible on the page and the click event is executed on the element
     * Here a PageElement value is passed through
     * @param elements via PageElements class
     * @param parameter
     * @return Boolean value
     */
    public boolean clickById(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            driver.findElement(By.id(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided id element is accessible on the page and the click event is executed on the element.
     * Here a string value is passed through for the element
     * @param elements via String
     * @param parameter
     * @return Boolean value
     */
    public boolean clickingById(String elements, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements);
            driver.findElement(By.id(elements)).click();
            System.out.println("[Info] Element " + elements + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.toString() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided id element is accessible on the page and the click event is executed on the element
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean clickByIdWithoutWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided id element is accessible on the page and the spacebar event is executed on the element
     * Here a PageElement value is passed through
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean sendSpaceBarKeyById(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            driver.findElement(By.id(elements.getElementId())).sendKeys(Keys.SPACE);
            System.out.println("[Info] Spacebar action successfully executed on element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Spacebar action was not successfully executed on element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided className element is accessible on the page and the click event is executed on the element
     * Here a PageElement value is passed through
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean clickByClassName(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForClassNameAccessibility(elements.getElementId());
            driver.findElement(By.className(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided className element is accessible on the page and the click event is executed on the element
     * Here a WebElement and PageElement value is passed through
     * @param row
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean clickByClassNameFromExistingElement(WebElement row, PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForClassNameAccessibility(elements.getElementId());
            row.findElement(By.className(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided id element is accessible on the page and the enter event is executed on the element
     * Here a PageElement value is passed through
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean enterById(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).sendKeys(Keys.ENTER);
            System.out.println("[Info] Enter action successfully executed on element " + elements.getElementNameOnPage() + ".");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Enter action not successfully executed on element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the enter event is executed on the provided id element
     * Here a WebElement, PageElement and String value is passed through
     * @param row
     * @param id
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean enterByIdStringValue(WebElement row, String id, PageElements element, Parameter parameter) throws Exception {
        try {
            row.findElement(By.id(id)).sendKeys(Keys.ENTER);
            System.out.println("[Info] Enter action successfully executed on element " + element.getElementNameOnPage() + ".");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Enter action not successfully executed on element " + element.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the space event is executed on the provided id element
     * Here a PageElement value is passed through
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean spaceById(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).sendKeys(Keys.SPACE);
            System.out.println("[Info] Space action successfully executed on element " + elements.getElementNameOnPage() + ".");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Space action not successfully executed on element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided xpath element is accessible on the page and the click event is executed on the element
     * Here a PageElement value is passed through
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public Boolean clickByXpath(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForXpathAccessibility(elements.getElementId());
            driver.findElement(By.xpath(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    /*
     * Returns true of false whether or not the provided xpath element is accessible on the page and the click event is executed on the element
     * Here a PageElement value is passed through
     * @param elements
     * @param parameter
     * @return Boolean value
     */
    public boolean clickByXpathWithoutWait(PageElements elements) throws Exception {
        try {
            driver.findElement(By.xpath(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickByXpathWithExistingElements(WebElement row, PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForXpathAccessibility(elements.getElementId());
            row.findElement(By.xpath(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickByLinkText(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForLinkAccessibility(elements.getElementId());
            driver.findElement(By.linkText(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickByPartialLinkText(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForLinkAccessibility(elements.getElementId());
            driver.findElement(By.partialLinkText(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickByLinkTextWithoutWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.linkText(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickByName(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForNameAccessibility(elements.getElementId());
            driver.findElement(By.name(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickElement(WebElement webElement, Parameter parameter) throws Exception {
        try {
            webElement.click();
        } catch (Exception ex) {
            System.err.println("[Error] Unable to click element " + webElement.getText() + " - " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean focusByName(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.name(elements.getElementId()));
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully focused on.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to focus on element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean typeById(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            driver.findElement(By.id(elements.getElementId())).clear();
            driver.findElement(By.id(elements.getElementId())).sendKeys(value);
            System.out.println("[Info] " + value + " was successfully typed to element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully typed to element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean typeByIdWithoutWait(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).clear();
            driver.findElement(By.id(elements.getElementId())).sendKeys(value);
            System.out.println("[Info] " + value + " was successfully typed to element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully typed to element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean typeByIdStingValueFromExistingRow(WebElement row, String id, String date, PageElements element, Parameter parameter) throws Exception {
        try {
            row.findElement(By.id(id)).clear();
            row.findElement(By.id(id)).sendKeys(date);
            System.out.println("[Info] " + date + " was successfully typed to element " + element.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + date + " was not successfully typed to element " + element.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public WebElement findElementById(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElement(By.id(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public WebElement findElementByIdWithoutWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElement(By.id(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public WebElement findElementByLinkText(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForLinkAccessibility(elements.getElementId());
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElement(By.linkText(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public List<WebElement> findElementsByTag(String tag, Parameter parameter) throws Exception {
        try {
            System.out.println("[Info] Element " + tag + " found.");
            return driver.findElements(By.tagName(tag));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + tag + " not found - " + ex.getMessage());
            return null;
        }
    }

    public List<WebElement> findElementsByClassName(PageElements elements) throws Exception {
        try {
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElements(By.className(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public List<WebElement> findElementsById(PageElements elements, Parameter parameter) throws Exception {
        try {
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElements(By.id(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public WebElement findElementByClassName(PageElements elements) throws Exception {
        try {
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElement(By.className(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public WebElement findElementByXpath(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForXpathAccessibility(elements.getElementId());
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElement(By.xpath(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public WebElement findElementByXpathWithoutWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForXpathAccessibility(elements.getElementId());
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElement(By.xpath(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public List<WebElement> findElementsByXpathWithoutWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForXpathAccessibility(elements.getElementId());
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " found.");
            return driver.findElements(By.xpath(elements.getElementId()));
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " not found - " + ex.getMessage());
            return null;
        }
    }

    public boolean typeBycss(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.cssSelector(elements.getElementId())).clear();
            driver.findElement(By.cssSelector(elements.getElementId())).sendKeys(value);
            System.out.println("[Info] " + value + " was successfully typed to element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully typed to element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clearFields(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).clear();
            System.out.println("[Info] Element field " + elements.getElementNameOnPage() + " successfully cleared ");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element field " + elements.getElementNameOnPage() + " not successfully cleared ");
            return false;
        }
    }

    public boolean typeByName(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            waitForNameAccessibility(elements.getElementId());
            driver.findElement(By.name(elements.getElementId())).clear();
            driver.findElement(By.name(elements.getElementId())).sendKeys(value);
            System.out.println("[Info] " + value + " was successfully typed to element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully typed to element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean selectionById(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            Select sel = new Select(driver.findElement(By.id(elements.getElementId())));
            sel.selectByVisibleText(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public WebElement findSelectionById(PageElements elements, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            Select sel = new Select(driver.findElement(By.id(elements.getElementId())));
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " was successfully found.");
            return sel.getFirstSelectedOption();
        } catch (Exception ex) {
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " was not found.");
            this.driverExceptionMessages = ex.getMessage();
        }
        return null;
    }

    public WebElement findSelectionByIdWithoutWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            Select sel = new Select(driver.findElement(By.id(elements.getElementId())));
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " was successfully found.");
            return sel.getFirstSelectedOption();
        } catch (Exception ex) {
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " was not found.");
            return null;
        }
    }

    /* this method find the dropdownlist from the page using id  
     * and returns with all the options in the list.
     */
    public List<WebElement> findSelectionOptionsWithoutWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            Select sel = new Select(driver.findElement(By.id(elements.getElementId())));
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " was successfully found.");
            return sel.getOptions();
        } catch (Exception ex) {
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " was not found.");
            return null;
        }
    }

    public boolean selectionByName(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            waitForNameAccessibility(elements.getElementId());
            Select sel = new Select(driver.findElement(By.name(elements.getElementId())));
            sel.selectByVisibleText(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionByIdValue(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            Select sel = new Select(driver.findElement(By.id(elements.getElementId())));
            sel.selectByValue(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionByIdValueFromExistingElement(WebElement row, PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            waitForIDAccessibility(elements.getElementId());
            Select sel = new Select(row.findElement(By.id(elements.getElementId())));
            sel.selectByValue(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionByIdWithoutWait(PageElements elements, String value) throws Exception {
        try {
            Select sel = new Select(driver.findElement(By.id(elements.getElementId())));
            sel.selectByVisibleText(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionBycss(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            Select sel = new Select(driver.findElement(By.cssSelector(elements.getElementId())));
            sel.selectByVisibleText(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionByClassName(WebElement row, PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            Select sel = new Select(row.findElement(By.cssSelector(elements.getElementId())));
            sel.selectByVisibleText(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionByXpath(PageElements elements, String text, Parameter parameter) throws Exception {
        try {
            waitForXpathAccessibility(elements.getElementId());
            Select sel = new Select(driver.findElement(By.xpath(elements.getElementId())));
            sel.selectByVisibleText(text);
            System.out.println("[Info] " + text + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + text + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionBycssValue(PageElements elements, String value, Parameter parameter) throws Exception {
        try {
            Select sel = new Select(driver.findElement(By.cssSelector(elements.getElementId())));
            sel.selectByValue(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + elements.getElementNameOnPage());
            return false;
        }
    }

    public boolean selectionByIdStingValueExistingRow(WebElement row, String id, String value, Parameter parameter, PageElements element) throws Exception {
        try {
            Select sel = new Select(row.findElement(By.id(id)));
            sel.selectByValue(value);
            System.out.println("[Info] " + value + " was successfully selected for element " + element.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] " + value + " was not successfully selected for element " + element.getElementNameOnPage());
            return false;
        }
    }

    public boolean clickBycss(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.cssSelector(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickBycssWithWait(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.cssSelector(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to get the page source. An error occurred - " + ex.getMessage());
            return null;
        }
    }

    public boolean submit(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).submit();
            System.out.println("[Info] Submit action on element " + elements.getElementNameOnPage() + " was successfully.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Submit action on element " + elements.getElementNameOnPage() + " was not successfully - " + ex.getMessage());
            return false;
        }
    }

    public boolean textElementPresent(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.cssSelector("css=th." + elements.getElementId())).isDisplayed();
            System.out.println("[Info] Text element " + elements.getElementNameOnPage() + " was present.");
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Text element " + elements.getElementNameOnPage() + " was not present - " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean idElementPresent(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.cssSelector(elements.getElementId())).isEnabled();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " was present.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element " + elements.getElementNameOnPage() + " was not present - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickByElement(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.cssSelector(elements.getElementId())).click();
            System.out.println("[Info] Element " + elements.getElementNameOnPage() + " successfully clicked.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Unable to click element " + elements.getElementNameOnPage() + " - " + ex.getMessage());
            return false;
        }
    }

    public boolean clearAllFields(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElements(By.name(elements.getElementId())).clear();
            System.out.println("[Info] Element field " + elements.getElementNameOnPage() + " successfully cleared.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Element field" + elements.getElementNameOnPage() + " not successfully cleared - " + ex.getMessage());
            return false;
        }
    }

    public boolean refreshPage() throws Exception {
        try {
            driver.navigate().refresh();
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred. The page was not refreshed - " + ex.getMessage());
            return false;
        }
    }

    public String getDate(int d, int m, int y) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateNow = formatter.format(currentDate.getTime());
            return dateNow;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDate in String format (dd/MM/yyyy) - " + ex.getMessage());
            return null;
        }
    }

    public String getMonthAndYear(int m, int y) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
            String dateNow = formatter.format(currentDate.getTime());
            return dateNow;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDate in String format (MM/yyyy) - " + ex.getMessage());
            return null;
        }
    }

    public String getDateWithYearFist(int d, int m, int y) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String dateNow = formatter.format(currentDate.getTime());
            return dateNow;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDate in String format (yyyy/MM/dd) - " + ex.getMessage());
            return null;
        }
    }

    public Integer getCurrentMonth(int m) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.MONTH, m);
            SimpleDateFormat formatter = new SimpleDateFormat("MM");
            String dateNow = formatter.format(currentDate.getTime());
            int month = Integer.valueOf(dateNow);
            return month;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getCurrentMonth in Integer format (MM) - " + ex.getMessage());
            return null;
        }
    }

    public String getDateDashFormat(int d, int m, int y) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = formatter.format(currentDate.getTime());
            return dateNow;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDateDashFormat in String format (yyyy-MM-dd) - " + ex.getMessage());
            return null;
        }
    }

    public Date getDateInDateFormat(int d, int m, int y) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            Date currDate = currentDate.getTime();
            return currDate;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDateInDateFormat in default Date format - " + ex.getMessage());
            return null;
        }
    }

    public String getDateAndTime(int d, int m, int y, int hh, int mm, int ss) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            currentDate.add(Calendar.HOUR, hh);
            currentDate.add(Calendar.MINUTE, mm);
            currentDate.add(Calendar.SECOND, ss);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateNow = formatter.format(currentDate.getTime());
            return dateNow;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDateAndTime in String format (dd/MM/yyyy HH:mm:ss) - " + ex.getMessage());
            return null;
        }
    }

    public String getDateAndTimeYearFirst(int d, int m, int y, int hh, int mm, int ss) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            currentDate.add(Calendar.HOUR, hh);
            currentDate.add(Calendar.MINUTE, mm);
            currentDate.add(Calendar.SECOND, ss);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String dateNow = formatter.format(currentDate.getTime());
            return dateNow;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDateAndTime in String format (yyyy/MM/dd HH:mm:ss) - " + ex.getMessage());
            return null;
        }
    }

    public Date getDateAndTimeInDateFormat(int d, int m, int y, int hh, int mm, int ss) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            currentDate.add(Calendar.HOUR, hh);
            currentDate.add(Calendar.MINUTE, mm);
            currentDate.add(Calendar.SECOND, ss);
            Date currDate = currentDate.getTime();
            return currDate;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDateAndTimeInDateFormat in default Date format - " + ex.getMessage());
            return null;
        }
    }

    public String getDateAndTimeDashFormat(int d, int m, int y, int hh, int mm, int ss) throws Exception {
        try {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DATE, d);
            currentDate.add(Calendar.MONTH, m);
            currentDate.add(Calendar.YEAR, y);
            currentDate.add(Calendar.HOUR, hh);
            currentDate.add(Calendar.MINUTE, mm);
            currentDate.add(Calendar.SECOND, ss);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNow = formatter.format(currentDate.getTime());
            return dateNow;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getDateAndTimeDashFormat in String format (yyyy-MM-dd HH:mm:ss) - " + ex.getMessage());
            return null;
        }
    }

    public Integer getAge(Date dateOfBirth) {
        int age = 0;
        try {
            Calendar born = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            if (dateOfBirth != null) {
                now.setTime(new Date());
                born.setTime(dateOfBirth);
                if (born.after(now)) {
                    throw new IllegalArgumentException("You can't be born in the future!");
                }
                age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                    age -= 1;
                }
            }
            System.out.println("The member is " + age + " years old.");
            return age;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] An error occurred while trying to getAge in Integer format - " + ex.getMessage());
            return null;
        }
    }

    /* Receives a date value in string format yyyy-MM-dd HH:mm:ss.
     * Format this value to be in a string format dd-MM-yyyy
     */
    public String formatStringDate(String date) throws ParseException {
        String formattedStringDate = "";
        int indexOfLastDash = date.lastIndexOf("-") + 3;
        String datePortion = date.substring(0, indexOfLastDash);
        System.out.println("[Info] Date Portion from value field: " + datePortion);
        Integer y = Integer.valueOf(datePortion.substring(0, datePortion.indexOf("-")));
        Integer m = Integer.valueOf(datePortion.substring(datePortion.indexOf("-") + 1, datePortion.lastIndexOf("-")));
        Integer d = Integer.valueOf(datePortion.substring(datePortion.lastIndexOf("-") + 1, datePortion.lastIndexOf("-") + 3));
        String dd = "";
        String mm = "";
        String yy = "";
        int increasedDay = d + 10;
//        checking last day of the month
        if ((increasedDay) > 30) {
            d = increasedDay - 30;
            m++;
//            making sure the day value consist of 2 digits
            if (d < 10) {
                dd = "0" + String.valueOf(d);
            }
            if (d >= 10) {
                dd = String.valueOf(d);
            }

//            making sure the month value consist of 2 digits
            if (m < 10) {
                mm = "0" + String.valueOf(m);
                yy = String.valueOf(y);
            }

            if (m > 12) {
                mm = "01";
                yy = String.valueOf(y + 1);
            }
            if (m >= 10 && m < 13) { // if m = 10 or 11 or 12 then nothing needs to be done to the month value
                mm = String.valueOf(m);
                yy = String.valueOf(y);
            }
        } else {
//            making sure that the month value consist of 2 digits
            if (m < 10) {
                dd = String.valueOf(increasedDay);
                mm = "0" + String.valueOf(m);
                yy = yy = String.valueOf(y);
            } else {
                dd = String.valueOf(increasedDay);
                mm = String.valueOf(m);
                yy = yy = String.valueOf(y);
            }
        }
        formattedStringDate = dd + "/" + mm + "/" + yy;
        System.out.println("[Info] Formatted Date: " + formattedStringDate);
        return formattedStringDate;
    }

    public boolean tabById(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).sendKeys(Keys.TAB);
            System.out.println("[Info] Successful tab action executed from element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Tab action from element " + elements.getElementNameOnPage() + " was not successful - " + ex.getMessage());
            return false;
        }
    }

    public boolean tabByCss(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.cssSelector(elements.getElementId())).sendKeys(Keys.TAB);
            System.out.println("[Info] Successful tab action executed from element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Tab action from element " + elements.getElementNameOnPage() + " was not successful - " + ex.getMessage());
            return false;
        }
    }

    public boolean pageDown(PageElements elements, Parameter parameter) throws Exception {
        try {
            driver.findElement(By.id(elements.getElementId())).sendKeys(Keys.PAGE_DOWN);
            System.out.println("[Info] Successful tab action executed from element " + elements.getElementNameOnPage());
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Page Down action from element " + elements.getElementNameOnPage() + " was not successful - " + ex.getMessage());
            return false;
        }
    }

    public String getFloatingBoxMessage(Parameter parameter) throws Exception {
        WebElement messageId;
        String message;
        try {
            waitForIdOnPage(PageElements.FloatingMessageBox.getElementId());
            messageId = findElementById(PageElements.FloatingMessageBox, parameter);
            message = messageId.getText().trim();
            List<WebElement> classButtons = findElementsByClassName(PageElements.FloatingMessageBoxCloseButton);
            for (WebElement classButton : classButtons) {
                if (classButton.getText().contains("Close")) {
                    classButton.click();
                    System.out.println("[Info] Floating Message box successfully closed.");
                }
            }
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
//            exceptionMessage(parameter, ex, PageElements.FloatingMessageBox);
            System.err.println("[Error] Floating Message box was not successfully closed - " + ex.getMessage());
            message = "The message was NOT retrieved from the page!!!!";
        }
        return message;
    }

    public boolean clickConfirmButtonOnFloatingMessage(PageElements elements) throws Exception {
        try {
            List<WebElement> classButtons = findElementsByClassName(elements);
            for (WebElement classButton : classButtons) {
                if (classButton.getText().equalsIgnoreCase("Confirm")) {
                    classButton.click();
                    break;
                }
            }
            System.out.println("[Info] Confirm button was successfully clicked on floating message box.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Confirm button was not successfully clicked on floating message box - " + ex.getMessage());
            return false;
        }
    }

    public boolean clickAddButtonOnFloatingMessage(PageElements elements) throws Exception {
        try {
            List<WebElement> classButtons = findElementsByClassName(elements);
            for (WebElement classButton : classButtons) {
                if (classButton.getText().equalsIgnoreCase("Add")) {
                    classButton.click();
                    break;
                }
            }
            System.out.println("[Info] Confirm button was successfully clicked on floating message box.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Confirm button was not successfully clicked on floating message box - " + ex.getMessage());
            return false;
        }
    }

    public String getQuoteStatus(Parameter parameter) throws Exception {
        String status = null;
        WebElement leftPane = findElementByClassName(PageElements.LeftPane);
        List<WebElement> divs = leftPane.findElements(By.tagName("div"));
        for (WebElement div : divs) {
            if (div.getText().equalsIgnoreCase("SCHEME INFORMATION")) {
                WebElement nextDiv = div.findElement(By.xpath("following-sibling::div"));
                List<WebElement> liLists = nextDiv.findElements(By.xpath("//ul[@id='lnav']//li//ul//li")); //"//table[@id='oldMutualClientPortfolio_clientPortfolio']//tbody//tr[2]//td//table//tbody"
                for (WebElement liList : liLists) {
                    if (liList.getText().contains("Status")) {
                        WebElement nextLi = liList.findElement(By.xpath("following-sibling::li"));
                        status = nextLi.getText();
                    }
//                    break;
                }
            }
        }
        return status;
    }

    public String getSchemeStatus(Parameter parameter) throws Exception {
        String status = null;
        WebElement leftPane = findElementByClassName(PageElements.LeftPane);
        List<WebElement> divs = leftPane.findElements(By.tagName("div"));
        for (WebElement div : divs) {
            if (div.getText().equalsIgnoreCase("SCHEME INFORMATION")) {
                WebElement nextElement = div.findElement(By.xpath("following-sibling::ul"));
                List<WebElement> liLists = nextElement.findElements(By.xpath("//li//ul//li")); //"//table[@id='oldMutualClientPortfolio_clientPortfolio']//tbody//tr[2]//td//table//tbody"
                for (WebElement liList : liLists) {
                    if (liList.getText().contains("Scheme Status")) {
                        WebElement nextLi = liList.findElement(By.xpath("following-sibling::li"));
                        status = nextLi.getText();
                        System.out.println("[Info] Scheme Status \"" + status + " \" successfully retrieved from the page");
                    }
                }
            }
        }
        return status;
    }

    public String getSchemePremiumsDue(Parameter parameter) throws Exception {
        String premiumsDue = null;
        WebElement leftPane = findElementByClassName(PageElements.LeftPane);
        List<WebElement> divs = leftPane.findElements(By.tagName("div"));
        for (WebElement div : divs) {
            if (div.getText().equalsIgnoreCase("SCHEME INFORMATION")) {
                WebElement nextElement = div.findElement(By.xpath("following-sibling::ul"));
                List<WebElement> liLists = nextElement.findElements(By.xpath("//li//ul//li"));
                for (WebElement liList : liLists) {
                    if (liList.getText().contains("Scheme Premiums Due")) {
                        WebElement nextLi = liList.findElement(By.xpath("following-sibling::li"));
                        String premRaw = nextLi.getText();
                        int indexOfR = premRaw.indexOf("R ") + 1;
                        premiumsDue = premRaw.substring(indexOfR).trim();
//                        premiumsDue = premRaw.substring(indexOfR).replace(".", "").trim();
                        System.out.println("[Info] Scheme Premiums Due Amount \"" + premiumsDue + "\" successfully retrieved from the page");
                    }
                }
            }
        }
        return premiumsDue;
    }

    public void exceptionMessage(Parameter parameter, Exception ex) throws Exception {
        String error = ex.getMessage().toString();
        String currentPageURL = driver.getCurrentUrl();
    }

    /* 
     * This will get the Scheme Number from the page. The scheme number that appears next to SCHEME INFORMATION
     */
    public String getRefNumbersFromHeading(Parameter parameter) throws Exception {
        WebElement schemeInfo = findElementByClassName(PageElements.SchemeHeadingLabel);
        int indexOfSpaceBeforeSchemeNo = schemeInfo.getText().indexOf('-');
        int indexOfStartOfSchemeNo = indexOfSpaceBeforeSchemeNo + 2;
        String refNo = schemeInfo.getText().substring(indexOfStartOfSchemeNo);
        return refNo;
    }

    /*
     * Clicking on the Search button when searching for a quote
     */
    public boolean viewDetailsButton(Parameter parameter) throws Exception {
        try {
            WebElement actionsSection = findElementByXpath(PageElements.SearchingActionsSection, parameter);
            actionsSection.findElement(By.xpath(PageElements.QuotesViewDetailsButton.getElementId())).click();
//            WebElement drillDownToImage = actionsSection.findElement(By.xpath("//span//a"));
//            drillDownToImage.findElement(By.xpath(PageElements.QuotesViewDetailsButton.getElementId())).click();
            System.out.println("[Info] View Details Button found.");
            return true;
        } catch (Exception ex) {
            this.driverExceptionMessages = ex.getMessage();
            System.err.println("[Error] Could not find the View Details button - " + ex.getMessage());
            return false;
        }
    }

    public void setScheduleParameterValueToTodaysDate(String env) throws Exception {
//        get the value field of the prd.ScheduleParameter table
//        String scheduleParameterValue = dao.getScheduleParameterValue(env);
//        System.out.println("Schedule Parameter Default Value is: " + scheduleParameterValue);
//        System.out.println("***********************Changing the Value item on the ScheduleParameter table to " + getDateWithYearFist(0, 0, 0));
        dao.setScheduleParameterValue(env, getDateWithYearFist(0, 0, 0));
//        return scheduleParameterValue;
    }

    public void setScheduleParameterValueToSpecificValue(String env, String scheduleParameterValue) throws Exception {
//        here a check is done see if there is a YMRaisingDate for the selected parameter value. If there's not then select a different one
        scheduleParameterValue = dao.checkIfSelectedParameterValueExistOnYMRaisingScheduleTable(env, scheduleParameterValue);
        dao.setScheduleParameterValue(env, scheduleParameterValue);
    }

    public String getTheSmallerValueFromPage(PageElements elements, Parameter parameter) {
        String smallestValue = "";
        int opt1 = 0, opt2 = 0;
        try {
            List<WebElement> getOptionsInList = findSelectionOptionsWithoutWait(elements, parameter);
            opt1 = Integer.valueOf(getOptionsInList.get(0).getText());
            opt2 = Integer.valueOf(getOptionsInList.get(1).getText());
            if (opt1 < opt2) {
                smallestValue = String.valueOf(opt1);
            } else {
                smallestValue = String.valueOf(opt2);
            }
            System.out.println("[Info] The No Of Premium value is: " + smallestValue);
            return smallestValue;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred when attempting to return the smallest No Of Premium value - " + ex.getMessage());
            return null;
        }
    }
}

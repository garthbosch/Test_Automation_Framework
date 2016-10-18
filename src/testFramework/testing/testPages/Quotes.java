package testFramework.testing.testPages;

import testFramework.enums.NotificationMessages;
import testFramework.enums.PageElements;
import testFramework.enums.ProductType;
import testFramework.enums.QuoteStatuses;
import testFramework.utils.Parameter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testFramework.testing.testSuites.BaseClass;

/**
 *
 * @author Garth
 */
public class Quotes extends BaseClass {

    public Quotes() {
//        super(seleniumDriver.driver);
    }

    private Boolean testCompletion = false;

    /*
     * Clicking on the Quotes link that appears on the top menu bar
     */
    public boolean navigateToQuotes(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByXpath(PageElements.QuotesNavigateToLink, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForLinkAccessibility(PageElements.QuotesCreateQuoteLink.getElementId())) {
            return false;
        }
        filename = "Quotes_Page_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Create Quote link that appears on the side pane under the Actions Section
     */
    public boolean clickCreateQuoteLink(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByLinkText(PageElements.QuotesCreateQuoteLink, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesCreateGFSQuoteNormalLink.getElementId())) {
            return false;
        }
        filename = "Quotes_BeforeSelectingProductType_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Create GFS Normal link
     */
    private boolean clickCreateGFSNormalQuote(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByXpath(PageElements.QuotesCreateGFSQuoteNormalLink, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesNextButton.getElementId())) {
            return false;
        }
        filename = "QuotesGFS_MinReqsBeforeCapture_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Create GFS Normal link
     */
    private boolean clickCreateGFSCustomisedQuote(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByXpath(PageElements.QuotesCreateGFSQuoteCustomisedLink, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesNextButton.getElementId())) {
            return false;
        }
        filename = "QuotesGFS_MinReqsBeforeCapture_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Create BSSP Customised link
     */
    private boolean clickCreateBSSPCustomisedQuote(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByXpath(PageElements.QuotesCreateBSSPCustomisedQuoteLink, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesNextButton.getElementId())) {
            return false;
        }
        filename = "QuotesBSSP_MinReqsBeforeCapture_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Next button when creating a quote
     */
    private boolean clickNextButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.QuotesNextButton, parameter);
    }

    /*
     * Clicking the cash back indicator on scheme page while capturing a quote.
     */
    private boolean clickCashBackIndicator(Parameter parameter) throws Exception {
        return seleniumDriver.clickById(PageElements.SchemesCashBackIndicatorCheckBox, parameter);
    }

    /*
     * Clicking on the Add Quote button when adding a new quote
     */
    private boolean clickAddQuoteButton(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByXpath(PageElements.QuotesAddQuoteButton, parameter)) {
            return false;
        }
//        this code has been commented out because the cash back functionality is not active. uncomment this once the cash back functionality is active again.
        filename = "Quotes_CashBackWarningMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickByXpath(PageElements.QuotesAddQuoteButton, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForClassNameAccessibility(PageElements.FloatingMessageBoxCloseButton.getElementId())) {
            return false;
        }
        filename = "Quotes_SuccessfullyCreatedMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Search button when searching for a quote
     */
    private boolean quoteSearchButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemeSearchButton, parameter);
    }

    /*
     * Clicking on the Continue button when viewing the information of the quote
     */
    private boolean clickContinueButton(Parameter parameter) throws Exception {
        if (!seleniumDriver.waitForLinkAccessibility(PageElements.QuotesContinueButton.getElementId())) {
            return false;
        }
        filename = "Quotes_SuccessfullyCreated_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickByLinkText(PageElements.QuotesContinueButton, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesPreviousButton.getElementId())) {
            return false;
        }
        filename = "Quotes_AgeAnalysis_BeforeCaturing_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Save Profile button
     */
    private boolean clickSaveProfileButton(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByXpath(PageElements.QuotesSaveProfileButton, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesSaveProfileButton.getElementId())) {
            return false;
        }
        filename = "Quotes_AgeAnalysis_SavedValues_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Produce Age Profile button
     */
    private boolean clickProduceAgeProfile(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickByXpath(PageElements.QuotesProducAgeProfileButton, parameter)) {
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Quotes_ConfirmationFloatingMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Quotes link that appears on the top menu bar and performing a quote search.
     */
    public boolean searchQuote(Parameter parameter) throws Exception {
        if (!navigateToQuotes(parameter)) {
            System.err.println("[Error] Unable to Navigate to the Quotes Page");
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.SchemesSearchNameTextBox, parameter.getSchemeName(), parameter)) {
            return false;
        }
        if (!quoteSearchButton(parameter)) {
            System.err.println("[Error] Unable to click on the Quotes Search button");
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Quote_Searching_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.viewDetailsButton(parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForLinkAccessibility(PageElements.QuotesCreateQuoteLink.getElementId())) {
            return false;
        }
        filename = "Quote_View_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Approve link on the Quotes page.
     */
    private boolean clickApproveLink(Parameter parameter) throws Exception {
        return seleniumDriver.clickById(PageElements.QuotesApproveLink, parameter);
    }

    /*
     * Clicking on the Assess link on the Quotes page.
     */
    private boolean clickAssessLink(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickById(PageElements.QuotesAssesLink, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesSaveAndProduceAgeProfileButton.getElementId())) {
            return false;
        }
        filename = "Quotes_BeforeAssessing_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Save and Produce Age Profile Button on the Quotes page.
     */
    private boolean clickSaveAndProduceAgeProfileButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.QuotesSaveAndProduceAgeProfileButton, parameter);
    }

    private boolean clickAcceptDeclineOption(Parameter parameter, boolean acceptDecline) throws Exception {
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesAcceptRadioButton.getElementId())) {
            return false;
        }
        filename = "Quotes_BeforeApprovingOrDeclining_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickByXpath(acceptDecline ? PageElements.QuotesAcceptRadioButton : PageElements.QuotesDeclineRadioButton, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesAcceptRadioButton.getElementId())) {
            return false;
        }
        filename = "Quotes_AfterApprovingOrDeclining_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickById(PageElements.QuotesSaveAndCloseButton, parameter)) {
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Quotes_ConfirmingOption_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickConfirmButtonOnFloatingMessage(PageElements.FloatingMessageBoxConfirmButton)) {
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Quotes_SuccessfullyAssessed_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Schemes link that appears on the top menu bar and performing a scheme search.
     */
    public Boolean createQuote(Parameter parameter, String env) throws Exception {
        if (!navigateToQuotes(parameter)) {
            System.err.println("[Error] Unable to navigte to the Quotes page");
            return false;
        }
        if (!clickCreateQuoteLink(parameter)) {
            System.err.println("[Error] Unable to click the Create Quote Link");
            return false;
        }

//        this is to check if your data file is correct
        if (!parameter.getProductType().equals(ProductType.BSCustomised.getProductTypeName())) {
            if (!parameter.getProductType().equals(ProductType.GFS.getProductTypeName())) {
                if (!parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName())) {
                    System.err.println("[Error] Please make sure that your data file contains either BSSP Customised, GFS, GFS Customised for Product Type");
                    return false;
                }
            }
        }
//        step 1 select product type
        if (!selectProductType(parameter)) {
            System.err.println("[Error] Unable to select a Product Type");
            return false;
        }
//        step 2 minimum requirements
        if (!captureMinRequirements(parameter)) {
            System.err.println("[Error] Unable to capture Quotes Minimum Requirements");
            return false;
        }
//        step 3 scheme details
        if (!captureSchemeDetails(parameter)) {
            System.err.println("[Error] Unable to capture Quotes Scheme Details");
            return false;
        }
//        get quote number from the message box
        String quoteMessageAfterSchemeDetails = seleniumDriver.getFloatingBoxMessage(parameter);
        quoteNumber = getQuoteFromMessage(quoteMessageAfterSchemeDetails);
        seleniumDriver.pause(2000);
        String quoteStatusQuoteLogged = seleniumDriver.getQuoteStatus(parameter); //getting the quote status after the scheme details were added - status should be Quote Logged
        String pgSource = seleniumDriver.driver.getPageSource();
        if (!pgSource.contains(PageElements.QuotesContinueButton.getElementId())) {
            if (!searchQuote(parameter)) {
                System.err.println("[Error] Search Quote was unsuccessful");
                return false;
            }
        }
        if (!clickContinueButton(parameter)) { //after this button is clicked the quote status should changed to WIP
            System.err.println("[Error] Unable to click the continue button");
            return false;
        }
        String quoteStatusWIP = seleniumDriver.getQuoteStatus(parameter);
//        step 4 age analysis
        if (!captureAgeAnalysis(parameter)) {
            System.err.println("[Error] Unable to capture Age Analysis");
            return false;
        }
        if (parameter.getProductType().equals(ProductType.BSCustomised.getProductTypeName())
                || parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName())) {
            String quoteStatusToBeAssessed = seleniumDriver.getQuoteStatus(parameter);
            System.out.println("Message after adding scheme quote details: " + quoteMessageAfterSchemeDetails);
            System.out.println("Quote Number: " + quoteNumber);
            System.out.println("Quote Logged Status: " + quoteStatusQuoteLogged);
            System.out.println("Quote WIP Status: " + quoteStatusWIP);
            System.out.println("Quote To be Assessed Status: " + quoteStatusToBeAssessed);
            testCompletion = quoteMessageAfterSchemeDetails.contains(NotificationMessages.SuccessfullySavedQuoteMsg.getMessage())
                    && quoteStatusQuoteLogged.equalsIgnoreCase(QuoteStatuses.QuoteLogged.getQuoteStatusDescription())
                    && quoteStatusWIP.equalsIgnoreCase(QuoteStatuses.WIP.getQuoteStatusDescription())
                    && quoteStatusToBeAssessed.equalsIgnoreCase(QuoteStatuses.ToBeAssessed.getQuoteStatusDescription());
        }
        if (parameter.getProductType().equals(ProductType.GFS.getProductTypeName())) {
            String quoteStatusProvided = seleniumDriver.getQuoteStatus(parameter);
            System.out.println("Message after adding scheme quote details: " + quoteMessageAfterSchemeDetails);
            System.out.println("Quote Number: " + quoteNumber);
            System.out.println("Quote Logged Status: " + quoteStatusQuoteLogged);
            System.out.println("Quote WIP Status: " + quoteStatusWIP);
            System.out.println("Quote Provided Status: " + quoteStatusProvided);
            testCompletion = quoteMessageAfterSchemeDetails.contains(NotificationMessages.SuccessfullySavedQuoteMsg.getMessage())
                    && quoteStatusQuoteLogged.equalsIgnoreCase(QuoteStatuses.QuoteLogged.getQuoteStatusDescription())
                    && quoteStatusWIP.equalsIgnoreCase(QuoteStatuses.WIP.getQuoteStatusDescription())
                    && quoteStatusProvided.equalsIgnoreCase(QuoteStatuses.Provided.getQuoteStatusDescription());
        }
        if (!testCompletion) {
            System.err.println("[Error] An error occured. Create Quote was not successfully completed");
            return false;
        }
        return true;
    }

    public Boolean approveQuote(Parameter parameter, String env) throws Exception {
        String pgSource = seleniumDriver.driver.getPageSource();
        if (!pgSource.contains(PageElements.QuotesApproveLink.getElementId())) {
            if (!searchQuote(parameter)) {
                System.err.println("[Error] Search Quote was unsuccessful");
                return false;
            }
        }
        if (!clickApproveLink(parameter)) {
            System.err.println("[Error] Unable to click the Approve Link");
            return false;
        }
        quoteNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        if (!clickAcceptDeclineOption(parameter, true)) {
            System.err.println("[Error] Unable to click the Accept Decline Link");
        }
        String quoteMessageAfterApproval = seleniumDriver.getFloatingBoxMessage(parameter);
        String quoteStatusAccepted = seleniumDriver.getQuoteStatus(parameter);
        System.out.println("Quote Number: " + quoteNumber);
        System.out.println("Quote message after approval: " + quoteMessageAfterApproval);
        System.out.println("Quote Accepted Status: " + quoteStatusAccepted);
        testCompletion = quoteMessageAfterApproval.equalsIgnoreCase("1: Quote " + quoteNumber + " accepted.")
                && quoteStatusAccepted.equalsIgnoreCase(QuoteStatuses.Accepted.getQuoteStatusDescription());
        if (!testCompletion) {
            System.err.println("[Error] An error occured. Approve GFS Normal Quote was not successfully completed");
            return false;
        }
        return true;
    }

    public boolean assessBSSPCustomisedQuote(Parameter parameter, String env) throws Exception {
        String pgSource = seleniumDriver.driver.getPageSource();
        if (!pgSource.contains(PageElements.QuotesAssesLink.getElementId())) {
            if (!searchQuote(parameter)) {
                System.err.println("[Error] Search Quote was unsuccessful");
                return false;
            }
        }
        if (!clickAssessLink(parameter)) {
            System.err.println("[Error] Unable to click Assess Link");
            return false;
        }
        if (!clickSaveAndProduceAgeProfileButton(parameter)) {
            System.err.println("[Error] Unable to click Save And Produce Age Profile Button");
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Quotes_AssessingConfirmationMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        seleniumDriver.clickConfirmButtonOnFloatingMessage(PageElements.FloatingMessageBoxConfirmButton);
        seleniumDriver.pause(2000);
        filename = "Quotes_AssessmentSuccessMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String quoteMessageAfterAssessment = seleniumDriver.getFloatingBoxMessage(parameter);
        String quoteStatusProvided = seleniumDriver.getQuoteStatus(parameter);
        System.out.println("Quote Number: " + quoteNumber);
        System.out.println("Quote message after assessment: " + quoteMessageAfterAssessment);
        System.out.println("Quote Provided Status: " + quoteStatusProvided);
        testCompletion = quoteMessageAfterAssessment.equalsIgnoreCase("Assessment: Quote " + quoteNumber + " was successfully assessed.")
                && quoteStatusProvided.equalsIgnoreCase(QuoteStatuses.Provided.getQuoteStatusDescription());
        if (!testCompletion) {
            System.err.println("[Error] Assess BSSP Customised Quote was not successfully executed");
            return false;
        }
        return true;
    }

    private boolean captureMinRequirements(Parameter parameter) throws Exception {
        WebElement minReqFieldSet = seleniumDriver.findElementByXpath(PageElements.QuotesMinReqFieldset, parameter);
        List<WebElement> allRows = minReqFieldSet.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            if (row.getText().contains("Batch Header")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Quote Request Form")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("External Vendor")) {
                List<WebElement> rowElements = row.findElements(By.tagName("label"));
                for (WebElement rowElement : rowElements) {
                    if (parameter.isExternalVendor() && rowElement.getText().contains("Yes")) {
                        rowElement.click();
                    }
                    if (!parameter.isExternalVendor() && rowElement.getText().contains("No")) {
                        rowElement.click();
                    }
                }
            }
            if (row.getText().contains("External Vendor Name") && parameter.isExternalVendor()) {
                String externalVendorNameId = row.findElement(By.xpath("//td//select")).getAttribute("id");
                if (!seleniumDriver.selectionByIdStingValueExistingRow(row, externalVendorNameId, "Long Life", parameter, PageElements.SomeElement)) {
                    return false;
                }
            }

            if (row.getText().contains("Re-Issued Business")) {
                List<WebElement> rowElements = row.findElements(By.tagName("label"));
                for (WebElement rowElement : rowElements) {
                    if (parameter.isReIssuedBusiness() && rowElement.getText().contains("Yes")) {
                        rowElement.click();
                    }
                    if (!parameter.isReIssuedBusiness() && rowElement.getText().contains("No")) {
                        rowElement.click();
                    }
                }
            }
            if (row.getText().contains("Re-Issued Business Reason") && parameter.isReIssuedBusiness()) {
                String reIssueBusinessReasonId = row.findElement(By.tagName("select")).getAttribute("id");
                if (!seleniumDriver.selectionByIdStingValueExistingRow(row, reIssueBusinessReasonId, "Scheme cancelled within 12 months", parameter, PageElements.SomeElement)) {
                    return false;
                }
            }
            if (row.getText().contains("Date Received")) {
                List<WebElement> rowElements = row.findElements(By.tagName("input"));
                for (int i = 0; i < rowElements.size(); i++) {
                    String dateReceivedId = rowElements.get(i).getAttribute("id");
                    if (!seleniumDriver.typeByIdStingValueFromExistingRow(row, dateReceivedId, getDateReceived(), PageElements.SomeElement, parameter)) {
                        return false;
                    }
                    if (!seleniumDriver.enterByIdStringValue(row, dateReceivedId, PageElements.SomeElement, parameter)) {
                        return false;
                    }
                }

            }
            if (parameter.getProductType().equals(ProductType.GFS.getProductTypeName())
                    || parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName())) {
                if (row.getText().contains("FSP license no.")) {
                    if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                        return false;
                    }
                }
                if (row.getText().contains("VAT registration no.")) {
                    String vatRegNoId = row.findElement(By.tagName("input")).getAttribute("id");
                    if (!seleniumDriver.typeByIdStingValueFromExistingRow(row, vatRegNoId, "24569852", PageElements.SomeElement, parameter)) {
                        return false;
                    }
                }
                if (row.getText().contains("Member data")) {
                    if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                        return false;
                    }
                }
            }
            if (parameter.getProductType().equals(ProductType.BSCustomised.getProductTypeName())) {
                if (row.getText().contains("Member Data")) {
                    if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                        return false;
                    }
                }
            }
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesNextButton.getElementId())) {
            return false;
        }
        filename = "Quotes_MinReqsAfterCapture_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickNextButton(parameter)) {
            System.err.println("[Error] An error occurred when clicking the next button");
            return false;
        }
        filename = "Quotes_MinReqsValidations_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickNextButton(parameter)) {
            System.err.println("[Error] An error occurred when clicking the next button");
            return false;
        }
        seleniumDriver.pause(1000);
        if (!seleniumDriver.waitForIDAccessibility(PageElements.QuotesSchemeNameTextField.getElementId())) {
            return false;
        }
        filename = "Quotes_SchemeDetails_BeforeCaturing_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    private boolean captureSchemeDetails(Parameter parameter) throws Exception {
        if (!seleniumDriver.typeById(PageElements.QuotesSchemeNameTextField, parameter.getSchemeName(), parameter)) {
            return false;
        }
        if (!seleniumDriver.selectionById(PageElements.QuotesSchemeTypeDropDown, parameter.getSchemeType(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesSchemeLicenseNoTextbox, "25140369", parameter)) {
            return false;
        }
        if (!seleniumDriver.selectionById(PageElements.QuotesSchemeRegionIDDropDown, parameter.getSchemeRegion(), parameter)) {
            return false;
        }
//        this was inserted for cash back testing
        if (parameter.isCashBack() && (parameter.getProductType().equalsIgnoreCase(ProductType.GFSCustomised.getProductTypeName())
                || parameter.getProductType().equalsIgnoreCase(ProductType.BSCustomised.getProductTypeName()))) {
            if (!seleniumDriver.clickById(PageElements.SchemesCashBackIndicatorCheckBox, parameter)) {
                return false;
            }
        }
        if (!seleniumDriver.selectionById(PageElements.QuotesSchemeContactTitleDropDown, parameter.getSchemeContactPersonTitle(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesSchemeContactSurnameTextBox, parameter.getSchemeContactPersonSurname(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesSchemeContactFirstnameTextBox, parameter.getSchemeContactPersonFirstname(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesSchemeContactEmailTextBox, parameter.getSchemeContactPersonEmail(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesContactPhoneNoTextBox, parameter.getSchemeContactPersonPhoneNo(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesContactPhoneFaxNoTextBox, parameter.getSchemeContactPersonPhoneFaxNo(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesContactPhoneCellNoTextBox, parameter.getSchemeContactPersonPhoneCellNo(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesStreetAddress1TextBox, parameter.getSchemeStreetAddress1(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesStreetAddress2TextBox, parameter.getSchemeStreetAddress2(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesStreetAddress3TextBox, parameter.getSchemeStreetAddress3(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesStreetAddress4TextBox, parameter.getSchemeStreetAddress4(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesStreetAddressPostalCodeTextBox, parameter.getSchemeStreetAddressPostalCode(), parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesStreetAddressCityTextBox, parameter.getSchemeStreetAddressCity(), parameter)) {
            return false;
        }
        if (!seleniumDriver.selectionById(PageElements.QuotesStreetAddressCountryDropDown, parameter.getSchemeStreetAddressCountry(), parameter)) {
            return false;
        }
        if (!captureAddress(parameter)) {
            System.err.println("[Error] An error occurred while trying to capture the Address Details");
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.QuotesServicingConsultantStaffCodeTextBox, parameter.getSchemeServicingConsultantStaffCode(), parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesAddQuoteButton.getElementId())) {
            return false;
        }
        filename = "Quotes_SchemeDetails_AfterCaturing_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickAddQuoteButton(parameter)) { //scheme details added
            System.err.println("[Error] An error occurred when clicking the Add Quote Button");
            return false;
        }
        return true;
    }

    private String getQuoteFromMessage(String message) {
        try {
            int lastIndex = message.lastIndexOf(":");
            int indexOfQuoteNo = lastIndex + 2;
            return message.substring(indexOfQuoteNo);
        } catch (Exception ex) {
            System.err.println("[Error] Unable to return the quote from the message - " + ex.getMessage());
            return null;
        }
    }

    private boolean captureAgeAnalysis(Parameter parameter) throws Exception {
        if (parameter.getProductType().equals(ProductType.GFS.getProductTypeName())
                || parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName())) {
            if (!seleniumDriver.selectionByXpath(PageElements.QuotesProductOptionMethodDropDown, "Age Profile", parameter)) {
                return false;
            }
            if (!captureAgeProfile(parameter)) {
                System.err.println("[Error] Unable to capture Age Profile");
            }
        }
        if (parameter.getProductType().equals(ProductType.BSCustomised.getProductTypeName())) {
            if (!seleniumDriver.selectionByXpath(PageElements.QuotesProductOptionMethodDropDown, "Age Profile", parameter)) {
                return false;
            }
            if (!seleniumDriver.clickByXpath(PageElements.QuotesAddProductOptionButton, parameter)) {
                return false;
            }
            seleniumDriver.pause(1000);
            filename = "QuotesBSSP_AgeAnalysis_AddingProductOption_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            if (!seleniumDriver.selectionById(PageElements.QuotesProductDropDown, parameter.getSchemeProductOption().toUpperCase(), parameter)) {
                return false;
            }
            seleniumDriver.pause(1000);
            if (!seleniumDriver.selectionById(PageElements.QuotesCoverDropDown, parameter.getSchemeProductOptionCover(), parameter)) {
                return false;
            }
            if (!seleniumDriver.clickAddButtonOnFloatingMessage(PageElements.FloatingMessageBoxAddButton)) {
                return false;
            }
            seleniumDriver.pause(1000);
            filename = "QuotesBSSP_AddQuotesProductionOptionAdded_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            if (!captureAgeProfile(parameter)) {
                System.err.println("[Error] Unable to capture Age Profile");
            }
        }
        return true;
    }

    private boolean selectProductType(Parameter parameter) throws Exception {
        try {
            if (parameter.getProductType().equals(ProductType.GFS.getProductTypeName())) {
                if (!clickCreateGFSNormalQuote(parameter)) {
                    System.err.println("[Error] Unable to click the link Create GFS Nornaml Quote");
                    return false;
                }
            }
            if (parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName())) {
                if (!clickCreateGFSCustomisedQuote(parameter)) {
                    System.err.println("[Error] Unable to click the link Create GFS Customised Quote");
                    return false;
                }
            }
            if (parameter.getProductType().equals(ProductType.BSCustomised.getProductTypeName())) {
                if (!clickCreateBSSPCustomisedQuote(parameter)) {
                    System.err.println("[Error] Unable to click the link Create BSSP Customised Quote");
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while trying to select a Product Type - " + ex.getMessage());
            return false;
        }
    }

    private boolean captureAgeProfile(Parameter parameter) throws Exception {
        if (parameter.getProductType().equals(ProductType.GFS.getProductTypeName())
                || parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName())) {
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Male0To15Textbox, "50", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Female0To15Textbox, "60", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Male16To64Textbox, "25", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Female16To64Textbox, "20", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Male65To74Textbox, "5", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Female65To74Textbox, "8", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Male75To84Textbox, "6", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Female75To84Textbox, "5", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Male85To100Textbox, "1", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesProducAnalysisAgeBand15000Female85To100Textbox, "2", parameter)) {
                return false;
            }
            if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesSaveProfileButton.getElementId())) {
                return false;
            }
            filename = "QuotesGFS_AgeAnalysis_AfterCaturing_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        }
        if (parameter.getProductType().equals(ProductType.BSCustomised.getProductTypeName())) {
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Male0To15Textbox, "50", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Female0To15Textbox, "60", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Male16To64Textbox, "25", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Female16To64Textbox, "20", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Male65To74Textbox, "5", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Female65To74Textbox, "8", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Male75To84Textbox, "6", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Female75To84Textbox, "5", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Male85To100Textbox, "1", parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.QuotesBSSPProducAnalysisAgeBand15000Female85To100Textbox, "2", parameter)) {
                return false;
            }
            if (!seleniumDriver.waitForXpathAccessibility(PageElements.QuotesSaveProfileButton.getElementId())) {
                return false;
            }
            filename = "QuotesBSSP_AgeAnalysis_AfterCaturing_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        }
        clickSaveProfileButton(parameter);
        clickProduceAgeProfile(parameter);
        if (!seleniumDriver.clickConfirmButtonOnFloatingMessage(PageElements.FloatingMessageBoxConfirmButton)) { //after confirmed button is clicked the quote status should be Provided
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Quotes_Summary_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    private boolean captureAddress(Parameter parameter) throws Exception {
        return (parameter.getSchemeStreetAddressSameForPostalInd()
                ? seleniumDriver.clickById(PageElements.QuotesStreetAddressSameForPostalCheckBox, parameter)
                : seleniumDriver.typeById(PageElements.QuotesPostalAddress1TextBox, parameter.getSchemePostalAddress1(), parameter)
                && seleniumDriver.typeById(PageElements.QuotesPostalAddress2TextBox, parameter.getSchemePostalAddress2(), parameter)
                && seleniumDriver.typeById(PageElements.QuotesPostalAddress3TextBox, parameter.getSchemePostalAddress3(), parameter)
                && seleniumDriver.typeById(PageElements.QuotesPostalAddress4TextBox, parameter.getSchemePostalAddress4(), parameter)
                && seleniumDriver.typeById(PageElements.QuotesPostalAddressPostalCodeTextBox, parameter.getSchemePostalAddressPostalCode(), parameter)
                && seleniumDriver.typeById(PageElements.QuotesPostalAddressCityTextBox, parameter.getSchemePostalAddressCity(), parameter));
    }

    private String getDateReceived() throws Exception {
        String date;
        date = "01/" + seleniumDriver.getMonthAndYear(0, 0);
        return date;
    }
}

package testFramework.testing.testPages;

import java.text.DecimalFormat;
import testFramework.enums.NotificationMessages;
import testFramework.enums.PageElements;
import testFramework.enums.ProductType;
import testFramework.enums.SchemeStatuses;
import testFramework.objects.Scheme;
import testFramework.objects.WorkflowData;
import testFramework.utils.MemberDetails;
import testFramework.utils.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import testFramework.testing.testSuites.BaseClass;
import static testFramework.testing.testSuites.BaseClass.executeEF;
import static testFramework.testing.testSuites.BaseClass.seleniumDriver;
import static testFramework.testing.testSuites.BaseClass.scrnShotsandMsgs;
import static testFramework.testing.testSuites.BaseClass.dao;

/* 
 * @author Garth
 */
public class Schemes extends BaseClass {

    private Boolean testCompletion = false;

    public String[] deleteNewYMStatusReasons = new String[]{"DEBIT NOT ALLOWED", "PAYMENT STOPPED", "ACCOUNT FROZEN", "ACCOUNT CLOSED",
        "DRAWER DECEASED", "NO AUTHORITY TO DEBIT"};

    /*
     * Clicking on the Schemes link that appears on the top menu bar
     */
    public boolean navigateToSchemes(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemesNavigateToLink, parameter);
    }

    /*
     * Clicking on the Create BSSP link that appears on the side pane under the Actions Section
     */
    public boolean clickCreateBSSPLink(Parameter parameter) throws Exception {
        return seleniumDriver.clickByLinkText(PageElements.SchemesCreateBSSPLink, parameter);
    }

    /*
     * Clicking on the Policy Admin link that appears on the side pane udner the Actions Section
     */
    public boolean navigateToPolicyAdminLinkUnderActionsSidePane(Parameter parameter) throws Exception {
        return seleniumDriver.clickByLinkText(PageElements.SchemesPolicyAdminLink, parameter);
    }

    /*
     * Clicking on the Transaction History link that appears on the side pane udner the Actions Section
     */
    private boolean navigateToTransactionHistoryLinkOnSidePane(Parameter parameter) throws Exception {
        return seleniumDriver.clickByLinkText(PageElements.SchemesTransactionHistoryLink, parameter);
    }

    /*
     * Clicking on the Add Scheme button when creating a new scheme
     */
    public boolean addSchemeButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemesAddSchemeButton, parameter);
    }

    /*
     * Clicking on the Add Product button when adding a new new product to the scheme
     */
    public boolean addSchemeProductOptionButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemesAddSchemeProductOptionButton, parameter);
    }

    /*
     * Clicking on the Search button when searching for scheme
     */
    public boolean schemeSearchButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemeSearchButton, parameter);
    }

    /*
     * Clicking on the update scheme button after a scheme search has been done.
     */
    public boolean schemeUpdateButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemeUpdateButton, parameter);
    }

    /*
     * Clicking on the view scheme button after a scheme search has been done.
     */
    public boolean schemeViewButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemeViewButton, parameter);
    }

    /*
     * Clicking on the Policy Admin button that appears on the Schemes page
     */
    public boolean schemePolicyAdminButton(Parameter parameter) throws Exception {
        if (!seleniumDriver.clickById(PageElements.SchemesPolicyAdminButton, parameter)) {
            return false;
        }
//        if (!seleniumDriver.waitForLinkAccessibility(PageElements.SchemesDownloadAgeProfileLink.getElementId())) {
//            return false;
//        }
        filename = "Schemes_PolicyAdmin_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    /*
     * Clicking on the Add Principal Member when adding members to the scheme
     */
    public boolean schemeAddPrincipleMemberButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemesAddingPrincipleMemberButton, parameter);
    }

    /*
     * Clicking on the Cash Confirmation Button that appears on the Schemes page
     */
    public boolean schemeCashConfirmationButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickById(PageElements.SchemesCashConfirmationButton, parameter);
    }

    /*
     * Clicking on the Next button when creating a scheme
     */
    public boolean clickNextButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickById(PageElements.SchemesNextButton, parameter);
    }

    /*
     * Clicking on the update scheme button from the side pane.
     */
    public boolean schemeUpdateButtonFromSidePane(Parameter parameter) throws Exception {
        return seleniumDriver.clickByLinkText(PageElements.SchemeUpdateButtonSidePane, parameter);
    }

    /*
     * Clicking on the save button when updating scheme.
     */
    public boolean clickSaveButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemeSaveButton, parameter);
    }

    public boolean clickSaveButtonOnMinReq(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemeMinReqUpdateSaveButton, parameter);
    }

    /*
     * Clicking on the minimum requirements link under schemes.
     */
    private boolean clickMinRequirementsLink(Parameter parameter) throws Exception {
        return seleniumDriver.clickById(PageElements.SchemesMinRequirementLinkId, parameter);
    }

    /*
     * Clicking the cash back indicator when doing an update on a scheme.
     */
    private boolean clickCashBackIndicatorViaUpdate(Parameter parameter) throws Exception {
        return seleniumDriver.clickById(PageElements.SchemesCashBackIndicatorViaUpdateCheckBox, parameter);
    }

    /*
     * Clicking on the Schemes link that appears on the top menu bar and performing a scheme search then click on Update Scheme.
     */
    public boolean searchSchemeUpdateButton(Parameter parameter) throws Exception {
        if (!navigateToSchemes(parameter)) {
            System.err.println("[Error] An error occurred while navigating to Schemes page");
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.SchemesSearchNameTextBox, parameter.getSchemeName(), parameter)) {
            return false;
        }
        if (!schemeSearchButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking the search button");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeUpdateButton.getElementId())) {
            return false;
        }
        filename = "Schemes_Searching_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!schemeUpdateButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking the update button");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeSaveButton.getElementId())) {
            return false;
        }
        filename = "Schemes_UpdatingView_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }
    /*
     * Clicking on the Schemes link that appears on the top menu bar and performing a scheme search and click on View Scheme.
     */

    public boolean searchSchemeViewButton(Parameter parameter) throws Exception {
        if (!navigateToSchemes(parameter)) {
            System.err.println("[Error] An error occurred while navigating to Schemes page");
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.SchemesSearchNameTextBox, parameter.getSchemeName(), parameter)) {
            return false;
        }
        if (!schemeSearchButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking the search button");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeViewButton.getElementId())) {
            return false;
        }
        filename = "Schemes_Searching_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!schemeViewButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking the view scheme button");
            return false;
        }
        if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesInfoTable.getElementId(), "Scheme Data")) {
            return false;
        }
        filename = "Schemes_InformationView_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    private boolean cancelSchemeLink(Parameter parameter) throws Exception {
        return seleniumDriver.clickById(PageElements.SchemesCancelLink, parameter);
    }

    private boolean clickCancelSchemeButton(Parameter parameter) throws Exception {
        return seleniumDriver.clickByXpath(PageElements.SchemeCancelSchemeButton, parameter);
    }

    private boolean policyAdminLink(Parameter parameter) throws Exception {
        return seleniumDriver.clickByLinkText(PageElements.SchemesPolicyAdminLink, parameter);
    }

    /*
     * Creating a new Scheme and returning a true boolean value if the successful message appears on the floating message box
     */
    public boolean createNewScheme(Parameter parameter, String env) throws Exception {
        if (!navigateToSchemes(parameter)) {
            System.err.println("[Error] An error occurred when attempting to navigate to the Schemes page");
            return false;
        }
        if (!seleniumDriver.waitForLinkAccessibility(PageElements.SchemesCreateBSSPLink.getElementId())) {
            System.err.println("[Error] An error occurred when attempting to navigate to the Schemes page");
            return false;
        }
        filename = "Schemes_Page_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickCreateBSSPLink(parameter)) {
            System.err.println("[Error] An error occurred when attempting to click Create BSSP Link");
        }
        if (!seleniumDriver.waitForIDAccessibility(PageElements.SchemesNextButton.getElementId())) {
            return false;
        }
        filename = "Schemes_BeforeCapturingMinReqs_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!captureMinRequirementsAllYes(parameter, true)) { //the boolean value must be true if a BSSP scheme without a quote is created
            System.err.println("[Error] An error occurred while capturing the minimum requirements");
            return false;
        }
//        here you need to do a double click to move to the next screen
        if (!clickNextButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking on the Next Button");
            return false;
        }
        if (!seleniumDriver.waitForIDAccessibility(PageElements.SchemesNextButton.getElementId())) {
            return false;
        }
        filename = "Schemes_MinReqsConfirmExternalVendor_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickNextButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking on the Next Button");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemesAddSchemeButton.getElementId())) {
            return false;
        }
        filename = "Schemes_SchemeDetails_BeforeCapturingSchemeDetails_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!captureSchemeDetails(parameter, env)) {
            System.err.println("[Error] An error occurred while capturing the Scheme Details");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemesAddSchemeButton.getElementId())) {
            return false;
        }
        filename = "Schemes_SchemeDetails_AfterCapturingSchemeDetails_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!addSchemeButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking the Add Button");
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SchemeDetails_SuccessfullyCreatedMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterAddingScheme = seleniumDriver.getFloatingBoxMessage(parameter);
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        String schemeStatusNewBusinessWIP = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("[Info] ************************Scheme Details************************");
        System.out.println("Scheme Number: " + schemeNumber);
        System.out.println("Scheme Status - NB WIP: " + schemeStatusNewBusinessWIP);
        System.out.println("Scheme Message After Adding Scheme: " + schemeMessageAfterAddingScheme);
        if (!schemeStatusNewBusinessWIP.equalsIgnoreCase(SchemeStatuses.NewBusinessWIP.getSchemeStatusDescription())) {
            System.err.println("[Error] The Scheme Status should be \"" + SchemeStatuses.NewBusinessLogged.getSchemeStatusDescription() + "\" but it was "
                    + schemeStatusNewBusinessWIP);
            return false;
        }
        if (!schemeMessageAfterAddingScheme.equalsIgnoreCase(NotificationMessages.SuccessfullySavedSchemeMsg.getMessage())) {
            System.err.println("[Error] The Scheme Message should be \"" + NotificationMessages.SuccessfullySavedSchemeMsg.getMessage() + "\" but it was "
                    + schemeMessageAfterAddingScheme);
            return false;
        }
        return true;
    }

    /*
     * Completing a scheme from a quote
     */
    public boolean completeScheme(Parameter parameter, String env) throws Exception {
        if (!navigateToSchemes(parameter)) {
            System.err.println("[Error] An error occurred when attempting to navigate to the Schemes page");
            return false;
        }
        if (!searchSchemeUpdateButton(parameter)) {
            System.err.println("[Error] An error occurred when attempting to search a Schemes");
            return false;
        }
        String schemeStatusNewBusinessLogged = seleniumDriver.getSchemeStatus(parameter); //scheme status should be New Business Logged
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeSaveButton.getElementId())) {
            return false;
        }
        filename = "Schemes_SchemeDataBeforeUpdate_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!enterSalesConsultantViaUpdate(parameter)) {
            System.err.println("[Error] An error occurred while entering Sales Consultant details");
            return false;
        }
        if (!enterBankingDetails(parameter)) {
            System.err.println("[Error] An error occurred while entering the Banking Details");
            return false;
        }
        if (!enterPaymentMethodViaUpdate(parameter, env, new String[]{"PaymentMethodType", "PaymentMethodPaymentDay"})) {
            System.err.println("[Error] An error occurred while entering the Payment Method");
            return false;
        }

        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeSaveButton.getElementId())) {
            return false;
        }
        filename = "Schemes_SchemeDataAfterUpdate_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickSaveButton(parameter)) {
            System.err.println("[Error] An occurred while clicking on the Save Button");
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfullSavedSchemeMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterInfoUpdate = seleniumDriver.getFloatingBoxMessage(parameter);
        filename = "Schemes_SuccessfullSavedSchemeMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickMinRequirementsLink(parameter)) {
            System.err.println("[Error] An error occurred while clicking the Minimum Requirements Link");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeMinReqUpdateSaveButton.getElementId())) {
            return false;
        }
        filename = "Schemes_SchemeMinReqBeforeUpdate_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!captureMinRequirementsAllYes(parameter, false)) { //the boolean value must be false for a scheme that is created via a quote and an update is done on the shceme
            System.err.println("[Error] An error occurred while capturing the Minimum Requirements");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeMinReqUpdateSaveButton.getElementId())) {
            return false;
        }
        filename = "Schemes_SchemeMinReqAfterUpdate_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
//        here you need to do a double click to move to the next screen
        if (!clickSaveButtonOnMinReq(parameter)) {
            System.err.println("[Error] An error occurred while clicking on the Save Button on the Minimum Requirements page");
            return false;
        }
        if (!clickSaveButtonOnMinReq(parameter)) {
            System.err.println("[Error] An error occurred while clicking on the Save Button on the Minimum Requirements page");
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfulSavedMinReqMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterMinReqUpdate = seleniumDriver.getFloatingBoxMessage(parameter);
        String schemeStatusNewBusinessWIP = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("[Info] ************************Completing Scheme Details************************");
        System.out.println("Scheme Number: " + schemeNumber);
        System.out.println("Scheme Status - NB Logged: " + schemeStatusNewBusinessLogged);
        System.out.println("Scheme Message After Informaation Update: " + schemeMessageAfterInfoUpdate);
        System.out.println("Scheme Message After Min Req Update: " + schemeMessageAfterMinReqUpdate);
        System.out.println("Scheme Status - NB WIP: " + schemeStatusNewBusinessWIP);
        if (!schemeStatusNewBusinessLogged.equalsIgnoreCase(SchemeStatuses.NewBusinessLogged.getSchemeStatusDescription())) {
            System.err.println("[Error] The Scheme Status should be \"" + SchemeStatuses.NewBusinessLogged.getSchemeStatusDescription() + "\" but it was "
                    + schemeStatusNewBusinessLogged);
            return false;
        }
        if (!schemeMessageAfterInfoUpdate.equalsIgnoreCase(NotificationMessages.SuccessfullyUpdatedSchemeInfoMsg.getMessage())) {
            System.err.println("[Error] The Scheme Message should be \"" + NotificationMessages.SuccessfullyUpdatedSchemeInfoMsg.getMessage() + "\" but it was "
                    + schemeMessageAfterInfoUpdate);
            return false;
        }
        if (!schemeMessageAfterMinReqUpdate.equalsIgnoreCase(NotificationMessages.SuccessfullyUpdatedMinReqMsg.getMessage())) {
            System.err.println("[Error] The Scheme Message should be \"" + NotificationMessages.SuccessfullyUpdatedMinReqMsg.getMessage() + "\" but it was "
                    + schemeMessageAfterMinReqUpdate);
            return false;
        }
        if (!schemeStatusNewBusinessWIP.equalsIgnoreCase(SchemeStatuses.NewBusinessWIP.getSchemeStatusDescription())) {
            System.err.println("[Error] The Scheme Status should be \"" + SchemeStatuses.NewBusinessWIP.getSchemeStatusDescription() + "\" but it was "
                    + schemeStatusNewBusinessWIP);
            return false;
        }
        return true;
    }

    /*
     * This method check if the 
     * Adding a new Scheme Product and true boolean value if the product added appears on the page
     */
    public Boolean addSchemeProductOptions(Parameter parameter, String env) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesAddOptionButton.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
            if (!schemePolicyAdminButton(parameter)) {
                System.err.println("[Error] An error occurred while attempting to click the Policy Admin Button");
                return false;
            }
        }
        if (!seleniumDriver.waitForIDAccessibility(PageElements.SchemesAddOptionButton.getElementId())) {
            return false;
        }
        filename = "Schemes_PolicyAdmin_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickById(PageElements.SchemesAddOptionButton, parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemesAddSchemeProductOptionButton.getElementId())) {
            return false;
        }
        filename = "Schemes_AddSchemeProductionOptionBoxBeforeSelection_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.selectionById(PageElements.SchemesAddProductDropdownBox, parameter.getSchemeProductOption().toUpperCase(), parameter)) {
            return false;
        }
        seleniumDriver.pause(2000);
        if (!seleniumDriver.selectionById(PageElements.SchemesAddCoverDropdownBox, parameter.getSchemeProductOptionCover(), parameter)) {
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemesAddSchemeProductOptionButton.getElementId())) {
            return false;
        }
        filename = "Schemes_AddSchemeProductionOptionBoxAfterSelection_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!addSchemeProductOptionButton(parameter)) {
            System.err.println("[Error] An error occurred while trying to click the Add Scheme Product Option Button");
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_AddSchemeProductionOptionAdded_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String policyAdminData = seleniumDriver.findElementById(PageElements.SchemesPolicyAdminProductDescLabel, parameter).getText();
        testCompletion = policyAdminData.contains(parameter.getSchemeProductOption());
        if (!testCompletion) {
            System.err.println("[Error] An error occurred while attempting to Add a Product Option");
            return false;
        }
        return true;
    }

    /*
     * Adding New Members (data collected from a Members Data file) to a Scheme. 
     * If the member already exist then the member will be added as an existing member
     */
    public Boolean addSchemeMembersViaPolicyAdmin(Parameter parameter, String env, List<MemberDetails> memDetails) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesAddingPrincipleMemberButton.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred while attempting to search a scheme");
                return false;
            }
            if (!schemePolicyAdminButton(parameter)) {
                System.err.println("[Error] An error occurred while attempting to click the Policy Admin Button");
                return false;
            }
        }
        if (!captureMembers(parameter, env, memDetails)) {
            System.err.println("[Error] An error occurred while capturing members");
            return false;
        }
        return true;
    }

    /*
     * Doing an Assessment on a newly created Scheme with members
     */
    public boolean setSchemeToBeAssessed(Parameter parameter, String env) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SetSchemeToBeAssessedButton.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred while attempting to search a scheme");
                return false;
            }
            if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
                System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
                return false;
            }
        }
        if (!seleniumDriver.waitForIDAccessibility(PageElements.SetSchemeToBeAssessedButton.getElementId())) {
            return false;
        }
        filename = "Schemes_PolicyAdminAfterMemberAdded_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickById(PageElements.SetSchemeToBeAssessedButton, parameter)) {
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        Scheme scheme = dao.getSchemeData(schemeNumber, env);
        if (scheme.getProductTypeID().getProductTypeName().equalsIgnoreCase(ProductType.GFS.getProductTypeName())
                || scheme.getProductTypeID().getProductTypeName().equalsIgnoreCase(ProductType.GFSCustomised.getProductTypeName())) {
            seleniumDriver.pause(2000);
            filename = "Schemes_AssessSchemeQuestions_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
//            drilling down to the confirm button
            if (!seleniumDriver.clickConfirmButtonOnFloatingMessage(PageElements.FloatingMessageBoxConfirmButton)) {
                return false;
            }
            if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeAssessButton.getElementId())) {
                return false;
            }
            filename = "Schemes_AssessScheme_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            if (!seleniumDriver.selectionByName(PageElements.SchemeAssessMinWaitPeriodSelectionBox, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
            if (!seleniumDriver.selectionByName(PageElements.SchemeAssessMaxWaitPeriodSelectionBox, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
        } else {
            seleniumDriver.waitForXpathAccessibility(PageElements.SchemeAssessButton.getElementId());
            filename = "Schemes_AssessScheme_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            List<WebElement> rows = seleniumDriver.driver.findElements(By.xpath("//div[@id='schemeassessWrapper']//form[@id='frmAssess']//fieldset//table//tr//table//tbody//tr"));
            for (WebElement row : rows) {
                if (row.getText().contains(parameter.getProductType().toUpperCase())) {
                    List<WebElement> columns = row.findElements(By.tagName("td"));
                    for (WebElement column : columns) {
                        if (column.getText().contains(parameter.getProductType().toUpperCase())) {
                            WebElement nextColumn = column.findElement(By.xpath("following-sibling::td"));
                            List<WebElement> elements = nextColumn.findElements(By.xpath("select"));
                            if (elements != null) {
                                if (!seleniumDriver.selectionById(PageElements.SchemeAssessCoreFamilyOptionRiskDropDown, "Lower Risk Select", parameter)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            if (!seleniumDriver.selectionById(PageElements.SchemeAssessCoreFamilyOptionPMWaitingPeriodDropDown, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
            if (!seleniumDriver.selectionById(PageElements.SchemeAssessCoreFamilyOptionADWaitingPeriodDropDown, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
        }
        if (!seleniumDriver.clickById(PageElements.SchemeAssessmentAcceptRadioButton, parameter)) {
            return false;
        }
        String schemeStatusToBeAssessed = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("[Info] Scheme Status - To Be Assessed: " + schemeStatusToBeAssessed);
        if (!schemeStatusToBeAssessed.equalsIgnoreCase(SchemeStatuses.ToBeAssessed.getSchemeStatusDescription())) {
            System.err.println("[Error] The Scheme status should have been \'" + SchemeStatuses.ToBeAssessed.getSchemeStatusDescription() + "\' but it is in a/n"
                    + schemeStatusToBeAssessed);
            return false;
        }
        if (!seleniumDriver.clickByXpath(PageElements.SchemeAssessButton, parameter)) {
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfullyAssessedMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterAssessment = seleniumDriver.getFloatingBoxMessage(parameter);
        System.out.println("[Info] Scheme Message After Scheme Assessed: " + schemeMessageAfterAssessment);
        if (!schemeMessageAfterAssessment.equalsIgnoreCase(NotificationMessages.SuccessfullySavedSchemeAssessmentMsg.getMessage())) {
            System.err.println("[Error] The displayed Scheme cancel message: \'" + schemeMessageAfterAssessment + "\' is incorrect. It should have been: \'"
                    + NotificationMessages.SuccessfullySavedSchemeAssessmentMsg.getMessage() + "\'");
            return false;
        }
        System.out.println("[Info] Scheme Number: " + schemeNumber);
        scheme = dao.getSchemeData(schemeNumber, env);
        if (!seleniumDriver.waitForLinkAccessibility(PageElements.SchemesCreateBSSPLink.getElementId())) {
            return false;
        }
        filename = "Schemes_InfoViewBeforeStatusChangeToAwait1stPrem_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
//        here we are force the scheme to go to the next status which is Awaiting First premium
        if (!acceptSchemeAssesment(scheme, env)) {
            System.err.println("[Error] An error occurred while trying to AcceptSchemeAssessment");
            return false;
        }
        seleniumDriver.refreshPage();
        if (!seleniumDriver.waitForLinkAccessibility(PageElements.SchemesCreateBSSPLink.getElementId())) {
            return false;
        }
        filename = "Schemes_InfoViewAfterStatusChangeToAwait1stPrem_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeStatusAwaitingFirstPremium = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("[Info] Scheme Status - Awaiting First Premium: " + schemeStatusAwaitingFirstPremium);
        if (!schemeStatusAwaitingFirstPremium.equalsIgnoreCase(SchemeStatuses.AwaitingFirstPremium.getSchemeStatusDescription())) {
            System.err.println("[Error] The Scheme status should have been \'" + SchemeStatuses.AwaitingFirstPremium.getSchemeStatusDescription() + "\' but it is in a/n"
                    + schemeStatusAwaitingFirstPremium);
            return false;
        }
        System.out.println("[Info] Scheme successfully Assessed");
        return true;
    }

    /*
     * Doing a cash confirmation(for cash schemes) or advance debit order payment(for debit order schemes) on Scheme
     */
    public boolean acceptingSchemeFirstPaymentAndActivateScheme(Parameter parameter, String env) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesCashConfirmationButton.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
        }
        if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        if (parameter.getSchemePaymentMethodType().equals("Debit Order")) {
//            if the scheme payment mehtod is a debit order, then an advance payment will be made via a stored procedure
            if (!dao.doAdvancePaymentForDebitOrderScheme(schemeNumber, env)) {
                System.err.println("[Error] An error occurred while attempting do an Advance Payment for Debit Order Scheme");
                return false;
            }
        } else {
//            if the scheme payment method is a cash, then a cash confirmation payment must be made
            if (!doSchemeCashConfirmation(parameter, env, seleniumDriver.getDate(0, 0, 0), false)) {
                System.err.println("[Error] An error occurred while attempting to do a cash confirmation for the scheme");
                return false;
            }
        }
        if (activateSchemeViaJavaCode(env)) {
            seleniumDriver.refreshPage();
            seleniumDriver.pause(2000);
            String schemeStatusActive = seleniumDriver.getSchemeStatus(parameter);
            System.out.println("Scheme Status - Active: " + schemeStatusActive);
            filename = "Schemes_SchemeStatusChangedToActive_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            if (!schemeStatusActive.equalsIgnoreCase(SchemeStatuses.Active.getSchemeStatusDescription())) {
                System.err.println("[Error] The Scheme status should have been \'" + SchemeStatuses.Active.getSchemeStatusDescription() + "\' but it is in a/n"
                        + schemeStatusActive + " status");
                return false;
            }
//            this is not needed - it is just downloading data which will not be verified
//            if (!doDownloadAgeProfile(parameter)) {
//                System.err.println("[Error] The Download Age Profile function did not executed successfully.");
//                return false;
//            }
        }
        return true;
    }

    public boolean cancelScheme(Parameter parameter, String env) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemeCancelSchemeButton.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
            if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
                System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
                return false;
            }
        }
        if (!cancelSchemeLink(parameter)) {
            System.err.println("[Error] An error occurred while attemping to click on the Cancel Scheme Link");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeCancelSchemeButton.getElementId())) {
            return false;
        }
        filename = "Schemes_CancelOptionsBeforeCaptured_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!selectCancelSchemeReason(parameter)) {
            System.err.println("[Error] An error occurred while selecting the cancel reasons");
            return false;
        }
        if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemeCancelSchemeButton.getElementId())) {
            return false;
        }
        filename = "Schemes_CancelOptionsAfterCaptured_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickCancelSchemeButton(parameter)) {
            System.err.println("[Error] An error occurred while attempting to click on the Cancel Scheme Button");
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_CancelConfirmationMsg_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickConfirmButtonOnFloatingMessage(PageElements.FloatingMessageBoxConfirmButton)) {
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfulCancellation_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterCancellation = seleniumDriver.getFloatingBoxMessage(parameter);
//        inserted code to get the scheme status to cancel immediately and not wait for the long running job
//        if (!executeEF.runCancelScheme(env)) {
//            System.err.println("[Error] An error occurred while attempting to run the Cancel Scheme Job");
//            return false;
//        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        System.out.println("[Info] Scheme Number: " + schemeNumber);
        Scheme scheme = dao.getSchemeData(schemeNumber, env);
//        here we are force the scheme to go to the next status which is Awaiting First premium
        if (!cancelASpecificScheme(scheme, env)) {
            System.err.println("[Error] An error occurred while attempting to cancel the scheme");
            return false;
        }
        seleniumDriver.refreshPage();
        String schemeStatusCancel = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("Scheme Cancellation Message: " + schemeMessageAfterCancellation);
        System.out.println("Scheme Status - Cancelled: " + schemeStatusCancel);
        if (!schemeMessageAfterCancellation.contains(NotificationMessages.SuccessfullyCancelledMsg.getMessage())) {
            System.err.println("[Error] The displayed Scheme cancel message: \'" + schemeMessageAfterCancellation + "\' is incorrect. It should have been: \'"
                    + NotificationMessages.SuccessfullyCancelledMsg.getMessage() + "\'");
            return false;
        }
        if (!schemeStatusCancel.equalsIgnoreCase(SchemeStatuses.Cancelled.getSchemeStatusDescription())) {
            System.err.println("[Error] The Scheme status should have been \'" + SchemeStatuses.Cancelled.getSchemeStatusDescription() + "\' but it is in a/n "
                    + schemeStatusCancel);
            return false;
        }
        return true;
    }
//    this method will execute a daily raising for debit order schemes or a cash confirmation for cash schemes

    public boolean createDailyRaisingAndYMOutputFile(Parameter parameter, String env) throws Exception {
        if (parameter.getSchemePaymentMethodType().equalsIgnoreCase("Debit Order")) {
            seleniumDriver.pause(2000);
            String pgSource = seleniumDriver.getPageSource();
            if (!pgSource.contains(PageElements.SchemesCancelLink.getElementId())) {
                if (!searchSchemeUpdateButton(parameter)) {
                    System.err.println("[Error] An error occurred when attempting to search a Schemes");
                    return false;
                }
            }
            if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
                System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
                return false;
            }
            /*
             *  Perform a daily raising on the scheme
             */
            schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
            if (!executeEF.runDailyRaisingJob(env)) {
                System.err.println("[Error] An error occurred while attempting to run the Daily Raising Job");
                return false;
            }
            if (!executeEF.runProcessOuputYMfile(env)) {
                System.err.println("[Error] An error occurred while attempting to run the Process YM Output Job");
                return false;
            }
//        navigate to the Transaction History page to see if the transaction was recorded
            if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
                System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
                return false;
            }
            if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesTransactionHistoryTable.getElementId(), "Transaction Date")) {
                System.err.println("[Error] Unable to find the Transaction History Table");
                return false;
            }
            filename = "Schemes_TransactionHistoryAfterDailyRaising_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        } else {
            System.out.println("[Warning] The Payment Method of this scheme is NOT of Type Debit Order - a cash confirmation will be done");
//            if the scheme payment method is a cash, then a cash confirmation payment must be made
            if (!doSchemeCashConfirmation(parameter, env, seleniumDriver.getDate(0, 0, 0), false)) {
                System.err.println("[Error] An error occurred while attempting to do a cash confirmation for the scheme");
                return false;
            }
        }
//        TODO -- Insert a check to see if a transaction has been recorded for this daily raising
        return true;
    }

    public boolean createRandomDailyRaisingAndYMOutputFile(Parameter parameter, String env) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesCancelLink.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
        }
        if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
            return false;
        }
        /*
         *  Perform a daily raising 
         */
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        if (!executeEF.runDailyRaisingJob(env)) {
            System.err.println("[Error] An error occurred while attempting to run the Daily Raising Job");
            return false;
        }
        if (!executeEF.runProcessOuputYMfile(env)) {
            System.err.println("[Error] An error occurred while attempting to run the Process YM Output Job");
            return false;
        }
//        navigate to the Transaction History page to see if the transaction was recorded
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesTransactionHistoryTable.getElementId(), "Transaction Date")) {
            System.err.println("[Error] Unable to find the Transaction History Table");
            return false;
        }
        filename = "Schemes_TransactionHistoryAfterDailyRaising_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }

    public boolean lapsedOrLapsePendingScheme(Parameter parameter, String env, String schemeStatus, String rdReason) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesCancelLink.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
        }
        if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        if (!dao.updateDebitOrderEntryForRD(env, schemeNumber, rdReason)) {
            return false;
        }
//        navigate to the Transaction History page to see if the transaction was recorded
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesTransactionHistoryTable.getElementId(), "Transaction Date")) {
            System.err.println("[Error] Unable to find the Transaction History Table");
            return false;
        }
        filename = "Schemes_TransactionHistoryAfterDailyRaising_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
//        TODO -- need to change this to running the actual service and not doing the changes to the database
        if (!dao.cascadeStatus(env, schemeNumber, schemeStatus)) {
            return false;
        }
        if (schemeStatus.equalsIgnoreCase(SchemeStatuses.LapsePending.getSchemeStatusDescription())) {
            if (!verifySchemeLapsePendingStatus(parameter, schemeStatus)) {
                System.err.println("[Error] The scheme is not successfully set to Lapse Pending Status");
                return false;
            }
        }
        if (schemeStatus.equalsIgnoreCase(SchemeStatuses.Lapsed.getSchemeStatusDescription())) {
            if (!verifySchemeLapsedStatus(parameter, schemeStatus)) {
                System.err.println("[Error] The scheme is not successfully set to Lapses Status");
                return false;
            }
        }
        return true;
    }

    public boolean newBusinessNonPaymentOrNotTakenScheme(Parameter parameter, String env, String schemeStatus, String rdReason) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesCancelLink.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
        }
        if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        if (!dao.updateDebitOrderEntryForRD(env, schemeNumber, rdReason)) {
            return false;
        }
        if (!dao.cascadeStatus(env, schemeNumber, schemeStatus)) {
            return false;
        }
        if (schemeStatus.equalsIgnoreCase(SchemeStatuses.NewBusinessNonPayment.getSchemeStatusDescription())) {
            if (!verifySchemeNewBusinessNonPayment(parameter, schemeStatus)) {
                System.err.println("[Error] The scheme is not successfully set to New Business Non Payment Status");
                return false;
            }
        }
        if (schemeStatus.equalsIgnoreCase(SchemeStatuses.NotTaken.getSchemeStatusDescription())) {
            if (!verifySchemeNotTaken(parameter, schemeStatus)) {
                System.err.println("[Error] The scheme is not successfully set to Not Taken Status");
                return false;
            }
        }
        return true;
    }

    public boolean verifyNewTransactionEntryInYMOutputFile(Parameter parameter, String env, String dateRaised) throws Exception {
        if (parameter.getSchemePaymentMethodType().equalsIgnoreCase("Debit Order")) {
            seleniumDriver.pause(2000);
            String pgSource = seleniumDriver.getPageSource();
            if (!pgSource.contains(PageElements.SchemesCancelLink.getElementId())) {
                if (!searchSchemeUpdateButton(parameter)) {
                    System.err.println("[Error] An error occurred when attempting to search a Schemes");
                    return false;
                }
            }
            if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
                System.err.println("[Error] An error occurred while attempting to navigate to Transaction History Link Under Actions Side Pane");
                return false;
            }
            if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesTransactionHistoryTable.getElementId(), "Transaction Date")) {
                System.err.println("[Error] Unable to find the Transaction History Table");
                return false;
            }
            filename = "Schemes_TransactionHistory_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
            schemePremiumsRaisedFor = dao.getSchemePremiumsRaised(env, schemeNumber, dateRaised).getAmountDue();
            if (!executeEF.checkYMOutputFileForTransactions("New", schemeNumber, schemePremiumsRaisedFor, parameter)) {
                System.err.println("[Error] An error occurred while attempting to Check YMOutput File For Transactions");
                return false;
            }
        }
        return true;
    }

    public boolean verifyDeleteTransactionEntryInYMOutputFile(Parameter parameter, String env, String dateRaised) throws Exception {
        if (parameter.getSchemePaymentMethodType().equalsIgnoreCase("Debit Order")) {
            seleniumDriver.pause(2000);
            String pgSource = seleniumDriver.getPageSource();
            if (!pgSource.contains(PageElements.SchemesCancelLink.getElementId())) {
                if (!searchSchemeUpdateButton(parameter)) {
                    System.err.println("[Error] An error occurred when attempting to search a Schemes");
                    return false;
                }
            }
            if (!navigateToPolicyAdminLinkUnderActionsSidePane(parameter)) {
                System.err.println("[Error] An error occurred while attempting to navigate to Policy Admin Link Under Actions Side Pane");
                return false;
            }
            schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
            schemePremiumsRaisedFor = dao.getSchemePremiumsRaised(env, schemeNumber, dateRaised).getAmountDue();
            if (!executeEF.checkYMOutputFileForTransactions("Delete", schemeNumber, schemePremiumsRaisedFor, parameter)) {
                System.err.println("[Error] An error occurred while attempting to Check YMOutput File For Transactions");
                return false;
            }
        }
        return true;
    }

    public boolean activatingTheScheme(Parameter parameter, String env, Boolean isViaWorkflowService) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesCashConfirmationButton.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
        }
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);

        if (isViaWorkflowService) {
            if (!executeEF.runOneFMWorkflowService(env)) {
                System.err.println("[Error] An error occurred while attempting to activate the scheme via the OneFM Workflow Service");
                return false;
            }
        } else {
            if (!activateSchemeViaJavaCode(env)) {
                System.err.println("[Error] An error occurred while attempting to activate the scheme via the JAVA Code");
                return false;
            }
        }
        seleniumDriver.refreshPage();
        seleniumDriver.pause(2000);
        String schemeStatusActive = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("Scheme Status - Active: " + schemeStatusActive);
        filename = "Schemes_SchemeStatusChangedToActive_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!schemeStatusActive.equalsIgnoreCase(SchemeStatuses.Active.getSchemeStatusDescription())) {
            System.err.println("[Error] The Scheme status should have been \'" + SchemeStatuses.Active.getSchemeStatusDescription() + "\' but it is in a/n "
                    + schemeStatusActive + " status");
            return false;
        }
        return true;
    }

    public boolean changeSchemePaymentMethod(Parameter parameter, String env, String[] paymentMethodToBeChanged) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesSchemeUpdateLink.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
        } else {
            if (!clickSchemeUpdate(parameter)) {
                System.err.println("[Error] An error occurred when attempting to navigate to Scheme Update");
                return false;
            }
        }
        if (!enterPaymentMethodViaUpdate(parameter, env, paymentMethodToBeChanged)) {
            System.err.println("[Error] An error occurred when attempting to change the payment method to debit order");
            return false;
        }
        filename = "Schemes_PaymentMethodChanged_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickSaveButton(parameter)) {
            System.err.println("[Error] An error occurred when attempting to change the payment method to debit order");
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfullUpdateOfSchemeMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterInfoUpdate = seleniumDriver.getFloatingBoxMessage(parameter);
        filename = "Schemes_SuccessfullUpdatedSchemeMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!schemeMessageAfterInfoUpdate.equalsIgnoreCase(NotificationMessages.SuccessfullyUpdatedSchemeInfoMsg.getMessage())) {
            System.err.println("[Error] The Scheme Message should be \"" + NotificationMessages.SuccessfullyUpdatedSchemeInfoMsg.getMessage() + "\" but it was "
                    + schemeMessageAfterInfoUpdate);
            return false;
        }
        return true;
    }
//    newly implemented for the cash back functionality

    public boolean checkCashBackOptionViaUpdate(Parameter parameter) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesSchemeUpdateLink.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
        } else {
            if (!clickSchemeUpdate(parameter)) {
                System.err.println("[Error] An error occurred when attempting to navigate to Scheme Update");
                return false;
            }
        }
        if (!clickCashBackIndicatorViaUpdate(parameter)) {
            System.err.println("[Error] An error occrurred when attempting to click Cash Back Indicator");
            return false;
        }
        filename = "Schemes_CashBackInidcatorChecked_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!clickSaveButton(parameter)) {
            System.err.println("[Error] An error occurred when attempting to check the cash back indicator");
            return false;
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfullUpdateOfSchemeMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterInfoUpdate = seleniumDriver.getFloatingBoxMessage(parameter);
        filename = "Schemes_SuccessfullUpdatedSchemeMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!schemeMessageAfterInfoUpdate.equalsIgnoreCase(NotificationMessages.SuccessfullyUpdatedSchemeInfoMsg.getMessage())) {
            System.err.println("[Error] The Scheme Message should be \"" + NotificationMessages.SuccessfullyUpdatedSchemeInfoMsg.getMessage() + "\" but it was "
                    + schemeMessageAfterInfoUpdate);
            return false;
        }
        return true;
    }

    /**
     * ***************************************PRIVATE
     * CLASSESS******************************************************************
     */
    private boolean captureMinRequirementsAllYes(Parameter parameter, Boolean isNew) throws Exception {
        WebElement minReqTable = seleniumDriver.findElementById(isNew ? PageElements.SchemesMinRequirementId : PageElements.SchemesMinRequirementIdUpdate, parameter);
        List<WebElement> allRows = minReqTable.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            if (row.getText().contains("Batch Header")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Application Form fully completed")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Member Data")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Proposal & Declaration")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Constitution")) {
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
//                    else {
//                        System.err.println("[Error] Unable to click the " + rowElement.getText() + " Yes button");
//                    }
                    if (!parameter.isExternalVendor() && rowElement.getText().contains("No")) {
                        rowElement.click();
                    }
//                    else {
//                        System.err.println("[Error] Unable to click the " + rowElement.getText() + " No button");                        
//                    }
                }
            }
            if (row.getText().contains("External Vendor Name") && parameter.isExternalVendor()) {
                String externalVendorNameId = row.findElement(By.tagName("select")).getAttribute("id");
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
//                    else {
//                        System.err.println("[Error] Unable to click the " + rowElement.getText() + " Yes button");
//                    }
                    if (!parameter.isReIssuedBusiness() && rowElement.getText().contains("No")) {
                        rowElement.click();
                    }
//                    else {
//                        System.err.println("[Error] Unable to click the " + rowElement.getText() + " No button");                        
//                    }
                }
            }
            if (row.getText().contains("Re-Issued Business Reason") && parameter.isReIssuedBusiness()) {
                String reIssueBusinessReasonId = row.findElement(By.tagName("select")).getAttribute("id");
                if (!seleniumDriver.selectionByIdStingValueExistingRow(row, reIssueBusinessReasonId, "Scheme cancelled within 12 months", parameter, PageElements.SomeElement)) {
                    return false;
                }
            }
            if (row.getText().contains("Resolution")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Bank Statement")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Historical Background")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Financial Declaration")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Intermediary and Legislative Disclosure")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Policy Holder Protection Rules")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Financial Needs Analysis")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Waiting Period Letter")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Letter from Previous Underwriter")) {
                if (!seleniumDriver.clickByClassNameFromExistingElement(row, PageElements.SchemesMinReqsYesOption, parameter)) {
                    return false;
                }
            }
            if (row.getText().contains("Date Received")) {
                List<WebElement> rowElements = row.findElements(By.tagName("input"));
                for (int i = 0; i < rowElements.size(); i++) {
                    String dateReceivedId = rowElements.get(i).getAttribute("id");
                    ((JavascriptExecutor) seleniumDriver.driver).executeScript("document.getElementById('" + dateReceivedId + "').value = '" + seleniumDriver.getDate(0, -1, -4) + "'");
                }
            }
        }
        return true;
    }

    private boolean captureSchemeDetails(Parameter parameter, String env) throws Exception {
//        capture scheme data
        if (!enterSchemeData(parameter)) {
            System.err.println("[Error] Unable to capture the Scheme Data");
            return false;
        }
        if (!enterContactPerson(parameter)) {
            System.err.println("[Error] Unable to capture the Contact Person Data");
            return false;
        }
        if (!enterAddress(parameter)) {
            System.err.println("[Error] Unable to capture the Address Data");
            return false;
        }
        if (!enterBankingDetails(parameter)) {
            System.err.println("[Error] Unable to capture the Banking Details");
            return false;
        }
        if (!enterPaymentMethod(parameter, env)) {
            System.err.println("[Error] Unable to capture the Payment Method");
            return false;
        }
        if (!enterServicingConsultant(parameter)) {
            System.err.println("[Error] Unable to capture the Servicing Consultant Data");
            return false;
        }
        if (!enterSalesConsultant(parameter)) {
            System.err.println("[Error] Unable to capture the Sales Consultant Data");
            return false;
        }
        return true;
    }

    private boolean enterPrincipalMemberDetails(Parameter parameter, String id, String title, String surname, String firstname, String gender, String dob, String nextRelationship, String env) throws Exception {
        if (!seleniumDriver.typeById(PageElements.PrincipalMembersIdNumberTextBox, id, parameter)) {
            return false;
        }
        if (!seleniumDriver.selectionById(PageElements.PrincipalMembersTitleDropDownBox, title, parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.PrincipalMembersSurnameTextBox, surname, parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.PrincipalMembersFirstnameTextBox, firstname, parameter)) {
            return false;
        }
        if (!seleniumDriver.selectionById(PageElements.PrincipalMembersGenderDropDownBox, gender, parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.PrincipalMembersDateOfBirthTextBox, dob, parameter)) {
            return false;
        }
//        if the status of the scheme is active then the waiting period and join date must be added
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        String schemeStatus = dao.getSchemeStatusWithSchemeNumber(schemeNumber, env);
        if (schemeStatus.equalsIgnoreCase("Active")) {
            if (!seleniumDriver.selectionById(PageElements.PrincipalMembersWaitingPeriodDropDownBox, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.PrincipalMembersJoinDateTextBox, seleniumDriver.getDate(0, 0, 0), parameter)) {
                return false;
            }
        }
        if (isGFSproducts(parameter)) {
            seleniumDriver.pause(2000);
            if (!seleniumDriver.selectionById(PageElements.PrincipalMemberCoverDropDownBox, parameter.getSchemeProductOptionCover(), parameter)) {
                return false;
            }
        }
        if (!seleniumDriver.selectionById(PageElements.PrincipalMembersNextActionsDropDownBox, nextRelationship, parameter)) {
            return false;
        }
        return true;
    }

    private boolean enterExistingPrincipalMemberDetails(Parameter parameter, String id, String title, String surname, String firstname, String gender, String dob, String nextRelationship, String env) throws Exception {
        if (!seleniumDriver.typeById(PageElements.PrincipalMembersIdNumberTextBox, id, parameter)) {
            return false;
        }
        seleniumDriver.pause(2000);
        if (!seleniumDriver.tabById(PageElements.PrincipalMembersIdNumberTextBox, parameter)) {
            return false;
        }
//        if the status of the scheme is active then the waiting period and join date must be added
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        String schemeStatus = dao.getSchemeStatusWithSchemeNumber(schemeNumber, env);
        if (schemeStatus.equalsIgnoreCase("Active")) {
            if (!seleniumDriver.selectionById(PageElements.PrincipalMembersWaitingPeriodDropDownBox, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.PrincipalMembersJoinDateTextBox, seleniumDriver.getDate(0, 0, 0), parameter)) {
                return false;
            }
        }
        System.out.println("Products isGFS: " + isGFSproducts(parameter));
        if (isGFSproducts(parameter)) {
            seleniumDriver.pause(2000);
            if (!seleniumDriver.selectionById(PageElements.PrincipalMemberCoverDropDownBox, parameter.getSchemeProductOptionCover(), parameter)) {
                return false;
            }
        }
        if (!seleniumDriver.selectionById(PageElements.PrincipalMembersNextActionsDropDownBox, nextRelationship, parameter)) {
            return false;
        }
        return true;
    }

    private boolean enterPolicyMemberDetails(Parameter parameter, String currentRelationship, String id, String title, String surname, String firstname, String gender, String dob, String nextRelationship, String env) throws Exception {
        if (!seleniumDriver.typeById(PageElements.PolicyMembersIdNumberTextBox, id, parameter)) {
            return false;
        }
        if (!seleniumDriver.selectionById(PageElements.PolicyMembersTitleDropDownBox, title, parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.PolicyMembersSurnameTextBox, surname, parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.PolicyMembersFirstnameTextBox, firstname, parameter)) {
            return false;
        }
        if (!seleniumDriver.selectionById(PageElements.PolicyMembersGenderDropDownBox, gender, parameter)) {
            return false;
        }
        if (!seleniumDriver.typeById(PageElements.PolicyMembersDateOfBirthTextBox, dob, parameter)) {
            return false;
        }
//        if the status of the scheme is active then the waiting period and join date must be added 
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        String schemeStatus = dao.getSchemeStatusWithSchemeNumber(schemeNumber, env);
        if (schemeStatus.equalsIgnoreCase("Active")) {
            if (!seleniumDriver.selectionById(PageElements.PolicyMembersWaitingPeriodDropDownBox, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.PolicyMembersJoinDateTextBox, seleniumDriver.getDate(0, 0, 0), parameter)) {
                return false;
            }
        }
        if (currentRelationship.equals("Child") && isMemberOlderThan21(dob)) {
            if (!seleniumDriver.selectionById(PageElements.PolicyMembersExceptionDropDownBox, "Disabled", parameter)) {
                return false;
            }
        }
        if (isGFSproducts(parameter) && currentRelationship.equals("Adult Dependant Extended")) {
            seleniumDriver.pause(2000);
            if (!seleniumDriver.selectionById(PageElements.PolicyMemberCoverDropDownBox, parameter.getSchemeProductOptionCover(), parameter)) {
                return false;
            }
        }
        if (!seleniumDriver.selectionById(PageElements.PolicyMembersNextActionsDropDownBox, nextRelationship, parameter)) {
            return false;
        }
        return true;
    }

    private boolean enterExistingPolicyMemberDetails(Parameter parameter, String currentRelationship, String id, String title, String surname, String firstname, String gender, String dob, String nextRelationship, String env) throws Exception {
        if (!seleniumDriver.typeById(PageElements.PolicyMembersIdNumberTextBox, id, parameter)) {
            return false;
        }
        seleniumDriver.pause(2000);
        if (!seleniumDriver.tabById(PageElements.PolicyMembersIdNumberTextBox, parameter)) {
            return false;
        }
//        if the status of the scheme is active then the waiting period and join date must be added 
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        String schemeStatus = dao.getSchemeStatusWithSchemeNumber(schemeNumber, env);
        if (schemeStatus.equalsIgnoreCase("Active")) {
            if (!seleniumDriver.selectionById(PageElements.PolicyMembersWaitingPeriodDropDownBox, parameter.getWaitingPeriod(), parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.PolicyMembersJoinDateTextBox, seleniumDriver.getDate(0, 0, 0), parameter)) {
                return false;
            }
        }
        if (currentRelationship.equals("Child") && isMemberOlderThan21(dob)) {
            if (!seleniumDriver.selectionById(PageElements.PolicyMembersExceptionDropDownBox, "Disabled", parameter)) {
                return false;
            }
        }
        if (isGFSproducts(parameter) && currentRelationship.equals("Adult Dependant Extended")) {
            seleniumDriver.pause(2000);
            if (!seleniumDriver.selectionById(PageElements.PolicyMemberCoverDropDownBox, parameter.getSchemeProductOptionCover(), parameter)) {
                return false;
            }
        }
        if (!seleniumDriver.selectionById(PageElements.PolicyMembersNextActionsDropDownBox, nextRelationship, parameter)) {
            return false;
        }
        return true;
    }

    private boolean isMemberOlderThan21(String dob) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(dob);
        int age = seleniumDriver.getAge(date);
        return age >= 21;
    }

    /*
     * getting the amount due on the page and add it to the premium received field
     */
    private String getAmountDueFromPage(Parameter parameter, boolean isAmountDueOver) throws Exception {
        String amountDue = null;
        try {
            WebElement cashPayments = seleniumDriver.findElementById(PageElements.SchemesCashPaymentLabel, parameter);
            List<WebElement> getCashPaymentRows = cashPayments.findElements(By.tagName("tr"));
            for (WebElement paymentDueRow : getCashPaymentRows) {
                if (paymentDueRow.getText().contains("Payment Due")) {
                    List<WebElement> getAmountDueRows = paymentDueRow.findElements(By.tagName("tr"));
                    for (WebElement getAmountDueRow : getAmountDueRows) {
                        if (getAmountDueRow.getText().contains("Amount Due")) {
                            List<WebElement> getAmountDueColumns = getAmountDueRow.findElements(By.tagName("td"));
                            for (WebElement getAmountDueColumn : getAmountDueColumns) {
                                if (getAmountDueColumn.getText().contains("Amount Due")) {
//                                    selenium is used to get the value of the column's sibling
                                    WebElement nextAmountDueColumn = getAmountDueColumn.findElement(By.xpath("following-sibling::td"));
                                    amountDue = nextAmountDueColumn.getText().trim().replace(",", "").replace("R ", "");
                                    if (isAmountDueOver) {
                                        double incAmountDue = Double.valueOf(amountDue) + 50;
                                        //formatting to 2decimal place
                                        DecimalFormat formatter = new DecimalFormat("#0.00");
                                        amountDue = String.valueOf(formatter.format(incAmountDue));
//                                        System.out.printf("[Info] Increased Amount Due is %.2f" + incAmountDue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return amountDue;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to get the Amount Due from the page - " + ex.getMessage());
            return null;
        }
    }

    /*
     * getting the amount due on the page and add it to the premium received field
     */
    private String getPaymentTypeFromPage(Parameter parameter) throws Exception {
        String paymentType = null;
        try {
            WebElement cashPayments = seleniumDriver.findElementById(PageElements.SchemesCashPaymentLabel, parameter);
            List<WebElement> getCashPaymentRows = cashPayments.findElements(By.tagName("tr"));
            for (WebElement paymentDueRow : getCashPaymentRows) {
                if (paymentDueRow.getText().contains("Payment Due")) {
                    List<WebElement> getAmountDueRows = paymentDueRow.findElements(By.tagName("tr"));
                    for (WebElement getAmountDueRow : getAmountDueRows) {
                        if (getAmountDueRow.getText().contains("Payment Type")) {
                            List<WebElement> getAmountDueColumns = getAmountDueRow.findElements(By.tagName("td"));
                            for (WebElement getAmountDueColumn : getAmountDueColumns) {
                                if (getAmountDueColumn.getText().contains("Payment Type")) {
                                    WebElement nextAmountDueColumn = getAmountDueColumn.findElement(By.xpath("following-sibling::td"));
                                    paymentType = nextAmountDueColumn.getText().trim();
                                }
                            }
                        }
                    }
                }
            }
            return paymentType;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to get the Payment Type from the page - " + ex.getMessage());
            return null;
        }
    }

    private String getNextRelationship(String memDetails) {
        try {
            String relationship = " - Do nothing - ";
            if (memDetails != null && memDetails.equalsIgnoreCase("Spouse")) {
                relationship = "Spouse";
            }
            if (memDetails != null && memDetails.equalsIgnoreCase("Child")) {
                relationship = "Child";
            }
            if (memDetails != null && memDetails.equalsIgnoreCase("Adult Dependant Extended")) {
                relationship = "Adult Dependant/Extended";
            }
            if (memDetails == null) {
                relationship = " - Do nothing - ";
            }
            return relationship;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while getting the Next Relationship - " + ex.getMessage());
            return null;
        }
    }

    private boolean clickAddMemberButton(String currentRelationship, Parameter parameter) throws Exception {
        if (!seleniumDriver.waitForIDAccessibility(PageElements.SchemeAddMemberButton.getElementId())) {
            return false;
        }
        filename = "Schemes_Adding_" + currentRelationship + "_Member_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        if (!seleniumDriver.clickById(PageElements.SchemeAddMemberButton, parameter)) {
            return false;
        }
        seleniumDriver.pause(2000);
        return true;
    }

    private boolean compareIds(String memberId, String id) {
        try {
            Boolean isCustNo = false;

            System.out.println("Id from database: " + memberId);
            System.out.println("Id from datafile: " + id);
            if (memberId.equals(id)) {
                isCustNo = true;
            }
            System.out.println("Id numbers are equal: " + isCustNo);
            return isCustNo;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while trying to compare Id's - " + ex.getMessage());
            return false;
        }
    }

//    java code is doing what the AcceptSchemeAssessment scheduled job would do to change the status
    private boolean acceptSchemeAssesment(Scheme scheme, String env) throws Exception {
        try {
            if (scheme.getProductTypeID().getProductTypeName().equalsIgnoreCase("GFS")) {
                if (!dao.setGFSSchemeStatus(scheme, env)) {
                    System.err.println("[Error] An error occurred while attempting to set the GFS Scheme status - " + ex.getMessage());
                    return false;
                }
            }
            if (scheme.getProductTypeID().getProductTypeName().equalsIgnoreCase("BSSP")) {
                if (!dao.setBSSPSchemeStatus(scheme, env)) {
                    System.err.println("[Error] An error occurred while attempting to set the BSSP Scheme status - " + ex.getMessage());
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] Unable to Accept Scheme Assessment - " + ex.getMessage());
            return false;
        }
    }

//    java code is doing what the CancelScheme scheduled job would do to change the status
    private boolean cancelASpecificScheme(Scheme scheme, String env) throws Exception {
        try {
            if (!dao.setSchemeStatusToCancel(scheme, env)) {
                System.err.println("[Error] An error occurred while attempting to set the Scheme status to Cancel - " + ex.getMessage());
                return false;
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] Unable to Cancel Scheme - " + ex.getMessage());
            return false;
        }
    }

    private String getPaymentForMonthValue(Parameter parameter) throws Exception {
        String currentMonthId = null;
        try {
            int currentMonthFromSystem = seleniumDriver.getCurrentMonth(0);
            System.out.println("Current month from system: " + currentMonthFromSystem);
//        get the month as an integer - first radio button
            String firstRadioValue = seleniumDriver.findElementById(PageElements.SchemesConfirmPaymentForRadioButton1, parameter).getAttribute("value");
            int indexOfFirstHash = firstRadioValue.indexOf('/') + 1;
            int indexOfLastHash = firstRadioValue.lastIndexOf('/');
            String firstRadioValueMonth = firstRadioValue.substring(indexOfFirstHash, indexOfLastHash);
            int firstRadioMonth = Integer.valueOf(firstRadioValueMonth);
            System.out.println("First Radio button: " + firstRadioMonth);

//        get the month as an integer - second radio button        
            String secondRadioValue = seleniumDriver.findElementById(PageElements.SchemesConfirmPaymentForRadioButton2, parameter).getAttribute("value");
            indexOfFirstHash = secondRadioValue.indexOf('/') + 1;
            indexOfLastHash = secondRadioValue.lastIndexOf('/');
            String secondRadioValueMonth = secondRadioValue.substring(indexOfFirstHash, indexOfLastHash);
            int secondRadioMonth = Integer.valueOf(secondRadioValueMonth);
            System.out.println("Second Radio button: " + secondRadioMonth);

            if (firstRadioMonth == currentMonthFromSystem) {
                currentMonthId = PageElements.SchemesConfirmPaymentForRadioButton1.getElementId();
            }
            if (secondRadioMonth == currentMonthFromSystem) {
                currentMonthId = PageElements.SchemesConfirmPaymentForRadioButton2.getElementId();
            }
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while trying to get the Payment For Month Value - " + ex.getMessage());
        }

        return currentMonthId;
    }

    private boolean activateSchemeViaJavaCode(String env) throws Exception {
//       getting a scheme into an Active Status
        Scheme scheme = dao.getSchemeData(schemeNumber, env);
        WorkflowData wd = dao.getWorkflowDataToWakeUpItems(env, scheme);
        try {
            // wake up necessary item
            if (!WakeUpItems(wd, env)) {
                System.err.println("[Error] An error occurred while trying to Wake Up Work Items");
                return false;
            }
            // process items in automated queues
            if (!ProcessQueues(wd, env)) {
                System.err.println("[Error] An error occurred while trying to Process Queues");
                return false;
            }
            // advance items in final status to next step
            if (!AdvanceItems(wd, env)) {
                System.err.println("[Error] An error occurred while trying to Advance Items");
                return false;
            }
        } catch (Exception ex) {
            System.err.println("[Error] An occurred while performing a Workflow to activate a Scheme - " + ex.getMessage());
        }
        return true;
    }

    private boolean WakeUpItems(WorkflowData wd, String env) throws Exception {
//        WorkflowData wd = dao.getWorkflowDataToWakeUpItems(env, scheme);
        if (!dao.unPendWorkItem(wd.getWorkflowItemId(), env)) {
            System.err.println("[Error] An error occurred while Waking up an item");
            return false;
        }
        return true;
    }

    private boolean ProcessQueues(WorkflowData wd, String env) throws Exception {
        List<WorkflowData> wds = dao.getWorkflowDataToProcessQueues(env, wd);
        for (WorkflowData wdata : wds) {
            if (!dao.processQueues(wdata.getWorkflowItemId(), env)) {
                System.err.println("[Error] An error occurred while attempting to Process queues");
                return false;
            }
        }
        return true;
    }

    private boolean AdvanceItems(WorkflowData wd, String env) throws Exception {
        WorkflowData wdata = dao.getWorkflowDataToAdvanceItems(env, wd);
        Date dateRaisedFor = null;
        if (wdata.getProcessId() == 9 || wdata.getProcessId() == 20) {
            dateRaisedFor = wdata.getDateRaisedFor();
        }
        if (!dao.advanceWorkItemStep(wdata.getWorkflowItemId(), "WorkflowService", dateRaisedFor != null ? dateRaisedFor : null, 4, env)) {
            System.err.println("[Error] An error occurred while attempting to Advance Items");
            return false;
        }
        return true;
    }

    private boolean enterSchemeData(Parameter parameter) throws Exception {
        return seleniumDriver.typeById(PageElements.SchemesSchemeNameTextField, parameter.getSchemeName(), parameter)
                && seleniumDriver.selectionById(PageElements.SchemesSchemeTypeDropDown, parameter.getSchemeType(), parameter)
                && seleniumDriver.selectionById(PageElements.SchemesSchemeRegionIDDropDown, parameter.getSchemeRegion(), parameter);
    }

    private boolean enterContactPerson(Parameter parameter) throws Exception {
        return seleniumDriver.selectionById(PageElements.SchemesSchemeContactTitleDropDown, parameter.getSchemeContactPersonTitle(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesSchemeContactSurnameTextBox, parameter.getSchemeContactPersonSurname(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesSchemeContactFirstnameTextBox, parameter.getSchemeContactPersonFirstname(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesSchemeContactEmailTextBox, parameter.getSchemeContactPersonEmail(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesContactPhoneNoTextBox, parameter.getSchemeContactPersonPhoneNo(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesContactPhoneFaxNoTextBox, parameter.getSchemeContactPersonPhoneFaxNo(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesContactPhoneCellNoTextBox, parameter.getSchemeContactPersonPhoneCellNo(), parameter);
    }

    private boolean enterAddress(Parameter parameter) throws Exception {
        return seleniumDriver.typeById(PageElements.SchemesStreetAddress1TextBox, parameter.getSchemeStreetAddress1(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesStreetAddress2TextBox, parameter.getSchemeStreetAddress2(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesStreetAddress3TextBox, parameter.getSchemeStreetAddress3(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesStreetAddress4TextBox, parameter.getSchemeStreetAddress4(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesStreetAddressPostalCodeTextBox, parameter.getSchemeStreetAddressPostalCode(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesStreetAddressCityTextBox, parameter.getSchemeStreetAddressCity(), parameter)
                && seleniumDriver.selectionById(PageElements.SchemesStreetAddressCountryDropDown, parameter.getSchemeStreetAddressCountry(), parameter)
                && enterPostalAndStreetAddress(parameter);
    }

    private boolean enterPostalAndStreetAddress(Parameter parameter) throws Exception {
        return parameter.getSchemeStreetAddressSameForPostalInd()
                ? seleniumDriver.clickById(PageElements.SchemesStreetAddressSameForPostalCheckBox, parameter)
                : (seleniumDriver.typeById(PageElements.SchemesPostalAddress1TextBox, parameter.getSchemePostalAddress1(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesPostalAddress2TextBox, parameter.getSchemePostalAddress2(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesPostalAddress3TextBox, parameter.getSchemePostalAddress3(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesPostalAddress4TextBox, parameter.getSchemePostalAddress4(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesPostalAddressPostalCodeTextBox, parameter.getSchemePostalAddressPostalCode(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesPostalAddressCityTextBox, parameter.getSchemePostalAddressCity(), parameter));
    }

    private boolean enterBankingDetails(Parameter parameter) throws Exception {
        return seleniumDriver.selectionById(PageElements.SchemesBankingDetailsBankNameDropDown, parameter.getSchemeBankingDetailsBankName(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesBankingDetailsBankCodeTextBox, parameter.getSchemeBankingDetailsBankCode(), parameter)
                && seleniumDriver.typeById(PageElements.SchemesBankingAccNoTextBox, parameter.getSchemeBankingAccNo(), parameter)
                && seleniumDriver.selectionById(PageElements.SchemesBankingAccTypeDropDown, parameter.getSchemeBankingAccType(), parameter);
    }

    private boolean enterPaymentMethod(Parameter parameter, String env) throws Exception {
        parameter.setSchemePaymentMethodPaymentDay(dao.getPaymentDay(parameter, env));
        return seleniumDriver.selectionById(PageElements.SchemesPaymentMethodTypeDropDown, parameter.getSchemePaymentMethodType(), parameter)
                //        change implemented on 17 July 2014 where the payment day will be retrieved from the prd.schedule table so that this scheme be part of the
                //        daily raising for today
                && seleniumDriver.selectionById(PageElements.SchemesPaymentMethodPaymentDayDropDown, parameter.getSchemePaymentMethodPaymentDay(), parameter);
    }

    private boolean enterPaymentMethodViaUpdate(Parameter parameter, String env, String[] paymentMethodsToBeChanged) throws Exception {
        for (String paymentMethodToBeChanged : paymentMethodsToBeChanged) {
            if (paymentMethodToBeChanged.equalsIgnoreCase("PaymentMethodType")) {
                if (!seleniumDriver.selectionById(PageElements.SchemesPaymentMethodTypeUpdateDropDown, parameter.getSchemePaymentMethodType(), parameter)) {
                    System.err.println("[Error] The Payment Method Type was NOT Changed");
                    return false;
                }
            }
            if (paymentMethodToBeChanged.equalsIgnoreCase("PaymentMethodPaymentDay")) {
                parameter.setSchemePaymentMethodPaymentDay(dao.getPaymentDay(parameter, env));
//        change implemented on 17 July 2014 where the payment day will be retrieved from the prd.schedule table so that this scheme be part of the
//        daily raising for today
                if (!seleniumDriver.selectionById(PageElements.SchemesPaymentMethodPaymentDayUpdateDropDown, parameter.getSchemePaymentMethodPaymentDay(), parameter)) {
                    System.err.println("[Error] The Payment Method Payment Day was NOT Changed");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean enterServicingConsultant(Parameter parameter) throws Exception {
        return seleniumDriver.typeById(PageElements.SchemesServicingConsultantStaffCodeTextBox, parameter.getSchemeServicingConsultantStaffCode(), parameter);
    }

    private boolean enterSalesConsultant(Parameter parameter) throws Exception {
        return seleniumDriver.typeById(PageElements.SchemesSalesConsultantStaffCodeTextBox, parameter.getSchemeSalesConsultantStaffCode(), parameter);
    }

    private boolean enterSalesConsultantViaUpdate(Parameter parameter) throws Exception {
        return seleniumDriver.typeById(PageElements.SchemesSalesConsultantStaffCodeUpdateTextBox, parameter.getSchemeSalesConsultantStaffCode(), parameter);
    }

    private boolean captureMembers(Parameter parameter, String env, List<MemberDetails> memDetails) throws Exception {
        if (!schemeAddPrincipleMemberButton(parameter)) {
            System.err.println("[Error] An error occurred while attempting to click the Add Principle Member button");
            return false;
        }
        for (int i = 0; i < memDetails.size(); i++) {
            if (memDetails.get(i).getSchemeName().equals(parameter.getSchemeName())) {
                String addingMemberForm = seleniumDriver.getPageSource();
                String title = memDetails.get(i).getTitleDescription();
                String surname = memDetails.get(i).getSurname();
                String firstname = memDetails.get(i).getFirstName();
                String gender = memDetails.get(i).getGender();
                String dob = memDetails.get(i).getDob();
                String currentRelationship = memDetails.get(i).getRelationship();
                String nextRelationship = " - Do nothing - ";
                String id = memDetails.get(i).getIdNumber();
                Boolean isExistingCustId = dao.isExistingCust(id, env);
                String memRelationship;
                int n = i + 1;
                if (n >= memDetails.size()) {
                    memRelationship = null;
                } else {
                    memRelationship = memDetails.get(n).getRelationship();
                }
//                adding a principle member
                if (addingMemberForm.contains("Principal Member") && currentRelationship.equalsIgnoreCase("Principal")) {
                    if (!isExistingCustId) {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterPrincipalMemberDetails(parameter, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        if (!seleniumDriver.waitForIDAccessibility(PageElements.PrincipalMembersIdNumberTextBox.getElementId())) {
                            return false;
                        }
                        filename = "Schemes_Adding_" + currentRelationship + "_Member_" + parameter.getSchemeName();
                        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
                        clickAddOrCancelButton("Add", parameter);
                        System.out.println("*****************New Principal Member Added*****************");
                        System.out.println("ID: " + id);
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("************************************************************");
                    } else {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterExistingPrincipalMemberDetails(parameter, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        if (!seleniumDriver.waitForIDAccessibility(PageElements.PrincipalMembersNextActionsDropDownBox.getElementId())) {
                            return false;
                        }
                        filename = "Schemes_Adding_" + currentRelationship + "_ExistingMember_" + parameter.getSchemeName();
                        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
                        clickAddOrCancelButton("Add", parameter);
                        System.out.println("*****************Exiting Principal Member Added*****************");
                        System.out.println("The member with ID: " + id + " already exist on the database.");
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("***************************************************************");
                    }
                }
//                adding other members
                if (addingMemberForm.contains("Spouse") && currentRelationship.equalsIgnoreCase("Spouse")) {
                    if (!isExistingCustId) {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterPolicyMemberDetails(parameter, currentRelationship, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        clickAddMemberButton(currentRelationship, parameter);
                        System.out.println("*****************New Spouse Member Added*****************");
                        System.out.println("ID: " + id);
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("*********************************************************");
                    } else {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterExistingPolicyMemberDetails(parameter, currentRelationship, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        clickAddMemberButton(currentRelationship, parameter);
                        System.out.println("*****************Exiting Spouse Member Added*****************");
                        System.out.println("The member with ID: " + id + " already exist on the database.");
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("*************************************************************");
                    }
                }
                if (addingMemberForm.contains("Child") && currentRelationship.equalsIgnoreCase("Child")) {
                    if (!isExistingCustId) {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterPolicyMemberDetails(parameter, currentRelationship, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        clickAddMemberButton(currentRelationship, parameter);
                        System.out.println("*****************New Child Member Added*****************");
                        System.out.println("ID: " + id);
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("*********************************************************");
                    } else {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterExistingPolicyMemberDetails(parameter, currentRelationship, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        clickAddMemberButton(currentRelationship, parameter);
                        System.out.println("*****************Exiting Child Member Added*****************");
                        System.out.println("The member with ID: " + id + " already exist on the database.");
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("************************************************************");
                    }
                }
                if (addingMemberForm.contains("Adult Dependant Extended") && currentRelationship.equalsIgnoreCase("Adult Dependant Extended")) {
                    if (!isExistingCustId) {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterPolicyMemberDetails(parameter, currentRelationship, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        clickAddMemberButton(currentRelationship, parameter);
                        System.out.println("*****************New Adult Dependant Member Added*****************");
                        System.out.println("ID: " + id);
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("******************************************************************");
                    } else {
                        nextRelationship = getNextRelationship(memRelationship);
                        enterExistingPolicyMemberDetails(parameter, currentRelationship, id, title, surname, firstname, gender, dob, nextRelationship, env);
                        clickAddMemberButton(currentRelationship, parameter);
                        System.out.println("*****************Exiting Adult Dependant Member Added*****************");
                        System.out.println("The member with ID: " + id + " already exist on the database.");
                        System.out.println("Title: " + title);
                        System.out.println("Surname: " + surname);
                        System.out.println("Firstname: " + firstname);
                        System.out.println("Gender: " + gender);
                        System.out.println("DOB: " + dob);
                        System.out.println("Next Relationship: " + nextRelationship);
                        System.out.println("**********************************************************************");
                    }
                }
                if (nextRelationship.equals(" - Do nothing - ")) {
                    if (!seleniumDriver.waitForXpathAccessibility(PageElements.SchemesBackToPolicyAdminButton.getElementId())) {
                        return false;
                    }
                    filename = "Schemes_AllMembersAdded_" + parameter.getSchemeName();
                    scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
                    String schemaData = seleniumDriver.getPageSource();
                    if (!clickBackToPolicyAdminButton(parameter)) {
                        System.err.println("[Error] An error occurred while clicking on the Back to Policy Admin button");
                        return false;
                    }
//                    testCompletion = schemaData.contains(memDetails.get(i).getFirstName());
                    if (!schemaData.contains(memDetails.get(i).getFirstName())) {
                        System.err.println("[Error] The captured member details did not appear on the screen correctly");
                        return false;
                    }
                }
            }
//            else {
//                System.err.println("[Error] The scheme name \"" + parameter.getSchemeName() +"\" does not exist in the Members Data file");
//                return false;
//            }
        }
        return true;
    }

    private boolean clickBackToPolicyAdminButton(Parameter parameter) throws Exception {
        WebElement uiButton = seleniumDriver.findElementByXpath(PageElements.SchemesBackToPolicyAdminButton, parameter);
        if (uiButton.getText().contains("Back To Policy Admin")) {
            uiButton.click();
        } else {
            System.err.println("[Error] An error occurred while clicking on the Back to Policy Admin button");
            return false;
        }
        return true;
    }

    private boolean selectCancelSchemeReason(Parameter parameter) throws Exception {
        WebElement cancelReasonsTable = seleniumDriver.findElementByXpath(PageElements.SchemeCancelSchemeReasonTable, parameter);
        List<WebElement> allRows = cancelReasonsTable.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            if (row.getText().contains("Scheme not happy with the waiting period of AD's")) {
                if (!selectYesOrNoButton(row, parameter, "Yes")) {
                    System.err.println("[Error] An error occurred while attempting to select the Yes button");
                    return false;
                }
            }
            if (row.getText().contains("Scheme not happy with 50% cover for AD's")) {
                if (!selectYesOrNoButton(row, parameter, "No")) {
                    System.err.println("[Error] An error occurred while attempting to select the Yes button");
                    return false;
                }
            }
            if (row.getText().contains("Scheme requested an option change")) {
                if (!selectYesOrNoButton(row, parameter, "Yes")) {
                    System.err.println("[Error] An error occurred while attempting to select the No button");
                    return false;
                }
            }
            if (row.getText().contains("Scheme requested to cancel")) {
                if (!selectYesOrNoButton(row, parameter, "No")) {
                    System.err.println("[Error] An error occurred while attempting to select the No button");
                    return false;
                }
            }
            if (row.getText().contains("Scheme says that the product is not profitable for them")) {
                if (!selectYesOrNoButton(row, parameter, "Yes")) {
                    System.err.println("[Error] An error occurred while attempting to select the Yes button");
                    return false;
                }
            }
            if (row.getText().contains("Rate/Risk review")) {
                if (!selectYesOrNoButton(row, parameter, "No")) {
                    System.err.println("[Error] An error occurred while attempting to select the No button");
                    return false;
                }
            }
            if (row.getText().contains("Scheme was not happy with the service")) {
                if (!selectYesOrNoButton(row, parameter, "Yes")) {
                    System.err.println("[Error] An error occurred while attempting to select the Yes button");
                    return false;
                }
            }
            if (row.getText().contains("Split in the society")) {
                if (!selectYesOrNoButton(row, parameter, "No")) {
                    System.err.println("[Error] An error occurred while attempting to select the No button");
                    return false;
                }
            }
            if (row.getText().contains("Scheme submitted incorrect data")) {
                if (!selectYesOrNoButton(row, parameter, "Yes")) {
                    System.err.println("[Error] An error occurred while attempting to select the Yes button");
                    return false;
                }
            }
            if (row.getText().contains("Transferred scheme")) {
                if (!selectYesOrNoButton(row, parameter, "No")) {
                    System.err.println("[Error] An error occurred while attempting to select the No button");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean selectYesOrNoButton(WebElement row, Parameter parameter, String yesNo) throws Exception {
        List<WebElement> rowElements = row.findElements(By.tagName("label"));
        for (WebElement rowElement : rowElements) {
            if (rowElement.getText().contains(yesNo)) {
                if (!seleniumDriver.clickElement(rowElement, parameter)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean clickAddOrCancelButton(String buttonOption, Parameter parameter) throws Exception {
        List<WebElement> uibuttonsets = seleniumDriver.findElementsByClassName(PageElements.FloatingMessageUibuttonSet);
        for (WebElement uibuttonset : uibuttonsets) {
            if (uibuttonset.getText().contains("Add")) {
                List<WebElement> uibuttons = seleniumDriver.findElementsByClassName(PageElements.FloatingMessageBoxConfirmButton);
                for (WebElement uibutton : uibuttons) {
                    if (uibutton.getText().contains(buttonOption)) {
                        if (!seleniumDriver.clickElement(uibutton, parameter)) {
                            return false;
                        }
                        seleniumDriver.pause(2000);
                        break;
                    }
                }
            }
        }
        return true;
    }

    private boolean isGFSproducts(Parameter parameter) {
        return parameter.getProductType().equals(ProductType.GFS.getProductTypeName())
                || parameter.getProductType().equals(ProductType.GFSCustomised.getProductTypeName());
    }

    private boolean doDownloadAgeProfile(Parameter parameter) throws Exception {
        seleniumDriver.pause(2000);
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesAddOptionButton.getElementId())) {
            if (!searchSchemeUpdateButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Schemes");
                return false;
            }
            if (!schemePolicyAdminButton(parameter)) {
                System.err.println("[Error] An error occurred while attempting to click the Policy Admin Button");
                return false;
            }
        }
        seleniumDriver.waitForIDAccessibility(PageElements.SchemesAddOptionButton.getElementId());
        seleniumDriver.clickByLinkText(PageElements.SchemesDownloadAgeProfileLink, parameter);
//        executeEF.openDownloadedAgeProfileFile();
        return true;
    }

    private boolean verifySchemeLapsePendingStatus(Parameter parameter, String schemeStatus) throws Exception {
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        seleniumDriver.pause(2000);
        seleniumDriver.refreshPage();
        String schemeStatusLapsePending = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("Scheme Status - Lapse Pending: " + schemeStatusLapsePending);
        filename = "Schemes_SuccessfulLapsePendingStatus_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return schemeStatusLapsePending.equalsIgnoreCase(schemeStatus);
    }

    private boolean verifySchemeLapsedStatus(Parameter parameter, String schemeStatus) throws Exception {
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        seleniumDriver.pause(2000);
        seleniumDriver.refreshPage();
        String schemeStatusLapsed = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("Scheme Status - Lapsed: " + schemeStatusLapsed);
        filename = "Schemes_SuccessfulLapsedStatus_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return schemeStatusLapsed.equalsIgnoreCase(schemeStatus);
    }

    private boolean verifySchemeNewBusinessNonPayment(Parameter parameter, String schemeStatus) throws Exception {
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        seleniumDriver.pause(2000);
        seleniumDriver.refreshPage();
        String schemeStatusNewBusinessNonPayment = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("Scheme Status - New Business Non Payment: " + schemeStatusNewBusinessNonPayment);
        filename = "Schemes_SuccessfulNewBusinessNonPaymentStatus_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return schemeStatusNewBusinessNonPayment.equalsIgnoreCase(schemeStatus);
    }

    private boolean verifySchemeNotTaken(Parameter parameter, String schemeStatus) throws Exception {
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        seleniumDriver.pause(2000);
        seleniumDriver.refreshPage();
        String schemeStatusNotTaken = seleniumDriver.getSchemeStatus(parameter);
        System.out.println("Scheme Status - Not Taken: " + schemeStatusNotTaken);
        filename = "Schemes_SuccessfulNotTakenStatus_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return schemeStatusNotTaken.equalsIgnoreCase(schemeStatus);
    }

    private boolean clickSchemeUpdate(Parameter parameter) throws Exception {
        return seleniumDriver.clickByLinkText(PageElements.SchemesSchemeUpdateLink, parameter);
    }

    public boolean doSchemeCashConfirmation(Parameter parameter, String env, String paymentDate, boolean isAmountDueOver) throws Exception {
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesAddOptionButton.getElementId())) {
            if (!searchSchemeViewButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Scheme");
                return false;
            }
            if (!policyAdminLink(parameter)) {
                System.err.println("[Error] An error occurred when attempting to click policy admin link");
                return false;
            }
        }
        if (!schemeCashConfirmationButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking the Cash Confirmation Button");
            return false;
        }
        if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesCashPaymentLabel.getElementId(), "Payment Due")) {
            return false;
        }
        filename = "Schemes_CashConfirmationPage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String amountDue = getAmountDueFromPage(parameter, isAmountDueOver);
        String paymentType = getPaymentTypeFromPage(parameter);
//            select the current month        
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        String schemeStatus = dao.getSchemeStatusWithSchemeNumber(schemeNumber, env);

        if (schemeStatus.equalsIgnoreCase(SchemeStatuses.Lapsed.getSchemeStatusDescription())
                || schemeStatus.equalsIgnoreCase(SchemeStatuses.LapsePending.getSchemeStatusDescription())
                || schemeStatus.equalsIgnoreCase(SchemeStatuses.NewBusinessNonPayment.getSchemeStatusDescription())
                || schemeStatus.equalsIgnoreCase(SchemeStatuses.NotTaken.getSchemeStatusDescription())) {
            if (!seleniumDriver.typeById(PageElements.SchemesConfirmPaymentPremiumReceivedTextBoxLapsedScheme, amountDue, parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.SchemesConfirmPaymentDateReceivedTextBoxLapsedScheme, paymentDate, parameter)) {
                return false;
            }
            if (!seleniumDriver.tabById(PageElements.SchemesConfirmPaymentDateReceivedTextBoxLapsedScheme, parameter)) {
                return false;
            }
            filename = "Schemes_CashConfirmationCaptured_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            if (!seleniumDriver.clickByName(PageElements.SubmitButtonName, parameter)) {
                return false;
            }
//            if (!seleniumDriver.waitForIDAccessibility(PageElements.SchemesCashConfirmationButton.getElementId())) {
//                return false;
//            }
            filename = "Schemes_SuccessfullyPaid_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        }
        if (schemeStatus.equalsIgnoreCase(SchemeStatuses.AwaitingFirstPremium.getSchemeStatusDescription())) {
            String currentMonth = getPaymentForMonthValue(parameter);
            if (!seleniumDriver.clickingById(currentMonth, parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.SchemesConfirmPaymentPremiumReceivedTextBox, amountDue, parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.SchemesConfirmPaymentDateReceivedTextBox, paymentDate, parameter)) {
                return false;
            }
            if (!seleniumDriver.tabById(PageElements.SchemesConfirmPaymentDateReceivedTextBox, parameter)) {
                return false;
            }
            if (!seleniumDriver.waitForIDAccessibility(PageElements.SubmitButton.getElementId())) {
                return false;
            }
            filename = "Schemes_CashConfirmationCaptured_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
            if (!seleniumDriver.clickById(PageElements.SubmitButton, parameter)) {
                return false;
            }
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfullySavedPaymentMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterCashConfirmation = seleniumDriver.getFloatingBoxMessage(parameter);

        if (!verifyTransactionHistoryEntries(parameter, amountDue)) {
            System.err.println("[Error] The verifyTransactionHistoryEntries was not successfully executed");
            return false;
        }

        String expectedCashConfirmationMsg = "Payment Recorded: " + paymentType + "\nPremium Received: R " + amountDue + "\nDate Received: " + paymentDate;
        System.out.println("ExpectedCashConfirmationMsg: " + expectedCashConfirmationMsg);
        System.out.println("MessageBox: " + schemeMessageAfterCashConfirmation);
        System.out.println("Scheme Number: " + schemeNumber);
        if (!schemeMessageAfterCashConfirmation.contains(expectedCashConfirmationMsg)) {
            System.err.println("The messge \"" + schemeMessageAfterCashConfirmation + "\" is incorrect. It should have been: \""
                    + expectedCashConfirmationMsg + "\"");
            return false;
        }
        return true;
    }

    public boolean doSchemeCashConfirmationWithPMAR(Parameter parameter, String env, String paymentDate) throws Exception {
        String pgSource = seleniumDriver.getPageSource();
        if (!pgSource.contains(PageElements.SchemesAddOptionButton.getElementId())) {
            if (!searchSchemeViewButton(parameter)) {
                System.err.println("[Error] An error occurred when attempting to search a Scheme");
                return false;
            }
            if (!policyAdminLink(parameter)) {
                System.err.println("[Error] An error occurred when attempting to click policy admin link");
                return false;
            }
        }
        if (!schemeCashConfirmationButton(parameter)) {
            System.err.println("[Error] An error occurred while clicking the Cash Confirmation Button");
            return false;
        }
        if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesCashPaymentLabel.getElementId(), "Payment Due")) {
            return false;
        }
        filename = "Schemes_CashConfirmationPage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String amountDue = getAmountDueFromPage(parameter, false);
        String paymentType = getPaymentTypeFromPage(parameter);
        String schemePremiumDue = seleniumDriver.getSchemePremiumsDue(parameter);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        String premiumReceived = String.valueOf(formatter.format(Double.valueOf(amountDue) - Double.valueOf(schemePremiumDue)));
        String noOfPremiums = seleniumDriver.getTheSmallerValueFromPage(PageElements.SchemesConfirmPaymentNoOfPremiumsDropdownLapsedScheme, parameter);
        schemeNumber = seleniumDriver.getRefNumbersFromHeading(parameter);
        String schemeStatus = dao.getSchemeStatusWithSchemeNumber(schemeNumber, env);

        if (schemeStatus.equalsIgnoreCase(SchemeStatuses.Lapsed.getSchemeStatusDescription())
                || schemeStatus.equalsIgnoreCase(SchemeStatuses.LapsePending.getSchemeStatusDescription())
                || schemeStatus.equalsIgnoreCase(SchemeStatuses.NewBusinessNonPayment.getSchemeStatusDescription())
                || schemeStatus.equalsIgnoreCase(SchemeStatuses.NotTaken.getSchemeStatusDescription())) {
            if (!seleniumDriver.typeById(PageElements.SchemesConfirmPaymentPremiumReceivedTextBoxLapsedScheme, premiumReceived, parameter)) {
                return false;
            }
            if (!seleniumDriver.typeById(PageElements.SchemesConfirmPaymentDateReceivedTextBoxLapsedScheme, paymentDate, parameter)) {
                return false;
            }
            if (!seleniumDriver.tabById(PageElements.SchemesConfirmPaymentDateReceivedTextBoxLapsedScheme, parameter)) {
                return false;
            }
            if (!seleniumDriver.selectionById(PageElements.SchemesConfirmPaymentNoOfPremiumsDropdownLapsedScheme, noOfPremiums, parameter)) {
                return false;
            }
            if (!seleniumDriver.selectionById(PageElements.SchemesConfirmPaymentPaymentTypeDropdownLapsedScheme, "M65 Arrear", parameter)) {
                return false;
            }
            filename = "Schemes_CashConfirmationCaptured_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);

//            check to see the PMAR option came up
            if (!seleniumDriver.findElementById(PageElements.SchemesConfirmPaymentPMARActionDateDropDown, parameter).isEnabled()) {
                System.err.println("[Error] The " + PageElements.SchemesConfirmPaymentPMARActionDateDropDown.getElementNameOnPage() + " should have "
                        + "appeared on the page but did not appear");
                return false;
            }

            if (!seleniumDriver.clickByName(PageElements.SubmitButtonName, parameter)) {
                return false;
            }
//            if (!seleniumDriver.waitForIDAccessibility(PageElements.SchemesCashConfirmationButton.getElementId())) {
//                return false;
//            }
            filename = "Schemes_SuccessfullyPaid_" + parameter.getSchemeName();
            scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        }
        seleniumDriver.pause(2000);
        filename = "Schemes_SuccessfullySavedPaymentMessage_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        String schemeMessageAfterCashConfirmation = seleniumDriver.getFloatingBoxMessage(parameter);

        if (!verifyTransactionHistoryEntries(parameter, premiumReceived)) {
            System.err.println("[Error] The verifyTransactionHistoryEntries was not successfully executed");
            return false;
        }

        String expectedCashConfirmationMsg = "Payment Recorded: " + paymentType + "\nPremium Received: R " + premiumReceived + "\nDate Received: " + paymentDate;
        System.out.println("ExpectedCashConfirmationMsg: " + expectedCashConfirmationMsg);
        System.out.println("MessageBox: " + schemeMessageAfterCashConfirmation);
        System.out.println("Scheme Number: " + schemeNumber);
        if (!schemeMessageAfterCashConfirmation.contains(expectedCashConfirmationMsg)) {
            System.err.println("The messge \"" + schemeMessageAfterCashConfirmation + "\" is incorrect. It should have been: \""
                    + expectedCashConfirmationMsg + "\"");
            return false;
        }
        return true;
    }

    private boolean verifyTransactionHistoryEntries(Parameter parameter, String amountDue) throws Exception {
//        go check if the amount captured exist on the Transaction History Page
//        navigate to the Transaction History page to see if the transaction was recorded
        if (!navigateToTransactionHistoryLinkOnSidePane(parameter)) {
            System.err.println("[Error] An error occurred while attempting to navigate to the Transaction History link on the side pane");
            return false;
        }
        if (!seleniumDriver.waitForTextToBePresentInId(PageElements.SchemesTransactionHistoryTable.getElementId(), "Transaction Date")) {
            System.err.println("[Error] Unable to find the Transaction History Table");
            return false;
        }
        if (!seleniumDriver.getPageSource().contains(amountDue)) {
            System.err.println("[Error] The amount \"" + amountDue + "\" does not exist on this page.");
            return false;
        }
        filename = "Schemes_TransactionHistoryAfterDailyRaising_" + parameter.getSchemeName();
        scrnShotsandMsgs.captureScreenshot(filename, parameter, false);
        return true;
    }
}

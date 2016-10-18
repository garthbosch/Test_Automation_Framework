package testFramework.utils;

/*
 * @author Garth Bosch
 */
public class Parameter {

    private String testCaseName;
    private String testCaseCode;
    private String schemeName;
    private String schemeType;
    private String schemeRegion;
    private String schemeContactPersonTitle;
    private String schemeContactPersonSurname;
    private String schemeContactPersonFirstname;
    private String schemeContactPersonEmail;
    private String schemeContactPersonPhoneNo;
    private String schemeContactPersonPhoneFaxNo;
    private String schemeContactPersonPhoneCellNo;
    private String schemeStreetAddress1;
    private String schemeStreetAddress2;
    private String schemeStreetAddress3;
    private String schemeStreetAddress4;
    private String schemeStreetAddressPostalCode;
    private String schemeStreetAddressCity;
    private String schemeStreetAddressCountry;
    private Boolean schemeStreetAddressSameForPostalInd;
    private String schemePostalAddress1;
    private String schemePostalAddress2;
    private String schemePostalAddress3;
    private String schemePostalAddress4;
    private String schemePostalAddressPostalCode;
    private String schemePostalAddressCity;
    private String schemeBankingDetailsBankName;
    private String schemeBankingDetailsBankCode;
    private String schemeBankingAccNo;
    private String schemeBankingAccType;
    private String schemePaymentMethodType;
    private String schemePaymentMethodPaymentDay;
    private String schemeServicingConsultantStaffCode;
    private String schemeSalesConsultantStaffCode;
    private String schemeProductOption;
    private String schemeProductOptionCover;
    private String ProductType;
    private Boolean externalVendor;
    private Boolean reIssuedBusiness;
    private Boolean cashBack;
    private String waitingPeriod;
    private String qcInstanceName;
    private String screenshotsLocation;
    private Boolean runTest;

    public Parameter() {
    }

    public Parameter(String testCaseName, String testCaseCode, String schemeName, String schemeType, String schemeRegion,
            String schemeContactPersonTitle, String schemeContactPersonSurname,
            String schemeContactPersonFirstname, String schemeContactPersonEmail,
            String schemeContactPersonPhoneNo, String schemeContactPersonPhoneFaxNo,
            String schemeContactPersonPhoneCellNo, String schemeStreetAddress1,
            String schemeStreetAddress2, String schemeStreetAddress3, String schemeStreetAddress4,
            String schemeStreetAddressPostalCode, String schemeStreetAddressCity,
            String schemeStreetAddressCountry, Boolean schemeStreetAddressSameForPostalInd,
            String schemePostalAddress1, String schemePostalAddress2, String schemePostalAddress3,
            String schemePostalAddress4, String schemePostalAddressPostalCode,
            String schemePostalAddressCity, String schemeBankingDetailsBankName,
            String schemeBankingDetailsBankCode, String schemeBankingAccNo,
            String schemeBankingAccType, String schemePaymentMethodType,
            String schemePaymentMethodPaymentDay, String schemeServicingConsultantStaffCode,
            String schemeSalesConsultantStaffCode, String schemeProductOption, String schemeProductOptionCover,
            String ProductType, Boolean externalVendor, Boolean reIssuedBusiness, String waitingPeriod, Boolean cashBack, String qcInstanceName,
            String screenshotsLocation, Boolean runTest) {
        this.testCaseName = testCaseName;
        this.testCaseCode = testCaseCode;
        this.schemeName = schemeName;
        this.schemeType = schemeType;
        this.schemeRegion = schemeRegion;
        this.schemeContactPersonTitle = schemeContactPersonTitle;
        this.schemeContactPersonSurname = schemeContactPersonSurname;
        this.schemeContactPersonFirstname = schemeContactPersonFirstname;
        this.schemeContactPersonEmail = schemeContactPersonEmail;
        this.schemeContactPersonPhoneNo = schemeContactPersonPhoneNo;
        this.schemeContactPersonPhoneFaxNo = schemeContactPersonPhoneFaxNo;
        this.schemeContactPersonPhoneCellNo = schemeContactPersonPhoneCellNo;
        this.schemeStreetAddress1 = schemeStreetAddress1;
        this.schemeStreetAddress2 = schemeStreetAddress2;
        this.schemeStreetAddress3 = schemeStreetAddress3;
        this.schemeStreetAddress4 = schemeStreetAddress4;
        this.schemeStreetAddressPostalCode = schemeStreetAddressPostalCode;
        this.schemeStreetAddressCity = schemeStreetAddressCity;
        this.schemeStreetAddressCountry = schemeStreetAddressCountry;
        this.schemeStreetAddressSameForPostalInd = schemeStreetAddressSameForPostalInd;
        this.schemePostalAddress1 = schemePostalAddress1;
        this.schemePostalAddress2 = schemePostalAddress2;
        this.schemePostalAddress3 = schemePostalAddress3;
        this.schemePostalAddress4 = schemePostalAddress4;
        this.schemePostalAddressPostalCode = schemePostalAddressPostalCode;
        this.schemePostalAddressCity = schemePostalAddressCity;
        this.schemeBankingDetailsBankName = schemeBankingDetailsBankName;
        this.schemeBankingDetailsBankCode = schemeBankingDetailsBankCode;
        this.schemeBankingAccNo = schemeBankingAccNo;
        this.schemeBankingAccType = schemeBankingAccType;
        this.schemePaymentMethodType = schemePaymentMethodType;
        this.schemePaymentMethodPaymentDay = schemePaymentMethodPaymentDay;
        this.schemeServicingConsultantStaffCode = schemeServicingConsultantStaffCode;
        this.schemeSalesConsultantStaffCode = schemeSalesConsultantStaffCode;
        this.schemeProductOption = schemeProductOption;
        this.schemeProductOptionCover = schemeProductOptionCover;
        this.ProductType = ProductType;
        this.externalVendor = externalVendor;
        this.reIssuedBusiness = reIssuedBusiness;
        this.cashBack = cashBack;
        this.waitingPeriod = waitingPeriod;
        this.qcInstanceName = qcInstanceName;
        this.screenshotsLocation = screenshotsLocation;
        this.runTest = runTest;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestCaseCode() {
        return testCaseCode;
    }

    public void setTestCaseCode(String testCaseCode) {
        this.testCaseCode = testCaseCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getSchemeRegion() {
        return schemeRegion;
    }

    public void setSchemeRegion(String schemeRegion) {
        this.schemeRegion = schemeRegion;
    }

    public String getSchemeContactPersonTitle() {
        return schemeContactPersonTitle;
    }

    public void setSchemeContactPersonTitle(String schemeContactPersonTitle) {
        this.schemeContactPersonTitle = schemeContactPersonTitle;
    }

    public String getSchemeContactPersonSurname() {
        return schemeContactPersonSurname;
    }

    public void setSchemeContactPersonSurname(String schemeContactPersonSurname) {
        this.schemeContactPersonSurname = schemeContactPersonSurname;
    }

    public String getSchemeContactPersonFirstname() {
        return schemeContactPersonFirstname;
    }

    public void setSchemeContactPersonFirstname(String schemeContactPersonFirstname) {
        this.schemeContactPersonFirstname = schemeContactPersonFirstname;
    }

    public String getSchemeContactPersonEmail() {
        return schemeContactPersonEmail;
    }

    public void setSchemeContactPersonEmail(String schemeContactPersonEmail) {
        this.schemeContactPersonEmail = schemeContactPersonEmail;
    }

    public String getSchemeContactPersonPhoneNo() {
        return schemeContactPersonPhoneNo;
    }

    public void setSchemeContactPersonPhoneNo(String schemeContactPersonPhoneNo) {
        this.schemeContactPersonPhoneNo = schemeContactPersonPhoneNo;
    }

    public String getSchemeContactPersonPhoneFaxNo() {
        return schemeContactPersonPhoneFaxNo;
    }

    public void setSchemeContactPersonPhoneFaxNo(String schemeContactPersonPhoneFaxNo) {
        this.schemeContactPersonPhoneFaxNo = schemeContactPersonPhoneFaxNo;
    }

    public String getSchemeContactPersonPhoneCellNo() {
        return schemeContactPersonPhoneCellNo;
    }

    public void setSchemeContactPersonPhoneCellNo(String schemeContactPersonPhoneCellNo) {
        this.schemeContactPersonPhoneCellNo = schemeContactPersonPhoneCellNo;
    }

    public String getSchemeStreetAddress1() {
        return schemeStreetAddress1;
    }

    public void setSchemeStreetAddress1(String schemeStreetAddress1) {
        this.schemeStreetAddress1 = schemeStreetAddress1;
    }

    public String getSchemeStreetAddress2() {
        return schemeStreetAddress2;
    }

    public void setSchemeStreetAddress2(String schemeStreetAddress2) {
        this.schemeStreetAddress2 = schemeStreetAddress2;
    }

    public String getSchemeStreetAddress3() {
        return schemeStreetAddress3;
    }

    public void setSchemeStreetAddress3(String schemeStreetAddress3) {
        this.schemeStreetAddress3 = schemeStreetAddress3;
    }

    public String getSchemeStreetAddress4() {
        return schemeStreetAddress4;
    }

    public void setSchemeStreetAddress4(String schemeStreetAddress4) {
        this.schemeStreetAddress4 = schemeStreetAddress4;
    }

    public String getSchemeStreetAddressPostalCode() {
        return schemeStreetAddressPostalCode;
    }

    public void setSchemeStreetAddressPostalCode(String schemeStreetAddressPostalCode) {
        this.schemeStreetAddressPostalCode = schemeStreetAddressPostalCode;
    }

    public String getSchemeStreetAddressCity() {
        return schemeStreetAddressCity;
    }

    public void setSchemeStreetAddressCity(String schemeStreetAddressCity) {
        this.schemeStreetAddressCity = schemeStreetAddressCity;
    }

    public String getSchemeStreetAddressCountry() {
        return schemeStreetAddressCountry;
    }

    public void setSchemeStreetAddressCountry(String schemeStreetAddressCountry) {
        this.schemeStreetAddressCountry = schemeStreetAddressCountry;
    }

    public Boolean getSchemeStreetAddressSameForPostalInd() {
        return schemeStreetAddressSameForPostalInd;
    }

    public void setSchemeStreetAddressSameForPostalInd(Boolean schemeStreetAddressSameForPostalInd) {
        this.schemeStreetAddressSameForPostalInd = schemeStreetAddressSameForPostalInd;
    }

    public String getSchemePostalAddress1() {
        return schemePostalAddress1;
    }

    public void setSchemePostalAddress1(String schemePostalAddress1) {
        this.schemePostalAddress1 = schemePostalAddress1;
    }

    public String getSchemePostalAddress2() {
        return schemePostalAddress2;
    }

    public void setSchemePostalAddress2(String schemePostalAddress2) {
        this.schemePostalAddress2 = schemePostalAddress2;
    }

    public String getSchemePostalAddress3() {
        return schemePostalAddress3;
    }

    public void setSchemePostalAddress3(String schemePostalAddress3) {
        this.schemePostalAddress3 = schemePostalAddress3;
    }

    public String getSchemePostalAddress4() {
        return schemePostalAddress4;
    }

    public void setSchemePostalAddress4(String schemePostalAddress4) {
        this.schemePostalAddress4 = schemePostalAddress4;
    }

    public String getSchemePostalAddressPostalCode() {
        return schemePostalAddressPostalCode;
    }

    public void setSchemePostalAddressPostalCode(String schemePostalAddressPostalCode) {
        this.schemePostalAddressPostalCode = schemePostalAddressPostalCode;
    }

    public String getSchemePostalAddressCity() {
        return schemePostalAddressCity;
    }

    public void setSchemePostalAddressCity(String schemePostalAddressCity) {
        this.schemePostalAddressCity = schemePostalAddressCity;
    }

    public String getSchemeBankingDetailsBankName() {
        return schemeBankingDetailsBankName;
    }

    public void setSchemeBankingDetailsBankName(String schemeBankingDetailsBankName) {
        this.schemeBankingDetailsBankName = schemeBankingDetailsBankName;
    }

    public String getSchemeBankingDetailsBankCode() {
        return schemeBankingDetailsBankCode;
    }

    public void setSchemeBankingDetailsBankCode(String schemeBankingDetailsBankCode) {
        this.schemeBankingDetailsBankCode = schemeBankingDetailsBankCode;
    }

    public String getSchemeBankingAccNo() {
        return schemeBankingAccNo;
    }

    public void setSchemeBankingAccNo(String schemeBankingAccNo) {
        this.schemeBankingAccNo = schemeBankingAccNo;
    }

    public String getSchemeBankingAccType() {
        return schemeBankingAccType;
    }

    public void setSchemeBankingAccType(String schemeBankingAccType) {
        this.schemeBankingAccType = schemeBankingAccType;
    }

    public String getSchemePaymentMethodType() {
        return schemePaymentMethodType;
    }

    public void setSchemePaymentMethodType(String schemePaymentMethodType) {
        this.schemePaymentMethodType = schemePaymentMethodType;
    }

    public String getSchemePaymentMethodPaymentDay() {
        return schemePaymentMethodPaymentDay;
    }

    public void setSchemePaymentMethodPaymentDay(String schemePaymentMethodPaymentDay) {
        this.schemePaymentMethodPaymentDay = schemePaymentMethodPaymentDay;
    }

    public String getSchemeServicingConsultantStaffCode() {
        return schemeServicingConsultantStaffCode;
    }

    public void setSchemeServicingConsultantStaffCode(String schemeServicingConsultantStaffCode) {
        this.schemeServicingConsultantStaffCode = schemeServicingConsultantStaffCode;
    }

    public String getSchemeSalesConsultantStaffCode() {
        return schemeSalesConsultantStaffCode;
    }

    public void setSchemeSalesConsultantStaffCode(String schemeSalesConsultantStaffCode) {
        this.schemeSalesConsultantStaffCode = schemeSalesConsultantStaffCode;
    }

    public String getSchemeProductOption() {
        return schemeProductOption;
    }

    public void setSchemeProductOption(String schemeProductOption) {
        this.schemeProductOption = schemeProductOption;
    }

    public String getSchemeProductOptionCover() {
        return schemeProductOptionCover;
    }

    public void setSchemeProductOptionCover(String schemeProductOptionCover) {
        this.schemeProductOptionCover = schemeProductOptionCover;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public Boolean isExternalVendor() {
        return externalVendor;
    }

    public void setExternalVendor(Boolean externalVendor) {
        this.externalVendor = externalVendor;
    }

    public Boolean isReIssuedBusiness() {
        return reIssuedBusiness;
    }

    public void setReIssuedBusiness(Boolean reIssuedBusiness) {
        this.reIssuedBusiness = reIssuedBusiness;
    }

    public Boolean isCashBack() {
        return cashBack;
    }

    public void setCashBack(Boolean cashBack) {
        this.cashBack = cashBack;
    }

    public String getScreenshotsLocation() {
        return screenshotsLocation;
    }

    public void setScreenshotsLocation(String screenshotsLocation) {
        this.screenshotsLocation = screenshotsLocation;
    }

    public String getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(String waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public String getQcInstanceName() {
        return qcInstanceName;
    }

    public void setQcInstanceName(String qcInstanceName) {
        this.qcInstanceName = qcInstanceName;
    }

    public Boolean isRunTest() {
        return runTest;
    }

    public void setRunTest(Boolean runTest) {
        this.runTest = runTest;
    }
}

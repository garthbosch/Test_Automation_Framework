package testFramework.objects;

import java.util.Date;

/**
 *
 * @author Garth Bosch
 */
public class LastDailyRaising {

    private int schemeId;
    private String schemeNumber;
    private int deductionDay;
    private String status;
    private double amountDue;
    private String bankAccountNumber;
    private String bankSortCode;
    private Date dateRaised;
    private String ymActionInd;
    private String paymentType;
    private String ymInstructionType;
    private String userId;
    private Date dateRaisedFor;
    private int latestInd ;
    private String ymType;
    private boolean ymOnly;
    private int debitOrderId;
    private String ymStatus;
    private String ymStatusReason;
    private int workflowItemId;

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeNumber() {
        return schemeNumber;
    }

    public void setSchemeNumber(String schemeNumber) {
        this.schemeNumber = schemeNumber;
    }

    public int getDeductionDay() {
        return deductionDay;
    }

    public void setDeductionDay(int deductionDay) {
        this.deductionDay = deductionDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankSortCode() {
        return bankSortCode;
    }

    public void setBankSortCode(String bankSortCode) {
        this.bankSortCode = bankSortCode;
    }

    public Date getDateRaised() {
        return dateRaised;
    }

    public void setDateRaised(Date dateRaised) {
        this.dateRaised = dateRaised;
    }

    public String getYmActionInd() {
        return ymActionInd;
    }

    public void setYmActionInd(String ymActionInd) {
        this.ymActionInd = ymActionInd;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getYmInstructionType() {
        return ymInstructionType;
    }

    public void setYmInstructionType(String ymInstructionType) {
        this.ymInstructionType = ymInstructionType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateRaisedFor() {
        return dateRaisedFor;
    }

    public void setDateRaisedFor(Date dateRaisedFor) {
        this.dateRaisedFor = dateRaisedFor;
    }

    public int getLatestInd() {
        return latestInd;
    }

    public void setLatestInd(int latestInd) {
        this.latestInd = latestInd;
    }

    public String getYmType() {
        return ymType;
    }

    public void setYmType(String ymType) {
        this.ymType = ymType;
    }

    public boolean isYmOnly() {
        return ymOnly;
    }

    public void setYmOnly(boolean ymOnly) {
        this.ymOnly = ymOnly;
    }

    public int getDebitOrderId() {
        return debitOrderId;
    }

    public void setDebitOrderId(int debitOrderId) {
        this.debitOrderId = debitOrderId;
    }

    public String getYmStatus() {
        return ymStatus;
    }

    public void setYmStatus(String ymStatus) {
        this.ymStatus = ymStatus;
    }

    public String getYmStatusReason() {
        return ymStatusReason;
    }

    public void setYmStatusReason(String ymStatusReason) {
        this.ymStatusReason = ymStatusReason;
    }

    public int getWorkflowItemId() {
        return workflowItemId;
    }

    public void setWorkflowItemId(int workflowItemId) {
        this.workflowItemId = workflowItemId;
    }
}

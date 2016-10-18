package testFramework.objects;

import java.util.Date;

/**
 *
 * @author Garth Bosch
 */
public class DebitOrdersData {
    
    private String debitOrderId;
    private String schemeId;
    private String schemeNumber;
    private String amountDue;
    private Date dateRaised;
    private Date dateRaisedFor;
    private String paymentType;

    public String getDebitOrderId() {
        return debitOrderId;
    }

    public void setDebitOrderId(String debitOrderId) {
        this.debitOrderId = debitOrderId;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeNumber() {
        return schemeNumber;
    }

    public void setSchemeNumber(String schemeNumber) {
        this.schemeNumber = schemeNumber;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public Date getDateRaised() {
        return dateRaised;
    }

    public void setDateRaised(Date dateRaised) {
        this.dateRaised = dateRaised;
    }

    public Date getDateRaisedFor() {
        return dateRaisedFor;
    }

    public void setDateRaisedFor(Date dateRaisedFor) {
        this.dateRaisedFor = dateRaisedFor;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }    
}

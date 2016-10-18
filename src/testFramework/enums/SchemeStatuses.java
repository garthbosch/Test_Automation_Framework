package testFramework.enums;

import java.io.Serializable;

/**
 *
 * @author Garth Bosch
 */
public enum SchemeStatuses implements Serializable {

    Active("Active"),
    AssessmentPended("Assessment Pended"),
    AwaitingActuarialDecision("Awaiting Actuarial Decision"),
    AwaitingFirstPremium("Awaiting First Premium"),
    Cancelled("Cancelled"),
    LapsePending("Lapse Pending"),
    Lapsed("Lapsed"),
    NewBusinessClosed("New Business Closed"),
    NewBusinessLogged("New Business Logged"),
    NewBusinessNonPayment("New Business Non Payment"),
    NewBusinessNonPending("New Business Non Pending"),
    NewBusinessWIP("New Business WIP"),
    NotTaken("Not Taken"),
    OptionChanged("Option Changed"),
    Quote("Quote"),
    Rejected("Rejected"),
    ToBeAssessed("To Be Assessed"),
    ;
    private final String schemeStatusDescription;

    SchemeStatuses(String schemeStatusDescription) {
        this.schemeStatusDescription = schemeStatusDescription;
    }
    
    public String getSchemeStatusDescription() {
        return schemeStatusDescription;
    }
}

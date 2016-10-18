package testFramework.enums;

import java.io.Serializable;

/**
 *
 * @author Garth Bosch
 */
public enum NotificationMessages implements Serializable {

    // Schemes Page    
    SuccessfullySavedSchemeMsg("You have successfully added the scheme"),
    SuccessfullySavedSchemeAssessmentMsg("Success: Accepting a scheme requires a recalculation of the premium and will be done by the OneFM Workflow Service. The scheme will be made AFP in the next 10 - 15 minutes."),
    SuccessfullyUpdatedSchemeInfoMsg("You have successfully updated the scheme!"),
    SuccessfullyUpdatedMinReqMsg("Minimum Requirements: You have successfully updated the Minimum Requirements!"),
    SuccessfullySavedPaymentMsg("Date Received: "),
    SuccessfullySavedQuoteMsg("1: Your Quote Reference Number: "),
    SuccessfullyCancelledMsg("Scheme set to be cancelled, please allow 10 minutes for changes to take affect."),
//    SuccessfullyCancelledMsg("Scheme successfully cancelled!"),
    SuccessfullyReverseCancellationMsg("Cancellation Reversal: The scheme was flagged for reversal please be patient while the workflow reverses the cancellation for the scheme."),
    ;
    private final String message;

    NotificationMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

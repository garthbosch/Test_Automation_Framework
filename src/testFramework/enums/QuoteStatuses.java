package testFramework.enums;

import java.io.Serializable;

/**
 *
 * @author Garth Bosch
 */
public enum QuoteStatuses implements Serializable {

    Accepted("Accepted"),
    Closed("Closed"),
    Declined("Declined"),
    Expired("Expired"),
    Provided("Provided"),
    QuoteLogged("Quote Logged"),
    QuotePended("Quote Pended"),
    ToBeAssessed("To Be Assessed"),
    WIP("WIP"),
    ;
    private final String quoteStatusDescription;

    QuoteStatuses(String quoteStatusDescription) {
        this.quoteStatusDescription = quoteStatusDescription;
    }
    
    public String getQuoteStatusDescription() {
        return quoteStatusDescription;
    }
}

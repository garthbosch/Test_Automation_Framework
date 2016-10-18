package testFramework.enums;

import java.io.Serializable;

/**
 *
 * @author Garth Bosch
 */
public enum SchemeOption implements Serializable {

    SingleMemberAdvance(1, "SINGLE-ADV"),
    SingleMemberDeLux(2, "SINGLE-DE-LUX");
        
    private static final long serialVersionUID = -6021731140979373963L;
    private final Integer productID;
    private final String productCode;

    SchemeOption(Integer productID, String productCode) {
        this.productID = productID;
        this.productCode = productCode;
    }

    public Integer getProductID() {
        return productID;
    }

    public String getProductCode() {
        return productCode;
    }
}

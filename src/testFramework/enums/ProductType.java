package testFramework.enums;

import java.io.Serializable;

/**
 *
 * @author Garth Bosch
 */
public enum ProductType implements Serializable {

    BS(1, "BSSP"),
    BSCustomised(1, "BSSP Customised"),
    GFS(2, "GFS"),
    GFSCustomised(2, "GFS Customised");
//    private static final long serialVersionUID = -6021731140979373963L;
    private final Integer productTypeID;
    private final String productTypeName;

    ProductType(Integer productTypeID, String productTypeName) {
        this.productTypeID = productTypeID;
        this.productTypeName = productTypeName;
    }

    public Integer getProductTypeID() {
        return productTypeID;
    }

    public String getProductTypeName() {
        return productTypeName;
    }
}
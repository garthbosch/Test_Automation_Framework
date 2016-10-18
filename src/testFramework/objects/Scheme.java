package testFramework.objects;

import testFramework.enums.ProductType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Garth Bosch
 */
public class Scheme {

    private String schemeId;
    private String schemeName;
    private String schemeNumber;
    private ProductType productTypeID;
    private String schemeStatusDescription;
    private String longRunningJob;
    private String lastChangedBy;
    private Date lastChangedDate;
    private List<SchemeMembers> schemeMembers;
    private List<Product> products;    

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeNumber() {
        return schemeNumber;
    }

    public void setSchemeNumber(String schemeNumber) {
        this.schemeNumber = schemeNumber;
    }

    public ProductType getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(ProductType productTypeID) {
        this.productTypeID = productTypeID;
    }

    public String getSchemeStatusDescription() {
        return schemeStatusDescription;
    }

    public void setSchemeStatusDescription(String schemeStatusDescription) {
        this.schemeStatusDescription = schemeStatusDescription;
    }

    public String getLongRunningJob() {
        return longRunningJob;
    }

    public void setLongRunningJob(String longRunningJob) {
        this.longRunningJob = longRunningJob;
    }

    public String getLastChangedBy() {
        return lastChangedBy;
    }

    public void setLastChangedBy(String lastChangedBy) {
        this.lastChangedBy = lastChangedBy;
    }

    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public List<SchemeMembers> getSchemeMembers() {
        return schemeMembers;
    }

    public void setSchemeMembers(List<SchemeMembers> schemeMembers) {
        this.schemeMembers = schemeMembers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
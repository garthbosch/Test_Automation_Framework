package testFramework.objects;

/**
 *
 * @author Garth Bosch
 */
public class Product {
    
    private String productId;
    private String productCode;
    private Integer waitingPeriod;
    private Integer coverAmount;
    private Integer waitingPeriod2;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(Integer waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public Integer getCoverAmount() {
        return coverAmount;
    }

    public void setCoverAmount(Integer coverAmount) {
        this.coverAmount = coverAmount;
    }

    public Integer getWaitingPeriod2() {
        return waitingPeriod2;
    }

    public void setWaitingPeriod2(Integer waitingPeriod2) {
        this.waitingPeriod2 = waitingPeriod2;
    }    
}
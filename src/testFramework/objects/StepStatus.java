package testFramework.objects;

/**
 *
 * @author Garth Bosch
 */
public class StepStatus {
    
    private Integer statusId;
    private Integer stepId;
    private String description;    

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
}
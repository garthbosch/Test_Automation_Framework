package testFramework.objects;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Garth Bosch
 */
public class WorkflowData {
    
    private String debitOrderId;
    private Integer workflowItemId;
    private String paymentType;
    private Integer stepId;
    private Integer statusId;
    private Date wakeUpOn;
    private Integer priorityId;
    private Integer pended;
    private List<StepStatus> stepStatus;
    private Integer processId;
    private Integer queueId;
    private Integer automatedQueue;
    private Integer finalStatus;
    private Date dateRaisedFor;
    private String statusAction;

    public String getDebitOrderId() {
        return debitOrderId;
    }

    public void setDebitOrderId(String debitOrderId) {
        this.debitOrderId = debitOrderId;
    }

    public Integer getWorkflowItemId() {
        return workflowItemId;
    }

    public void setWorkflowItemId(Integer workflowItemId) {
        this.workflowItemId = workflowItemId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Date getWakeUpOn() {
        return wakeUpOn;
    }

    public void setWakeUpOn(Date wakeUpOn) {
        this.wakeUpOn = wakeUpOn;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getPended() {
        return pended;
    }

    public void setPended(Integer pended) {
        this.pended = pended;
    }        

    public List<StepStatus> getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(List<StepStatus> stepStatus) {
        this.stepStatus = stepStatus;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Integer getAutomatedQueue() {
        return automatedQueue;
    }

    public void setAutomatedQueue(Integer automatedQueue) {
        this.automatedQueue = automatedQueue;
    }

    public Integer getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(Integer finalStatus) {
        this.finalStatus = finalStatus;
    }

    public Date getDateRaisedFor() {
        return dateRaisedFor;
    }

    public void setDateRaisedFor(Date dateRaisedFor) {
        this.dateRaisedFor = dateRaisedFor;
    }

    public String getStatusAction() {
        return statusAction;
    }

    public void setStatusAction(String statusAction) {
        this.statusAction = statusAction;
    }
}
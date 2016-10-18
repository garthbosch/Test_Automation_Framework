package testFramework.enums;

import java.io.Serializable;

/**
 *
 * @author Garth Bosch
 */
public enum Priority implements Serializable {

    Urgent(1, "Urgent", 2),
    High(2, "High", 3),
    Normal(3, "Normal", 4),
    Low(4, "Low", 5),
    Critical(5, "Critical", 1);
    private static final long serialVersionUID = -6021731140979373963L;
    private final Integer priorityId;
    private final String priortiyDescription;
    private final Integer sortOrder;

    Priority(Integer priorityId, String priortiyDescription, Integer sortOrder) {
        this.priorityId = priorityId;
        this.priortiyDescription = priortiyDescription;
        this.sortOrder = sortOrder;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public String getPriortiyDescription() {
        return priortiyDescription;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }
}

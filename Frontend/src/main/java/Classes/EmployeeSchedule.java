package Classes;

public class EmployeeSchedule {
    private int employeeId;
    private int taskId;
    private int workstationId;
    private String shift;
    private boolean isAvailable;

    public EmployeeSchedule(){

    }

    public EmployeeSchedule(int employeeId, int taskId, int workstationId, String shift, boolean isAvailable){
        this.employeeId = employeeId;
        this.taskId = taskId;
        this.workstationId = workstationId;
        this.shift = shift;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "EmployeeSchedule{" +
                "employeeId=" + employeeId +
                ", taskId=" + taskId +
                ", workstationId=" + workstationId +
                ", shift='" + shift + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getWorkstationId() {
        return workstationId;
    }

    public void setWorkstationId(int workstationId) {
        this.workstationId = workstationId;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}



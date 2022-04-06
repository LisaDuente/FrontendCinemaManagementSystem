package Classes;

public class EmployeeWorkplan {
    String employeeName;
    String workstation;
    String task;
    String shift;

    public EmployeeWorkplan(){
    }


    public EmployeeWorkplan(String employeeName, String workstation, String task, String shift){
        this.employeeName = employeeName;
        this.workstation = workstation;
        this.task = task;
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "EmployeeWorkplan{" +
                "employeeName='" + employeeName + '\'' +
                ", workstation='" + workstation + '\'' +
                ", task='" + task + '\'' +
                ", shift='" + shift + '\'' +
                '}';
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}

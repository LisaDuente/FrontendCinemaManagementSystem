package Classes;

public class Employee {  // Toros

    private int employeeID;
    private String employeeName;
    private String employeeTel;
    private String employeeEmail;

    public Employee() {

    }

    public Employee(int employeeID, String employeeName, String employeeTel, String employeeEmail) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeTel = employeeTel;
        this.employeeEmail = employeeEmail;
    }

    @Override
    public String toString() {
        return "Classes.Employee{" +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName + '\'' +
                ", employeeTel='" + employeeTel + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                '}';
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeTel() {
        return employeeTel;
    }

    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    // below method has been replaced by encodeToURL()
    public void changeSpecialCharacters(){
        if (this.employeeName.contains(" ")) {
            this.employeeName = this.getEmployeeName().replace(" ","+");
        }
        if(this.employeeTel.contains(" ")){
            this.employeeTel = this.employeeTel.replace(" ","+");
        }
        if(this.employeeEmail.contains("@")){
            this.employeeEmail = this.employeeEmail.replace("@","%40");
            this.employeeEmail = this.employeeEmail.replace(".","%2E");
        }
    }
}


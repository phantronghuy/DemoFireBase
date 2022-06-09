package group7.com.demofirebase;



public class EmployeeInfo {



    // string variable for

    // storing employee name.

    private String employeeName;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "employeeName='" + employeeName + '\'' +
                ", id=" + id +
                ", employeeContactNumber='" + employeeContactNumber + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                '}';
    }
    // string variable for storing

    // employee contact number

    private String employeeContactNumber;



    // string variable for storing

    // employee address.

    private String employeeAddress;



    // an empty constructor is

    // required when using

    // Firebase Realtime Database.

    public EmployeeInfo() {



    }



    // created getter and setter methods

    // for all our variables.

    public String getEmployeeName() {

        return employeeName;

    }



    public void setEmployeeName(String employeeName) {

        this.employeeName = employeeName;

    }



    public String getEmployeeContactNumber() {

        return employeeContactNumber;

    }



    public void setEmployeeContactNumber(String employeeContactNumber) {

        this.employeeContactNumber = employeeContactNumber;

    }



    public String getEmployeeAddress() {

        return employeeAddress;

    }



    public void setEmployeeAddress(String employeeAddress) {

        this.employeeAddress = employeeAddress;

    }
}


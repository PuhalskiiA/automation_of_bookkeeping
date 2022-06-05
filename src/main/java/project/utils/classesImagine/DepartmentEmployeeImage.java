package project.utils.classesImagine;

public class DepartmentEmployeeImage {
    private DepartmentImage department;
    private EmployeeImage employee;

    public DepartmentEmployeeImage() {
    }

    public DepartmentEmployeeImage(DepartmentImage department, EmployeeImage employee) {
        this.department = department;
        this.employee = employee;
    }

    public DepartmentImage getDepartment() {
        return department;
    }

    public EmployeeImage getEmployee() {
        return employee;
    }
}

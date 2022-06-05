package project.exception;

public class DepartmentsEmployeesNotFoundException extends RuntimeException{
    public DepartmentsEmployeesNotFoundException(String msg) {
        super(msg);
    }
}

package project.utils.classesImagine;

public class EmployeeImage {
    private Long id;
    private String firstName;
    private String lastName;
    private String patherName;
    private String position;
    private int salary;

    public EmployeeImage() {
    }

    public EmployeeImage(Long id, String firstName, String lastName, String patherName, String position, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.position = position;
        this.salary = salary;
    }

    public EmployeeImage(String firstName, String lastName, String patherName, String position, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatherName() {
        return patherName;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }
}

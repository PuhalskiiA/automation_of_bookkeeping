package project.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments_employees")
public class DepartmentsEmployees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "departments_id",
            referencedColumnName = "id"
    )
    private Departments department;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "employees_id",
            referencedColumnName = "id"
    )
    private Employee employee;

    public DepartmentsEmployees() {

    }

    public DepartmentsEmployees(Departments department, Employee employee) {
        this.department = department;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "DepartmentsEmployees{" +
                "id=" + id +
                ", department=" + department +
                ", employee=" + employee +
                '}';
    }
}

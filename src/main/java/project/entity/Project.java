package project.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long cost;
    private Date dateBeg;
    private Date dateEnd;
    private Date dateEndReal;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Departments department;

    public Project() {}

    public Project(Departments department, String name, Long cost, Date dateBeg, Date dateEnd, Date dateEndReal) {
        this.department = department;
        this.name = name;
        this.cost = cost;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateEndReal = dateEndReal;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Date getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(Date dateBeg) {
        this.dateBeg = dateBeg;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateEndReal() {
        return dateEndReal;
    }

    public void setDateEndReal(Date dateEndReal) {
        this.dateEndReal = dateEndReal;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", dateBeg=" + dateBeg +
                ", dateEnd=" + dateEnd +
                ", dateEndReal=" + dateEndReal +
                '}';
    }
}

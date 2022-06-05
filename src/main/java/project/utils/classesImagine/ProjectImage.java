package project.utils.classesImagine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;

public class ProjectImage {
    private String name;
    private Long cost;
    private Date dateBeg;
    private Date dateEnd;
    private Date dateEndReal;
    private DepartmentImage department;

    public ProjectImage() {
    }

    public ProjectImage(String name, Long cost, Date dateBeg, Date dateEnd, Date dateEndReal, String department)
            throws JsonProcessingException {
        this.name = name;
        this.cost = cost;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateEndReal = dateEndReal;
        this.department = new ObjectMapper().readValue(department, DepartmentImage.class);
    }

    public String getName() {
        return name;
    }

    public Long getCost() {
        return cost;
    }

    public Date getDateBeg() {
        return dateBeg;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Date getDateEndReal() {
        return dateEndReal;
    }

    public DepartmentImage getDepartment() {
        return department;
    }
}

package project.service;

import project.entity.Project;

import java.sql.Date;
import java.util.List;

public interface ProjectService {
    Project findProjectById(Long id);

    List<Project> findProjectsByName(String name);

    List<Project> findProjectsByCost(Long cost);

    Long getDepartmentIdByProjectId(Long id);

    Project insertProject(Project project);

    Project removeProjectById(Long id);

    Project updateProjectById(Long id, String name, Long cost, Date dateBeg, Date dateEnd, Date dateEndReal);

    List<Long> findEmployeesIdByProjectId(Long id);
}

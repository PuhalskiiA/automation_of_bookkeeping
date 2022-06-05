package project.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectById(Long id);

    List<Project> findProjectsByName(String name);

    List<Project> findProjectsByCost(Long cost);

    @Query(value = "select s.id from departments s inner join project on s.id = project.department_id " +
            "and project.id = ?1", nativeQuery = true)
    Optional<Long> getDepartmentIdByProjectId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from project where project.id = ?1", nativeQuery = true)
    void removeProjectById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update project set name = ?2, cost = ?3, date_beg = ?4, date_end = ?5, " +
            "date_end_real = ?6 where project.id = ?1", nativeQuery = true)
    void updateProjectById(Long id, String name, Long cost, Date dateBeg, Date dateEnd, Date dateEndReal);

    @Query(value = "select s.id from employee s " +
            "inner join departments_employees de on s.id = de.employees_id " +
            "inner join departments d on d.id = de.departments_id " +
            "inner join project p on d.id = p.department_id and p.id = ?1",
            nativeQuery = true)
    List<Long> findEmployeesIdByProjectId(Long id);
}

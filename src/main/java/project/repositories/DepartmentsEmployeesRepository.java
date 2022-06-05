package project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.DepartmentsEmployees;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentsEmployeesRepository extends JpaRepository<DepartmentsEmployees, Long> {

    @Query(value = "select s.employees_id from departments_employees s " +
            "inner join departments on s.departments_id = departments.id " +
            "inner join employee on s.employees_id = employee.id and departments.id = ?1",
            nativeQuery = true)
    List<Long> findEmployeesIdByDepartmentId(Long id);

    @Query(value = "select s.departments_id from departments_employees s " +
            "inner join departments on s.departments_id = departments.id " +
            "inner join employee on s.employees_id = employee.id and employee.id = ?1",
            nativeQuery = true)
    List<Long> findDepartmentsIdByEmployeeId(Long id);

    Optional<DepartmentsEmployees> findDepartmentsEmployeesByEmployeeIdAndDepartmentId(Long empId, Long depId);
}

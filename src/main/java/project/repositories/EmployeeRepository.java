package project.repositories;

import org.springframework.data.jpa.repository.Modifying;
import project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeById(Long id);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByFirstName(String firstName);
    
    List<Employee> findByPatherName(String patherName);

    List<Employee> findBySalary(int salary);

    List<Employee> findByPosition(String position);

    Employee findEmployeeByFirstNameAndLastNameAndPatherNameAndPositionAndSalary(String firstName, String lastName,
                                                                                 String patherName, String posoton,
                                                                                 int salary);

    @Modifying
    @Transactional
    @Query(value = "insert into employee(first_name, last_name, pather_name, position, salary) "
            + "VALUES(?, ?, ?, ?, ?)", nativeQuery = true)
    void insertEmployee(String firstName, String lastName, String patherName, String position, int salary);

    @Modifying
    @Transactional
    @Query(value = "delete from employee where employee.id = ?1", nativeQuery = true)
    void removeEmployeeById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update employee set first_name = ?2, last_name = ?3, pather_name = ?4," +
            "position = ?5, salary = ?6 where employee.id = ?1", nativeQuery = true)
    void updateTheNameOfEmployeeById(Long id, String firstName, String lastName, String patherName,
                                     String position, int salary);

    @Query(value = "select s.id from project s " +
            "inner join departments d on s.department_id = d.id " +
            "inner join departments_employees de on d.id = de.departments_id " +
            "inner join employee e on de.employees_id = e.id and e.id = ?1",
            nativeQuery = true)
    List<Long> findProjectsIdByEmployeeId(Long id);
}

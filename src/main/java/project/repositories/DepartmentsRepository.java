package project.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import project.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Long> {
    Optional<Departments> findDepartmentById(Long id);

    Optional<Departments> findDepartmentByName(String Name);

    @Query(value = "select s.id from project s " +
            "inner join departments on s.department_id = departments.id " +
            "and departments.id = ?1", nativeQuery = true)
    List<Long> getProjectsIdByDepartmentId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from departments where id = ?1", nativeQuery = true)
    void removeDepartmentsById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update departments set name = ?2 where id = ?1", nativeQuery = true)
    void updateTheNameOfDepartmentById(Long id, String name);

}

package project.service;

import project.entity.DepartmentsEmployees;

import java.util.List;

public interface DepartmentsEmployeesService {
    List<Long> findEmployeesIdByDepartmentId(Long id);

    List<Long> findDepartmentsIdByEmployeeId(Long id);

    DepartmentsEmployees insertDepartmentsEmployees(DepartmentsEmployees departmentsEmployees);

    DepartmentsEmployees removeDepartmentsEmployeesByEmployeeIdAndDepartmentId(Long empId, Long depId);


}

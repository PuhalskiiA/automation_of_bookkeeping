package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Departments;
import project.entity.DepartmentsEmployees;
import project.entity.Employee;
import project.exception.DepartmentNotFoundException;
import project.exception.DepartmentsEmployeesNotFoundException;
import project.exception.EmployeeNotFoundException;
import project.repositories.DepartmentsEmployeesRepository;
import project.repositories.DepartmentsRepository;
import project.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsEmployeesServiceImpl implements DepartmentsEmployeesService {
    @Autowired
    private DepartmentsEmployeesRepository departmentsEmployeesRepository;
    @Autowired
    private DepartmentsRepository departmentsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Long> findEmployeesIdByDepartmentId(Long id) {
        Optional<Departments> opDep = departmentsRepository.findDepartmentById(id);

        if (opDep.isPresent()) {
            return departmentsEmployeesRepository.findEmployeesIdByDepartmentId(id);
        }
        else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    @Override
    public List<Long> findDepartmentsIdByEmployeeId(Long id) {
        Optional<Employee> opEmp = employeeRepository.findEmployeeById(id);

        if (opEmp.isPresent()) {
            return departmentsEmployeesRepository.findDepartmentsIdByEmployeeId(id);
        }
        else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public DepartmentsEmployees insertDepartmentsEmployees(DepartmentsEmployees departmentsEmployees) {
        return departmentsEmployeesRepository.save(departmentsEmployees);
    }

    @Override
    public DepartmentsEmployees removeDepartmentsEmployeesByEmployeeIdAndDepartmentId(Long empId, Long depId) {
        Optional<Employee> opEmp = employeeRepository.findEmployeeById(empId);
        Optional<Departments> opDep = departmentsRepository.findDepartmentById(depId);
        Optional<DepartmentsEmployees> opDepEmp = departmentsEmployeesRepository
                .findDepartmentsEmployeesByEmployeeIdAndDepartmentId(empId, depId);

        if (opEmp.isPresent() && opDep.isPresent() && opDepEmp.isPresent()) {
            DepartmentsEmployees saveDepEmp = opDepEmp.get();

            departmentsEmployeesRepository.delete(opDepEmp.get());

            return saveDepEmp;
        }
        else {
            throw new DepartmentsEmployeesNotFoundException("Can't find departmentsEmployees object");
        }
    }

}

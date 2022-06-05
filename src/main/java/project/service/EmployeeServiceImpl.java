package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Employee;
import project.exception.EmployeeNotFoundException;
import project.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findEmployeeById(Long id) {
        Optional<Employee> opEmp = employeeRepository.findEmployeeById(id);

        if (opEmp.isPresent()) {
            return opEmp.get();
        }
        else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    public List<Employee> findByPatherName(String patherName) {
        return employeeRepository.findByPatherName(patherName);
    }

    @Override
    public List<Employee> findBySalary(int salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    @Override
    public Employee insertEmployee(String firstName, String lastName, String patherName, String position, int salary) {
        employeeRepository.insertEmployee(firstName, lastName, patherName, position, salary);
        return employeeRepository.findEmployeeByFirstNameAndLastNameAndPatherNameAndPositionAndSalary(firstName,
                lastName, patherName, position, salary);
    }

    @Override
    public Employee removeEmployeeById(Long id) {
        Optional<Employee> opEmp = employeeRepository.findEmployeeById(id);

        if (opEmp.isPresent()) {
            employeeRepository.removeEmployeeById(id);
            return opEmp.get();
        }
        else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public Employee updateTheNameOfEmployeeById(Long id, String firstName, String lastName,
                                            String patherName, String position, int salary) {
        Optional<Employee> opEmp = employeeRepository.findEmployeeById(id);

        if (opEmp.isPresent()) {
            employeeRepository.updateTheNameOfEmployeeById(id, firstName, lastName, patherName, position, salary);
            return employeeRepository.findEmployeeById(id).get();
        }
        else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public List<Long> findProjectsIdByEmployeeId(Long id) {
        Optional<Employee> opEmp = employeeRepository.findEmployeeById(id);

        if (opEmp.isPresent()) {
            return employeeRepository.findProjectsIdByEmployeeId(id);
        }
        else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }
}

package project.service;

import project.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findEmployeeById(Long id);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByPatherName(String patherName);

    List<Employee> findBySalary(int salary);

    List<Employee> findByPosition(String position);

    Employee insertEmployee(String firstName, String lastName, String patherName, String position, int salary);

    Employee removeEmployeeById(Long id);

    Employee updateTheNameOfEmployeeById(Long id, String firstName, String lastName, String patherName,
                                     String position, int salary);

    List<Long> findProjectsIdByEmployeeId(Long id);
}

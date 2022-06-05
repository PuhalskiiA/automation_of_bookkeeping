package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Employee;
import project.exception.EmployeeNotFoundException;
import project.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeesController {
    private EmployeeService employeeService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    @GetMapping("/getByLastName/{lastName}")
    public ResponseEntity<List<Employee>> getEmployeesByLastName(@PathVariable("lastName") String lastName) {
        return new ResponseEntity<>(employeeService.findByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/getByFirstName/{firstName}")
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(@PathVariable("firstName") String firstName) {
        return new ResponseEntity<>(employeeService.findByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/getByPatherName/{patherName}")
    public ResponseEntity<List<Employee>> getEmployeesByPatherName(@PathVariable("patherName") String patherName) {
        return new ResponseEntity<>(employeeService.findByPatherName(patherName), HttpStatus.OK);
    }

    @GetMapping("/getBySalary/{salary}")
    public ResponseEntity<List<Employee>> getEmployeesBySalary(@PathVariable("salary") int salary) {
        return new ResponseEntity<>(employeeService.findBySalary(salary), HttpStatus.OK);
    }

    @GetMapping("/getByPosition/{position}")
    public ResponseEntity<List<Employee>> getEmployeesByPosition(@PathVariable("position") String position) {
        return new ResponseEntity<>(employeeService.findByPosition(position), HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeById/{id}")
    public ResponseEntity<Employee> removeEmployeeById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(employeeService.removeEmployeeById(id), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    @PostMapping(value = "/add",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.insertEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getPatherName(), employee.getPosition(), employee.getSalary()),
                HttpStatus.OK);
    }

    @PutMapping(value = "/updateById/{id}",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Employee> updateTheNameOfEmployeeById(@PathVariable("id") Long id,
                                                            @RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeService.updateTheNameOfEmployeeById(id, employee.getFirstName(),
                    employee.getLastName(), employee.getPatherName(), employee.getPosition(), employee.getSalary()),
                    HttpStatus.OK);
        }
        catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    @GetMapping("/employeeId-projectsId/{id}")
    public ResponseEntity<List<Long>> getProjectsIdByEmployeeId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findProjectsIdByEmployeeId(id), HttpStatus.OK);
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}

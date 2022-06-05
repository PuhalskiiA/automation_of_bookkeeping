package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.entity.DepartmentsEmployees;
import project.exception.DepartmentNotFoundException;
import project.exception.DepartmentsEmployeesNotFoundException;
import project.exception.EmployeeNotFoundException;
import project.service.DepartmentsEmployeesService;

import java.util.List;

@RestController
@RequestMapping("/departmentsEmployees")
public class DepartmentsEmployeesController {
    private DepartmentsEmployeesService departmentsEmployeesService;

    @GetMapping("/departmentId-employeesId/{id}")
    public ResponseEntity<List<Long>> getEmployeesIdByDepartmentId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(departmentsEmployeesService.findEmployeesIdByDepartmentId(id), HttpStatus.OK);
        }
        catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @GetMapping("/employeeId-departmentsId/{id}")
    public ResponseEntity<List<Long>> getDepartmentsIdByEmployeeId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(departmentsEmployeesService.findDepartmentsIdByEmployeeId(id), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    @PostMapping(value = "/add",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<DepartmentsEmployees> addDepartmentsEmployees(@RequestBody DepartmentsEmployees departmentsEmployees) {
        return new ResponseEntity<>(departmentsEmployeesService.insertDepartmentsEmployees(departmentsEmployees),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeByEmpIdAndDepId/{empId}/{depId}")
    public ResponseEntity<DepartmentsEmployees> removeDepartmentsEmployees(@PathVariable("empId") Long empId,
                                                           @PathVariable("depId") Long depId) {
        try {
            return new ResponseEntity<>(departmentsEmployeesService
                    .removeDepartmentsEmployeesByEmployeeIdAndDepartmentId(empId, depId),
                    HttpStatus.OK);
        }
        catch (DepartmentsEmployeesNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    public void setDepartmentsEmployeesService(DepartmentsEmployeesService departmentsEmployeesService) {
        this.departmentsEmployeesService = departmentsEmployeesService;
    }
}

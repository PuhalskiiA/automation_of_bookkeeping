package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Departments;
import project.exception.DepartmentNotFoundException;
import project.service.DepartmentsService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private DepartmentsService departmentsService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Departments> getDepartmentById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(departmentsService.findDepartmentById(id), HttpStatus.OK);
        }
        catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Departments> getDepartmentByName(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(departmentsService.findDepartmentByName(name), HttpStatus.OK);
        }
        catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @GetMapping("/departmentId-projectsId/{id}")
    public ResponseEntity<List<Long>> getProjectsIdByDepartmentId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(departmentsService.getProjectsIdByDepartmentId(id), HttpStatus.OK);
        }
        catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @PostMapping(value = "/add",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Departments> addDepartment(@RequestBody Departments newDep) {
        return new ResponseEntity<>(departmentsService.insertDepartment(newDep),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeById/{id}")
    public ResponseEntity<Departments> removeDepartmentById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(departmentsService.removeDepartmentById(id),
                    HttpStatus.OK);
        }
        catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @PutMapping(value = "/updateByIdAndName/{id}/{name}")
    public ResponseEntity<Departments> updateDepartmentById(@PathVariable("id") Long id,
                                                            @PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(departmentsService.updateDepartmentById(id, name),
                    HttpStatus.OK);
        }
        catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @Autowired
    public void setDepartmentsService(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }
}

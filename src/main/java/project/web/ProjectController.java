package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Project;
import project.exception.ProjectNotFoundException;
import project.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(projectService.findProjectById(id), HttpStatus.OK);
        }
        catch (ProjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Project>> getProjectsByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(projectService.findProjectsByName(name), HttpStatus.OK);
    }

    @GetMapping("/getByCost/{cost}")
    public ResponseEntity<List<Project>> getProjectsByCost(@PathVariable("cost") Long cost) {
        return new ResponseEntity<>(projectService.findProjectsByCost(cost), HttpStatus.OK);
    }

    @GetMapping("/projectId-departmentId/{id}")
    public ResponseEntity<Long> getDepartmentIdByProjectId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(projectService.getDepartmentIdByProjectId(id), HttpStatus.OK);
        }
        catch (ProjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @PostMapping(value = "/add",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.insertProject(project), HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeById/{id}")
    public ResponseEntity<Project> removeProjectById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(projectService.removeProjectById(id), HttpStatus.OK);
        }
        catch (ProjectNotFoundException exception) {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @PutMapping(value = "/updateById/{id}", consumes = "application/json")
    public ResponseEntity<Project> updateProjectById(@PathVariable("id") Long id, @RequestBody Project project) {
        try {
            return new ResponseEntity<>(projectService.updateProjectById(id, project.getName(), project.getCost(),
                    project.getDateBeg(), project.getDateEnd(), project.getDateEndReal()), HttpStatus.OK);
        }
        catch (ProjectNotFoundException exception) {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @GetMapping("/projectId-employeesId/{id}")
    public ResponseEntity<List<Long>> getEmployeesIdByProjectId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(projectService.findEmployeesIdByProjectId(id), HttpStatus.OK);
        }
        catch (ProjectNotFoundException exception) {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}

package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Project;
import project.exception.ProjectNotFoundException;
import project.repositories.ProjectRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project findProjectById(Long id) {
        Optional<Project> opPr = projectRepository.findProjectById(id);

        if (opPr.isPresent()) {
            return opPr.get();
        }
        else {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Override
    public List<Project> findProjectsByName(String name) {
        return projectRepository.findProjectsByName(name);
    }

    @Override
    public List<Project> findProjectsByCost(Long cost) {
        return projectRepository.findProjectsByCost(cost);
    }

    @Override
    public Long getDepartmentIdByProjectId(Long id) {
        Optional<Long> opDepId = projectRepository.getDepartmentIdByProjectId(id);

        if (opDepId.isPresent()) {
            return opDepId.get();
        }
        else {
            throw new ProjectNotFoundException("Can't find department Id by project Id");
        }
    }

    @Override
    public Project insertProject(Project project) {
        projectRepository.save(project);
        return project;
    }

    @Override
    public Project removeProjectById(Long id) {
        Optional<Project> opProj = projectRepository.findProjectById(id);

        if (opProj.isPresent()) {
            projectRepository.removeProjectById(id);
            return opProj.get();
        }
        else {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Override
    public Project updateProjectById(Long id, String name, Long cost, Date dateBeg, Date dateEnd, Date dateEndReal) {
        Optional<Project> opProj = projectRepository.findProjectById(id);

        if (opProj.isPresent()) {
            projectRepository.updateProjectById(id, name, cost, dateBeg, dateEnd, dateEndReal);
            return projectRepository.findProjectById(id).get();
        }
        else {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Override
    public List<Long> findEmployeesIdByProjectId(Long id) {
        Optional<Project> opProj = projectRepository.findProjectById(id);

        if (opProj.isPresent()) {
            return projectRepository.findEmployeesIdByProjectId(id);
        }
        else {
            throw new ProjectNotFoundException("Project not found");
        }
    }
}

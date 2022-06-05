package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Departments;
import project.exception.DepartmentNotFoundException;
import project.repositories.DepartmentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Override
    public Departments findDepartmentById(Long id) {
        Optional<Departments> opDep = departmentsRepository.findById(id);

        if (opDep.isPresent()) {
            return opDep.get();
        }
        else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    @Override
    public Departments findDepartmentByName(String name) {
        Optional<Departments> opDep = departmentsRepository.findDepartmentByName(name);

        if (opDep.isPresent()) {
            return opDep.get();
        }
        else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    @Override
    public List<Long> getProjectsIdByDepartmentId(Long id) {
        Optional<Departments> opDep = departmentsRepository.findDepartmentById(id);

        if (opDep.isPresent()) {
            return departmentsRepository.getProjectsIdByDepartmentId(id);
        }
        else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    @Override
    public Departments insertDepartment(Departments department) {
        departmentsRepository.save(department);
        return department;
    }

    @Override
    public Departments removeDepartmentById(Long id) {
        Optional<Departments> opDep = departmentsRepository.findDepartmentById(id);

        if (opDep.isPresent()) {
            departmentsRepository.removeDepartmentsById(id);
            return opDep.get();
        }
        else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    @Override
    public Departments updateDepartmentById(Long id, String name) {
        Optional<Departments> opDep = departmentsRepository.findDepartmentById(id);

        if (opDep.isPresent()) {
            departmentsRepository.updateTheNameOfDepartmentById(id, name);
            return departmentsRepository.findDepartmentById(id).get();
        }
        else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }
}

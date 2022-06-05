package project.service;

import project.entity.Departments;

import java.util.List;

public interface DepartmentsService {
    Departments findDepartmentById(Long id);

    Departments findDepartmentByName(String Name);

    List<Long> getProjectsIdByDepartmentId(Long id);

    Departments insertDepartment(Departments department);

    Departments removeDepartmentById(Long id);

    Departments updateDepartmentById(Long id, String name);
}

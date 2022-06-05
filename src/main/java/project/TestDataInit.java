package project;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.entity.*;
import project.repositories.*;
import project.service.CustomUserDetailsService;

import java.util.Collections;
import java.sql.Date;

@Component
public class TestDataInit implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentsEmployeesRepository departmentsEmployeesRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    public void run(String... args) throws Exception {
        Employee emp1 = new Employee("Aleksandr", "Puhalskii",
                "Igorevich", "Russia", 999999);
        Employee emp2 = new Employee("Ilya", "Arestov",
                "Maximovich", "Russia", 999999);
        Employee emp3 = new Employee("Aleksandr", "Tihonov",
                "Nicolaevich", "Russia", 999999);

        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);

        Departments dep1 = new Departments("firstDepartment");
        Departments dep2 = new Departments("secondDepartment");
        Departments dep3 = new Departments("thirdDepartment");

        departmentsRepository.save(dep1);
        departmentsRepository.save(dep2);

        Date dateBeg = new Date(122, 1, 2);
        Date dateEnd = new Date(123, 1, 2);
        Date dateEndReal = new Date(123, 4, 2);

        Project prj1 = new Project(dep1, "myFirstProject", 1500L, dateBeg, dateEnd, dateEndReal);
        Project prj2 = new Project(dep2, "mySecondProject", 3000L, dateBeg, dateEnd, dateEndReal);
        Project prj3 = new Project(dep2, "mySecondProject2", 3000L, dateBeg, dateEnd, dateEndReal);

        projectRepository.save(prj1);
        projectRepository.save(prj2);
        projectRepository.save(prj3);

        Employee emp5 = new Employee("Aleksandr", "Sirafimovich",
                "Igorevich", "Russia", 2500);
        Employee emp6 = new Employee("Nikita", "Evdokimov",
                "Alexeevich", "USA", 3600);

        employeeRepository.save(emp5);
        employeeRepository.save(emp6);

        departmentsEmployeesRepository.save(new DepartmentsEmployees(dep1, emp6));
        departmentsEmployeesRepository.save(new DepartmentsEmployees(dep2, emp5));
        departmentsEmployeesRepository.save(new DepartmentsEmployees(dep2, emp6));

        departmentsRepository.save(new Departments("ourDep"));

        userRepository.save(new User("user", passwordEncoder.encode("AnyoneMayDie123"),
                Collections.singletonList("ROLE_USER")));

        Gson gson = new Gson();
        System.out.println(gson.toJson(new DepartmentsEmployees(dep1, emp6)));
        System.out.println(gson.toJson(prj1));
    }
}

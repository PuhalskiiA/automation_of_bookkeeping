package project.utils.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import project.utils.classesImagine.DepartmentEmployeeImage;
import project.utils.classesImagine.DepartmentImage;
import project.utils.classesImagine.EmployeeImage;

import javax.naming.AuthenticationException;
import java.util.Scanner;

public class DepartmentEmployeeCommands {
    private final static RestTemplate restTemplate = new RestTemplate();
    private static ResponseEntity<String> responseEntity;
    private final static HttpHeaders headers = new HttpHeaders();


    public static void getEmployeesByDep(Scanner scan, String url) {
        try {
            System.out.println("Enter department id:");

            if (scan.hasNextLong()) {
                Long id = scan.nextLong();

                responseEntity = restTemplate.getForEntity(url + "/departmentsEmployees/departmentId-employeesId/"
                        + id, String.class);

                System.out.println(responseEntity.getStatusCode());
                System.out.println(responseEntity.getBody());
            } else {
                throw new NumberFormatException("Value must be integer");
            }
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department");
        }
    }

    public static void getDepartmentsByEmp(Scanner scan, String url) {
        try {
            System.out.println("Enter employee id:");

            if (scan.hasNextLong()) {
                Long id = scan.nextLong();

                responseEntity = restTemplate.getForEntity(url + "/departmentsEmployees/employeeId-departmentsId/"
                        + id, String.class);

                System.out.println(responseEntity.getStatusCode());
                System.out.println(responseEntity.getBody());
            } else {
                throw new NumberFormatException("Value must be integer");
            }
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check employee");
        }
    }

    public static void add(Scanner scan, String url, String token) {
        try {
            if (token == null) {
                throw new AuthenticationException("You should authorized");
            } else {
                System.out.println("Enter id of department");

                Long depId = 0L;
                if (scan.hasNextLong()) {
                    depId = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                System.out.println("Enter the name of department:");
                String depName = scan.next();

                System.out.println("Enter id of employee:");

                Long empId = 0L;
                if (scan.hasNextLong()) {
                    empId = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                System.out.println("Enter first name of employee:");
                String firstName = scan.next();

                System.out.println("Enter second name of employee:");
                String secondName = scan.next();

                System.out.println("Enter pather name of employee:");
                String patherName = scan.next();

                System.out.println("Enter position of employee:");
                String position = scan.next();

                System.out.println("Enter salary of employee:");

                int salary = 0;
                if (scan.hasNextInt()) {
                    salary = scan.nextInt();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                DepartmentImage department = new DepartmentImage(depId, depName);
                EmployeeImage employee = new EmployeeImage(empId, firstName, secondName, patherName, position, salary);
                DepartmentEmployeeImage departmentEmployee = new DepartmentEmployeeImage(department, employee);

                Gson gson = new Gson();

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(gson.toJson(departmentEmployee), headers);

                restTemplate.exchange(url + "/departmentsEmployees/add", HttpMethod.POST, request, String.class);

                System.out.println("Update is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department or employee, or token expired");
        }
    }

    public static void remove(Scanner scan, String url, String token) {
        try {
            if (token == null) {
                throw new AuthenticationException("You should authorized");
            } else {
                System.out.println("Enter id of department:");

                Long depId = 0L;
                if (scan.hasNextLong()) {
                    depId = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                System.out.println("Enter id of employee:");

                Long empId = 0L;
                if (scan.hasNextLong()) {
                    empId = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);

                HttpEntity<String> request = new HttpEntity<>(headers);

                restTemplate.exchange(url + "/departmentsEmployees/removeByEmpIdAndDepId/" + empId + "/" + depId,
                        HttpMethod.DELETE, request, String.class);

                System.out.println("Remove is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department or employee, or token expired");
        }
    }
}

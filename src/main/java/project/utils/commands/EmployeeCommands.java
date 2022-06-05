package project.utils.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import project.utils.classesImagine.EmployeeImage;

import javax.naming.AuthenticationException;
import java.util.Scanner;

public class EmployeeCommands {
    private final static RestTemplate restTemplate = new RestTemplate();
    private static ResponseEntity<String> responseEntity;
    private final static HttpHeaders headers = new HttpHeaders();

    public static void getById(Scanner scan, String url) {
        try {
            System.out.println("Enter id:");

            if (scan.hasNextLong()) {
                Long id = scan.nextLong();

                responseEntity = restTemplate.getForEntity(url + "/employee/getById/" + id, String.class);

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

    public static void getByLastName(Scanner scan, String url) {
        try {
            System.out.println("Enter last name:");

            String lastName = scan.next();

            responseEntity = restTemplate.getForEntity(url + "/employee/getByLastName/" + lastName, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (Exception exception) {
            System.err.println("Something wrong. Try again");
        }
    }

    public static void getByFirstName(Scanner scan, String url) {
        try {
            System.out.println("Enter name:");

            String firstName = scan.next();

            responseEntity = restTemplate.getForEntity(url + "/employee/getByFirstName/" + firstName, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (Exception exception) {
            System.err.println("Something wrong.Try again");
        }
    }

    public static void getByPatherName(Scanner scan, String url) {
        try {
            System.out.println("Enter pather name:");

            String patherName = scan.next();

            responseEntity = restTemplate.getForEntity(url + "/employee/getByPatherName/" + patherName, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (Exception exception) {
            System.err.println("Something wrong. Try again");
        }
    }

    public static void getBySalary(Scanner scan, String url) {
        try {
            System.out.println("Enter salary:");

            if (scan.hasNextInt()) {
                int salary = scan.nextInt();

                responseEntity = restTemplate.getForEntity(url + "/employee/getBySalary/" + salary, String.class);

                System.out.println(responseEntity.getStatusCode());
                System.out.println(responseEntity.getBody());
            } else {
                throw new NumberFormatException("Value must be integer");
            }
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Try again");
        }
    }

    public static void getByPosition(Scanner scan, String url) {
        try {
            System.out.println("Enter position:");

            String position = scan.next();

            responseEntity = restTemplate.getForEntity(url + "/employee/getByPosition/" + position, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (Exception exception) {
            System.err.println("Something wrong. Try again");
        }
    }

    public static void remove(Scanner scan, String url, String token) {
        try {
            if (token == null) {
                throw new AuthenticationException("You should authorized");
            } else {
                System.out.println("Enter id:");

                Long id = 0L;
                if (scan.hasNextLong()) {
                    id = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(headers);

                restTemplate.exchange(url + "/employee/removeById/" + id, HttpMethod.DELETE, request, String.class);

                System.out.println("Remove is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check employee or token expired. " +
                    "If you want remove employee, you should delete department-employee connection");
        }
    }

    public static void add(Scanner scan, String url, String token) {
        try {
            if (token == null) {
                throw new AuthenticationException("You should authorized");
            } else {
                System.out.println("Enter firstName:");
                String firstName = scan.next();

                System.out.println("Enter last name:");
                String lastName = scan.next();

                System.out.println("Enter pather name:");
                String patherName = scan.next();

                System.out.println("Enter position:");
                String position = scan.next();

                System.out.println("Enter salary:");

                int salary = 0;
                if (scan.hasNextInt()) {
                    salary = scan.nextInt();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                EmployeeImage employee = new EmployeeImage(firstName, lastName, patherName, position, salary);

                Gson gson = new Gson();

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(gson.toJson(employee), headers);

                restTemplate.exchange(url + "/employee/add", HttpMethod.POST, request, String.class);

                System.out.println("Add is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Try again. Maybe token expired");
        }
    }

    public static void update(Scanner scan, String url, String token) {
        try {
            if (token == null) {
                throw new AuthenticationException("You should authorized");
            } else {
                System.out.println("Enter id:");

                Long id = 0L;
                if (scan.hasNextLong()) {
                    id = scan.nextLong();;
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                System.out.println("Enter firstName:");
                String firstName = scan.next();

                System.out.println("Enter last name:");
                String lastName = scan.next();

                System.out.println("Enter pather name:");
                String patherName = scan.next();

                System.out.println("Enter position:");
                String position = scan.next();

                System.out.println("Enter salary:");

                int salary = 0;
                if (scan.hasNextInt()) {
                    salary = scan.nextInt();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                EmployeeImage employee = new EmployeeImage(firstName, lastName, patherName, position, salary);

                Gson gson = new Gson();

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(gson.toJson(employee), headers);

                restTemplate.exchange(url + "/employee/updateById/" + id, HttpMethod.PUT, request, String.class);

                System.out.println("Update is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check employee or token expired");
        }
    }

    public static void getProjectsId(Scanner scan, String url) {
        try {
            System.out.println("Enter employee id:");

            Long id = 0L;
            if (scan.hasNextLong()) {
                id = scan.nextLong();

                responseEntity = restTemplate.getForEntity(url + "/employee/employeeId-projectsId/" + id, String.class);

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
}

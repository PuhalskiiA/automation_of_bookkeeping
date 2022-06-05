package project.utils.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import project.utils.classesImagine.DepartmentImage;

import javax.naming.AuthenticationException;
import java.util.Scanner;

public class DepartmentCommands {
    private final static RestTemplate restTemplate = new RestTemplate();
    private static ResponseEntity<String> responseEntity;
    private final static HttpHeaders headers = new HttpHeaders();

    public static void getById(Scanner scan, String url) {
        try {
            System.out.println("Enter id:");

            if (scan.hasNextLong()) {
                Long id = scan.nextLong();

                responseEntity = restTemplate.getForEntity(url + "/departments/getById/" + id, String.class);

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

    public static void getByName(Scanner scan, String url) {
        try {
            System.out.println("Enter name:");

            String name = scan.next();

            responseEntity = restTemplate.getForEntity(url + "/departments/getByName/" + name, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department");
        }
    }

    public static void getProjectsId(Scanner scan, String url) {
        try {
            System.out.println("Enter department id:");

            if (scan.hasNextLong()) {
                Long id = scan.nextLong();

                responseEntity = restTemplate.getForEntity(url + "/departments/departmentId-projectsId/" + id, String.class);

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

    public static void add(Scanner scan, String url, String token) {
        try {
            if (token == null) {
                throw new AuthenticationException("You should authorized");
            } else {
                System.out.println("Enter name:");

                String name = scan.next();

                DepartmentImage department = new DepartmentImage(name);

                Gson gson = new Gson();

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(gson.toJson(department), headers);

                restTemplate.exchange(url + "/departments/add", HttpMethod.POST, request, String.class);

                System.out.println("Add is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (Exception exception) {
            System.err.println("Something wrong. Try again. Maybe token expired");
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

                restTemplate.exchange(url + "/departments/removeById/" + id, HttpMethod.DELETE, request, String.class);

                System.out.println("Remove is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department or token expired. " +
                    "If you want remove department, you should delete department-employee or project connection");
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
                    id = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                System.out.println("Enter name:");

                String name = scan.next();

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(headers);

                restTemplate.exchange(url + "/departments/updateByIdAndName/" + id + "/" + name, HttpMethod.PUT,
                        request, String.class);

                System.out.println("Update is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department or token expired");
        }
    }
}

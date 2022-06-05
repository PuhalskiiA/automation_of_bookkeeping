package project.utils.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import project.utils.classesImagine.ProjectImage;
import project.utils.exceptionsForCommands.IncorrectDateException;

import javax.naming.AuthenticationException;
import java.sql.Date;
import java.util.Scanner;

public class ProjectCommands {
    private final static RestTemplate restTemplate = new RestTemplate();
    private static ResponseEntity<String> responseEntity;
    private final static HttpHeaders headers = new HttpHeaders();

    public static void getById(Scanner scan, String url) {
        try {
            System.out.println("Enter id:");

            Long id = 0L;
            if (scan.hasNextLong()) {
                id = scan.nextLong();
            } else {
                throw new NumberFormatException("Value must be integer");
            }

            responseEntity = restTemplate.getForEntity(url + "/project/getById/" + id, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check project");
        }
    }

    public static void getByName(Scanner scan, String url) {
        try {
            System.out.println("Enter name:");

            String name = scan.next();

            responseEntity = restTemplate.getForEntity(url + "/project/getByName/" + name, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (Exception exception) {
            System.err.println("Something wrong. Try again");
        }
    }

    public static void getByCost(Scanner scan, String url) {
        try {
            System.out.println("Enter cost:");

            Long cost = 0L;
            if (scan.hasNextLong()) {
                cost = scan.nextLong();
            } else {
                throw new NumberFormatException("Value must be integer");
            }

            responseEntity = restTemplate.getForEntity(url + "/project/getByCost/" + cost, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong.Try again");
        }
    }

    public static void getDepartment(Scanner scan, String url) {
        try {
            System.out.println("Enter project id:");

            Long id = 0L;
            if (scan.hasNextLong()) {
                id = scan.nextLong();
            } else {
                throw new NumberFormatException("Value must be integer");
            }

            responseEntity = restTemplate.getForEntity(url + "/project/projectId-departmentId/" + id, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check project");
        }
    }

    public static void add(Scanner scan, String url, String token) {
        try {
            if (token == null) {
                throw new AuthenticationException("You should authorized");
            } else {
                System.out.println("Enter the name of department:");
                String department = scan.next();

                responseEntity = restTemplate.getForEntity(url + "/departments/getByName/" + department, String.class);
                department = responseEntity.getBody();

                System.out.println("Enter the name of the project:");
                String projName = scan.next();

                Long cost = 0L;
                System.out.println("Enter cost:");
                if (scan.hasNextLong()) {
                    cost = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                System.out.println("Enter the date of begin(mm/dd/yyyy):");
                String dateBegStr = scan.next();
                Date dateBeg = DateParse.getDate(dateBegStr);

                System.out.println("Enter the date of end(mm/dd/yyyy):");
                String dateEndStr = scan.next();
                Date dateEnd = DateParse.getDate(dateEndStr);

                System.out.println("Enter the date of end real(mm/dd/yyyy):");
                String dateEndRealStr = scan.next();
                Date dateEndReal = DateParse.getDate(dateEndRealStr);

                if (dateBeg.after(dateEnd) || dateBeg.after(dateEndReal)) {
                    throw new IncorrectDateException("Incorrect date");
                }

                ProjectImage project = new ProjectImage(projName, cost, dateBeg, dateEnd, dateEndReal, department);

                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(gson.toJson(project), headers);

                restTemplate.exchange(url + "/project/add", HttpMethod.POST, request, String.class);

                System.out.println("Add is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (IncorrectDateException exception) {
            System.err.println("Incorrect date");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (JsonProcessingException exception) {
            System.err.println("Something wrong.. Try again");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department or token expired");
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

                restTemplate.exchange(url + "/project/removeById/" + id, HttpMethod.DELETE, request, String.class);

                System.out.println("Remove is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("Ypu should authorized");
        } catch (IncorrectDateException exception) {
            System.err.println("Incorrect date");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check project or token expired");
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

                System.out.println("Enter the name of department:");
                String department = scan.next();

                responseEntity = restTemplate.getForEntity(url + "/departments/getByName/" + department, String.class);
                department = responseEntity.getBody();

                System.out.println("Enter the name of the project:");
                String projName = scan.next();

                Long cost = 0L;
                System.out.println("Enter cost:");
                if (scan.hasNextLong()) {
                    cost = scan.nextLong();
                } else {
                    throw new NumberFormatException("Value must be integer");
                }

                System.out.println("Enter the date of begin(mm/dd/yyyy):");
                String dateBegStr = scan.next();
                Date dateBeg = DateParse.getDate(dateBegStr);

                System.out.println("Enter the date of end(mm/dd/yyyy):");
                String dateEndStr = scan.next();
                Date dateEnd = DateParse.getDate(dateEndStr);

                System.out.println("Enter the date of end real(mm/dd/yyyy):");
                String dateEndRealStr = scan.next();
                Date dateEndReal = DateParse.getDate(dateEndRealStr);

                if (dateBeg.after(dateEnd) || dateBeg.after(dateEndReal)) {
                    throw new IncorrectDateException("Incorrect date");
                }

                ProjectImage project = new ProjectImage(projName, cost, dateBeg, dateEnd, dateEndReal, department);

                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> request = new HttpEntity<>(gson.toJson(project), headers);

                restTemplate.exchange(url + "/project/updateById/" + id, HttpMethod.PUT, request, String.class);

                System.out.println("Update is successful");
            }
        } catch (AuthenticationException exception) {
            System.err.println("You should authorized");
        } catch (IncorrectDateException exception) {
            System.err.println("Incorrect date");
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (JsonProcessingException exception) {
            System.err.println("Something wrong. Try again");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check department or token expired");
        }
    }

    public static void getEmployees(Scanner scan, String url) {
        try {
            System.out.println("Enter project id:");

            Long id = 0L;
            if (scan.hasNextLong()) {
                id = scan.nextLong();
            } else {
                throw new NumberFormatException("Value must be integer");
            }

            responseEntity = restTemplate.getForEntity(url + "/project/projectId-employeesId/" + id, String.class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
        } catch (NumberFormatException exception) {
            scan.next();
            System.err.println("Value must be integer");
        } catch (Exception exception) {
            System.err.println("Something wrong. Maybe you should check project");
        }
    }
}

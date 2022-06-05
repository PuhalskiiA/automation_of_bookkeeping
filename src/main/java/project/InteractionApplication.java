package project;

import project.utils.commands.*;

import java.util.Locale;
import java.util.Scanner;

public class InteractionApplication {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String url = "http://localhost:8080";

        String command;
        String token = null;

        System.out.println("Hi! I'm your assistant. You can type \"help\" for getting commands" +
                " or \"exit\" for shut down the program.");

        while (true) {
            System.out.println("Enter command:");

            command = scan.next();
            command = command.toLowerCase(Locale.ROOT);

            switch (command) {
                case "exit" -> System.exit(0);

                case "help" -> Helper.getHelp();
                case "help.departments" -> Helper.getHelpDep();
                case "help.employees" -> Helper.getHelpEmp();
                case "help.departments-employees" -> Helper.getHelpDepEmp();
                case "help.projects" -> Helper.getHelpProject();

                case "auth" -> token = Authorization.getAuthorization(scan, url);

                case "d.getbyid" -> DepartmentCommands.getById(scan, url);
                case "d.getbyname" -> DepartmentCommands.getByName(scan, url);
                case "d.getprojectsid" -> DepartmentCommands.getProjectsId(scan, url);
                case "d.add" -> DepartmentCommands.add(scan, url, token);
                case "d.remove" -> DepartmentCommands.remove(scan, url, token);
                case "d.update" -> DepartmentCommands.update(scan, url, token);

                case "e.getbyid" -> EmployeeCommands.getById(scan, url);
                case "e.getbylastname" -> EmployeeCommands.getByLastName(scan, url);
                case "e.getbyfirstname" -> EmployeeCommands.getByFirstName(scan, url);
                case "e.getbypathername" -> EmployeeCommands.getByPatherName(scan, url);
                case "e.getbysalary" -> EmployeeCommands.getBySalary(scan, url);
                case "e.getbyposition" -> EmployeeCommands.getByPosition(scan, url);
                case "e.remove" -> EmployeeCommands.remove(scan, url, token);
                case "e.add" -> EmployeeCommands.add(scan, url, token);
                case "e.update" -> EmployeeCommands.update(scan, url, token);
                case "e.getprojectsid" -> EmployeeCommands.getProjectsId(scan, url);

                case "de.getemployees" -> DepartmentEmployeeCommands.getEmployeesByDep(scan, url);
                case "de.getdepartments" -> DepartmentEmployeeCommands.getDepartmentsByEmp(scan, url);
                case "de.add" -> DepartmentEmployeeCommands.add(scan, url, token);
                case "de.remove" -> DepartmentEmployeeCommands.remove(scan, url, token);

                case "p.getbyid" -> ProjectCommands.getById(scan, url);
                case "p.getbyname" -> ProjectCommands.getByName(scan, url);
                case "p.getbycost" -> ProjectCommands.getByCost(scan, url);
                case "p.getdepartment" -> ProjectCommands.getDepartment(scan, url);
                case "p.add" -> ProjectCommands.add(scan, url, token);
                case "p.remove" -> ProjectCommands.remove(scan, url, token);
                case "p.update" -> ProjectCommands.update(scan, url, token);
                case "p.getemployees" -> ProjectCommands.getEmployees(scan, url);

                default -> System.out.println("Unable to recognize command.");
            }

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException exception) {
                System.out.println("Interrupted exception");
            }
        }
    }
}

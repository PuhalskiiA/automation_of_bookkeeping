package project.utils.commands;

public class Helper {
    public Helper() {
    }

    public static void getHelp() {
        System.out.println("Type \"auth\" for authorization. Without authorization, your rights are limited.\n " +
                "Type \"help.departments\" to receive commands for interaction with departments.\n " +
                "Type \"help.employees\" to receive commands for interaction with departments.\n " +
                "Type \"help.departments-employees\" to receive commands for interaction with departments.\n " +
                "Type \"help.projects\" to receive commands for interaction with departments. ");
    }

    public static void getHelpDep() {
        System.out.println("Type \"d.getById\" to get department by id.\n " +
                "Type \"d.getByName\" to get department by name.\n " +
                "Type \"d.getProjectsId\" to get projects id that belong to the department.\n " +
                "Type \"d.add\". Attention! Authorization required.\n " +
                "Type \"d.remove\". Attention! Authorization required.\n " +
                "Type \"d.update\". Attention! Authorization required. ");
    }

    public static void getHelpEmp() {
        System.out.println("Type \"e.getById\" to get employee by id.\n " +
                "Type \"e.getByLastName\" to get employees by last name.\n " +
                "Type \"e.getByFirstName\" to get employees by first name.\n " +
                "Type \"e.getByPatherName\" to get employees by pather name.\n " +
                "Type \"e.getBySalary\" to get employees by salary.\n " +
                "Type \"e.getByPosition\" to get employees by position.\n " +
                "Type \"e.remove\". Attention! Authorization required.\n " +
                "Type \"e.add\". Attention! Authorization required.\n " +
                "Type \"e.update\". Attention! Authorization required.\n " +
                "Type \"e.getProjectsId\" to get projects id that belong to the employees. ");
    }

    public static void getHelpDepEmp() {
        System.out.println("Type \"de.getEmployees\" to get employees id by department id.\n " +
                "Type \"de.getDepartments\" to get departments id by employee id.\n " +
                "Type \"de.add\". Attention! Authorization required.\n " +
                "Type \"de.remove\". Attention! Authorization required. ");
    }

    public static void getHelpProject() {
        System.out.println("Type \"p.getById\" to get project by id.\n " +
                "Type \"p.getByName\" to get projects by name.\n " +
                "Type \"p.getByCost\" to get projects by cost.\n " +
                "Type \"p.getDepartment\" to get departments id that belong to the project.\n " +
                "Type \"p.add\". Attention! Authorization required.\n " +
                "Type \"p.remove\". Attention! Authorization required.\n " +
                "Type \"p.update\". Attention! Authorization required.\n " +
                "Type \"p.getEmployees\" to get employees id that belong to project. ");
    }
}

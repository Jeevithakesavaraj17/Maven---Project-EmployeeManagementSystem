package com.ideas2it.ems.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.service.ProjectService;
import com.ideas2it.ems.service.ProjectServiceImpl;
import com.ideas2it.ems.util.Validator;

/**
 * <p>
 * This class is used for displaying menu for project Management and
 * have some methods for getting project details, add and display employees.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public class ProjectController {
    Scanner scanner = new Scanner(System.in);
    private static Logger logger = LogManager.getLogger();
    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * <p>
     * Display menu which has functions to get project details and display project details.
     * </p>
     */
    public void displayMenu() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("-------------------------------------------"
                               + "\n1.Add Project\n2.Display Projects"
                               + "\n3.Display Employees by Project"
                               + "\n4.Update Project name"
                               + "\n5.Delete Project\n6.Exit");
            int userChoice = getValidNumber();
            switch (userChoice) {
            case 1:
                addProject();
                break;
            case 2:
                displayProjects();
                break;
            case 3:
                displayEmployeesByProjectId();
                break;
            case 4:
                updateProjectName();
                break;
            case 5:
                deleteProject();
                break;
            case 6:
                isExit = true;
                System.out.println("Exiting..");
                break;
            default:
                System.out.println("Invalid choice.");
            }
        }    
    }

    /**
     * <p>
     * Get ProjectId and Project name and add that project details
     * </p>
     */
    public void addProject() {
        scanner.nextLine();
        System.out.print("Enter Project Name : ");
        String projectName = scanner.nextLine();
        try {
            Project project = projectService.addProject(projectName);
            System.out.println(project);
            logger.info("Project added successfully : " + projectName);
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * Display list of projects
     * </p>
     */
    public void displayProjects() {
        try {
            System.out.println("List of Projects");
            List<Project> projects = projectService.getProjects();
            String format = "| %-4s | %15s \n";
            System.out.format(format, "Id", "Name");
            for(Project project : projects) {
                System.out.format(format, project.getProjectId(), 
                                  project.getProjectName());
            }
            logger.info("Displayed list of departments.");
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * Get project id and display employees by that project id.
     * </p>
     */
    public void displayEmployeesByProjectId() {
        System.out.println("Display employees by Project Id");
        displayProjects();
        System.out.print("Enter ProjectId:");
        int projectId = scanner.nextInt();
        try {
            Project project = projectService.getProject(projectId);
            List<Employee> employees = new ArrayList<>(project.getEmployees());
            String format = "| %-4s | %15s | %-4s | %-12s | %-12s | %-20s | %-10s \n";
            System.out.format(format, "Id", "Name", "Age", "DepartmentId", "PhoneNumber", "MailId", "Experience");
            for (Employee employee : employees) {
                System.out.format(format, employee.getEmployeeId(),
                                      employee.getEmployeeName(),
                                      employee.getAge(),
                                      employee.getDepartment().getDepartmentId(),
                                      employee.getPhoneNumber(),
                                      employee.getMailId(),
                                      employee.getExperience());
            }
            logger.info("Displayed List of employees in the project " + projectId);
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /** 
     * <p>
     * Update project name by getting project Id and new name for the project
     * </p>
     */
    public void updateProjectName() {
        System.out.print("Enter Project Id : ");
        int projectId = scanner.nextInt();
        try {
            Project project = projectService.getProject(projectId);
            if (null == project) {
                logger.warn("No project found.");
            } else {
                scanner.nextLine();
                System.out.print("Enter new name for the project : ");
                String projectName = scanner.nextLine();
                project.setProjectName(projectName);
                Project projectObject = projectService.updateProjectName(project);
                System.out.println(projectObject);
                logger.info("Project name updated successfully for " + projectId);
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    } 

    /**
     * <p>
     * Delete the project by project Id
     * </p>
     */
    public void deleteProject() {
        try {
            displayProjects();
            System.out.print("Enter project Id : ");
            int projectId = scanner.nextInt();
            Project project = projectService.getProject(projectId);
            if (null == project) {
                logger.warn("No project Found.");
            } else {
                List<Employee> employees = new ArrayList<>(project.getEmployees());
                if (employees.isEmpty()) {
                    if (projectService.isProjectDeleted(project)) {
                        logger.info(project.getProjectName() + " project deleted successfully.");
                    }
                } else {
                    logger.warn("Enable to delete the project " + projectId + " because it has employees.");
                }
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Get number and validate
     * @return number   integer to be validation
     */
    public int getValidNumber() {
        boolean isValid = true;
        while (isValid) {
            try {
                System.out.print("Enter choice : ");
                int validNumber = scanner.nextInt();
                isValid = false;
                return validNumber;
            } catch (InputMismatchException e) {
                System.out.print("Invalid choice.");
                scanner.next();
            }   
        }
        return 0;
    }
}
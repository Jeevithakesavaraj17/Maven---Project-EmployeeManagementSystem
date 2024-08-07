package com.ideas2it.ems.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.service.DepartmentService;
import com.ideas2it.ems.service.DepartmentServiceImpl;

/**
 * <p>
 * This class is for display menu and have methods for get department details and
 * displaying the details of department and list of departments.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public class DepartmentController {
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger();
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    /**
     * <p>
     * Display menu which has functions to get department details and display department details.
     * </p>
     */
    public void displayMenu() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("--------------------------------------------");
            System.out.println("1.Add Department\n2.Display Departments"
                               + "\n3.Display Employees by Department\n4.Update Department name\n"
                               + "5.Delete Department\n6.Exit");
            int userChoice = getValidNumber();
            switch (userChoice) {
            case 1:
                addDepartment();
                break;
            case 2:
                displayDepartments();
                break;
            case 3:
                displayEmployeesByDepartmentId();
                break;
            case 4:
                updateDepartmentName();
                break;
            case 5:
                deleteDepartment();
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
     * Get DepartmentId and Department name and add that department details
     * </p>
     */
    public void addDepartment() {
        scanner.nextLine();
        System.out.print("Enter Department Name : ");
        String departmentName = scanner.nextLine();
        try {
            Department department = departmentService.addDepartment(departmentName); 
            System.out.println(department);
            logger.info(departmentName + " department added successfully.");
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * Display list of departments
     * </p>
     */
    public void displayDepartments() {
        try {
            List<Department> departments = departmentService.getDepartments();
            String format = "| %-5s | %-15s \n";
            System.out.format(format, "Id", "Name");
            for(Department department : departments) {
                System.out.format(format, department.getDepartmentId(), 
                                          department.getDepartmentName());
            }
            logger.info("Departments displayed");
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * Display list of employees for the particular department Id.
     * </p>
     */
    public void displayEmployeesByDepartmentId() {
        System.out.println("Employees");
        displayDepartments();
        System.out.print("Enter Department Id : ");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartment(departmentId);
            if (null == department) {
                logger.warn("No department Found.");
            } else {
                List<Employee> employees = new ArrayList<>(department.getEmployees()); 
                if (employees.isEmpty()) {
                    logger.warn("No employees Found");
                } else {
                    String format = "| %-4s | %15s | %-4s | %-12s | %-12s | %-20s | %-10s | %-20s \n";
                    System.out.format(format, "Id", "Name", "Age", "DepartmentId",
                                             "PhoneNumber", "MailId", "Experience", "Projects");
                    for (Employee employee : employees) {
                        if (!employee.getIsDeleted()) {
                            System.out.format(format, employee.getEmployeeId(),
                                                   employee.getEmployeeName(),
                                                   employee.getAge(),
                                                   employee.getDepartment().getDepartmentId(),
                                                   employee.getPhoneNumber(),
                                                   employee.getMailId(),
                                                   employee.getExperience(),
                                                   employee.getProjectDetails());
                        }
                    }
                    logger.info("Displayed list of employees in the department " + department.getDepartmentName());
                }
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /** 
     * <p>
     * Update department name by getting department Id and new name for the department
     * </p>
     */
    public void updateDepartmentName() {
        System.out.print("Enter DepartmentId : ");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartment(departmentId);
            if (null == department) {
                logger.warn("No department found.");
            } else {
                scanner.nextLine();
                System.out.print("Enter new name for the department : ");
                String departmentName = scanner.nextLine();
                department.setDepartmentName(departmentName);
                Department departmentObject = departmentService.updateDepartmentName(department);
                System.out.println(departmentObject);
                logger.info("Department name updated successfully for " + departmentId);
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }  

    /**
     * <p>
     * Delete the department by department Id
     * </p>
     */
    public void deleteDepartment() {
        try {
            displayDepartments();
            System.out.print("Enter department Id : ");
            int departmentId = scanner.nextInt();
            Department department = departmentService.getDepartment(departmentId);
            if (null == department) {
                logger.warn("No department Found.");
            } else {
                List<Employee> employees = new ArrayList<>(department.getEmployees());
                if (employees.isEmpty()) {
                    if (departmentService.isDepartmentDeleted(department)) {
                        logger.info(department.getDepartmentName() + " department deleted successfully.");
                    }
                } else {
                    logger.warn("Enable to delete " + department.getDepartmentName() + " the department because it has employees.");
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

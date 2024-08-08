package com.ideas2it.ems.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.SalaryAccount;
import com.ideas2it.ems.service.DepartmentService;
import com.ideas2it.ems.service.DepartmentServiceImpl;
import com.ideas2it.ems.service.EmployeeService;
import com.ideas2it.ems.service.EmployeeServiceImpl;
import com.ideas2it.ems.service.ProjectService;
import com.ideas2it.ems.service.ProjectServiceImpl;
import com.ideas2it.ems.service.SalaryAccountService;
import com.ideas2it.ems.service.SalaryAccountServiceImpl;
import com.ideas2it.ems.util.Validator;

/**
 * <p>
 * This class is used for displaying menu for employee Management and
 * have some methods for getting employee details, add and display employee, employees.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public class EmployeeController {
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final ProjectService projectService = new ProjectServiceImpl();
    private final SalaryAccountService salaryAccountService = new SalaryAccountServiceImpl();
    
    /**
     * <p>
     * Display menu which has functions to get employee input and display employee details.
     * </p>
     */
    public void displayMenu() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("--------------------------------------------");
            System.out.println("Employee Services");
            System.out.println("1.Add Employee\n2.Display Employees"
                               + "\n3.update Employee\n4.Add Project"
                               + "\n5.Display Projects of Employee"
                               + "\n6.Delete Employee"
                               + "\n7.Exit.");
            int userChoice = getValidNumber();
            switch (userChoice) {
            case 1:
                addEmployeeDetails();
                break;
            case 2:
                displayEmployees();
                break;
            case 3:
                updateEmployeeDetails();
                break;
            case 4:
                addProjectToEmployee();
                break;
            case 5:
                displayProjectsOfEmployee();
                break;
            case 6:
                deleteEmployee();
                break;
            case 7:
                isExit = true;
                System.out.println("Exiting...");
                break;
            default:
                logger.error("Invalid Choice.");
            }
        }
    }

    /**
     * <p>
     * Get employee details and add employee data to the list.
     * </p>
     */
    public void addEmployeeDetails() {
        System.out.println("Add Employee:");
        scanner.nextLine();
        String employeeName = getEmployeeName();
        LocalDate birthDate = getDateOfBirth();
        String phoneNumber = getPhoneNumber();
        scanner.nextLine();
        String mailId = getMailId();
        int experience = getExperience();
        try {
            List<Department> departments = departmentService.getDepartments();
            if (departments.isEmpty()) {
                logger.warn("No Departments found. you have to add the department first.");
            } else{
                String format = "| %-5s | %-15s \n";
                System.out.format(format, "Id", "Name");
                for(Department department : departments) {
                    System.out.format(format, department.getDepartmentId(), 
                                              department.getDepartmentName());
                }
                System.out.print("Enter Department Id : ");
                int departmentId = scanner.nextInt();
                while (!departmentService.isDepartmentPresent(departmentId)) {
                    System.out.print("No Department found. Please Enter valid departmentId : ");
                    departmentId = scanner.nextInt();
                }
                System.out.print("Enter Account Number : ");
                long accountNumber = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Enter IFSC Code : ");
                String ifscCode = scanner.nextLine();
                Employee employee = employeeService.addEmployee(employeeName, birthDate,
                                                          phoneNumber, mailId, experience, 
                                                         departmentId, accountNumber, ifscCode);
                System.out.println(employee);
                logger.info("{} added successfully.", employee.getEmployeeName());
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        } 
    }

    /**
     * <p>
     * Display list of employees
     * </p>
     */
    public void displayEmployees() {
        System.out.println("Employees");
        try {
            if (employeeService.isEmployeeListEmpty()) {
                logger.warn("No Employee Records...");
            } else {
                List<Employee> employees = employeeService.getEmployees();
                String format = "| %-4s | %15s | %-4s | %-15s | %-12s | %-12s | %-20s | %-10s | %-20s \n";
                System.out.format(format, "Id", "Name", "Age", "DepartmentName",
                                          "AccountNumber", "PhoneNumber", "MailId",
                                          "Experience", "Projects");
                for (Employee employee : employees) {
                    System.out.format(format, employee.getEmployeeId(),
                                      employee.getEmployeeName(),
                                      employee.getAge(),
                                      employee.getDepartment().getDepartmentName(),
                                      employee.getSalaryAccount().getAccountNumber(),
                                      employee.getPhoneNumber(),
                                      employee.getMailId(),
                                      employee.getExperience(),
                                      employee.getProjectDetails());
                }
                logger.info("All employees are displayed.");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * Get Id of the employee and update their details.
     * </p>
     */
    public void updateEmployeeDetails() {
        int employeeId = getEmployeeId();
        try {
            if (!employeeService.isEmployeePresent(employeeId)) {
                logger.warn("No Employee Found.");
            } else {
                Employee employee = employeeService.getEmployeeById(employeeId);
                System.out.println(employee);
                System.out.println("----------------------------------------");
                System.out.println("1.Update Name\n2.Update Date of Birth"
                                   + "\n3.Update phone number\n4.Update mailId"
                                   + "\n5.Update Experience"
                                   + "\n6.Update Department"
                                   + "\n7.Update SalaryAccount");
                int choice = getValidNumber();
                scanner.nextLine();
                switch (choice) {
                case 1:
                    String employeeName = getEmployeeName();
                    employee.setEmployeeName(employeeName);
                    break;
                case 2:
                    LocalDate dateOfBirth = getDateOfBirth();
                    employee.setDateOfBirth(dateOfBirth);
                    break;
                case 3:
                    String phoneNumber = getPhoneNumber();
                    employee.setPhoneNumber(phoneNumber);
                    break;
                case 4:
                    String mailId = getMailId();
                    employee.setMailId(mailId);
                    break;
                case 5:
                    System.out.print("Enter Experience : ");
                    int experience = scanner.nextInt();
                    employee.setExperience(experience);
                    break;
                case 6:
                    List<Department> departments = departmentService
                                                       .getDepartments();
                    for (Department department : departments) {
                        System.out.println(department.getDepartmentId()
                                           + " "
                                           + department.getDepartmentName());
                    }
                    System.out.print("Enter Department Id : ");
                    int departmentId = scanner.nextInt();
                    while (!departmentService.isDepartmentPresent(departmentId)) {
                        logger.error("Invalid departmentId. Please enter correct Id : ");
                        departmentId = scanner.nextInt();
                    }
                    Department department = departmentService.getDepartment(departmentId);
                    employee.setDepartment(department);
                    break;

                case 7:
                    System.out.print("Enter Account Number : ");
                    long accountNumber = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter IFSC Code : ");
                    String ifscCode = scanner.nextLine();
                    SalaryAccount salaryAccount = employee.getSalaryAccount();
                    salaryAccount.setAccountNumber(accountNumber);
                    salaryAccount.setIfscCode(ifscCode);
                    salaryAccountService.updateSalaryAccount(salaryAccount); 
                    employee.setSalaryAccount(salaryAccount);
                    break;
                default:
                    System.out.println("Invalid choice.");
                }
                Employee updatedEmployee = employeeService.updateEmployeeDetails(employee);
                System.out.println(updatedEmployee);
                logger.info("Details updated successfully for {}", employee.getEmployeeName());
            }   
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        } 
    }
    
    /**
     * <p>
     * Get Id of the employee, project Id and
     * add project to the employee according to the employeeId.
     * </p>
     */
    public void addProjectToEmployee() {
        int employeeId = getEmployeeId();
        try {
            if (!employeeService.isEmployeePresent(employeeId)) {
                logger.info("No Employee Found.");
            } else {
                Employee employee = employeeService.getEmployeeById(employeeId); 
                List<Project> projects = projectService.getProjects();
                if (projects.isEmpty()) {
                    logger.warn("No projects found.");
                } else {
                    String format = "| %-4s | %15s \n";
                    System.out.format(format, "Id", "Name");
                    for(Project project : projects) {
                        System.out.format(format, project.getProjectId(), 
                                            project.getProjectName());
                    }
                    System.out.print("Enter ProjectId : ");
                    int projectId = scanner.nextInt();
                    Project project = projectService.getProject(projectId);
                    if (null == project) {
                        logger.warn("No project found.");
                    } else {
                        projectService.addProjectToEmployee(project, employee);
                        logger.info("{}project added successfully for {}", project.getProjectName(), employee.getEmployeeName());
                    }
                }
            }  
        } catch (EmployeeException e) {
            logger.error("Employee is already assigned to the project{}", e.getMessage());
        }
    }

    /**
     * <p>
     * Get employeeId and display all projects in that employeeId.
     * </p>
     */
    public void displayProjectsOfEmployee() {
        System.out.println("Display Projects by Employee");
        int employeeId = getEmployeeId();
        try {
            if (!employeeService.isEmployeePresent(employeeId)) {
                logger.warn("No employeeFound.");
            } else {
                Employee employee = employeeService.getEmployeeById(employeeId);
                List<Project> projects = new ArrayList<>(employee.getProjects());
                if (projects.isEmpty()) {
                    logger.info("No Projects Found.");
                } else {
                    String format = "| %-4s | %15s \n";
                    System.out.format(format, "Id", "Name");
                    for(Project project : projects) {
                        System.out.format(format, project.getProjectId(), 
                                            project.getProjectName());
                    }
                    logger.info("List of projects of employee{}", employee.getEmployeeName());
                }
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }      
    }

    /**
     * <p>
     * Get employee Id and delete that Employee.
     * </p>
     */
    public void deleteEmployee() {
        int employeeId = getEmployeeId();
        try {
            if (employeeService.isEmployeePresent(employeeId)) {
                if (employeeService.isEmployeeDeleted(employeeId)) {
                    logger.info("Employee {} Deleted.", employeeId);
                }
            } else {
                logger.warn("No employee found.");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }
    
    /**  
     * Get employeeId and validate that id.
     * @return employeeId    Id of the employee
     */
    public int getEmployeeId() {
        int employeeId = 0;
        try {
            System.out.print("Enter Employee ID : ");
            employeeId = scanner.nextInt();
        } catch (Exception e) {
            logger.error("Invalid Type.");
            getEmployeeId();
        }
        return employeeId;
    }

    /**  
     * Get employee name and validate that name.
     * @return employeeName    Name of the employee
     */
    public String getEmployeeName() {
        System.out.print("Enter Name : ");
        String employeeName = scanner.nextLine();
        while (!Validator.isValidName(employeeName)) {
            logger.error("Invalid format.");
            System.out.print("Enter Name :");
            employeeName = scanner.nextLine();
        }
        return employeeName;
    }

    /**
     * Get employee date of birth and validate.
     * @return daeOfBirth     date of birth of the employee
     */
    public LocalDate getDateOfBirth() {
        LocalDate dateOfBirth = null;
        String date;
        try {
            System.out.print("Enter Date Of Birth (YYYY-MM-DD) : ");
            date = scanner.nextLine();
            while (!Validator.isValidDate(date)) {
                System.out.print("Invalid date format.Please enter correct"
                                  + "format(YYYY-MM-DD) : ");
                date = scanner.nextLine();
            }
            dateOfBirth = LocalDate.parse(date);
        } catch (InputMismatchException e) {
            logger.error("Invalid date format.");
            getDateOfBirth();
        }
        return dateOfBirth;
    }

    /**
     * Get number and validate
     * @return number   integer to be validation
     */
    public int getValidNumber() {
        int validNumber = 0;
        try {
            System.out.print("Enter choice : ");
            validNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            logger.error("Invalid choice.");
            getValidNumber();
        }
        return validNumber;
    }

    /**
     * Get employee phone number and validate.
     * @return phoneNumber     employee's phone number
     */
    public String getPhoneNumber() {
        String phoneNumber = "";
        try {
            System.out.print("Enter PhoneNumber : ");
            phoneNumber = scanner.nextLine();
            while (!Validator.isValidPhoneNumber(phoneNumber)) {
                System.out.print("Invalid format."
                                 + "Please Enter PhoneNumber :");
                phoneNumber = scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            logger.error("Invalid format.Please enter correct mobile number format.");
            getPhoneNumber();
        }
        return phoneNumber;
    }

    /**
     * Get employee mail Id and validate
     * @return mailId    employee's mailId
     */
    public String getMailId() {
        System.out.print("Enter MailId : ");
        String mailId = scanner.nextLine();
        while (!Validator.isValidMailId(mailId)) {  
            logger.error("Invalid format."
                             + "Please enter correct format :");
            mailId = scanner.nextLine();
        }
        return mailId;
    } 

    /**
     * Get employee experience and validate
     * @return experience     employee's experience
     */ 
    public int getExperience() {
        int experience = 0;
        try {
            System.out.print("Enter Experience : ");
            experience = scanner.nextInt();
            while (!Validator.isValidExperience(experience)) {
                System.out.print("Invalid format."
                                 + "Please Enter correct Experience :");
                experience = scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            logger.error("Invalid format.");
            getExperience();
        }
        return experience;
    }
}
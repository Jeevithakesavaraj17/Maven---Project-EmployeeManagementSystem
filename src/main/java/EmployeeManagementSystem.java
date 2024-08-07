import java.util.Scanner;

import com.ideas2it.ems.controller.DepartmentController;
import com.ideas2it.ems.controller.EmployeeController;
import com.ideas2it.ems.controller.ProjectController;

/**
 * <p>
 * This class contains main application for controlling employee, department and project details
 * </p>
 *
 * @author Jeevithakesavaraj
 */ 
public class EmployeeManagementSystem {

    public static void main(String[] args) {
        EmployeeManagementSystem employeemanagement = new EmployeeManagementSystem();
        employeemanagement.displayMenu();
    }

    /**
     * <p>
     * This method displays menu for the employee management system and consists of 
     * employee, department and project controller
     * </p>
     */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        DepartmentController departmentController = new DepartmentController();
        EmployeeController employeeController = new EmployeeController();
        ProjectController projectController = new ProjectController();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("----------------------------------------------"
                               + "\n1. Manage Department\n2. Manage Employee"
                               + "\n3. Manage Project\n4.Exit");
            System.out.print("Enter Choice : ");
            int userChoice = scanner.nextInt();
            switch (userChoice) {
            case 1:
                departmentController.displayMenu();
                break;
            case 2:
                employeeController.displayMenu();
                break;
            case 3:
                projectController.displayMenu();
                break;
            case 4:
                isExit = true;
                System.out.println("Exiting..");
                break;
            default:
                System.out.println("Invalid Choice.");
            }
        }
    }
}
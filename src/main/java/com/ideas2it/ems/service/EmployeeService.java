package com.ideas2it.ems.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * This interface consists of method declaration for add, get, update, delete the employee details.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public interface EmployeeService {

    /**
     * <p>
     * Add employee details
     * </p>
     *
     * @param employeeName    Name of the employee
     * @param dateOfBirth     employee's date of birth
     * @param phoneNumber     employee's Phone number
     * @param mailId          employee's MailId
     * @param experience      experience of the employee
     * @param departmentId    ID of the department
     * @param accountNumber    salary account number
     * @param ifscCode         IFSC code
     * @return Employee        If employee is added, return employee object
     * @throws EmployeeException  If exception occurs, while inserting the employee data.
     */
    Employee addEmployee (String employeeName,
                                 LocalDate dateOfBirth, String phoneNumber,
                                 String mailId, int experience, int departmentId,
                                 long accountNumber, String ifscCode) throws EmployeeException;

    /**
     * <p>
     * Get list of employees
     * </p>
     *
     * @return List<Employee>    list of employees
     * @throws EmployeeException   If exception occurs, while getting the list of employees.
     */
    List<Employee> getEmployees() throws EmployeeException;

    /**
     * <p>
     * Get employee details by employeeId
     * </p>
     *
     * @param employeeId     ID of the employee
     * @return Employee      If employee present, return employee details
     *                      else return null.
     * @throws EmployeeException   If exception occurs, while getting the employee details.
     */
    Employee getEmployeeById(int employeeId) throws EmployeeException;

    /**
     * <p>
     * Checks if employee List is empty or not
     * </p>
     *
     * @return boolean             If empty, returns true or else false 
     * @throws EmployeeException   If exception occurs, while checking for the employee list is empty or not.
     */
    boolean isEmployeeListEmpty() throws EmployeeException;
   
    /**
     * <p>
     * Check if employee is present by employeeId
     * </p>
     *
     * @param employeeId     ID of the employee
     * @return Employee      If employee present, return employee details
     *                      else return null.
     * @throws EmployeeException   If exception occurs, while checking for the employee.
     */
    boolean isEmployeePresent(int employeeId) throws EmployeeException;

    /**
     * <p>
     * Update employee name by their employee id
     * </p>
     *
     * @param employee      updated employee details
     * @return Employee     If employee detail is updated, return employee
     * @throws EmployeeException  If exception occurs, while updating the details of employee by id.
     */
    Employee updateEmployeeDetails(Employee employee) throws EmployeeException;

    /**
     * <p>
     * Delete employee by their employeeId
     * </p>
     *
     * @param employeeId   ID of the employee
     * @return boolean     If employee deleted, returns true or else false
     * @throws EmployeeException  If exception occurs, while deleting the employee.
     */
    boolean isEmployeeDeleted(int employeeId) throws EmployeeException;
    
}
package com.ideas2it.ems.dao;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 * This interface have method declaration for employee CRUD operations
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public interface EmployeeDao {

    /**
     * <p>
     * Add employee details to the employee table
     * </p>
     *
     * @param employee      Employee object
     * @return Employee     If employee is added, return employee object
     * @throws EmployeeException   If exception occurs, while inserting the employee data
     */
    public Employee insertEmployee(Employee employee) throws EmployeeException;

    /**
     * <p>
     * Get Employee list
     * </p>
     *
     * @return List<Employee>   list of employees
     * @throws EmployeeException   If exception occurs, while getting list of employees
     */
    public List<Employee> retrieveEmployees() throws EmployeeException;

    /**
     * <p>
     * Get employee by their employeeId
     * </p>
     *
     * @param employeeId     Id of the employee
     * @return Employee    If Employee is present, return employee or else return null
     * @throws EmployeeException   If exception occurs, while getting employee by employeeId
     */
    public Employee retrieveEmployeeById(int id) throws EmployeeException;

    /**
     * <p>
     * Update employeedetails
     * </p>
     *
     * @param employee   details of the employee
     * @return Employee   If employee detail is updated, return employee
     * @throws EmployeeException    If exception occurs, while updating the employee details
     */
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeException;

    /**
     * <p>
     * Delete employee by employeeId
     * </p>
     *
     * @param employeeId    Id of the employee
     * @return boolean      If employee deleted, returns true or else return false.
     * @throws EmployeeException   If exception occurs, while checking for the employee deleted or not
     */
    public boolean isEmployeeDeleted(int employeeId) throws EmployeeException;

}

package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * This interface contains method declaration for add, get, update, delete the 
 * department details and get employees in the particular department.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public interface DepartmentService {

    /**
     * <p>
     * Add Department details
     * </p>
     *
     * @param DepartmentName   Name of the department
     * @return Department         If department added, return department
     * @throws EmployeeException  If exception occurs, while inserting the department details.
     */
    public Department addDepartment(String departmentName) throws EmployeeException;

    /**
     * <p>
     * Get list of Departments
     * </p>
     *
     * @return List<Department>    list of Departments
     * @throws EmployeeException   If exception occurs, while getting the list of departments.
     */
    public List<Department> getDepartments() throws EmployeeException;

    /**
     * <p>
     * Get department by departmentId
     * </p>
     *
     * @param departmentId      Id of the department
     * @return department      Id department is present, return true or else return false.
     * @throws EmployeeException   If exception occurs, while getting the department details.
     */
    public Department getDepartment(int departmentId) throws EmployeeException;

    /**
     * <p>
     * Checks if the department is present or not by department Id
     * </p>
     *
     * @param departmentId   Id of the department
     * @return boolean       If department is present, it returns true, or else return false
     * @throws EmployeeException   If exception occurs, while checking for the department
     */
    public boolean isDepartmentPresent(int departmentId) throws EmployeeException;

    /**
     * <p>
     * Get employees for the particular department using departmentId
     * </p>
     *
     *@param departmentId       Id of the department
     *@return List<Employee>    list of employees in that department
     *@throws EmployeeException  If exception occurs, while getting the list of employees.
     */
    public List<Employee> getEmployeesByDepartment(int departmentId) throws EmployeeException;

    /**
     * <p>
     * Update department name in the database
     * </p>
     *
     * @param Department   DepartmentName
     * @return Department  If department is updated, returns department object
     * @throws EmployeeException   If exception occurs, while updating the department
     */
    public Department updateDepartmentName(Department department) throws EmployeeException;

    /**
     * <p>
     * Delete department 
     * </p>
     *
     * @param Department   department id and department name
     * @return boolean     If department is deleted, returns true or else return false
     * @throws EmployeeException   If exception occurs, while deleting the department.
     */
    public boolean isDepartmentDeleted(Department department) throws EmployeeException;

}
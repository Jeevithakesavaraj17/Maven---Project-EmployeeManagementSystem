package com.ideas2it.ems.dao;

import java.util.List; 

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Department;

/**
 * <p>
 * This interface have method declaration for CRUD operation in department repository.
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public interface DepartmentDao {

    /**
     * <p>
     * Add the department to department repository.
     * </p>
     *
     * @param department        department details
     * @return Department       If department added, return department
     * @throws EmployeeException  If exception occurs while adding the department to the database.
     */
    Department insertDepartment(Department department) throws EmployeeException;

    /**
     * <p>
     * Get the list of departments
     * </p>
     *
     * @return List<Department>  list of departments
     * @throws EmployeeException   If exception occurs, while getting the list of departments
     */ 
    List<Department> retrieveDepartments() throws EmployeeException;

    /**
     * <p>
     * Get the department by the departmentId
     * </p>
     *
     * @param departmentId     ID of the department
     * @return Department      details of the department
     * @throws EmployeeException   If exception occurs, while getting the department object.
     */
    Department retrieveDepartment(int departmentId) throws EmployeeException;

    /**
     * <p>
     * Get the list of employees by department id
     * </p>
     *
     * @param departmentId   ID of the department
     * @return List<Employee>   list of the employees
     * @throws EmployeeException   If exception occurs, while getting the list of employees in the particular department.
     */
    List<Employee> retrieveEmployeesByDepartment(int departmentId) throws EmployeeException;

    /**
     * <p>
     * Update department name in the database
     * </p>
     *
     * @param department   DepartmentName
     * @return Department   If department added, returns department or else throw exception
     * @throws EmployeeException   If exception occurs, while updating the department
     */
    Department updateDepartmentName(Department department) throws EmployeeException;

    /**
     * <p>
     * Delete department
     * </p>
     *
     * @param department   department object(department details)
     * @return boolean     If department deleted, return true or else return false.
     * @throws EmployeeException   If exception occurs, while deleting tha department.
     */
    boolean isDepartmentDeleted(Department department) throws EmployeeException;

}
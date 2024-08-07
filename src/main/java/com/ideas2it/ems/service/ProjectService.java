package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 * This interface consists of method declaration for add, get the project details and employees in the particular project.
 * </p>
 *
 * @author  Jeevithakesavaraj
 */
public interface ProjectService {
 
    /**
     * <p>
     * Add project to the list
     * </p>
     *
     * @param projectName  Name of the project
     * @return Project     If project added, return project object
     * @throws EmployeeException   If exception occurs, while inserting the project details.
     */
    Project addProject(String projectName) throws EmployeeException;

    /**
     * <p>
     * Get list of projects
     * </p>
     *
     * @return List<Project>    list of projects
     * @throws EmployeeException If exception occurs, while getting the list of projects.
     */
    List<Project> getProjects() throws EmployeeException;

    /**
     * <p>
     * Get project from the list of projects using project Id
     * </p>
     *
     * @param projectId   ID of the project
     * @return Project    If project present, return project object or else return null.
     * @throws EmployeeException   If exception occurs, while getting the project
     */
    Project getProject(int projectId) throws EmployeeException;

     /**
      * <p>
      * Update project name in the database
      * </p>
      * @param project   project
      * @return Project  If project is updated, returns project object
      * @throws EmployeeException   If exception occurs, while updating the project
      */
    Project updateProjectName(Project project) throws EmployeeException;

    /**
     * <p>
     * Add project details to the employee
     * </p>
     *
     * @param project    Project details that is added to the employee
     * @param employee   Employee to whom we add the project
     * @throws EmployeeException   If exception occurs, while adding the project to the employee
     */
    void addProjectToEmployee(Project project, Employee employee) throws EmployeeException;

    /**
     * <p>
     * Get list of employees by project Id
     * </p>
     *
     * @param projectId          ID of the project
     * @return List<Employee>    If employees present, return employees or else return null.
     * @throws EmployeeException   If exception occurs, while getting the employees for the projectId
     */
    List<Employee> getEmployeesByProject(int projectId) throws EmployeeException;

    /**
     * <p>
     * Delete project in project table
     * </p>
     *
     * @param project     project ID and project name
     * @return boolean     If project is deleted, returns true or else return false
     * @throws EmployeeException   If exception occurs, while deleting the project.
     */
    boolean isProjectDeleted(Project project) throws EmployeeException;

}
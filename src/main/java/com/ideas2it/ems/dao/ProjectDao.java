package com.ideas2it.ems.dao;

import java.util.List; 

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 * This interface consists method declaration of insert and retrieve project details of the employee for project dao
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public interface ProjectDao {

    /**
     * <p>
     * Add project details to the project table 
     * </p>
     *
     * @param project   project details
     * @return project  If project added, it returns project object
     * @throws EmployeeException   If exception occurs, while adding the project details
     */
    Project insertProject(Project project) throws EmployeeException;

    /**
     * <p>
     * Get list of projects
     * </p>
     *
     * @return List<Project>   list of projects
     * @throws EmployeeException    If exception occurs, while getting the list of projects
     */
    List<Project> retrieveProjects() throws EmployeeException;

    /**
     * <p>
     * Get project from the database
     * </p>
     *
     * @param projectId   ID of the project
     * @return Project    If project present, return project object or else return null
     * @throws EmployeeException   If exception occurs, while getting the project by projectId
     */
    Project retrieveProject(int projectId) throws EmployeeException;
    
    /**
     * <p>
     * Update project name in the database 
     * </p>
     *
     * @param project   project details
     * @return Project  If project is updated, returns project object
     * @throws EmployeeException   If exception occurs, while updating the project
     */
    Project updateProjectName(Project project) throws EmployeeException;

    /**
     * <p>
     * Add project to the employee
     * </p>
     *
     * @param employee           employee who we have to add project
     * @param project             project that is added to the employee
     * @throws EmployeeException  If exception occurs, while adding the project to the employee
     */
    void addProjectToEmployee(Project project, Employee employee) throws EmployeeException;

    /**
     * <p>
     * Get list of employees for the particular project using projectId
     * </p>
     *
     * @param projectId          ID of the project
     * @return List<Employee>    If employees present, return employee list or else return null
     * @throws EmployeeException If exception occurs, while getting the employees for the project by projectId
     */
    List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException;

    /**
     * <p>
     * Delete project in project table
     * </p>
     *
     * @param project            project id and project name
     * @return boolean           If project is deleted, returns true or else return false
     * @throws EmployeeException   If exception occurs, while deleting the project.
     */
    boolean isProjectDeleted(Project project) throws EmployeeException;

}
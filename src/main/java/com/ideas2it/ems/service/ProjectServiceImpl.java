package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dao.ProjectDaoImpl;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 * This class implements project service and manages the project and get the project details
 * </p>
 *
 * @author  Jeevithakesavaraj
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao = new ProjectDaoImpl();
    
    @Override
    public Project addProject(String projectName) throws EmployeeException {
        Project project = new Project();
        project.setProjectName(projectName);
        return projectDao.insertProject(project);
    }

    @Override
    public List<Project> getProjects() throws EmployeeException {
        return projectDao.retrieveProjects();
    }

    @Override
    public Project getProject(int projectId) throws EmployeeException {
        return projectDao.retrieveProject(projectId);
    }

    @Override 
    public Project updateProjectName(Project project) throws EmployeeException {
        return projectDao.updateProjectName(project);
    }

    @Override
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException {
        projectDao.addProjectToEmployee(project, employee);
    }

    @Override
    public List<Employee> getEmployeesByProject(int projectId) throws EmployeeException {
        return projectDao.retrieveEmployeesByProject(projectId);
    }

    @Override
    public boolean isProjectDeleted(Project project) throws EmployeeException {
        return projectDao.isProjectDeleted(project);
    }
}
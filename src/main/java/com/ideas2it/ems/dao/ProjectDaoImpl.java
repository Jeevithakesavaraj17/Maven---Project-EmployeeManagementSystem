package com.ideas2it.ems.dao;
	
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.connectionmanager.HibernateConnection;
import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * This class is for insert, retrieve project details of the employee
 * and get list of the projects and implements ProjectDao interface.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public class ProjectDaoImpl implements ProjectDao {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Project insertProject(Project project) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to add the project : " + project.getProjectName());
            throw new EmployeeException("Unable to add the project : " + project.getProjectName(), e);
        } finally {
            session.close();
        }
        return project;
    }

    @Override
    public List<Project> retrieveProjects() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Project> projects = null;
        try {
            transaction = session.beginTransaction();
            Query<Project> query = session.createQuery("FROM Project WHERE isDeleted = :isDeleted", Project.class).setParameter("isDeleted", false);
            projects = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get the list of projects.");
            throw new EmployeeException("Unable to get the list of projects.", e);
        } finally {
            session.close(); 
        }
        return projects;
    }

    @Override
    public Project retrieveProject(int projectId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Project project = null;
        try {
            transaction = session.beginTransaction();
            Query<Project> query = session.createQuery("FROM Project where projectId = :projectId and isDeleted = false", Project.class);
            query.setParameter("projectId", projectId);
            project = query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get the project " + projectId);
            throw new EmployeeException("Unable to get the project" + projectId, e);
        } finally {
            session.close();
        }
        return project;
    }

    @Override 
    public Project updateProjectName(Project project) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to update the project for the project Id : " + project.getProjectId());
            throw new EmployeeException("Unable to update the project for the project Id : " + project.getProjectId(), e);
        } finally {
            session.close();
        }
        return project;
    }

    @Override 
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Employee employeeObject = session.createQuery("FROM Employee WHERE isDeleted = :isDeleted and employeeId = :employeeId", Employee.class)
                                             .setParameter("isDeleted", false)
                                             .setParameter("employeeId", employee.getEmployeeId()).uniqueResult();
            Project projectObject = session.createQuery("FROM Project where projectId = :projectId and isDeleted = false", Project.class)
                                           .setParameter("projectId", project.getProjectId()).uniqueResult();
            Set<Employee> employees = projectObject.getEmployees();
            Set<Project> projects = employeeObject.getProjects();
            projects.add(projectObject);
            employees.add(employeeObject);
            session.saveOrUpdate(employeeObject);
            session.saveOrUpdate(projectObject);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to add the project for the employee :" + employee.getEmployeeName());
            throw new EmployeeException("Unable to add the project for the employee :" + employee.getEmployeeName(), e);
        } catch (Exception e) { 
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Project project = null;
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            String query = "select p from Project p LEFT JOIN FETCH p.employees WHERE p.projectId = :id";
            project = session.createQuery(query, Project.class).setParameter("projectId", projectId).uniqueResult();
            if (project != null) {
                employees = new ArrayList<>( project.getEmployees()); 
            } 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get the employees for the project " + project.getProjectName());
            throw new EmployeeException("Unable to get the employees for the project" + project.getProjectName(), e);
        }
        return employees;
    }  
 
    @Override  
    public boolean isProjectDeleted(Project project) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        int projectId = project.getProjectId();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Project SET isDeleted = :isDeleted WHERE id = :projectId");
            query.setParameter("isDeleted", true);
            query.setParameter("projectId", projectId);
            int row = query.executeUpdate();
            transaction.commit();
            if (row == 1) {
                return true;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to delete the project " + project.getProjectName());
            throw new EmployeeException("Unable to delete the project" + project.getProjectName(), e);
        } finally { 
            session.close();   
        }
        return false;
    }
  
}
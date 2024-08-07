package com.ideas2it.ems.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * This class is have methods for department CRUD operation and implements department Dao. 
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public class DepartmentDaoImpl implements DepartmentDao {
    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public Department insertDepartment(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            logger.debug("{} department added successfully.", department.getDepartmentName());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to add the Department : {}", department.getDepartmentName());
            throw new EmployeeException("Unable to add the Department : " + department.getDepartmentName(), e);
        }
        return department;
    }

    @Override
    public List<Department> retrieveDepartments() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Department> departments;
        try {
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("FROM Department WHERE isDeleted = :isDeleted", Department.class)
                                              .setParameter("isDeleted", false);
            departments = query.list();
            logger.debug("Retrieved list of departments.");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get the list of departments.");
            throw new EmployeeException("Unable to get the list of departments.", e);
        } finally {
            session.close();
        }
        return departments;
    }
  
    @Override
    public Department retrieveDepartment(int departmentId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Department department;
        try {
            transaction = session.beginTransaction();
            department = session.createQuery("FROM Department WHERE isDeleted = false and departmentId = :departmentId", Department.class)
                                .setParameter("departmentId", departmentId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get department for the ID : {}", departmentId);
            throw new EmployeeException("Unable to get department" + departmentId, e);
        } finally {
            session.close();
        }
        return department;
    }
    
    @Override
    public List<Employee> retrieveEmployeesByDepartment(int departmentId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Department department = null;
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            department = session.createQuery("FROM Department WHERE isDeleted = false and departmentId = :departmentId", Department.class)
                                .setParameter("departmentId", departmentId).uniqueResult();
            if (null != department) {
                employees = new ArrayList<>(department.getEmployees());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            assert department != null;
            logger.error("Unable to get employees for the department {}", department.getDepartmentName());
            throw new EmployeeException("Unable to get employees for the department " + department.getDepartmentName(), e);
        } 
        return employees;
    } 

    @Override 
    public Department updateDepartmentName(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to update the department {}", department.getDepartmentName());
            throw new EmployeeException("Unable to update the department " + department.getDepartmentName(), e);
        }
        return department;
    }

    @Override  
    public boolean isDepartmentDeleted(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            int departmentId = department.getDepartmentId();
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Department SET isDeleted = :isDeleted WHERE id = :departmentId");
            query.setParameter("isDeleted", true);
            query.setParameter("departmentId", departmentId);
            int row = query.executeUpdate();
            transaction.commit();
            if (row == 1) {
                return true;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to delete the department{}", department.getDepartmentName());
            throw new EmployeeException("Unable to delete the department" + department.getDepartmentName(), e);
        }
        return false;
    }

}
package com.ideas2it.ems.dao;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * <p>
 * This class is have methods for employee CRUD operations and implements EmployeeDao interface
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public Employee insertEmployee(Employee employee) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to add the employee{}", employee.getEmployeeName());
            throw new EmployeeException("Unable to add the employee" + employee.getEmployeeName(), e);
        }
        return employee;
    }

    @Override
    public List<Employee> retrieveEmployees() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Employee> employees;
        try {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("FROM Employee WHERE isDeleted = :isDeleted", Employee.class)
                                           .setParameter("isDeleted", false);
            employees = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get the employees");
            throw new EmployeeException("Unable to get employees", e);
        }
        return employees;
    }

    @Override
    public Employee retrieveEmployeeById(int id) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Employee employee;
        try {
            transaction = session.beginTransaction();
            employee = session.createQuery("FROM Employee WHERE isDeleted = :isDeleted and employeeId = :employeeId", Employee.class)
                              .setParameter("isDeleted", false)
                              .setParameter("employeeId", id).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get the employee{}", id);
            throw new EmployeeException("Unable to get employee" + id, e);
        } finally {
            session.close();
        }
        return employee;
    }


    @Override
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to update the employee {}", employee.getEmployeeId());
            throw new EmployeeException("Unable to update the employee " + employee.getEmployeeId(), e);
        }
        return employee;
    }
   
    @Override
    public boolean isEmployeeDeleted(int employeeId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Employee SET isDeleted = :isDeleted WHERE id = :employeeId");
            query.setParameter("isDeleted", true);
            query.setParameter("employeeId", employeeId);
            int row = query.executeUpdate();
            transaction.commit();
            if (row == 1) {
                return true;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to delete the employee {}", employeeId);
            throw new EmployeeException("Unable to delete the employee" + employeeId, e);
        }
        return false;
    }
}
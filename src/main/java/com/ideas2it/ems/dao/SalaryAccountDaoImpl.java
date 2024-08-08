package com.ideas2it.ems.dao;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.SalaryAccount;

/**
 * <p>
 * This class is for inserting and retrieving the salary account details of the employee from the database
 * and implements SalaryAccountDao interface.
 * </p>
 *
 * @author    JeevithaKesavaraj
 */
public class SalaryAccountDaoImpl implements SalaryAccountDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void insertSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(salaryAccount);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to add the account details : {}", salaryAccount.getAccountNumber());
            throw new EmployeeException("Unable to add the account details : " + salaryAccount.getAccountNumber(), e);
        }
    }

    @Override 
    public SalaryAccount retrieveSalaryAccount(int id) throws EmployeeException {
        SalaryAccount salaryAccount;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            salaryAccount = session.get(SalaryAccount.class, id);
        } catch (Exception e) {
            logger.error("Unable to get the salary account details");
            throw new EmployeeException("Unable to get the salary account details", e);
        }
        return salaryAccount;
    }

    @Override
    public void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(salaryAccount);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to update the account details : {}", salaryAccount.getAccountNumber());
            throw new EmployeeException("Unable to update the account details : " + salaryAccount.getAccountNumber(), e);
        }
    }

}
package com.ideas2it.ems.dao;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.SalaryAccount;

/**
 * <p>
 * This interface has method declaration for inserting and retrieving the salary  
 * account details of the employee from the database.
 * </p>
 *
 * @author    JeevithaKesavaraj
 */
public interface SalaryAccountDao {

    /**
     * <p>
     * Insert salary account details to the salary account table
     * </p>
     *
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while inserting the salary account details
     */
    void insertSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;

    /**
     * <p>
     * Retrieve salary account details of the employee
     * </p>
     *
     * @param  id               employee's account ID
     * @return SalaryAccount    salary account details
     * @throws EmployeeException  If exception occurs, while retrieving the salary account details
     */
    SalaryAccount retrieveSalaryAccount(int id) throws EmployeeException;

    /**
     * <p>
     * Update salary account details to the salary account table
     * </p>
     *
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while inserting the salary account details
     */
    void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;

}
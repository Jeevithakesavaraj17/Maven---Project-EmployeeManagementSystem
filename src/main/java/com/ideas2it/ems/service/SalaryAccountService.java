package com.ideas2it.ems.service;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.SalaryAccount;

/**
 * <p>
 * This interface has method declaration for inserting and retrieving the salary 
 * account details of the employee from the database
 * </p>
 *
 * @author    JeevithaKesavaraj
 */
public interface SalaryAccountService {

    /**
     * <p>
     * Add salary account details to the salary account table
     * </p>
     *
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while adding the salary account details
     */
    void addSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;

    /**
     * <p>
     * Get salary account details of the employee
     * </p>
     *
     * @param  id               employee's account id
     * @return SalaryAccount    salary account details
     * @throws EmployeeException  If exception occurs, while getting the salary account details
     */
    SalaryAccount getSalaryAccount(int id) throws EmployeeException;

    /**
     * <p>
     * Update salary account details to the salary account table
     * </p>
     *
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while updating the salary account details
     */
    void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;
}
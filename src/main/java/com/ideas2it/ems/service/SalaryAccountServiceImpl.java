package com.ideas2it.ems.service;

import com.ideas2it.ems.dao.SalaryAccountDao;
import com.ideas2it.ems.dao.SalaryAccountDaoImpl;
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
public class SalaryAccountServiceImpl implements SalaryAccountService {
    private final SalaryAccountDao salaryAccountDao = new SalaryAccountDaoImpl();

    @Override
    public void addSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        salaryAccountDao.insertSalaryAccount(salaryAccount);   
    }

    @Override
    public SalaryAccount getSalaryAccount(int id) throws EmployeeException {
        return salaryAccountDao.retrieveSalaryAccount(id);
    }

    @Override
    public void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        salaryAccountDao.updateSalaryAccount(salaryAccount);
    }
}
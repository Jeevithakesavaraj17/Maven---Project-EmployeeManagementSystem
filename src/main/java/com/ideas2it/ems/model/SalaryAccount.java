package com.ideas2it.ems.model;

import com.ideas2it.ems.model.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * This classs represents salary account details of the employee
 * id               id for the account detail of the employee
 * accountNumber    employee's Account number
 * ifscCode         IFSC code of the account
 * </p>
 *
 * @author Jeevithakesavaraj
 */

@Entity
@Table(name = "salary_account")
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "ifsc_code")
    private String ifscCode;

    public SalaryAccount() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public int getId() {
        return id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    
    public String getIfscCode() {
        return ifscCode;
    }

    @Override
    public String toString() {
        return "Account Number : " + accountNumber
                + "\nIFSC Code : " + ifscCode;
    }
}
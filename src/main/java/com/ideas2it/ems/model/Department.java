package com.ideas2it.ems.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <p>
 * Department is nothing but a domain where group of employees working.
 * This class represents department details
 * </p>
 *
 * departmentId   ID of the department
 * departmentName Name of the department
 * Set<Employee>  set of employees in the particular department
 *
 * @author Jeevithakesavaraj
 */

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int departmentId;

    @Column(name = "name")
    private String departmentName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    Set<Employee> employees;

    public Department() {}

    public void setDepartmentId(int id) {
        this.departmentId = id;
    }
    
    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getDepartmentId() {
        return departmentId;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public Set<Employee> getEmployees() {
        return employees;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department Id : " + departmentId
                + "\nDepartmentName : " + departmentName;
    }
}
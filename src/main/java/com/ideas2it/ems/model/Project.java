package com.ideas2it.ems.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * This classs represents Project Details and set of employees who are all in same projectId.
 * projectId    Id of the project 
 * ProjectName   Name of the Project
 * Set<Employee>  Set of employees in the particular project
 * </p>
 *
 * @author Jeevithakesavaraj
 */

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int projectId;

    @Column(name = "name")
    private String projectName;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private Set<Employee> employees;
    
    public Project() {}

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
   
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) { 
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project Id : " + projectId
                + "\nProject name : " + projectName;
    }

}
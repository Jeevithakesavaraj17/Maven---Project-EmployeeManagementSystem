package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dao.DepartmentDaoImpl;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.service.DepartmentService;

/**
 * <p>
 * This class implements Department service which have method for add, get, update and delete
 * department details and get employees in the particular department.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    
    @Override
    public Department addDepartment(String departmentName) throws EmployeeException {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        return departmentDao.insertDepartment(department);
    }

    @Override
    public List<Department> getDepartments() throws EmployeeException {
        return departmentDao.retrieveDepartments();
    }
 
    @Override
    public Department getDepartment(int departmentId) throws EmployeeException {
        return departmentDao.retrieveDepartment(departmentId);
    }

    @Override 
    public boolean isDepartmentPresent(int departmentId) throws EmployeeException {
        Department department = departmentDao.retrieveDepartment(departmentId);
        if (null == department) {
            return false;
        }
        return true;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) throws EmployeeException {
        return departmentDao.retrieveEmployeesByDepartment(departmentId);
    }

    @Override 
    public Department updateDepartmentName(Department department) throws EmployeeException {
        return departmentDao.updateDepartmentName(department);
    }

    @Override
    public boolean isDepartmentDeleted(Department department) throws EmployeeException {
        return departmentDao.isDepartmentDeleted(department);
    }
}

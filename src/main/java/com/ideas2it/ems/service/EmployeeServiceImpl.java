package com.ideas2it.ems.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.dao.EmployeeDaoImpl;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.SalaryAccount;
import com.ideas2it.ems.service.DepartmentService;
import com.ideas2it.ems.service.DepartmentServiceImpl;
import com.ideas2it.ems.service.ProjectService;
import com.ideas2it.ems.service.ProjectServiceImpl;
import com.ideas2it.ems.service.SalaryAccountService;
import com.ideas2it.ems.service.SalaryAccountServiceImpl;

/**
 * <p>
 * This class implements employee service and have methods for add, get, update, delete the employee details.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
public class EmployeeServiceImpl implements EmployeeService {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();
    private SalaryAccountService salaryAccountService = new SalaryAccountServiceImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
  
    @Override
    public Employee addEmployee(String employeeName, 
                            LocalDate dateOfBirth, long phoneNumber,
                            String mailId, int experience, int departmentId,
                            long accountNumber, String ifscCode) throws EmployeeException {
        Department department = departmentService.getDepartment(departmentId);
        SalaryAccount salaryAccount = new SalaryAccount();
        salaryAccount.setAccountNumber(accountNumber);
        salaryAccount.setIfscCode(ifscCode);
        salaryAccountService.addSalaryAccount(salaryAccount);
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setDepartment(department);
        employee.setSalaryAccount(salaryAccount);
        employee.setPhoneNumber(phoneNumber);
        employee.setMailId(mailId);
        employee.setExperience(experience);
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() throws EmployeeException {
        return employeeDao.retrieveEmployees();
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        return employeeDao.retrieveEmployeeById(employeeId);
    }

    @Override
    public boolean isEmployeeListEmpty() throws EmployeeException {
        List<Employee> employees = employeeDao.retrieveEmployees();
        if (employees.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmployeePresent(int employeeId) throws EmployeeException {
        Employee employee = employeeDao.retrieveEmployeeById(employeeId);
        if (null == employee) {
            return false;
        }
        return true;
    }

    @Override
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeException {
        return employeeDao.updateEmployeeDetails(employee);
    }

    @Override
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException {
        projectService.addProjectToEmployee(project, employee);
    }

    @Override
    public boolean isEmployeeDeleted(int employeeId) throws EmployeeException {
        return employeeDao.isEmployeeDeleted(employeeId);
    }
}
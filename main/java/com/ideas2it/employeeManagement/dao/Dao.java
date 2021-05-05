package com.ideas2it.employeeManagement.dao;

import java.util.List;

import com.ideas2it.employeeManagement.model.Employee;

/**
 * Dao to get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 19-03-2021.
 * @author   Nandhakumar.
 */
public interface Dao {
	
    /**
     * Employee Details are get from user and,
     * add Employee details to database.
     *
     * @param employee   that contains all details of employee.
     *
     * @isEmployeeAdded  return true when added sucessfully or return false. 
     */
    public int insertEmployeeDetails(Employee employee);
	
    /**
     * getting new address from user and add to database.
     *
     * @param employeeId  it contains new address to add.
     * 
     * @return isAdded return true when sucessfully added or return false
     */
    public boolean addNewAddress(Employee employee);
	
    /**
     * Here EmployeeID  get from user and,
     * EmployeeDetails are get from database.
     *
     * @param employeeId    EmployeeId to get that specific person,
     * details from employees map. 
     *
     * @return employee   Employee Details for user View. 
     */
    public Employee getEmployeeDetails(int employeeId);
	
    /**
     * Employee Details are get from user and,
     * update Employee details to database.
     *
     * @param employee  it contains details to update
     *
     * @return isUpdated true when sucessfully updated or return false.
     */
    public boolean updateEmployee(Employee employee);
	 
    /**
     * Get AllEmployees details from employeesMap and send for user view.
     *
     * @return allEmployees  list contains all employeeDetails.
     */
    public List getAllEmployeeDetails(boolean isDeleted);
	  
    /**
     * EmployeeId get from user and,
     * Checking Id Already we have or not.
     *
     * @param employeeId  For checking whether already we have or not.
     *
     * @return true when Employee ID already Registered or return false.
     */  
    public boolean checkEmployeeIdExists(int employeeId);
    
}
	  
















 




 
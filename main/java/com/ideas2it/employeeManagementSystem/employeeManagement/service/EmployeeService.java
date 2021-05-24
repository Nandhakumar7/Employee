package com.ideas2it.employeeManagementSystem.employeeManagement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employeeManagement.model.Address;
import com.ideas2it.employeeManagementSystem.employeeManagement.model.Employee;

/**
 * EmployeeService get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public interface EmployeeService {

    /**
     * Employee Details are get from user and,
     * add Employee details to Employees database.
     *
     * @param employeeId    details to create employee.
     *
     * @isEmployeeAdded  return true when added sucessfully or return false. 
     */
    public int addEmployeeDetails(Employee employee) throws EmployeeManagementException;
    
    /**
     * Here EmployeeID  get from user and,
     * EmployeeDetails are get from database.
     *
     * @param employeeId    EmployeeId to get that specific person,
     * details from employees map. 
     *
     * @return Employee   Employee Details for user View. 
     * @throws EmployeeManagementException 
     */
    public Employee getEmployeeDetails(int employeeId) throws EmployeeManagementException;

    /**
     * EmployeeId get from user and,
     * Checking Id Already we have or not.
     *
     * @param employeeId  For checking whether already we have or not.
     *
     * @return true when Employee ID already Registered or return false.
     * @throws EmployeeManagementException 
     */
    public boolean checkEmployeeIdExists(int employeeId) throws EmployeeManagementException;
  
    /**
     * Employee Details are get from user and,
     * update Employee details to database.
     *
     * @param employee  employeeDetails to update.
     *
     * @return isUpdated true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */     
    public void updateEmployee(Employee employee) throws EmployeeManagementException;
    
    /**
     * EmployeeID are get from user and,
     * get Employee address details from database.
     *
     * @param employeeId  for get that specific employee address. 
     * @return employeeAddress  It contains employee all Address.
     * @throws EmployeeManagementException 
     */
    public Address getEmployeeAddress(int employeeId, int addressId) throws EmployeeManagementException;
	
    /**
     * EmployeeID are get from user and,
     * remove Employee details from database.
     *
     * @param employeeId  for remove that specific employee. 
     * @param choosedaddress  to delete that specific address.
     * @throws EmployeeManagementException 
     * 
     */
    public boolean deleteEmployeeAddress(int choosedAddress, int employeeId) 
    		throws EmployeeManagementException;
	
    /**
     * update address as a primary address
     * 
     * @choosedAddress  address to update
     * @employeeId   to update specific employeeAddress.
     * 
     * @return boolean true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public void updateAsPrimaryAddress(int choosedAddress, int employeeId) 
    		throws EmployeeManagementException;
	
    /**
     * Employee Details are get from user and,
     * add Employee address details to database.
     *
     * @param employeeId     Specific Id for individual persons.  
     * @param address        address deatils to update.
     * @param choosedAddress old address for update.
     *
     * @throws EmployeeManagementException 
     */
    public void updateAddress(int choosedAddress, Address address,
    		int employeeId) throws EmployeeManagementException;

    /**
     * Employee Details are get from user and,
     * add Employee address details to database.
     *
     * @param employeeId     Specific Id for individual persons.  
     * @param address      address details to add.
     *
     * @return isUpdated true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public boolean addNewAddress(int employeeId, Address address) 
    		throws EmployeeManagementException;
			
    /**
     * EmployeeID are get from user and,
     * remove Employee details from database.
     *
     * @param employeeId  for remove that specific employee. 
     * 
     * @return isDeleted return true when sucessfully deleted or return false
     * @throws EmployeeManagementException 
     */
    public void deleteOrRestoreEmployee(int employeeId) throws EmployeeManagementException;

    /**
     * projectId and employeeId are get from user and,
     * assign project for that employees.
     *
     * @param employeeId  for assign that specific employee.
     * @param projectsId   it contains projects to assign.	 
     * 
     * @return isAssigned return true when sucessfully assigned or return false
     * @throws EmployeeManagementException 
     */
    public void assignProject(int employeeId, List projectsId) throws EmployeeManagementException;

    /**
     * projectId get from user and,
     * get list employees already worked in that project.
     *
     * @param employeeId  For getting specific project employees.
     * @param projectsId  it contains project ids.
     *
     * @return List  It contains list of projects assigned and un assigned.
     * @throws EmployeeManagementException 
     */
    public List<List> employeeProjects(int employeeId) throws EmployeeManagementException;

    /**
     * employeeId get from user and,
     * get list employees from database.
     *
     * @param employeeId  For getting specific  employees.
     *
     * @return List  It contains list of employees assigned and unassign operation.
     * @throws EmployeeManagementException 
     */
    public List<Employee> getEmployeeForProject(List<Integer> employeeId) throws EmployeeManagementException;

    /**
     * get all address and check deleted or not.
     *
     * @param list<Address>  it contains all addresses
     *
     * @return list<Address>  It contains list of existing addresses
     */
    public List<Address> getUnDeletedAddress(List<Address> addresses);   

    /**
     * get address and check whether primary or not.
     *
     * @param choosedAddress  For check primary or not.
     *
     * @return boolean  true when given address primary or return false.
     */
    public boolean checkAddressPrimaryOrNot(Address choosedAddress);

    /**
     * Get AllEmployees details from employeesMap and send for user view.
     *
     * @return allEmployees  list contains all employeeDetails.
     * @throws EmployeeManagementException 
     */
	public List<Employee> getAllEmployees(boolean b) throws EmployeeManagementException;
}
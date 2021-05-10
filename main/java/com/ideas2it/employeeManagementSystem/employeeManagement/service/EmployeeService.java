package com.ideas2it.employeeManagementSystem.employeeManagement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

import com.ideas2it.exception.EmployeeManagementException;
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
     * @param employeeId    Specific Id for individual persons.  
     * @param name          Name of employee need to add.
     * @param salary        Salary of employee need to add.
     * @param mobileNumber  MobileNumber of employee need to add.
     * @param dateOfBirth   DateOfBirth of employee need to add.
     * @param addresses     List of employee Addresses.
     * @throws EmployeeManagementException 
     *
     * @isEmployeeAdded  return true when added sucessfully or return false. 
     */
    public int addEmployeeDetails(String name,
            float salary, String mobileNumber, Date dateofbirth,
            List<List<String>> addresses) throws EmployeeManagementException;
			
    /**
     * Employee Details are get from user and,
     * add Employee details to database.
     *
     * @param employeeId     Specific Id for individual persons.  
     * @param doorNumber         doorNumber of employee need to add.
     * @param streetName     streetName of employee need to add.
     * @param district       district of employee need to add.
     * @param State          State of employee need to add.
     * @param country        country of employee need to add.
     * @param pinCode        pinCode of employee need to add.
     */
     public List<Address> getAddressList(List<List<String>> addresses);

    /**
     * Here EmployeeID  get from user and,
     * EmployeeDetails are get from database.
     *
     * @param employeeId    EmployeeId to get that specific person,
     * details from employees map. 
     *
     * @return String   Employee Details for user View. 
     * @throws EmployeeManagementException 
     */
    public List<List<String>> getEmployeeDetails(int employeeId) throws EmployeeManagementException;

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
     * Get AllEmployees details from employeesMap and send for user view.
     *
     * @return allEmployees  list contains all employeeDetails.
     * @throws EmployeeManagementException 
     */
    public List<List<String>> getAllEmployee(boolean isDeleted) throws EmployeeManagementException;

    /**
     * projectId get from user and,
     * Checking Id Already we have or not.
     *
     * @param projectId  For checking whether already we have or not.
     *
     * @return true when Employee ID already Registered or return false.
     * @throws EmployeeManagementException 
     */
    public boolean checkProjectIdExists(int projectId) throws EmployeeManagementException;

    /**
     * Check whether given mobilenumber valid or not.
     *
     * @param mobileNumber mobileNumber for validation. 
     *
     * @return true when mobileNumber is valid or return false.
     *
     */
    public boolean validateMobileNumber(String mobileNumber);

    /**
     * DateOfBirth are get from user and,
     * Checking DateOfBirth valid or not.
     *
     * @param dateOfBirth. DateOfBirth for validation.
     *
     * @return VaildatedDateOfBirth   If valid DateOfBirth,
     * return DateOfBirth or return null
     */
    public Date getDateOfBirth(String dateOfBirth);
  
    /**
     * Employee Details are get from user and,
     * update Employee details to database.
     *
     * @param employeeId   Id is for one whose Details need to Change.       
     * @param name         new Name for change.
     * @param salary       new Salary for change.
     * @param mobileNumber new mobileNumber for change.
     * @param dateOfBirth  new DateOfBirth for change.
     *
     * @return isUpdated true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */     
    public void updateEmployee(int employeeId, String name, float salary,
            String mobileNumber, Date dateOfBirth) throws EmployeeManagementException;
    
    /**
     * EmployeeID are get from user and,
     * get Employee address details from database.
     *
     * @param employeeId  for get that specific employee address. 
     * @return employeeAddress  It contains employee all Address.
     * @throws EmployeeManagementException 
     */
    public List<String> getEmployeeAddress(int employeeId) throws EmployeeManagementException;
	
    /**
     * EmployeeID are get from user and,
     * remove Employee details from database.
     *
     * @param employeeId  for remove that specific employee. 
     * @throws EmployeeManagementException 
     */
    public boolean deleteEmployeeAddress(int choosedAddress, int employeeId) throws EmployeeManagementException;
	
    /**
     * update address as a primary address
     * 
     * @choosedAddress  address to update
     * 
     * @return boolean true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public void updateAsPrimaryAddress(int choosedAddress, int employeeId) throws EmployeeManagementException;
	
    /**
     * Employee Details are get from user and,
     * add Employee address details to database.
     *
     * @param employeeId     Specific Id for individual persons.  
     * @param doorNumber         doorNumber need to change.
     * @param streetName     streetName need to change.
     * @param district       district need to change.
     * @param State          State of need to change.
     * @param country        country need to change.
     * @param pinCode        pinCode need to change.
     * @param choosedAddress old address for update.
     *
     * @return isUpdated true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public void updateAddress(int choosedAddress, int employeeId,
            int doorNumber, String streetName, String district, String State,
            String Country, int pinCode) throws EmployeeManagementException;

    /**
     * Employee Details are get from user and,
     * add Employee address details to database.
     *
     * @param employeeId     Specific Id for individual persons.  
     * @param doorNumber         doorNumber need to change.
     * @param streetName     streetName need to change.
     * @param district       district need to change.
     * @param State          State of need to change.
     * @param country        country need to change.
     * @param pinCode        pinCode need to change.
     * @param choosedAddress old address for update.
     *
     * @return isUpdated true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public boolean addNewAddress(int employeeId,
            int doorNumber, String streetName, String district, String State,
            String Country, int pinCode) throws EmployeeManagementException;
			
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
    public List<List<String>> employeeProjects(int employeeId) throws EmployeeManagementException;

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
}
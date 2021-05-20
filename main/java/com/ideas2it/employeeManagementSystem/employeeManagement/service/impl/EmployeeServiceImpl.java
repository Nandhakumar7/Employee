package com.ideas2it.employeeManagementSystem.employeeManagement.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ideas2it.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employeeManagement.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.employeeManagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeeManagementSystem.employeeManagement.model.Address;
import com.ideas2it.employeeManagementSystem.employeeManagement.model.Employee; 
import com.ideas2it.employeeManagementSystem.projectManagement.model.Project;
import com.ideas2it.employeeManagementSystem.projectManagement.service.ProjectService;
import com.ideas2it.employeeManagementSystem.projectManagement.service.impl.ProjectServiceImpl;
import com.ideas2it.employeeManagementSystem.employeeManagement.service.EmployeeService;
import com.ideas2it.logger.EmployeeManagementLogger;

/**
 * ServiceImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 19-03-2021.
 * @author   Nandhakumar.
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public int addEmployeeDetails(Employee employee) throws EmployeeManagementException {
       // List<Address> addressList = getAddressList(addresses);
       // boolean isDeleted = false;
       // Employee employee = new Employee (name, salary,
               // mobileNumber, dateofbirth, addressList, isDeleted);
        int employeeId
                = employeeDaoImpl.insertEmployeeDetails(employee);				
        return employeeId;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Address> getAddressList(List<List<String>> addresses) {
        List <Address> addressList = new ArrayList<Address>();
        for(List<String> address : addresses) {
            int doorNumber = Integer.parseInt(address.get(1));
            int pinCode = Integer.parseInt(address.get(6));
            boolean isDeleted = false;	
        addressList.add(new Address (doorNumber, address.get(2), address.get(3), 
                address.get(4), address.get(5), pinCode,
                address.get(0), isDeleted));
        }
        return addressList;
    }
			
    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployeeDetails(int employeeId)
    		throws EmployeeManagementException {
        Employee employee = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<Address> addressList = employee.getAddressList();	
        List<Address> addresses = getUnDeletedAddress(addressList);
        employee.setAddressList(addresses);
        return employee;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkEmployeeIdExists(int employeeId)
    		throws EmployeeManagementException {
        return(employeeDaoImpl.checkEmployeeIdExists(employeeId));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployees(boolean isDeleted) 
    		throws EmployeeManagementException {
    	List<Employee> allEmployee = employeeDaoImpl.getAllEmployeeDetails(isDeleted);
		return allEmployee;
    	
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateMobileNumber(String mobileNumber) {
        return (mobileNumber.matches("[6-9][0-9]{9}"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDateOfBirth(String dateOfBirth) {
        Date validatedDateOfBirth;
        System.out.println(dateOfBirth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            validatedDateOfBirth = dateFormat.parse(dateOfBirth);
        } catch (Exception e) {
            validatedDateOfBirth = null;
    	}
    	return validatedDateOfBirth;
    }
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public void updateEmployee(Employee updateEmployee) throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(EmployeeServiceImpl.class);
    	Employee employee =null;
    	try {
            employee = employeeDaoImpl.getEmployeeDetails(updateEmployee.getId());
            String newName = (null == updateEmployee.getName()) ? employee.getName() : updateEmployee.getName();
            String newMobileNumber
                    = (null == updateEmployee.getMobileNumber()) ? employee.getMobileNumber() : updateEmployee.getMobileNumber();
            float newSalary = (0 == updateEmployee.getSalary()) ? employee.getSalary() : updateEmployee.getSalary();
            Date newDateOfBirth
                    = (null == updateEmployee.getDateOfBirth()) ? employee.getDateOfBirth() : updateEmployee.getDateOfBirth();
            employee.setName(newName);
            employee.setMobileNumber(newMobileNumber);
            employee.setSalary(newSalary);
            employee.setDateOfBirth(newDateOfBirth);
            employeeDaoImpl.updateEmployee(employee);
    	} catch(EmployeeManagementException e) {
    		log.logError("fail to update Id is " + updateEmployee.getId(), e);
    	    throw new EmployeeManagementException("Employee Update Failure");	
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Address> getUnDeletedAddress(List<Address> addresses) {
        List<Address> addressList = new LinkedList<Address>();
        for(Address address : addresses)  {
            if( ! (address.getIsDeleted())) {
                addressList.add(address);
            }
        }
        return addressList;
    }
        
        
    /**
     * {@inheritDoc}
     */
    @Override
    public Address getEmployeeAddress(int employeeId, int id) 
    		throws EmployeeManagementException {
        Employee employee
                = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<Address> addressList = employee.getAddressList();
        List<Address> addresses = getUnDeletedAddress(addressList);
        Address choosedAddress = null;
        for(Address address : addressList) {
        	if(address.getId() == id) {
        		choosedAddress = address;
        	}
        }
        return choosedAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAddressPrimaryOrNot(Address choosedAddress) {
        String primary = choosedAddress.getAddressType();
        String addressType = "Primary";
        return (addressType.equals(primary));
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteEmployeeAddress(int choosedAddress, int employeeId)
    		throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(EmployeeServiceImpl.class);
    	try {
            boolean isDeleted = false;
            Employee employee
                    = employeeDaoImpl.getEmployeeDetails(employeeId);
            List<Address> addressList = employee.getAddressList();	
            List<Address> addresses = getUnDeletedAddress(addressList);
            Address employeeAddress = addresses.get(choosedAddress - 1);
            if( !(checkAddressPrimaryOrNot(employeeAddress))) {
                int addressId = employeeAddress.getId();
                for(Address addressObject: addresses)  { 
                    if(addressId == addressObject.getId()) {
                        addressObject.setIsDeleted(true);
                    }
                }
                return employeeDaoImpl.updateEmployee(employee);  
            } else {
                return isDeleted;
            }
        } catch (EmployeeManagementException e) {
        	log.logError("Fail to delete address employeeId is " + employeeId, e);
            throw new EmployeeManagementException
                    ("Employee Address Delete Operation Failure");	
        }
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override
    public void updateAsPrimaryAddress(int choosedAddress, int employeeId) 
    		throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(EmployeeServiceImpl.class);
		Employee employee = null;
		try {
            employee = employeeDaoImpl.getEmployeeDetails(employeeId);
            List<Address> addressList = employee.getAddressList();
            List<Address> addresses = getUnDeletedAddress(addressList);
            Address employeeAddress = addresses.get(choosedAddress - 1);
            for(Address address : addresses)  {
                String primary = address.getAddressType();
                String addressType = "Primary";
                if (addressType.equals(primary)) {
                    address.setAddressType("Secondary");
                }		
            }
            employeeAddress.setAddressType("Primary");
            employee.setAddressList(addressList);
            employeeDaoImpl.updateEmployee(employee);
        } catch (EmployeeManagementException e) {
        	log.logError("Fail to update as primary address employeeId is " + employeeId, e);
            throw new EmployeeManagementException("Employee Primary AddressUpdate Failure");	
        }
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAddress(int choosedAddress, Address address,
    		int employeeId) throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(EmployeeServiceImpl.class);
        Employee employee = null;
        try {
            employee = employeeDaoImpl.getEmployeeDetails(employeeId);
            List<Address> addressList = employee.getAddressList();
            List<Address> addresses = getUnDeletedAddress(addressList);
            Address oldAddress = addresses.get(choosedAddress - 1);
            oldAddress.setdoorNumber(address.getdoorNumber());
            oldAddress.setStreetName(address.getStreetName());
		    oldAddress.setDistrict(address.getDistrict());
            oldAddress.setState(address.getState());
		    oldAddress.setCountry(address.getCountry());
		    oldAddress.setPinCode(address.getPinCode());
            employee.setAddressList(addressList);
            employeeDaoImpl.updateEmployee(employee);
        } catch (EmployeeManagementException e) {
        	log.logError("Fail to update address employeeId is " + employeeId, e);
            throw new EmployeeManagementException("Employee AddressUpdate Failure");	
        }
    }
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean addNewAddress(int employeeId, Address address) throws EmployeeManagementException {
        Employee employee = null;
        try {
            employee = employeeDaoImpl.getEmployeeDetails(employeeId);
            List<Address> addressList = employee.getAddressList();
            String addressType = "Secondary";
            address.setAddressType(addressType);
            addressList.add(address);
            employee.setAddressList(addressList);
        } catch (EmployeeManagementException e) {
            throw new EmployeeManagementException("Employee Address Adding Failure");	
        }
        return employeeDaoImpl.addNewAddress(employee);
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override
    public void deleteOrRestoreEmployee(int employeeId) 
    		throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(EmployeeServiceImpl.class);
        Employee employee = null;
        try {
            employee = employeeDaoImpl.getEmployeeDetails(employeeId);
            boolean isDeleted;
            if(employee.getIsDeleted()) {
        	    isDeleted = false;
            } else {
        	    isDeleted = true;
            }
            employee.setIsDeleted(isDeleted);
            List<Address> employeeAddress = employee.getAddressList();
            for(Address address : employeeAddress) {
                address.setIsDeleted(isDeleted);
            }
            employee.setProjectList(null);
            employeeDaoImpl.updateEmployee(employee);
        } catch (EmployeeManagementException e) {
        	log.logError("fail to delete or restore id is " + employeeId, e);
            throw new EmployeeManagementException("Employee Delete/Restore Failure");	
        }
    }
 
    /**
     * {@inheritDoc} 
     */ 
    @Override
    public boolean checkProjectIdExists(int projectId) 
    		throws EmployeeManagementException {
       ProjectService projectService 
               = new ProjectServiceImpl();
        return projectService.checkProjectIdExists(projectId);
    }
	
    /**
     * {@inheritDoc}
     * @throws EmployeeManagementException 
     */
    @Override
	public List<Employee> getEmployeeForProject(List<Integer> employeeId) 
			throws EmployeeManagementException {
        List<Employee> requiredEmployees = new ArrayList<Employee>();
        try {
            List<Employee> employees = (employeeDaoImpl.getAllEmployeeDetails(false));
            for (Employee employee : employees) {			
                if (employeeId.contains(employee.getId())) {
                    requiredEmployees.add(employee);
                }
            }
        } catch (EmployeeManagementException e) {
            throw new EmployeeManagementException("Fail to get Details Please try again..");	
        }
        return requiredEmployees;
    }
	
    /**
     * {@inheritDoc} 
     */ 
    @Override
    public void assignProject(int employeeId, List projectsId) 
    		throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(EmployeeServiceImpl.class);
    	Employee employee;
    	try {
            ProjectService projectService = new ProjectServiceImpl();
            employee = employeeDaoImpl.getEmployeeDetails(employeeId);
            List<Project> employeeProjects = employee.getProjectList();
            List<Project> projects = projectService.getProjectsForEmployee(projectsId);
            employee.setProjectList(projects);	
            employeeDaoImpl.updateEmployee(employee);
        } catch (EmployeeManagementException e) {
        	log.logError("fail to assign project employeeId is " + employeeId, e);
            throw new EmployeeManagementException("Project Assigning Failure..Try Again..");	
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List> employeeProjects(int employeeId) 
    		throws EmployeeManagementException {
    	List<List> employeeProjectAssignedDetails = new LinkedList<List>();
    	try { 
    	    ProjectService projectService = new ProjectServiceImpl();
            Employee employee = employeeDaoImpl.getEmployeeDetails(employeeId);
            List<Project> projects = employee.getProjectList();
            List<Integer> assignedProjectsId = new ArrayList<Integer>();
            List<Project> allProjects = projectService.getProjects(false);
            List<Project> unAssignedEmployees = new ArrayList<Project>();
            if (null == projects) {
            	assignedProjectsId = null;
            } else {
                for(Project project : projects) {
                	assignedProjectsId.add(project.getId());
                }
            }
            for(Project project : allProjects) {
        	    if(!(assignedProjectsId.contains(project.getId()))) {
        	    	unAssignedEmployees.add(project); 
        	    }
            }
            employeeProjectAssignedDetails.add(projects);
            employeeProjectAssignedDetails.add(unAssignedEmployees);
    	}  catch (EmployeeManagementException e) {
            throw new EmployeeManagementException
                    ("SomeThing Went Wrong Try Again!");
        }
        return employeeProjectAssignedDetails;
    }
}
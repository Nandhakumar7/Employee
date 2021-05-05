package com.ideas2it.employeeManagement.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeeManagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeeManagement.model.Address;
import com.ideas2it.employeeManagement.model.Employee; 
import com.ideas2it.projectManagement.model.Project;
import com.ideas2it.projectManagement.service.ProjectService;
import com.ideas2it.projectManagement.service.impl.ProjectServiceImpl;
import com.ideas2it.employeeManagement.service.EmployeeService;

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
    public int addEmployeeDetails(String name,
            float salary, String mobileNumber, Date dateofbirth,
            List<List<String>> addresses) {
        List<Address> addressList = getAddressList(addresses);
        boolean isDeleted = false;
        Employee employee = new Employee (name, salary,
                mobileNumber, dateofbirth, addressList, isDeleted);
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
    public List<List<String>> getEmployeeDetails(int employeeId) {
        Employee employee = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<Project> employeeProjects = new ArrayList<Project>();
        List<String> employeeDetails = new LinkedList<String>();
        List<List<String>> employeeDetailsList = new LinkedList<List<String>>();
        List<Address> address = employee.getAddressList();
        employeeProjects = employee.getProjectList();
        employeeDetails.add(String.valueOf(employee.getId()));
        employeeDetails.add(employee.getName());
        employeeDetails.add(employee.getMobileNumber());
        employeeDetails.add(String.valueOf(employee.getSalary()));
        Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirth = dateFormat.format(employee.getDateOfBirth());
        employeeDetails.add(dateOfBirth);
        for(Project project : employeeProjects) { 
            employeeDetails.add(String.valueOf(project.getId()));
            employeeDetails.add(project.getProjectName());
        } 
        employeeDetailsList.add(employeeDetails);
        List<String> addresses = new LinkedList<String>();
        for(Address employeeAddress : address) {
            if( ! (employeeAddress.getIsDeleted())) {
            	addresses.add(employeeAddress.getAddressType());
            	addresses.add(String.valueOf(employeeAddress.getdoorNumber()));
            	addresses.add(employeeAddress.getStreetName());
            	addresses.add(employeeAddress.getDistrict());
            	addresses.add(employeeAddress.getState());
            	addresses.add(employeeAddress.getCountry());
            	addresses.add(String.valueOf(employeeAddress.getPinCode()));
            }
        }
        employeeDetailsList.add(addresses);
        return employeeDetailsList;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkEmployeeIdExists(int employeeId) {
        return(employeeDaoImpl.checkEmployeeIdExists(employeeId));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<String>> getAllEmployee(boolean isDeleted) {
        List<Employee> allEmployee = employeeDaoImpl.getAllEmployeeDetails(isDeleted);
        List<List<String>> employees = new LinkedList<List<String>>();
        for(Employee employee : allEmployee) {
        	List<String> employeeDetails = new LinkedList<String>();
        	employeeDetails.add(String.valueOf(employee.getId())); 
        	employeeDetails.add(employee.getName());
        	employeeDetails.add(employee.getMobileNumber());
        	employeeDetails.add(String.valueOf(employee.getSalary()));
            Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfBirth = dateFormat.format(employee.getDateOfBirth());
            employeeDetails.add(dateOfBirth);
        	employees.add(employeeDetails);
        }
        return employees;
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
    public boolean updateEmployee(int employeeId, String name, float salary,
            String mobileNumber, Date dateOfBirth) {
        Employee employee
                = employeeDaoImpl.getEmployeeDetails(employeeId);
        String newName = (null == name) ? employee.getName() : name;
        String newMobileNumber
                = (null == mobileNumber) ? employee.getMobileNumber() : mobileNumber;
        float newSalary = (0 == salary) ? employee.getSalary() : salary;
        Date newDateOfBirth
                = (null == dateOfBirth) ? employee.getDateOfBirth() : dateOfBirth;
        employee.setName(newName);
        employee.setMobileNumber(newMobileNumber);
        employee.setSalary(newSalary);
        employee.setDateOfBirth(newDateOfBirth);
        return employeeDaoImpl.updateEmployee(employee);
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
    public List<String> getEmployeeAddress(int employeeId) {
        Employee employee
                = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<Address> addressList = employee.getAddressList();
        List<Address> addresses = getUnDeletedAddress(addressList);
        List<String> employeeAddress = new LinkedList<String>();
        for(Address address : addresses) {
            employeeAddress.add(address.toString());
        }
        return employeeAddress;
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
    public boolean deleteEmployeeAddress(int choosedAddress, int employeeId) {
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
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean updateAsPrimaryAddress(int choosedAddress, int employeeId) {
		Employee employee
                = employeeDaoImpl.getEmployeeDetails(employeeId);
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
        return employeeDaoImpl.updateEmployee(employee);
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateAddress(int choosedAddress,int employeeId,
            int doorNumber, String streetName, String district,
            String State, String Country, int pinCode) {
        Employee employee
                = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<Address> addressList = employee.getAddressList();
        List<Address> addresses = getUnDeletedAddress(addressList);
        Address oldAddress = addresses.get(choosedAddress - 1);
        oldAddress.setdoorNumber(doorNumber);
        oldAddress.setStreetName(streetName);
		oldAddress.setDistrict(district);
        oldAddress.setState(State);
		oldAddress.setCountry(Country);
		oldAddress.setPinCode(pinCode);
        employee.setAddressList(addressList);
        return employeeDaoImpl.updateEmployee(employee);
    }
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean addNewAddress(int employeeId,
            int doorNumber, String streetName, String district,
            String State, String Country, int pinCode) {
        Employee employee
                = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<Address> address = employee.getAddressList();
        String addressType = "Secondary";
        Address newAddress = new Address (doorNumber, 
                streetName, district, State, Country, pinCode, addressType, false); 
        address.add(newAddress);
        employee.setAddressList(address);
        return employeeDaoImpl.addNewAddress(employee);
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean deleteOrRestoreEmployee(int employeeId) {
        Employee employee = employeeDaoImpl.getEmployeeDetails(employeeId);
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
        return employeeDaoImpl.updateEmployee(employee);
    }
 
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean checkProjectIdExists(int projectId) {
       ProjectService projectService 
               = new ProjectServiceImpl();
        return projectService.checkProjectIdExists(projectId);
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
	public List<Employee> getEmployeeForProject(List<Integer> employeeId) {
        List<Employee> requiredEmployees = new ArrayList<Employee>();
        List<Employee> employees = (employeeDaoImpl.getAllEmployeeDetails(false));
        for (Employee employee : employees) {			
            if (employeeId.contains(employee.getId())) {
                requiredEmployees.add(employee);
            }
        }
        return requiredEmployees;
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean assignProject(int employeeId, List projectsId) {
        ProjectService projectService = new ProjectServiceImpl();
        Employee employee = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<Project> employeeProjects = employee.getProjectList();
        List<Project> projects = projectService.getProjectsForEmployee(projectsId);
        employee.setProjectList(projects);	
        return employeeDaoImpl.updateEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<String>> employeeProjects(int employeeId) {
    	ProjectService projectService = new ProjectServiceImpl();
        Employee employee = employeeDaoImpl.getEmployeeDetails(employeeId);
        List<String> employeeAssignedProjects = new LinkedList<String>();
        List<Project> projects = employee.getProjectList();
        List<List<String>> allProjects = new LinkedList<List<String>>();
        allProjects = projectService.getProjects(false);
        List<String> allProjectsDetails = new ArrayList<String>();
        List<Integer> allProjectsId = new ArrayList<Integer>();
        List<List<String>> employeeProjectAssignedDetails = new LinkedList<List<String>>();
        if (null == projects) {
            employeeAssignedProjects = null;
        } else {
            for(Project project : projects) {
                employeeAssignedProjects.add(String.valueOf(project.getId()));
                employeeAssignedProjects.add((project.getProjectName()));
                allProjectsId.add(project.getId());
            }
        }
        for(List<String> project : allProjects) {
        	int projectId = Integer.parseInt(project.get(0));
        	if(!(allProjectsId.contains(projectId))) {
        		allProjectsDetails.add(project.get(0));
        		allProjectsDetails.add(project.get(1));
        	}
        }
        employeeProjectAssignedDetails.add(employeeAssignedProjects);
        employeeProjectAssignedDetails.add(allProjectsDetails);
        return employeeProjectAssignedDetails;
    }
}
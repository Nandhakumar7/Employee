package com.ideas2it.employeeManagementSystem.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.employeeManagementSystem.employeeManagement.model.Address;
import com.ideas2it.employeeManagementSystem.employeeManagement.model.Employee;
import com.ideas2it.employeeManagementSystem.employeeManagement.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.employeeManagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.exception.EmployeeManagementException;

/**
 * EmployeeController for doing CRUD operation.
 * 
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
@Controller    
public class EmployeeController {  
	EmployeeService employeeService = new EmployeeServiceImpl();
    
	/**
	 * call Employee create Form.
	 * 
     * @param model   to send model form to create employee.
     * 
	 * @return String viewPage name.
     */
    @RequestMapping("/addEmployee")    
    public String showform(Model model) { 
        model.addAttribute("command", new Employee());  
        return "addEmployee";   
    } 
    
    /**
	 * call editEmployeeForm.
	 * 
     * @param model   to send model form to update employee.
     * 
	 * @return String viewPage name.
     */
    @RequestMapping("/editEmployeeForm")    
    public ModelAndView showEditEmployeeForm(@RequestParam int id) { 
    	ModelAndView modelAndView = new ModelAndView(); 
    	try {
			Employee employee = employeeService.getEmployeeDetails(id);
			modelAndView.addObject("command", employee);
			modelAndView.setViewName("editEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		    modelAndView.setViewName("messagePrint");
		}
        return modelAndView;   
    }
    
	/**
	 * call addAddress create Form.
	 * 
     * @param model   to send model form to create Address
     * 
	 * @return String viewPage name.
     */
    @RequestMapping("/addAddress")    
    public ModelAndView addAddressForm(@RequestParam int id) {
    	ModelAndView modelAndView = new ModelAndView(); 
    	modelAndView.addObject("command", new Address()); 
    	modelAndView.addObject("employeeId", id);
    	modelAndView.addObject("choosedAddress", 0);
    	modelAndView.setViewName("editAddress");
        return modelAndView;   
    }
    
    /**
	 * update employeedetails.
	 * 
     * @param model   to send model form to edit Address
     * 
	 * @return String viewPage name.
     */
    @RequestMapping("/updateEmployee")    
    public ModelAndView updateEmployee(@ModelAttribute Employee updatedEmployee) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
    		employeeService.updateEmployee(updatedEmployee);
    		Employee employee = employeeService.getEmployeeDetails(updatedEmployee.getId());
			modelAndView.addObject("employee", employee);
	    	modelAndView.setViewName("showEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		    modelAndView.setViewName("messagePrint");
		} 
        return modelAndView;   
    }
    
    /**
	 * call editAddress Form.
	 * 
     * @param model   to send model form to edit Address
     * 
	 * @return String viewPage name.
     */
    @RequestMapping("/editAddress")    
    public ModelAndView editAddress(@RequestParam("choosedAddress")int addressCount, 
    		@RequestParam("id")int id, @RequestParam("choosedAddressId")int addressId ) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
			modelAndView.addObject("command", employeeService.getEmployeeAddress(id, addressId));
			modelAndView.addObject("employeeId", id);
			modelAndView.addObject("choosedAddress", addressCount);
	    	modelAndView.setViewName("editAddress");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		    modelAndView.setViewName("messagePrint");
		} 
        return modelAndView;   
    }
    
    /**
	 * add new address to employee
	 * 
     * @param address   address details to add
     * @param id     to add address to specific employee
     * 
	 * @return ModelAndView contains details to user view.
     */
    @RequestMapping("/saveAddress")    
    public ModelAndView saveNewAddress(@RequestParam("employeeId") int id,
    		@ModelAttribute Address address, @RequestParam("choosedAddress") 
            int count) {
    	ModelAndView modelAndView = new ModelAndView(); 
    	try {
    		if(0 == address.getId()) {
			    employeeService.addNewAddress(id, address);
    		} else {
    			employeeService.updateAddress(count, address, id);
    		}
    		Employee employee = employeeService.getEmployeeDetails(id);
    		modelAndView.addObject("employee", employee);
    	    modelAndView.setViewName("showEmployee");
    	} catch (EmployeeManagementException e) {
		    modelAndView.addObject("message", e.getMessage());
		    modelAndView.setViewName("messagePrint");
    	}
        return modelAndView;  
    }
    
	/**
	 * send allEmployee Details to user View
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
	@RequestMapping("/showALLEmployee")   
    public ModelAndView showAllProject() { 
    	List<Employee> allEmployees = null;
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
			allEmployees = employeeService.getAllEmployees(false);
			modelAndView.addObject("allEmployees", allEmployees);
			modelAndView.addObject("message", "DeleteEmployee");
			modelAndView.setViewName("restoreEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;
    }
    
	/**
	 * to delete Employee address from database
	 * 
     * @param choosedAddress  to delete specific employee address  Details.
     * @param id     to delete specific employee address.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/DeleteAddress")    
    public ModelAndView deleteEmployeeAddress(@RequestParam("employeeId") int employeeId,
    		@RequestParam("choosedAddress") int choosedAddress) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			employeeService.deleteEmployeeAddress(choosedAddress, employeeId);
	    	Employee employee = employeeService.getEmployeeDetails(employeeId);
	    	modelAndView.addObject("employee", employee);
	    	modelAndView.setViewName("showEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;  
    }
    
    /**
	 * to update address as primary 
	 * 
     * @param choosedAddress  to update specific employee address  Details.
     * @param id     to update specific employee address.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/updateAsPrimaryAddress")    
    public ModelAndView updateAddressAsPrimary(@RequestParam("id") int employeeId,
    		@RequestParam("choosedAddress") int choosedAddress) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			employeeService.updateAsPrimaryAddress(choosedAddress, employeeId);
	    	Employee employee = employeeService.getEmployeeDetails(employeeId);
	    	modelAndView.addObject("employee", employee);
	    	modelAndView.setViewName("showEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;  
    }
    
    
	/**
	 * get Specific Employee details and send to userView.
	 * 
     * @param id   to get specific Employee details.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/showEmployee")    
    public ModelAndView getProject(@RequestParam("id") int id) {
    	Employee employee;
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
			if( !(employeeService.checkEmployeeIdExists(id))) {
			    employee = employeeService.getEmployeeDetails(id);
			    modelAndView.addObject("employee", employee);
			    modelAndView.setViewName("showEmployee");
			} else {
				modelAndView.addObject("message", "Employee Id NotExists");
				modelAndView.setViewName("messagePrint");
			}
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;   
    }
    
	/**
	 * Delete specific Employee from database.
	 * 
     * @param id  to delete specific Employee.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/DeleteEmployee" )    
    public ModelAndView DeleteProject(@RequestParam("id") int id) { 
    	ModelAndView modelAndView = new ModelAndView(); 
    	String message = null;
		try {
			employeeService.deleteOrRestoreEmployee(id);
			message = "DELETED SUCESSFULLY";
			modelAndView.addObject("message", message);
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("messagePrint");
		return modelAndView;
    }
    
	/**
	 * restore specific Employee from database.
	 * 
     * @param id  to restore specific employee.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/restoreEmployee" )    
    public ModelAndView restoreProject(@RequestParam("id") int id) { 
    	ModelAndView modelAndView = new ModelAndView(); 
    	String message = null;
		try {
			employeeService.deleteOrRestoreEmployee(id);
			message = "Restored SUCESSFULLY";
			modelAndView.addObject("message", message);
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("messagePrint");
        return modelAndView;
    }
    
	/**
	 * get All deleted Employees from database and send
	 * to userView.
	 * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/showAllDeletedEmployees")    
    public ModelAndView showAllDeletedProject() { 
    	List<Employee> allEmployees = null;
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
			allEmployees = employeeService.getAllEmployees(true);
			modelAndView.addObject("allEmployees", allEmployees);
			modelAndView.addObject("message", "restoreEmployee");
			modelAndView.setViewName("restoreEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;
    }
    
	/**
	 * call EmployeeHomePage.
	 * 
	 * @return String viewPage name.
     */
    @RequestMapping("/EmployeeHomePage")    
    public String callProjectHome() { 
        return "employeeHomePage";   
    }
    
    /**
	 * call showEmployeePage.
     * 
	 * @return String viewPage name.
     */
    @RequestMapping("/showEmployeePage")    
    public String callviewProjectPage() {
        return "showEmployee";   
    }
    
	/**
	 * Assign Employees to Projects.
	 * 
     * @param employeeId  Employees Id to assign project.
     * @param projectsId   to assign projects to employee.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/assignProject")    
    public ModelAndView assignEmployee(@RequestParam("projectId")String[] checkboxValue,
    		@RequestParam("employeeId")String[] employeeId) { 
    	ModelAndView modelAndView = new ModelAndView();
    	try {
		    List<Integer> employeesId = new ArrayList<Integer>();
		    int id = Integer.parseInt(employeeId[0]);
		    if(null != checkboxValue) {
		        for(int i = 1; i < checkboxValue.length; i ++) {
			        employeesId.add(Integer.parseInt(checkboxValue[i]));
		        }
		    }
		    employeeService.assignProject(id,employeesId);
		    Employee employee = employeeService.getEmployeeDetails(id);
			modelAndView.addObject("employee", employee);
			modelAndView.setViewName("showEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;
    }
    
	/**
	 * get Assignedprojects and unAssignedprojects
	 * list and send to userView.
	 * 
     * @param id  to get specific employee project  Details.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping("/getEmployeeProjects")    
    public ModelAndView getProjectEmployees(@RequestParam int id) {
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
	    	Employee employee = employeeService.getEmployeeDetails(id);
	    	modelAndView.addObject("employee", employee);
	    	modelAndView.addObject
	    	        ("projectEmployees", employeeService.employeeProjects(id));
	    	modelAndView.setViewName("showEmployee");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;  
    }
    
	/**
	 * To Cretae new Employee.
	 * 
     * @param employee  to create new Employee.
     * 
     * @return model  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)    
    public String saveProject(@ModelAttribute("emp") Employee employee, Model model) {
    	String message = null;
    	try {
            int employeId = employeeService.addEmployeeDetails(employee);
            message = "Your ProjectId is :" + employeId;
            model.addAttribute("message", message);
    	} catch(EmployeeManagementException e) {
    		model.addAttribute("message", e.getMessage());
    	}
        return "messagePrint";
    }
}
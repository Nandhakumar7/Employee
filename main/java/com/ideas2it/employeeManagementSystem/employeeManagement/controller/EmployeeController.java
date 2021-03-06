package com.ideas2it.employeeManagementSystem.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;

/**
 * EmployeeController for doing CRUD operation.
 * 
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
@Controller    
public class EmployeeController {  
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
    
	/**
	 * call Employee create Form.
	 * 
     * @param model   to send model form to create employee.
     * 
	 * @return String viewPage name.
     */
    @RequestMapping(value="/addEmployee", method = RequestMethod.GET)        
    public String showform(Model model) { 
        model.addAttribute("employee", new Employee());  
        return "addEmployee";   
    } 
    
    /**
	 * call editEmployeeForm.
	 * 
     * @param model   to send model form to update employee.
     * 
	 * @return ModelAndView model and viewpage name.
     */
    @RequestMapping(value="/editEmployeeForm", method = RequestMethod.GET)           
    public ModelAndView showEditEmployeeForm(@RequestParam int id) { 
        ModelAndView modelAndView = new ModelAndView(); 
    	try {
            Employee employee = employeeService.getEmployeeDetails(id);
            modelAndView.addObject("employee", employee);
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
    @RequestMapping(value="/addAddress" , method = RequestMethod.GET)    
    public ModelAndView addAddressForm(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(); 
        modelAndView.addObject("address", new Address()); 
        modelAndView.addObject("employeeId", id);
        modelAndView.addObject("choosedAddress", 0);
        modelAndView.setViewName("editAddress");
        return modelAndView;   
    }
    
    /**
	 * get details from user and update.
	 * 
     * @param model   to send model form to edit Address
     * 
	 * @return String viewPage name.
     */
    @RequestMapping(value="/updateEmployee", method = RequestMethod.POST)           
    public ModelAndView updateEmployee
            (@ModelAttribute Employee updatedEmployee) {
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
    @RequestMapping(value="/editAddress" , method = RequestMethod.GET)
    public ModelAndView editAddress(@RequestParam("choosedAddress")int addressCount, 
    		@RequestParam("id")int id, @RequestParam("choosedAddressId")int addressId ) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
			modelAndView.addObject("address", employeeService.getEmployeeAddress(id, addressId));
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
    @RequestMapping(value="/saveAddress", method = RequestMethod.POST)           
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
	@RequestMapping(value="/showALLEmployee", method = RequestMethod.GET)           
    public ModelAndView showAllProject() { 
    	List<Employee> allEmployees = null;
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
			allEmployees = employeeService.getAllEmployees(false);
			modelAndView.addObject("allEmployees", allEmployees);
			modelAndView.addObject("message", "showEmployee");
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
    @RequestMapping(value="/DeleteAddress", method = RequestMethod.GET)           
    public ModelAndView deleteEmployeeAddress(@RequestParam("employeeId") int employeeId,
    		@RequestParam("choosedAddress") int choosedAddress) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			if(employeeService.deleteEmployeeAddress(choosedAddress, employeeId)) {
	    	    Employee employee = employeeService.getEmployeeDetails(employeeId);
	    	    modelAndView.addObject("employee", employee);
	    	    modelAndView.setViewName("showEmployee");
			} else {
				modelAndView.addObject("message","PRIMARY ADDRESS CAN'T ABLE TO DELETE"
						+ "PLEASE ADD OTHER ADDRESS AS A PRIMARY BEFOR DELETE");
				modelAndView.setViewName("messagePrint");
			}
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
    @RequestMapping(value="/updateAsPrimaryAddress", method = RequestMethod.GET)           
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
    @RequestMapping(value="/showEmployee", method = RequestMethod.GET)            
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
    @RequestMapping(value="/DeleteEmployee", method = RequestMethod.GET)           
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
    @RequestMapping(value="/restoreEmployee", method = RequestMethod.GET)           
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
    @RequestMapping(value="/showAllDeletedEmployees", method = RequestMethod.GET)           
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
    @RequestMapping(value="/EmployeeHomePage", method = RequestMethod.GET)            
    public String callProjectHome() { 
        return "employeeHomePage";   
    }
    
    /**
	 * call showEmployeePage.
     * 
	 * @return String viewPage name.
     */
    @RequestMapping(value="/showEmployeePage", method = RequestMethod.GET)           
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
    @RequestMapping(value="/assignProject", method = RequestMethod.POST)           
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
    @RequestMapping(value="/getEmployeeProjects", method = RequestMethod.GET)           
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
    public String saveProject(@ModelAttribute Employee employee, Model model) {
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
package com.ideas2it.employeeManagementSystem.projectManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.employeeManagementSystem.projectManagement.model.Project;
import com.ideas2it.employeeManagementSystem.projectManagement.service.ProjectService;
import com.ideas2it.employeeManagementSystem.projectManagement.service.impl.ProjectServiceImpl;
import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;
    

/**
 * ProjectController for doing CRUD operation.
 * 
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
@Controller    
public class controllerProject {  
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	ProjectService projectService = context.getBean("projectService", ProjectService.class);
	
	/**
	 * call Project create Form.
	 * 
     * @param model   to send model form to create Project
     * 
	 * @return String viewPage name.
     */
    @RequestMapping(value="/addProject", method = RequestMethod.GET)  
    public String showform(Model model) { 
        model.addAttribute("project", new Project());  
        return "addProject";   
    } 
    
	/**
	 * call Project update Form.
	 * 
     * @param model  send Exists project model to edit.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/editProject", method = RequestMethod.GET)   
    public ModelAndView editProjectForm(@RequestParam int id) {
        Project project = null;
        ModelAndView modelAndView = new ModelAndView(); 
		try {
			project = projectService.getProjectDetails(id);
			modelAndView.addObject("project", project);
			modelAndView.setViewName("addProject");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;   
    } 
   
	/**
	 * send allProject Details to user View
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
	@RequestMapping(value="/showAllProject", method = RequestMethod.GET)   
    public ModelAndView showAllProject() { 
    	List<Project> allProjects = null;
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
			allProjects = projectService.getProjects(false);
			modelAndView.addObject("allProjects", allProjects);
			modelAndView.addObject("message", "showProject");
			modelAndView.setViewName("restoreProject");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;
    }
    
	/**
	 * get Specific project details and send to userView.
	 * 
     * @param id   to get specific project details.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/showProject", method = RequestMethod.GET)      
    public ModelAndView getProject(@RequestParam("id") int id) {
    	Project project;
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
			if( !(projectService.checkProjectIdExists(id))) {
			    project = projectService.getProjectDetails(id);
			    modelAndView.addObject("project", project);
			    modelAndView.setViewName("view");
			} else {
				modelAndView.addObject("message", "Project Id NotExists");
				modelAndView.setViewName("messagePrint");
			}
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;   
    }
    
	/**
	 * Delete specific project from database.
	 * 
     * @param id  to delete specific project.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/DeleteProject", method = RequestMethod.GET)       
    public ModelAndView DeleteProject(@RequestParam("id") int id) { 
    	ModelAndView modelAndView = new ModelAndView(); 
    	String message = null;
		try {
			projectService.deleteOrRestoreProjectDetails(id);
			message = "DELETED SUCESSFULLY";
			modelAndView.addObject("message", message);
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("messagePrint");
		return modelAndView;
    }
    
	/**
	 * restore specific project from database.
	 * 
     * @param id  to restore specific project.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/restoreProject", method = RequestMethod.GET)      
    public ModelAndView restoreProject(@RequestParam("id") int id) { 
    	ModelAndView modelAndView = new ModelAndView(); 
    	String message = null;
		try {
			projectService.deleteOrRestoreProjectDetails(id);
			message = "Restored SUCESSFULLY";
			modelAndView.addObject("message", message);
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("messagePrint");
        return modelAndView;
    }
    
	/**
	 * get All deleted projects from database and send
	 * to userView.
	 * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/showAllDeletedProject", method = RequestMethod.GET)       
    public ModelAndView showAllDeletedProject() { 
    	List<Project> allProjects = null;
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
			allProjects = projectService.getProjects(true);
			modelAndView.addObject("allProjects", allProjects);
			modelAndView.addObject("message", "restoreProject");
			modelAndView.setViewName("restoreProject");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;
    }
    
	/**
	 * call ProjectHomePage.
	 * 
	 * @return String viewPage name.
     */
    @RequestMapping(value="/projectHome", method = RequestMethod.GET)       
    public String callProjectHome() { 
        return "projectHomePage";   
    }
    
    /**
	 * call mainPage.
     * 
	 * @return String viewPage name.
     */
    @RequestMapping("/")       
    public String callMain() { 
        return "index";   
    }
    
    /**
	 * call viewProjectPage.
     * 
	 * @return String viewPage name.
     */
    @RequestMapping(value="/viewProjectPage", method = RequestMethod.GET)       
    public String callviewProjectPage() {
        return "view";   
    }
    
	/**
	 * Assign Employees to Projects.
	 * 
     * @param employeeId  Employees Id to assign project.
     * @param projectId   to assign that project
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/assignEmployee", method = RequestMethod.POST)      
    public ModelAndView assignEmployee(@RequestParam("employeeId")String[] checkboxValue,
    		@RequestParam("projectId")String[] projectId) { 
    	ModelAndView modelAndView = new ModelAndView();
    	try {
		    List<Integer> employeesId = new ArrayList<Integer>();
		    int id = Integer.parseInt(projectId[0]);
		    if(null != checkboxValue) {
		        for(int i = 1; i < checkboxValue.length; i ++) {
			        employeesId.add(Integer.parseInt(checkboxValue[i]));
		        }
		    }
		    projectService.assignProject(id,employeesId);
		    Project project = projectService.getProjectDetails(id);
			modelAndView.addObject("project", project);
			modelAndView.setViewName("view");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;
    }
    
	/**
	 * get AssignedEmployees and unAssignedemployees
	 * list and send to userView.
	 * 
     * @param id  to get specific project Employees Details.
     * 
     * @return ModelAndView  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/getProjectEmployeesList", method = RequestMethod.GET)      
    public ModelAndView getProjectEmployees(@RequestParam int id) {
    	ModelAndView modelAndView = new ModelAndView(); 
		try {
	    	Project project = projectService.getProjectDetails(id);
	    	modelAndView.addObject("project", project);
	    	modelAndView.addObject
	    	        ("projectEmployees", projectService.getProjectEmployees(id));
	    	modelAndView.setViewName("view");
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("messagePrint");
		}
        return modelAndView;  
    }
    
	/**
	 * To Cretae new Project or Update Existing Project.
	 * 
     * @param project  to create or update project.
     * 
     * @return model  Contains details for userView and viewPage Details.
     */
    @RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)    
    public String saveProject(@ModelAttribute("emp") Project project, Model model) {
    	String message = null;
    	try {
    	    if(0 == project.getId()) {
            int projectId = projectService.addProject(project);
            message = "Your ProjectId is :" + projectId;
    	    } else {
    	    projectService.updateProject(project);
    	    message = "updated Sucessfully";
    	    }
            model.addAttribute("message", message);
    	} catch(EmployeeManagementException e) {
    		model.addAttribute("message", e.getMessage());
    	}
        return "messagePrint";
    }
}
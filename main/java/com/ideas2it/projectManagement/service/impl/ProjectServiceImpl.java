package com.ideas2it.projectManagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ideas2it.projectManagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.projectManagement.model.Project;
import com.ideas2it.employeeManagement.service.EmployeeService;
import com.ideas2it.employeeManagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.projectManagement.service.ProjectService;

/**
 * ServiceImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectServiceImpl implements ProjectService{
    private ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public int addProject(String projectName,
            String projectManager, String department,
            int timePeriod) {
        Project project = new Project (projectName, 
     	        projectManager, department, timePeriod);
        int projectId = projectDaoImpl.insertProjectDetails(project);	
        return projectId;
    }
			
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getProjectDetails(int projectId) {
        Project project = projectDaoImpl.getProjectDetails(projectId);
        List<String> projectDetails = new LinkedList<String>();
        List<Employee> employees = new ArrayList<Employee>();
        employees = project.getEmployeesList();
        Integer id = project.getId();
        Integer timePeriod = project.getTimePeriod();
        projectDetails.add(id.toString());
        projectDetails.add(project.getProjectName());
        projectDetails.add(project.getManagerName());
        projectDetails.add(project.getDepartment());
        projectDetails.add(timePeriod.toString());
        if(null != employees) {
            for(Employee employee : employees) {	
                Integer employeeId = employee.getId();
                projectDetails.add(employeeId.toString());
                projectDetails.add(employee.getName());
            } 	
        } else {
            projectDetails.add("\n" + "NO Employee Assigned ");
        }
        return projectDetails;
    }
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean checkProjectIdExists(int projectId){
        return(projectDaoImpl.checkProjectIdExists(projectId));
    }

    /**
     * {@inheritDoc}
     */ 
	@Override
    public List<List<String>> getProjects(boolean isDeleted) {
        List<Project> allProject = projectDaoImpl.getAllProject(isDeleted);
        List<List<String>> projects = new LinkedList<List<String>>();
        for(Project project : allProject) {
        	List<String> projectDetails = new LinkedList<String>();
        	projectDetails.add(String.valueOf(project.getId())); 
        	projectDetails.add(project.getProjectName());
        	projectDetails.add(project.getManagerName());
        	projectDetails.add(project.getDepartment());
        	projectDetails.add(String.valueOf(project.getTimePeriod())); 
        	projects.add(projectDetails);
        }
        return projects;
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean updateProject(int projectId, String projectName, String managerName,
            String department, int timePeriod) {
        Project project = projectDaoImpl.getProjectDetails(projectId);
        String newProjectName
                = (null == projectName) ? project.getProjectName() : projectName;
        String newManagerName
                = (null == managerName) ? project.getManagerName() : managerName;
        String newDepartment
                = (null == department) ? project.getDepartment() : department;
        int newTimePeriod
                = (0 == timePeriod) ? project.getTimePeriod() : timePeriod;
        project.setProjectName(newProjectName);
        project.setManagerName(newManagerName);
        project.setDepartment(newDepartment);
        project.setTimePeriod(newTimePeriod);
        return projectDaoImpl.updateProject(project);
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean assignProject(int projectId, List<Integer> employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDaoImpl.getProjectDetails(projectId);
        List<Employee> projectEmployees = project.getEmployeesList();
        List<Employee> employees 
                = employeeService.getEmployeeForProject(employeeId);
        project.setEmployeesList(employees);
        return projectDaoImpl.updateProject(project);
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public List<Project> getProjectsForEmployee(List<Integer> projectsId) {
        List<Project> requiredProjects = new ArrayList<Project>();
        List<Project> projects = projectDaoImpl.getAllProject(false);
        for (Project project : projects) {			
            if (projectsId.contains(project.getId())) {
                requiredProjects.add(project);
            }
        }
        return requiredProjects;
    }
	
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean deleteOrRestoreProjectDetails(int projectId) {
        Project project = projectDaoImpl.getProjectDetails(projectId);
        if(project.getIsDeleted()) {
        	project.setIsDeleted(false);
        } else {
        	project.setIsDeleted(true);
        }
        project.setEmployeesList(null);
        return projectDaoImpl.updateProject(project);
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override  
    public boolean checkEmployeeIdExists(int employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();		
        return employeeService.checkEmployeeIdExists(employeeId);
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public List<List<String>> getProjectEmployees(int projectId) {
    	EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDaoImpl.getProjectDetails(projectId);
        List<String> projectAssignedEmployees = new LinkedList<String>();
        List<Employee> employees = project.getEmployeesList();
        List<String> allEmployeesId = new ArrayList<String>();
        List<Integer> assignedEmployeesId = new ArrayList<Integer>();
        List<List<String>> allEmployees = employeeService.getAllEmployee(false); 
        List<List<String>> employeeProjectAssignedDetails = new LinkedList<List<String>>();
        if (null == employees) {
            projectAssignedEmployees = null;
        } else {
            for(Employee employee : employees) {
                projectAssignedEmployees.add(String.valueOf(employee.getId()));
                projectAssignedEmployees.add(employee.getName());
                assignedEmployeesId.add(employee.getId());
            }
        }
        for(List<String> employee : allEmployees) {
        	int employeeId = Integer.parseInt(employee.get(0));
        	if(!(assignedEmployeesId.contains(employeeId))) {
        		allEmployeesId.add(employee.get(0));
        		allEmployeesId.add(employee.get(1));
        	}
        }
        employeeProjectAssignedDetails.add(projectAssignedEmployees);
        employeeProjectAssignedDetails.add(allEmployeesId);
        return employeeProjectAssignedDetails;
    }
}
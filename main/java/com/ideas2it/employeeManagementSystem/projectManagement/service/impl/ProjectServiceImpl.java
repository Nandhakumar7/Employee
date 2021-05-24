package com.ideas2it.employeeManagementSystem.projectManagement.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ideas2it.employeeManagementSystem.projectManagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.projectManagement.dao.ProjectDao;
import com.ideas2it.employeeManagementSystem.employeeManagement.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.employeeManagement.model.Employee;
import com.ideas2it.employeeManagementSystem.projectManagement.model.Project;
import com.ideas2it.employeeManagementSystem.employeeManagement.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.employeeManagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;
import com.ideas2it.employeeManagementSystem.projectManagement.service.ProjectService;

/**
 * ServiceImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;
    private EmployeeService employeeService;
    private int test;
    public ProjectServiceImpl(ProjectDao projectDao) {
    	this.projectDao = projectDao;
    }
    public void setEmployeeService(EmployeeService employeeService) {
        System.out.println("Inside setSpellChecker." );
        this.employeeService = employeeService;
    }
    
    /**
     * {@inheritDoc}
     * @throws Exception 
     */
    @Override
    public int addProject(Project project) throws EmployeeManagementException{
        int projectId = projectDao.insertProjectDetails(project);	
        return projectId;
    }
			
    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProjectDetails(int projectId) 
            throws EmployeeManagementException {
        Project project = projectDao.getProjectDetails(projectId);
        return project;
    }
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean checkProjectIdExists(int projectId) 
    		throws EmployeeManagementException  {
        return(projectDao.checkProjectIdExists(projectId));
    }

    /**
     * {@inheritDoc}
     */ 
	@Override
    public List<Project> getProjects(boolean isDeleted) 
            throws EmployeeManagementException {
        List<Project> allProject = projectDao.getAllProject(isDeleted);
        return allProject;
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public void updateProject(Project project) throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(ProjectServiceImpl.class);
    	Project oldProject = null;
    	try {
    		oldProject = projectDao.getProjectDetails(project.getId());
            String newProjectName
                    = (null == project.getProjectName()) ? project.getProjectName() : project.getProjectName();
            String newManagerName
                    = (null == project.getManagerName()) ? project.getManagerName() : project.getManagerName();
            String newDepartment
                    = (null == project.getDepartment()) ? project.getDepartment() : project.getDepartment();
            int newTimePeriod
                    = (0 == project.getTimePeriod()) ? project.getTimePeriod() : project.getTimePeriod();
            oldProject.setProjectName(newProjectName);
            oldProject.setManagerName(newManagerName);
            oldProject.setDepartment(newDepartment);
            oldProject.setTimePeriod(newTimePeriod);
            projectDao.updateProject(oldProject);
    	} catch (EmployeeManagementException e) {
    		log.logError("Failed to update Project Id is" + project.getId(), e);
        	throw new EmployeeManagementException("Employee Update Failure");
    	}
    }
	
    /**
     * {@inheritDoc} 
     */ 
    @Override
    public void assignProject(int projectId, List<Integer> employeeId) 
            throws EmployeeManagementException {
    	Project project = null;
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(ProjectServiceImpl.class);
    	try {
    		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    		//EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
           // EmployeeService employeeService = new EmployeeServiceImpl();
            project = projectDao.getProjectDetails(projectId);
            List<Employee> projectEmployees = project.getEmployeesList();
            List<Employee> employees 
                    = employeeService.getEmployeeForProject(employeeId);
            project.setEmployeesList(employees);
            projectDao.updateProject(project);
    	} catch (EmployeeManagementException e) {
    		log.logError("Failed to Assigning Employees,Project Id is " + projectId, e);
        	throw new EmployeeManagementException("Employee Assigning/UnAssigning Failure");
    	} 
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public List<Project> getProjectsForEmployee(List<Integer> projectsId) 
	        throws EmployeeManagementException {
    	List<Project> requiredProjects = new ArrayList<Project>();
    	try {
            List<Project> projects = projectDao.getAllProject(false);
            for (Project project : projects) {			
                if (projectsId.contains(project.getId())) {
                    requiredProjects.add(project);
                }
            }
    	} catch (EmployeeManagementException e) {
            throw new EmployeeManagementException
                    ("SomeThing Went Wrong Try Again!");
        } 
        return requiredProjects;
    }
	
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public void deleteOrRestoreProjectDetails(int projectId) 
             throws EmployeeManagementException {
    	EmployeeManagementLogger log 
                = new EmployeeManagementLogger(ProjectServiceImpl.class);
    	Project project = null;
    	try {
            project = projectDao.getProjectDetails(projectId);
            if(project.getIsDeleted()) {
        	    project.setIsDeleted(false);
            } else {
        	    project.setIsDeleted(true);
            }
            project.setEmployeesList(null);
            projectDao.updateProject(project);
    	}  catch (EmployeeManagementException e) {
    		log.logError("Failed to Delete or Restore " + projectId, e);
            throw new EmployeeManagementException("Fail To Delete");
        }  
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override  
    public boolean checkEmployeeIdExists(int employeeId) 
    		throws EmployeeManagementException {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    	EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);	
        return employeeService.checkEmployeeIdExists(employeeId);
    }

    /**
     * {@inheritDoc}
     * @throws EmployeeManagementException 
     */ 
    @Override
    public List<List> getProjectEmployees(int projectId) 
            throws EmployeeManagementException {
    	List<List> employeeProjectAssignedDetails = new LinkedList<List>();
    	try { 
    		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    		//EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
    	   /// EmployeeService employeeService = new EmployeeServiceImpl();
            Project project = projectDao.getProjectDetails(projectId);
            List<Employee> employees = project.getEmployeesList();
            List<Integer> assignedEmployeesId = new ArrayList<Integer>();
            List<Employee> allEmployees = employeeService.getAllEmployees(false); 
            List<Employee> unAssignedEmployees = new ArrayList<Employee>();
            if (null == employees) {
            	assignedEmployeesId = null;
            } else {
                for(Employee employee : employees) {
                    assignedEmployeesId.add(employee.getId());
                }
            }
            for(Employee employee : allEmployees) {
        	    if(!(assignedEmployeesId.contains(employee.getId()))) {
        	    	unAssignedEmployees.add(employee); 
        	    }
            }
            employeeProjectAssignedDetails.add(employees);
            employeeProjectAssignedDetails.add(unAssignedEmployees);
    	}  catch (EmployeeManagementException e) {
            throw new EmployeeManagementException
                    ("SomeThing Went Wrong Try Again!");
        }
        return employeeProjectAssignedDetails;
    }
}
package com.ideas2it.employeeManagementSystem.projectManagement.service;

import java.util.List;
import java.util.Map;

import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.projectManagement.model.Project;

/**
 * Service get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public interface ProjectService {

    /**
     * project Details are get from user and,
     * insert details to database.
     * 
     * @param project   contains Project details to create
     *
     * @return isProjectAdded true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public int addProject(Project project) throws EmployeeManagementException;
			
    /**
     * Here ProjectID  get from user and,
     * EmployeeDetails are get from database.
     *
     * @param projectId    projectId to get that specific project
     * details from  database.
     *
     * @return project   project Details for user view. 
     * @throws EmployeeManagementException 
     */
    public Project getProjectDetails(int projectId) throws EmployeeManagementException;
    
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
     * project Details are get from user and,
     * update project details to database.
     *
     * @param project   contains details to update.
     *
     * @return isUpdated true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public void updateProject(Project project) throws EmployeeManagementException;
	
    /**
     * projectId and employeeId are get from user and,
     * assign project for that employees.
     *
     * @param projectId  for assign that specific project.
     * @employeesId       it contains employees to assign.	 
     * 
     * @return isAssigned return true when sucessfully assigned or return false
     * @throws EmployeeManagementException 
     */
    public void assignProject(int projectId, List<Integer> employeesId) throws EmployeeManagementException;

    /**
     * projectId are get from user and,
     * remove details from database.
     *
     * @param projectId  for remove that specific project. 
     * 
     * @return isDeleted return true when sucessfully deleted or return false
     * @throws EmployeeManagementException 
     */
    public void deleteOrRestoreProjectDetails(int projectId) throws EmployeeManagementException;
	
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
     * projectId get from user and,
     * get list employees already worked in that project.
     *
     * @param projectId  For getting specific project employees..
     *
     * @return List  It contains list of employees.
     * @throws EmployeeManagementException 
     */
    public List<List> getProjectEmployees(int projectId) throws EmployeeManagementException;

    /**
     * Get allProject details from database and send for user view.
     *
     * @return allProject  list contains all project.
     * @throws EmployeeManagementException 
     */
    public List<Project> getProjects(boolean isDeleted) throws EmployeeManagementException;

    /**
     * projectId get from user and,
     * get list employees already worked in that project.
     *
     * @param projectsId  For getting specific project details.
     *
     * @return List  It contains list of projects.
     * @throws EmployeeManagementException 
     */
    public List<Project> getProjectsForEmployee(List<Integer> projectsId) throws EmployeeManagementException;

}
package com.ideas2it.projectManagement.service;

import java.util.List;
import java.util.Map;

import com.ideas2it.projectManagement.model.Project;

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
     * @param projectId   Id is for unique for specific prject.       
     * @param managerName  manager of the project.
     * @param projectName  name of the project.
     * @param department  department of the project.
     * @param timePeriod   timeperiod for completing project.
     *
     * @return isProjectAdded true when sucessfully updated or return false.
     */
    public int addProject(String projectName, 
            String projectManager, String department,
            int timePeriod);
			
    /**
     * Here ProjectID  get from user and,
     * EmployeeDetails are get from database.
     *
     * @param projectId    projectId to get that specific project
     * details from  database.
     *
     * @return project   project Details for user view. 
     */
    public List<String> getProjectDetails(int projectId);
    
    /**
     * projectId get from user and,
     * Checking Id Already we have or not.
     *
     * @param projectId  For checking whether already we have or not.
     *
     * @return true when Employee ID already Registered or return false.
     */
    public boolean checkProjectIdExists(int projectId);
 
    /**
     * project Details are get from user and,
     * update project details to database.
     *
     * @param projectId   Id is for one which Details need to Change.       
     * @param managerName  new managerName for change.
     * @param projectName   new name for change.
     * @param department  new department for change.
     * @param timePeriod   new DateOfBirth for change.
     *
     * @return isUpdated true when sucessfully updated or return false.
     */
    public boolean updateProject(int projectId, String projectName,
             String managerName, String department, int timePeriod);
	
    /**
     * projectId and employeeId are get from user and,
     * assign project for that employees.
     *
     * @param projectId  for assign that specific project.
     * @employeesId       it contains employees to assign.	 
     * 
     * @return isAssigned return true when sucessfully assigned or return false
     */
    public boolean assignProject(int projectId, List<Integer> employeesId);

    /**
     * projectId are get from user and,
     * remove details from database.
     *
     * @param projectId  for remove that specific project. 
     * 
     * @return isDeleted return true when sucessfully deleted or return false
     */
    public boolean deleteOrRestoreProjectDetails(int projectId);
	
    /**
     * EmployeeId get from user and,
     * Checking Id Already we have or not.
     *
     * @param employeeId  For checking whether already we have or not.
     *
     * @return true when Employee ID already Registered or return false.
     */ 
    public boolean checkEmployeeIdExists(int employeeId);

    /**
     * projectId get from user and,
     * get list employees already worked in that project.
     *
     * @param projectId  For getting specific project employees..
     *
     * @return List  It contains list of employees.
     */
    public List<List<String>> getProjectEmployees(int projectId);

    /**
     * Get allProject details from database and send for user view.
     *
     * @return allProject  list contains all project.
     */
    public List<List<String>> getProjects(boolean isDeleted);

    /**
     * projectId get from user and,
     * get list employees already worked in that project.
     *
     * @param projectsId  For getting specific project details.
     *
     * @return List  It contains list of projects.
     */
    public List<Project> getProjectsForEmployee(List<Integer> projectsId);

}
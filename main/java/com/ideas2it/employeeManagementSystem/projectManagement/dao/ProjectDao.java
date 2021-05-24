package com.ideas2it.employeeManagementSystem.projectManagement.dao;

import java.util.List;

import org.hibernate.Session;

import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.projectManagement.model.Project;

/**
 * Dao get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public interface ProjectDao {
	
    /**
     * project Details are get from user and,
     * add Employee details to database.
     *
     * @param project   that contains all details of project.
     * @throws Exception 
     *
     * @isProjectAdded  return true when added sucessfully or return false. 
     */
    public int insertProjectDetails(Project project) throws EmployeeManagementException;
	
    /**
     * Here ProjectID  get from user and,
     * projectdetails are get from database.
     *
     * @param projectId    projectId to get that specific project
     * details from  database.
     *
     * @return project   project Details for user view. 
     * @throws EmployeeManagementException 
     */
    public Project getProjectDetails(int projectId) throws EmployeeManagementException;
  
    /**
     * project Details are get from user and,
     * update Employee details to database.
     *
     * @param project  it contains project details for update.       
     *
     * @return isUpdated true when sucessfully updated or return false.
     * @throws EmployeeManagementException 
     */
    public boolean updateProject(Project project) throws EmployeeManagementException;
	
    /**
     * projectId get from user and,
     * Checking Id Already we have or not.
     *
     * @param projectId  For checking whether already we have or not.
     *
     * @return true when projectId  already Registered or return false.
     * @throws EmployeeManagementException 
     */ 
    public boolean checkProjectIdExists(int projectId) throws EmployeeManagementException;

    /**
     * get all project details from database and 
     * give to user view	 
     * 
     * @return List It contains all project details.
     * @throws EmployeeManagementException 
     */
    public List<Project> getAllProject(boolean isDeleted) throws EmployeeManagementException;
    
    /**
     * used to close the session after complete work	 
     * 
     * @param session  to close that session
     */
    public void closeSeesion(Session session);
}
	  
















 
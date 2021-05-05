package com.ideas2it.projectManagement.dao;

import java.util.List;

import com.ideas2it.projectManagement.model.Project;

/**
 * Dao get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public interface Dao {
	
    /**
     * project Details are get from user and,
     * add Employee details to database.
     *
     * @param project   that contains all details of project.
     *
     * @isProjectAdded  return true when added sucessfully or return false. 
     */
    public int insertProjectDetails(Project project);
	
    /**
     * Here ProjectID  get from user and,
     * projectdetails are get from database.
     *
     * @param projectId    projectId to get that specific project
     * details from  database.
     *
     * @return project   project Details for user view. 
     */
    public Project getProjectDetails(int projectId);
  
    /**
     * project Details are get from user and,
     * update Employee details to database.
     *
     * @param project  it contains project details for update.       
     *
     * @return isUpdated true when sucessfully updated or return false.
     */
    public boolean updateProject(Project project);
	
    /**
     * projectId get from user and,
     * Checking Id Already we have or not.
     *
     * @param projectId  For checking whether already we have or not.
     *
     * @return true when projectId  already Registered or return false.
     */ 
    public boolean checkProjectIdExists(int projectId);

    /**
     * get all project details from database and 
     * give to user view	 
     * 
     * @return List It contains all project details.
     */
    public List<Project> getAllProject(boolean isDeleted);
}
	  
















 
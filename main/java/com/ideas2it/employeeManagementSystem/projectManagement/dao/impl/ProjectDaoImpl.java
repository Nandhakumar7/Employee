package com.ideas2it.employeeManagementSystem.projectManagement.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;
import com.ideas2it.employeeManagementSystem.projectManagement.dao.ProjectDao;
import com.ideas2it.employeeManagementSystem.projectManagement.model.Project;
import com.ideas2it.employeeManagementSystem.sessionFactory.DataBaseConnection;

/**
 * DaoImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectDaoImpl implements ProjectDao {
    private DataBaseConnection dataBaseConnection
            = DataBaseConnection.getInstance();
    private EmployeeManagementLogger log 
            = new EmployeeManagementLogger(ProjectDaoImpl.class);
	
    /**
     * {@inheritDoc}
     * @throws Exception 
     */
    @Override
    public int insertProjectDetails(Project project) 
            throws EmployeeManagementException {
        int projectId = 0;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
		    if(null != sessionFactory) {
                session = sessionFactory.openSession();
		        session.beginTransaction();
                projectId = (int)session.save(project);
		        session.getTransaction().commit();
		    }
        } catch(Exception e) {
        	log.logError("failed to create Project Name: "
                    + project.getProjectName(), e);
        	if(null != session) {
        	    session.getTransaction().rollback();
        	}
        	throw new EmployeeManagementException("Project Add failure");
        } finally {
        	closeSeesion(session);
        }
		return projectId;	
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProjectDetails(int projectId) 
    		throws EmployeeManagementException {
        Session session = null;
        Project project = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            if(null != sessionFactory) {
                session = sessionFactory.openSession();
                project = (Project) session.get(Project.class, projectId);
                project.getEmployeesList().size();
            }
        } catch(HibernateException e) {
        	project = null;
        	log.logError("Fail to get Details from database Id: "
        	        + projectId, e);
        	throw new EmployeeManagementException("Fail To get Details.Try Again.");
        } finally {
            closeSeesion(session);
        }			
        return project;
    }
	
	/**
     * {@inheritDoc}
     */
    @Override
    public void closeSeesion(Session session) {
        if(session != null) {
            session.close();
        }
	}

	/**
     * {@inheritDoc}
	 * @throws EmployeeManagementException 
     */
    @Override
    public boolean updateProject(Project project) 
    		throws EmployeeManagementException {
        boolean isUpdated = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
		    if(null != sessionFactory) {
                session = sessionFactory.openSession();
		        session.beginTransaction();
                session.update(project);
		        session.getTransaction().commit();
		    }
        } catch(HibernateException e) {
            isUpdated = false;
            if(null != session) {
        	    session.getTransaction().rollback();
        	}
            throw new EmployeeManagementException(e);
        } finally {
        	closeSeesion(session);
        }
        return isUpdated;		
    }
		
    /**
     * {@inheritDoc}
     */
    @Override	  
    public boolean checkProjectIdExists(int projectId)
    	    throws EmployeeManagementException {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            if(null != sessionFactory) {
                session = sessionFactory.openSession();
                session = sessionFactory.openSession();
                String selectQuery = "FROM Project WHERE id = :id AND isDeleted = false";
                Query query = session.createQuery(selectQuery);
                query.setParameter("id", projectId);
                List<Project> projects = query.list();
                if (0 == projects.size()) {
                    idExists = true;
			    }
            }
        } catch(HibernateException e) {
        	throw new EmployeeManagementException
        	        ("Failed to check Id please try again");
        } finally {
        	closeSeesion(session);
        }			
        return idExists;
    }
	
    /**
     * {@inheritDoc}
     */ 
	@Override
    public List<Project> getAllProject(boolean isDeleted) 
    		throws EmployeeManagementException {
        Session session = null;
        List<Project> projects = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            if(null != sessionFactory) {
                session = sessionFactory.openSession();
                String selectQuery = "FROM Project WHERE isDeleted = :isDeletedValue";
                Query query = session.createQuery(selectQuery);
                query.setParameter("isDeletedValue", isDeleted);
                projects = query.list();
            }
        } catch(HibernateException e) { 
            projects = null;
            log.logError("Fail to get all Details", e);
            throw new EmployeeManagementException("Fail To get Details.Try Again.");
        } finally {
        	closeSeesion(session);
        }			
        return projects;
    }
}
	  
















 
package com.ideas2it.projectManagement.dao.impl;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.ideas2it.projectManagement.dao.Dao;
import com.ideas2it.projectManagement.model.Project;
import com.ideas2it.sessionFactory.DataBaseConnection;

/**
 * DaoImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectDaoImpl implements Dao {
    private DataBaseConnection dataBaseConnection
            = DataBaseConnection.getInstance();
	
    /**
     * {@inheritDoc}
     */
    @Override
    public int insertProjectDetails(Project project) {
        int projectId = 0;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            projectId = (int)session.save(project);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return projectId;		
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProjectDetails(int projectId) {
        Session session = null;
        Project project = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            project = (Project) session.get(Project.class, projectId);
            project.getEmployeesList().size();
        } catch(HibernateException e) { 
            project = null;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return project;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(Project project) {
        boolean isUpdated = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.update(project);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            isUpdated = false;
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return isUpdated;		
    }
		
    /**
     * {@inheritDoc}
     */
    @Override	  
    public boolean checkProjectIdExists(int projectId) {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            session = sessionFactory.openSession();
            String selectQuery = "FROM Project WHERE id = :id AND isDeleted = false";
            Query query = session.createQuery(selectQuery);
            query.setParameter("id", projectId);
            List<Project> projects = query.list();
            System.out.println(projects);
            if (0 == projects.size()) {
                idExists = true;
			   }
        } catch(HibernateException e) {			
            idExists = true;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return idExists;
    }
	
    /**
     * {@inheritDoc}
     */ 
	@Override
    public List<Project> getAllProject(boolean isDeleted) {
        Session session = null;
        List<Project> projects = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            String selectQuery = "FROM Project WHERE isDeleted = :isDeletedValue";
            Query query = session.createQuery(selectQuery);
            query.setParameter("isDeletedValue", isDeleted);
            projects = query.list();
        } catch(HibernateException e) { 
            projects = null;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return projects;
    }
}
	  
















 
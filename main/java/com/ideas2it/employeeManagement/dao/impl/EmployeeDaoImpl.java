package com.ideas2it.employeeManagement.dao.impl;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.ideas2it.sessionFactory.DataBaseConnection;
import com.ideas2it.Exception.EmployeeManagementException;
import com.ideas2it.employeeManagement.dao.Dao;
import com.ideas2it.employeeManagement.model.Employee;

/**
 * DaoImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class EmployeeDaoImpl implements Dao {
    private DataBaseConnection dataBaseConnection
            = DataBaseConnection.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertEmployeeDetails(Employee employee) throws EmployeeManagementException {
        int employeeId = 0;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
		    employeeId = (int)session.save(employee);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            throw new EmployeeManagementException("Employee Add failure");
        } finally {
        	closeSeesion(session);
        }
        return employeeId;		
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean updateEmployee(Employee employee) throws EmployeeManagementException {
        boolean isUpdated = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.update(employee);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            throw new EmployeeManagementException(e);
        } finally {
        	closeSeesion(session);
        }
        return isUpdated;		
    }		
	
    /**
     * {@inheritDoc}
     * @throws EmployeeManagementException 
     */
   @Override
    public Employee getEmployeeDetails(int employeeId) throws EmployeeManagementException {
        Session session = null;
        Employee employee = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            employee = (Employee) session.get(Employee.class, employeeId);
            employee.getAddressList().size();
            employee.getProjectList().size();
        } catch(HibernateException e) { 
            employee = null;
            throw new EmployeeManagementException("Failed to get Details..Try Again..");
        } finally {
        	closeSeesion(session);
        }
        return employee;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addNewAddress(Employee employee) throws EmployeeManagementException {
        boolean isAddressAdded = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.saveOrUpdate(employee);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            isAddressAdded = false;
            throw new EmployeeManagementException("Address Adding Failure");
        } finally {
        	closeSeesion(session);
        }
        return isAddressAdded;
    }
	 
    /**
     * {@inheritDoc} 
     */ 
    @Override
    public List getAllEmployeeDetails(boolean isDeleted) throws EmployeeManagementException {
        Session session = null;
        List<Employee> employees = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            String selectQuery = "FROM Employee WHERE isDeleted = :isDeletedValue";
            Query query = session.createQuery(selectQuery);
            query.setParameter("isDeletedValue", isDeleted);
            employees = query.list();
        } catch(HibernateException e) { 
            employees = null;
            throw new EmployeeManagementException("Failed to get Details..Try Again..");
        } finally {
        	closeSeesion(session);
        }			
        return employees;
    } 
 
    /**
     * {@inheritDoc}
     */
    @Override	  
    public boolean checkEmployeeIdExists(int employeeId) {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            String selectQuery = "FROM Employee WHERE id = :id AND isDeleted = false";
            Query query = session.createQuery(selectQuery);
            query.setParameter("id", employeeId);
            List<Employee> employee = query.list();
            if (0 == employee.size()) {
                idExists = true;
            }
        } catch(HibernateException e) { 
            idExists = true;
        } finally {
        	closeSeesion(session);
        }			
        return idExists;
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

}
	  
















 
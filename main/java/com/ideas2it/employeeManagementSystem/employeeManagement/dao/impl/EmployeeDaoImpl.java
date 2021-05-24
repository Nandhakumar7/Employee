package com.ideas2it.employeeManagementSystem.employeeManagement.dao.impl;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.ideas2it.employeeManagementSystem.sessionFactory.DataBaseConnection;
import com.ideas2it.employeeManagementSystem.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.employeeManagement.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.employeeManagement.model.Employee;
import com.ideas2it.employeeManagementSystem.logger.EmployeeManagementLogger;

/**
 * DaoImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private DataBaseConnection dataBaseConnection
            = DataBaseConnection.getInstance();
    private EmployeeManagementLogger log 
            = new EmployeeManagementLogger(EmployeeDaoImpl.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int insertEmployeeDetails(Employee employee) 
            throws EmployeeManagementException {
        int employeeId = 0;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
		    if(null != sessionFactory) {
                session = sessionFactory.openSession();
		        session.beginTransaction();
		        employeeId = (int)session.save(employee);
		        session.getTransaction().commit();
		    }
        } catch(HibernateException e) {
        	if(null != session) {
                session.getTransaction().rollback();
        	}
            log.logError("Create Employee Fails Name is :" + employee.getName(), e);
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
    public boolean updateEmployee(Employee employee) 
    		throws EmployeeManagementException {
        boolean isUpdated = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
		    if(null != sessionFactory) {
                session = sessionFactory.openSession();
		        session.beginTransaction();
                session.update(employee);
		        session.getTransaction().commit();
		    }
        } catch(HibernateException e) {
        	if(null != session) {
                session.getTransaction().rollback();
        	}
        	isUpdated = false;
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
    public Employee getEmployeeDetails(int employeeId) 
    		throws EmployeeManagementException {
        Session session = null;
        Employee employee = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            if(null != sessionFactory) {
                session = sessionFactory.openSession();
                employee = (Employee) session.get(Employee.class, employeeId);
                employee.getAddressList().size();
                employee.getProjectList().size();
            }
        } catch(HibernateException e) { 
        	log.logError("Get Details Failure Id is :" + employeeId, e);
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
    public boolean addNewAddress(Employee employee) 
    		throws EmployeeManagementException {
        boolean isAddressAdded = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
		    if(null != sessionFactory) {
                session = sessionFactory.openSession();
		        session.beginTransaction();
                session.saveOrUpdate(employee);
		        session.getTransaction().commit();
		    }
        } catch(HibernateException e) {
        	if(null != session) {
                session.getTransaction().rollback();
        	}
            isAddressAdded = false;
            log.logError("add Address Failed employeeId is" + employee.getId(), e);
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
    public List getAllEmployeeDetails(boolean isDeleted) 
    		throws EmployeeManagementException {
        Session session = null;
        List<Employee> employees = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            if(null != sessionFactory) {
                session = sessionFactory.openSession();
                String selectQuery = "FROM Employee WHERE isDeleted = :isDeletedValue";
                Query query = session.createQuery(selectQuery);
                query.setParameter("isDeletedValue", isDeleted);
                employees = query.list();
            }
        } catch(HibernateException e) { 
            employees = null;
            log.logError("Get All Details failed", e);
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
    public boolean checkEmployeeIdExists(int employeeId) 
    		throws EmployeeManagementException {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            if(null != sessionFactory) {
                session = sessionFactory.openSession();
                String selectQuery = "FROM Employee WHERE id = :id AND isDeleted = false";
                Query query = session.createQuery(selectQuery);
                query.setParameter("id", employeeId);
                List<Employee> employee = query.list();
                if (0 == employee.size()) {
                    idExists = true;
                }
            }
        } catch(HibernateException e) { 
            idExists = true;
            throw new EmployeeManagementException(e);
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
	  
















 
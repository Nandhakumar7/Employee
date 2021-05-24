package com.ideas2it.employeeManagementSystem.sessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * DataBaseConnection for create SessionFactory.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class DataBaseConnection {
    private static DataBaseConnection dataBaseConnection = null;
    private static SessionFactory factory = null;

    /**
     * DataBaseConnection used to restrict object creation 
     * outside the class.
     */
    private DataBaseConnection() {
    }

    /**
     * getInstance used to create DataBaseConnection object.
     *
     * @return dataBaseConnection  object of the class.
     */
    public static DataBaseConnection getInstance() {
        if (null == dataBaseConnection) {
            dataBaseConnection = new DataBaseConnection();
        }
        return dataBaseConnection;
    }
	
    /**
     * SessionFactory created between java and database.
     *
     * @return factory for storing details of employees to database.
     */
    public static SessionFactory getSessionFactory() {
        try {
            if (null == factory) {
                System.out.println("connecting...");
                factory = new Configuration().configure
                        ("/hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
            }
        } catch(HibernateException e) {
            System.out.println(e);			
            System.out.println("failed to connect");
        }
        return factory;
    }
}
package com.ideas2it.employeeManagementSystem.exception;

/**
 * EmployeeManagementException is used for create own 
 * Exception.
 *
 * @version  1.0 05-05-2021.
 * @author   Nandhakumar.K
 */
public class EmployeeManagementException extends Exception {
	
    public EmployeeManagementException(Throwable cause) {
        super(cause);
	}
    
    public EmployeeManagementException(String message) {
		super(message);
	}
	
	public EmployeeManagementException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EmployeeManagementException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

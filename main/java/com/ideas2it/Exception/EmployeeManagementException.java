package com.ideas2it.Exception;

public class EmployeeManagementException extends Exception {
    public EmployeeManagementException(Throwable c ) {
    	super(c);
	}
	public EmployeeManagementException(String message) {
		super(message);
	}
}

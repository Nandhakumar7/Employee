package com.ideas2it.Exception;

public class EmployeeManagementException extends Exception {
    String message;
    public EmployeeManagementException() {
	}
	public EmployeeManagementException(String message) {
		super(message);
	}
}

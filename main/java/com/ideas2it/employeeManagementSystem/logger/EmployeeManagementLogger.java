package com.ideas2it.employeeManagementSystem.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * EmployeeManagementLogger is used for log the 
 * activities and errors future use and reference.
 *
 * @version  1.0 05-05-2021.
 * @author   Nandhakumar.K
 */
public class EmployeeManagementLogger {	
    private Logger logger;
	
	public EmployeeManagementLogger(Class<?> className) {
		logger = LogManager.getLogger(className);
	}
	
	/**
	 * log the information in logs
	 * 
	 * @param info  information to log.   
	 */
	public void logInfo(Object info) {	
		logger.info(info);
	}
	
	/**
	 * log the errors in logs.
	 * 
	 * @param error   Error for logging  
	 * @param message  it contains error details.
	 */
	public void logError(String messsage, Object error) {
		logger.error(messsage, error);
	}
	
	/**
	 * log the warning in logs.
	 * 
	 * @param warning   to log warning 
	 */
	public void logWarning(Object warning) {
		logger.warn(warning);
	}
	
	/**
	 * log the fatal in logs.
	 * 
	 * @param fatal   to log Fatal.
	 */
	public void logFatal(Object fatal) {
		logger.fatal(fatal);
	}
    
	/**
	 * log the debug in logs.
	 * 
	 * @param debug   debug information to log.
	 */
	public void logDebug(Object debug) {
		logger.debug(debug);
	}
}
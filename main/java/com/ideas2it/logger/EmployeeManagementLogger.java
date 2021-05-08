package com.ideas2it.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeManagementLogger {
	
private Logger logger;
	
	public EmployeeManagementLogger(Class<?> className) {
		System.out.println("hiiall");
		System.out.println(className);
		logger = LogManager.getLogger(className);
	}
	
	/**
	 * logging Information getting from info logs it into EmployeeManagementLogs
	 * 
	 * @param info
	 */
	public void logInfo(Object info) {
		
		logger.info(info);
	}
	
	/**
	 *  logging Error getting from error logs it into EmployeeManagementLogs
	 * 
	 * @param error
	 */
	public void logError(String messsage, Object error) {
		System.out.println(messsage);
		System.out.println(error);
		logger.error(messsage, error);
	}
	
	/**
	 * logging Warning getting from warning and logs it into EmployeeManagementLogs
	 * 
	 * @param warning
	 */
	public void logWarning(Object warning) {
		logger.warn(warning);
	}
	
	/**
	 * logging Fatal getting from fatal and logs it into EmployeeManagementLogs
	 * 
	 * @param fatal
	 */
	public void logFatal(Object fatal) {
		logger.fatal(fatal);
	}
    
	/**
	 * Logging Debug getting from debug and logs it into EmployeeManagementLogs
	 * 
	 * @param debug
	 */
	public void logDebug(Object debug) {
		logger.debug(debug);
	}
}
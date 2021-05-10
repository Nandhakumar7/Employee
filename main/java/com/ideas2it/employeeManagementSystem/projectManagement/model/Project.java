package com.ideas2it.employeeManagementSystem.projectManagement.model;

import java.util.List;

import com.ideas2it.employeeManagementSystem.employeeManagement.model.Employee;

/**
 * project for storing and getting project details.
 *
 * @version  1.0 02-04-2021.
 * @author   Nandhakumar.
 */
public class Project {
    private int id;
    private String projectName;
    private String managerName;
    private String department;
    private int timePeriod;
    private List<Employee> employeesList;
    private boolean isDeleted; 
	
    public Project(int id, String projectName, String managerName, 
            String department, int timePeriod, List<Employee> employeesList,
            boolean isDeleted) {
        this.id = id;
        this.projectName = projectName;
        this.managerName = managerName;
        this.department = department;
        this.timePeriod = timePeriod;
        this.employeesList = employeesList;
        this.isDeleted = isDeleted;
    }

    public Project(String projectName, String managerName, 
            String department, int timePeriod) {
        this.projectName = projectName;
        this.managerName = managerName;
        this.department = department;
        this.timePeriod = timePeriod;
    }
 
    public Project() {
    }
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String  managerName) {
        this.managerName = managerName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
	
    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }
	
    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }
	
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public String toString() {
        return "\n\t  ProjectId            = " + id
                + "\n\t  Project Name         = " + projectName  
                + "\n\t  Manager Name         = " + managerName
                + "\n\t  Department           = " + department  
                + "\n\tTime period of project = " + timePeriod +"Months";
    }
}

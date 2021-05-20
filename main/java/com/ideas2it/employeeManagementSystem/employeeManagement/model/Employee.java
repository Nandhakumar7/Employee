package com.ideas2it.employeeManagementSystem.employeeManagement.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ideas2it.employeeManagementSystem.employeeManagement.model.Address;
import com.ideas2it.employeeManagementSystem.projectManagement.model.Project;

/**
 * Employee for storing and getting employee details.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class Employee {
    private int id;
    private String name;
    private float salary;
    private String mobileNumber;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    private boolean isDeleted;
    private List<Address> addressList;
    private List<Project> projectList;
    
    public Employee(String name, float salary, 
            String mobileNumber, Date dateOfBirth, List<Address> addressList,
            boolean isDeleted) {
        this.name = name;
        this.salary = salary;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.addressList = addressList;
        this.isDeleted = isDeleted;
    }
	
    public Employee(int id, String name, float salary, 
            String mobileNumber, Date dateOfBirth, List<Address> addressList,
            boolean isDeleted, List<Project> projectList) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.addressList = addressList;
		this.projectList = projectList;
        this.isDeleted = isDeleted;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float  salary) {
        this.salary = salary;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

   public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
	
    public List<Project> getProjectList() {
        return projectList;
    }

   public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
	
    public boolean getIsDeleted() {
        return isDeleted;
    }

   public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
	
    public String toString() {
        return "\nEmployeeId   = " + id
                + "\nName         = " + name  
                + "\nDateOfBirth  = " + dateOfBirth
                + "\nSalary       = " + salary  
                + "\nMobileNumber = " + mobileNumber;
    }
}

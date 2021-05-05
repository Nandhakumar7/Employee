package com.ideas2it.employeeManagement.model;

import java.util.Date;

import com.ideas2it.employeeManagement.model.Employee;

/**
 * Address for storing and getting employee address details.
 *
 * @version  1.0 19-03-2021.
 * @author   Nandhakumar.
 */
public class Address {
    private int id;
    private int doorNumber;
    private String streetName;
    private String district;
    private String state;
    private String country;
    private int pinCode;
    private String addressType;
    private boolean isDeleted;
	private Employee employee;
	
    public Address(int doorNumber, String streetName, String district, 
            String state, String country, int pinCode, String addressType,
            boolean isDeleted) {
        this.id = id;
        this.doorNumber = doorNumber;
        this.streetName = streetName;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.addressType = addressType;
        this.isDeleted = isDeleted;
    }
    
	public Address() { 
	}
	 	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getdoorNumber() {
        return doorNumber;
    }

    public void setdoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String  district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
	
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
	
    public boolean getIsDeleted() {
        return isDeleted;
    }

   public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public String toString() {
        return "\n" + addressType + " Address" + "\n" + doorNumber + "," 
                + streetName + ",\n" + district + "," + state + "-"
                + pinCode + ",\n" + country;
    }
}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html>
<html>
<head>
    <style type = "text/css">
    label {
        width:100px;
        display:inline-block;
    }
    </style>
    <title>Address</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagment</h1>
<body style="background-color:lightblue;text-align:center"> 
    <form:form method="post" action="saveAddress?employeeId=${employeeId}&choosedAddress=${choosedAddress}"
        modelAttribute="employee">     
        <label>DoorNumber</label> <form:input path="doorNumber" required="required"/><br><br>
        <label>StreetName</label> <form:input path="streetName" required="required"/><br><br>
        <label>District</label> <form:input path="district" required="required"/><br><br>
        <label>State</label> <form:input path="state" required="required"/><br><br>
        <label>country</label> <form:input path="country" required="required"/><br><br>
        <label>pinCode</label> <form:input path="pinCode" required="required"/><br><br>
        <form:hidden path="addressType" value="secondary" />
        <form:hidden path="id" />       
        <input type = "submit" value ="saveAddress"><br><br>
    </form:form>
    <a href = "EmployeeHomePage"><button class = "gfg"> Back</button></a>
</body>
</html>
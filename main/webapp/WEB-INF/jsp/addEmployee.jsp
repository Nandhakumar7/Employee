<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html>
<html>
<head>
    <style type = "text/css">
    label{
        width:100px;
        display:inline-block;
     }
    </style>
    <title>addEmployee</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <h2>Create New Project</h2>
      <form:form method="post" action="save">  
        <label>Name</label><form:input path="name" required="required"/><br><br>
        <label>Salary</label><form:input path="salary" required="required"/><br><br>
        <label>MobileNumber</label><form:input path="mobileNumber" pattern ="[6789][0-9]{9}" required="required"/><br><br>
        <label>DateOfBirth</label><form:input path="dateOfBirth" type ="date" required="required"/>
        Primary Address<br><br>
        <label>DoorNumber</label> <form:input path="addressList[0].doorNumber" required="required"/><br><br>
        <label>StreetName</label> <form:input path="addressList[0].streetName" required="required"/><br><br>
        <label>District</label> <form:input path="addressList[0].district" required="required"/><br><br>
        <label>State</label> <form:input path="addressList[0].state" required="required"/><br><br>
        <label>country</label> <form:input path="addressList[0].country" required="required"/><br><br>
        <label>pinCode</label> <form:input path="addressList[0].pinCode" required="required"/><br><br>
        <label></label> <form:hidden path="addressList[0].addressType" value="primary" />
        <input type = "submit" value ="save">   
   </form:form>     
    <br><br>
    <a href = "EmployeeHomePage"><button class = "gfg"> Back</button></a>
</body>
</html>
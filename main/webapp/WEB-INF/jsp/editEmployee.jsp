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
    <title>editEmployeeForm</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagment</h1>
<body style="background-color:lightblue;text-align:center">
    <form:form method="post" action="updateEmployee">  
        <form:hidden path="id" required="required"/>
        <label>Name</label><form:input path="name" required="required"/><br><br>
        <label>Salary</label><form:input path="salary" required="required"/><br><br>
        <label>MobileNumber</label><form:input path="mobileNumber" required="required"/><br><br>
        <label>DateOfBirth</label><form:input path="dateOfBirth" type ="date" required="required"/><br><br>
        <input type = "submit" value="updateEmployee"> 
    </form:form><br><br>
    <a href = "EmployeeHomePage"><button class = "gfg">Back</button></a><br><br>
</body>
</html>
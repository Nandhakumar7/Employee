<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
    <style>
    table,th,tr{
        border:1px solid black;
    }
    table.conter{
        margin-left: auto;
        margin-right: auto;
    }
    </style>
    <title>Employee</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <a href = "EmployeeHomePage"><button class = "gfg"> Back</button></a><br>
    <br><br>
    <h1 style="text-align: center;background-color: white;">Employees</h1>
    <table align= "center" style="width:70%;">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Salary</th>
            <th>MobileNumber</th>
            <th>DateOfBirth</th>
            <th>${message}</th>
        </tr>
        <c:forEach var="employee" items="${allEmployees}">
            <tr>
                <th><c:out value="${employee.id}"/></th>
                <th><c:out value="${employee.name}"/></th>
                <th><c:out value="${employee.salary}"/></th>
                <th><c:out value="${employee.mobileNumber}"/></th>
                <th><c:out value="${employee.dateOfBirth}"/></th>
                <th><a href="${message}?id=${employee.id}">${message}</a></th> 
            </tr>
        </c:forEach>
    </table>
</body>
</html>
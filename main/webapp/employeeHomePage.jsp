<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
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
    <a href = "Employee?action=callAddEmployeeForm"><button class = "gfg">CreateEmployee</button></a><br><br>
    <a href = "Employee?action=callViewPage"><button class = "gfg">showEmploye</button></a><br><br>
    <a href = "Employee?action=showAll&isDeleted=<%="false" %>"><button class = "gfg">showAllEmployee</button></a><br><br>
    <a href = "Employee?action=showAll&isDeleted=<%="true" %>"><button class = "gfg">RestoreEmployee</button></a><br><br>
    <a href = "Employee?action=callMainPage"><button class = "gfg">Back</button></a>
    <br><br>
    <% List<List<String>> employees
    = (List<List<String>>)request.getAttribute("allEmployees"); %>
    <% if(null != employees) { %>
        <h1 style="text-align: center;background-color: white;">Employees</h1>
        <table align= "center" style="width:70%;">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>MobileNumber</th>
                <th>Salary</th>
                <th>DateOfBirth</th>
            </tr>
             <%for(List<String> employee : employees) { %>
                <tr>
                    <th><%= employee.get(0) %></th>
                    <th><%= employee.get(1) %></th>
                    <th><%= employee.get(2) %></th>
                    <th><%= employee.get(3) %></th>
                    <th><%= employee.get(4) %></th>
                </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>
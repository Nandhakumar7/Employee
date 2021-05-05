<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
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
        <form action="Employee?action=updateEmployee" method = "post">
            <label>Employee id:</label> <input value="<%= session.getAttribute("id") %>" type
            = "number" name = "employeeId" readonly><br><br>
            <label>Name:</label> <input value="<%= session.getAttribute("name") %>" type
            = "text" name = "name" required><br><br>
            <label>Salary:</label> <input value="<%= session.getAttribute("salary") %>"
            type = "number" name = "salary" required><br><br>
            <label>DateOfBirth</label> <input value="<%= session.getAttribute("dateOfBirth") %>"
            type = "date" name = "dateOfBirth" required><br><br>
            <label>MobileNumber</label> <input value="<%= session.getAttribute("mobileNumber") %>"
            type = "number" pattern ="[6789][0-9]{9}" name = "mobileNumber" required><br><br>
            <input type = "submit"> 
        </form><br><br>
        <a href = "Employee?action=callViewPage"><button class = "gfg">Back</button></a><br><br>
</body>
</html>
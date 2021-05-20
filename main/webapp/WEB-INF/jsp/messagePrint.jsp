<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <title>messagePrint</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagement System</h1>
<body style="background-color:lightblue;text-align:center">
    <br><br><br><br>
    <table align= "center" style="width:50%;background-color:lightGreen;">
        <th>${message}</th>
    </table>
    <br><br>
     <a href = "EmployeeHomePage"><button class = "gfg">Employee</button></a><br><br>
    <a href = "projectHome"><button class = "gfg">Project</button></a></a>
</body>
</html>
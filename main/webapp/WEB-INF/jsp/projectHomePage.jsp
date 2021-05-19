<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ page import="java.util.Map"%>  
<!DOCTYPE html>
<html>
<head>
    <style>
    table,th,tr {
        border:1px solid black;
    }
    table.conter {
        margin-left: auto;
        margin-right: auto;
    }
    </style>
    <title>Project</title>
</head>
<h1 style="text-align: center;background-color: white;">ProjectManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <a href = "addProject"><button class = "gfg">  
    CreateProject</button></a><br><br>
    <a href = "viewProjectPage"><button class = "gfg">  
    showProject</button></a><br><br>
    <a href = "showAllProject"><button class = "gfg">  
    showAllProject</button></a><br><br>
    <a href = "showAllDeletedProject"><button class = "gfg">  
    RestoreProject</button></a><br><br>
    <a href = "mainPage"><button class = "gfg">  
    Back</button></a><br><br>
</body>
</html>
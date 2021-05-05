<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <style type = "text/css">
        label{
            width:100px;
            display:inline-block;
        }
    </style>
    <title>CreateProject</title>
</head>
<h1 style="text-align: center;background-color: white;">ProjectManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <% int projectId = 0; %>
    <% List<String> projectDetails = (List<String>)request.getAttribute("view"); %>
    <% String name = null;  %>
    <% String managerName = null;  %>
    <% String department = null ;  %>
    <% String timePeriod = null ; %>
    <% if (null != projectDetails) { %> 
        <% int id = Integer.parseInt(projectDetails.get(0)); %>
        <% projectId = id; %> 
        <% name = "value = " + projectDetails.get(1);  %>
        <% managerName = "value = " + projectDetails.get(2);   %>
        <% department = "value = " +projectDetails.get(3);  %>
        <% timePeriod = "value = " + projectDetails.get(4); %>
    <%} %>
    
    <form action="project?action=add" method = "post">
        <input type = "text" 
        value = <%= projectId %> name = "id" hidden><br><br>
        <label>Project name:</label> <input type = "text" 
        name = "name" <%= name %> required><br><br>
        <label>Project manager:</label> <input type = "text" 
        name = "manager" <%= managerName %> required><br><br>
        <label>Department:</label> <input type = "text" 
        name = "department" <%= department %> required><br><br>
        <label>TimePeriod:</label> <input type = "text" 
        name = "timePeriod" <%= timePeriod  %> required><br><br>
        <input type = "submit"> 
    </form><br><br>
    <a href = "project?action=callProjectPage"><button class = "gfg">Back</button></a>
</body>
</html>
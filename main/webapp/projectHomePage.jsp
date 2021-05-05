<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%> 
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
    <a href = "project?action=callAddProjectForm"><button class = "gfg">  
    CreateProject</button></a><br><br>
    <a href = "project?action=callViewPage"><button class = "gfg">  
    showProject</button></a><br><br>
    <a href = "project?action=showProjects&isDeleted=<%="false"%>"><button class = "gfg">  
    showAllProject</button></a><br><br>
    <a href = "project?action=showProjects&isDeleted=<%="true"%>"><button class = "gfg">  
    RestoreProject</button></a><br><br>
    <a href = "project?action=callMainPage"><button class = "gfg">Back</button></a><br><br>
    <% List<List<String>> projects
     = (List<List<String>>)request.getAttribute("allProject"); %>
    <% if(null != projects) { %>
        <h1 style="text-align: center;background-color: white;">Projects</h1>
        <table align= "center" style="width:70%;">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>ManagerName</th>
                <th>Department</th>
                <th>TimePeriod</th>
            </tr>
            <%for(List<String> project :projects)  {%>
                <tr>
                    <th><%= project.get(0) %></th>
                    <th><%= project.get(1) %></th>
                    <th><%= project.get(2) %></th>
                    <th><%= project.get(3) %></th>
                    <th><%= project.get(4) %></th>
                </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%> 
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
    <a href = "project?action=callMainPage"><button class = "gfg">Back</button></a><br><br>
      <%List<List<String>> projects
     = (List<List<String>>)request.getAttribute("allProject"); %>
        <h1 style="text-align: center;background-color: white;">Deleted Projects</h1>
        <table align= "center" style="width:70%;">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>ManagerName</th>
                <th>Department</th>
                <th>TimePeriod</th>
                <th>Restore</th>
            </tr>
            <%for(List<String> project :projects)  {%>
                <tr>
                    <th><%= project.get(0) %></th>
                    <th><%= project.get(1) %></th>
                    <th><%= project.get(2) %></th>
                    <th><%= project.get(3) %></th>
                    <th><%= project.get(4) %></th>
                    <th><a href = "project?action=deleteOrRestore&id=<%= project.get(0)%>">
                    <button class = "gfg">RestoreProject</button></a></th>
                </tr>
            <% } %>
        </table>
</body>
</html>
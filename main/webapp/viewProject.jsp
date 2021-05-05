<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
    <title>showProject</title>
    <style>
    table,th,tr{
        border:1px solid black;
    }
    table.conter{
        margin-left: auto;
        margin-right: auto;
    }
    </style>
</head>
<h1 style="text-align: center;background-color: white;">ProjectManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <form action="project?action=view" method = "post">
        Project id: <input type = "number" name = "id" required><br><br>
        <input type = "submit"> 
    </form><br><br>
    <% List<String> project
    = (List<String>)request.getAttribute("view"); %>
    <%if(null != project) { %>
        <% if(1 == project.size()) { %>
            <div class="alert">
                <span class="closebtn">&times;</span>  
                <strong>Project</strong>Not Exists
            </div>
        <% } else { %>
            <% session.setAttribute("id",project.get(0)); %>
            <% session.setAttribute("name",project.get(1)); %>
            <% session.setAttribute("managerName",project.get(2)); %>
            <% session.setAttribute("department",project.get(3)); %>
            <% session.setAttribute("timePeriod",project.get(4)); %>
            <table align= "center" style="width:70%;">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>ManagerName</th>
                    <th>Department</th>
                    <th>TimePeriod</th>
                    <th>Assign/unAssign</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <tr>
                    <th><%= project.get(0)%></th>
                    <th><%= project.get(1)%></th>
                    <th><%= project.get(2)%></th>
                    <th><%= project.get(3)%></th>
                    <th><%= project.get(4)%></th>
                    <th><a href = "project?action=getAllProjectEmployees&id=<%= session.getAttribute("id") %>">
                     <button class = "gfg">Assign/UnAssign</button></a></th>
                    <th><a href = "project?action=show&id=<%= project.get(0)%>">
                    <button class = "gfg">Edit</button></a></th>
                    <th><a href = "project?action=deleteOrRestore&id=<%= session.getAttribute("id") %>">
                    <button class = "gfg">Delete Project</button></a><br><br></th>
                </tr>
            </table><br><br>
            <%if(6 <= project.size()) { %>
                <table align= "center" style="width:70%;">
                    <tr>
                        <th>EmployeeId</th>
                        <th>Name</th>
                    </tr>
                    <% for(int i = 5;i < project.size();i++) { %>
                        <tr>
                            <th><%= project.get(i)%></th>
                            <th><%= project.get(i+1)%></th>
                        </tr>
                        <% i = i + 1; %>
                    <% } %>
                </table>
            <% } %>
        <% } %>
    <% } %>
    <br><br>
    <a href = "project?action=callProjectPage"><button class = "gfg"> back</button></a>
    
     <% List<List<String>> projectEmployees 
    = (List<List<String>>)request.getAttribute("projectEmployees"); %>
    <% if(null != projectEmployees) { %> 
        <form action="project?action=assignAndUnassign&id=<%= session.getAttribute("id") %>" 
        method = "post">
            <h3 style="text-align: center;background-color: white;">
            ProjectAssignment</h3>
            <h4 style="text-align: center">EmployeesId</h4>
            <h5 style="text-align: center">(please check the box to assign/uncheck 
            the box to unassign)</h5>
            <table align= "center" style="width:70%;"> 
                    <tr>
                    <th>EmployeeId</th>
                    <th>EmployeeName</th>
                    <th>Assign</th>
                    </tr>
            <% for(int i = 0; i < (projectEmployees.get(0)).size();i++) { %>
                    <tr>
                    <th><%= (projectEmployees.get(0)).get(i) %></th>
                    <th><%= (projectEmployees.get(0)).get(i + 1) %></th>
                    <th><input  type = "checkbox"
                value ="<%=(projectEmployees.get(0)).get(i) %>" name = "employeeId" checked></th>
                <% i = i + 1; %>
                </tr>
            <% } %>
            <% for(int i = 0; i < (projectEmployees.get(1)).size();i++) { %>
                <tr>
                    <th><%= (projectEmployees.get(1)).get(i) %></th>
                    <th><%= (projectEmployees.get(1)).get(i + 1) %></th>
                    <th><input  type = "checkbox"
                      value ="<%=(projectEmployees.get(1)).get(i) %>" name = "employeeId" ></th>
                </tr>
                <% i = i + 1; %>
            <% } %>
            </table>
            <br><br>
            <input type = "submit"> 
        </form>
    <% } %>
</body>
</html>
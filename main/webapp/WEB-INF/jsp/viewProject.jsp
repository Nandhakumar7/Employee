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
    <form action="showProject" method = "post">
        Project id: <input type = "number" name = "id" required><br><br>
        <input type = "submit"> 
    </form><br><br>
    <c:if test ="${view != null}">
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
                   <th>${project.id}</th>
                   <th>${project.projectName}</th>
                   <th>${project.managerName}</th>
                   <th>${project.department}</th>
                   <th>${project.timePeriod}</th>

                </tr>
            </table><br><br>
            </c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
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
    <a href = "projectHome"><button class = "gfg">Back</button></a><br><br>
    <h1 style="text-align: center;background-color: white;">Projects</h1>
    <table align= "center" style="width:70%;">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>ManagerName</th>
            <th>Department</th>
            <th>TimePeriod</th>
            <th>${message}</th>
        </tr>
        <c:forEach var="project" items="${allProjects}">
            <tr>
                <th><c:out value="${project.id}"/></th>
                <th><c:out value="${project.projectName}"/></th>
                <th><c:out value="${project.managerName}"/></th>
                <th><c:out value="${project.department}"/></th>
                <th><c:out value="${project.timePeriod}"/></th>
                <th><a href="${message}?id=${project.id}">${message}</a></th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
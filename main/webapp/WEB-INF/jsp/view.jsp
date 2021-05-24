<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<title>showProject</title>
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
    <form action="showProject">
        Project id: <input type = "number" name = "id" required><br><br>
        <input type = "submit"> 
    </form><br><br>
    <a href = "projectHome"><button class = "gfg">Back</button></a>
    <c:if test ="${project != null}">
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
            <th><a href="getProjectEmployeesList?id=${project.id}">Assign/UnAssign</a></th>
            <th><a href="editProject?id=${project.id}">Edit</a></th>
            <th><a href="DeleteProject?id=${project.id}">Delete</a></th>
        </tr>
    </table><br><br>
    <c:choose>
        <c:when test ="${(project.employeesList).isEmpty()}">
            No Employees Assigned..
        </c:when>
        <c:otherwise>
         <table align= "center" style="width:70%;">    
             <tr>
                 <th>EmployeeId</th>   
                 <th>EmployeeName</th> 
            </tr>
            <c:forEach var="employee" items="${project.employeesList}">
                <tr>
                    <th><c:out value="${employee.id}"/></th>
                    <th><c:out value="${employee.name}"/></th>
                </tr>
            </c:forEach>     
         </table>
         </c:otherwise>
    </c:choose>
    </c:if>
    <c:if test ="${projectEmployees != null}">
        <form action="assignEmployee"  method = "post">
            <input  type = "checkbox"
            value ="${project.id}" name = "projectId" checked="checked" hidden>
            <input  type = "checkbox"
            value =<%=0 %> name = "employeeId" checked="checked" hidden>
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
                <c:forEach var="employee" items="${projectEmployees.get(0)}">
                    <tr>
                        <th><c:out value="${employee.id}"/></th>
                        <th><c:out value="${employee.name}"/></th>
                        <th><input  type = "checkbox"
                        value ="${employee.id}" name = "employeeId" checked></th>
                    </tr>
                </c:forEach>
                <c:forEach var="employee" items="${projectEmployees.get(1)}">
                    <tr>
                        <th><c:out value="${employee.id}"/></th>
                        <th><c:out value="${employee.name}"/></th>
                        <th><input  type = "checkbox"
                        value ="${employee.id}" name = "employeeId" ></th>
                    </tr>
                </c:forEach>
            </table><br><br>
            <input type = "submit"> 
        </form>
    </c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
    <title>showEmployee</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <form action="showEmployee">
        Employee Id: <input type = "number" name = "id" required><br><br>
        <input type = "submit"> 
    </form><br><br>
    <a href = "EmployeeHomePage"><button class = "gfg"> Back</button></a>
    <br><br>
    <c:if test ="${employee != null}">
        <table align= "center" style="width:70%;">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>salary</th>
                <th>mobileNumber</th>
                <th>DateOfBirth</th>
                <th>addAddress</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <tr>
                <th>${employee.id}</th>
                <th>${employee.name}</th>
                <th>${employee.salary}</th>
                <th>${employee.mobileNumber}</th>
                <th>${employee.dateOfBirth}</th>
                <th><a href="addAddress?id=${employee.id}">addAddress</a></th>
                <th><a href="editEmployeeForm?id=${employee.id}">Edit</a></th>
                <th><a href="DeleteEmployee?id=${employee.id}">Delete</a></th>
            </tr>
        </table><br><br>
        <table align= "center" style="width:70%;">
            <tr>
                <th>S.N0</th>
                <th>AddressType</th>
                <th>DoorNumber</th>
                <th>StreetName</th>
                <th>State</th>
                <th>District</th>
                <th>Country</th>
                <th>Pincode</th>
                <th>Edit</th>
                <th>Delete</th>
                <th>UpdateAsPrimary</th>
            </tr>
            <% int count = 1; %>
            <c:forEach var="address" items="${employee.addressList}">
                <tr>
                    <th><%=count%></th>
                    <th><c:out value="${address.addressType}"/></th>
                    <th><c:out value="${address.doorNumber}"/></th>
                    <th><c:out value="${address.streetName}"/></th>
                    <th><c:out value="${address.state}"/></th>
                    <th><c:out value="${address.district}"/></th>
                    <th><c:out value="${address.country}"/></th>
                    <th><c:out value="${address.pinCode}"/></th>
                    <th><a href = "editAddress?choosedAddressId=${address.id}&id=${employee.id}&choosedAddress=<%=count%>">
                    <button class = "gfg">Edit</button></a></th>
                    <th><a href = "DeleteAddress?choosedAddress=<%=count%>&employeeId=${employee.id}">
                    <button class = "gfg">DeleteAddress</button></a><br><br></th>
                    <th><a href = "updateAsPrimaryAddress?choosedAddress=<%=count %>&id=${employee.id}">
                    <button class = "gfg">updateAsPrimary</button></a><br></th>
                    <% count = count + 1; %>
                </tr>
            </c:forEach>
        </table><br><br>
        <c:choose>
            <c:when test ="${(employee.projectList).isEmpty()}">
                No Projects Assigned..
                <a href="getEmployeeProjects?id=${employee.id}">AssignProject</a>
            </c:when>
           <c:otherwise>
           <table align= "center" style="width:70%;">    
               <tr>
                   <th>ProjectId</th>   
                   <th>ProjectName</th> 
               </tr>
               <c:forEach var="project" items="${employee.projectList}">
                   <tr>
                      <th><c:out value="${project.id}"/></th>
                      <th><c:out value="${project.projectName}"/></th>
                   </tr>
               </c:forEach>     
           </table>
           <th><a href="getEmployeeProjects?id=${employee.id}">Assign/UnAssign</a></th>
           </c:otherwise>
       </c:choose>
   </c:if>
   <c:if test ="${projectEmployees != null}">
       <form action="assignProject"  method = "post">
           <input  type = "checkbox"
           value ="${employee.id}" name = "employeeId" checked="checked" hidden>
           <input  type = "checkbox"
           value =<%=0 %> name = "projectId" checked="checked" hidden>
           <h3 style="text-align: center;background-color: white;">
           ProjectAssignment</h3>
           <h4 style="text-align: center">ProjectsId</h4>
           <h5 style="text-align: center">(please check the box to assign/uncheck 
           the box to unassign)</h5>
           <table align= "center" style="width:70%;"> 
               <tr>
                   <th>ProjectId</th>
                   <th>ProjectName</th>
                   <th>Assign</th>
               </tr>
               <c:forEach var="project" items="${projectEmployees.get(0)}">
                   <tr>
                        <th><c:out value="${project.id}"/></th>
                        <th><c:out value="${project.projectName}"/></th>
                        <th><input  type = "checkbox"
                        value ="${project.id}" name = "projectId" checked></th>
                   </tr>
               </c:forEach>
               <c:forEach var="project" items="${projectEmployees.get(1)}">
                   <tr>
                       <th><c:out value="${project.id}"/></th>
                       <th><c:out value="${project.projectName}"/></th>
                       <th><input  type = "checkbox"
                       value ="${project.id}" name = "projectId" ></th>
                   </tr>
               </c:forEach>
           </table><br><br>
           <input type = "submit"> 
        </form>
    </c:if>
</body>
</html>
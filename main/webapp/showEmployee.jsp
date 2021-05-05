<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
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
    <form action="Employee?action=view" method = "post">
        Employee Id: <input type = "number" name = "employeeId" required><br><br>
        <input type = "submit"> 
    </form>
    <br><br>
    <% List<List<String>> employee
    = (List<List<String>>)request.getAttribute("view"); %>
    <%if(null != employee) { %>
        <% List<String> employeeAddress = employee.get(1); %>
        <% List<String> employeeDetails = employee.get(0); %>
        <%if(1 == employee.size()) { %>
            <div class="alert">
                <span class="closebtn">&times;</span>  
                <strong>Employee</strong>Not Exists
            </div>
        <% } else { %>
            <% session.setAttribute("id",employeeDetails.get(0)); %>
            <% session.setAttribute("name",employeeDetails.get(1)); %>
            <% session.setAttribute("salary",employeeDetails.get(3)); %>
            <% session.setAttribute("mobileNumber",employeeDetails.get(2)); %>
            <% session.setAttribute("dateOfBirth",employeeDetails.get(4)); %>
            <table align= "center" style="width:70%;">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Salary</th>
                    <th>MobileNumber</th>
                    <th>DateOfBirth</th>
                    <th>Edit</th>
                    <th>addAddress</th>
                    <th>Delete</th>
                </tr>
                <tr>
                    <th><%= employeeDetails.get(0)%></th>
                    <th><%= employeeDetails.get(1)%></th>
                    <th><%= employeeDetails.get(3)%></th>
                    <th><%= employeeDetails.get(2)%></th>
                    <th><%= employeeDetails.get(4)%></th>
                    <th><a href = "Employee?action=sendEditEmployeeDetails">
                    <button class = "gfg">Edit</button></a></th>
                    <th><a href = "Employee?action=newAddressFormCall&employeeId=<%= employeeDetails.get(0)%>">
                    <button class = "gfg">AddAddress</button></a></th>
                    <th><a href = "Employee?action=deleteOrRestore&employeeId=<%= employeeDetails.get(0)%>">
                    <button class = "gfg">Delete</button></a></th>
                </tr>
            </table>
            <br><br>
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
                <% session.setAttribute("addresses", employeeAddress); %>
                <% int count = 1; %>
                <%for(int i = 0;i<employeeAddress.size();i++) { %>
                    <tr>
                        <th><%=count%></th>
                        <th><%= employeeAddress.get(i) %></th>
                        <th><%= employeeAddress.get(i + 1) %></th>
                        <th><%= employeeAddress.get(i + 2) %></th>
                        <th><%= employeeAddress.get(i + 3) %></th>
                        <th><%= employeeAddress.get(i + 4) %></th>
                        <th><%= employeeAddress.get(i + 5) %></th>
                        <th><%= employeeAddress.get(i + 6) %></th>
                        <th><a href = "Employee?action=showAddressEdit&employeeId=<%= employeeDetails.get(0)
                        %>&count=<%=count%>">
                        <button class = "gfg">Edit</button></a></th>
                        <th><a href = "Employee?action=deleteAddress&count=<%=count%>&employeeId=<%= 
                        employeeDetails.get(0)%>">
                        <button class = "gfg">DeleteAddress</button></a><br><br></th>
                        <th><a href = "Employee?action=updateAsPrimary&count=<%= count%>&employeeId=<%=
                        employeeDetails.get(0) %>">
                        <button class = "gfg">updateAsPrimary</button></a><br></th>
                    </tr>
                    <% i = i + 6; %>
                    <% count = count + 1; %>
                <% } %>
            </table><br><br>
            <a href = "Employee?action=getEmployeeProjects&id=<%= session.getAttribute("id") %>">
                <b>Assign/UnAssign</b></a><br><br>
            <br><br><br>
            <%if(6 <= employeeDetails.size()) { %>
                <table align= "center" style="width:70%;">
                    <tr>
                        <th>ProjectId</th>
                        <th>Project Name</th>
                    </tr>
                    <% for(int i =5;i<employeeDetails.size();i++) { %>
                        <tr>
                            <th><%= employeeDetails.get(i)%></th>
                            <th><%= employeeDetails.get(i+1)%></th>
                        </tr>
                        <%i= i+1; %>
                    <% } %>
                </table>
            <% } %>
        <% } %>
    <% } %>
    <br><br>
    <a href = "Employee?action=callEmployeePage"><button class = "gfg"> back</button></a>
    <br><br> 
    <% List<List<String>> projectEmployees 
    = (List<List<String>>)request.getAttribute("projectEmployees"); %>
   <% if(null != projectEmployees) { %> 
       <form action="Employee?action=assignAndUnassign&employeeId=<%= session.getAttribute("id") %>" 
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
                value ="<%=(projectEmployees.get(0)).get(i) %>" name = "projectsId" checked></th>
                <% i = i + 1; %>
                </tr>
            <% } %>
            <% for(int i = 0; i < (projectEmployees.get(1)).size();i++) { %>
                <tr>
                    <th><%= (projectEmployees.get(1)).get(i) %></th>
                    <th><%= (projectEmployees.get(1)).get(i + 1) %></th>
                    <th><input  type = "checkbox"
                      value ="<%=(projectEmployees.get(1)).get(i) %>" name = "projectsId" ></th>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <style type = "text/css">
    label {
        width:100px;
        display:inline-block;
    }
    </style>
    <title>Address</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagment</h1>
<body style="background-color:lightblue;text-align:center"> 
    <% List<String> addresses = (List<String>)request.getAttribute("addresses"); %>
    <% String addressType = null, doorNumber = null, streetName = null, 
    district = null, state = null, country = null, pincode = null;%>
    <% if(null != addresses) { %>
        <% int index = 0; %>
        <% String count = request.getParameter("count"); %>
        <% int addressCount = Integer.parseInt(count); %>
        <% index = 0 + (addressCount - 1) * 7; %>
        <%  addressType = "value =" +  addresses.get(index); %>
        <%  doorNumber = "value =" +  addresses.get(index + 1); %>
        <%  streetName = "value =" +  addresses.get(index + 2); %>
        <%  district = "value =" +  addresses.get(index + 3); %>
        <%  state = "value =" +  addresses.get(index + 4); %>
        <%  country = "value =" +  addresses.get(index + 5); %>
        <%  pincode = "value =" +  addresses.get(index + 6); %>
    <% } %>
    <h2>Address</h2>
    <form action="Employee" method = post>
        <input  type = "text" <%= addressType %> name = "addressType" hidden><br><br>
        <label>DoorNumber</label> <input  type = "number"  <%= doorNumber %> name = "doorNumber" required><br><br>
        <label>StreetName</label> <input type = "text" <%= streetName %>  name = "streetName" required><br><br>
        <label>District</label> <input type = "text" name = "district" <%= district %>   required><br><br>
        <label>State:</label> <input type = "text" name = "state" <%= state %>  required><br><br>
        <label>Country:</label> <input type = "text" name = "country" <%= country%>  required><br><br>
        <label>PinCode:</label> <input type = "number" name = "pincode" <%= pincode %>  required><br><br>
        <input  type = "number" value ="<%= request.getParameter("count")%>"  name ="count" hidden>
        <input  type = "number" value = <%= request.getParameter("employeeId") %> name = "employeeId" hidden>
        <% if (null != addresses) { %>
            <input type = "submit" name = "action" value = "updateAddress" >
        <% } else { %>
            <input type = "submit" name = "action" value = "addressAddNew" > 
        <% } %>
    </form>
    <br><br>
    <a href = "Employee?action=callViewPage"><button class = "gfg">Back</button></a><br><br> 
</body>
</html>
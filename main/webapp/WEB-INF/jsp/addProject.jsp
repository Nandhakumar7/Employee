<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
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
       <form:form method="post" action="saveOrUpdate">    
       <table align= "center">  
         <tr>    
          <td><form:hidden path="id" /></td> 
         </tr>
         <tr>    
          <td>ProjectName : </td>   
          <td><form:input path="projectName" required="required"/></td>  
         </tr>    
         <tr>    
          <td>ManagerName :</td>    
          <td><form:input path="managerName" required="required"/></td>  
         </tr>   
         <tr>    
          <td>Department :</td>    
          <td><form:input path="department" required="required"/></td>  
         </tr>  
         <tr>    
          <td>TimePeriod :</td>    
          <td><form:input path="timePeriod" required="required"/></td>  
         </tr>  
         <tr>    
          <td> </td>    
          <td><input type="submit" value="save" /></td>    
         </tr>    
        </table>    
       </form:form>     
    <a href = "projectHome"><button class = "gfg">Back</button></a>
</body>
</html>
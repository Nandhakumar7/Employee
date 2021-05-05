package com.ideas2it.employeeManagement.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeeManagement.service.EmployeeService;
import com.ideas2it.employeeManagement.service.impl.EmployeeServiceImpl;

/**
 * EmployeeController for doing CRUD operation.
 * 
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService 
            = new EmployeeServiceImpl();
    
	/**
	 * receive action from user and send that  request to that specific method.
	 * 
	 * @param request   it contains action to do operation.
     * @param response  send response of the request to user.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
		switch (action) {
		    case "add":
		        addEmployee(request, response);
		    	break;
		    case "view":
		    	showEmployee(request, response);
		    	break;
		    case "updateEmployee":
		    	updateEmployee(request, response);
		    	break;
		    case "updateAddress":
		    	updateAddress(request, response);
		    	break;
		    case "addressAddNew":
		    	addNewAddress(request, response);
		    	break;
		    case "assignAndUnassign":
		    	assignAndUnassignProjects(request, response);
		    	break;
		    default:
		        break;
		 }
	}
	
	/**
	 * do get method used to do crud operation.
	 * 
	 * @param request   it contains action to do operation.
     * @param response  send response of the request to user.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
		switch (action) {
	        case "showAll":
		        getAllEmployees(request, response);
			    break;
	        case "callEmployeePage":
			    callEmployeePage(request, response);
			    break;
			case "showAddressEdit":
			    showAddressToEdit(request, response);
			    break;
			case "deleteOrRestore":
				deleteOrRestoreEmployee(request, response);
			    break;
			case "deleteAddress":
			    deleteAddress(request, response);
			    break;
			case "updateAsPrimary":
			    updateAsPrimary(request, response);
			    break;
			case "newAddressFormCall":
			    newAddressFormCall(request, response);
		    	break;
			case "sendEditEmployeeDetails":
			    sendEditEmployeeDetails(request, response);
			    break;
			case "getEmployeeProjects":
			    getAllEmployeeProjects(request, response);
		    	break;
			case "callViewPage":
		    	callViewEmployeePage(request, response);
		    	break;
		    case "callAddEmployeeForm":
		    	addEmployeeFormCall(request, response);
		    	break;
		    case "callMainPage":
		        callMainPage(request, response);
		        break;
			default:
			    break;
        }	     
	}
	
	/**
     * call employeeHome page for doing crud operation.
     *
     * @param request   it contains request of employeePage
     * @param response  send response of the request to user.
     */
    private void callEmployeePage
            (HttpServletRequest request, HttpServletResponse response) {
    	try {
    		response.sendRedirect("employeeHomePage.jsp"); 
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
    
	/**
     * call addPROJECT fORM page for CREATE NEW PROJECT.
     *
     * @param request   it contains request of newForm
     * @param response  send response of the request to user.
     */
	private void addEmployeeFormCall
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
    		response.sendRedirect("addEmployee.jsp"); 
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}

	/**
     * call homePage fORM page for choose employee or project operation.
     *
     * @param request   it contains request of mainPage
     * @param response  send response of the request to user.
     */
	private void callMainPage
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
    		response.sendRedirect("mainPage.html"); 
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
        
	
	/**
     * call viewProject  page for user view project details.
     *
     * @param request   it contains request of view page.
     * @param response  send response of the request to user.
     */
	private void callViewEmployeePage
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("showEmployee.jsp"); 
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}

    /**
     * Employee Id and projects id  get from user and,
     * assign or unassign project to employee.
     *
     * @param request   it contains projectId and employeeId.
     * @param response  send response of the request to user.
     */
	private void assignAndUnassignProjects
	        (HttpServletRequest request, HttpServletResponse response) {
        String projectId[] = (request.getParameterValues("projectsId"));
		List<Integer> projectsId = new ArrayList<Integer>();
		int id = Integer.parseInt(request.getParameter("employeeId"));
		if(null != projectId) {
		    for(int i = 0;i< projectId.length; i ++) {
		        projectsId.add(Integer.parseInt(projectId[i]));
		    }
		}
		employeeService.assignProject(id, projectsId);
		showEmployee(request,response);
	}

    /**
     * Employee Id and projects id  get from user and,
     * get assign or unassigned projects for  employee.
     *
     * @param request   it contains  employeeId to get projects.
     * @param response  send response of the request to user.
     */
	private void getAllEmployeeProjects
	        (HttpServletRequest request, HttpServletResponse response) {
	    try {
		    int id = Integer.parseInt(request.getParameter("id"));
		    List<List<String>> projectEmployeesList
		            = employeeService.employeeProjects(id);
		    List<List<String>> employee = employeeService.getEmployeeDetails(id);
   	        request.setAttribute("view", employee);
		    request.setAttribute("projectEmployees", projectEmployeesList);
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("showEmployee.jsp");
            dispatcher.forward(request, response);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
    }

    /**
     * used to call employee edit form.
     *
     * @param request   it contains  employeeId to get details.
     * @param response  send response of the request to user.
     */
	private void sendEditEmployeeDetails
	        (HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String employeeEdit = "true";
			request.setAttribute("editEnable", employeeEdit);
			RequestDispatcher dispatcher
		            = request.getRequestDispatcher("editEmployee.jsp");
		    dispatcher.forward(request, response);	
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
    }

    /**
     * Employee Id and addressDetails  get from user and,
     * add to database.
     *
     * @param request   it contains  employeeId and address details.
     * @param response  send response of the request to user.
     */
	private void addNewAddress
	        (HttpServletRequest request, HttpServletResponse response) {
        employeeService.addNewAddress
                (Integer.parseInt(request.getParameter("employeeId")),
	            Integer.parseInt(request.getParameter("doorNumber")),
	            request.getParameter("streetName"), request.getParameter("state"),
	            request.getParameter("district"),request.getParameter("country"),
	            Integer.parseInt(request.getParameter("pincode")));
        showEmployee(request,response);	
    }

    /**
     * Employee Id and addressDetails  get from user and,
     * update as a primary to database.
     *
     * @param request   it contains  employeeId and address details.
     * @param response  send response of the request to user.
     */
    private void updateAsPrimary
            (HttpServletRequest request, HttpServletResponse response) {
        employeeService.updateAsPrimaryAddress
                (Integer.parseInt(request.getParameter("count")),
    			Integer.parseInt(request.getParameter("employeeId")));
    	showEmployee(request,response);	
	}

    /**
     * Employee Id and addressDetails  get from user and,
     * delete from database.
     *
     * @param request   it contains  employeeId and address details.
     * @param response  send response of the request to user.
     */
	private void deleteAddress
	        (HttpServletRequest request, HttpServletResponse response) {
	    employeeService.deleteEmployeeAddress
	            (Integer.parseInt(request.getParameter("count")),
		        Integer.parseInt(request.getParameter("employeeId")));
		showEmployee(request,response);
	}

    /**
     * Employee Id and addressDetails  get from user and,
     * update address to database.
     *
     * @param request   it contains  employeeId and address details.
     * @param response  send response of the request to user.
     */
	private void updateAddress
	        (HttpServletRequest request, HttpServletResponse response) {
        String count =request.getParameter("count");
		employeeService.updateAddress(Integer.parseInt(count),
		        Integer.parseInt(request.getParameter("employeeId")),
				Integer.parseInt(request.getParameter("doorNumber")),
				request.getParameter("streetName"), request.getParameter("state"), 
				request.getParameter("district"),
				request.getParameter("country"),
				Integer.parseInt(request.getParameter("pincode")));
	    showEmployee(request,response);
	}

    /**
     * transfer request to show address and edit.
     *
     * @param request   it contains  employeeId and address details.
     * @param response  send response of the request to user.
     */
	private void showAddressToEdit
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("employeeId");
			int employeeId = Integer.parseInt(id);
			List<List<String>> employee = employeeService.getEmployeeDetails(employeeId);
		    request.setAttribute("addresses",employee.get(1));
			RequestDispatcher dispatcher 
			        = request.getRequestDispatcher("editAddress.jsp");
	        dispatcher.forward(request, response);	
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

    /**
     * used to call new address add form.
     * 
     * @param request   it contains  employeeId and address details.
     * @param response  send response of the request to user.
     */
	private void newAddressFormCall
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher dispatcher
			        = request.getRequestDispatcher("editAddress.jsp");
	        dispatcher.forward(request, response);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

    /**
     * get all Employee details to view.
     *
     * @param request   it contains request of get all employee.
     * @param response  send response of the request to user.
     */
	private void getAllEmployees
	        (HttpServletRequest request, HttpServletResponse response) { 
		try {
			String userRequest = request.getParameter("isDeleted");
			boolean isDeleted = Boolean.parseBoolean(userRequest);
	        request.setAttribute("allEmployees", employeeService.getAllEmployee(isDeleted));
	        if(isDeleted) {
	            RequestDispatcher dispatcher
	                    = request.getRequestDispatcher("restoreEmployee.jsp");
	            dispatcher.forward(request, response);
	        } else {
	        	RequestDispatcher dispatcher
                        = request.getRequestDispatcher("employeeHomePage.jsp");
                dispatcher.forward(request, response);
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	/**
	 * get  details from user and create employee
	 *
	 * @param request   it contains employee details to create.
     * @param response  send response of the request to user.
     */
    public void addEmployee
            (HttpServletRequest request, HttpServletResponse response) {
    	try {
    		String name = request.getParameter("name");
       	    float salary = Float.parseFloat(request.getParameter("salary"));
       	    String mobileNumber = request.getParameter("mobileNumber");
       	    String date = request.getParameter("dateOfBirth");
       	    java.util.Date DateOfBirth = employeeService.getDateOfBirth(date);
       	    List<List<String>> addresses = getAddressList(request, response);
       	    String message = null;
       	    int employeeId
       	            = (employeeService.addEmployeeDetails
       	            (name, salary, mobileNumber, DateOfBirth, addresses));
       	    if(0 != employeeId) {
                message = "SUCESSFULLY ADDED! + YOUR EmployeeId id is :"+ employeeId;    
    	    } else {
    	        message = "FAILED TO ADD!..PLEASE TRY AGAIN";    
    	    }
    	    request.setAttribute("message", message);
            RequestDispatcher dispatcher 
                    = request.getRequestDispatcher("messagePrint.jsp");
            dispatcher.forward(request, response);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
   
   /**
    * get address details from user and put into list
    *
    * @param request   it contains address List
    * @param response  send response of the request to user.
    * @return List it contains addresslist
    */   
    private List<List<String>> getAddressList
            (HttpServletRequest request, HttpServletResponse response) {
        List<String> primaryAddress = new LinkedList<String>();
	    List<String> secondaryAddress = new LinkedList<String>();
	    List<List<String>> addresses = new LinkedList<List<String>>();
	   	primaryAddress.add("Primary");
	   	primaryAddress.add(request.getParameter("doorNumber"));
	   	primaryAddress.add(request.getParameter("streetName"));
	   	primaryAddress.add(request.getParameter("district"));
	   	primaryAddress.add(request.getParameter("state"));
	   	primaryAddress.add(request.getParameter("country"));
	   	primaryAddress.add(request.getParameter("pincode"));
	   	addresses.add((LinkedList<String>) primaryAddress);
	   	secondaryAddress.add("Secondary");
	   	secondaryAddress.add(request.getParameter("doorNumber1"));
	   	secondaryAddress.add(request.getParameter("streetName1"));
	   	secondaryAddress.add(request.getParameter("district1"));
	   	secondaryAddress.add(request.getParameter("state1"));
	   	secondaryAddress.add(request.getParameter("country1"));
	   	secondaryAddress.add(request.getParameter("pincode1"));
	   	addresses.add((LinkedList<String>) secondaryAddress);
	   	return addresses;
    }

   /**
    * Employee Id and details are get from database
    * and send to view
    *
    * @param request   it contains employeeId to get details.
    * @param response  send response of the request to user.
    */
    public void showEmployee
            (HttpServletRequest request, HttpServletResponse response) {
    	try {
    		int id = Integer.parseInt(request.getParameter("employeeId"));
       	    List<List<String>> employee = new LinkedList<List<String>>();
            if(!(employeeService.checkEmployeeIdExists(id))) {
       	        employee = employeeService.getEmployeeDetails(id);
       	        request.setAttribute("view", employee);
                RequestDispatcher dispatcher 
                        = request.getRequestDispatcher("showEmployee.jsp");
                dispatcher.forward(request, response);
            } else {
            	String message = "EmployeeNot Exists";
            	request.setAttribute("message", message);
     	        RequestDispatcher dispatcher
     	                = request.getRequestDispatcher("messagePrint.jsp");
     	        dispatcher.forward(request, response);
            }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
    }
     

    /**
     * Employee Id and details are get from user and,
     * update into database. 
     *
     * @param request   it contains employeeId and details to update.
     * @param response  send response of the request to user.
     */
	public void updateEmployee
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("employeeId"));
	    	String name = request.getParameter("name");
	       	Float salary = Float.parseFloat(request.getParameter("salary"));
	       	String mobileNumber = request.getParameter("mobileNumber");
	       	String date = request.getParameter("dateOfBirth");
	       	java.util.Date DateOfBirth = employeeService.getDateOfBirth(date);
	    	employeeService.updateEmployee
	    	        (id, name, salary, mobileNumber, DateOfBirth);
	    	showEmployee(request,response);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
   }
   
    /**
     * Employee Id are get from user and,
     * Delete from database. 
     *
     * @param request   Http request it contains employeeId to delete.
     * @param response  send response of the request to user.
     */
   public void deleteOrRestoreEmployee
           (HttpServletRequest request, HttpServletResponse response) {
	   try {
		   System.out.println("hiialllakjeklde");
		   int id = Integer.parseInt(request.getParameter("employeeId"));
		   System.out.println(id);
	       String message =null;
	       if(employeeService.deleteOrRestoreEmployee(id)) {
	           message = "OPERATION SUCESSFULL!";    
		   } else {
			    message = "FAILED TO DELETE!..PLEASE TRY AGAIN";    
		   }
		   request.setAttribute("message", message);
	       RequestDispatcher dispatcher
	               = request.getRequestDispatcher("messagePrint.jsp");
	       dispatcher.forward(request, response);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
    }
}

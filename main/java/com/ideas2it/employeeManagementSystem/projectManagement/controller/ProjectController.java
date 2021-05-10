package com.ideas2it.employeeManagementSystem.projectManagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeeManagementSystem.projectManagement.service.impl.ProjectServiceImpl;
import com.ideas2it.exception.EmployeeManagementException;
import com.ideas2it.employeeManagementSystem.projectManagement.service.ProjectService;

/**
 * ProjectController for doing CRUD operation.
 * 
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectServiceImpl();
       
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
		        addProject(request, response);
		    	break;
		    case "view":
		        showProject(request, response);
		    	break;
		    case "edit":
		    	updateProject(request, response);
		    	break;
		    case "assignAndUnassign":
		        assignAndUnassignEmployees(request, response);
		        break;
		    default:
		    	break;
	    }
    }
	
    /**
     * get request action from jsp and transfer to do post method.
     * 
     * @param request  it contains action to perform operation.
     * @param response  send response of the request to user.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		switch (action) {
		    case "callProjectPage":
	    	    callProjectPage(request, response);
	    	    break;
		    case "showProjects":
		    	getAllProject(request, response);
		    	break;
		    case "show":
		    	getShowForEdit(request, response);
		    	break;
		    case "callViewPage":
		    	callViewProjectPage(request, response);
		    	break;
		    case "callAddProjectForm":
		    	addProjectFormcall(request, response);
		    	break;
		    case "deleteOrRestore":
		    	deleteOrRestoreProject(request, response);
		    	break;
		    case "getAllProjectEmployees":
		        getAllProjectEmployees(request, response);
		        break;
		    case "callMainPage":
		        callMainPage(request, response);
		        break;
		    default:
		    	break;
	    }    
	}
	
    /**
     * project Id and employees id  get from user and,
     * assign or unassign employees from project.
     *
     * @param request   it contains projectId and employeesIds.
     * @param response  send response of the request to user.
     */
	private void assignAndUnassignEmployees
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
	        String employeeId[] = (request.getParameterValues("employeeId"));
		    List<Integer> employeesId = new ArrayList<Integer>();
		    int id = Integer.parseInt(request.getParameter("id"));
		    if(null != employeeId) {
		        for(int i = 0;i< employeeId.length; i ++) {
			        employeesId.add(Integer.parseInt(employeeId[i]));
		        }
		    }
		    projectService.assignProject(id,employeesId);
		    showProject(request, response);
		} catch (EmployeeManagementException e) {
    	    sendMessage(e.getMessage(), request, response);	
    	} 
	}


    /**
     * project Id and   get from user and get
     * assign or unassign employees for that project.
     *
     * @param request   it contains projectId.
     * @param response  send response of the request to user.
     */
	private void getAllProjectEmployees
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<List<String>> projectEmployeesList
			        = projectService.getProjectEmployees(id);
			List<String> projectDetails = new LinkedList<String>();
    	    projectDetails = projectService.getProjectDetails(id);
            request.setAttribute("view", projectDetails);
			request.setAttribute("projectEmployees", projectEmployeesList);
	        RequestDispatcher dispatcher
	                = request.getRequestDispatcher("viewProject.jsp");
	        dispatcher.forward(request, response);
    	} catch (EmployeeManagementException e) {
    	    sendMessage(e.getMessage(), request, response);	
    	}catch (ServletException | IOException  e) {
    		String message = "Fail to Display Project Employees Details..Try Again!..";
    		sendMessage(message, request, response);	
    	}
	}

    /**
     * project Id and   get from user and get
     * details of the project to show.
     *
     * @param request   it contains projectId.
     * @param response  send response of the request to user.
     */
	private void getShowForEdit
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(request.getParameter("id"));
    	    List<String> projectDetails = new LinkedList<String>();
			projectDetails = projectService.getProjectDetails(id);
            request.setAttribute("view", projectDetails);
            RequestDispatcher dispatcher 
                    = request.getRequestDispatcher("addProject.jsp");
            dispatcher.forward(request, response);
		} catch (EmployeeManagementException e) {
    	    sendMessage(e.getMessage(), request, response);	
    	} catch (ServletException | IOException  e) {
    		String message = "Fail to Display Employee Details For Update..Try Again!..";
    		sendMessage(message, request, response);	
    	}
	}

	/**
     * allProject details get from database and send to view.
     *
     * @param request  http request to get all project details 
     * @param response  send response of the request to user.
     */
	private void getAllProject
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			String userRequest = request.getParameter("isDeleted");
			boolean isDeleted = Boolean.parseBoolean(userRequest);
	        request.setAttribute("allProject", projectService.getProjects(isDeleted));
	        if(isDeleted) {
	            RequestDispatcher dispatcher
	                    = request.getRequestDispatcher("restoreProject.jsp");
	            dispatcher.forward(request, response);
	        } else {
	        	RequestDispatcher dispatcher
                        = request.getRequestDispatcher("projectHomePage.jsp");
                dispatcher.forward(request, response);
	        }
    	} catch (EmployeeManagementException e) {
    	    sendMessage(e.getMessage(), request, response);	
    	} catch (ServletException | IOException  e) {
    		String message = "Fail to Display Details..Try Again!..";
    		sendMessage(message, request, response);	
    	}
	}
	
	/**
     * call addPROJECT fORM page for CREATE NEW PROJECT.
     *
     * @param request   it contains request of newForm
     * @param response  send response of the request to user.
     */
	private void addProjectFormcall
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("addProject.jsp");
    	} catch (IOException  e) {
    		String message = "Fail To LoadPage..Try Again.."; 
    		sendMessage(message, request, response);	
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
    	} catch (IOException  e) {
    		String message = "Fail To LoadPage..Try Again.."; 
    		sendMessage(message, request, response);	
    	}
	}

	
	/**
     * call viewProject  page for user view project details.
     *
     * @param request   it contains request of view page.
     * @param response  send response of the request to user.
     */
	private void callViewProjectPage
	        (HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("viewProject.jsp");
    	} catch (IOException  e) {
    		String message = "Fail To LoadPage..Try Again.."; 
    		sendMessage(message, request, response);	
    	}
	}
	
	/**
     * call projectHome page for doing crud operation.
     *
     * @param request   it contains request of projectPage
     * @param response  send response of the request to user.
     */
    private void callProjectPage
            (HttpServletRequest request, HttpServletResponse response) {
    	try {
    		response.sendRedirect("projectHomePage.jsp");
    	} catch (IOException  e) {
    		String message = "Fail To LoadPage..Try Again.."; 
    		sendMessage(message, request, response);	
    	}
	}

	/**
     * project Details are get from user and,
     * insert details to database.
     *
     * @param request   it contains project details to create.
     * @param response  send response of the request to user.
	 * @throws IOException 
	 * @throws ServletException 
     */
    public void addProject
            (HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            int idForCreate  = Integer.parseInt(id);
            String name = request.getParameter("name");
            String manager = request.getParameter("manager");
            String department = request.getParameter("department");
            int timePeriod =Integer.parseInt(request.getParameter("timePeriod"));
            if (0 == idForCreate) {
                int projectId = projectService.addProject
                    (name, manager, department, timePeriod);
                String message = "SUCESSFULLY ADDED! Your ProjectId is " + projectId;
                sendMessage(message, request, response);	
            } else {
                updateProject(request, response);
            }
        } catch (EmployeeManagementException e) {
            sendMessage(e.getMessage(), request, response);	
        }
    }

	/**
     * project Id are get from user and,
     * give details to view.
     *
     * @param request   it contains projectId to get details.
     * @param response  send response of the request to user.
     */
    public void showProject
            (HttpServletRequest request, HttpServletResponse response) {
    	try {
            int id = Integer.parseInt(request.getParameter("id"));
    	    List<String> projectDetails = new LinkedList<String>();
            if(projectService.checkProjectIdExists(id)) {
        	    projectDetails.add("Employee Not Exists"); 
            } else {
    	        projectDetails = projectService.getProjectDetails(id);
            }
            request.setAttribute("view", projectDetails);
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("viewProject.jsp");
            dispatcher.forward(request, response);
    	} catch (EmployeeManagementException e) {
    	    sendMessage(e.getMessage(), request, response);	
    	} catch (ServletException | IOException  e) {
    		String message = "Failed to Display..";
    		sendMessage(message, request, response);	
    	}
    }
      
    /**
     * project Details are get from user and,
     * update into database. 
     *
     * @param request   it contains projectDetails to update.
     * @param response  send response of the request to user.
     */
    public void updateProject
            (HttpServletRequest request, HttpServletResponse response) {
    	try {
    		int id = Integer.parseInt(request.getParameter("id"));
         	String name = request.getParameter("name");
         	String manager = request.getParameter("manager");
         	String department = request.getParameter("department");
         	int timePeriod = Integer.parseInt(request.getParameter("timePeriod"));
        	projectService.updateProject
        	        (id, name, manager, department,timePeriod);
        	String message = "SUCESSFULLY UPDATED!";    
        	sendMessage(message, request, response);	
    	} catch (EmployeeManagementException e) {
    	    sendMessage(e.getMessage(), request, response);	
    	}
    }
    
    /**
     * project Id are get from user and,
     * Delete from database. 
     *
     * @param request   it contains projectId to delete.
     * @param response  send response of the request to user.
     */
    public void deleteOrRestoreProject
            (HttpServletRequest request, HttpServletResponse response) {
    	try {
    		int id = Integer.parseInt(request.getParameter("id"));
            projectService.deleteOrRestoreProjectDetails(id);
            String message = "OPERATION SUCESSFULL!";
            sendMessage(message, request, response);	
    	} catch (EmployeeManagementException e) {
    	    sendMessage(e.getMessage(), request, response);	
    	}
    }
    
    /**
     * view message to user whether operation sucessfully 
     * done or not
     *
     * @param message       it contains message to display.
     * @param request   it contains projectId to delete.
     * @param response  send response of the request to user.
     */
    private void sendMessage(String message, HttpServletRequest request,
    		HttpServletResponse response) {
	    try {
            request.setAttribute("message", message);
		    RequestDispatcher dispatcher 
                    = request.getRequestDispatcher("messagePrint.jsp");
            dispatcher.forward(request, response);
	    } catch(ServletException | IOException  exception) {
	    	exception.printStackTrace();	
	    }	
    }
}
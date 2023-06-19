package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageEvents;
import managers.ManageUsers;
import models.Event;
import models.User;

/**
 * Servlet implementation class GetFollowedUsers
 */
@WebServlet("/GetOwnLeftTimeline")
public class GetOwnLeftTimeline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOwnLeftTimeline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("notfollowd");
		List<User> users = Collections.emptyList();
		List<Event> events = Collections.emptyList();
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		if (session != null || user != null) {
			
			ManageUsers userManager = new ManageUsers();
			//users = userManager.getNotFollowedUsers(user.getId(), user.getSport_interests(), 0, 4);
			users = userManager.getAllNotFollowedUsers(user.getId(), 0, 4);
			user = userManager.getUser(user.getId());
			userManager.finalize();
			ManageEvents eventManger = new ManageEvents();
			
			events = eventManger.getEvents(user);
			eventManger.finalize();
		
		}

		request.setAttribute("users",users);
		request.setAttribute("events",events);
		request.setAttribute("zone","OwnTimeline");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewNotFollowedUsers.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

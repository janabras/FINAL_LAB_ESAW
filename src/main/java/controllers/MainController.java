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

import managers.ManageTweets;
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if ((session==null || session.getAttribute("user")==null) && request.getParameter("uname")==null) {
			System.out.println("MainController: NO active session has been found.");
			// Load all tweets
			List<Tweet> tweets = Collections.emptyList();
			ManageTweets tweetManager = new ManageTweets();
			tweets = tweetManager.getRecentTweets(0,4);
			tweetManager.finalize();
			request.setAttribute("tweets",tweets);
			// Load templates
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			request.setAttribute("content","ViewTweets.jsp");
		}
		else if(request.getParameter("uname")!=null) {
			String uname = request.getParameter("uname");
			ManageUsers userManager = new ManageUsers();
			User user = userManager.getUser(uname);
			user.setId(-1); // Identify that it's not the owner
			userManager.finalize();
			request.setAttribute("user", user);
			// Load templates
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			request.setAttribute("content","ViewUserInfo.jsp");
		}
		else {
			System.out.println("Main Controller: active session has been found.");
			request.setAttribute("menu","ViewMenuLogged.jsp");
			request.setAttribute("content","ViewOwnTimeline.jsp");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageComments;
import managers.ManageTweets;
import managers.ManageUsers;
import models.Comment;
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
		// No session
		if ((session==null || session.getAttribute("user")==null) && request.getParameter("uname")==null) {
			System.out.println("MainController: NO active session has been found.");
			// Load all tweets
			List<Tweet> tweets = Collections.emptyList();
			ManageTweets tweetManager = new ManageTweets();
			tweets = tweetManager.getRecentTweets(0,10);
			tweetManager.finalize();
			request.setAttribute("tweets",tweets);

			ManageComments commentManager = new ManageComments();
			
			List<Comment> comments = Collections.emptyList();
			
			comments = commentManager.getComments();
			
			commentManager.finalize();
			
			request.setAttribute("comments", comments);
			
			// Load templates
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			request.setAttribute("content","ViewTweets.jsp");
		}
		// Request to get the edit/view profile
		
		/*
		else if(request.getParameter("uname")!=null) {
			String uname = request.getParameter("uname");
			ManageUsers userManager = new ManageUsers();
			System.out.println("Uname: " + uname);
			User user = userManager.getUser(uname);
			if(Objects.isNull(user.getId())) user.setId(-1); // Identify that it's not the owner
			userManager.finalize();
			
			// Load templates
			//if(Objects.nonNull(user.getName())) {
			if (session==null || session.getAttribute("user")==null) {
				user.setId(-1);
				request.setAttribute("menu","ViewMenuNotLogged.jsp");
				//request.setAttribute("menu","ViewMenuLogged.jsp");
			}
			else {
				User mainUser = (User) session.getAttribute("user");
				if(!mainUser.getName().equals(uname) && !mainUser.getIsAdmin()) user.setId(-1);
				request.setAttribute("menu","ViewMenuLogged.jsp");
				//request.setAttribute("menu","ViewMenuNotLogged.jsp");
			}
			request.setAttribute("user", user);
			request.setAttribute("content","ViewUserInfo.jsp");
		}
		*/
		
		// Registered and go home
		else {
			// Load all tweets
			List<Tweet> tweets = Collections.emptyList();
			ManageTweets tweetManager = new ManageTweets();
			User user = (User) session.getAttribute("user");
			tweets = tweetManager.getUsersFollowedTweets(user.getId(), 0, 10);
			
			for (Iterator<Tweet> iterator = tweets.iterator(); iterator.hasNext();) {
                Tweet next = iterator.next();
                next.setLiked(tweetManager.isLikedTweet(user.getId(), next.getId()));
            }
			
			tweetManager.finalize();
			request.setAttribute("tweets",tweets);
			request.setAttribute("user", user);
			request.setAttribute("menu","ViewMenuLogged.jsp");
			request.setAttribute("content","ViewTweets.jsp");
			request.setAttribute("isHome","true");
			ManageComments commentManager = new ManageComments();
			
			List<Comment> comments = Collections.emptyList();
			
			comments = commentManager.getComments();
			
			for (Iterator<Comment> iterator = comments.iterator(); iterator.hasNext();) {
				Comment next = iterator.next();
	            next.setLiked(commentManager.isLikedComment(user.getId(), next.getId()));
	        }
			
			commentManager.finalize();
			
			request.setAttribute("comments", comments);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		response.setContentType("text/html");
		dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


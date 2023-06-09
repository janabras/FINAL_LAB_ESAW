package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageUsers;
import managers.ManageComments;
import managers.ManageTweets;
import models.Comment;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetUserTweets")
public class GetUserTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserTweets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		if (session != null || user != null) {
			ManageTweets tweetManager = new ManageTweets();
			tweets = tweetManager.getUserTweets(user.getId(),0,4);
			
            for (Iterator<Tweet> iterator = tweets.iterator(); iterator.hasNext();) {
                Tweet next = iterator.next();
                next.setLiked(tweetManager.isLikedTweet(user.getId(), next.getId()));
            }
            
			request.setAttribute("user", user.getName());
			tweetManager.finalize();
		}
		request.setAttribute("tweets",tweets);
		request.setAttribute("user", user);
		
		ManageComments commentManager = new ManageComments();
		
		List<Comment> comments = Collections.emptyList();
		
		comments = commentManager.getComments();
		for (Iterator<Comment> iterator = comments.iterator(); iterator.hasNext();) {
			Comment next = iterator.next();
            next.setLiked(commentManager.isLikedComment(user.getId(), next.getId()));
        }
		commentManager.finalize();
		
		request.setAttribute("comments", comments);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewTweets.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
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

import org.apache.commons.beanutils.BeanUtils;
import managers.ManageUsers;
import managers.ManageTweets;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class AddTweetFromUser
 */
@WebServlet("/AccessProfile")
public class AccessProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("access", false);
		HttpSession session = request.getSession(false);
		ManageUsers userManager = new ManageUsers();
		User userAccesed = new User();
		
		try {
			BeanUtils.populate(userAccesed, request.getParameterMap());
			userAccesed = userManager.getUser(userAccesed.getName());
			if (session==null || session.getAttribute("user")==null) {
				//Get tweets
				List<Tweet> tweets = Collections.emptyList();
				
				ManageTweets tweetManager = new ManageTweets();
				tweets = tweetManager.getUserTweets(userAccesed.getId(),0,4);
				
				tweetManager.finalize();
				
				request.setAttribute("tweets",tweets);
					
			}
			else {
				User user = (User) session.getAttribute("user");
				if (user.getIsAdmin() || user.getId() == userAccesed.getId()) {
					request.setAttribute("access", true);
				}
				request.setAttribute("user", user);
				
				//Get tweets
				List<Tweet> tweets = Collections.emptyList();
				
				ManageTweets tweetManager = new ManageTweets();
				tweets = tweetManager.getUserTweets(userAccesed.getId(),0,4);
					
		        for (Iterator<Tweet> iterator = tweets.iterator(); iterator.hasNext();) {
		            Tweet next = iterator.next();
		            next.setLiked(tweetManager.isLikedTweet(user.getId(), next.getId()));
		        }
		          
				tweetManager.finalize();
				
				request.setAttribute("tweets",tweets);
				
			}
				
				
		} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		request.setAttribute("userAccesed", userAccesed);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUserInfo.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
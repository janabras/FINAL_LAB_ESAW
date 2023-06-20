package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageTweets;
import models.User;

@WebServlet("/DeleteTweetController")
public class DeleteTweetController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User adminUser = (User) session.getAttribute("user");
        
        if (adminUser != null && adminUser.getIsAdmin()) {
            int tweetId = Integer.parseInt(request.getParameter("tid"));
            int userId = Integer.parseInt(request.getParameter("uid"));
            
            ManageTweets tweetManager = new ManageTweets();
            tweetManager.deleteTweet(tweetId); 
            
            response.sendRedirect("AdminPanel.jsp"); 
        } else {
            response.sendRedirect("Login.jsp"); 
        }
    }

}


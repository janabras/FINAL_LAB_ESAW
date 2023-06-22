package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageTweets;
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class UserEditAdmin
 */
@WebServlet("/UserEditAdmin")
public class UserEditAdmin extends HttpServlet {
	private static final long serialVersionUID = 100L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditAdmin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User userAccesed = new User();
		ManageUsers userManager = new ManageUsers();
		
		try {
			BeanUtils.populate(userAccesed, request.getParameterMap());
			userAccesed = userManager.getUser(userAccesed.getName());
		} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		request.setAttribute("userAccesed", userAccesed);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditOtherUserInfo.jsp");
		dispatcher.forward(request, response);	
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

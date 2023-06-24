package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageComments;
import managers.ManageUsers;
import models.Comment;
import models.User;

/**
 * Servlet implementation class AddCommentLike
 */
@WebServlet("/AddCommentLike")
public class AddCommentLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentLike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User fuser = new User();
		Comment comment = new Comment();
		ManageComments commentManager = new ManageComments();
		ManageUsers usersManager = new ManageUsers();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		try {
			if (session != null || user != null) {
				BeanUtils.populate(comment, request.getParameterMap());
				commentManager.addLikeComment(user.getId(), comment.getId());
				commentManager.finalize();
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
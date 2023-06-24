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
import models.Comment;
import models.User;

@WebServlet("/AddComment")
public class AddComment extends HttpServlet {

	
	public AddComment() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comment comment = new Comment();
		ManageComments commentManager = new ManageComments();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try {
			if (session != null || user != null)
				BeanUtils.populate(comment, request.getParameterMap());
				comment.setUsername(user.getName());
				comment.setPostDateTime(new Timestamp(System.currentTimeMillis()));
				commentManager.addComment(comment);
				commentManager.finalize();

		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

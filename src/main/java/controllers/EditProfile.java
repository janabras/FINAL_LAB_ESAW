package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import org.apache.commons.beanutils.BeanUtils;
import java.io.FileOutputStream;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class AddTweetFromUser
 */
@WebServlet("/EditProfile")
@MultipartConfig
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        if (session != null && user != null) {
            ManageUsers userManager = new ManageUsers();
            try {
                BeanUtils.populate(user, request.getParameterMap());
                
                
                
                /*
                System.out.println("1get");
                Part filePart = request.getPart("picture");
                System.out.println("2get");

                if (filePart != null && filePart.getSize() > 0) {
                	InputStream fileContent = filePart.getInputStream();
                    System.out.println("3");

                	String fileName = "avatar_" + user.getName() + ".png";
                    String pathAvatars = "imgs/avatars";
                    File outputFile = new File(pathAvatars, fileName);
                    
                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = fileContent.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                    
                }
                
                
                // Get image
                Part filePart = request.getPart("picture");
                if (filePart != null && filePart.getSize() > 0) {
                    // Obtain input stream of the uploaded file
                    InputStream inputStream = filePart.getInputStream();

                    // Save the image to a specific directory
                    String fileName = "avatar_" + user.getName() + ".png";
                    String filePath = "imgs/avatars" + fileName;
                    File uploadedFile = new File(filePath);
                    Files.copy(inputStream, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Update the user object with the file path
                    user.setPicture(filePath);
                }
				*/
                // Modify the user in the database
                userManager.modifyUser(user);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            } finally {
                userManager.finalize();
            }

            // Set the updated user object as a request attribute
            request.setAttribute("user", user);
        }

        // Forward the request to the desired page
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        System.out.println("1post");

		doGet(request, response);
	}

}
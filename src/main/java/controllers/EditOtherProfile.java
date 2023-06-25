package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class EditOtherProfile
 */
@WebServlet("/EditOtherProfile")
@MultipartConfig
public class EditOtherProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOtherProfile() {
        super();
        // TODO Auto-generated constructor stub
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
                
                String jsonString = userAccesed.getSport_interests()[0];

                jsonString = jsonString.substring(1, jsonString.length() - 1);

                String[] interests = jsonString.split(",");
                
                Pattern pattern = Pattern.compile("\"(.*?)\"");
                for (int i = 0; i < interests.length; i++) {
                    Matcher matcher = pattern.matcher(interests[i]);
                    if (matcher.find()) {
                    	interests[i] = matcher.group(1);
                    }
                }
                userAccesed.setSport_interests(interests);
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
                
                if (userManager.isAvaiableWithoutId(userAccesed.getName(), "name", userAccesed.getId()) && userManager.isAvaiableWithoutId(userAccesed.getMail(), "mail", userAccesed.getId())){
                	userManager.modifyUser(userAccesed);
                	//request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                	request.setAttribute("userAccesed", userManager.getUser(userAccesed.getId()));
                	request.setAttribute("error", true);
                	request.setAttribute("access", true);
                	RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUserInfo.jsp");
            		dispatcher.forward(request, response);
                }
                
                
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            } finally {
                userManager.finalize();
            }

            // Set the updated user object as a request attribute
            //request.setAttribute("user", user);
        
        // Forward the request to the desired page
        //RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
        //dispatcher.forward(request, response);
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
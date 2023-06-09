package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import java.nio.file.Path;
/**
 * Servlet implementation class AddTweetFromUser
 */
@WebServlet("/EditProfile")
@MultipartConfig(fileSizeThreshold=1024*1024*2, 
maxFileSize=1024*1024*10, 
maxRequestSize=1024*1024*50)
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
                
               System.out.println("Guardando img");
                Part filePart = request.getPart("picture");

                // Obtener el flujo de entrada de la parte del archivo
                InputStream fileContent = filePart.getInputStream();

                // Crear una ruta y un nombre de archivo para guardar la imagen
                String fileName = "imagen.jpg";
                String filePath = "imgs/" + fileName;

                // Crear el flujo de salida hacia el archivo
                OutputStream outputStream = Files.newOutputStream(Paths.get(filePath));

                // Leer los datos del flujo de entrada y escribirlos en el flujo de salida
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // Cerrar los flujos de entrada y salida
                fileContent.close();
                outputStream.close();
                
                
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
                if (userManager.isAvaiableWithoutId(user.getName(), "name", user.getId()) && userManager.isAvaiableWithoutId(user.getMail(), "mail", user.getId())){
                	userManager.modifyUser(user);
                	// Forward the request to the desired page
                	request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
                    dispatcher.forward(request, response);
                } else {
                	request.setAttribute("user", userManager.getUser(user.getId()));
                	request.setAttribute("error", true);
                	RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewOwnInfo.jsp");
            		dispatcher.forward(request, response);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            } finally {
                userManager.finalize();
            }

            // Set the updated user object as a request attribute
            
        }

        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        /*Part filePart = request.getPart("picture");
        System.out.println("Guardando img");

        InputStream fileContent = filePart.getInputStream();

        String fileName = "imagen.jpg";
        String filePath = "imgs/" + fileName;

        OutputStream outputStream = Files.newOutputStream(Paths.get(filePath));

        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        fileContent.close();
        outputStream.close();*/
		doGet(request, response);
	}

}
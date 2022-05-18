package csci201_final_project;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import csci201_final_project.util.Constant;
import csci201_final_project.util.Helper;
import csci201_final_project.util.UserDataParser;

import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/RegisterDispatcher")
public class RegisterDispatcher extends HttpServlet {
	
	public Constant constant = new Constant();
	private UserDataParser parser = new UserDataParser();
	

	public RegisterDispatcher() {
	
	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		    	
	    	String email = request.getParameter("register-email");
	    	String userName = request.getParameter("register-username");
	    	String password = request.getParameter("register-password");
	    	String confirmPassword = request.getParameter("register-confirm-password");
	    
	
	    	if (password.compareTo(confirmPassword) != 0) {
	    		request.setAttribute("error", "Passwords do not match");
	            request.getRequestDispatcher("/auth.jsp").forward(request, response);
	    	}
	    	
	    	else if (!Helper.isValidEmail(email)) {
	    		request.setAttribute("error", "The email is not valid");
	            request.getRequestDispatcher("/auth.jsp").forward(request, response);
	
	    	}
	    	
	    	else if (Helper.emailAlreadyRegistered(email)) {
	    		request.setAttribute("error", "This email is already registered");
	            request.getRequestDispatcher("/auth.jsp").forward(request, response);
	    	}
	    	
	    	else if (Helper.usernameInUse(userName)) {
	    		request.setAttribute("error", "This username is already in use");
	            request.getRequestDispatcher("/auth.jsp").forward(request, response);
	    	}
	    	
	    	else if (!Helper.validName(userName)) {
	    		request.setAttribute("error", "The name is not valid");
	            request.getRequestDispatcher("/auth.jsp").forward(request, response);
	    	}
	    	
	    	else {
	    			
    			int userId = parser.registerUser(email, userName, password);
        		parser.createStatsRowForUser(userId);
	        		
	    		Cookie ck = new Cookie("userName", userName);  
	            response.addCookie(ck);              
	        	request.getRequestDispatcher("/index.jsp").include(request, response);
	    		
	    	}
    	
    	
    	
    }
    
    

}

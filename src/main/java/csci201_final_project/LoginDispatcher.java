package csci201_final_project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import csci201_final_project.util.Constant;
import csci201_final_project.util.UserDataParser;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Servlet implementation class LoginDispatcher
 */

@WebServlet("/LoginDispatcher")
public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private UserDataParser parser = new UserDataParser();
    
    public Constant constant = new Constant();
	
	public LoginDispatcher() {
		 try {
			 	Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (Exception ex) {
	            // handle the error
	        	System.out.println(ex.getMessage());
	        }
	}

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String email = request.getParameter("login-email");
    	String password = request.getParameter("login-password");
    	
    	String userName = parser.loginUser(email, password);
    	
    	if (userName == null) {
    		request.setAttribute("error", "Invalid email or password. Please try again.");
            request.getRequestDispatcher("/auth.jsp").forward(request, response);
    	} else {
    		Cookie ck = new Cookie("userName", userName);  
            response.addCookie(ck);                
        	request.getRequestDispatcher("/index.jsp").include(request, response);  
    	}
    	
    	
    }

}

package csci201_final_project.util;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import csci201_final_project.util.Constant;


public class Helper {
	
	
	
    /**
     * check if name is valid
     *
     * @param name the name user provides
     * @return valid or not valid
     */
    public static boolean validName(String name) {
        return Constant.namePattern.matcher(name).matches();
    }

    /**
     * check if email is valid
     *
     * @param email the email user provides
     * @return valid or not valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return Constant.emailPattern.matcher(email).matches();
    }


    /**
     * Check if email is already registered
     *
     * @param email
     * @param request
     * @param response
     * @return email registered or not
     * @throws ServletException
     * @throws IOException
     */
    public static boolean emailAlreadyRegistered(String email) {
    	
    	try {
		 	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        	System.out.println(ex.getMessage());
        }
    	
    	Constant constant = new Constant();
    	
    	String query = String.format("SELECT COUNT(email) FROM User WHERE User.email = \"%s\"", email);
    	try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
    		
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            
    		int foundMatch = rs.getInt("COUNT(email)");
    		if (foundMatch == 0) {  // email is not registered
    			return false;
    		}
    		return true;
            	
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in emailAlreadyRegistered(): " + ex.getMessage());
			return true;
		}
    }
    
    
public static boolean usernameInUse(String username) {
    	
    	try {
		 	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        	System.out.println(ex.getMessage());
        }
    	
    	Constant constant = new Constant();
    	
    	String query = String.format("SELECT COUNT(userName) FROM User WHERE User.userName = \"%s\"", username);
    	try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
    		
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            
    		int foundMatch = rs.getInt("COUNT(userName)");
    		if (foundMatch == 0) {  // username is not registered
    			return false;
    		}
    		return true;
            	
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in usernameInUse(): " + ex.getMessage());
			return true;
		}
    }
}

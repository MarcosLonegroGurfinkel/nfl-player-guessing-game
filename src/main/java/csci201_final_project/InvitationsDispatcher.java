package csci201_final_project;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csci201_final_project.util.UserDataParser;


@WebServlet("/InvitationsDispatcher")
public class InvitationsDispatcher extends HttpServlet{

	private static final long serialVersionUID = 1L;
	UserDataParser parser = new UserDataParser();

	public InvitationsDispatcher() {
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	Cookie[] cookies = request.getCookies();
		String userName = null;
		if (cookies != null){
			for (Cookie cookie : cookies){
				if (cookie.getName().compareTo("userName") == 0){
					userName = cookie.getValue();
				}
			}
		}
    	
    	List<String> invitations = parser.getUserInvitations(userName);
    	
    	request.setAttribute("invitations", invitations);
    	request.getRequestDispatcher("/invitations.jsp").include(request, response);  

    	
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	Cookie[] cookies = request.getCookies();
		String userName = null;
		if (cookies != null){
			for (Cookie cookie : cookies){
				if (cookie.getName().compareTo("userName") == 0){
					userName = cookie.getValue();
				}
			}
		}
    	
    	String invitationName = request.getParameter("invitation-name");
    	
    	
    	boolean exists = parser.checkIfUserExists(invitationName);
    	
    	if (exists) {
    		parser.createInvitation(userName, invitationName);
    		
    		request.getRequestDispatcher("/ConnectionDispatcher").forward(request, response);  

    		
    	} else {
    		request.setAttribute("error", "That username does not exist. Please try again!");
    		List<String> invitations = parser.getUserInvitations(userName);
        	request.setAttribute("invitations", invitations);
        	request.getRequestDispatcher("/invitations.jsp").include(request, response);  

    	}
    	
    	
    	
    }

}

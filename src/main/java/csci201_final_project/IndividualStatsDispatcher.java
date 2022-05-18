package csci201_final_project;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csci201_final_project.util.UserDataParser;


@WebServlet("/IndividualStatsDispatcher")
public class IndividualStatsDispatcher extends HttpServlet{
	
	UserDataParser parser = new UserDataParser();
	Map<String, Integer> stats = new TreeMap<String, Integer>();

	public IndividualStatsDispatcher() {
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
		
		stats = parser.getUserStats(userName);
		
		request.setAttribute("stats", stats);
		
    	request.getRequestDispatcher("/userStats.jsp").include(request, response);
		
    	
    }

}

package csci201_final_project;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;


/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/LogoutDispatcher")
public class LogoutDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
	    	for (Cookie cookie: cookies) {
	    		cookie.setMaxAge(0);
	    		response.addCookie(cookie);
	    	}
    	}

    	request.getRequestDispatcher("/index.jsp").include(request, response);  
    	
    }

}

package mypackage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Servlet implementation class GameDispatcher
 */
@WebServlet("/GameDispatcher.java")
public class GameDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public GameDispatcher() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        // TODO fetch Athlete info from API, Initialize FakeAPI by calling its init
        try {
        String url = "https://api.sportsdata.io/v3/nfl/scores/json/Teams?key=bbd0950f56cc41ae80c738564612d9f6";
        InputStream is = new URL(url).openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		StringBuilder sb = new StringBuilder();
		int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	 
		url = "https://api.sportsdata.io/v3/nfl/scores/json/Teams?key=bbd0950f56cc41ae80c738564612d9f6";
		is = new URL(url).openStream();
		rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		StringBuilder sb2 = new StringBuilder();
	    while ((cp = rd.read()) != -1) {
	      sb2.append((char) cp);
	    }
		FakeGameAPI.Init(sb.toString(), sb2.toString());
        }catch(Exception e){}
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
        request.getRequestDispatcher("Game.jsp").forward(request, response); //jsp name to be determined
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
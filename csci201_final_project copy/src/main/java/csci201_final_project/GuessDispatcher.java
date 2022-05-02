package csci201_final_project;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csci201_final_project.util.AthleteDataParser;

@WebServlet("/GuessDispatcher")
public class GuessDispatcher extends HttpServlet {
	
    public AthleteDataParser parser = new AthleteDataParser();


	public GuessDispatcher() {
		// TODO Auto-generated constructor stub
	}
	

	
	/**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }

}

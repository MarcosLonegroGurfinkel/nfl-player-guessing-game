package csci201_final_project;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csci201_final_project.util.AthleteDataParser;
import csci201_final_project.util.ComparisonResult;
import csci201_final_project.util.SinglePlayerGame;
import csci201_final_project.util.UserDataParser;


@WebServlet("/SinglePlayerGameDispatcher")
public class SinglePlayerGameDispatcher extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private AthleteDataParser athleteParser = new AthleteDataParser();
	private UserDataParser userParser = new UserDataParser();
	private SinglePlayerGame game;
	
	private List<Map<String, ComparisonResult> > allComparisons;

	public SinglePlayerGameDispatcher() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        InputStream stream = servletContext.getResourceAsStream("data.json");

        try {
        	athleteParser.parseFromJsontoDb(stream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        
        
    }
	

	/**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	game = new SinglePlayerGame();
    	allComparisons = new ArrayList<Map<String, ComparisonResult> >();
    	
    	// game.printAllAthleteNames();  // TODO: delete
    	
    	
    	request.getRequestDispatcher("/game.jsp").include(request, response);  

    }


	/**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	if (game == null) {  // FIXME: fix bug or create a good flow
    		request.getRequestDispatcher("/index.jsp").include(request, response);
    		return;
    	}
    	
    	// use to send guess and parse result
    	String targetAthleteName = game.getTargetAthleteName();
    	request.setAttribute("targetAthleteName", targetAthleteName);
    	System.out.println(targetAthleteName);
    	
    	String athleteName = request.getParameter("guess-name");
    	String actualName = athleteParser.getAthleteNameByComparison(athleteName);
		System.out.println("actual name " + actualName);

    	if (actualName == null) {
    		System.out.println("not found");
    		request.setAttribute("error", "The name is not valid, please try again.");
    		request.setAttribute("results", allComparisons);
    		request.setAttribute("guessNumber", game.getCurrentGuessNumber());
    		request.getRequestDispatcher("/game.jsp").include(request, response);
     		return;
    	}
    	
    	Map<String, ComparisonResult> comparison = game.compareGuess(actualName);
    	allComparisons.add(comparison);
    	    	
		request.setAttribute("guessNumber", game.getCurrentGuessNumber());
		request.setAttribute("results", allComparisons);
		
		if (game.isOver()) {
			if (game.isWon()) {
				request.setAttribute("gameOver", "won");
			}
			else {
				request.setAttribute("gameOver", "lost");
			}
			
			// save stats
			Cookie[] cookies = request.getCookies();
			String userName = null;
			if (cookies != null){
				for (Cookie cookie : cookies){
					if (cookie.getName().compareTo("userName") == 0){
						userName = cookie.getValue();
					}
				}
			}
			if (userName != null){
				userParser.saveUserStats(userName, game.isWon(), game.getCurrentGuessNumber());
			}
			
		}
		
    	request.getRequestDispatcher("/game.jsp").include(request, response);  

    	
    }

}

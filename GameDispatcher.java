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
        File file = new File ("/Users/yanyitian/eclipse-workspace/nfl-player-guessing-game/Players.json") ;
        Scanner scanner = new Scanner(file);
        String s1 = "";
        while (scanner.hasNext()) {
            s1 += scanner.nextLine();
        }
        
        file = new File ("/Users/yanyitian/eclipse-workspace/nfl-player-guessing-game/Teams.json") ;
        scanner = new Scanner(file);
        String s2 = "";
        while (scanner.hasNext()) {
            s2 += scanner.nextLine();
        }
        System.out.println(s1);
        FakeGameAPI.Init(s1, s2);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
 

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
        request.getRequestDispatcher("single-game.html").forward(request, response); //jsp name to be determined
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
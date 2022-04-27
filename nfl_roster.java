package hwinnie_CSCI201_final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


public class nfl_roster {
	private static Player[] getPlayerAPI() throws IOException {
		Player[] all_players = null;
		InputStream is = null;
		try {
			//URL url = new URL("https://api.sportsdata.io/v3/nfl/scores/json/Players?key=bbd0950f56cc41ae80c738564612d9f6");
			//HttpURLConnection con = (HttpURLConnection) url.openConnection();
			//con.setRequestMethod("GET");
			//con.setInstanceFollowRedirects(true);

			String url = "https://api.sportsdata.io/v3/nfl/scores/json/Players?key=bbd0950f56cc41ae80c738564612d9f6";
			is = new URL(url).openStream();
	    
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
	        //System.out.println(sb.toString());
	        
	        Gson gson = new Gson();
			all_players = gson.fromJson(sb.toString(), Player[].class);
			
			for (Player p: all_players) {
				System.out.println("PlayerID = " + p.getPlayerID());
				System.out.println("FullName = " + p.getName());
				System.out.println("Team = " + p.getTeam());
				System.out.println("Height = " + p.getHeight());
				System.out.println("Weight = " + p.getWeight());
				System.out.println("Position = " + p.getPosition());
				System.out.println("Age = " + p.getAge());
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	    	is.close();
	    	return all_players;
	    }
	}
	private static Team[] getTeamAPI() throws IOException {
		Team[] all_teams = null;
		InputStream is = null;
		try {
			String url = "https://api.sportsdata.io/v3/nfl/scores/json/Teams?key=bbd0950f56cc41ae80c738564612d9f6";
			is = new URL(url).openStream();
	    
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
	        //System.out.println(sb.toString());
	        
	        Gson gson = new Gson();
			all_teams = gson.fromJson(sb.toString(), Team[].class);
			
			for (Team t: all_teams) {
				System.out.println("Team = " + t.getTeam());
				System.out.println("Conference = " + t.getConference());
				System.out.println("Division = " + t.getDivision());
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	    	is.close();
	    	return all_teams;
	    }
	}
	private static Player[] readPlayerFile(Scanner scanner) {
    	scanner.reset();
    	String temp = "";
		try {
			System.out.println("What is the name of the file containing the player information?");
	    	File file = new File (scanner.next()) ;
			
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				//System.out.println("here");
				temp += scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			scanner = new Scanner(System.in);
			return readPlayerFile(scanner);
		}
		
		Gson gson = new Gson();
		Player[] all_players = gson.fromJson(temp, Player[].class);
		
		/*
		for (Player p: all_players) {
			System.out.println("PlayerID = " + p.getPlayerID());
			System.out.println("FullName = " + p.getName());
			System.out.println("Team = " + p.getTeam());
			System.out.println("Height = " + p.getHeight());
			System.out.println("Weight = " + p.getWeight());
			System.out.println("Position = " + p.getPosition());
			System.out.println("Age = " + p.getAge());
			System.out.println();
		}
		*/
		return all_players;
    }
	private static Team[] loadTeamInfo(Scanner scanner) throws FileNotFoundException {
    	File file = new File ("nfl_teams.json") ;
		scanner = new Scanner(file);
		String temp = "";
		while (scanner.hasNext()) {
			temp += scanner.nextLine();
		}
		//System.out.println(temp);
		Gson gson = new Gson();
		Team[] all_teams = gson.fromJson(temp, Team[].class);
		/*
		for (Team t: all_teams) {
			System.out.println("Team = " + t.getTeam());
			System.out.println("Conference = " + t.getConference());
			System.out.println("Division = " + t.getDivision());
			System.out.println();
		}
		*/
		return all_teams;
    }
	private static void saveToDatabase(Player[] all_players, Team[] all_teams) {
		int id_increment = 0;
		
		String db = "jdbc:mysql://localhost:3306/final_project";
    	String user = "root";
    	String pwd = "root";
    	
		Connection conn;
		Gson gson = new Gson();
    	try {
    		conn = DriverManager.getConnection(db, user, pwd);
			Statement st = conn.createStatement();
			String sql5 = "truncate Athletes;";
			st.executeUpdate(sql5);
			//System.out.println("done");
    	
	    	for (Player p : all_players) {
	    		String getDivision = "null";
	    		String getConference = "null";
	    		for (Team t : all_teams) {
	    			if (p.getTeam() == null) {break;}
	    			if (p.getTeam().equals(t.getTeam())) {
	    				getDivision = t.getDivision();
	    				getConference = t.getConference();
	    				break;
	    			}
	    		}
	    		String height = p.getHeight().replace("'", "''");
	    		String name = p.getName().replace("'", "''");
		    	String sql1 = "INSERT INTO Athletes (athleteId, fullName, conference, division, team, position, height, weight, age) VALUES (" + p.getPlayerID() + ", '" + name + "', '" + getConference + "', '" + getDivision + "', '" + p.getTeam() + "', '" + p.getPosition()+ "', '" + height + "', " + p.getWeight()+ ", " + p.getAge() + ");";
		    	//System.out.println(sql1);
		    	st.executeUpdate(sql1);
		    	id_increment++;
			}
	    	conn.close();
    	}
    	
    	catch (SQLException ex) {
    		System.out.println ("SQLException: " + ex.getMessage());
    	}
	}
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
    	Scanner scanner = new Scanner(System.in);
    	//Player[] all_players = readPlayerFile(scanner);
    	Player[] all_players = null;
		try {
			all_players = getPlayerAPI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println(all_players.length + " players");
    	//Team[] all_teams = loadTeamInfo(scanner);
    	Team[] all_teams = null;
		try {
			all_teams = getTeamAPI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	saveToDatabase(all_players, all_teams);
    	scanner.close();
    }
}
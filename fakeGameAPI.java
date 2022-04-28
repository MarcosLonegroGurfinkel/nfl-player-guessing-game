package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.google.gson.Gson;

public class FakeGameAPI {
	  private static Boolean ready = false;
	  static public Player[] all_players = null;
	  static public Team[] all_teams = null;
	  static public Player targetPlayer = null;
	  static public Team targetTeam = null;
	  private static Random rand = new Random();
	 public static void Init(String playerinfo, String teaminfo) {
        if (ready) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ready = true;
        Gson gson = new Gson();
		all_players = gson.fromJson(playerinfo, Player[].class);
		all_teams = gson.fromJson(teaminfo, Team[].class);
		//find targetPlayer and targetTeam
		int id = rand.nextInt(all_players.length);
		targetPlayer = all_players[id];
		for(Team t: all_teams) {
			if(targetPlayer.getTeam().contentEquals(t.getTeam())) {
				targetTeam = t;
				break;
			}
		}
		try(Connection conn = DriverManager.getConnection(Constant.url , Constant.DBUserName, Constant.DBPassword);
		    Statement st = conn.createStatement();){
			for(Player i: all_players) {
				Team team = null;
				for(Team t: all_teams) {
					if(i.getTeam().contentEquals(t.getTeam())) {
						team = t;
						break;
					}
				}
				String sql = "INSERT INTO Athletes VALUES(NULL,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, i.getName());
				pst.setString(2, team.getConference());
				pst.setString(3, team.getDivision());
				pst.setString(4,i.getTeam());
				pst.setString(5, i.getPosition());
				pst.setString(6, i.getHeight());
				pst.setInt(7, i.getWeight());
				pst.setInt(8, i.getAge());
				pst.executeUpdate();
			}
		}catch(SQLException e) {
			System.out.println ("SQLException : " + e.getMessage());
		}
	 }
	 
	 //once you start the game, display all Athletes by calling getAllAthletesNames()
	 public List<String> getAllAthletesNames() {
		 List<String> names = new ArrayList<String>();
		 for(Player i: all_players) {
			 names.add(i.getName());
		 }
		 return names;
	 }
	 
	 //in each guess, call compare() to compare the inputted athlete to the target athlete
	 public HashMap<String, String> compare(String inputtedPlayerName) {
		 HashMap<String, String> map = new HashMap<String, String>();
		 //get inputtedPlayer info
		 try {
	            Class.forName("com.mysql.jdbc.Driver");

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        try(Connection conn = DriverManager.getConnection(Constant.url , Constant.DBUserName, Constant.DBPassword);
					Statement st = conn.createStatement();){
	        	String sql = "select * from Athletes where fullName = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, inputtedPlayerName);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					String conference = rs.getString("conference");
					String division = rs.getString("division");
					String team = rs.getString("team");
					String position = rs.getString("position");
					String height = rs.getString("height");
					int weight = rs.getInt("weight");
					int age = rs.getInt("age");
					if(conference.contentEquals(targetTeam.getConference())) {
						map.put("Conference", "green");
					}
					else {
						map.put("Conference", "gray");
					}
					if(division.contentEquals(targetTeam.getDivision())) {
						map.put("Division", "green");
					}
					else {
						map.put("Division", "gray");
					}
					if(team.contentEquals(targetTeam.getTeam())) {
						map.put("Team", "green");
					}
					else {
						map.put("Team", "gray");
					}
					if(position.contentEquals(targetPlayer.getPosition())) {
						map.put("Pos", "green"); //yellow if incorrect but in the right offense/defense???
					}
					else {
						map.put("Pos", "gray");
					}
					if(compareHeight(targetPlayer.getHeight(), height) == 0) {
						map.put("Height", "green");
					}
					else if(compareHeight(targetPlayer.getHeight(), height) > 0) {
						map.put("Height", "higher");
					}
					else {
						map.put("Height", "lower");
					}
					if(weight == targetPlayer.getWeight()) {
						map.put("Weight", "green");
					}
					else if(weight > targetPlayer.getWeight()) {
						map.put("Weight", "lower");
					}
					else {
						map.put("Weight", "higher");
					}
					if(age == targetPlayer.getAge()) {
						map.put("#", "green");
					}
					else if(age > targetPlayer.getAge()) {
						map.put("#", "lower");
					}
					else {
						map.put("#", "higher");
					}
				}
				return map;
			}
			catch(SQLException ex){
				System.out.println ("SQLException : " + ex.getMessage());
			}
	        return null;
	 }
	 
	 private int compareHeight(String a, String b) {
		 String[] temp = a.split("'");
		 int a1 = Integer.parseInt(temp[0]);
		 temp = temp[1].split("\"");
		 int a2 = Integer.parseInt(temp[0]);
		 
		 temp = b.split("'");
		 int b1 = Integer.parseInt(temp[0]);
		 temp = temp[1].split("\"");
		 int b2 = Integer.parseInt(temp[0]);
		 
		 if(a1 < b1) {
			 return -1;
		 }
		 else if(a1 > b1) {
			 return 1;
		 }
		 else {
			 if(a2 < b2) {
				 return -1;
			 }
			 else if(a2 > b2) {
				 return 1;
			 }
			 else {
				 return 0;
			 }
		 }
	 }
	 
	 
}

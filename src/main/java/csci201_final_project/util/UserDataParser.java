package csci201_final_project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;

public class UserDataParser {
	
	private Constant constant = new Constant();

	public UserDataParser() {
		try {
		 	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        	System.out.println(ex.getMessage());
        }
	}
	
	
	public int registerUser(String email, String userName, String password) {  // registers user in User table and returns userId
		
		int userId = 0;
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
			String sql = "INSERT INTO User (email, userName, password) VALUES (?, ?, ?)";
    		PreparedStatement statement = conn.prepareStatement(sql);
    		statement.setString(1, email);
    		statement.setString(2, userName);
    		statement.setString(3, password);
    		int result = statement.executeUpdate();
    		
    		userId = getUserId(userName);

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in registerUser(): " + ex.getMessage());
		}
		
		return userId;
		
	}
	
	public String loginUser(String email, String password) {  // returns userName
		String userName = null;
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
    		
        	String query = String.format("SELECT * FROM User WHERE User.email = \"%s\" AND User.password = \"%s\"", email, password);

    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()){
            	userName = rs.getString("userName");
            }
           		
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in loginUser(): " + ex.getMessage());
		}
		
		return userName;
		
	}
	
	public void createStatsRowForUser(int userId) {  // creates stats row for user with id userId
		
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
			String statsSql = "INSERT INTO UserStats (userId) VALUES (?)";
			PreparedStatement statsStatement = conn.prepareStatement(statsSql);
			statsStatement.setInt(1, userId);
    		int result = statsStatement.executeUpdate();

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in createStatsRowForUser(): " + ex.getMessage());
		}
		
		
	}
	
	public void saveUserStats(String userName, boolean gameWon, int numberGuesses) {
		
		// get userId from userName
		int userId = getUserId(userName);
		
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	// get row values using userId
			
			String sqlQuery = "SELECT * FROM UserStats WHERE userId = " + Integer.toString(userId);
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            rs.next();			
			
			// based on those values, update values in row appropiately
		
            int gamesPlayed = rs.getInt("gamesPlayed");
            int gamesWon = rs.getInt("gamesWon");
            int currentStreak = rs.getInt("currentStreak");
            int maxStreak = rs.getInt("maxStreak");
            int winsInFirst = rs.getInt("winsInFirst");
            int winsInSecond = rs.getInt("winsInSecond");
            int winsInThird = rs.getInt("winsInThird");
            int winsInFourth = rs.getInt("winsInFourth");
            int winsInFifth = rs.getInt("winsInFifth");
            int winsInSixth = rs.getInt("winsInSixth");
            int winsInSeventh = rs.getInt("winsInSeventh");
            int winsInEighth = rs.getInt("winsInEighth");

            
            gamesPlayed += 1;
            if (gameWon) {
            	gamesWon += 1;
            	currentStreak += 1;
            	if (currentStreak > maxStreak) {
            		maxStreak = currentStreak;
            	}
            	
            	if (numberGuesses == 1) {
            		winsInFirst += 1;
            	} else if (numberGuesses == 2) {
            		winsInSecond += 1;
            	} else if (numberGuesses == 3) {
            		winsInThird += 1;
            	} else if (numberGuesses == 4) {
            		winsInFourth += 1;
            	} else if (numberGuesses == 5) {
            		winsInFifth += 1;
            	} else if (numberGuesses == 6) {
            		winsInSixth += 1;
            	} else if (numberGuesses == 7) {
            		winsInSeventh += 1;
            	} else {
            		winsInEighth += 1;
            	}
            	
            } else {
            	currentStreak = 0;
            }
            
            
            // update row
			String sql = "UPDATE UserStats "
					+ "SET gamesPlayed = " + Integer.toString(gamesPlayed)
					+ ", gamesWon = " + Integer.toString(gamesWon)
					+ ", currentStreak = " + Integer.toString(currentStreak)
					+ ", maxStreak = " + Integer.toString(maxStreak)
					+ ", winsInFirst = " + Integer.toString(winsInFirst)
					+ ", winsInSecond = " + Integer.toString(winsInSecond)
					+ ", winsInThird = " + Integer.toString(winsInThird)
					+ ", winsInFourth = " + Integer.toString(winsInFourth)
					+ ", winsInFifth = " + Integer.toString(winsInFifth)
					+ ", winsInSixth = " + Integer.toString(winsInFifth)
					+ ", winsInSeventh = " + Integer.toString(winsInSeventh)
					+ ", winsInEighth = " + Integer.toString(winsInEighth)
					+ " WHERE userId = " + Integer.toString(userId);
			
    		Statement statement = conn.createStatement();
    		int result = statement.executeUpdate(sql);
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in saveUserStats(): " + ex.getMessage());
		}
		
		
	}
	
	
	public Map<String, Integer> getUserStats(String userName){
		
		Map<String, Integer> stats = new TreeMap<String, Integer>();
		
		int userId = getUserId(userName);
		
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
			String statsSql = "SELECT * FROM UserStats WHERE userId = " + Integer.toString(userId);
			Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statsSql);
            rs.next();			
			
	        stats.put("gamesPlayed", rs.getInt("gamesPlayed"));
	        stats.put("gamesWon", rs.getInt("gamesWon"));
	        stats.put("currentStreak", rs.getInt("currentStreak"));
	        stats.put("maxStreak", rs.getInt("maxStreak"));
	        stats.put("winsInFirst", rs.getInt("winsInFirst"));
	        stats.put("winsInSecond", rs.getInt("winsInSecond"));
	        stats.put("winsInThird", rs.getInt("winsInThird"));
	        stats.put("winsInFourth", rs.getInt("winsInFourth"));
	        stats.put("winsInFifth", rs.getInt("winsInFifth"));
	        stats.put("winsInSixth", rs.getInt("winsInSixth"));
	        stats.put("winsInSeventh", rs.getInt("winsInSeventh"));
	        stats.put("winsInEighth", rs.getInt("winsInEighth"));
	    		
	        
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in getUserStats(): " + ex.getMessage());
		}
		
		return stats;
	}
	
	public List<String> getUserInvitations(String userName) {
		
		List<String> invitations = new ArrayList<String>();
		
		int userId = getUserId(userName);
		
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
			
			String sql = "SELECT user1Id FROM UserInvitation WHERE user2Id = " + Integer.toString(userId);
			Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				invitations.add(getUserName(rs.getInt("user1Id")));		
			}
			
		
		} catch (SQLException ex) {
			System.out.println ("SQLException in getUserInvitations(): " + ex.getMessage());
		}
		
		return invitations;
		
	}
	
	public boolean checkIfUserExists(String userName) {
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
			
			String sql = "SELECT * FROM User WHERE userName = \"" + userName + "\"";
			Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		
		} catch (SQLException ex) {
			System.out.println ("SQLException in checkIfUserExists(): " + ex.getMessage());
		}
		
		return false;
		
	}
	
	
	public void createInvitation(String senderUserName, String receiverUserName) {
		
		int user1Id = getUserId(senderUserName);
		int user2Id = getUserId(receiverUserName);
		
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
			String sql = "INSERT INTO UserInvitation (user1Id, user2Id) VALUES (?, ?)";
    		PreparedStatement statement = conn.prepareStatement(sql);
    		statement.setInt(1, user1Id);
    		statement.setInt(2, user2Id);
    		int result = statement.executeUpdate();

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in createInvitation(): " + ex.getMessage());
		}
		
	}
	

	public void createMultiPlayerEntry(String userName) {
		int userId = getUserId(userName);
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
			String sql = "INSERT INTO MultiplayerGame (user1Id) VALUES (?)";
    		PreparedStatement statement = conn.prepareStatement(sql);
    		statement.setInt(1, userId);
    		int result = statement.executeUpdate();
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in createMultiPlayerEntry(): " + ex.getMessage());
		}
		
	}
	
	public void completeMultiPlayerEntry(String userName1, String userName2) {
		int userId1 = getUserId(userName1);
		int userId2 = getUserId(userName2);
		
		// get gameId containing userId1
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
            
            String sql = "UPDATE MultiplayerGame SET user2Id = " + Integer.toString(userId2) + " WHERE user1Id = " + Integer.toString(userId1);
            Statement statement = conn.createStatement();
    		int result = statement.executeUpdate(sql);
      
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in completeMultiPlayerEntry(): " + ex.getMessage());
		}
		
		
	}
	
	public void saveMultiplayerGameStats(String userName, boolean won, int numberGuesses) {
		int userId = getUserId(userName);
		int wonInt;
    	if (won == true) {
    		wonInt = 1;
    	} else {
    		wonInt = 0;
    	}
		
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){

			// check if userName is user1 or user2, then update accordingly
						
    		String sqlQuery = "SELECT * FROM MultiplayerGame WHERE user1Id = " + Integer.toString(userId);
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {  // it was user1
            	
            	String updateQuery1 = "UPDATE MultiplayerGame SET user1Won = " + Integer.toString(wonInt) + ", user1Guesses = " + Integer.toString(numberGuesses) + " WHERE user1Id = " + Integer.toString(userId);
            	Statement state = conn.createStatement();
         		state.executeUpdate(updateQuery1);
         		
            	
            } else { 
                
                String updateQuery2 = "UPDATE MultiplayerGame SET user2Won = " + Integer.toString(wonInt) + ", user2Guesses = " + Integer.toString(numberGuesses) + " WHERE user2Id = " + Integer.toString(userId);
            	Statement state = conn.createStatement();
         		state.executeUpdate(updateQuery2);
         		
            }

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in saveMultiplayerGameStats(): " + ex.getMessage());
		}
		
	}
	
	public void deleteInvitation(String userName1, String userName2) {
		int user1Id = getUserId(userName1);
		int user2Id = getUserId(userName2);
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
			String sql = "DELETE FROM UserInvitation WHERE user1Id = " + Integer.toString(user1Id) + " AND user2Id = " + Integer.toString(user2Id);
            Statement statement = conn.createStatement();
    		statement.executeUpdate(sql);

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in deleteInvitation(): " + ex.getMessage());
		}
		
	}
	
	public List<Map<String, String> > getAllMultiplayerStats(){
		List<Map<String, String> > allStats = new ArrayList<Map<String, String> >();
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
    		String sqlQuery = "SELECT * FROM MultiplayerGame";
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
            	Map<String, String> gameStats = new TreeMap<String, String>();
            	
            	String player1 = getUserName(rs.getInt("user1Id"));
            	String player2 = getUserName(rs.getInt("user2Id"));
            	gameStats.put("player1", player1);
            	gameStats.put("player2", player2);
            	
            	// compute winner
            	if (rs.getInt("user1Won") > rs.getInt("user2Won")){
            		gameStats.put("winner", player1);
            	} else if (rs.getInt("user1Won") < rs.getInt("user2Won")) {
            		gameStats.put("winner", player2);
            	} else {
            		
            		try {
            		
	            		if (rs.getInt("user1Guesses") < rs.getInt("user2Guesses")) {
	            			gameStats.put("winner", player1);
	            		} else if (rs.getInt("user1Guesses") > rs.getInt("user2Guesses")) {
	            			gameStats.put("winner", player2);
	            		} else {
	            			gameStats.put("winner", "Tie");
	            		}
            		
            		} catch (Exception e) {
            			gameStats.put("winner", "Tie");
            		}
            	}
            	
            	allStats.add(gameStats);
            }

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in getAllMultiplayerStats(): " + ex.getMessage());
		}
		
		return allStats;
		
	}
	
	
	
	private int getUserId(String userName) {
		
		int userId = 0;
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
    		String sqlQuery = "SELECT userId FROM User WHERE userName LIKE \"" + userName + "\"";
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            rs.next();
            userId = rs.getInt("userId");

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in getUserId(): " + ex.getMessage());
		}
		
		return userId;
		
	}
	
	
	private String getUserName(int userId) {
		
		String userName = new String();
		
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
    		String sqlQuery = "SELECT userName FROM User WHERE userId = " + Integer.toString(userId);
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            rs.next();
            
            userName = rs.getString("userName");

				
		} catch (SQLException ex) {
			System.out.println ("SQLException in getUserName(): " + ex.getMessage());
		}
		
		return userName;
		
	}

}

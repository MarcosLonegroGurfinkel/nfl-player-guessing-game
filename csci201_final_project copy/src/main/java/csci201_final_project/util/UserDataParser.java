package csci201_final_project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		int userId = 0;  // FIXME
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
			String sql = "INSERT INTO User (email, userName, password) VALUES (?, ?, ?)";
    		PreparedStatement statement = conn.prepareStatement(sql);
    		statement.setString(1, email);
    		statement.setString(2, userName);
    		statement.setString(3, password);
    		int result = statement.executeUpdate();
    		
    		userId = getUserId(userName);

				
		} catch (SQLException ex) {
			System.out.println ("SQLException: " + ex.getMessage());
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
			System.out.println ("SQLException: " + ex.getMessage());
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
			System.out.println ("SQLException: " + ex.getMessage());
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
			System.out.println ("SQLException: " + ex.getMessage());
		}
		
		
	}
	
	private int getUserId(String userName) {
		
		int userId = 0;  // FIXME
		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
	    	
    		String sqlQuery = "SELECT userId FROM User WHERE userName LIKE \"" + userName + "\"";
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            rs.next();
            userId = rs.getInt("userId");

				
		} catch (SQLException ex) {
			System.out.println ("SQLException: " + ex.getMessage());
		}
		
		return userId;
		
	}

}

package csci201_final_project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import csci201_final_project.util.Athlete;


public class AthleteDataParser {
	
	Constant constant = new Constant();

	public void parseFromJsontoDb(InputStream inputStream) throws IOException, ParseException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	  
        Gson gson = new Gson();
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<Athlete> athletesRaw = Arrays.asList(gson.fromJson(reader, Athlete[].class));
        
        List<Athlete> athletes = new ArrayList<Athlete>();

        for (Athlete athlete : athletesRaw) {
        	if (athlete.hasAllInformation()) {
        		athlete.generateAge();
            	athlete.generateConferenceAndDivision();
            	athlete.generateFullName();
        		athletes.add(athlete);
        	}
        }
        
        try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
    		
    		String sqlLoaded = "SELECT * FROM Athlete";
			Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery(sqlLoaded);
            
            boolean alreadyLoaded = false;
            if (resultSet.next()) {
            	alreadyLoaded = true;
            }
            
            if (!alreadyLoaded) {
            
            	for (Athlete athlete : athletes) {
            		
            		String sql = "INSERT INTO Athlete (fullName, conference, division, team, position, height, weight, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    				PreparedStatement statement = conn.prepareStatement(sql);
    				statement.setString(1, athlete.getFullName());
    				statement.setString(2, athlete.getConference());
    				statement.setString(3, athlete.getDivision());
    				statement.setString(4, athlete.getTeam());
    				statement.setString(5, athlete.getPosition());
    				statement.setString(6, athlete.getHeight());
    				statement.setInt(7, athlete.getWeight());
    				statement.setInt(8, athlete.getAge());
    				int result = statement.executeUpdate();
            		
            	}
            	
            }
		
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in Parser.parseFromJson(): " + ex.getMessage());
		}
	        
	}
	
	public List<Athlete> getAthletes(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
        List<Athlete> athletes = new ArrayList<Athlete>();

		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
    		
    		String sql = "SELECT * FROM Athlete";
			Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
            	Athlete currentAthlete = new Athlete();
            	currentAthlete.setFullName(resultSet.getString("fullName"));
            	currentAthlete.setConference(resultSet.getString("conference"));
            	currentAthlete.setDivision(resultSet.getString("division"));
            	currentAthlete.setTeam(resultSet.getString("team"));
            	currentAthlete.setPosition(resultSet.getString("position"));
            	currentAthlete.setHeight(resultSet.getString("height"));
            	currentAthlete.setWeight(resultSet.getInt("weight"));
            	currentAthlete.setAge(resultSet.getInt("age"));
            	athletes.add(currentAthlete);
            }
            		
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in Parser.getAthletes(): " + ex.getMessage());
		}
		
		 return athletes;
	}
	
	public String getAthleteNameByComparison(String nameToCompare) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
        String athleteName = null;

		try (Connection conn = DriverManager.getConnection(constant.DBURL, constant.DBUserName, constant.DBPassword)){
    		
    		String sql = "SELECT fullName FROM Athlete WHERE fullName LIKE \"%" + nameToCompare + "%\"";
    		System.out.println(sql);
			Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            int validResultCounter = 0;
            while (resultSet.next()) {
            	athleteName = resultSet.getString("fullName");
            	validResultCounter += 1;
            }
            
            if (validResultCounter > 1) {
            	return null;
            }
            		
				
		} catch (SQLException ex) {
			System.out.println ("SQLException in Parser.getAthletes(): " + ex.getMessage());
		}
		
		 return athleteName;
	}

}

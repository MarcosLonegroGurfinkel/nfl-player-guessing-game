package csci201_final_project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Athlete {
	
	
	// parsed from json
	private String Team;
	private String FirstName;
	private String LastName;
	private String Position;
	private String Height;
	private int Weight;
	private String BirthDate;
	
	// created from json data
	private String fullName;
	private String conference;
	private String division;
	private int age;

	public Athlete() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTeam(String team) {
		this.Team = team;
	}
	
	public String getTeam() {
		return Team;
	}
	
	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setLastName(String lastName) {
		this.LastName = lastName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setPosition(String position) {
		this.Position = position;
	}
	
	public String getPosition() {
		return Position;
	}
	
	public void setHeight(String height) {
		this.Height = height;
	}
	
	public String getHeight() {
		return Height;
	}
	
	public void setWeight(int weight) {
		this.Weight = weight;
	}
	
	public int getWeight() {
		return Weight;
	}
	
	public void setBirthDate(String birthDate) {
		this.BirthDate = birthDate;
	}
	
	public String getBirthDate() {
		return BirthDate;
	}
	
	public void generateFullName() {
		fullName = FirstName + " " + LastName;
	}
	
	public void generateConferenceAndDivision() {
		List<String> afcNorth = Arrays.asList("BAL", "CIN", "CLE", "PIT");
		List<String> afcEast = Arrays.asList("BUF", "MIA", "NE", "NYJ");
		List<String> afcSouth = Arrays.asList("HOU", "IND", "JAX", "TEN");
		List<String> afcWest = Arrays.asList("KC", "LAC", "LV", "DEN");
		
		List<String> nfcNorth = Arrays.asList("MIN", "GB", "CHI", "DET");
		List<String> nfcEast = Arrays.asList("PHI", "NYG", "WAS", "DAL");
		List<String> nfcSouth = Arrays.asList("NO", "CAR", "TB", "ATL");
		List<String> nfcWest = Arrays.asList("LAR", "SEA", "ARI", "SF");


		if (afcNorth.contains(Team)) {
			conference = "AFC";
			division = "North";
		}
		else if (afcEast.contains(Team)) {
			conference = "AFC";
			division = "East";
		}
		else if (afcSouth.contains(Team)) {
			conference = "AFC";
			division = "South";
		}
		else if (afcWest.contains(Team)) {
			conference = "AFC";
			division = "West";
		}
		else if (nfcNorth.contains(Team)) {
			conference = "NFC";
			division = "North";
		}
		else if (nfcEast.contains(Team)) {
			conference = "NFC";
			division = "East";
		}
		else if (nfcSouth.contains(Team)) {
			conference = "NFC";
			division = "South";
		}
		else if (nfcWest.contains(Team)) {
			conference = "NFC";
			division = "West";
		}
		
	}
	
	public void generateAge() throws ParseException {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		  String[] splitBirthDate = BirthDate.split("T");
		  
		  Date birthDate = sdf.parse(splitBirthDate[0]);
		  Date today = sdf.parse("2022-04-27");  // change to todays's date automatically
		  
		  long diffInMillies = Math.abs(today.getTime() - birthDate.getTime());
		  long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		  
		  age = (int) diffInDays / 365;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getConference() {
		return conference;
	}
	
	public String getDivision() {
		return division;
	}
	
	public int getAge() {
		return age;
	}
	
	public boolean hasAllInformation() {
		if (Team == null || FirstName == null || LastName == null || Position == null || Height == null || Weight == 0 || BirthDate == null) {
			return false;
		}
		return true;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setConference(String conference) {
		this.conference = conference;
	}
	
	public void setDivision(String division) {
		this.division = division;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

}

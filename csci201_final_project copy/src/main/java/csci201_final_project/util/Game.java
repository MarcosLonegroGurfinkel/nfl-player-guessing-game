package csci201_final_project.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class Game {
	
	private Map<String, Athlete> allAthletes = new TreeMap<String, Athlete>();  // name to athlete object
	private Athlete targetAthlete;
	boolean finished = false;
	boolean won = false;
	int guessCount = 0;
	
	private AthleteDataParser parser = new AthleteDataParser();

	public Game() {
		setAthletesMap();
		setTargetAthlete();
	}
	
	public String getTargetAthleteName() {
		return targetAthlete.getFullName();
	}
	
	public void printAllAthleteNames(){  // TODO: delete
		List<Athlete> athletes = parser.getAthletes();
		List<String> names = new ArrayList<String>();
		for (Athlete athlete : athletes) {
			String name = "";
			name += '"';
			name += athlete.getFullName();
			name += '"';
			names.add(name);
		}
		System.out.println(names);
	}
	
	public void setAthletesMap() {
		
		//  create athlete map from db
		List<Athlete> athleteList = parser.getAthletes();
		System.out.println("athlete List length");
		System.out.println(athleteList.size());
		for (Athlete athlete : athleteList) {
			allAthletes.put(athlete.getFullName(), athlete);
		}
		
		
	}
	
	public void setTargetAthlete() {
		
		// choose random athlete from all athletes
		Random generator = new Random();
		Object[] values = allAthletes.values().toArray();
		targetAthlete = (Athlete) values[generator.nextInt(values.length)];
		
	}
	
	public Map<String, ComparisonResult> compareGuess(String athleteGuessName){
		
		guessCount += 1;
		
		Athlete guessedAthlete = allAthletes.get(athleteGuessName);
		
		Map<String, ComparisonResult> comparison = new TreeMap<String, ComparisonResult>();
		
		if (guessedAthlete.getFullName().compareTo(targetAthlete.getFullName()) == 0) {
			finished = true;
			won = true;
		}
		
		if (guessCount == 8) {
			finished = true;
		}
		
		
		if (guessedAthlete.getFullName().compareTo(targetAthlete.getFullName()) == 0) {
			comparison.put("name", new ComparisonResult("name", guessedAthlete.getFullName(), "green"));
		} else {
			comparison.put("name", new ComparisonResult("name", guessedAthlete.getFullName(), "grey"));
		}
		
		if (guessedAthlete.getConference().compareTo(targetAthlete.getConference()) == 0) {
			comparison.put("conference", new ComparisonResult("conference", guessedAthlete.getConference(), "green"));
		} else {
			comparison.put("conference", new ComparisonResult("conference", guessedAthlete.getConference(), "grey"));
		}
		
		if (guessedAthlete.getDivision().compareTo(targetAthlete.getDivision()) == 0) {
			comparison.put("division", new ComparisonResult("division", guessedAthlete.getDivision(), "green"));
		} else {
			comparison.put("division", new ComparisonResult("division", guessedAthlete.getDivision(), "grey"));
		}
		
		if (guessedAthlete.getTeam().compareTo(targetAthlete.getTeam()) == 0) {
			comparison.put("team", new ComparisonResult("team", guessedAthlete.getTeam(), "green"));
		} else {
			comparison.put("team", new ComparisonResult("team", guessedAthlete.getTeam(), "grey"));
		}
		
		List<String> offense = Arrays.asList("QB", "TE", "WR", "C", "G", "OT", "FB", "RB", "OL");
		List<String> defense = Arrays.asList("DE", "FS", "SS", "CB", "DT", "ILB", "OLB", "DL", "DB", "S", "NT", "LB");
		List<String> special = Arrays.asList("P", "K", "LS");
		
		if (guessedAthlete.getPosition().compareTo(targetAthlete.getPosition()) == 0) {
			comparison.put("position", new ComparisonResult("position", guessedAthlete.getPosition(), "green"));
		} else if (offense.contains(guessedAthlete.getPosition()) && offense.contains(targetAthlete.getPosition())) {
			comparison.put("position", new ComparisonResult("position", guessedAthlete.getPosition(), "yellow"));
		} else if (defense.contains(guessedAthlete.getPosition()) && defense.contains(targetAthlete.getPosition())) {
			comparison.put("position", new ComparisonResult("position", guessedAthlete.getPosition(), "yellow"));
		} else if (special.contains(guessedAthlete.getPosition()) && special.contains(targetAthlete.getPosition())) {
			comparison.put("position", new ComparisonResult("position", guessedAthlete.getPosition(), "yellow"));
		} else {
			comparison.put("position", new ComparisonResult("position", guessedAthlete.getPosition(), "grey"));
		}
		
		if (guessedAthlete.getAge() == targetAthlete.getAge()) {
			comparison.put("age", new ComparisonResult("age", Integer.toString(guessedAthlete.getAge()), "green"));
		} else if (guessedAthlete.getAge() > targetAthlete.getAge()) {
			comparison.put("age", new ComparisonResult("age", Integer.toString(guessedAthlete.getAge()), "target_lower"));
		} else {
			comparison.put("age", new ComparisonResult("age", Integer.toString(guessedAthlete.getAge()), "target_higher"));
		}
		
		if (guessedAthlete.getWeight() == targetAthlete.getWeight()) {
			comparison.put("weight", new ComparisonResult("weight", Integer.toString(guessedAthlete.getWeight()), "green"));
		} else if (guessedAthlete.getWeight() > targetAthlete.getWeight()) {
			comparison.put("weight", new ComparisonResult("weight", Integer.toString(guessedAthlete.getWeight()), "target_lower"));
		} else {
			comparison.put("weight", new ComparisonResult("weight", Integer.toString(guessedAthlete.getWeight()), "target_higher"));
		}
		
		int heightComparisonResult = compareHeight(guessedAthlete.getHeight(), targetAthlete.getHeight());
		if (heightComparisonResult == 0) {
			comparison.put("height",  new ComparisonResult("height", guessedAthlete.getHeight(), "green"));
		} else if (heightComparisonResult == 1) {
			comparison.put("height", new ComparisonResult("height", guessedAthlete.getHeight(), "target_lower"));
		} else {
			comparison.put("height", new ComparisonResult("height", guessedAthlete.getHeight(), "target_higher"));
		}
		
		
		return comparison;
	}
	
	public boolean isOver() {
		return finished;
	}
	
	public boolean isWon() {
		return won;
	}
	
	public int getCurrentGuessNumber() {
		return guessCount;
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

package hwinnie_CSCI201_final;

public class Team {
	private String Key;
	private String Division;
	private String Conference;
	public Team(String Key, String Division, String Conference) {
		this.Key = Key;
		this.Conference = Conference;
		this.Division = Division;
	}
	public String getTeam() {
		return Key;
	}
	public String getConference() {
		return Conference;
	}
	public String getDivision() {
		return Division;
	}
}
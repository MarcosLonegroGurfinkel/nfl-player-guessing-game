package hwinnie_CSCI201_final;

public class Player {
	private String PlayerID;
	private String Team;
	private String FirstName;
	private String LastName;
	private String Height;
	private int Weight;
	private String Position;
	private int Age;
	
	public Player(String PlayerID, String Team, String FirstName, String LastName, String Height, int Weight, String Position, int Age) {
		this.PlayerID = PlayerID;
		this.Team = Team;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Height = Height;
		this.Weight = Weight;
		this.Position = Position;
		this.Age = Age;
	}
	
	public String getName() {
		return FirstName + " " + LastName;
	}
	public String getPlayerID() {
		return PlayerID;
	}
	public String getTeam() {
		return Team;
	}
	public String getHeight() {
		return Height;
	}
	public int getWeight() {
		return Weight;
	}
	public String getPosition() {
		return Position;
	}
	public int getAge() {
		return Age;
	}
}
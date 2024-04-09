package football;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Comparable<Team> ,Serializable{
	private String teamName;
	private ArrayList<Player> players;
	
	
	public Team(String teamName) {
		super();
		this.teamName = teamName;
		this.players = new ArrayList<Player>();
	}

	public void addPlayer(Player player) {
		if (players.size() >= 11) {
		} else {
			players.add(player);
		}
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public ArrayList<Player> getPlayer() {
		return players;
	}

	public void setPlayer(ArrayList<Player> players) {
		this.players = players;
	}

	@Override
	public int compareTo(Team o) {
		return this.teamName.compareToIgnoreCase(o.teamName);
	}

}

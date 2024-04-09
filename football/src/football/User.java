package football;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {
	private String emailId;
	private String pw;
	private String teamName;
	private int balance;
	private ArrayList<Player> playerList;

	public User(String emailId, String pw) {
		this.emailId = emailId;
		this.pw = pw;
	}

	public User(String emailId, String pw, String teamName, ArrayList<Player> playerList) {
		super();
		this.emailId = emailId;
		this.pw = pw;
		this.teamName = teamName;
		this.balance = 10000;
		this.playerList = playerList;
	}

	public String getIemailId() {
		return emailId;
	}

	public void setId(String emailId) {
		this.emailId = emailId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(emailId, other.emailId);
	}

	@Override
	public String toString() {
		return "emailId=" + emailId + ", pw=" + pw + ", teamName=" + teamName + ", balance=" + balance;
	}

}

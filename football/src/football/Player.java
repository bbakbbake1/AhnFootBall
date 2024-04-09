package football;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Comparable<Player> ,Serializable{
	private String name;
	private int backNum;
	private int shot;
	private int pass;
	private int def;
	private String position;
	private int price;
	private int index;

	public Player(String name, int backNum, int shot, int pass, int def, String position, int price) {
		super();
		this.name = name;
		this.backNum = backNum;
		this.shot = shot;
		this.pass = pass;
		this.def = def;
		this.position = position;
		this.price = price;
		switch (position) {
		case "gk":
			this.index = 0;
			break;
		case "df":
			this.index = 1;
			break;
		case "mf":
			this.index = 2;
			break;
		case "fw":
			this.index = 3;
			break;
		}

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBackNum() {
		return backNum;
	}

	public void setBackNum(int backNum) {
		this.backNum = backNum;
	}

	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(backNum, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return backNum == other.backNum && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", backNum=" + backNum + ", shot=" + shot + ", pass=" + pass + ", def=" + def
				+ ", position=" + position + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Player o) {
		return this.index - o.index;
	}

}

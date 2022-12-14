package core;

import java.awt.Color;
import java.util.Objects;

public class Player {

	private String name;
	private Color discColor;
	private int score;

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public Color getDiscColor() {
		return discColor;
	}

//	Added or Modified Version 2
	public void setDiscColor(Color discColor) {
		if (null == discColor) {
			throw new NullPointerException();
		}
		this.discColor = discColor;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

//	Added or Modified Version 2
	public int hashCode() {
		return Objects.hash(discColor, name, score);
	}

//	Added or Modified Version 2
	public boolean equals(Object obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		return Objects.equals(discColor, other.discColor) && Objects.equals(name, other.name) && score == other.score;
	}

//	Added or Modified Version 2
	public String toString() {
		return "Player [name=" + name + ", discColor=" + discColor + ", score=" + score + "]";
	}

}

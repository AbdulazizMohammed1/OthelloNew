
package core;

import java.awt.Color;
import java.util.Objects;

public class Disc {

	private Color disccolor;

	public Color getDiscColor() {
		return disccolor;
	}

//	Added or Modified Version 2
	public void setdisccolor(Color discColor) {
		if (null == discColor) {
			throw new NullPointerException();
		}
		this.disccolor = discColor;
	}

//	Added or Modified Version 2
	public int hashCode() {
		return Objects.hash(disccolor);
	}

//	Added or Modified Version 2
	public boolean equals(Object obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Disc)) {
			return false;
		}
		Disc other = (Disc) obj;
		return Objects.equals(disccolor, other.disccolor);
	}

	public String toString() {
		return "Disc [discclr=" + disccolor + "]";
	}

}

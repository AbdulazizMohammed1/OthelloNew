package userInterface;

import core.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.JFrame;

public class OthelloUi extends JFrame {

	private Game g;
	private GameUi gUi;
	private BoardUi boardUi;

	public OthelloUi(Game g) {
		if (g == null) {
			throw new NullPointerException();
		}
		this.g = g;

		initComponents();
	}

	private void initComponents() {
		this.setPreferredSize(new Dimension(600, 600));
		this.setMinimumSize(new Dimension(600, 600));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gUi = new GameUi(g);
		boardUi = new BoardUi(g, gUi);

		this.add(gUi, BorderLayout.NORTH);
		this.add(boardUi, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public Game getG() {
		return g;
	}

	public void setG(Game g) {
		if (g == null) {
			throw new NullPointerException();
		}
		this.g = g;
	}

	public GameUi getgUi() {
		return gUi;
	}

	public void setgUi(GameUi gUi) {
		if (gUi == null) {
			throw new NullPointerException();
		}
		this.gUi = gUi;
	}

	public BoardUi getBoardUi() {
		return boardUi;
	}

	public void setBoardUi(BoardUi boardUi) {
		if (boardUi == null) {
			throw new NullPointerException();
		}
		this.boardUi = boardUi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(boardUi, g, gUi);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		OthelloUi other = (OthelloUi) obj;
		return Objects.equals(boardUi, other.boardUi) && Objects.equals(g, other.g) && Objects.equals(gUi, other.gUi);
	}

	@Override
	public String toString() {
		return "OthelloUi [g=" + g + ", gUi=" + gUi + ", boardUi=" + boardUi + "]";
	}

}

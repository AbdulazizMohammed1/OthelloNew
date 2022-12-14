package userInterface;

import core.Disc;
import core.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardUi extends JPanel {
	private static Color DARK = Color.BLACK; // Added or Modified Version 2
	private static Color LIGHT = Color.WHITE; // Added or Modified Version 2
	private static final Color EMPTY = null; // Added or Modified Version 2
	private static int PLAYER_ONE = 0; // Added or Modified Version 2
	private static int PLAYER_TWO = 1; // Added or Modified Version 2
	private static int ROWS = 8; // Added or Modified Version 2
	private static int COLUMNS = 8; // Added or Modified Version 2
	private JButton[][] brd;
	private BoardListener listener;
	private Game g;
	private GameUi gUi;

	public BoardUi(Game g, GameUi gUi) {
		this.g = g;
		this.gUi = gUi;
		initComponents();
		listener.updateUi();
	}

	private void initComponents() {
		int i;
		int j;

		this.setPreferredSize(new Dimension(400, 400));
		this.setMinimumSize(new Dimension(400, 400));

		this.setLayout(new GridLayout(ROWS, COLUMNS));

		brd = new JButton[ROWS][COLUMNS];
		listener = new BoardListener();

		for (i = 0; i < ROWS; i++) {
			for (j = 0; j < COLUMNS; j++) {
				brd[i][j] = new JButton();

				brd[i][j].putClientProperty("row", i);
				brd[i][j].putClientProperty("col", j);
				brd[i][j].putClientProperty("color", EMPTY);

				brd[i][j].setBackground(Color.WHITE);

				brd[i][j].addActionListener(listener);

				this.add(brd[i][j]);

			}
		}

	}

	private class BoardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {

				JButton button = (JButton) e.getSource();
				int row = (int) button.getClientProperty("row");
				int col = (int) button.getClientProperty("col");

				if (isvalidmove(row, col, g.getCurrentPlayer().getDiscColor())) {
					updateUi();
					chngp();
				} else
					JOptionPane.showMessageDialog(button, "Move is not valid, select another space.");
			}
		}

		public void updateUi() {
			Disc[][] discs = g.getboard().getBoard();
			ImageIcon disc = null;

			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLUMNS; col++) {
					try {

						if (discs[row][col].getDiscColor() == DARK) {
							disc = new ImageIcon(getClass().getResource("../images/colorDark.png"));
							brd[row][col].setIcon(disc);
							brd[row][col].putClientProperty("color", DARK);
						} else if (discs[row][col].getDiscColor() == LIGHT) {
							disc = new ImageIcon(getClass().getResource("../images/colorLight.png"));
							brd[row][col].setIcon(disc);
							brd[row][col].putClientProperty("color", LIGHT);
						}
					} catch (Exception e) {
						System.out.println("File Not Found. Please try again.");
						break;
					}
				}
			}

			gUi.getScoreOne().setText(String.valueOf(g.getPlayers().get(PLAYER_ONE).getScore()));
			gUi.getScoreTwo().setText(String.valueOf(g.getPlayers().get(PLAYER_TWO).getScore()));
		}

		public boolean isvalidmove(int row, int col, Color color) {
			if (color == null) {
				throw new NullPointerException();
			}

			boolean isvld = false;

			if (g.getboard().isValidMove(row, col, color)) {
				isvld = true;
			}

			return isvld;
		}

		public void chngp() {
			if (g.getCurrentPlayer() == g.getPlayers().get(PLAYER_ONE))
				g.setCurrentPlayer(g.getPlayers().get(PLAYER_TWO));
			else
				g.setCurrentPlayer(g.getPlayers().get(PLAYER_ONE));
		}

	}

	public JButton[][] getBrd() {
		return brd;
	}

	public void setBrd(JButton[][] brd) {
		this.brd = brd;
	}

	public BoardListener getListener() {
		return listener;
	}

	public void setListener(BoardListener listener) {
		if (listener == null) {
			throw new NullPointerException();
		}
		this.listener = listener;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(brd);
		result = prime * result + Objects.hash(g, gUi, listener);
		return result;
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
		BoardUi other = (BoardUi) obj;
		return Arrays.deepEquals(brd, other.brd) && Objects.equals(g, other.g) && Objects.equals(gUi, other.gUi)
				&& Objects.equals(listener, other.listener);
	}

	@Override
	public String toString() {
		return "BoardUi [brd=" + Arrays.toString(brd) + ", listener=" + listener + ", g=" + g + ", gUi=" + gUi + "]";
	}

}

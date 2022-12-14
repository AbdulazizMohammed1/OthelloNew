package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {
	private static Color DARK = Color.BLACK; // Added or Modified Version 2
	private static Color LIGHT = Color.WHITE; // Added or Modified Version 2
	private static final Color EMPTY = null; // Added or Modified Version 2
	private static int PLAYER_ONE = 0; // Added or Modified Version 2
	private static int PLAYER_TWO = 1; // Added or Modified Version 2
	private static int ROWS = 8; // Added or Modified Version 2
	private static int COLUMNS = 8; // Added or Modified Version 2
	private static int MAXIMUM_PLAYERS = 2; // Added or Modified Version 2
	private Disc[][] board;
	private int darkCount;
	private int lightCount;
	private ArrayList<Player> players;
//	Added or Modified Version 2
	private static Board single_instance = null;

//	Added or Modified Version 2
	private Board() {
		initObjects();
	}

//	Added or Modified Version 2
	public static Board getInstance() {
		if (null == single_instance) {
			single_instance = new Board();
		}
		return single_instance;
	}

	public Disc[][] getBoard() {
		return board;
	}

//	Added or Modified Version 2
	public void setboard(Disc[][] board) {
		if (null == board) {
			throw new NullPointerException();
		}
		this.board = board;
	}

	public List<Player> getplayers() {
		return players;
	}

//	Added or Modified Version 2

	public void setplayers(List<Player> players) {
		if (null == players) {
			throw new NullPointerException();
		}
		if (MAXIMUM_PLAYERS != players.size()) {
			throw new IndexOutOfBoundsException("The list must contain 2 players");
		}

		this.players = (ArrayList<Player>) players;
	}

	private void initObjects() {
		int traceRow;
		int traceColumn;
		board = new Disc[8][8];

		// For each space in our 2D array, populate the space with a new
		// Disc object.
		for (traceRow = 0; traceRow < 8; traceRow++) {
			for (traceColumn = 0; traceColumn < 8; traceColumn++) {
				board[traceRow][traceColumn] = new Disc();
			}
		}

		board[3][3].setdisccolor(LIGHT);
		board[3][4].setdisccolor(DARK);
		board[4][3].setdisccolor(DARK);
		board[4][4].setdisccolor(LIGHT);
	}

	public int getdarkcount() {
		return darkCount;
	}

	public void setdarkcount(int darkCount) {
		this.darkCount = darkCount;
	}

	public int getlightcount() {
		return lightCount;
	}

	public void setlightcount(int lightCount) {
		this.lightCount = lightCount;
	}

	public void tracePlayer() {
		int traceRow;
		int traceColumn;
		darkCount = 0;
		lightCount = 0;
		for (traceRow = 0; traceRow < ROWS; traceRow++) {
			for (traceColumn = 0; traceColumn < COLUMNS; traceColumn++) {
				if (board[traceRow][traceColumn].getDiscColor() == DARK) {
					darkCount++;
				} else if (board[traceRow][traceColumn].getDiscColor() == LIGHT) {
					lightCount++;
				}
			}
		}
		assignScores(darkCount, lightCount);

	}

	public void assignScores(int darkCount, int lightCount) {
		// Players is an array of both player 1(0) and 2(1). Get
		// the corresponding player at each index and set its score.
		players.get(PLAYER_ONE).setScore(darkCount);
		players.get(PLAYER_TWO).setScore(lightCount);

	}

//	Added or Modified Version 2
	public boolean checkUp(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}
		int flpsquare = 0;
		int checkRow = row - 1;
		boolean smec = false;
		boolean isvalid = false;

		while (checkRow >= 0 && !smec) {

			if (board[checkRow][col].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[checkRow][col].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkRow--;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				row--;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

//	Added or Modified Version 2
	public boolean checkUpLeft(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}

		int flpsquare = 0;
		int checkRow = row - 1;
		int checkCol = col - 1;
		boolean smec = false;
		boolean isvalid = false;

		while (checkRow >= 0 && checkCol >= 0 && !smec) {

			if (board[checkRow][checkCol].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[checkRow][checkCol].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkRow--;
			checkCol--;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				row--;
				col--;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

//	Added or Modified Version 2
	public boolean checkLeft(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}

		int flpsquare = 0;
		int checkCol = col - 1;
		boolean smec = false;
		boolean isvalid = false;

		while (checkCol >= 0 && !smec) {

			if (board[row][checkCol].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[row][checkCol].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkCol--;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				col--;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

//	Added or Modified Version 2
	public boolean checkDownLeft(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}

		int flpsquare = 0;
		int checkRow = row + 1;
		int checkCol = col - 1;
		boolean smec = false;
		boolean isvalid = false;

		while ((checkRow < ROWS && checkCol >= 0) && !smec) {

			if (board[checkRow][checkCol].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[checkRow][checkCol].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkRow++;
			checkCol--;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				row++;
				col--;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

//	Added or Modified Version 2
	public boolean checkDown(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}

		int flpsquare = 0;
		int checkRow = row + 1;
		boolean smec = false;
		boolean isvalid = false;

		while (checkRow < ROWS && !smec) {

			if (board[checkRow][col].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[checkRow][col].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkRow++;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				row++;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

//	Added or Modified Version 2
	public boolean checkDownRight(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}
		int flpsquare = 0;
		int checkRow = row + 1;
		int checkCol = col + 1;
		boolean smec = false;
		boolean isvalid = false;

		while ((checkRow < ROWS && checkCol < COLUMNS) && !smec) {

			if (board[checkRow][checkCol].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[checkRow][checkCol].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkRow++;
			checkCol++;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				row++;
				col++;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

//	Added or Modified Version 2
	public boolean checkRight(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}

		int flpsquare = 0;
		int checkCol = col + 1;
		boolean smec = false;
		boolean isvalid = false;

		while (checkCol < COLUMNS && !smec) {

			if (board[row][checkCol].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[row][checkCol].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkCol++;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				col++;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

//	Added or Modified Version 2
	public boolean checkUpRight(int row, int col, Color color) {
		if (0 > row || 0 > col) {
			return false;
		}
		if (null == color) {
			throw new NullPointerException();
		}

		int flpsquare = 0;
		int checkRow = row - 1;
		int checkCol = col + 1;
		boolean smec = false;
		boolean isvalid = false;

		while (checkRow >= 0 && checkCol < COLUMNS && !smec) {

			if (board[checkRow][checkCol].getDiscColor() == EMPTY) {
				return isvalid;
			} else if (board[checkRow][checkCol].getDiscColor() != color) {
				flpsquare++;
			} else {
				smec = true;
			}

			checkRow--;
			checkCol++;
		}

		if (smec && flpsquare > 0) {
			board[row][col].setdisccolor(color);
			do {
				row--;
				col++;
				flpsquare--;
				board[row][col].setdisccolor(color);
			} while (flpsquare > 0);

			isvalid = true;
			tracePlayer();

		} else {
			isvalid = false;
		}
		return isvalid;
	}

	public boolean boardFull() {
		return players.get(PLAYER_ONE).getScore() + players.get(PLAYER_TWO).getScore() == 64;
	}

//	Added or Modified Version 2
	public boolean gameOver(Color color) {
		if (null == color) {
			throw new NullPointerException();
		}

		boolean gameover = false;

		if (boardFull()) {
			gameover = true;
			return gameover;
		}
		if (!(hasMove(DARK) || hasMove(LIGHT))) {
			gameover = true;
		}
		return gameover;
	}

//	Added or Modified Version 2
	public boolean hasMove(Color color) {
		if (null == color) {
			throw new NullPointerException();
		}

		boolean ismove = false;

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				if (board[row][col].getDiscColor() == null && ismove) {
					ismove = isValidMove(row, col, color);
				}
			}
		}

		return ismove;
	}

//	Added or Modified Version 2
	public boolean isValidMove(int row, int col, Color color) {
		if (null == color) {
			throw new NullPointerException();
		}

		boolean isvalid = false;

		if (checkUp(row, col, color)) {
			isvalid = true;
		} else if (checkUpLeft(row, col, color)) {
			isvalid = true;
		} else if (checkLeft(row, col, color)) {
			isvalid = true;
		} else if (checkDownLeft(row, col, color)) {
			isvalid = true;
		} else if (checkDown(row, col, color)) {
			isvalid = true;
		} else if (checkDownRight(row, col, color)) {
			isvalid = true;
		} else if (checkRight(row, col, color)) {
			isvalid = true;
		} else if (checkUpRight(row, col, color)) {
			isvalid = true;
		}

		if (isvalid) {
			board[row][col].setdisccolor(color);
		}

		gameOver(color);

		return isvalid;

	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		result = prime * result + Objects.hash(darkCount, lightCount, players);
		return result;
	}

//	Added or Modified Version 2
	public boolean equals(Object obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Board)) {
			return false;
		}
		Board other = (Board) obj;
		return Arrays.deepEquals(board, other.board) && darkCount == other.darkCount && lightCount == other.lightCount
				&& Objects.equals(players, other.players);
	}

}

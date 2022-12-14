package core;

import java.awt.Color; // extra functionality
import java.awt.Dimension; // extra functionality
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.swing.JFrame; // extra functionality 
import javax.swing.JPanel; // extra functionality
import java.awt.Font; // extra functionality
import java.awt.event.ActionEvent; // extra functionality
import java.awt.event.ActionListener; // extra functionality
import java.util.concurrent.TimeUnit; // extra functionality
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton; // extra functionality
import javax.swing.JLabel; // extra functionality

public class Game {

	private static int MAXIMUM_PLAYERS = 2; // Added or Modified Version 2
	private static final int INITIAL_SCORE = 2; // Added or Modified Version 2
	private static int PLAYER_ONE = 0; // Added or Modified Version 2
	private static int PLAYER_TWO = 1; // Added or Modified Version 2
	private static Color DARK = Color.BLACK; // Added or Modified Version 2
	private static Color LIGHT = Color.WHITE; // Added or Modified Version 2

//	Added or Modified Version 2
	private static Game single_instance = null;

//	Added or Modified Version 2
	private Game() {

		initObjects();

	}

//	Added or Modified Version 2
	public static Game getInstance() {
		if (null == single_instance) {
			single_instance = new Game();
		}
		return single_instance;
	}

	private ArrayList<Player> players;
	private Board board;
	private Player currentPlayer;

	public Board getboard() {
		return board;
	}

//	Added or Modified Version 2
	public void setboard(Board board) {
		if (null == board) {
			throw new NullPointerException();
		}
		this.board = board;
	}

	public List<Player> getPlayers() {
		return players;
	}

//	Added or Modified Version 2
	public void setPlayers(List<Player> players) {
		if (null == players) {
			throw new NullPointerException();
		}
		if (MAXIMUM_PLAYERS != players.size()) {
			throw new IllegalArgumentException("There must be exactly two players");
		}

		this.players = (ArrayList<Player>) players;
	}

//	Added or Modified Version 2
	private void initObjects() {

		startScreen(); // extra functionality
		introductionPage(); // extra functionality
		instructionsFirstPage(); // extra functionality
		instructionsSecondPage(); // extra functionality
		instructionsThirdPage(); // extra functionality
		loadingPage(); // extra functionality
		board = Board.getInstance();
		createPlayers();
		board.setplayers(players);
		// printPlayers();
		players.get(PLAYER_ONE).setScore(INITIAL_SCORE);
		players.get(PLAYER_TWO).setScore(INITIAL_SCORE);
		currentPlayer = players.get(PLAYER_ONE);

	}

//	Added or Modified Version 2

	public void createPlayers() {

		players = new ArrayList<>();

		for (int playerCount = 0; playerCount < MAXIMUM_PLAYERS; playerCount++) {
			String askname = JOptionPane.showInputDialog(null, "Enter player's namee");
			Player player = new Player();
			player.setname(askname);

			if (playerCount == 0)

			{
				player.setDiscColor(DARK);
			} else {
				player.setDiscColor(LIGHT);
			}

			players.add(player);
		}
	}

	public void printPlayers() {
		System.out.println("The game has the following pls:");

		players.forEach((player) -> {
			System.out.println("Player " + player.getname() + " is playing disc color " + player.getDiscColor());
		});
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

//	Added or Modified Version 2
	public void setCurrentPlayer(Player currentPlayer) {
		if (null == currentPlayer) {
			throw new NullPointerException("Player Cannot be null");
		}
		this.currentPlayer = currentPlayer;
	}

	volatile boolean pressed = false; // extra functionality

	public void startScreen() { // extra functionality

		JFrame frame = new JFrame("Start Screen");
		JPanel panel = new JPanel();
		Font font = new Font("Courier", Font.BOLD, 16);
		JButton button = new JButton("Start Game");
		button.setBackground(Color.GREEN);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pressed = true;
				frame.setVisible(false);
				frame.dispose();
			}

		});

		JLabel label = new JLabel("Othello");
		label.setFont(font);
		Dimension buttonSize = button.getPreferredSize();
		Dimension labelSize = label.getPreferredSize();
		int buttonWidth = buttonSize.width;
		int buttonHeight = buttonSize.height;
		int labelWidth = labelSize.width;
		int labelHeight = labelSize.height;
		button.setBounds(300 - (int) (0.5 * buttonWidth),
				250 - (int) (0.5 * (buttonHeight + labelHeight)) + labelHeight, buttonWidth, buttonHeight);
		label.setBounds(300 - (int) (0.5 * labelWidth), 200 - (int) (0.5 * (buttonHeight + labelHeight)), labelWidth,
				labelHeight);
		panel.setLayout(null);
		panel.add(button);
		panel.add(label);
		panel.setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setResizable(false);
		frame.setVisible(true);

		while (!pressed) {
			System.out.print("Waiting");

		}

		pressed = false;
	}

	public void introductionPage() { // extra functionality

		JFrame frame = new JFrame("Introduction");
		JPanel panel = new JPanel();
		JButton button = new JButton("Next");
		button.setBackground(Color.GREEN);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pressed = true;
				frame.setVisible(false);
				frame.dispose();
			}

		});

		JLabel headLabel = new JLabel("SE411 Project");
		JLabel descriptionLabel = new JLabel(
				"<html>This game will be used in SE411 Software Construction course.<br> This is the second version of the code, in which we are trying to <br> refactor the code and enhance its quality and readability in general</html>");

		Dimension buttonSize = button.getPreferredSize();
		Dimension labelSize = descriptionLabel.getPreferredSize();
		int buttonWidth = buttonSize.width;
		int buttonHeight = buttonSize.height;
		int labelWidth = labelSize.width;
		int labelHeight = labelSize.height;
		button.setBounds(300 - (int) (0.5 * buttonWidth),
				450 - (int) (0.5 * (buttonHeight + labelHeight)) + labelHeight, buttonWidth, buttonHeight);
		descriptionLabel.setBounds(300 - (int) (0.5 * labelWidth), 300 - (int) (0.5 * (buttonHeight + labelHeight)),
				labelWidth, labelHeight);
		headLabel.setBounds(300 - (int) (0.5 * headLabel.getPreferredSize().width),
				200 - (int) (0.5 * (buttonHeight + labelHeight)), labelWidth, labelHeight);
		setUpFrame(frame, panel, new JLabel[] {headLabel, descriptionLabel});
		panel.add(button);   
		while (!pressed) {
			System.out.print("Waiting");
		}

		pressed = false;

	}

	public void instructionsFirstPage() { // extra functionality

		JFrame frame = new JFrame("Instructions");
		JPanel panel = new JPanel();

		JButton button = new JButton("Next");
		button.setBackground(Color.GREEN);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pressed = true;
				frame.setVisible(false);
				frame.dispose();
			}

		});

		JLabel headLabel = new JLabel("Basics of the game");

		JLabel descriptionLabel = new JLabel(
				"<html>Othello is a two player strategy board game. Their are<br> two types of discs 'Android' and 'Apple'. Android has the<br> first move. players play turn by turn. A turn consists<br> of making a move called an 'outflank'. The winner is<br> the one with most discs when the board is full<br> or their are no possible moves.</html>");

		Dimension buttonSize = button.getPreferredSize();
		Dimension labelSize = descriptionLabel.getPreferredSize();
		int buttonWidth = buttonSize.width;
		int buttonHeight = buttonSize.height;
		int labelWidth = labelSize.width;
		int labelHeight = labelSize.height;
		button.setBounds(300 - (int) (0.5 * buttonWidth),
				450 - (int) (0.5 * (buttonHeight + labelHeight)) + labelHeight, buttonWidth, buttonHeight);
		descriptionLabel.setBounds(300 - (int) (0.5 * labelWidth), 300 - (int) (0.5 * (buttonHeight + labelHeight)),
				labelWidth, labelHeight);
		headLabel.setBounds(300 - (int) (0.5 * headLabel.getPreferredSize().width),
				200 - (int) (0.5 * (buttonHeight + labelHeight)), labelWidth, labelHeight);
		setUpFrame(frame, panel, new JLabel[] {headLabel, descriptionLabel});
		panel.add(button);
		while (!pressed) {
			System.out.print("Waiting");

		}

		pressed = false;

	}

	public void instructionsSecondPage() { // extra functionality

		JFrame frame = new JFrame("Instructions");
		JPanel panel = new JPanel();

		JButton button = new JButton("Next");
		button.setBackground(Color.GREEN);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pressed = true;
				frame.setVisible(false);
				frame.dispose();
			}

		});

		JLabel headLabel = new JLabel("Outflanks");

		JLabel descriptionLabel = new JLabel(
				"<html>To outflank means to place a disc on the board<br> so that your opponent's row (or rows) of disc(s) is<br> bordered at each end by a disc of your color. A row <br>is defined as one or more discs in a continuous<br> straight line. A row may be horizontal, vertical <br>or diagonal.</html>");

		Dimension buttonSize = button.getPreferredSize();
		Dimension labelSize = descriptionLabel.getPreferredSize();
		int buttonWidth = buttonSize.width;
		int buttonHeight = buttonSize.height;
		int labelWidth = labelSize.width;
		int labelHeight = labelSize.height;
		button.setBounds(300 - (int) (0.5 * buttonWidth),
				450 - (int) (0.5 * (buttonHeight + labelHeight)) + labelHeight, buttonWidth, buttonHeight);
		descriptionLabel.setBounds(300 - (int) (0.5 * labelWidth), 300 - (int) (0.5 * (buttonHeight + labelHeight)),
				labelWidth, labelHeight);
		headLabel.setBounds(300 - (int) (0.5 * headLabel.getPreferredSize().width),
				200 - (int) (0.5 * (buttonHeight + labelHeight)), labelWidth, labelHeight);
		setUpFrame(frame, panel, new JLabel[] {headLabel, descriptionLabel});
		panel.add(button);

		while (!pressed) {
			System.out.print("Waiting");

		}

		pressed = false;

	}

	public void instructionsThirdPage() { // extra functionality

		JFrame frame = new JFrame("Instructions");
		JPanel panel = new JPanel();
		Font buttonFont = new Font("Courier", Font.BOLD, 8);
		JButton button = new JButton("Done");
		button.setBackground(Color.GREEN);
		button.setFont(buttonFont);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pressed = true;
				frame.setVisible(false);
				frame.dispose();
			}

		});

		JLabel headLabel = new JLabel("Declaring Players");

		JLabel descriptionLabel = new JLabel(
				"<html>After finishing the tutorial text box appears. <br> Fill names and choose who is going to be android</html>");
		descriptionLabel.setFont(buttonFont);
		Dimension buttonSize = button.getPreferredSize();
		Dimension labelSize = descriptionLabel.getPreferredSize();
		int buttonWidth = buttonSize.width;
		int buttonHeight = buttonSize.height;
		int labelWidth = labelSize.width;
		int labelHeight = labelSize.height;
		button.setBounds(300 - (int) (0.5 * buttonWidth),
				450 - (int) (0.5 * (buttonHeight + labelHeight)) + labelHeight, buttonWidth, buttonHeight);
		descriptionLabel.setBounds(300 - (int) (0.5 * labelWidth), 300 - (int) (0.5 * (buttonHeight + labelHeight)),
				labelWidth, labelHeight);
		headLabel.setBounds(300 - (int) (0.5 * headLabel.getPreferredSize().width),
				200 - (int) (0.5 * (buttonHeight + labelHeight)), labelWidth, labelHeight);
		setUpFrame(frame, panel, new JLabel[] {headLabel, descriptionLabel});
                panel.add(button);
		while (!pressed) {
			System.out.print("Waiting");

		}

		pressed = false;

	}

	public void loadingPage() { // extra functionality

		JFrame frame = new JFrame("Loading");
		JPanel panel = new JPanel();

		JLabel label = new JLabel("Loading");

		Dimension labelSize = label.getPreferredSize();
		int labelWidth = labelSize.width;
		int labelHeight = labelSize.height;
		label.setBounds(300 - (int) (0.5 * labelWidth), 300 - 2 * labelHeight, labelWidth + 50, labelHeight + 50);
                setUpFrame(frame, panel, new JLabel[] {label});
		// Provide loading animation

		try {

			TimeUnit.SECONDS.sleep(1);
			label.setText("Loading.");
			label.repaint();
			TimeUnit.SECONDS.sleep(1);
			label.setText("Loading..");
			label.repaint();
			TimeUnit.SECONDS.sleep(1);
			label.setText("Loading...");
			label.repaint();
			TimeUnit.SECONDS.sleep(1);
			label.setText("Loading");
			label.repaint();
			TimeUnit.SECONDS.sleep(1);

		} catch (InterruptedException ex) {

			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);

		}

		frame.setVisible(false);
		frame.dispose();

	}

//	Added or Modified Version 2
	public void setUpFrame(JFrame frame, JPanel panel, JLabel[] label) {
		panel.setLayout(null);
                for (int labelCount = 0; labelCount < label.length; labelCount++) {
                    panel.add(label[labelCount]);
                }
		panel.setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setResizable(false);
		frame.setVisible(true);
	}
        
//	Added or Modified Version 2
	public int hashCode() {
		return Objects.hash(board, currentPlayer, players);
	}

//	Added or Modified Version 2
	public boolean equals(Object obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Game)) {
			return false;
		}
		Game other = (Game) obj;
		return Objects.equals(board, other.board) && Objects.equals(currentPlayer, other.currentPlayer)
				&& Objects.equals(players, other.players);
	}

	public String toString() {
		return "Game [pls=" + players + ", brd=" + board + ", cp=" + currentPlayer + "]";
	}

}

package userInterface;

import core.Game;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUi extends JPanel {
        private static int PLAYER_ONE = 0; //	Added or Modified Version 2
	private static int PLAYER_TWO = 1; //	Added or Modified Version 2
	public GameUi(Game game) {
		if(game==null) {
			throw new NullPointerException();
		}
		this.game = game;
		initComponents();
	}

	private JLabel nameOne;
	private JLabel nameTwo;
	private JLabel scoreOne;
	private JLabel scoreTwo;
	private Game game;

	private void initComponents() {

		this.setPreferredSize(new Dimension(400, 50));
		this.setMinimumSize(new Dimension(400, 50));
                try {
                  
                    ImageIcon discOne = new ImageIcon(getClass().getResource("../images/colorDark.png"));
                    nameOne = new JLabel();
                    nameOne.setIcon(discOne);
                    nameOne.setText(game.getPlayers().get(PLAYER_ONE).getname());
                    nameOne.setMinimumSize(new Dimension(100, 50));
                    nameOne.setPreferredSize(new Dimension(100, 50));

                    ImageIcon discTwo = new ImageIcon(getClass().getResource("../images/colorLight.png"));                          
                    nameTwo = new JLabel();
                    nameTwo.setIcon(discTwo);
                    nameTwo.setText(game.getPlayers().get(PLAYER_TWO).getname());
                    nameTwo.setMinimumSize(new Dimension(100, 50));
                } catch (Exception e) {
                    System.out.println("File Not Found. Please try again.");
                }

		scoreOne = new JLabel();
		scoreOne.setText(String.valueOf(game.getPlayers().get(PLAYER_ONE).getScore()));
		scoreOne.setMinimumSize(new Dimension(150, 50));
		scoreOne.setPreferredSize(new Dimension(150, 50));

		scoreTwo = new JLabel();
		scoreTwo.setText(String.valueOf(game.getPlayers().get(PLAYER_TWO).getScore()));
		scoreTwo.setMinimumSize(new Dimension(150, 50));
		scoreTwo.setPreferredSize(new Dimension(150, 50));

		this.add(nameOne);
		this.add(scoreOne);
		this.add(nameTwo);
		this.add(scoreTwo);

	}

	public JLabel getname1() {
		return nameOne;
	}

	public void setfstname(JLabel nameOne) {
		if(nameOne==null) {
			throw new NullPointerException();
		}
		this.nameOne = nameOne;
	}

	public JLabel getname2() {
		return nameTwo;
	}

	public void set2ndname(JLabel nameTwo) {
		if(nameTwo==null) {
			throw new NullPointerException();
		}
		this.nameTwo = nameTwo;
	}

	public JLabel getScoreOne() {
		return scoreOne;
	}

	public void setScoreOne(JLabel scoreOne) {
		if(scoreOne==null) {
			throw new NullPointerException();
		}
		this.scoreOne = scoreOne;
	}

	public JLabel getScoreTwo() {
		return scoreTwo;
	}

	public void setScoreTwo(JLabel scoreTwo) {
		if(scoreTwo==null) {
			throw new NullPointerException();
		}
		this.scoreTwo = scoreTwo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(game, nameOne, nameTwo, scoreOne, scoreTwo);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			throw new NullPointerException();
		}
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		GameUi other = (GameUi) obj;
		return Objects.equals(game, other.game) && Objects.equals(nameOne, other.nameOne)
				&& Objects.equals(nameTwo, other.nameTwo) && Objects.equals(scoreOne, other.scoreOne)
				&& Objects.equals(scoreTwo, other.scoreTwo);
	}

	@Override
	public String toString() {
		return "GameUi [nameOne=" + nameOne + ", nameTwo=" + nameTwo + ", scoreOne=" + scoreOne + ", scoreTwo="
				+ scoreTwo + ", game=" + game + "]";
	}
	
}

package othello;

import core.Game;
import userInterface.OthelloUi;

public class Othello {

	// main is "static" because it's going to be called by the java interpreter
	// before any objects are created, thus, it does not need to be in an
	// instance of the Othello class to be called, it can simply be called
	// whenever.
	public static void main(String[] args) {
		Game game = Game.getInstance();
		new OthelloUi(game);
	}

}

package sk.tsystems.gamestudio.game.guessnumber;


import sk.tsystems.gamestudio.game.guessnumber.consoleui.*;
import sk.tsystems.gamestudio.game.guessnumber.core.*;
public class GuessNumber {

	public GuessNumber() {
		ConsoleUI game = new ConsoleUI();

		TheNumber number = new TheNumber(100);
		game.newGameStarted(number);

	}

	public static void main(String[] args) {
		new GuessNumber();
	}
}

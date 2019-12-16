package sk.tsystems.gamestudio.consoleUI;

import sk.tsystems.gamestudio.game.guessnumber.GuessNumber;
import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.npuzzle.Puzzle;
import java.util.Scanner;

public class Menu {

	String[] Games = {"Minesweeper", "GuessNumber", "Puzzle"};

	
	Scanner input = new Scanner(System.in);

	String user = System.getProperty("user.name");
	
	
	public void run() {

		System.out.println();
		System.out.println("Welcome to GameStudio "+ user);
		System.out.println("--------------------------------");
		showGames();
		chooseGame();
	}
	private void showGames() {
		int numberOfGame = 1;
		for (String game : Games) {
			System.out.println(numberOfGame + ". " + game);
			numberOfGame++;
		}
	}

	private void chooseGame() {
		System.out.println("Choose your game from 1 to 3 or exit with X: ");
		while (true) {
			String in = input.nextLine();
			try {
				if (in.toUpperCase().equals("X")) {
					System.err.println("GoodBye");
					System.exit(0);
				}
				int gameNum = Integer.parseInt(in);
				
					switch (gameNum) {
					case 1:
						new Minesweeper();
						break;
					case 2:
						new GuessNumber();
						break;
					case 3:
						new Puzzle();
					default:
					System.err.println("Invalid input! Try again");
						break;
					}
				
			} catch (Exception e) {
			System.err.println("Invalid input! Try again");
			}
		}

	}
}

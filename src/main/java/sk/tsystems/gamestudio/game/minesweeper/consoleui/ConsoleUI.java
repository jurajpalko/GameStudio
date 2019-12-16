package sk.tsystems.gamestudio.game.minesweeper.consoleui;

import sk.tsystems.gamestudio.consoleUI.Menu;
import sk.tsystems.gamestudio.game.minesweeper.*;
import sk.tsystems.gamestudio.game.minesweeper.core.*;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;
import sk.tsystems.gamestudio.service.GameStudioException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Starts the game.
	 * 
	 * @param field field of mines and clues
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;

		do {
			update();
			processInput();
			if (field.getState() == GameState.FAILED) {
				System.err.println("GAME OVER!!");
				update();
				Menu menu = new Menu();
				menu.run();
			} else if (field.getState() == GameState.SOLVED) {
				
				System.out.println();
				update();
				System.err.println("--- YOU WON!! ---");
				System.out.println("Your Score is: " + field.getScore());
				Menu menu = new Menu();
				menu.run();
			}
		} while (true);
	}

	/**
	 * Updates user interface - prints the field.
	 */
	@Override
	public void update() {
		for (int i = 0; i < field.getColumnCount(); i++) {
			if (i==0) {
				System.out.print(" ");				
			}
			System.out.print("  ");
			System.out.printf("%c", '0' + i);
		}
		System.out.println();

		for (int i = 0; i <= field.getRowCount() - 1; i++) {
			System.out.printf("%c", 'A' + i);

			for (int j = 0; j <= field.getColumnCount() - 1; j++) {
				Tile tile = field.getTile(i, j);

				if (tile.getState() == State.CLOSED) {
					System.out.print("  -");
				} else if (tile.getState() == State.MARKED) {
					System.out.print("  M");
				} else if (tile.getState() == State.OPEN) {
					if (tile instanceof Clue) {
						Clue clue = (Clue) tile;
						System.out.print("  " + clue.getValue());
					} else if (tile instanceof Mine) {
						System.out.print("  X");
					}
				}
			}
			System.out.println();
		}
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out.print("Please enter your selection <X> EXIT, <MA1> MARK, <OB4> OPEN:");
		String answer = readLine().trim().toUpperCase();
		Pattern pattern = Pattern.compile("(M|O)([A-I])([0-8])");
		Matcher matcher = pattern.matcher(answer);
		try {
			if (answer.equals("X")) {
			Menu menu = new Menu();
			menu.run();
//				System.err.println("Good bye");
//				System.exit(0);
			}
			if (matcher.matches()) {
				char c = matcher.group(2).charAt(0);
				int axisX = (int) c - 65;
				int axisY = Integer.parseInt(matcher.group(3));
				if (matcher.group(1).equals("M")) {
					field.markTile(axisX, axisY);
				}
				if (matcher.group(1).equals("O")) {
					field.openTile(axisX, axisY);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// throw new UnsupportedOperationException("Method processInput not yet
		// implemented");
	}

}

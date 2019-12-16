package sk.tsystems.gamestudio.game.npuzzle;

public class Puzzle {
	private UserInterface userInterface;

	public Puzzle() {
		userInterface = new ConsoleUI();
		Field field = new Field(10, 10);
		userInterface.newGameStarted(field);
	}

	public static void main(String[] args) {

		new Puzzle();

	}

}

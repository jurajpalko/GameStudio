package sk.tsystems.gamestudio.game.minesweeper.core;


import java.util.Random;


import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */
	
	private GameState state = GameState.PLAYING;

	
	
	// Counting Score
	
	private int maxScore;
	private int finScore;
	private long startMillies;
	
	
	public long getStartMillies() {
		return startMillies;
	}
	
	public long getScoreSeconds() {
		return (System.currentTimeMillis()-startMillies)/1000;
	}
	public int getScore() {
		
		return finScore;
	}
	
	
	
	
	
	/**
	 * Constructor.
	 *
	 * @param rowCount    row count
	 * @param columnCount column count
	 * @param mineCount   mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		maxScore = rowCount*columnCount*5;
		tiles = new Tile[rowCount][columnCount];
		startMillies = System.currentTimeMillis();

		
		// generate the field content
		generate();
	}

	/**
	 * Opens tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void openTile(int row, int column) {
		if (tiles[row][column].getState() == Tile.State.CLOSED) {
			tiles[row][column].setState(Tile.State.OPEN);
			if (tiles[row][column] instanceof Mine) {
				state = GameState.FAILED;
				return;
			} else if (tiles[row][column] instanceof Clue) {
				Clue clue = (Clue) tiles[row][column];
				if (clue.getValue() == 0) {
					openAdjacentTiles(row, column);
				}
			}

			if (isSolved()) {
				state = GameState.SOLVED;
				finScore = (int) (maxScore-getScoreSeconds());
				if(finScore<=0)
					finScore=0;
				
				return;
			}
		}
	}

	/**
	 * Marks tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */

	// zmeni status zvolenej dlazdice z CLOSED na MARKED
	// alebo z MARKED na CLOSED
	public void markTile(int row, int column) {
		if (tiles[row][column].getState() == State.CLOSED) {
			tiles[row][column].setState(State.MARKED);
		} else if (tiles[row][column].getState() == State.MARKED) {
			tiles[row][column].setState(State.CLOSED);
		}
	}

	/**
	 * Generates playing field.
	 */
	private void generate() {
		Random ran = new Random();
		int count = 0;
		// nahodne umiestni mineCount pocet dlazdic typu Mine na herne pole

		while (count < mineCount) {
			int r = ran.nextInt(rowCount);
			int c = ran.nextInt(columnCount);
			if (tiles[r][c] == null) {
				tiles[r][c] = new Mine();
				count++;
			}

		}

//		for (int i = 0; i < mineCount; i++) {
//			int r = ran.nextInt(rowCount);
//			int c = ran.nextInt(columnCount);
//			if (tiles[r][c] == null) {
//				tiles[r][c] = new Mine();
//			} else {
//				i--;
//			}
//		}

		// prejde cele pole a na prazdne dalzdice prida dlazdice typu Clue
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = new Clue(countAdjacentMines(i, j));
				}
			}
		}

	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	private boolean isSolved() {
		return getColumnCount() * getRowCount() - getNumberOf(State.OPEN) == mineCount;
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row    row number.
	 * @param column column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	private void openAdjacentTiles(int row, int column) {

		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						

							openTile(actRow, actColumn);

						
					}
				}
			}
		}

	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public GameState getState() {
		return state;
	}

	private int getNumberOf(Tile.State state) {
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (tiles[i][j].getState() == state) {
					count++;
				}
			}
		}
		return count;
	}
}

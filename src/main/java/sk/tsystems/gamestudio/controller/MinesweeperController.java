package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.game.minesweeper.core.*;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.consoleUI.Menu;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.minesweeper.*;

@Controller

@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesweeperController {
	private Field field;
	private boolean marking;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private MainController mainController;
	@RequestMapping("/minesweeper")
	public String index() {
		field = new Field(9, 9, 5);
		marking = false;

		return "minesweeper";
	}

	@RequestMapping("/minesweeper/switchMarking")
	public String switchMarking() {
		marking = !marking;
		return "minesweeper";
	}

	@RequestMapping("/minesweeper/openTile")
	public String openTile(int row, int column) {
		if (getStatus() == GameState.PLAYING) {
			field.openTile(row, column);
					}
		if (getStatus() == GameState.SOLVED && mainController.isLogged()) {
			scoreService
					.addScore(new Score(mainController.getLoggedPlayer().getName(), "mines", field.getScore()));

		}


		return "minesweeper";
	}

	@RequestMapping("/minesweeper/markTile")
	public String markTile(int row, int column) {
		if (getStatus() == GameState.PLAYING) {
			field.markTile(row, column);
		
		}
		if (getStatus() == GameState.SOLVED && mainController.isLogged()) {
			scoreService
					.addScore(new Score(mainController.getLoggedPlayer().getName(), "mines", field.getScore()));

		}

		return "minesweeper";
	}

	public boolean getMarking() {
		return marking;
	}

	public GameState getStatus() {
		return field.getState();

	}

	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<table class=\"table\">\n");

		for (int row = 0; row < field.getRowCount(); row++) {

			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {

				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (marking) {
					tileAction(tile, f, row, column, "markTile");
				} else {
					tileAction(tile, f, row, column, "openTile");
				}

				f.format("</td>\n");

			}
			f.format("</tr>\n");
		}

		f.format("</table>\n");

		return f.toString();
	}

	public void tileAction(Tile tile, Formatter f, int row, int column, String action) {

		if (tile.getState() == State.CLOSED) {

			f.format("<a href='/minesweeper/" + action
					+ "?row=%d&column=%d'><img src='/images/mines/closed.png'</img></a>", row, column);
		}
		if (tile.getState() == State.MARKED) {
			f.format("<a href='/minesweeper/" + action
					+ "?row=%d&column=%d'><img src='/images/mines/marked.png'</img></a>", row, column);
		}
		if (tile.getState() == State.OPEN) {

			if (tile instanceof Clue) {
				Clue clue = (Clue) tile;
				f.format("<img src='/images/mines/open%d.png'</img>", clue.getValue());

			} else if (tile instanceof Mine) {

				f.format("<img src='/images/mines/mine.png'</img>");

			}
		}
	}

	public List<Score> getScores() {
		return scoreService.getTopScores("mines");
	}

}

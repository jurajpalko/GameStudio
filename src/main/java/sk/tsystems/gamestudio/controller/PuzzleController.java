package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.game.npuzzle.Field;
import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.npuzzle.*;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {

	private Field field;
	private String comment;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private MainController mainController;

	@Autowired
	private CommentService commentService;

	@Autowired
	private RatingService ratingService;
	
	@RequestMapping("/puzzle")
	public String index() {

		field = new Field(4, 4);
		return "puzzle";
	}

	@RequestMapping("/puzzle/move")
	public String move(int tile) {
		try {
			field.move(tile);

			if (field.isState() && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "puzzle", field.getScore()));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "puzzle";
	}
	@RequestMapping("/puzzle/rate")
	public String rate(int rating) {
		if (mainController.isLogged()) {
			
			try {
				ratingService.setRating(new Rating("puzzle", rating, mainController.getLoggedPlayer().getName()));
				System.out.println("-------------------------" + rating);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "puzzle";
	}

	@RequestMapping("/puzzle/comment")
	public String comment(String comment) {
		if (comment.trim().length() > 0) {
			try {
				this.comment = comment;
				if (mainController.isLogged()) {
					commentService
							.addComment(new Comment(mainController.getLoggedPlayer().getName(), "puzzle", this.comment));

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "puzzle";
	}

	public String getMessage() {
		return "Hello from Java";
	}

	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<table class=\"table\">\n");

		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");

			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");

				Tile tile = field.getTile(row, column);

				if (tile.getValue() != 0) {
					f.format(
							"<a href='/puzzle/move?tile=%d'><img class=\"img-fluid\" src='/images/puzzle/img%d.jpg'</img></a>",
							field.getTile(row, column).getValue(), tile.getValue());
					f.format("</td>\n");

				}

			}
			f.format("</tr>\n");
		}

		f.format("</table>\n");

		return f.toString();
	}

	public boolean isSolved() {
		return field.isState();
	}

	public List<Score> getScores() {
		return scoreService.getTopScores("puzzle");
	}

	public List<Comment> getComment() {
		return commentService.getComments("puzzle");
	}

	public double getAverageRating() {
		double rating =0;
		try {
			rating = ratingService.getAverateRating("puzzle");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rating;
	}
}

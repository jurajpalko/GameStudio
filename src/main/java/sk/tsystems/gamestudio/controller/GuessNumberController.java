package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.guessnumber.core.TheNumber;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessNumberController {

	private TheNumber number;
	private int guess;
	private String message;
	private String comment;
	private int score;

	@Autowired
	private ScoreService scoreService;
	@Autowired
	private MainController mainController;
	@Autowired
	private CommentService commentService;
	@Autowired
	private RatingService ratingService;
	@RequestMapping("/guessnumber")
	public String index() {
		number = new TheNumber(100);
		guess = -1;
		score = 100;
		return "guessnumber";
	}

	@RequestMapping("/guessnumber/comment")
	public String comment(String comment) {
		if (comment.trim().length() > 0) {
			try {
				this.comment = comment;
				if (mainController.isLogged()) {
					commentService.addComment(
							new Comment(mainController.getLoggedPlayer().getName(), "guessnumber", this.comment));

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "guessnumber";
	}

	public boolean isPlaying() {
		return number.isPlaying();
	}

	public int getMaxNum() {
		return number.getMaxNum();

	}

	public int getGuess() {
		return guess;
	}

	public int getRandom() {
		return number.getRandomNum();
	}
	@RequestMapping("/guessnumber/rate")
	public String rate(int rating) {
		if(rating>0&&rating<6){
		if (mainController.isLogged()) {
			
			try {
				ratingService.setRating(new Rating("guessnumber", rating, mainController.getLoggedPlayer().getName()));
				System.out.println("-------------------------" + rating);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}
		return "guessnumber";
	}
	@RequestMapping("/guessnumber/guess")
	public String setGuess(String guess) {
		try {
			int g = Integer.parseInt(guess);
			this.guess = g;
			score -= 10;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "guessnumber";
	}

	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<form action=\"/guessnumber/guess\">");

		f.format("<input type=\"text\" name=\"guess\" min=\"0\" max=\"100\" required autofocus/>");

		f.format("<input type=\"submit\" value=\"submit guess\" /> ");

		f.format("</form>");

		return f.toString();
	}

	public String getMessage() {

		if (guess == getRandom()) {
			if (mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "guessnumber", score));

			}

			return "It sure is " + getRandom() + " You Won!!";
		}
		if (guess < getRandom())
			return "Your guess is too low, try again.";
		if (guess > getRandom())
			return "Your guess is too high, try again.";

		return message;
	}

	public List<Score> getScores() {
		return scoreService.getTopScores("guessnumber");
	}

	public List<Comment> getComment() {
		return commentService.getComments("guessnumber");
	}
	public double getAverageRating() {
		double rating =0;
		try {
			rating = ratingService.getAverateRating("guessnumber");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rating;
	}
}

package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.game.guessnumber.core.TheNumber;



@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessNumberController {

	private TheNumber number;
	private int guess;
	private String message;
	
	@RequestMapping("/guessnumber")
	public String index() {
		number = new TheNumber(100);
		guess = -1;
		
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
	
	
	@RequestMapping("/guessnumber/guess")
	public String setGuess(String guess) {
		try {
			int g = Integer.parseInt(guess);
			this.guess = g;
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return "guessnumber";
	}
	
	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<form action=\"/guessnumber/guess\">");
		
		f.format("<input type=\"text\" name=\"guess\" min=\"0\" max=\"100\" required />");
		
		f.format("<input type=\"submit\" value=\"submit guess\" /> ");
		
		f.format("</form>");
		
		
		
		
		return f.toString();
	}
	
	public String getMessage() {
		
		
		if (guess==getRandom()) 
			return "It sure is " + getRandom() + " You Won!!" ;
		if (guess<getRandom()) 
			return "Your guess is too low, try again.";
		if (guess>getRandom()) 
			return "Your guess is too high, try again.";
		
		return message;
	}
	
	
	
}




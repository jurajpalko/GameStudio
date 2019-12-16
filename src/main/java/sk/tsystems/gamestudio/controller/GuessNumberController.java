package sk.tsystems.gamestudio.controller;

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
	private int guess = -1;
	
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
	
	
	public int  getGuess() {
		return guess;
	}
	public int getRandom() {
		return number.getRandomNum();
	}
	
	@RequestMapping("/guessnumber/guess")
	public String setGuess(int guess) {
		this.guess = guess;
		return "guessnumber";
	}
}



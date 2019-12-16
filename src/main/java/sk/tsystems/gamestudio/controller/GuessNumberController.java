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

	
		
	
	@RequestMapping("/guessnumber")
	public String index() {
		TheNumber number = new TheNumber(100);
		

		return "guessnumber";
	}
	
	
	
	
	
}



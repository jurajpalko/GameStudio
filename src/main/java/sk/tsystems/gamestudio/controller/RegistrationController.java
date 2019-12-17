package sk.tsystems.gamestudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.service.PlayerService;


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class RegistrationController {
	private Player loggedPlayer;

	@Autowired
	PlayerService playerService;

		@RequestMapping("/registration")
		public String index() {
			return "registration";
		}
		

		@RequestMapping("/registration/register")
		public String register(Player player) {
			
			playerService.addPlayer(new Player(player.getName(), player.getPasswd()));
			return "redirect:/";
		}
		
		
		
		
		
		

}

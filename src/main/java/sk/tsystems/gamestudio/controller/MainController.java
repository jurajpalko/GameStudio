package sk.tsystems.gamestudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.service.PlayerService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {
private Player loggedPlayer;

@Autowired
PlayerService playerService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	

	@RequestMapping("/login")
	public String login(Player player) {
		
		try {
			if (playerService.getPlayer(player.getName()).getPasswd().equals(player.getPasswd())) {
				loggedPlayer = player;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public String logout() {
		loggedPlayer = null;
		return "redirect:/";
	}
	
	public boolean isLogged() {
		return loggedPlayer !=null;
	}
	public Player getLoggedPlayer() {
		return loggedPlayer;
	}

}

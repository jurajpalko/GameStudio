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
private String loginErr = "";
@Autowired
PlayerService playerService;

	@RequestMapping("/")
	public String index() {
		loginErr = "";
		return "index";
	}
	
	@RequestMapping("/notlogged")
	public String nlog() {
		
		return "index";
	}
	@RequestMapping("/login")
	public String login(Player player) {
		
		
		
		try {
			if (playerService.getPlayer(player.getName()).getPasswd().equals(player.getPasswd())) {
				loggedPlayer = player;
			}else {
				loginErr = "Login not successful";
				return "redirect:/notlogged";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Not able to login.");
			loginErr = "Login not successful!";
			e.printStackTrace();
		return "redirect:/notlogged";
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


	public String getLoginErr() {
		return loginErr;
	}


	

}

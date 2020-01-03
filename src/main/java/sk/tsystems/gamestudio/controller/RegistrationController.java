package sk.tsystems.gamestudio.controller;

import java.text.Format;

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
	private String name;
	private String registrationErr = "";

	@Autowired
	PlayerService playerService;

	@Autowired
	MainController mainController;

	@RequestMapping("/registration")
	public String index() {
		registrationErr = "";
		return "registration";
	}
	@RequestMapping("/notRegistered")
	public String nReg() {
		
		return "registration";
	}
	
	@RequestMapping("/registration/register")
	public String register(Player player) {
		if (player.getName().trim().length() > 0) {
			Player nameUnvailable = playerService.getPlayer(player.getName());
			try {
				if (nameUnvailable == null) {

					playerService.addPlayer(new Player(player.getName(), player.getPasswd()));
					mainController.login(player);

				} else {
					registrationErr="Name not available!";
					return "redirect:/notRegistered";
				}
			} catch (Exception e) {
				registrationErr="Registration not successful!";
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Not able to register.");
				return "redirect:/notRegistered";
			}
		}

		return "redirect:/";

	}

	public String getRegistrationErr() {
		return registrationErr;
	}

}

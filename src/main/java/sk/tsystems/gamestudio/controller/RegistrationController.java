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
	private String nameErr = "";
	private String passwordErr = "";

	@Autowired
	PlayerService playerService;

	@Autowired
	MainController mainController;

	@RequestMapping("/registration")
	public String index() {
		nameErr = "";
		passwordErr = "";
		return "registration";
	}

	@RequestMapping("/notRegistered")
	public String nReg() {

		return "registration";
	}

	@RequestMapping("/registration/register")
	public String register(Player player) {

		if (player.getName().toLowerCase().trim().length() > 0) {

			if (player.getName().trim().length() < 4 || player.getName().trim().length() > 12
					|| player.getPasswd().length() < 4 || player.getPasswd().length() > 12
					|| player.getName().contains(" ") || player.getPasswd().contains(" ")) {

				if (player.getName().trim().length() < 4) {
					nameErr = "Name too short!";

				} else if (player.getName().trim().length() > 12) {
					nameErr = "Name too long!";

				} else if (player.getName().contains(" ")) {
					nameErr = "Name can not contain spaces";
				} else {
					nameErr = "";
				}

				if (player.getPasswd().length() < 4) {
					passwordErr = "Password too short!";

				} else if (player.getPasswd().length() > 12) {
					passwordErr = "Password too long!";

				} else if (player.getPasswd().contains(" ")) {
					passwordErr = "Password can not contain spaces";
				} else {
					passwordErr = "";
				}
				return "redirect:/notRegistered";
			} else {
				passwordErr = "";
				nameErr = "";
				Player nameUnvailable = playerService.getPlayer(player.getName().toLowerCase());
				try {
					if (nameUnvailable == null) {

						playerService.addPlayer(new Player(player.getName().toLowerCase(), player.getPasswd()));
						mainController.login(player);

					} else {
						nameErr = "Name not available!";
						return "redirect:/notRegistered";
					}
				} catch (Exception e) {
					nameErr = "Registration not successful!";

					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Not able to register.");
					return "redirect:/notRegistered";
				}
			}
		}

		return "redirect:/";

	}

	public String getNameErr() {
		return nameErr;
	}

	public String getPasswordErr() {
		return passwordErr;
	}

}

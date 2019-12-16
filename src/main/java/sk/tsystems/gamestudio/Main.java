package sk.tsystems.gamestudio;

import sk.tsystems.gamestudio.consoleUI.Menu;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.guessnumber.GuessNumber;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.service.ScoreServiceJDBC;

public class Main {

	public static void main(String[] args) {
		
		
		
		
		ScoreService scoreService = new ScoreServiceJDBC();

		//scoreService.addScore(new Score("Ivan", "mines", 63));
		
//		for (Score score: scoreService.getTopScores("npuzzle")) {
//			System.out.println(score.getUsername()+" "+score.getValue());
//		}
		
		Menu menu = new Menu();
		menu.run();
		
	
		
		
		
		
		
//		System.out.println("Wellcome to GameStudio.");
//		System.out.print("Select your game:");
//		System.out.println();
		
	}

}

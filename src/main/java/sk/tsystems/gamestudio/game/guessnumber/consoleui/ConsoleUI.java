package sk.tsystems.gamestudio.game.guessnumber.consoleui;

import sk.tsystems.gamestudio.consoleUI.Menu;
import sk.tsystems.gamestudio.game.guessnumber.core.*;


import java.util.Scanner;



public class ConsoleUI {

		private TheNumber maxNum;

		private Scanner scanner = new Scanner(System.in);
		
		//private int guess = scanner.nextInt();
		
		
		public void newGameStarted(TheNumber maxNum) {
			this.maxNum=maxNum;
		

		
		
		do {
			System.out.println("Enter your gues from 0 to " + maxNum.getMaxNum() + " or X to exit");
			String in = scanner.nextLine().toUpperCase();
			if (in.equals("X")) {
				Menu menu = new Menu();
				menu.run();
				
			}
			try {
				int guess = Integer.parseInt(in);

					if (guess==maxNum.getRandomNum()) {
						System.out.println("You won!!");
						System.exit(0);
					}
					if (guess>maxNum.getRandomNum()) {
						
						System.out.println("Too high, try again!");
					}else {
						System.out.println("Too low, try again!");
					}

			} catch (Exception e) {
				System.err.println("wrong input");
			}

		} while (true);
		
		}

	}

	



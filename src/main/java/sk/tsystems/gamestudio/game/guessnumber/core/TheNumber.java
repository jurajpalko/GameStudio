package sk.tsystems.gamestudio.game.guessnumber.core;


import java.util.Random;

public class TheNumber {

		

		Random random = new Random();
		private final int maxNum;
		private final int randomNum;
		private int guess = 0;
		private boolean Playing = true;
		private int score;

		
		
		public TheNumber(int maxNum) {
			this.maxNum = maxNum;
			this.randomNum = random.nextInt(maxNum)+1;
			score = 100;
		}


		public int getRandomNum() {
			return randomNum;
		}
		public int getMaxNum() {
			return maxNum;
		}
		
		public void setGuess(int guess) {
			this.guess = guess;
		}


		public boolean isPlaying() {
			return Playing;
		}
		
	public int getScore() {
		return score;
	}
}

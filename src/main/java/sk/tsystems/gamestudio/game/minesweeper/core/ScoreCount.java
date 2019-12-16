package sk.tsystems.gamestudio.game.minesweeper.core;

public class ScoreCount {

	private int maxScore = 1000;
	private long startMillies= System.currentTimeMillis();
	
	
	public long getStartMillies() {
		return startMillies;
	}
	
	public long getScoreSeconds() {
		return (System.currentTimeMillis()-startMillies)/1000;
	}
	public int getScore() {
		
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	
	

	
	
	
}

package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating {
	@Id
	@GeneratedValue
	private int ident;
	private String game;
	private int rating;
	private String owner;
	
	public Rating() {
		
	}

	public Rating(String game, int value, String owner) {
		
		this.game = game;
		this.rating = value;
		this.owner = owner;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getValue() {
		return rating;
	}

	public void setValue(int value) {
		this.rating = value;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}

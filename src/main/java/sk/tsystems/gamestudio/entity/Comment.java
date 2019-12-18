package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private int ident;

	@NotEmpty
	private String username;
	@NotEmpty
	private String game;
	@NotEmpty
	private String content;
	public Comment() {

	}

	public Comment(String username, String game, String content) {

		this.username = username;
		this.game = game;
		this.content = content;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Player {

	@Id
	@GeneratedValue
	private int ident;
	@NotEmpty
	private String name;
	@NotEmpty
	private String passwd;

	public Player() {

	}

	public Player(String name, String passwd) {
	
		this.name = name;
		this.passwd = passwd;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
}

package sk.tsystems.gamestudio.entity;

public class Player {
private int ident;
private String name;
private String passwd;

public Player() {

}

public Player(int ident, String name, String passwd) {
	this.ident = ident;
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

package farmacia_legado.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	
	private SimpleIntegerProperty id;
	private String full_name;
	private SimpleStringProperty username;
	private SimpleIntegerProperty phone;
	private SimpleStringProperty turn;
	private SimpleIntegerProperty rol;
	private SimpleStringProperty email;
	private SimpleStringProperty password;
	
	public User(int id, String full_name, String turn, int rol) {
		this.id = new SimpleIntegerProperty(id);
		this.full_name = full_name;
		this.turn = new SimpleStringProperty(turn);
		this.rol = new SimpleIntegerProperty(rol);
	}

	public SimpleIntegerProperty getId() {
		return id;
	}

	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}
	
	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public SimpleStringProperty getUsername() {
		return username;
	}

	public void setUsername(SimpleStringProperty username) {
		this.username = username;
	}

	public SimpleIntegerProperty getPhone() {
		return phone;
	}

	public void setPhone(SimpleIntegerProperty phone) {
		this.phone = phone;
	}

	public SimpleStringProperty getTurn() {
		return turn;
	}

	public void setTurn(SimpleStringProperty turn) {
		this.turn = turn;
	}

	public SimpleIntegerProperty getRol() {
		return rol;
	}

	public void setRol(SimpleIntegerProperty rol) {
		this.rol = rol;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}

	public SimpleStringProperty getPassword() {
		return password;
	}

	public void setPassword(SimpleStringProperty password) {
		this.password = password;
	}
}

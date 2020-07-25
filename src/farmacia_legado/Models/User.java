package farmacia_legado.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	
	private SimpleIntegerProperty id;
	private String full_name;
	private String name;
	private String last_name;
	private String username;
	private String phone;
	private SimpleStringProperty turn;
	private SimpleStringProperty rol;
	private String email;
	private String password;
	
	public User(int id, String full_name, String turn, int rol) {
		this.id = new SimpleIntegerProperty(id);
		this.full_name = full_name;
		this.turn = new SimpleStringProperty(turn);
		String aux = null;
		if (rol == 2)
			aux = "Dueño";
		else if (rol == 3)
			aux = "Empleado";
		this.rol = new SimpleStringProperty(aux);
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public SimpleIntegerProperty idProperty() {
		return id;
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setFull_name(String name, String last_name) {
		this.full_name = name + " " + last_name;
	}
	
	public String getFull_name() {
		return full_name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setTurn(String turn) {
		this.turn.set(turn);
	}
	
	public SimpleStringProperty turnProperty() {
		return turn;
	}
	
	public String getTurn() {
		return turn.get();
	}
	
	public void setRol(String rol) {
		this.rol.set(rol);
	}
	
	public SimpleStringProperty rolProperty() {
		return rol;
	}
	
	public String getRol() {
		return rol.get();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}
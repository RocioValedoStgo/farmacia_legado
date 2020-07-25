package farmacia_legado.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Provider {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty address;
	private SimpleStringProperty email;
	private SimpleStringProperty phone;
	
	public Provider(int id, String name, String address, String email, String phone) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
	}
	
	public int getId() {
		return id.get();
	}
	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}
	public String getName() {
		return name.get();
	}
	public void setName(SimpleStringProperty name) {
		this.name = name;
	}
	public String getAddress() {
		return address.get();
	}
	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(SimpleStringProperty phone) {
		this.phone = phone;
	}
}

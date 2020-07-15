package farmacia_legado.Models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private String image;
	private SimpleFloatProperty price;
	private SimpleIntegerProperty quantity;
	private SimpleIntegerProperty provider_id;
	private SimpleIntegerProperty category_id;
	
	public Product(int id, String name, float price, int quantity) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleFloatProperty(price);
		this.quantity = new SimpleIntegerProperty(quantity);
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
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setPrice(float price) {
		this.price.set(price);
	}
	
	public SimpleFloatProperty priceProperty() {
		return price;
	}
	
	public float getPrice() {
		return price.get();
	}
	
	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
	}
	
	public SimpleIntegerProperty quantityProperty() {
		return quantity;
	}
	
	public int getQuantity() {
		return quantity.get();
	}
	
	public void setProvider(int provider_id) {
		this.provider_id.set(provider_id);
	}
	
	public SimpleIntegerProperty providerProperty() {
		return provider_id;
	}
	
	public int getProvider() {
		return provider_id.get();
	}
	
	public void setCategory(int category_id) {
		this.category_id.set(category_id);
	}
	
	public SimpleIntegerProperty categoryProperty() {
		return category_id;
	}
	
	public int getCategory() {
		return category_id.get();
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
}

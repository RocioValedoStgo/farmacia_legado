package farmacia_legado.Models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private String description;
	private String image;
	private SimpleFloatProperty price;
	private SimpleIntegerProperty quantity;
	private SimpleIntegerProperty provider_id;
	private int category_id;
	
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
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
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
	
	public void setprovider_id(int provider_id) {
		this.provider_id.set(provider_id);
	}
	
	public SimpleIntegerProperty provider_idProperty() {
		return provider_id;
	}
	
	public int getProvider_Id() {
		return provider_id.get();
	}
	
	public void setcategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	public int getCategory_Id() {
		return category_id;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
}

package farmacia_legado.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category {

	private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty father_id;
    private String image;
    
    public Category(int id, String name, String father_id) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.father_id = new SimpleStringProperty(father_id);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFather_Id() {
        return father_id.get();
    }

    public SimpleStringProperty father_idProperty() {
        return father_id;
    }

    public void setfather_id(String father_id) {
        this.father_id.set(father_id);
    }
    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}

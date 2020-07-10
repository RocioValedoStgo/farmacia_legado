package farmacia_legado.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category {
	
	private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty father_id;

    public Category(int id, String name, int father_id) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.father_id = new SimpleIntegerProperty(father_id);
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

    public int getfather_id() {
        return father_id.get();
    }

    public SimpleIntegerProperty father_idProperty() {
        return father_id;
    }

    public void setfather_id(int father_id) {
        this.father_id.set(father_id);
    }
}

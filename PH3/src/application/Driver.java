package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Driver {
    private final IntegerProperty id;
    private final IntegerProperty managerId;
    private final StringProperty name;
    private final StringProperty phone;
    private final StringProperty address;

    // Constructor
    public Driver(int id, int managerId, String name, String phone, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.managerId = new SimpleIntegerProperty(managerId);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
    }

    // Getter methods
    public int getId() {
        return id.get();
    }

    public int getManagerId() {
        return managerId.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getAddress() {
        return address.get();
    }

    // Property methods (useful for JavaFX binding)
    public IntegerProperty idProperty() {
        return id;
    }

    public IntegerProperty managerIdProperty() {
        return managerId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty addressProperty() {
        return address;
    }
}

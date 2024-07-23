package src.main.java.pixelcraft;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PCModel {
    private final StringProperty someProperty = new SimpleStringProperty();

    public String getSomeProperty() {
        return someProperty.get();
    }

    public void setSomeProperty(String value) {
        someProperty.set(value);
    }

    public StringProperty somePropertyProperty() {
        return someProperty;
    }

    public void someModelMethod() {
        // Update the model state
        setSomeProperty("New Value");
    }
}

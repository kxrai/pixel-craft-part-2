package src.main.java.pixelcraft;

import javafx.stage.Stage;
import java.util.Observer;
import java.util.Observable;

public class PCView implements Observer {
    private Stage stage;

    public PCView(Stage stage) {
        this.stage = stage;
        // Initialize your view components here
    }

    @Override
    public void update(Observable o, Object arg) {
        // Update the view based on model changes
    }
}

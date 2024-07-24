package pixelcraft.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pixelcraft.model.PCModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

public class PCView implements Observer {
    private Stage stage;
    private BorderPane pane;
    private ImageView imageView;
    private PCModel model;

    public PCView(Stage stage) {
        this.stage = stage;
        this.pane = new BorderPane();
        this.imageView = new ImageView();
        this.pane.setCenter(imageView);

        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("PixelCraft Image Converter");
        stage.show();

        setupUI();
    }

    private void setupUI() {
        // Add buttons and other UI elements
        Button loadButton = new Button("Load Image");
        loadButton.setOnAction(e -> loadImage());

        // Place the buttons in the pane (this example adds it to the top)
        pane.setTop(loadButton);
    }

    private void loadImage() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                Image image = new Image(new FileInputStream(file));
                model.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setModel(PCModel model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        imageView.setImage(model.getImage());
    }
}

//package pixelcraft;
//
//import controller.PCController;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import model.PCModel;
//import view.PCView;
//
///*
// * PixelCraftGUI class serves as the entry point for the application.
// * It sets up the MVC architecture and launches the GUI
// */
//public class PixelCraftGUI extends Application {
//
//    /**
//     * The start method is called when the application is launched
//     */
//    @Override
//    public void start(Stage stage) {
//        // Initialize model and view
//        PCModel model = new PCModel();
//        PCView view = new PCView(stage, model);
//
//        // Set up the view to observe changes in the model
//        model.addObserver(view);
//
//        // Initialize controller and set up button actions
//        PCController controller = new PCController(model, view);
//        controller.installControllers();
//    }
//
//    /**
//     * Main method is the entry point of the application to launch the project
//     */
//    public static void main(String[] args) {
//        launch();
//    }
//}

// File: PixelCraftGUI.java
package pixelcraft;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.PCView;
import model.PCModel;
import controller.PCController;

public class PixelCraftGUI extends Application {

    @Override
    public void start(Stage stage) {
        PCModel model = new PCModel();
        PCView view = new PCView(stage, model);
        PCController controller = new PCController(model, view);

        stage.setTitle("PixelCraft GUI");
        stage.setScene(view.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

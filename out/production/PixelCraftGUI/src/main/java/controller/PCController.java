package controller;

// Import necessary JavaFX and other libraries
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import model.*;
import view.PCView;
import java.io.File;
import java.io.IOException;

/**
 * PCController class handles the user interactions with the view
 * and coordinates with the model to apply transformations and manage image files
 */
public class PCController {

    // Reference to the model
    private final PCModel model;

    // Reference to the view
    private final PCView view;

    /**
     * Constructor to initialize controller with the model and view
     */
    public PCController(PCModel model, PCView view) {
        // Initialize the model
        this.model = model;
        // Initialize the view
        this.view = view;

        // Initialize button actions + keyboard shortcuts
        installControllers();
    }

    /**
     * This method initializes the button actions and keyboard shortcuts
     */
    public void installControllers() {
        // Initialize button actions
        initButtonActions();
        // Initialize keyboard shortcuts
        initKeyboardShortcuts(view.getScene());
    }

    /**
     * Initializes keyboard shortcuts for undo, redo, and save operations
     */
    private void initKeyboardShortcuts(Scene scene) {
        // Ctrl+Z for Undo
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN),
                () -> model.undo() // Call undo method in the model
        );

        // Ctrl+Y for Redo
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN),
                () -> model.redo() // Call redo method in the model
        );

        // Ctrl+S for Save
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
                () -> view.getBtnSave().fire() // Trigger the save action
        );
    }

    /**
     * Sets up actions for the buttons in the view.
     */
    private void initButtonActions() {
        // Open button action
        view.getBtnOpen().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Open file dialog
                File file = view.showOpenFileDialog();
                // Check if a file is selected
                if (file != null) {
                    try {
                        // Load the selected image
                        Image image = new Image(file.toURI().toString());
                        // Set the image in the model
                        model.setImage(image);
                        // Display the image in the view
                        view.setImage(image);
                    } catch (Exception e) {
                        // Handle any exceptions
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        // Save button action
        view.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the current image from the model
                Image image = model.getImage();
                // Check if the image is not null
                if (image != null) {
                    // Show the save file dialog
                    File file = view.showSaveFileDialog();
                    // Check if a file is selected
                    if (file != null) {
                        try {
                            // Save the image to the selected file
                            model.saveImage(model.getImage(), file.getAbsolutePath());
                            // Show alert message
                            view.showAlert("Image Saved", "Image saved successfully.");
                        } catch (IOException e) {
                            // Handle exception
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    // Show a warning dialog to the user if there is no image to save
                    view.showAlert("No Image", "There is no image to save.");
                }
            }
        });

        // Grayscale button action
        view.getBtnGrayscale().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the current image from the model
                Image image = model.getImage();
                // Check if an image is present
                if (image != null) {
                    // Create a new Grayscale converter
                    Grayscale grayscale = new Grayscale();
                    // Convert the image to grayscale
                    Image grayImage = grayscale.convertImage(image);
                    // Update the model with the new image
                    model.setImage(grayImage);
                    // Update the view with the new image
                    view.setImage(grayImage);
                }
            }
        });

        // Blur button action
        view.getBtnBlur().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the current image from the model
                Image image = model.getImage();
                // Check if an image is present
                if (image != null) {
                    // Create a new Blur converter
                    Blur blur = new Blur();
                    // Apply the blur effect
                    Image blurImage = blur.convertImage(image);
                    // Update the model with the new image
                    model.setImage(blurImage);
                    // Update the view with the new image
                    view.setImage(blurImage);
                }
            }
        });

        // Rotate button action
        view.getBtnRotate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the current image from the model
                Image image = model.getImage();
                // Check if an image is present
                if (image != null) {
                    // Create a new Rotate converter
                    Rotate rotate = new Rotate();
                    // Apply the rotation
                    Image rotateImage = rotate.convertImage(image);
                    // Update the model with the new image
                    model.setImage(rotateImage);
                    // Update the view with the new image
                    view.setImage(rotateImage);
                }
            }
        });

        // Warmer button action
        view.getBtnWarmer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the current image from the model
                Image image = model.getImage();
                // Check if an image is present
                if (image != null) {
                    // Create a new Warmer converter
                    Warmer warmer = new Warmer(50);
                    // Apply the warming effect
                    Image warmerImage = warmer.convertImage(image);
                    // Update the model with the new image
                    model.setImage(warmerImage);
                    // Update the view with the new image
                    view.setImage(warmerImage);
                }
            }
        });

        // Undo button action
        view.getBtnUndo().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the undo method
                model.undo();
            }
        });

        // Redo button action
        view.getBtnRedo().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the redo action
                model.redo();
            }
        });
    }

}

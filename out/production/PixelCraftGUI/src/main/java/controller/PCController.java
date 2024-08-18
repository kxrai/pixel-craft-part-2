//package controller;
//
//// Import necessary JavaFX and other libraries
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyCodeCombination;
//import javafx.scene.input.KeyCombination;
//import model.*;
//import view.PCView;
//import java.io.File;
//import java.io.IOException;
//
///**
// * PCController class handles the user interactions with the view
// * and coordinates with the model to apply transformations and manage image files
// */
//public class PCController {
//
//    // Reference to the model
//    private final PCModel model;
//
//    // Reference to the view
//    private final PCView view;
//
//    /**
//     * Constructor to initialize controller with the model and view
//     */
//    public PCController(PCModel model, PCView view) {
//        // Initialize the model
//        this.model = model;
//        // Initialize the view
//        this.view = view;
//
//        // Initialize button actions + keyboard shortcuts
//        installControllers();
//    }
//
//    /**
//     * This method initializes the button actions and keyboard shortcuts
//     */
//    public void installControllers() {
//        // Initialize button actions
//        initButtonActions();
//        // Initialize keyboard shortcuts
//        initKeyboardShortcuts(view.getScene());
//    }
//
//    /**
//     * Initializes keyboard shortcuts for undo, redo, and save operations
//     */
//    private void initKeyboardShortcuts(Scene scene) {
//        // Ctrl+Z for Undo
//        scene.getAccelerators().put(
//                new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN),
//                () -> model.undo() // Call undo method in the model
//        );
//
//        // Ctrl+Y for Redo
//        scene.getAccelerators().put(
//                new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN),
//                () -> model.redo() // Call redo method in the model
//        );
//
//        // Ctrl+S for Save
//        scene.getAccelerators().put(
//                new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
//                () -> view.getBtnSave().fire() // Trigger the save action
//        );
//    }
//
//    /**
//     * Sets up actions for the buttons in the view.
//     */
//    private void initButtonActions() {
//        // Open button action
//        view.getBtnOpen().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Open file dialog
//                File file = view.showOpenFileDialog();
//                // Check if a file is selected
//                if (file != null) {
//                    try {
//                        // Load the selected image
//                        Image image = new Image(file.toURI().toString());
//                        // Set the image in the model
//                        model.setImage(image);
//                        // Display the image in the view
//                        view.setImage(image);
//                    } catch (Exception e) {
//                        // Handle any exceptions
//                        System.out.println(e.getMessage());
//                    }
//                }
//            }
//        });
//
//        // Save button action
//        view.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Get the current image from the model
//                Image image = model.getImage();
//                // Check if the image is not null
//                if (image != null) {
//                    // Show the save file dialog
//                    File file = view.showSaveFileDialog();
//                    // Check if a file is selected
//                    if (file != null) {
//                        try {
//                            // Save the image to the selected file
//                            model.saveImage(model.getImage(), file.getAbsolutePath());
//                            // Show alert message
//                            view.showAlert("Image Saved", "Image saved successfully.");
//                        } catch (IOException e) {
//                            // Handle exception
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                } else {
//                    // Show a warning dialog to the user if there is no image to save
//                    view.showAlert("No Image", "There is no image to save.");
//                }
//            }
//        });
//
//        // Grayscale button action
//        view.getBtnGrayscale().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Get the current image from the model
//                Image image = model.getImage();
//                // Check if an image is present
//                if (image != null) {
//                    // Create a new Grayscale converter
//                    Grayscale grayscale = new Grayscale();
//                    // Convert the image to grayscale
//                    Image grayImage = grayscale.convertImage(image);
//                    // Update the model with the new image
//                    model.setImage(grayImage);
//                    // Update the view with the new image
//                    view.setImage(grayImage);
//                }
//            }
//        });
//
//        // Blur button action
//        view.getBtnBlur().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Get the current image from the model
//                Image image = model.getImage();
//                // Check if an image is present
//                if (image != null) {
//                    // Create a new Blur converter
//                    Blur blur = new Blur();
//                    // Apply the blur effect
//                    Image blurImage = blur.convertImage(image);
//                    // Update the model with the new image
//                    model.setImage(blurImage);
//                    // Update the view with the new image
//                    view.setImage(blurImage);
//                }
//            }
//        });
//
//        // Rotate button action
//        view.getBtnRotate().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Get the current image from the model
//                Image image = model.getImage();
//                // Check if an image is present
//                if (image != null) {
//                    // Create a new Rotate converter
//                    Rotate rotate = new Rotate();
//                    // Apply the rotation
//                    Image rotateImage = rotate.convertImage(image);
//                    // Update the model with the new image
//                    model.setImage(rotateImage);
//                    // Update the view with the new image
//                    view.setImage(rotateImage);
//                }
//            }
//        });
//
//        // Warmer button action
//        view.getBtnWarmer().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Get the current image from the model
//                Image image = model.getImage();
//                // Check if an image is present
//                if (image != null) {
//                    // Create a new Warmer converter
//                    Warmer warmer = new Warmer(50);
//                    // Apply the warming effect
//                    Image warmerImage = warmer.convertImage(image);
//                    // Update the model with the new image
//                    model.setImage(warmerImage);
//                    // Update the view with the new image
//                    view.setImage(warmerImage);
//                }
//            }
//        });
//
//        // Undo button action
//        view.getBtnUndo().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Call the undo method
//                model.undo();
//            }
//        });
//
//        // Redo button action
//        view.getBtnRedo().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Call the redo action
//                model.redo();
//            }
//        });
//    }
//
//}


// File: PCController.java
package controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import model.Blur;
import model.Grayscale;
import model.PCModel;
import model.Rotate;
import model.Warmer;
import view.PCView;

import java.io.File;
import java.io.IOException;

public class PCController {

    private final PCModel model;
    private final PCView view;

    public PCController(PCModel model, PCView view) {
        this.model = model;
        this.view = view;

        installControllers();
    }

    private void installControllers() {
        initButtonActions();
        initKeyboardShortcuts(view.getScene());
    }

    private void initKeyboardShortcuts(Scene scene) {
        // Ctrl+Z for Undo
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN),
                () -> model.undo()
        );

        // Ctrl+Y for Redo
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN),
                () -> model.redo()
        );

        // Ctrl+S for Save
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
                () -> view.getBtnSave().fire()
        );
    }

    private void initButtonActions() {
        // Open button action
        view.getBtnOpen().setOnAction(event -> handleOpen());

        // Save button action
        view.getBtnSave().setOnAction(event -> handleSave());

        // Grayscale button action
        view.getBtnGrayscale().setOnAction(event -> handleGrayscale());

        // Blur button action
        view.getBtnBlur().setOnAction(event -> handleBlur());

        // Rotate button action
        view.getBtnRotate().setOnAction(event -> handleRotate());

        // Warmer button action
        view.getBtnWarmer().setOnAction(event -> handleWarmer());

        // Undo button action
        view.getBtnUndo().setOnAction(event -> model.undo());

        // Redo button action
        view.getBtnRedo().setOnAction(event -> model.redo());

        // Next Image button action
        view.getBtnNextImage().setOnAction(event -> handleNextImage());

        // Previous Image button action
        view.getBtnPreviousImage().setOnAction(event -> handlePreviousImage());

        // Clear Annotations button action
        view.getBtnClearAnnotations().setOnAction(event -> handleClearAnnotations());

        // Start Annotations button action
        view.getBtnStartAnnotations().setOnAction(event -> handleStartAnnotations());
    }

    private void handleOpen() {
        File file = view.showOpenFileDialog();
        if (file != null) {
            try {
                Image image = new Image(file.toURI().toString());
                model.addImage(image);
                view.setImage(model.getCurrentImage());
            } catch (Exception e) {
                view.showAlert("Open Error", "Failed to open the image.");
            }
        }
    }

    private void handleSave() {
        Image image = model.getCurrentImage();
        if (image != null) {
            File file = view.showSaveFileDialog();
            if (file != null) {
                try {
                    model.saveImageWithAnnotations(image, view.getAnnotationTool().getCanvas(), file.getAbsolutePath());
                    view.showAlert("Image Saved", "Image and annotations saved successfully.");
                } catch (IOException e) {
                    view.showAlert("Save Error", "Failed to save the image.");
                }
            }
        } else {
            view.showAlert("No Image", "There is no image to save.");
        }
    }

    private void handleGrayscale() {
        Image image = model.getCurrentImage();
        if (image != null) {
            Grayscale grayscale = new Grayscale();
            Image grayImage = grayscale.convertImage(image);
            model.setImage(grayImage);
            view.setImage(grayImage);
        }
    }

    private void handleBlur() {
        Image image = model.getCurrentImage();
        if (image != null) {
            Blur blur = new Blur();
            Image blurImage = blur.convertImage(image);
            model.setImage(blurImage);
            view.setImage(blurImage);
        }
    }

    private void handleRotate() {
        Image image = model.getCurrentImage();
        if (image != null) {
            Rotate rotate = new Rotate();
            Image rotateImage = rotate.convertImage(image);
            model.setImage(rotateImage);
            view.setImage(rotateImage);
        }
    }

    private void handleWarmer() {
        Image image = model.getCurrentImage();
        if (image != null) {
            Warmer warmer = new Warmer(50);
            Image warmerImage = warmer.convertImage(image);
            model.setImage(warmerImage);
            view.setImage(warmerImage);
        }
    }

    private void handleNextImage() {
        model.nextImage();
        view.setImage(model.getCurrentImage());
    }

    private void handlePreviousImage() {
        model.previousImage();
        view.setImage(model.getCurrentImage());
    }

    private void handleClearAnnotations() {
        view.getAnnotationTool().clearCanvas();
        view.getAnnotationTool().setDrawingEnabled(false);
    }

    private void handleStartAnnotations() {
        view.getAnnotationTool().setDrawingEnabled(true);
    }
}


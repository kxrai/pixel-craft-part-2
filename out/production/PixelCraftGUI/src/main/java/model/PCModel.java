package model;

// Import necessary JavaFX and other libraries
import javafx.scene.image.Image;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Stack;

/**
 * PCModel class serves as the Model in the MVC architecture.
 * It manages the image data and provides methods to process and observe changes
 */
public class PCModel extends Observable {

    // Property to hold the current image
    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
    // List to keep track of applied converters
    private final ObservableList<Converter> converters = FXCollections.observableArrayList();
    // Stack to hold image history for undo operations
    private final Stack<Image> undoStack = new Stack<>();
    // Stack to hold image history for redo operations
    private final Stack<Image> redoStack = new Stack<>();

    /**
     * Gets the current image.
     */
    public Image getImage() {
        return image.get();
    }

    /**
     * Sets a new image and notifies observers of the change
     */
    public void setImage(Image newImage) {
        if (newImage != null) {
            // Push the current image to the undo stack before setting a new one
            undoStack.push(image.get());
            this.image.set(newImage);
            // Mark this object as having been changed
            setChanged();
            // Notify observers of the change
            notifyObservers();
        }
    }

    /**
     * Method to performs undo operation by restoring the last image from the undo stack
     */
    public void undo() {
        // If undoStack is not empty
        if (!undoStack.isEmpty()) {
            // Push the current image to the redo stack before undoing
            redoStack.push(image.get());
            // Pop the last image from the undo stack and set it as the current image
            Image previousImage = undoStack.pop();
            this.image.set(previousImage);
            // Mark this object as having been changed
            setChanged();
            // Notify observers of the change
            notifyObservers();
        }
    }

    /**
     * Method to performs redo operation by restoring the last image from the redo stack
     */
    public void redo() {
        // If redoStack is not empty
        if (!redoStack.isEmpty()) {
            // Push the current image to the undo stack before redoing
            undoStack.push(image.get());
            // Pop the last image from the redo stack and set it as the current image
            Image nextImage = redoStack.pop();
            image.set(nextImage);
            // Mark this object as having been changed
            setChanged();
            // Notify observers of the change
            notifyObservers();
        }
    }

    /**
     * Method to savesthe current image to a file
     */
    public void saveImage(Image image, String filePath) throws IOException {
        // Get the width and height of the image
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Create a writable image
        WritableImage writableImage = new WritableImage(width, height);

        // Get the pixel reader from the original image
        PixelReader pixelReader = image.getPixelReader();

        // Create a BufferedImage
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Copy the pixels from the Image to the BufferedImage
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, pixelReader.getArgb(x, y));
            }
        }

        // Save the BufferedImage as a PNG file
        try {
            ImageIO.write(bufferedImage, "png", new File(filePath));
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }
}

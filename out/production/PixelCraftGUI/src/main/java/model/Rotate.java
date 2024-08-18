package model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Rotate class to apply Rotate filter to images
 */
public class Rotate implements Converter {

    /**
     * Method to converts the input image by applying rotate effect
     */
    @Override
    public Image convertImage(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        // Create a writable image with the same dimensions as the input image
        WritableImage rotatedImage = new WritableImage(height, width);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = rotatedImage.getPixelWriter();

        // Iterate through each pixel in the original image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the pixel at the original location
                int pixel = reader.getArgb(x, y);
                // Set the pixel in the new location by rotating the image 90 degrees clockwise
                writer.setArgb(height - 1 - y, x, pixel);
            }
        }
        return rotatedImage;
    }
}

package model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Warmer class to apply Warmer filter to images
 */
public class Warmer implements Converter {

    private final int intensity;

    /**
     * Constructor to initializes the intensity
     */
    public Warmer(int intensity) {
        this.intensity = intensity;
    }

    /**
     * Method to converts the input image by applying a warmer effect
     */
    @Override
    public Image convertImage(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        // Create a writable image with the same dimensions as the input image
        WritableImage warmerImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = warmerImage.getPixelWriter();

        // Loop through each pixel in the image by row
        for (int y = 0; y < height; y++) {
            // Loop through each pixel in the image by column
            for (int x = 0; x < width; x++) {
                // Read the ARGB value of the current pixel
                ARGB pixel = new ARGB(reader.getArgb(x, y));
                // Increase the red channel by the intensity value, ensuring it does not exceed 255
                int newRed = Math.min(255, pixel.red + intensity);
                // Create a new ARGB object with the modified red value, keeping other channels the same
                ARGB warmerPixel = new ARGB(pixel.alpha, newRed, pixel.green, pixel.blue);
                // Write the new ARGB value to the corresponding pixel in the warmer image
                writer.setArgb(x, y, warmerPixel.toInt());
            }
        }
        return warmerImage;
    }
}

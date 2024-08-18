package model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Blur class to apply Blur filter to images
 */
public class Blur implements Converter {
    // To adjust the level of Blur
    private static final int NEIGHBORHOOD_SIZE = 7;
    private static final int RADIUS = NEIGHBORHOOD_SIZE / 2;

    /**
     * Method to converts the input image by applying a Blur effect
     */
    @Override
    public Image convertImage(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        // Create a writable image with the same dimensions as the input image
        WritableImage blurredImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = blurredImage.getPixelWriter();

        // Iterate over each pixel of the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // alpha, red, green, blue
                int[] rgba = new int[4];
                int count = 0;

                // Iterate over the pixel's 5x5 neighborhood
                for (int dy = -RADIUS; dy <= RADIUS; dy++) {
                    for (int dx = -RADIUS; dx <= RADIUS; dx++) {
                        // Calculate neighbor's coordinates
                        int nx = x + dx;
                        int ny = y + dy;

                        // Check if the neighbor is within image bounds
                        if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                            // Get the neighbor's pixel value
                            int pixel = reader.getArgb(nx, ny);
                            // Accumulate alpha
                            rgba[0] += (pixel >> 24) & 0xff;
                            // Accumulate red
                            rgba[1] += (pixel >> 16) & 0xff;
                            // Accumulate green
                            rgba[2] += (pixel >> 8) & 0xff;
                            // Accumulate blue
                            rgba[3] += pixel & 0xff;
                            count++;
                        }
                    }
                }

                // Average the accumulated values for each color component
                for (int i = 0; i < 4; i++) {
                    rgba[i] /= count;
                }

                // Combine the averaged components back into a single integer pixel value
                int newPixel = (rgba[0] << 24) | (rgba[1] << 16) | (rgba[2] << 8) | rgba[3];
                // Set the blurred pixel in the new image
                writer.setArgb(x, y, newPixel);
            }
        }
        return blurredImage;
    }
}

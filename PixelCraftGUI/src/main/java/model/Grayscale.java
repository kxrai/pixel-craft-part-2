package model;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Grayscale class to apply Grayscale filter to images
 */
public class Grayscale implements Converter {

    /**
     * Method to converts the input image by applying a Grayscale effect
     */
    @Override
    public Image convertImage(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        // Create a writable image with the same dimensions as the input image
        WritableImage greyImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = greyImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB before = new ARGB(reader.getArgb(x, y));
                // Calculate average
                int avg = (before.red + before.green + before.blue) / 3;
                // Replace RGB value with avg
                ARGB after = new ARGB(before.alpha, avg, avg, avg);
                writer.setArgb(x, y, after.toInt());
            }
        }
        return greyImage;
    }
}

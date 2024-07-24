package pixelcraft.converters;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import pixelcraft.model.ARGB;

public class Grayscale implements Converter {

    @Override
    public Image convertImage(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        WritableImage greyImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = greyImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB before = new ARGB(reader.getArgb(x, y));
                int avg = (before.red + before.green + before.blue) / 3;
                ARGB after = new ARGB(before.alpha, avg, avg, avg);
                writer.setArgb(x, y, after.toInt());
            }
        }
        return greyImage;
    }
}

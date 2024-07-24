package pixelcraft.model;

import javafx.scene.image.Image;
import java.util.Observable;

public class PCModel extends Observable {
    private Image currentImage;

    public Image getImage() {
        return currentImage;
    }

    public void setImage(Image image) {
        this.currentImage = image;
        setChanged();
        notifyObservers();
    }

    // Additional methods for applying converters
}

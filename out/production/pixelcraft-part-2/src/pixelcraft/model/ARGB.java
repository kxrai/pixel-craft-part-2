package pixelcraft.model;

public class ARGB {
    public int alpha;
    public int red;
    public int green;
    public int blue;

    public ARGB(int argb) {
        this.alpha = (argb >> 24) & 0xff;
        this.red = (argb >> 16) & 0xff;
        this.green = (argb >> 8) & 0xff;
        this.blue = argb & 0xff;
    }

    public ARGB(int alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int toInt() {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}

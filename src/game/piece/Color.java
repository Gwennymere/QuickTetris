package game.piece;

public enum Color {
    RED(255, 1, 1),
    ORANGE(255, 127, 1),
    YELLOW(255, 255, 1),
    GREEN(1, 255, 1),
    BLUE(1, 1, 255),
    INDIGO(46, 43, 95),
    VIOLET(139, 1, 255);

    private int red;
    private int green;
    private int blue;

    private Color(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}

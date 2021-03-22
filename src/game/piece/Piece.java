package game.piece;

import java.util.HashMap;
import java.util.Map;

public class Piece {
    public int yOffset = 0;
    public int xOffset = 0;
    private BoundingBox boundingBox;
    private Shape shape;
    private Color color;

    private static final Map<Shape, Color> COLOR_MAPPING = new HashMap<>() {{
        put(Shape.L, Color.RED);
        put(Shape._L, Color.BLUE);
        put(Shape.Z, Color.GREEN);
        put(Shape._Z, Color.VIOLET);
        put(Shape.T, Color.INDIGO);
        put(Shape.O, Color.YELLOW);
        put(Shape.I, Color.ORANGE);
    }};

    public Piece(Shape shape) {
        this.setShape(shape);
    }

    private void setShape(Shape shape) {
        this.shape = shape;
        color = COLOR_MAPPING.get(shape);
        this.boundingBox = new BoundingBox(shape);
    }

    public void rotate(boolean clockwise, int[][] surroundingAspect) {
        int directionModifier = -1;
        if (!clockwise) {
            directionModifier = 1;
        }
        this.boundingBox.rotate(directionModifier, surroundingAspect);
    }

    public int[][][] getColoredAspect() {
        final int[][] aspect = boundingBox.getAspect();
        final int[][][] coloredAspect = new int[aspect.length][aspect[0].length][3];
        for (int i = 0; i < aspect.length; i++) {
            for (int j = 0; j < aspect[0].length; j++) {
                coloredAspect[i][j][0] = aspect[i][j] * color.getRed();
                coloredAspect[i][j][1] = aspect[i][j] * color.getGreen();
                coloredAspect[i][j][2] = aspect[i][j] * color.getBlue();
            }
        }
        return coloredAspect;
    }

    public int getHeight() {
        return boundingBox.getHeight();
    }

    public int getWidth() {
        return boundingBox.getWidth();
    }

    public boolean moveDown() {
        return false;
    }
}

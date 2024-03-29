package game.piece;

import java.util.HashMap;
import java.util.Map;

public class Piece {
    private static final int DEFAULT_Y = 0;
    private static final int DEFAULT_X = 3;
    public int yOffset = DEFAULT_Y;
    public int xOffset = DEFAULT_X;
    private BoundingBox boundingBox;
    private Shape shape;
    private Color color;
    private boolean disabled = true;

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

    public boolean rotate(boolean clockwise, int[][] surroundingAspect) {
        int directionModifier = -1;
        if (!clockwise) {
            directionModifier = 1;
        }
        return this.boundingBox.rotate(directionModifier, surroundingAspect);
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

    public boolean moveDown(int[][] surroundingAspect) {
        final boolean boundingStateIsValid = boundingBox.isBoundingStateValid(surroundingAspect);
        if (boundingStateIsValid) {
            this.yOffset++;
            return true;
        }
        return false;
    }

    public boolean move(int dir, int[][] gridAspect) {
        final boolean boundingStateIsValid = boundingBox.isBoundingStateValid(gridAspect);
        if (boundingStateIsValid) {
            this.xOffset += dir;
            return true;
        }
        return false;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.disabled = isDisabled;
    }

    public void reinitialize(Shape newShape) {
        this.setShape(newShape);
        this.yOffset = DEFAULT_Y;
        this.xOffset = DEFAULT_X;
    }

    public boolean isBoundingStateValid(int[][] gridAspect) {
        return boundingBox.isBoundingStateValid(gridAspect);
    }

    public void reinitialize() {
        this.reinitialize(shape);
    }
}

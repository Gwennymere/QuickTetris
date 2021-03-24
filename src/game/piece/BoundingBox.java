package game.piece;

public class BoundingBox {
    private Shape shape;
    private int rotation = 0;

    public BoundingBox(Shape shape) {
        setShape(shape);
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int[][] getAspect() {
        return getRotatedAspect(0);
    }

    public int[][] getRotatedAspect(int rotation) {
        return shape.getAspect(this.rotation + rotation);
    }

    public boolean rotate(int directionModifier, int[][] surroundingAspect) {
        if (!isRotatedBoundingStateValid(surroundingAspect, directionModifier)) {
            return false;
        } else {
            this.rotation += directionModifier;
            return true;
        }
    }

    public boolean isBoundingStateValid(int[][] surroundingAspect) {
        return isRotatedBoundingStateValid(surroundingAspect, 0);
    }

    public boolean isRotatedBoundingStateValid(int[][] surroundingAspect, int rotation) {
        final int[][] ownAspect = this.getRotatedAspect(rotation);
        for (int i = 0; i < surroundingAspect.length; i++) {
            for (int j = 0; j < surroundingAspect[0].length; j++) {
                if (ownAspect[i][j] == 1 && surroundingAspect[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getHeight() {
        return getAspect().length;
    }

    public int getWidth() {
        return getAspect()[0].length;
    }
}

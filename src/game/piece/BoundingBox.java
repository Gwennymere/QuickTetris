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

    public void rotate(int directionModifier, int[][] surroundingAspect) {
        if (!isRotatedBoundingStateValid(surroundingAspect, directionModifier)) {
            System.out.println("Could not rotate to " + (rotation + directionModifier));
        } else {
            this.rotation += directionModifier;
            System.out.println("Could rotate to " + rotation);
        }
    }

    private boolean isBoundingStateValid(int[][] surroundingAspect) {
        return isRotatedBoundingStateValid(surroundingAspect, 0);
    }

    private boolean isRotatedBoundingStateValid(int[][] surroundingAspect, int rotation) {
        final int[][] ownAspect = this.getRotatedAspect(rotation);
        System.out.println("------------------");
        for (int i = 0; i < surroundingAspect.length; i++) {
            for (int j = 0; j < surroundingAspect[0].length; j++) {
                if (ownAspect[i][j] == 1 && surroundingAspect[i][j] == 1) {
                    System.out.print("X");
                    return false;
                } else if (ownAspect[i][j] == 1) {
                    System.out.print("r");
                } else if (surroundingAspect[i][j] == 0) {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println("------------------");
        return true;
    }

    public int getHeight() {
        return getAspect().length;
    }

    public int getWidth() {
        return getAspect()[0].length;
    }
}

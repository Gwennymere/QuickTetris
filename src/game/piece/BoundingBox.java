package game.piece;

public class BoundingBox {
    private Shape shape;
    private int rotation = 0;

    public BoundingBox(Shape shape) {
        setPiece(shape);
    }

    public void setPiece(Shape shape) {
        this.shape = shape;
    }

    public int[][] getAspect() {
        return shape.getAspect(this.rotation);
    }

    public void rotate(int directionModifier, int[][] surroundingAspect) {
        this.rotation += directionModifier;
        if (!isBoundingStateValid(surroundingAspect)) {
            this.rotation -= rotation;
            System.out.println("Could not rotate");
        } else {
            System.out.println("Could rotate");
        }
    }

    private boolean isBoundingStateValid(int[][] surroundingAspect) {
        final int[][] ownAspect = this.getAspect();
        for (int i = 0; i < surroundingAspect.length; i++){
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

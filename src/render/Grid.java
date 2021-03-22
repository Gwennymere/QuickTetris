package render;

public class Grid {
    private int[][][] data;

    public Grid(int width, int height) {
        this.data = new int[width][height][3];

        // DUMMY
        this.data[0][5][0] = 255;
    }

    public int getHeight() {
        return data[0].length;
    }

    public int getWidth() {
        return data.length;
    }

    public int[][] getAspectCutout(int xPos, int yPos, int width, int height) {
        int[][] surroundingAspect = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int gridX = x + xPos;
                final int gridY = y + yPos;
                if (gridX < 0 || gridX > this.getWidth() - 1 || gridY < 0 || gridY > this.getHeight() - 1) {
                    surroundingAspect[x][y] = 1;
                } else {
                    if (this.data[gridX][gridY][0] > 0) {
                        surroundingAspect[x][y] = 1;
                    } else {
                        surroundingAspect[x][y] = 0;
                    }
                }
            }
        }
        return surroundingAspect;
    }

    public void setData(int[][][] newData) {
        this.data = newData;
    }

    public int[][][] getData() {
        return data;
    }
}

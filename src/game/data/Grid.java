package game.data;

import java.util.Arrays;

public class Grid {
    private int[][][] data;
    private int[] filledLanes;

    public Grid(int width, int height) {
        this.data = new int[width][height][3];
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

    public void setAspectCutout(int[][][] cutout, int xPos, int yPos, boolean overrideNullData) {
        for (int x = 0; x < cutout.length; x++) {
            for (int y = 0; y < cutout[0].length; y++) {
                if (overrideNullData || cutout[x][y][0] > 0) {
                    data[x + xPos][y + yPos] = cutout[x][y];
                }
            }
        }
    }

    public int[] identifyFilledLanes(int from, int to) {
        filledLanes = new int[0];
        for (int y = from; y <= to; y++) {
            if (isLaneFilled(y)) {
                filledLanes = Arrays.copyOf(filledLanes, filledLanes.length + 1);
                filledLanes[filledLanes.length - 1] = y;
            }
        }
        return filledLanes;
    }

    public boolean isLaneFilled(int lane) {
        for (int x = 0; x < getWidth(); x++) {
            if (data[x][lane][0] == 0) {
                return false;
            }
        }
        return true;
    }

    public void clearAndDropLanes(int lane) {
        for (int y = lane; y > 0; y--) {
            for (int x = 0; x < getWidth(); x++) {
                data[x][y] = data[x][y - 1];
            }
        }
        clearLane(0);
    }

    private void clearLane(int lane) {
        for (int x = 0; x < getWidth(); x++) {
            data[x][lane] = new int[]{0, 0, 0};
        }
    }

    public void clearMultipleLanes(int[] lane) {
        for (int i = 0; i < lane.length; i++) {
            clearAndDropLanes(lane[i]);
        }
    }

    public void clear() {
        for (int y = 0; y < getHeight(); y++) {
            clearLane(y);
        }
    }
}

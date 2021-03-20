package render;

public class Grid {
    public int[][][] data;

    public Grid(int width, int height) {
        this.data = new int[width][height][3];

        // DUMMY
        this.data[0][5][0] = 255;
    }
}

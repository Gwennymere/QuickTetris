package render;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private final int spotWidth = 8;
    private final int spotHeight = spotWidth;
    private final int borderWidth = 1;
    private final int borderHeight = borderWidth;
    private Grid grid;
    private final Color borderColor = Color.GRAY;

    public Canvas(Grid grid) {
        super(true);
        this.grid = grid;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("repainting");
        for (int i = 0; i < grid.data.length; i++) {
            for (int j = 0; j < grid.data[0].length; j++) {
                drawSpot(i, j, g);
            }
        }
    }

    private void drawSpot(int i, int j, Graphics g) {
        g.setColor(borderColor);
        int totalWidth = spotWidth + 2 * borderWidth;
        int totalHeight = spotHeight + 2 * borderHeight;
        g.fillRect(i * totalWidth, j * totalHeight, totalWidth, totalHeight);
        g.setColor(new Color(grid.data[i][j][0], grid.data[i][j][1], grid.data[i][j][2]));
        g.fillRect(i * (spotWidth + borderWidth * 2), j * (spotHeight + borderWidth * 2), spotWidth, spotHeight);
    }
}

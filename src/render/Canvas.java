package render;

import game.piece.Piece;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private static final int CELL_WIDTH = 10;
    private static final int CELL_HEIGHT = CELL_WIDTH;
    private static final int BORDER_WIDTH = 1;
    private static final int BORDER_HEIGHT = BORDER_WIDTH;
    private static final int CUBE_WIDTH = CELL_WIDTH - 2 * BORDER_WIDTH;
    private static final int CUBE_HEIGHT = CELL_HEIGHT - 2 * BORDER_HEIGHT;
    private static final Color BORDER_COLOR = Color.GRAY;
    private GameData gameData;
    private final int gameBoardPosX;
    private final int gameBoardPosY;

    public Canvas(GameData gameData, int gameBoardPosX, int gameBoardPosY) {
        super(true);
        this.gameData = gameData;
        this.gameBoardPosX = gameBoardPosX % CELL_WIDTH;
        this.gameBoardPosY = gameBoardPosY % CELL_HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawGameBoardGrid(g);
        this.drawActivePiece(g);
        System.out.println("repainted");
    }

    private void drawGameBoardGrid(Graphics g) {
        this.drawAspect(gameData.getGrid().data, gameBoardPosX, gameBoardPosY, true, g);
    }

    private void drawActivePiece(Graphics g) {
        final Piece activePiece = gameData.getActivePiece();
        drawAspect(activePiece.getColoredAspect(), gameBoardPosX + activePiece.xOffset,
                gameBoardPosY + activePiece.yOffset, false, g);
    }

    private void drawAspect(int[][][] aspectData, int xPos, int yPos, boolean drawBlack, Graphics graphics) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < aspectData.length; i++) {
            for (int j = 0; j < aspectData[0].length; j++) {
                final int newR = aspectData[i][j][0];
                final int newG = aspectData[i][j][1];
                final int newB = aspectData[i][j][2];
                if (r != newR || g != newG || b != newB) {
                    r = newR;
                    g = newG;
                    b = newB;
                }
                if (drawBlack || r != 0 && g != 0 && b != 0) {
                    drawCell(xPos + i, yPos + j, new Color(r, g, b), graphics);
                }
            }
        }
    }

    private void drawCell(int xPos, int yPos, Color color, Graphics g) {
        final int[][][] gridData = gameData.getGrid().data;
        g.setColor(BORDER_COLOR);
        g.fillRect(xPos * CELL_WIDTH, yPos * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
        g.setColor(color);
        g.fillRect(xPos * CELL_WIDTH + BORDER_WIDTH, yPos * CELL_HEIGHT + BORDER_HEIGHT, CUBE_WIDTH + BORDER_WIDTH, CUBE_HEIGHT + BORDER_HEIGHT);
    }
}

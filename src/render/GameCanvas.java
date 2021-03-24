package render;

import game.data.GameData;
import game.piece.Piece;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    private static final int BOARD_OFFSET_X = 10;
    private static final int BOARD_OFFSET_Y = 10;
    private static final int BOARD_WIDTH = 300;
    private static final int CELL_WIDTH = (BOARD_WIDTH - BOARD_OFFSET_X * 4) / GameData.GRID_WIDTH;
    private static final int CELL_HEIGHT = CELL_WIDTH;
    private static final int BORDER_WIDTH = 1;
    private static final int BORDER_HEIGHT = BORDER_WIDTH;
    private static final int CUBE_WIDTH = CELL_WIDTH - 2 * BORDER_WIDTH;
    private static final int CUBE_HEIGHT = CELL_HEIGHT - 2 * BORDER_HEIGHT;
    private static final Color BORDER_COLOR = Color.GRAY;
    private GameData gameData;

    public GameCanvas() {
        super(true);
        this.gameData = GameData.getInstance();

        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.BASELINE;
        constraints.fill = GridBagConstraints.BOTH;
        setLayout(new GridBagLayout());
        add(new GameSidePanel(), constraints);

        System.out.println(this.getClass() + " created!");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawGameBoardGrid(g);
        this.drawActivePiece(g);
    }

    private void drawGameBoardGrid(Graphics g) {
        this.drawAspect(gameData.getGrid().getData(),0, 0, true, g);
    }

    private void drawActivePiece(Graphics g) {
        final Piece activePiece = gameData.getActivePiece();
        if(activePiece.isDisabled()) {
            return;
        }
        drawAspect(activePiece.getColoredAspect(), activePiece.xOffset, activePiece.yOffset, false, g);
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
        g.setColor(BORDER_COLOR);
        g.fillRect(xPos * CELL_WIDTH + BOARD_OFFSET_X, yPos * CELL_HEIGHT + BOARD_OFFSET_Y, CELL_WIDTH, CELL_HEIGHT);
        g.setColor(color);
        g.fillRect(xPos * CELL_WIDTH + BORDER_WIDTH + BOARD_OFFSET_X, yPos * CELL_HEIGHT + BORDER_HEIGHT + BOARD_OFFSET_Y, CUBE_WIDTH + BORDER_WIDTH, CUBE_HEIGHT + BORDER_HEIGHT);
    }
}

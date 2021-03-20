package game;

import game.piece.Piece;
import game.piece.Shape;
import input.KeyType;
import render.AppWindow;
import render.GameData;

public class GameManager {
    private final GameData gameData;
    private final Piece[] pieces = new Piece[2];
    private AppWindow appWindow;

    public GameManager(AppWindow appWindow) {
        this.appWindow = appWindow;
        this.gameData = GameData.getInstance();
        pieces[0] = new Piece(Shape._L);
        update();
    }

    public void update() {
        int[][][] gridData = gameData.getGrid().data;
        final int[][][] coloredAspect = pieces[0].getColoredAspect();
        for (int x = 0; x < pieces[0].getWidth(); x++) {
            final int yOffset = pieces[0].yOffset;
            if (yOffset > 0 && coloredAspect[x][0][0] >= 1) {
                for (int c = 0; c < gridData[x][0].length; c++) {
                    gridData[x][yOffset - 1][c] = 0;
                }
            }
            int yCutOff = yOffset - GameData.GRID_HEIGHT + pieces[0].getHeight();
            if (yCutOff < 0) {
                yCutOff = 0;
            }
            for (int y = 0; y < pieces[0].getHeight() - yCutOff; y++) {
                if (coloredAspect[x][y][0] > 0) {
                    for (int colorCode = 0; colorCode < 3; colorCode++) {
                        gridData[x][y + yOffset][colorCode] = coloredAspect[x][y][colorCode];
                    }
                }
            }
        }
        gameData.setGridData(gridData);
        appWindow.repaint();
    }

    public void dive() {
        this.pieces[0].yOffset++;
        pieces[0].moveDown();
        System.out.println(this.pieces[0].yOffset);
    }

    public void keyAction(KeyType keyType) {
        switch (keyType) {
            case DIVE:
                dive();
                break;
            case TURN_CLOCKWISE:
                rotatePiece(true);
                break;
        }
        update();
    }

    private void rotatePiece(boolean clockwise) {
        pieces[0].rotate(clockwise, getSurroundingAspect());
    }

    private int[][] getSurroundingAspect() {
        final Piece piece = pieces[0];
        int[][][] gridData = gameData.getGrid().data;

        int[][] surroundingAspect = new int[piece.getWidth()][piece.getHeight()];
        for (int x = 0; x < piece.getWidth(); x++) {
            for (int y = 0; y < piece.getHeight(); y++) {
                if (piece.getColoredAspect()[x][y][0] == 0 && gridData[x][y][0] > 0) {
                    surroundingAspect[x][y] = 1;
                } else {
                    surroundingAspect[x][y] = 0;
                }
            }
        }
        return surroundingAspect;
    }
}

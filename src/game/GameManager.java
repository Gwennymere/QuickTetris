package game;

import game.piece.Piece;
import input.KeyType;
import render.AppWindow;
import render.GameData;

public class GameManager {
    private final GameData gameData;
    private AppWindow appWindow;

    public GameManager(AppWindow appWindow) {
        this.appWindow = appWindow;
        this.gameData = GameData.getInstance();
        update();
    }

    public void update() {
        appWindow.repaint();
    }

    @Deprecated
    public void updateOld() {
        final Piece activePiece = gameData.getActivePiece();
        int[][][] gridData = gameData.getGrid().data;
        final int[][][] coloredAspect = activePiece.getColoredAspect();
        for (int x = 0; x < activePiece.getWidth(); x++) {
            final int yOffset = activePiece.yOffset;
            if (yOffset > 0 && coloredAspect[x][0][0] >= 1) {
                for (int c = 0; c < gridData[x][0].length; c++) {
                    gridData[x][yOffset - 1][c] = 0;
                }
            }
            int yCutOff = yOffset - GameData.GRID_HEIGHT + activePiece.getHeight();
            if (yCutOff < 0) {
                yCutOff = 0;
            }
            for (int y = 0; y < activePiece.getHeight() - yCutOff; y++) {
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
        gameData.getActivePiece().moveDown(gameData.getGridAspectOverlappingWithActivePieceWithOffset(0, 1));
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
        gameData.getActivePiece().rotate(clockwise, gameData.getGridAspectOverlappingWithActivePiece());
    }
}

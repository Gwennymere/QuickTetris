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

    public void divePiece() {
        gameData.getActivePiece().moveDown(gameData.getGridAspectOverlappingWithActivePieceWithOffset(0, 1));
    }

    public void keyAction(KeyType keyType) {
        switch (keyType) {
            case DIVE:
                divePiece();
                break;
            case TURN_CLOCKWISE:
                rotatePiece(true);
                break;
            case TURN_COUNTER_CLOCKWISE:
                rotatePiece(false);
                break;
            case MOVE_LEFT:
                shiftPiece(true);
                break;
            case MOVE_RIGHT:
                shiftPiece(false);
                break;
        }
        update();
    }

    private void shiftPiece(boolean direction) {
        int dir = 1;
        if (direction) {
            dir = -1;
        }
        gameData.getActivePiece().move(dir, gameData.getGridAspectOverlappingWithActivePieceWithOffset(dir, 0));
    }

    private void rotatePiece(boolean clockwise) {
        gameData.getActivePiece().rotate(clockwise, gameData.getGridAspectOverlappingWithActivePiece());
    }
}

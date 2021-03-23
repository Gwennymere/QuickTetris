package game;

import game.piece.Piece;
import game.piece.Shape;
import input.KeyType;
import render.AppWindow;
import render.GameData;

public class AppManager implements Runnable {
    private static final AppManager instance = new AppManager();

    private final GameData gameData;
    private final AppWindow appWindow;
    private int GAME_SPEED = 400;

    private boolean appIsRunning = true;

    private AppManager() {
        this.appWindow = AppWindow.getInstance();
        this.gameData = GameData.getInstance();
        redraw();
    }

    private void redraw() {
        appWindow.repaint();
    }

    public void keyAction(KeyType keyType) {
        switch (keyType) {
            case DIVE:
                movePieceDown();
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
            case FREEZE:
                movePieceDataToGridData();
                break;
            case ESCAPE:
                setAppRunning(!appIsRunning);
                break;
        }
        redraw();
    }

    private synchronized void setAppRunning(boolean running) {
        this.appIsRunning = running;
    }

    public synchronized boolean movePieceDown() {
        return gameData.getActivePiece().moveDown(gameData.getGridAspectOverlappingWithActivePieceWithOffset(0, 1));
    }

    private synchronized boolean shiftPiece(boolean direction) {
        int dir = 1;
        if (direction) {
            dir = -1;
        }
        return gameData.getActivePiece().move(dir, gameData.getGridAspectOverlappingWithActivePieceWithOffset(dir, 0));
    }

    private synchronized boolean rotatePiece(boolean clockwise) {
        return gameData.getActivePiece().rotate(clockwise, gameData.getGridAspectOverlappingWithActivePiece());
    }

    private synchronized void movePieceDataToGridData() {
        this.gameData.movePieceToGrid();
    }

    @Override
    public void run() {
        enableActivePiece();
        while (appIsRunning) {
            update();
            redraw();
            threadWait();
        }
    }

    private void threadWait() {
        try {
            Thread.sleep(GAME_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        final int[] lanes = gameData.getGrid().identifyFilledLanes(0, 19);
        final boolean filledLanesArePresent = lanes.length > 0;

        if (filledLanesArePresent) {
            System.out.println("deleting");
            gameData.getGrid().clearMultipleLanes(lanes);
            resetAndEnableActivePiece();
        } else if (!gameData.getActivePiece().isDisabled()) {
            System.out.println("moving");
            final boolean pieceIsMovable = movePieceDown();
            if (!pieceIsMovable) {
                System.out.println("wasnt moving");
                movePieceDataToGridData();
                disableActivePiece();
            }
        } else {
            resetAndEnableActivePiece();
        }
    }

    private void resetAndEnableActivePiece() {
        resetActivePiece();
        enableActivePiece();
    }

    private void disableActivePiece() {
        gameData.getActivePiece().setDisabled(true);
    }

    private void enableActivePiece() {
        gameData.getActivePiece().setDisabled(false);
    }

    private void resetActivePiece() {
        final Piece activePiece = this.gameData.getActivePiece();
        activePiece.reinitialize(Shape.randomShape());
        final boolean boundingStateValid = activePiece.isBoundingStateValid(gameData.getGridAspectOverlappingWithActivePiece());
        if (!boundingStateValid) {
            appIsRunning = false;
        }
    }

    public static AppManager getInstance() {
        return instance;
    }
}

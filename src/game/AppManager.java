package game;

import input.KeyType;
import render.AppWindow;
import render.GameData;

public class AppManager implements Runnable{
    private final GameData gameData;
    private final AppWindow appWindow;
    private int GAME_SPEED = 400;

    private boolean appIsRunning = true;

    public AppManager(AppWindow appWindow) {
        this.appWindow = appWindow;
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
                movePieceToGrid();
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

    private synchronized void movePieceToGrid() {
        this.gameData.movePieceToGrid();
    }

    @Override
    public void run() {
        while (appIsRunning) {
            final int[] lanes = gameData.getGrid().identifyFilledLanes(0, 19);
            gameData.getGrid().clearMultipleLanes(lanes);
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
        final boolean wasAbleToMove = movePieceDown();
        if (!wasAbleToMove) {
            movePieceToGrid();
        }
    }
}

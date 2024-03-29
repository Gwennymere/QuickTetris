package game;

import app.AppManager;
import game.data.GameData;
import game.piece.Piece;
import game.piece.Shape;
import input.KeyType;

public class GameManager implements Runnable {
    private static final GameManager instance = new GameManager();
    private static Thread thread;

    private final GameData gameData;
    private AppManager appManager;
    private int GAME_SPEED = 400;

    private boolean gameIsRunning = false;

    private GameManager() {
        this.gameData = GameData.getInstance();
        System.out.println(this.getClass() + " created!");
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
                setAppRunning(!gameIsRunning);
                break;
        }
    }

    private synchronized void setAppRunning(boolean running) {
        this.gameIsRunning = running;
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
        if (appManager == null) {
            appManager = AppManager.getInstance();
        }
        resetActivePiece();
        enableActivePiece();
        while (gameIsRunning) {
            update();
            appManager.requestRedraw();
            threadWait();
        }
        appManager.gameConcluded();
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
            gameData.addToScore((lanes.length + lanes.length) / 2 * GAME_SPEED);
            gameData.getGrid().clearMultipleLanes(lanes);
            resetAndEnableActivePiece();
        } else if (!gameData.getActivePiece().isDisabled()) {
            final boolean pieceIsMovable = movePieceDown();
            if (!pieceIsMovable) {
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
            gameIsRunning = false;
        }
    }

    public static GameManager getInstance() {
        return instance;
    }

    public void runNewGame() {
        this.gameIsRunning = true;
        this.gameData.getGrid().clear();
        this.thread = new Thread(this);
        this.thread.start();
    }
}

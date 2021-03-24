package game.data;

import game.piece.Piece;
import game.piece.Shape;
import listener.ScoreListenerManager;

public class GameData {
    private static final GameData instance = new GameData();
    private final ScoreListenerManager scoreListenerManager = ScoreListenerManager.getInstance();
    private final Grid grid;
    public final static int GRID_WIDTH = 10;
    public final static int GRID_HEIGHT = 20;
    private Piece activePiece = new Piece(Shape._L);
    private int score = 0;

    private GameData() {
        this.grid = new Grid(GRID_WIDTH, GRID_HEIGHT);
    }

    public Grid getGrid() {
        return grid;
    }

    public void movePieceToGrid() {
        grid.setAspectCutout(activePiece.getColoredAspect(), activePiece.xOffset, activePiece.yOffset, false);
    }

    public void overrideGridData(int[][][] newGridData) {
        this.grid.setData(newGridData);
    }

    public static GameData getInstance() {
        return instance;
    }

    public Piece getActivePiece() {
        return activePiece;
    }

    public int[][] getGridAspectOverlappingWithActivePieceWithOffset(int xOffset, int yOffset) {
        final Piece activePiece = getActivePiece();
        return grid.getAspectCutout(activePiece.xOffset + xOffset,
                activePiece.yOffset + yOffset,
                activePiece.getWidth(),
                activePiece.getHeight());
    }

    public int[][] getGridAspectOverlappingWithActivePiece() {
        return getGridAspectOverlappingWithActivePieceWithOffset(0, 0);
    }

    public void addToScore(int amount) {
        score += amount;
        scoreListenerManager.notify(score);
    }
}

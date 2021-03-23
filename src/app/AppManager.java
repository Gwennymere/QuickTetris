package app;

import game.GameManager;
import input.KeyType;
import render.AppWindow;

public class AppManager {
    private static AppManager instance = new AppManager();

    private final GameManager gameManager = GameManager.getInstance();
    private final AppWindow appWindow = AppWindow.getInstance();

    private AppManager() {
        System.out.println(this.getClass() + " created!");
    }

    public void keyAction(KeyType keyType) {
        gameManager.keyAction(keyType);
        requestRedraw();
    }

    public static AppManager getInstance() {
        return instance;
    }

    public void requestRedraw() {
        appWindow.repaint();
    }

    public void gameConcluded() {
        appWindow.addRestartButton();
    }

    public void startNewGame() {
        this.gameManager.runNewGame();
        this.appWindow.requestFocus();
    }
}

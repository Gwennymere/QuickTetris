package app;

import game.GameManager;
import input.KeyType;

public class AppManager {
    private static AppManager instance = new AppManager();

    private GameManager gameManager = GameManager.getInstance();

    private AppManager() {}

    public void keyAction(KeyType keyType) {
        gameManager.keyAction(keyType);
    }

    public static AppManager getInstance() {
        return instance;
    }
}

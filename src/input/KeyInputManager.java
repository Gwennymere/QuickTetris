package input;

import game.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputManager implements KeyListener {
    private static KeyInputManager instance;
    private GameManager gameManager;

    private KeyInputManager(GameManager gameManager) {
        setGameManager(gameManager);
    }

    public static KeyInputManager createAndGetInstance(GameManager gameManager) {
        if (instance != null) {
            instance.setGameManager(gameManager);
            return instance;
        }
        instance = new KeyInputManager(gameManager);
        return instance;
    }

    private void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        final int keyCode = e.getExtendedKeyCode();
        gameManager.keyAction(KeyType.get(keyCode));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package input;

import game.AppManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputManager implements KeyListener {
    private static KeyInputManager instance = new KeyInputManager();
    private AppManager appManager;

    private KeyInputManager() {
        setGameManager(AppManager.getInstance());
    }

    public static KeyInputManager getInstance() {
        return instance;
    }

    private void setGameManager(AppManager appManager) {
        this.appManager = appManager;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        final int keyCode = e.getExtendedKeyCode();
        appManager.keyAction(KeyType.get(keyCode));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

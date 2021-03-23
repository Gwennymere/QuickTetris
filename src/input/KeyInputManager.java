package input;

import app.AppManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputManager implements KeyListener {
    private static KeyInputManager instance = new KeyInputManager();
    private AppManager appManager;

    private KeyInputManager() {
        appManager = (AppManager.getInstance());
    }

    public static KeyInputManager getInstance() {
        return instance;
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

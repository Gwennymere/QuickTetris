import exceptions.ObjectAlreadyCreatedException;
import game.GameManager;
import input.KeyInputManager;
import render.AppWindow;

public class Main {
    public static void main(String[] args) {
        try {
            final AppWindow appWindow = AppWindow.createAndGet();
            final GameManager gameManager = new GameManager(appWindow);
            final KeyInputManager keyInputManager = KeyInputManager.createAndGetInstance(gameManager);
            appWindow.addKeyListener(keyInputManager);
            gameManager.update();
            appWindow.repaint();
        } catch (ObjectAlreadyCreatedException e) {
            e.printStackTrace();
        }
    }
}

import game.AppManager;
import input.KeyInputManager;
import render.AppWindow;

public class Main {
    public static void main(String[] args) {
        final AppManager appManager = AppManager.getInstance();
        final KeyInputManager keyInputManager = KeyInputManager.getInstance();
        AppWindow.getInstance().addKeyListener(keyInputManager);

        Thread app = new Thread(appManager);
        app.start();
    }
}

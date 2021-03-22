import exceptions.ObjectAlreadyCreatedException;
import game.AppManager;
import input.KeyInputManager;
import render.AppWindow;

public class Main {
    public static void main(String[] args) {
        try {
            final AppWindow appWindow = AppWindow.createAndGet();
            final AppManager appManager = new AppManager(appWindow);
            final KeyInputManager keyInputManager = KeyInputManager.createAndGetInstance(appManager);
            appWindow.addKeyListener(keyInputManager);

            Thread app = new Thread(appManager);
            app.start();
        } catch (ObjectAlreadyCreatedException e) {
            e.printStackTrace();
        }
    }
}

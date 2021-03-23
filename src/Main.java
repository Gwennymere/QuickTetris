import input.KeyInputManager;
import render.AppWindow;

public class Main {
    public static void main(String[] args) {
        AppWindow.getInstance().addKeyListener(KeyInputManager.getInstance());
        AppWindow.getInstance().setupMainMenu();
        AppWindow.getInstance().repaint();
    }
}

import game.GameManager;
import input.KeyInputManager;
import render.AppWindow;

public class Main {
    public static void main(String[] args) {
        AppWindow.getInstance().addKeyListener(KeyInputManager.getInstance());
        GameManager.getInstance().runNewGame();
    }
}

package render;

import javax.swing.*;

public class AppWindow extends JFrame {
    private static int WIDTH = 300;
    private static int HEIGHT = 650;

    private static AppWindow instance;
    private Canvas canvas;
    private GameData gameData;

    private AppWindow(GameData gameData) {
        super("QuickTetris");
        this.gameData = gameData;
        this.canvas = new Canvas(gameData, 50, 50);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(this.canvas);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static AppWindow getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new AppWindow(GameData.getInstance());
        return instance;
    }
}

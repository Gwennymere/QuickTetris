package render;

import game.AppManager;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    private static int WIDTH = 300;
    private static int HEIGHT = 650;

    private static AppWindow instance = new AppWindow(GameData.getInstance());
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
        return instance;
    }

    public void addRestartButton() {
        final Button restart = new Button("Restart");
        canvas.add(restart);
        restart.addActionListener(e -> {
            AppManager.getInstance().runNewGame();
            canvas.remove(restart);
        });
        setVisible(true);
    }
}

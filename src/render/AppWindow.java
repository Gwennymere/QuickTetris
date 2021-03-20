package render;

import exceptions.ObjectAlreadyCreatedException;

import javax.swing.*;

public class AppWindow extends JFrame {
    private static int WIDTH = 300;
    private static int HEIGHT = 650;

    private static AppWindow self;
    private Canvas canvas;
    private GameData gameData;

    private AppWindow(GameData gameData) {
        super("QuickTetris");
        this.gameData = gameData;
        this.canvas = new Canvas(gameData.getGrid());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(this.canvas);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static AppWindow createAndGet() throws ObjectAlreadyCreatedException {
        if (self != null) {
            throw new ObjectAlreadyCreatedException();
        }
        self = new AppWindow(GameData.getInstance());
        return self;
    }
}

package render;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class AppWindow extends JFrame {
    private static int WIDTH = 300;
    private static int HEIGHT = 650;

    private static AppWindow instance = new AppWindow(GameData.getInstance());
    private GameCanvas gameCanvas;
    private final Collection<Component> componentList = new ArrayList<>();
    private final JPanel mainMenu = new MainMenu();

    private AppWindow(GameData gameData) {
        super("QuickTetris");
        this.gameCanvas = new GameCanvas(gameData, 50, 50);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);


        System.out.println(this.getClass() + " created!");
    }

    public static AppWindow getInstance() {
        return instance;
    }

    public void setupMainMenu() {
        removeAllComponentsFromWindow();
        addComponent(this.mainMenu);
        repaint();
        setVisible(true);
    }

    public void setupGameScreen() {
        removeAllComponentsFromWindow();
        addComponent(this.gameCanvas);
        repaint();
        setVisible(true);
    }

    private void addComponent(Component comp) {
        componentList.add(comp);
        add(comp);
    }

    private void removeAllComponentsFromWindow() {
        for (Component comp : componentList) {
            this.remove(comp);
        }
        componentList.clear();
    }
}

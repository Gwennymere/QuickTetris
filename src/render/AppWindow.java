package render;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class AppWindow extends JFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 650;

    private static AppWindow instance = new AppWindow();
    private GameCanvas gameCanvas;
    private final Collection<Component> componentList = new ArrayList<>();
    private final JPanel mainMenu = new MainMenu();

    private AppWindow() {
        super("QuickTetris");
        this.gameCanvas = new GameCanvas();

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

        addComponent(gameCanvas);
        repaint();
        setVisible(true);
    }

    private void addComponent(Component comp) {
        this.addComponent(comp, null);
    }

    private void addComponent(Component comp, GridBagConstraints c) {
        componentList.add(comp);
        if (c != null) {
            add(comp, c);
        } else {
            add(comp);
        }
    }

    private void removeAllComponentsFromWindow() {
        for (Component comp : componentList) {
            this.remove(comp);
        }
        componentList.clear();
    }
}

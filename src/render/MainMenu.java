package render;

import app.AppManager;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private final Button BTN_START = new Button("Start");

    public MainMenu() {
        setupButton();
    }

    private void setupButton() {
        BTN_START.setBounds(100, 50, 100, 50);
        BTN_START.addActionListener(e -> {
            AppManager.getInstance().startNewGame();
        });

        this.add(BTN_START);
    }
}

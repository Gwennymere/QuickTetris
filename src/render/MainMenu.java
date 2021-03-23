package render;

import app.AppManager;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private final Button BTN_START = new Button("Start");
    private final Button BTN_QUIT = new Button("Quit");
    private final Dimension buttonDimension = new Dimension(200, 50);
    private final GridBagLayout layout = new GridBagLayout();

    public MainMenu() {
        setupLayout();
        setupButton();
    }

    private void setupLayout() {
        this.setLayout(layout);
    }

    private void setupButton() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        BTN_START.addActionListener(e -> AppManager.getInstance().startNewGame());
        BTN_START.setPreferredSize(buttonDimension);
        this.add(BTN_START, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        BTN_QUIT.addActionListener(e -> AppWindow.getInstance().dispose());
        BTN_QUIT.setPreferredSize(buttonDimension);
        this.add(BTN_QUIT, constraints);
    }
}

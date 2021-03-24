package render;

import javax.swing.*;
import java.awt.*;

public class GameSidePanel extends JPanel {
    public GameSidePanel() {
        setLayout(new GridBagLayout());

        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTHEAST;

        add(new ScoreView(), constraints);
    }
}

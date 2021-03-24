package render;

import listener.ScoreListener;
import listener.ScoreListenerManager;

import javax.swing.*;
import java.awt.*;

public class ScoreView extends JLabel implements ScoreListener {
    public ScoreView() {
        super("0");
        ScoreListenerManager.getInstance().register(this);
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
    }

    public void prepareDeletion() {
        ScoreListenerManager.getInstance().deregister(this);
    }

    @Override
    public void onScoreChange(int score) {
        this.setText(Integer.toString(score));
    }
}

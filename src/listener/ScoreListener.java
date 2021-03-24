package listener;

import java.util.EventListener;

public interface ScoreListener extends EventListener {
    void onScoreChange(int score);
}

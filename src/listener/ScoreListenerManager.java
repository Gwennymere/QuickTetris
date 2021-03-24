package listener;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreListenerManager {
    private static ScoreListenerManager instance = new ScoreListenerManager();
    private Collection<ScoreListener> scoreListenerList = new ArrayList<>();

    private ScoreListenerManager() {}

    public void register(ScoreListener scoreListener) {
        scoreListenerList.add(scoreListener);
    }

    public void deregister(ScoreListener scoreListener) {
        scoreListenerList.remove(scoreListener);
    }

    public void notify(int score) {
        for (ScoreListener listener: scoreListenerList) {
            listener.onScoreChange(score);
        }
    }

    public static ScoreListenerManager getInstance() {
        return instance;
    }
}

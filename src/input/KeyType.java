package input;

import render.GameData;

import java.util.HashMap;
import java.util.Map;

public enum KeyType {
    TURN_CLOCKWISE,
    TURN_COUNTER_CLOCKWISE,
    DIVE,
    NONE;

    private static final GameData gameData = GameData.getInstance();

    private static final Map<Integer, KeyType> ingameKeyCodeMap = new HashMap<>(){{
        put(68, TURN_CLOCKWISE);
        put(39, TURN_CLOCKWISE);
        put(65, TURN_COUNTER_CLOCKWISE);
        put(37, TURN_COUNTER_CLOCKWISE);
        put(83, DIVE);
        put(40, DIVE);
    }};

    public static KeyType get(int keyCode) {
        switch (KeyType.gameData.getView()) {
            case INGAME:
                final KeyType keyType = ingameKeyCodeMap.get(keyCode);
                return keyType != null ? keyType : NONE;
        }
        return NONE;
    }
}

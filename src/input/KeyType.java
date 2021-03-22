package input;

import render.GameData;

import java.util.HashMap;
import java.util.Map;

public enum KeyType {
    TURN_CLOCKWISE,
    TURN_COUNTER_CLOCKWISE,
    MOVE_LEFT,
    MOVE_RIGHT,
    DIVE,
    FREEZE,
    ESCAPE,
    NONE;

    private static final GameData gameData = GameData.getInstance();

    private static final Map<Integer, KeyType> ingameKeyCodeMap = new HashMap<>(){{
        put(69, TURN_CLOCKWISE);
        put(81, TURN_COUNTER_CLOCKWISE);
        put(68, MOVE_RIGHT);
        put(39, MOVE_RIGHT);
        put(65, MOVE_LEFT);
        put(37, MOVE_LEFT);
        put(32, FREEZE);
        put(83, DIVE);
        put(40, DIVE);
        put(27, ESCAPE);
    }};

    public static KeyType get(int keyCode) {
        System.out.print(keyCode);
        switch (KeyType.gameData.getView()) {
            case INGAME:
                final KeyType keyType = ingameKeyCodeMap.get(keyCode);
                return keyType != null ? keyType : NONE;
        }
        return NONE;
    }
}

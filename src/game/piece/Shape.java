package game.piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Shape {
    L(new int[][][]
            {{
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0}
            }, {
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 1}
            }, {
                    {0, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0}
            }, {
                    {1, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0}
            }}),
    _L(new int[][][]
            {{
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 1}
            }, {
                    {0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 0}
            }, {
                    {1, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            }, {
                    {0, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0}
            }}),
    Z(new int[][][]
            {{
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 0, 1}
            }, {
                    {1, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0}
            }, {
                    {0, 0, 1},
                    {0, 1, 1},
                    {0, 1, 0}
            }, {
                    {1, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0}
            }}),
    _Z(new int[][][]
            {{
                    {1, 0, 0},
                    {1, 1, 0},
                    {0, 1, 0}
            }, {
                    {0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0}
            }, {
                    {1, 0, 0},
                    {1, 1, 0},
                    {0, 1, 0}
            }, {
                    {0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0}
            }}),
    I(new int[][][]
            {{
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }, {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            }, {
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }, {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            }}),
    O(new int[][][]{{{1, 1}, {1, 1}}}),
    T(new int[][][]
            {{
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 1, 0}
            }, {
                    {1, 1, 1},
                    {0, 1, 0},
                    {0, 0, 0}
            }, {
                    {0, 1, 0},
                    {1, 1, 0},
                    {0, 1, 0}
            }, {
                    {0, 1, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            }});

    private int[][][] layout;

    private Shape(int[][][] layout) {
        this.layout = layout;
    }

    public int[][] getAspect(int rotation) {
        final int depth = this.getDepth();
        int actualRot = rotation % depth;
        if (actualRot < 0) {
            actualRot += depth;
        }
        return this.layout[actualRot];
    }

    public int getDepth() {
        return layout.length;
    }

    private static final List<Shape> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Shape randomShape() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

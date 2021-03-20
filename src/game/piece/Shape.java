package game.piece;

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
        int actualRot = rotation % layout.length;
        return this.layout[actualRot];
    }
}

package snake;

import java.awt.*;

public class WorldCell extends Cell{

    /**
     * Create a cell of a specified type
     *
     * @param x
     * @param y
     */
    public WorldCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int eat() {
        return 0;
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }
}

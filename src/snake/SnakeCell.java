package snake;

import java.awt.*;

public class SnakeCell extends Cell{
    /**
     * Create a cell of a specified type
     *
     * @param x
     * @param y
     */
    public SnakeCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int eat() {
        return -1;
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}

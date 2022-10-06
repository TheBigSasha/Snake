package snake;

import java.awt.*;

public class FoodCell extends Cell {
    /**
     * Create a cell of a specified type
     *
     * @param x
     * @param y
     */
    public FoodCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int eat() {
        SnakeApp.PERIOD = Math.max(SnakeApp.PERIOD - 50, 50);
        return 1;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
